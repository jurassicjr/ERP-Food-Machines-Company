package model;

public class City {
	
	private int id;
	private String name;
	private State state;
	
	public City(int id, String name, State state) {
		this.id = id;
		this.name = name;
		this.state = state;
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
	
	public State getState() {
		return state;
	}
	
	@Override
	public String toString(){
		return name;
	}
	public boolean equals(Object object)
	{
		try {
			City city;
			if(object instanceof City)
			{
				city = (City) object;
				return city.getId() == id;
			}
			else
				return false;
			
		} catch (Exception e) {
			return false;
		}
	}
}
