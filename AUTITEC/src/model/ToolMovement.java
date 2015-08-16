package model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class ToolMovement {
	
	private Integer id;
	private Tool tool;
	private ToolBox toolBox;
	private String movementType;
	private User user;
	private Employee employee;
	private Date date;
	
	public ToolMovement() 
	{
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Tool getTool() {
		return tool;
	}
	public void setTool(Tool tool) {
		this.tool = tool;
	}
	public ToolBox getToolBox() {
		return toolBox;
	}
	public void setToolBox(ToolBox toolBox) {
		this.toolBox = toolBox;
	}
	public String getMovementType() {
		return movementType;
	}
	public void setMovementType(String movementType) {
		this.movementType = movementType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String toString()
	{
		String tipo = movementType.equals("E")? "Entrada" : "Saída";
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return format.format(date)+" "+employee.getName()+" "+tipo+" "+tool.toString()+" "+toolBox.getDescription();
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		ToolMovement other = (ToolMovement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
