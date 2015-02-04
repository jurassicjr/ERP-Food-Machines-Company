package model;

import java.util.ArrayList;

/**
 * Representa um usuário do sistema
 */
public class User {
		
	private Employee employee;
	private ArrayList<String> permissions;
	
	public User(Employee employee, ArrayList<String> permissions) {
		this.employee = employee;
		this.permissions = permissions;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
		
	public ArrayList<String> getPermissions() {
		return permissions;
	}
		
	public String toString() {
		return employee.getName();
	}

}
