package database.dao;

import database.DataBase;
import model.Tool;

public class ToolDAO {

	private DataBase dataBase;
	public ToolDAO() 
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	public void persist(Tool tool)
	{
		try {
			String sql = "insert into tool(brand,model,description,serialnumber) values(?,?,?,?)";
			Object[] parameters = {
					tool.getBrand(),
					tool.getModel(),
					tool.getDescription(),
					tool.getSerialNumber()
					
			};
			
			dataBase.executeUpdate(sql,parameters);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	
	

}
