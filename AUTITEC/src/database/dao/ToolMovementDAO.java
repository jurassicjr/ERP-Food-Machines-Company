package database.dao;

import database.DataBase;
import model.ToolMovement;

public class ToolMovementDAO {
	
	private DataBase database;
	
	public ToolMovementDAO()
	{
		database = new DataBase();
		database.connect();
	}
	public void persist(ToolMovement toolMovement)
	{
		try {
			String sql = "insert into tool_movement(tool,toolbox,movimenttype,user,employee,date) "
					+ "values ?,?,?,?,?,?";	
			
			Object[] parameters = {
					toolMovement.getTool().getId(),
					toolMovement.getToolBox().getId(),
					toolMovement.getMovementType(),
					toolMovement.getUser().getId(),
					toolMovement.getEmployee().getId(),
					toolMovement.getDate()
			};
			
			database.executeUpdate(sql,parameters);
		} catch (Exception e) {
			
		}
	}
	
}
