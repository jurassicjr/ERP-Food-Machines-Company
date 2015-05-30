package database.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import model.Employee;
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
		
		int trainingID = dataBase.getAutoIncrementValue("training");
		String sqlTraining = "INSERT INTO training(title, objective, date, motive, event_type, period, duration) VALUES(?,?,?,?,?,?,?)";
		Object[] objTraining = new Object[]{title, objetive, trainingDate, motive, eventType, period, duration};
		
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
	
	
}
