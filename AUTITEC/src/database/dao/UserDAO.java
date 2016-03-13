package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DataBase;
import model.Employee;
import rh.view.RegisterUserFrame.CheckBoxNode;

public class UserDAO {
	
	private DataBase dataBase;
	
	public UserDAO(Employee employee, ArrayList<CheckBoxNode> permissions, String password) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
		persist(employee, permissions, password);
	}
	
	public UserDAO() {
		dataBase = new DataBase();
		dataBase.connect();	
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
	
	public boolean verify(Employee e, String pass) {
	    String query = "SELECT *FROM user WHERE employee = ? AND password = ?";
	    try(ResultSet rs = dataBase.executeQuery(query, new Object[] {e.getId(), pass})){
	    	if(rs.next())return true;
	    } catch (SQLException e1) {
	        e1.printStackTrace();
        }
		return false;
    }
	

}