package model;

public class ToolBox {

	private Integer id;
	private String description;
	private Integer responsible;
	
	public ToolBox(Integer id, String description, Integer responsible) {
		super();
		this.id = id;
		this.description = description;
		this.responsible = responsible;
	}

	public ToolBox() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getResponsible() {
		return responsible;
	}

	public void setResponsible(Integer responsible) {
		this.responsible = responsible;
	}

	@Override
	public String toString() {
		return responsible+" "+description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToolBox other = (ToolBox) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
}
