package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Bill {
	
	private String bill; 
	private String creditor;
	private int installmentsNumber;
	private String observation;
	private ArrayList<Installment> installments;
	
	public Bill(String bill, String creditor, Date dueDate, int installmentsNumber, String observation) {
		
		this.bill = bill;
		this.creditor = creditor;
		this.installmentsNumber = installmentsNumber;
		this.observation = observation;
		installments = new ArrayList<Installment>();
		
		createInstallments(dueDate);
	}
	
	public void createInstallments(Date dueDate) {
				
		Calendar calendar = Calendar.getInstance();
		Date date = new Date(dueDate.getTime());
		
		for(int i = 0; i < installmentsNumber; ++i) {
			
			calendar.setTime(dueDate);
			installments.add(new Installment(date));
			
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
			dueDate = calendar.getTime();
			
			int dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
			
			if(dayOfWeek == GregorianCalendar.SATURDAY) {
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 2);
			}
			else if(dayOfWeek == GregorianCalendar.SUNDAY) {
				calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
			}
			
			date = calendar.getTime();
			
		}
				
	}	

	public String getBill() {
		return bill;
	}

	public void setBill(String bill) {
		this.bill = bill;
	}

	public String getCreditor() {
		return creditor;
	}

	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}

	public int getInstallmentsNumber() {
		return installmentsNumber;
	}

	public void setInstallmentsNumber(int installmentsNumber) {
		this.installmentsNumber = installmentsNumber;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public ArrayList<Installment> getInstallments() {
		return installments;
	}

	public void setInstallments(ArrayList<Installment> installments) {
		this.installments = installments;
	}
}
