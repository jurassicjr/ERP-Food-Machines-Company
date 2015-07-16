package model;

public class RouteDestination {
	
	private Integer id;
	private Route route;
	private Client client;
	private String otherDestination;
	
	public RouteDestination(Integer id, Route route, Client client,
			String otherDestination) {
		super();
		this.id = id;
		this.route = route;
		this.client = client;
		this.otherDestination = otherDestination;
	}
	
	
	
	public RouteDestination() {
		super();
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getOtherDestination() {
		return otherDestination;
	}

	public void setOtherDestination(String otherDestination) {
		this.otherDestination = otherDestination;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RouteDestination other = (RouteDestination) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return client!=null ? client.getName() : this.otherDestination;
	}
	
	
	
}
