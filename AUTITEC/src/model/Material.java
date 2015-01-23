package model;

public class Material {
	private int id;
	private String name;
	private String descrition;
	private String NCM;
	private String internalCode;
	private double ammount;

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

	
	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descricao) {
		this.descrition = descricao;
	}
	@Override
	public String toString() {
	    return this.name;
	}

	public String getInternalCode() {
	    return internalCode;
    }

	public void setInternalCode(String internalCode) {
	    this.internalCode = internalCode;
    }

	public String getNCM() {
	    return NCM;
    }

	public void setNCM(String nCM) {
	    NCM = nCM;
    }

	public double getAmmount() {
	    return ammount;
    }

	public void setAmmount(double ammount) {
	    this.ammount = ammount;
    }
}
