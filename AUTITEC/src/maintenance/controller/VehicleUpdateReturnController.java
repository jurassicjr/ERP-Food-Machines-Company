package maintenance.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import model.Route;
import model.RouteDestination;
import model.RouteProduct;
import database.dao.RouteDAO;
import database.dao.RouteDestinationDAO;
import database.dao.RouteProductDAO;

public class VehicleUpdateReturnController {

	
	public VehicleUpdateReturnController() {
		super();	
	}
	
	public ArrayList<Route> getActiveRoutes()
	{
		List routes =  new RouteDAO().getList();
		if(!routes.isEmpty())
			return (ArrayList<Route>) routes;
		else
			return new ArrayList<Route>();
	}
	public ArrayList<RouteDestination> getDestinationsByRoute(Route route)
	{
		
		List destinations = new RouteDestinationDAO().getListByRoute(route);

		if(!destinations.isEmpty())
			return (ArrayList<RouteDestination>) destinations;
		else
			return new ArrayList<RouteDestination>();
	}
	public ArrayList<RouteProduct> getProductssByRouteDestination(RouteDestination routeDestination)
	{
		
		List products = new RouteProductDAO().getListByRouteDestination(routeDestination);

		if(!products.isEmpty())
			return (ArrayList<RouteProduct>) products;
		else
			return new ArrayList<RouteProduct>();
	}
	public boolean updateRoute(Route route)
	{
		try {
			 System.err.println(route.getReturnKm());
			 return  new RouteDAO().update(route);
	
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
	}
	public boolean deleteRoute(DefaultMutableTreeNode routeNode)
	{
		try {
			Route route = (Route) routeNode.getUserObject();
			List destList = new RouteDestinationDAO().getListByRoute(route);
			if(!destList.isEmpty())
			{
				ArrayList<RouteDestination> destinations = (ArrayList<RouteDestination>) destList;
				for(RouteDestination destination :destinations)
				{
					new RouteProductDAO().deleteByRoute(destination.getId());	
				}
				new RouteDestinationDAO().deleteByRoute(route.getId());
			}
			new RouteDAO().delete(route);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
	}
}
