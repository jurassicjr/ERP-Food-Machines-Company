package model;

public class MaterialModel {

	
	int id;
	String name;
	
	public MaterialModel(String name) {
		this.name = name;
	}
	
	public MaterialModel(String name, int id) {
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

	public String getName() {
	    return this.name;
    }
}
