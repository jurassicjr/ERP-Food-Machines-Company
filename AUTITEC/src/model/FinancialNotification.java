package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FinancialNotification {
	
	private String bill;
	private Date dueDate;
	private boolean urgent;
	
	public FinancialNotification(String bill, Date dueDate) {
		
		this.bill = bill;
		this.dueDate = dueDate;
				
		urgent = new Date().after(dueDate);
		
	}
	
	public String getBill() {
		return bill;
	}
	
	public void setBill(String bill) {
		this.bill = bill;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public boolean isUrgent() {
		return urgent;
	}
	
	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}
	
	@Override
	public String toString() {
		return bill + "\n" + new SimpleDateFormat("dd/MM/yyyy").format(dueDate);
	}
	
}
