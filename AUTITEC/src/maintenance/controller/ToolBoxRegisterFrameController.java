package maintenance.controller;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JList;

import java.util.Vector;

import database.DataBase;
import database.dao.ToolBoxDAO;
import database.dao.ToolDAO;
import model.Employee;
import model.Tool;
import model.ToolBox;

public class ToolBoxRegisterFrameController {
	
	private DataBase dataBase;
	private Vector<Tool> vetTools;
	
	
	public ToolBoxRegisterFrameController()
	{
		dataBase = new DataBase();
		dataBase.connect();
		vetTools = new Vector<Tool>();
	}

	public void fillToolCbo(JComboBox<Tool> cboTool)
	{
		ToolDAO dao = new ToolDAO();
		ArrayList<Tool> tools = dao.getUnusedTools();
		cboTool.removeAllItems();
		for(Tool tool:tools)
			cboTool.addItem(tool);
		
	}
	
	public void fillResponsibleCbo(JComboBox<Employee> cboEmployee)
	{
		 String sql = "SELECT id,name FROM employee order by name";
		 cboEmployee.removeAllItems();
		 
		 try{
			 ResultSet rs = dataBase.executeQuery(sql);
				while(rs.next()) {
					
					Employee employee  = new Employee();
					employee.setId(rs.getInt("id"));
					employee.setName(rs.getString("name"));
					cboEmployee.addItem(employee);
			}
		 }catch(Exception ex)
		 {
			 
		 }
	}
	public Integer persistToolBox(ToolBox toolBox)
	{
		Integer lastToolBoxId = dataBase.getAutoIncrementValue("toolbox");
		ToolBoxDAO dao = new ToolBoxDAO();
		dao.persist(toolBox);
		return lastToolBoxId;
	}
	public void persistToolBoxTools(Integer toolBoxId)
	{
		if(vetTools.size() > 0)
			new ToolBoxDAO().persistTools(vetTools,toolBoxId);	
	}
	public void clearToolList(JList<Tool> toolList)
	{
		vetTools.removeAllElements();
		toolList.setListData(vetTools);
	}
	public void addTool(Tool tool,JList<Tool> toolList,JComboBox<Tool> cboTool)
	{
		try {
			vetTools.addElement(tool); 
			toolList.setListData(vetTools);
			cboTool.removeItem(tool);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	public void removeTool(Tool tool,JList<Tool> toolList,JComboBox<Tool> cboTool)
	{
		try {
			vetTools.removeElement(tool); 
			toolList.setListData(vetTools);
			cboTool.addItem(tool);
			
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	

	
}
