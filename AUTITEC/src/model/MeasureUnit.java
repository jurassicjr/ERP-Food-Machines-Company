package model;

public class MeasureUnit {

	
	private int id;
	private String name;
	
	public MeasureUnit(String name) {
		this.name = name;
	}
	
	public MeasureUnit(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
