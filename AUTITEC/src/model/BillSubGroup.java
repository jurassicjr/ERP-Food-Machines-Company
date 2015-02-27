package model;

import java.util.ArrayList;

public class BillSubGroup {
	
	private int id;
	private String code;
	private String name;
	private ArrayList<BillName> billNames;
	
	public BillSubGroup(int id, String code, String name) {
		billNames = new ArrayList<>();
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	public BillSubGroup(int id, String code, String name, ArrayList<BillName> billNames) {
		this.billNames = billNames;
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<BillName> getBillNames() {
		return billNames;
	}
	
	@Override
	public String toString() {
		return code + " - " + name;
	}

}
