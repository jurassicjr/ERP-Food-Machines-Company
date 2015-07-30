package database.dao;

import database.DataBase;
import model.ObjectInfra;

public class ObjectInfraDAO 
{
	private DataBase database;

	public ObjectInfraDAO() 
	{
		this.database = new DataBase();
		this.database.connect();
	}
	
	public void persist(ObjectInfra objectInfra)// função insere
	{
		try 
		{
			String sql = "insert into object_infra (description) values (?)";
			//Object[] parameter = {objectInfra.getId(),objectInfra.getDescription()};
			database.executeUpdate(sql, objectInfra.getDescription());						
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
}
