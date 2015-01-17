package model;

public class BankingData {
	
	private int id;
	private Bank bank;
	private String agency;
	private String account;
	
	public BankingData(int id, Bank bank, String agency, String account) {
		this.id = id;
		this.bank = bank;
		this.agency = agency;
		this.account = account;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Bank getBank() {
		return bank;
	}
	
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	public String getAgency() {
		return agency;
	}
	
	public void setAgency(String agency) {
		this.agency = agency;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}	

}
