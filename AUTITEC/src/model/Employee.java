package model;

/**
 * Representação de um empregado
 */
public class Employee {
	
	public static final int MALE = 0;
	public static final int FEMALE = 1;
	
	public static final int SINGLE = 0;
	public static final int MARRIED = 1; 
	public static final int DIVORCED = 2;
	public static final int WIDOWER = 3;
	
	public static final int CATEGORY_A = 0;
	public static final int CATEGORY_B = 1;
	public static final int CATEGORY_AB = 2;
	public static final int CATEGORY_AC = 3;
	public static final int CATEGORY_AD = 4;
	public static final int CATEGORY_AE = 5;
	public static final int CATEGORY_C = 6;
	public static final int CATEGORY_D = 7;
	public static final int CATEGORY_E = 8;
	
	public static final int INCOMPLETE_BASIC_SCHOOL = 0;
	public static final int BASIC_SCHOOL = 1;
	public static final int INCOMPLETE_HIGH_SCHOOL = 2;
	public static final int HIGH_SCHOOL = 3;
	public static final int INCOMPLETE_TECHNICAL_SCHOLL = 4;
	public static final int TECHNICAL_SCHOLL = 5;
	public static final int INCOMPLETE_HIGHER_EDUCATION = 6;
	public static final int HIGHER_EDUCATION = 7;
	public static final int POSTGRADUATE = 8;
	
	public static final int MONTHLY = 0;
	public static final int HOURLY = 1;
	public static final int CONTRACT = 2;
	public static final int WEEKLY = 3;
	public static final int FORTNIGHTLY = 4;
	
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
