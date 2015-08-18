package maintenance.controller;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JComboBox;

import database.DataBase;
import database.dao.ToolBoxDAO;
import database.dao.ToolDAO;
import model.Employee;
import model.Tool;
import model.ToolBox;

public class ToolMovementRegisterFrameController 
{	
	
	private DataBase dataBase;
	public ToolMovementRegisterFrameController()
	{
		dataBase = new DataBase();
		dataBase.connect();
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
}
