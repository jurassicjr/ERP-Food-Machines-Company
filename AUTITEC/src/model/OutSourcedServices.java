package model;

public class OutSourcedServices {
	
	private int id;
	private String name;
	private String observation;
	
	public OutSourcedServices(String name, String observation) {
		this.name = name;
		this.observation = observation;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getObservation() {
		return observation;
	}
	public void setId(int id) {
		this.id =  id;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
