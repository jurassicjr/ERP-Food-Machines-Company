package maintenance.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JList;

import database.DataBase;
import database.dao.EmployeeDAO;
import database.dao.ToolBoxDAO;
import database.dao.ToolDAO;
import model.Employee;
import model.Tool;
import model.ToolBox;

public class ToolBoxUpdateFrameController {
	
	private DataBase dataBase;
	private Vector<Tool> vetTools;
	
	
	public ToolBoxUpdateFrameController()
	{
		dataBase = new DataBase();
		dataBase.connect();
		vetTools = new Vector<Tool>();
	}
	public Employee getEmployeeRegister(Integer empId)
	{
		return  EmployeeDAO.getEmployeeById(empId);
	}
	public void fillToolCbo(JComboBox<Tool> cboTool)
	{
		ToolDAO dao = new ToolDAO();
		ArrayList<Tool> tools = dao.getUnusedTools();
		cboTool.removeAllItems();
		for(Tool tool:tools)
			cboTool.addItem(tool);
		
	}
	public void fillToolBoxCbo(JComboBox<ToolBox> cboToolBoxes)
	{
		ToolBoxDAO dao = new ToolBoxDAO();
		ArrayList<ToolBox> toolBoxes = dao.getList();
		cboToolBoxes.removeAllItems();
		for(ToolBox toolBox:toolBoxes)
			cboToolBoxes.addItem(toolBox);
			
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
	public Integer updateToolBox(ToolBox toolBox)
	{
		Integer updatedToolBoxId = toolBox.getId();
		ToolBoxDAO dao = new ToolBoxDAO();
		dao.update(toolBox);
		return updatedToolBoxId;
	}
	public void updateToolBoxTools(Integer toolBoxId)
	{
		new ToolDAO().removeByToolBox(toolBoxId);
		
		if(vetTools.size() > 0)
			new ToolBoxDAO().persistTools(vetTools,toolBoxId);	
	}
	public void clearToolList(JList<Tool> toolList)
	{
		vetTools.removeAllElements();
		toolList.setListData(vetTools);
	}
	public void removeToolBoxAndTools(ToolBox toolBox)
	{
		new ToolDAO().removeByToolBox(toolBox.getId());
		new ToolBoxDAO().remove(toolBox);
	}
	public void getTools(JList<Tool> toolsList,Integer toolBoxId)
	{
		vetTools.removeAllElements();
		ArrayList<Tool> tools = new ToolDAO().getListByToolBox(toolBoxId);
		for(Tool tool:tools)
			vetTools.addElement(tool);
		
		toolsList.setListData(vetTools);
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
