package core;

public class Bank {
	
	private int id;
	private String febrabanCode;
	private String bank;
	
	public Bank(int id, String febrabanCode, String bank) {
		this.id = id;
		this.febrabanCode = febrabanCode;
		this.bank = bank;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCode() {
		return febrabanCode;
	}
	
	public void setCode(String code) {
		this.febrabanCode = code;
	}
	
	public String getTitle() {
		return bank;
	}
	
	public void setTitle(String title) {
		this.bank = title;
	}
	
	@Override
	public String toString() {
		return bank;
	}

}
