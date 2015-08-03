package maintenance.controller;

import java.util.ArrayList;

import javax.swing.JComboBox;

import database.dao.ToolDAO;
import model.Tool;

public class ToolRegisterFrameController {

	public void persist(Tool tool)
	{
		ToolDAO dao = new ToolDAO();
		dao.persist(tool);	
	}

	
}
