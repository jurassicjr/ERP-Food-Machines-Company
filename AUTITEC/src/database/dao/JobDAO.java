package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import model.CBO;
import model.Job;
import database.DataBase;

public class JobDAO {
	
	public static Job getJobById(int id) {
		
		Job job = null;
		
		DataBase dataBase = new DataBase();
		dataBase.connect();
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM job WHERE id = ?", id);
			
			if(resultSet.next()) {
				
				Date admissionDate = resultSet.getDate("admission_date");
				CBO cbo = CboDAO.getCBObyId(resultSet.getInt("cbo"));
				double salary = resultSet.getDouble("initial_salary");
				int payment = resultSet.getInt("payment");
				
				job = new Job(admissionDate, cbo, salary, payment);
				
			}
							
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}		
				
		return job;
		
	}

}
