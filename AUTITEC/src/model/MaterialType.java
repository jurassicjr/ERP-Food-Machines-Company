package model;

public class MaterialType {

	private int id;
	private String name;
	
	public MaterialType(String name) {
		this.name = name;
	}
	
	public MaterialType(String name, int id) {
		this.name = name;
		this.id = id;
	   
    }

	@Override
	public String toString() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
}
