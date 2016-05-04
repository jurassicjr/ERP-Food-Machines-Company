package model;

public class OutSourcedServices {
	
	private int id;
	private String name;
	private String description;
	
	public OutSourcedServices(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getObservation() {
		return description;
	}
	
}
