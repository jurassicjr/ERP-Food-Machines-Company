package database.dao;

import java.sql.ResultSet;

import model.Route;
import database.DataBase;

public class RouteDAO {
	
private DataBase dataBase;
	
	
	public RouteDAO()
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public boolean persist(Route route)
	{
		try{
			String sql = "insert into route(description,date,initialhodometer,vehicle,conductor,user) "
				+ "value(?,?,?,?,?,?)";
		
			Object[] parameters = 
				{
					route.getDescription(),
					route.getDate(),
					route.getInitialKm(),
					route.getVehicle().getId(),
					route.getConductor().getId(),
					route.getUser().getId()
				};
			dataBase.executeUpdate(sql,parameters);
			return true;
		}catch(Exception ex)
		{
			return false;
		}
		
	}
}
