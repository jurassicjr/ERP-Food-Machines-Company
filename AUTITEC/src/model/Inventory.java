package model;

public class Inventory {

	private Material material;
	private CNPJ cnpj;
	private Supplier supplier;
	private String fiscalNote;
	private int ammount;
	private int id;
	private double noteValue;
	private double icms;
	private double cofins;
	private double pis;
	private double ir;
	private double entryValue;
	
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
	public void setNoteValue(double noteValue) {
		this.noteValue = noteValue;	  
    }
	public double getNoteValue() {
		return this.noteValue;
	}
	public void setIcms(double icms) {
		this.icms = icms;
    }
	public double getIcms() {
		return this.icms;
	}
	public void setCofins(double cofins) {
		this.cofins = cofins;
    }
	public double getCofins() {
		return this.cofins;
	}
	public void setPis(double pis) {
		this.pis = pis;	   
    }
	public double getPis() {
		return this.pis;
	}
	public void setIr(double ir) {
		this.ir = ir;
    }
	public double getIr() {
		return this.ir;
	}
	public void setEntryValue(double entryValue) {
		this.entryValue = entryValue;
    }
	public double getEntryValue() {
		return this.entryValue;
	}
	
}
