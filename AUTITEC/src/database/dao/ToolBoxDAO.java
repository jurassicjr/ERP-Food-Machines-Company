package database.dao;

import java.util.Vector;

import database.DataBase;
import model.Tool;
import model.ToolBox;

public class ToolBoxDAO {
	
	private DataBase dataBase;
	
	public ToolBoxDAO()
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void persist(ToolBox toolBox)
	{
		
		try {
			String sql = "insert into toolbox(description,responsible) values(?,?)";
			Object[] parameters = {
					toolBox.getDescription(),
					toolBox.getResponsible()
					
			};
			dataBase.executeUpdate(sql,parameters);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void persistTools(Vector<Tool> tools,Integer toolBoxId)
	{
		try{
			for(Tool tool:tools)
			{
				String sql = "insert into toolbox_tool(toolbox,tool) values(?,?)";
				Object[] parameters = 
					{
							toolBoxId,
							tool.getId()	
					};
				dataBase.executeUpdate(sql,parameters);
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
}
