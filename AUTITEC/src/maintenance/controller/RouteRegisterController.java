package maintenance.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import model.Client;
import model.Employee;
import model.Product;
import model.Route;
import model.RouteDestination;
import model.RouteProduct;
import model.Vehicle;
import database.DataBase;
import database.dao.RouteDAO;
import database.dao.RouteDestinationDAO;
import database.dao.RouteProductDAO;
import database.dao.VehicleDAO;

public class RouteRegisterController 
{
	
	private DataBase dataBase;
	
	
	
	public RouteRegisterController() 
	{
		 dataBase = new DataBase();
		 dataBase.connect();
	}
	
	public void fillVehicleCbo(JComboBox<Vehicle> cboVehicle)
	{
		VehicleDAO dao = new VehicleDAO();
		List vehicleList = dao.getList();
		cboVehicle.removeAllItems();
		if(vehicleList.size() > 0)
		{	
			ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleList;
			for(Vehicle vehicle : vehicles)
				cboVehicle.addItem(vehicle);
		}
	}
	public void fillClientCbo(JComboBox<Client> cboClient)
	{
		 String sql = "SELECT id,name FROM client order by name";
		 cboClient.removeAllItems();
		 
		 try{
			 ResultSet rs = dataBase.executeQuery(sql);
				while(rs.next()) {
					
					Client client  = new Client();
					client.setId(rs.getInt("id"));
					client.setName(rs.getString("name"));
					cboClient.addItem(client);
			}
		 }catch(Exception ex)
		 {
			 
		 }
	}
	public void fillEmployeeCbo(JComboBox<Employee> cboEmployee)
	{
		 String sql = "SELECT id,name FROM employee order by name";
		 cboEmployee.removeAllItems();
		 
		 try{
			 ResultSet rs = dataBase.executeQuery(sql);
				while(rs.next()) {
					
					Employee employee  = new Employee();
					employee.setId(rs.getInt("id"));
					employee.setName(rs.getString("name"));
					cboEmployee.addItem(employee);
			}
		 }catch(Exception ex)
		 {
			 
		 }
	}
	public void fillProductCbo(JComboBox<Product> cboProduct)
	{
		 String sql = "SELECT id,name FROM Product order by name";
		 cboProduct.removeAllItems();
		 
		 try{
			 ResultSet rs = dataBase.executeQuery(sql);
				while(rs.next()) {
					
					Product product  = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					cboProduct.addItem(product);
			}
		 }catch(Exception ex)
		 {
			 
		 }
	}
	public boolean insertRoute(Route route)
	{
		RouteDAO dao = new RouteDAO();
		return dao.persist(route);
	}
	public boolean insertRouteDestination(RouteDestination routeDestination)
	{
		RouteDestinationDAO dao = new RouteDestinationDAO();
		return dao.persist(routeDestination);
	}
	public boolean insertRouteProduct(RouteProduct routeProduct)
	{
		RouteProductDAO dao = new RouteProductDAO();
		return dao.persist(routeProduct);
	}
}
