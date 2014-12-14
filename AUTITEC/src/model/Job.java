package model;

import java.sql.Date;

public class Job {
	
	private Date admissionDate;
	private CBO cbo;
	private double salary;
	private String payment;
	
	public Job(Date admissionDate, CBO cbo, double salary, String payment) {
		this.admissionDate = admissionDate;
		this.cbo = cbo;
		this.salary = salary;
		this.payment = payment;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public CBO getCbo() {
		return cbo;
	}

	public void setCbo(CBO cbo) {
		this.cbo = cbo;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

}
