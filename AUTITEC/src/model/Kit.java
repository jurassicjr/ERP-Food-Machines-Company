package model;

public class Kit {
	private String name;
	private String description;
	private int id;
	private double auxPrice;
	private double auxAmmount;

	public Kit(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;

	}
	public Kit()
	{}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
	    return this.name;
	}
	
	@Override
    public boolean equals(Object obj)
	{
		try {
			 if(obj instanceof Kit)
			 {
				Kit kit = (Kit) obj;
				return kit.id == this.id;
				 
			 }else return false;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	public double getAuxPrice() {
	    return auxPrice;
    }
	public void setAuxPrice(double auxPrice) {
	    this.auxPrice = auxPrice;
    }
	public double getAuxAmmount() {
	    return auxAmmount;
    }
	public void setAuxAmmount(double auxAmmount) {
	    this.auxAmmount = auxAmmount;
    }
}
