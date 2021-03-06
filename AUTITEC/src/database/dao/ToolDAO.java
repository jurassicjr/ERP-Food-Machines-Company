package database.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

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
	public void update(Tool tool)
	{
		
		try {
			String sql = "update tool set "
					+ "brand = ?,"
					+ "model = ?,"
					+ "description=?,"
					+ "serialnumber=? where id = ?";
			Object[] parameters = {
					tool.getBrand(),
					tool.getModel(),
					tool.getDescription(),
					tool.getSerialNumber(),
					tool.getId()	
			};
			
			dataBase.executeUpdate(sql,parameters);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void removeByToolBox(Integer toolBoxId)
	{
		try {
			String sql = "delete from toolbox_tool where toolbox = ?";
			dataBase.executeUpdate(sql,toolBoxId);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public ArrayList<Tool> getList()
	{
		ArrayList<Tool> tools = new ArrayList<>();
		try {
			String sql = "select * from tool order by model"; 
			ResultSet rs = dataBase.executeQuery(sql);
			Tool tool;
			while(rs.next())
			{
				tool = new Tool();
				tool.setId(rs.getInt("id"));
				tool.setBrand(rs.getString("brand"));
				tool.setModel(rs.getString("model"));
				tool.setDescription(rs.getString("description"));
				tool.setSerialNumber(rs.getString("serialnumber"));
				tools.add(tool);
			}
		}catch(Exception ex)
		{
			
		}
		return tools;	
	}
	public ArrayList<Tool> getListByToolBox(Integer toolBoxId)
	{
		ArrayList<Tool> tools = new ArrayList<>();
		try {
			String sql = "select * from tool,toolbox_tool where tool.id = toolbox_tool.tool "
					+ "and toolbox_tool.toolbox = ? order by model"; 
			ResultSet rs = dataBase.executeQuery(sql,toolBoxId);
			Tool tool;
			while(rs.next())
			{
				tool = new Tool();
				tool.setId(rs.getInt("id"));
				tool.setBrand(rs.getString("brand"));
				tool.setModel(rs.getString("model"));
				tool.setDescription(rs.getString("description"));
				tool.setSerialNumber(rs.getString("serialnumber"));
				tools.add(tool);
			}
		}catch(Exception ex)
		{
			
		}
		return tools;	
	}
	public ArrayList<Tool> getUnusedTools()
	{
		ArrayList<Tool> tools = new ArrayList<>();
		try {
			String sql = "select * from tool  where id not in (select tool from toolbox_tool) order by model"; 
			ResultSet rs = dataBase.executeQuery(sql);
			Tool tool;
			while(rs.next())
			{
				tool = new Tool();
				tool.setId(rs.getInt("id"));
				tool.setBrand(rs.getString("brand"));
				tool.setModel(rs.getString("model"));
				tool.setDescription(rs.getString("description"));
				tool.setSerialNumber(rs.getString("serialnumber"));
				tools.add(tool);
			}
		}catch(Exception ex)
		{
			
		}
		return tools;	
	}
	public Tool getRegister(Integer toolId)
	{
			Tool tool = new Tool();
		try {
				String sql = "select * from tool where id = ?"; 
				ResultSet rs = dataBase.executeQuery(sql,toolId);
				if(rs.next())
				{
					tool = new Tool();
					tool.setId(rs.getInt("id"));
					tool.setBrand(rs.getString("brand"));
					tool.setModel(rs.getString("model"));
					tool.setDescription(rs.getString("description"));
					tool.setSerialNumber(rs.getString("serialnumber"));
			
				}
			}catch(Exception ex)
			{
				
			}
			return tool;
			
		}
	public void remove(Integer toolId)
	{
		Tool tool = new Tool();
		try {
				String sql = "delete from tool where id = ?"; 
				dataBase.executeUpdate(sql,toolId);
				
			}catch(Exception ex)
			{
				
			}
			
	}
	
	
	

}
