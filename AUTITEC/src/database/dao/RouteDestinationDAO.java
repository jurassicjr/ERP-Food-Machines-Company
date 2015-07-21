package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.City;
import model.Client;
import model.Route;
import model.RouteDestination;
import model.State;
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
			
			Client client = routeDestination.getClient() != null ? routeDestination.getClient() : new Client();
			
			Object[] parameters = 
				{
					client.getId(),
					routeDestination.getOtherDestination(),
				};
			dataBase.executeUpdate(sql,parameters);
			return true;
		}catch(Exception ex)
		{
			return false;
		}
		
	}
	public boolean deleteByRoute(Integer routeId)
	{
		try{
			String sql = "delete from route_destination where route = ?";
			
		
			dataBase.executeUpdate(sql,routeId);
			return true;
		}catch(Exception ex)
		{
			return false;
		}
		
	}
	public List<RouteDestination>  getListByRoute(Route route)
	{
		ArrayList<RouteDestination> routeList = new ArrayList<RouteDestination>();
		try{
			String sql = "select id,client,otherdestination from route_destination where route = ?";
			ResultSet result =  dataBase.executeQuery(sql,route.getId());
			while(result.next())
				{
					RouteDestination routeDestination = new RouteDestination();
					routeDestination.setId((result.getInt("id")));
					Integer cliendId = result.getInt("client");
					if(cliendId!=null)
					{
						sql = "select id,name,companyname from client where id = ? ";
						ResultSet rs = dataBase.executeQuery(sql,cliendId);
						if(rs.next())
						{
						    Client client  = new Client();
						    client.setId(rs.getInt("id"));
							client.setName(rs.getString("name"));
							client.setCompanyNAme(rs.getString("companyname"));
							routeDestination.setClient(client);
						}
					}
					
					routeDestination.setRoute(route);
					routeDestination.setOtherDestination(result.getString("otherdestination"));
					routeList.add(routeDestination);
				};

		}catch(Exception ex)
		{
			System.err.println(ex);
		}
		
		return routeList;
		
	}
	
	private void setClientData(Client client,ResultSet rs)
	{
	
		
		try {
			client.setId(rs.getInt("id"));
			client.setName(rs.getString("name"));
			client.setCompanyNAme(rs.getString("companyname"));
			client.setStreet(rs.getString("street"));
			client.setBirthDate(rs.getDate("birthdate"));
			client.setNeighborhood(rs.getString("neighborhood"));
			client.setState(new State(rs.getInt("state"),""));
			client.setCity(new City(rs.getInt("city"),"",client.getState()));
			client.setCep(rs.getString("cep"));
			client.setPhone(rs.getString("phone"));
			client.setEmail(rs.getString("email"));
			client.setCompanyNAme(rs.getString("companyname"));
			client.setCpf(rs.getString("cpf"));
			client.setCnpj(rs.getString("cnpj"));
			client.setStateInscrition(rs.getString("ie"));
			client.setSex(rs.getString("sex"));
			client.setContactName(rs.getString("companycontactname"));
			client.setRg(rs.getString("rg"));
		} catch (SQLException e) {
			
		}
		
	}
	
}
