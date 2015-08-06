
package maintenance.controller;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

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

			DefaultListModel<Tool> listModel = (DefaultListModel<Tool>) cboTools.getModel();
			for(Tool tool:tools)
				listModel.addElement(tool);
				
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
}
