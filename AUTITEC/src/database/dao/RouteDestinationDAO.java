package database.dao;

import model.Client;
import model.RouteDestination;
import model.RouteProduct;
import database.DataBase;

public class RouteDestinationDAO {

	private DataBase dataBase;
	
	public RouteDestinationDAO()
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	public boolean persist(RouteDestination routeDestination)
	{
		try{
			String sql = "insert into route_destination(route,client,otherdestination) "
				+ "value((select id from route order by id desc limit 1),?,?)";
			
			Client client = routeDestination.getClient() == null ? routeDestination.getClient() : new Client();
			
			Object[] parameters = 
				{
					routeDestination.getClient().getId(),
					routeDestination.getOtherDestination(),
				};
			dataBase.executeUpdate(sql,parameters);
			return true;
		}catch(Exception ex)
		{
			return false;
		}
		
	}
}
