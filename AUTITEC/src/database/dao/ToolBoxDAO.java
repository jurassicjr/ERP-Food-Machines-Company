package database.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
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
	public void remove(ToolBox toolBox)
	{
		try {
			String sql = "delete from toolbox where id = ?";
			
			dataBase.executeUpdate(sql,toolBox.getId());
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void update(ToolBox toolBox)
	{
		try {
			String sql = "update toolbox set description= ?,responsible = ? where id = ?";
			Object[] parameters = {
					toolBox.getDescription(),
					toolBox.getResponsible(),
					toolBox.getId()
					
			};
			dataBase.executeUpdate(sql,parameters);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public ArrayList<ToolBox> getList()
	{
		ArrayList<ToolBox> toolBoxes = new ArrayList<>();
			try {
				String sql = "select * from toolbox";
				ResultSet rs = dataBase.executeQuery(sql);
				
				while(rs.next())
				{
					ToolBox toolBox = new ToolBox();
					toolBox.setId(rs.getInt("id"));
					toolBox.setDescription(rs.getString("description"));
					toolBox.setResponsible(rs.getInt("responsible"));
					toolBoxes.add(toolBox);
				}
								
			} catch (Exception e) {
				// TODO: handle exception
			}
		return toolBoxes;
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
