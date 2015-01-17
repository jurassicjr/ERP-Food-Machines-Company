package model;

import java.util.Date;

public class GuaranteeFund {
	
	private int id;
	private Date optionDate;
	private Date retractionDate;
	private Bank bank;
		
	public GuaranteeFund(int id, Date optionDate, Date retractionDate, Bank bank) {
		this.id = id;
		this.optionDate = optionDate;
		this.retractionDate = retractionDate;
		this.bank = bank;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getOptionDate() {
		return optionDate;
	}
	
	public void setOptionDate(Date optionDate) {
		this.optionDate = optionDate;
	}
	
	public Date getRetractionDate() {
		return retractionDate;
	}
	
	public void setRetractionDate(Date retractionDate) {
		this.retractionDate = retractionDate;
	}
	
	public Bank getBank() {
		return bank;
	}
	
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
}
