package model;

public class Address {
	
	private int id;
	private String address;
	private String neighborhood;
	private String cep;
	private City city;
	
	public Address(int id, String address, String neighborhood, String cep,	City city) {
		this.id = id;
		this.address = address;
		this.neighborhood = neighborhood;
		this.cep = cep;
		this.city = city;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getNeighborhood() {
		return neighborhood;
	}
	
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
	public String getFormattedCep() {
		return cep.substring(0, 2) + "." + cep.substring(2, 5) + "-" + cep.substring(5, 8);
	}
	
	@Override
	public String toString() {
		return address;
	}

}
