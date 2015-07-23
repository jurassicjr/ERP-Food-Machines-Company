package model;

import java.sql.Date;

public class VehicleDebt {
	
	private Integer id;
	private Vehicle vehicle;
	private Employee conductor;
	private String debtType;
	private String description;
	private String fuelType;
	private Double fuelQuantity;
	private String observations;
	private Date paydate;
	private Date duedate;
	private Double value;
	private User user;
	
	public VehicleDebt(Integer id, Vehicle vehicle, Employee conductor, String debtType, String description,
			String fuelType, Double fuelQuantity, String observations, Date paydate, Date duedate, Double value,
			User user) {
		super();
		this.id = id;
		this.vehicle = vehicle;
		this.conductor = conductor;
		this.debtType = debtType;
		this.description = description;
		this.fuelType = fuelType;
		this.fuelQuantity = fuelQuantity;
		this.observations = observations;
		this.paydate = paydate;
		this.duedate = duedate;
		this.value = value;
		this.user = user;
	}
	
	public VehicleDebt() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Employee getConductor() {
		return conductor;
	}
	public void setConductor(Employee conductor) {
		this.conductor = conductor;
	}
	public String getDebtType() {
		return debtType;
	}
	public void setDebtType(String debtType) {
		this.debtType = debtType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public Double getFuelQuantity() {
		return fuelQuantity;
	}
	public void setFuelQuantity(Double fuelQuantity) {
		this.fuelQuantity = fuelQuantity;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public Date getPaydate() {
		return paydate;
	}
	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
