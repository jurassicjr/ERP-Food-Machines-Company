package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.CBO;
import database.DataBase;

public class CboDAO {
	
	private DataBase dataBase;
	
	public CboDAO(String title, String code) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
		persist(title, code);
	}
	
	private void persist(String title, String code) {
		dataBase.executeUpdate("INSERT INTO cbo (code, title) VALUES (?, ?)", new Object[]{code, title});
	}
	
	public static CBO getCBObyId(int id) {
		
		CBO cbo = null;
		
		DataBase dataBase = new DataBase();
		dataBase.connect();
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM cbo WHERE id = ?", id);
			
			if(resultSet.next()) {
				
				String code = resultSet.getString("code");
				String title = resultSet.getString("title");
				
				cbo = new CBO(id, code, title);
				
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
		return cbo;
		
	}
	

}
