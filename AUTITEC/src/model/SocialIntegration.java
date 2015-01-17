package model;

import java.util.Date;

public class SocialIntegration {
	
	private int id;
	private Date cadastreDate;
	private String cadastreNumber;
	private String address;
	private BankingData bankingData;
	
	public SocialIntegration(int id, Date cadastreDate, String cadastreNumber, String address, BankingData bankingData) {
		this.id = id;
		this.cadastreDate = cadastreDate;
		this.cadastreNumber = cadastreNumber;
		this.address = address;
		this.bankingData = bankingData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCadastreDate() {
		return cadastreDate;
	}

	public void setCadastreDate(Date cadastreDate) {
		this.cadastreDate = cadastreDate;
	}

	public String getCadastreNumber() {
		return cadastreNumber;
	}

	public void setCadastreNumber(String cadastreNumber) {
		this.cadastreNumber = cadastreNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BankingData getBankingData() {
		return bankingData;
	}

	public void setBankingData(BankingData bankingData) {
		this.bankingData = bankingData;
	}	

}
