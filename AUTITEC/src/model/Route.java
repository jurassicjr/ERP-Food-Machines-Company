package model;

import java.sql.Date;

public class Route {
	
	private Integer id;
	private String description;
	private Date date;
	private Double initialKm;
	private Employee conductor;
	private Vehicle vehicle;
	private User user;
	
	
	
	
	public Route(Integer id, String description, Date date, Double initialKm,
			Employee conductor, Vehicle vehicle, User user) {
		super();
		this.id = id;
		this.description = description;
		this.date = date;
		this.initialKm = initialKm;
		this.conductor = conductor;
		this.vehicle = vehicle;
		this.user = user;
	}
	
	
	
	public Route() {
		super();
		
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getInitialKm() {
		return initialKm;
	}
	public void setInitialKm(Double initialKm) {
		this.initialKm = initialKm;
	}
	public Employee getConductor() {
		return conductor;
	}
	public void setConductor(Employee conductor) {
		this.conductor = conductor;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return description;
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
		Route other = (Route) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
}
