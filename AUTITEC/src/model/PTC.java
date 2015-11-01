package model;

import java.util.List;

public class PTC {
	private int id;
	private String title;
	private List<Material> materialList;
	private List<Product> productList;
	private List<Service> serviceList;
	private List<Kit> kitList;
	private double suggestedPrice;
	private double aliquot;
	private double brutePrice;
	private double aliquotPlusBrute;
	private Client client;
	private double contribuition;
	private double discount;
	private double finalPrice;
	
	public PTC(String title, double suggestedPrice, double aliquot, double brutePrice, double aliquotPlusBrute, double discount, double finalPrice, Client client) {
		this.title = title;
		this.suggestedPrice = suggestedPrice;
		this.aliquot = aliquot;
		this.brutePrice = brutePrice;
		this.aliquotPlusBrute = aliquotPlusBrute;
		this.discount = discount;
		this.finalPrice = finalPrice;
		this.client = client;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<Material> getMaterialList() {
		return materialList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public List<Kit> getKitList() {
		return kitList;
	}

	public double getSuggestedPrice() {
		return suggestedPrice;
	}

	public double getAliquot() {
		return aliquot;
	}

	public double getBrutePrice() {
		return brutePrice;
	}

	public double getAliquotPlusBrute() {
		return aliquotPlusBrute;
	}

	public Client getClient() {
		return client;
	}

	public double getContribuition() {
		return contribuition;
	}

	public double getDiscount() {
		return discount;
	}

	public double getFinalPrice() {
		return finalPrice;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

	public void setKitList(List<Kit> kitList) {
		this.kitList = kitList;
	}

	public void setSuggestedPrice(double suggestedPrice) {
		this.suggestedPrice = suggestedPrice;
	}
	
}
