package model;

public class BankAccount {

	private int id;
	private Bank bank;
	private String agency;
	private String account;
	private String safeMoneyAccount;
	private double inicialValue;
	
	
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
	public String getSafeMoneyAccount() {
		return safeMoneyAccount;
	}
	public void setSafeMoneyAccount(String safeMoneyAccount) {
		this.safeMoneyAccount = safeMoneyAccount;
	}
	public double getInicialValue() {
		return inicialValue;
	}
	public void setInicialValue(double inicialValue) {
		this.inicialValue = inicialValue;
	}
}
