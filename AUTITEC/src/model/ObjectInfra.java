package model;

public class ObjectInfra 
{
	private Integer id;
	private String description;
	
	public Integer getId() 
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public ObjectInfra(Integer id, String description)
	{
		super();
		this.id = id;
		this.description = description;
	}
	
	public ObjectInfra()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectInfra other = (ObjectInfra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() 
	{
		return description;
	}
	
	
	
	
}
