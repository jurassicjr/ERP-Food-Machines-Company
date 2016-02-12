package model;

public class Material {
	private int id;
	private String name;
	private String descrition;
	private String NCM;
	private String internalCode;
	private double ammount;
	private int materialType;
	private int model;
	private int auxAmmount;
	private double auxPrice;
	private int measureUnit;
	private boolean isDiscriminated;
	private double width;
	private double length;
	private double heigth;
	
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

	public void setMaterialType(int materialTypeId) {
		this.materialType = materialTypeId;    
    }
	
	public int getMaterialType() {
		return this.materialType;
	}
	
	public void setModel(int modelId) {
		this.model = modelId;
	}
	public int getModel() {
		return this.model;
	}

	public int getAuxAmmount() {
	    return auxAmmount;
    }

	public void setAuxAmmount(int auxAmmount) {
	    this.auxAmmount = auxAmmount;
    }

	public double getAuxPrice() {
	    return auxPrice;
    }

	public void setAuxPrice(double auxPrice) {
	    this.auxPrice = auxPrice;
    }

	public int getMeasureUnit() {
	    return measureUnit;
    }

	public void setMeasureUnit(int measureUnit) {
	    this.measureUnit = measureUnit;
    }

	public boolean isDiscriminated() {
	    return isDiscriminated;
    }

	public void setDiscriminated(boolean isDiscriminated) {
	    this.isDiscriminated = isDiscriminated;
    }

	public double getWidth() {
	    return width;
    }

	public void setWidth(double width) {
	    this.width = width;
    }

	public double getLength() {
	    return length;
    }

	public void setLength(double length) {
	    this.length = length;
    }

	public double getHeigth() {
	    return heigth;
    }

	public void setHeigth(double heigth) {
	    this.heigth = heigth;
    }
}
