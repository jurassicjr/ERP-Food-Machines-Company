package database.dao;

import java.util.ArrayList;

import model.Employee;
import rh.view.RegisterUserFrame.CheckBoxNode;
import database.DataBase;

public class UserDAO {
	
	private DataBase dataBase;
	
	public UserDAO(Employee employee, ArrayList<CheckBoxNode> permissions, String password) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
		persist(employee, permissions, password);
	}
	
	private void persist(Employee employee, ArrayList<CheckBoxNode> permissions, String password) {
		
		int id = dataBase.getAutoIncrementValue("user");
				
		String sql = "INSERT INTO user (employee, password) VALUES (?, ?)";
		
		dataBase.executeUpdate(sql, new Object[]{employee.getId(), password});
		
		for(CheckBoxNode node : permissions) {
			
			sql = "INSERT INTO permission (user, permission) VALUES (?, ?)";
			dataBase.executeUpdate(sql, new Object[]{id, node.getMenuTag()});
			
		}
	}

}