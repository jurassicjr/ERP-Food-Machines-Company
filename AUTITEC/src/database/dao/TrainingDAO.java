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
		String otherMotive = (String) map.get("otherMotive");
		
		int trainingID = dataBase.getAutoIncrementValue("training");
		String sqlTraining = "INSERT INTO training(title, objective, date, motive, event_type, period, duration, place, other_motive) VALUES(?,?,?,?,?,?,?,?,?)";
		Object[] objTraining = new Object[]{title, objetive, trainingDate, motive, eventType, period, duration, place, otherMotive};
		
		try{
			dataBase.executeUpdate(sqlTraining, objTraining);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String sqlAssociation = "INSERT INTO training_employee_relation(training, employee, is_completed) VALUES(?,?, ?)";
		
		list.forEach(new Consumer<Employee>() {

			@Override
			public void accept(Employee e) {
				Object[] obj = new Object[]{trainingID, e.getId(), false};
				try{
					dataBase.executeUpdate(sqlAssociation, obj);
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
			
		});
		
		String query = "INSERT INTO training_status(training, training_status, con_can_date) VALUES (?,?,?)";
		Object[] data = new Object[]{trainingID, 0, null};
		dataBase.executeUpdate(query, data);
		
	}
	
	

	/**
	 * Consulta o banco de dados e retorna todos os treinamentos registrados que ainda não tenham sido concluidos ou cancelados.
	 * @return List<training> lista com os treinamentos adquiridos da busca no banco de dados.
	 */
	public List<Training> getTraining() {
		String sql = "SELECT * FROM training where id in ";
		List<Training> list = new ArrayList<Training>();
		try(ResultSet rs = dataBase.executeQuery(sql)){
			while(rs.next()){
			Training t = new Training();
			
			int id = rs.getInt("id");
			Date date = rs.getDate("date");
			String duration = rs.getString("duration");
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
			t.setId(id);
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

	public List<Training> getTrainingsListByEmployee(int id) {
		String sql = "SELECT *FROM training_employee_relation WHERE employee = ? AND is_completed = ?";
		Object[] data = new Object[] {id, true};
		List<Training> trainingList = new ArrayList<Training>();
		try(ResultSet rs = dataBase.executeQuery(sql, data)){
			while(rs.next()) {
				int trainingID = rs.getInt("training");
				Training t = getTrainingByID(trainingID);
				trainingList.add(t);
			}
			return trainingList;
		} catch (SQLException e) {
	        e.printStackTrace();
        }
		
		return trainingList;
    }

	private Training getTrainingByID(int trainingID) {
		String sql = "SELECT * FROM training WHERE id = ?";
		Training t = new Training();
		try(ResultSet rs = dataBase.executeQuery(sql, trainingID)){
			if(rs.next()){
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
			}
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Remove o registro de treinamentos no banco de dados.
	 * @param training treinamento à ser deletado.
	 */
	public void delete(Training training) {
		int id = training.getId();
	    String query = "delete from training where id = ?";
	    String statusQuery = "delele from training_status where training = ";
	    String employeeAssociationQuery = "delete from training_employee_relation where training = ?";
	    dataBase.executeUpdate(employeeAssociationQuery, id);
	    dataBase.executeUpdate(statusQuery, id);
	    dataBase.executeUpdate(query, id);
	}

	/**
	 * Método responsável por cancelar o treinamento desejado.
	 * 0 - Ativo.
	 * 1 - Concluido.
	 * -1 - Cancelado
	 * @param map com o traimento a ser cancelado e o motivo de seu cancelamento.
	 */
	public void cancel(Map<String, Object> map) {
	    String query = "UPDATE training_status SET training_status = ? AND con_can_date = ? AND motive = ? where training = ?";
	    String motive = (String) map.get("motive");
	    Training training = (Training) map.get("training");
	    int id = training.getId();
	    Date cancelDate = new Date(System.currentTimeMillis());
	    int trainingStatus = -1;
	    Object[] data = new Object[] {motive, cancelDate, trainingStatus, id};
	    dataBase.executeUpdate(query, data);
    }
	
}
