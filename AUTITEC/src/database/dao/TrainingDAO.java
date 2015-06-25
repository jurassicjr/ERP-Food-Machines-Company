package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import model.Employee;
import model.Training;
import database.DataBase;

public class TrainingDAO {

	private DataBase dataBase;

	public TrainingDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(Map<String, Object> map) {
		String title = (String) map.get("title");
		String objetive = (String) map.get("objective");
		Date date = (Date) map.get("date");
		List<Employee> list = (List<Employee>) map.get("list");
		String motive = (String) map.get("motive");
		String eventType = (String) map.get("eventType");
		String period = (String) map.get("period");
		String duration = (String) map.get("duration");
		java.sql.Date trainingDate = new java.sql.Date(date.getTime());
		String place = (String) map.get("place");
		
		int trainingID = dataBase.getAutoIncrementValue("training");
		String sqlTraining = "INSERT INTO training(title, objective, date, motive, event_type, period, duration, place) VALUES(?,?,?,?,?,?,?,?)";
		Object[] objTraining = new Object[]{title, objetive, trainingDate, motive, eventType, period, duration, place};
		
		try{
			dataBase.executeUpdate(sqlTraining, objTraining);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String sqlAssociation = "INSERT INTO training_employee_relation(training, employee) VALUES(?,?)";
		
		list.forEach(new Consumer<Employee>() {

			@Override
			public void accept(Employee e) {
				Object[] obj = new Object[]{trainingID, e.getId()};
				try{
					dataBase.executeUpdate(sqlAssociation, obj);
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			
		});
		
	}

	public List<Training> getTraining() {
		String sql = "SELECT * FROM training";
		List<Training> list = new ArrayList<Training>();
		try(ResultSet rs = dataBase.executeQuery(sql)){
			while(rs.next()){
			Training t = new Training();
			
			int id = rs.getInt("id");
			Date date = rs.getDate("date");
			String duration = rs.getString("Duration");
			List<Employee> employeeList = getEmployeeFromTraining(id);
			String eventType = rs.getString("event_type");
			String motive = rs.getString("motive");
			String objective = rs.getString("objective");
			String period = rs.getString("period");
			String place = rs.getString("place");
			String title = rs.getString("title");
			t.setTitle(title);
			t.setPlace(place);
			t.setPeriod(period);
			t.setObjective(objective);
			t.setMotive(motive);
			t.setEventType(eventType);
			t.setDate(date);
			t.setDuration(duration);
			t.setEmployeeList(employeeList);
			list.add(t);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Employee> getEmployeeFromTraining(int id){
		List<Employee> list = new ArrayList<Employee>();
		String sql = "SELECT * FROM training_employee_relation WHERE training = ?";
		try(ResultSet rs = dataBase.executeQuery(sql, id)){
			list = new ArrayList<Employee>();
			new EmployeeDAO();
			while(rs.next()){
				int empID = rs.getInt("employee");
				Employee e = EmployeeDAO.getEmployeeById(empID);
				list.add(e);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
