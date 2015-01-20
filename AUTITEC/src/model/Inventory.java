package model;

public class Inventory {

	private Material material;
	private CNPJ cnpj;
	private Supplier supplier;
	private String fiscalNote;
	private int ammount;
	private int id;
	
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public CNPJ getCnpj() {
		return cnpj;
	}
	public void setCnpj(CNPJ cnpj) {
		this.cnpj = cnpj;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public String getFiscalNote() {
		return fiscalNote;
	}
	public void setFiscalNote(String fiscalNote) {
		this.fiscalNote = fiscalNote;
	}
	public int getAmmount() {
		return ammount;
	}
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}
	public int getId() {
	    return id;
    }
	public void setId(int id) {
	    this.id = id;
    }
}
