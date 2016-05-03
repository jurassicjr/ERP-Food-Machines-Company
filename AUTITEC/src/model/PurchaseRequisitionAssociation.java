package model;

public class PurchaseRequisitionAssociation {

	private int id;
	private Material material;
	private double ammount;
	private boolean Bought = false; //Por padrão bought é igual a false.
	private String section;
	
	public PurchaseRequisitionAssociation(Material m, double ammount, String section) {
		material = m;
		this.ammount = ammount;
		this.section = section;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isBought() {
		return Bought;
	}

	public void setBought(boolean bought) {
		Bought = bought;
	}

	public Material getMaterial() {
		return material;
	}

	public double getAmmount() {
		return ammount;
	}

	public String getSection() {
		return section;
	}
	
	@Override
	public String toString() {
		return this.material.getName();
	}
}
