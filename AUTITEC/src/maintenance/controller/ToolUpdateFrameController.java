
package maintenance.controller;

import java.util.ArrayList;

import javax.swing.JComboBox;

import java.util.Vector;

import database.dao.ToolDAO;
import model.Tool;
@SuppressWarnings("unchecked")
public class ToolUpdateFrameController {
	
	
	public void fillCboTools(JComboBox<Tool> cboTools)
	{
		try {
			ToolDAO dao = new ToolDAO();
			cboTools.removeAllItems();
			ArrayList<Tool> tools = dao.getList();
			
			for(Tool tool:tools)
				cboTools.addItem(tool);
				
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	public void updateTool(Tool tool)
	{
		new ToolDAO().update(tool);
	}
	public void removeTool(Tool tool)
	{
		new ToolDAO().remove(tool.getId());
	}
	
}
