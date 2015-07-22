package database.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Product;
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
	public boolean deleteByRoute(Integer routeDestinationId)
	{
		try{
			String sql = "delete from route_product where routedestination = ?";
			dataBase.executeUpdate(sql,routeDestinationId);
			return true;
		}catch(Exception ex)
		{
			return false;
		}
		
	}
	public List<RouteProduct> getListByRouteDestination(RouteDestination destination)
	{
		ArrayList<RouteProduct> routeList = new ArrayList<RouteProduct>();
		try{
			String sql = "select id,product,quantity from route_product where routedestination = ? ";
			
			ResultSet result =  dataBase.executeQuery(sql,destination.getId());
			while(result.next())
				{
					RouteProduct product = new RouteProduct();
					
					product.setId(result.getInt("id"));
					sql = "select * from Product where id = ?";
					ResultSet rs = dataBase.executeQuery(sql,result.getInt("product"));
					rs.next();
					
						Product pr = new Product();
						pr.setId(rs.getInt("id"));
						pr.setDescription(rs.getString("descricao"));
						pr.setName(rs.getString("name"));
						product.setProduct(pr);
						product.setRouteDestination(destination);
						product.setQuantity(result.getDouble("quantity"));
						routeList.add(product);
					
				}

		}catch(Exception ex)
		{
			System.err.println(ex);
		}
		return routeList;
	}
	
}
