package model;

import java.util.Date;

public class Dependent {
	
	private int id;
	private String name;
	private String relationship;
	private Date birthWeddingDate;
	
	public Dependent(int id, String name, String relationship, Date birthWeddingDate) {
		this.id = id;
		this.name = name;
		this.relationship = relationship;
		this.birthWeddingDate = birthWeddingDate;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getRelationship() {
		return relationship;
	}
	
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	public Date getBirthWeddingDate() {
		return birthWeddingDate;
	}
	
	public void setBirthWeddingDate(Date birthWeddingDate) {
		this.birthWeddingDate = birthWeddingDate;
	}
	
	public String toString() {
		return name;
	}

}
