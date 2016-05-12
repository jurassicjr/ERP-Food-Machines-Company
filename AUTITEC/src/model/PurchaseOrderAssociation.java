package model;

public class PurchaseOrderAssociation {

	private int id;
	private Material material;
	private double unitPrice;
	private double ipi;
	private double compostPrice;
	private double ammount;
	private boolean hasArrived;
	
	public PurchaseOrderAssociation(Material material, double unitPrice, double ipi, double compostPrice, double ammount, boolean hasArrived) {
		this.material = material;
		this.unitPrice = unitPrice;
		this.ipi = ipi;
		this.compostPrice = compostPrice;
		this.ammount = ammount;
		this.hasArrived = hasArrived;
	}

	public PurchaseOrderAssociation(Material material, double unitPrice, double ipi, double compostPrice,
            double ammount) {
				this.material = material;
				this.unitPrice = unitPrice;
				this.ipi = ipi;
				this.compostPrice = compostPrice;
				this.ammount = ammount;
    }

	public boolean isHasArrived() {
		return hasArrived;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Material getMaterial() {
		return material;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public double getIpi() {
		return ipi;
	}

	public double getCompostPrice() {
		return compostPrice;
	}

	public double getAmmount() {
		return ammount;
	}
	
	@Override
	public String toString() {
		return this.material.getName();
	}
	
}
