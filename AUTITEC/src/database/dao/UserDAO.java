package database.dao;

import model.User;
import database.DataBase;

public class UserDAO {
	
	private DataBase dataBase;
	
	public UserDAO(User user) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
		persist(user);
	}
	
	private void persist(User user) {
		
		String sql = "INSERT INTO user (permission, employee, password) VALUES (?, ?, ?)";
		
		int permission = user.getPermission();
		int employeeId = user.getEmployee().getId();
		String password = user.getPassword();
		
		dataBase.executeUpdate(sql, new Object[]{permission, employeeId, password});
		
	}

}
