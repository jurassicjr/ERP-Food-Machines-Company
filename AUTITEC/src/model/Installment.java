package model;

import java.util.Date;

public class Installment {
	
	private Bill bill;
	
	private double value;
	private Date date;
	private Date paidDate;
	
	public Installment(Bill bill, double value, Date date, Date paidDate) {
		this.bill = bill;
		this.value = value;
		this.date = date;
		this.paidDate = paidDate;
	}
	
	public Installment(double value, Date date) {
		this.value = value;
		this.date = date;
	}

	public Bill getBill() {
		return bill;
	}
	
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getPaidDate() {
		return paidDate;
	}
	
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	
}
