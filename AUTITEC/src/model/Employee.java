package model;

public class Employee {
	
	private int id;
	private String name;
	private String cpf;
	private Job job;
	
	public Employee(int id, String name, String cpf, Job job) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.job = job;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return name;
	}
	
	

}
