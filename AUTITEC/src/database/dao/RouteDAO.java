package database.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Route;
import model.User;
import model.Vehicle;
import database.DataBase;

public class RouteDAO {
	
private DataBase dataBase;
	
	
	public RouteDAO()
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	public boolean update(Route route)
	{
		try{
			String sql = "update route set returnhodometer=?,returndate=?,returnobs=? where id = ?";
			
			Object[] parameters = 
				{
					route.getReturnKm(),
					route.getReturnDate(),
					route.getReturnObs(),
					route.getId()
				};
			
			dataBase.executeUpdate(sql,parameters);
			return true;
		}catch(Exception ex)
		{
			System.err.println(ex);
			return false;
		}
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
	public List<Route> getList()
	{
		ArrayList<Route> routeList = new ArrayList<Route>();
		try{
			String sql = "select id,description,date from route where returndate is null";
			ResultSet result =  dataBase.executeQuery(sql);
			while(result.next())
				{
					Route route = new Route();
					route.setId(result.getInt("id"));
					route.setDescription(result.getString("description"));
					route.setDate(result.getDate("date"));
					routeList.add(route);
				};

		}catch(Exception ex)
		{
			
		}
		return routeList;
	}
	public boolean delete(Route route)
	{
			try{
				String sql = "delete from route where id = ?";
		
				dataBase.executeUpdate(sql,route.getId());
				return true;
			}catch(Exception ex)
			{
				return false;
			}
			
		
	}
}
