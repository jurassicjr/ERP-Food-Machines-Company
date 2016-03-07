package model;

public class CFOPExit {

	private int id;
	private String description;
	private String cfopDE;
	private String cfopFE;
	private String cfopIN;
	private String obs;
	private String nat;
	
	public String getNat() {
		return nat;
	}

	public CFOPExit(int id, String description, String cfopDE, String cfeopFE, String cfopIN, String nat) {
		this.id = id;
		this.description = description;
		this.cfopDE = cfopDE;
		cfopFE = cfeopFE;
		this.cfopIN = cfopIN;
		this.nat = nat;
		
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getCfopDE() {
		return cfopDE;
	}

	public String getCfopFE() {
		return cfopFE;
	}

	public String getCfopIN() {
		return cfopIN;
	}

	public String getObs() {
		return obs;
	}
	
	
}
