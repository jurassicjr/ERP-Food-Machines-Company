package model;

import java.util.Date;

public class DebtToReceive {
	
	private int id;
	private String debt; 
	private String debtor; 
	private String observation;
	private Date date; 
	private boolean paid;
	private double value;
	
	public DebtToReceive(String debt, String debtor, String observation, Date date, boolean paid, double value) {
		this.debt = debt;
		this.debtor = debtor;
		this.observation = observation;
		this.date = date;
		this.paid = paid;
		this.value = value;
	}
	
	public DebtToReceive(int id, String debt, String debtor, String observation, Date date, boolean paid, double value) {
		this.id = id;
		this.debt = debt;
		this.debtor = debtor;
		this.observation = observation;
		this.date = date;
		this.paid = paid;
		this.value = value;
	}

	public String getDebt() {
		return debt;
	}

	public void setDebt(String debt) {
		this.debt = debt;
	}

	public String getDebtor() {
		return debtor;
	}

	public void setDebtor(String debtor) {
		this.debtor = debtor;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public String toString() {
		return debt;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

}
