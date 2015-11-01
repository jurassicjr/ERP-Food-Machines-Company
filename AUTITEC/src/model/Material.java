package model;

public class Material {
	private int id;
	private String name;
	private String descrition;
	private String NCM;
	private String internalCode;
	private double ammount;
	private String materialType;
	private String model;
	private int auxAmmount;
	
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
	@Override
    public boolean equals(Object obj)
	{
		try {
				if(obj instanceof Material)
				{
					Material matTemp = (Material) obj;
					return matTemp.id == this.id;
				}
				else return false;
		} catch (Exception e) {
			return false;
		}	
		
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;    
    }
	
	public String getMaterialType() {
		return this.materialType;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	public String getModel() {
		return this.model;
	}

	public int getAuxAmmount() {
	    return auxAmmount;
    }

	public void setAuxAmmount(int auxAmmount) {
	    this.auxAmmount = auxAmmount;
    }
}
