package model;

import java.util.Date;

public class Installment {
	
	private Date date;
	private double value;
	private boolean paid;
	
	public Installment(Date date, double value) {
		this.date = date;
		this.value = value;
		paid = false;
	}
	
	public Installment(Date date, boolean paid) {
		this.date = date;
		this.paid = paid;
	}

	public Date getDate() {
		return date;
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

}
