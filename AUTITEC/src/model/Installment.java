package model;

import java.util.Date;

public class Installment {
	
	private Date date;
	private boolean paid;
	
	public Installment(Date date) {
		this.date = date;
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

}
