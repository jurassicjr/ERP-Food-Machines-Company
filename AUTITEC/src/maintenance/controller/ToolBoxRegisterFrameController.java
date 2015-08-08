package maintenance.controller;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;

import database.DataBase;
import database.dao.ToolBoxDAO;
import database.dao.ToolDAO;
import model.Employee;
import model.Tool;
import model.ToolBox;
import userInterface.components.ToolTableModel;

public class ToolBoxRegisterFrameController {
	
	private DataBase dataBase;
	private ToolTableModel toolTableModel;
	
	
	public ToolBoxRegisterFrameController()
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	public void initToolsTable(JTable tableTools)
	{
		toolTableModel = new ToolTableModel();
		tableTools.setModel(toolTableModel);
	}
	public void fillToolCbo(JComboBox<Tool> cboTool)
	{
		ToolDAO dao = new ToolDAO();
		ArrayList<Tool> tools = dao.getList();
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
	public void persistToolBox(ToolBox toolBox)
	{
		ToolBoxDAO dao = new ToolBoxDAO();
		dao.persist(toolBox);
	}
	public void persistToolBoxTools(Integer toolBoxId)
	{
		if(toolTableModel.getRowCount() > 0)
			new ToolBoxDAO().persistTools(toolTableModel.getAsArrayList(),toolBoxId);
		
	}
	public void addTool(Tool tool)
	{
		toolTableModel.addTool(tool);
	}
	public void removeTool(int index)
	{
		toolTableModel.removeTool(index);
	}
	
}
