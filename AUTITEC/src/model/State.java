package model;

public class State {
	
	private int id;
	private String name;
		
	public State(int id, String name) {
		this.id = id;
		this.name = name;
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
	
	@Override
	public String toString() {
		return name;
	}
	public boolean equals(Object object)
	{
		try {
			State state;
			if(object instanceof State)
			{
				state = (State) object;
				return state.getId() == id;
			}
			else
				return false;
			
		} catch (Exception e) {
			return false;
		}
		
	}

}
