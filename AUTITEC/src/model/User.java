package model;

/**
 * Representa um usuário do sistema
 */
public class User {
	
	public static final int MANAGER = 0;
	public static final int COMMERCIAL = 1;
	public static final int SALES = 2;
	public static final int PRODUCTION_LECTURER = 3;
	public static final int PRODUCTION_SUPERVISOR = 4;
	public static final int FINANCIAL = 5;
	public static final int HUMAN_RESOURCES = 6;
	
	private Employee employee;
	private String password;
	private int permission;
	
	public User(Employee employee, String password, int permission) {
		this.employee = employee;
		this.password = password;
		this.permission = permission;
	}
	
	public User(Employee employee, int permission) {
		this.employee = employee;
		this.permission = permission;
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getPermission() {
		return permission;
	}
	
	public void setPermission(int permission) {
		this.permission = permission;
	}

}
