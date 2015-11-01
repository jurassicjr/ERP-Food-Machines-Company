package model;

public class Product {
	
	private int id;
	private String name;
	private String description;
	private int auxAmmount;
	
	public Product() {
    }
	
	public Product(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

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
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
	    return this.name;
	}
	
	@Override
    public boolean equals(Object obj)
	{
		try {
			 if(obj instanceof Product)
			 {
				Product pr = (Product) obj;
				return pr.id == this.id;
				 
			 }else return false;
			
		} catch (Exception e) {
			return false;
		}
		
	}

	public int getAuxAmmount() {
	    return auxAmmount;
    }

	public void setAuxAmmount(int auxAmmount) {
	    this.auxAmmount = auxAmmount;
    }
	
}
