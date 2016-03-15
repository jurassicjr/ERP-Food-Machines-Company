package model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

public class FinancialNotification {
	
	private Bill bill;
	private Date dueDate;
	private double value;
	private boolean urgent;
	
	public FinancialNotification(Bill bill, Date dueDate, double value) {
		
		this.bill = bill;
		this.dueDate = dueDate;
		this.value = value;
				
		urgent = new Date().after(dueDate);
		
	}
	
	public Bill getBill() {
		return bill;
	}
	
	public void setBill(Bill bill) {
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
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		
		if(bill.getType() == Bill.TO_PAY)
			builder.append("Conta a Pagar:\n");
		
		if(bill.getGroup() != null && bill.getBillName() == null)
			builder.append(bill.getGroup().getName().trim() + "\n");

		if(bill.getSubGroup() != null)
			builder.append(bill.getSubGroup().getName().trim() + "\n");
		
		if(bill.getBillName() != null)
			builder.append(bill.getBillName().getName().trim());
		
		if(builder.charAt(builder.length() - 1) == '\n')
			builder.delete(builder.length() - 1, builder.length());
		
		builder.append("\n" + NumberFormat.getCurrencyInstance().format(value));
		
		builder.append("\n" + new SimpleDateFormat("dd/MM/yyyy").format(dueDate));
		
		builder.append("\n" + bill.getCreditor());
		
		return builder.toString();
		
	}
	
}
