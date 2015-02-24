package model;

import java.util.ArrayList;

/**
 * Representa um usuário do sistema
 */
public class User {
		
	private Employee employee;
	private ArrayList<String> permissions;
	private int id;
	
	public User(Employee employee, ArrayList<String> permissions) {
		this.employee = employee;
		this.permissions = permissions;
	}
	
	public User(int id, Employee employee, ArrayList<String> permissions) {
		this.id = id;
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
	
	public int getId() {
		return id;
	}
		
	public String toString() {
		return employee.getName();
	}

}
