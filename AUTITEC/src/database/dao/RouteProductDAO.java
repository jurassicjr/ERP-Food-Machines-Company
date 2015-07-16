package database.dao;

import model.Client;
import model.RouteDestination;
import model.RouteProduct;
import database.DataBase;

public class RouteProductDAO {
	

	private DataBase dataBase;
	
	public RouteProductDAO()
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	public boolean persist(RouteProduct routeProduct)
	{
		try{
			String sql = "insert into route_product(product,quantity,routedestination) "
				+ "value(?,?,(select id from route_destination order by id desc limit 1))";
			
			Object[] parameters = 
				{
					routeProduct.getProduct().getId(),
					routeProduct.getQuantity()
				};
			dataBase.executeUpdate(sql,parameters);
			return true;
		}catch(Exception ex)
		{
			return false;
		}
		
	}
	
}
