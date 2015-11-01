package model;

public class Service {

	private int id;
	private String name;
	private String stayValue;
	private double price_I_I_I;
	private double price_I_I_II;
	private double price_I_II_I;
	private double price_I_II_II;
	private double price_I_III_I;
	private double price_I_III_II;
	private double price_II_I_I;
	private double price_II_I_II;
	private double price_II_II_I;
	private double price_II_II_II;
	private double price_II_III_I;
	private double price_II_III_II;
	private double pricePerKm;
	private double meal;
	private String observation;
	
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
	public String getStayValue() {
		return stayValue;
	}
	public void setStayValue(String stayValue) {
		this.stayValue = stayValue;
	}
	public double getPrice_I_I_I() {
		return price_I_I_I;
	}
	public void setPrice_I_I_I(double price_I_I_I) {
		this.price_I_I_I = price_I_I_I;
	}
	public double getPrice_I_I_II() {
		return price_I_I_II;
	}
	public void setPrice_I_I_II(double price_I_I_II) {
		this.price_I_I_II = price_I_I_II;
	}
	public double getPrice_I_II_I() {
		return price_I_II_I;
	}
	public void setPrice_I_II_I(double price_I_II_I) {
		this.price_I_II_I = price_I_II_I;
	}
	public double getPrice_I_II_II() {
		return price_I_II_II;
	}
	public void setPrice_I_II_II(double price_I_II_II) {
		this.price_I_II_II = price_I_II_II;
	}
	public double getPrice_I_III_I() {
		return price_I_III_I;
	}
	public void setPrice_I_III_I(double price_I_III_I) {
		this.price_I_III_I = price_I_III_I;
	}
	public double getPrice_I_III_II() {
		return price_I_III_II;
	}
	public void setPrice_I_III_II(double price_I_III_II) {
		this.price_I_III_II = price_I_III_II;
	}
	public double getPrice_II_I_I() {
		return price_II_I_I;
	}
	public void setPrice_II_I_I(double price_II_I_I) {
		this.price_II_I_I = price_II_I_I;
	}
	public double getPrice_II_I_II() {
		return price_II_I_II;
	}
	public void setPrice_II_I_II(double price_II_I_II) {
		this.price_II_I_II = price_II_I_II;
	}
	public double getPrice_II_II_I() {
		return price_II_II_I;
	}
	public void setPrice_II_II_I(double price_II_II_I) {
		this.price_II_II_I = price_II_II_I;
	}
	public double getPrice_II_II_II() {
		return price_II_II_II;
	}
	public void setPrice_II_II_II(double price_II_II_II) {
		this.price_II_II_II = price_II_II_II;
	}
	public double getPrice_II_III_I() {
		return price_II_III_I;
	}
	public void setPrice_II_III_I(double price_II_III_I) {
		this.price_II_III_I = price_II_III_I;
	}
	public double getPrice_II_III_II() {
		return price_II_III_II;
	}
	public void setPrice_II_III_II(double price_II_III_II) {
		this.price_II_III_II = price_II_III_II;
	}
	public double getPricePerKm() {
		return pricePerKm;
	}
	public void setPricePerKm(double pricePerKm) {
		this.pricePerKm = pricePerKm;
	}
	public double getMeal() {
		return meal;
	}
	public void setMeal(double meal) {
		this.meal = meal;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	@Override
	public String toString() {
	    return this.name;
	}
}
