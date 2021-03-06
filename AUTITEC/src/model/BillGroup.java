package model;

import java.util.ArrayList;

public class BillGroup {
	
	private int id;
	private String code;
	private String name;
	
	private ArrayList<BillSubGroup> subgroups;
		
	public BillGroup(int id, String code, String name) {
		subgroups = new ArrayList<>();
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	public BillGroup(int id, String code, String name, ArrayList<BillSubGroup> subgroups) {
		this.subgroups = subgroups;
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
	
	public ArrayList<BillSubGroup> getSubGroups() {
		return subgroups;
	}
	
	@Override
	public String toString() {
		return code + " - " + name;
	}

}
