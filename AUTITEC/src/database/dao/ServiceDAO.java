package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.OutSourcedServices;
import model.Service;
import model.Supplier;
import database.DataBase;

public class ServiceDAO {

	
	private DataBase dataBase;

	public ServiceDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(HashMap<String, Object> map) {
	   double meal = (double) map.get("meal");
	   String stayPrice = (String) map.get("stayValue");
	   double price_I_I_I = (double) map.get("price_I_I_I");
	   double price_I_I_II =  (double) map.get("price_I_I_II");
	   double price_I_II_I = (double) map.get("price_I_II_I");
	   double price_I_II_II = (double) map.get("price_I_II_II");
	   double price_I_III_I = (double) map.get("price_I_III_I");
	   double price_I_III_II = (double) map.get("price_I_III_II");
	   double price_II_I_I = (double) map.get("price_II_I_I");
	   double price_II_I_II = (double) map.get("price_II_I_II");
	   double price_II_II_I = (double) map.get("price_II_II_I");
	   double price_II_II_II = (double) map.get("price_II_II_II");
	   double price_II_III_I = (double) map.get("price_II_III_I");
	   double price_II_III_II = (double) map.get("price_II_III_II");
	   double pricePerKm = (double) map.get("pricePerkm");
	   String description = (String) map.get("observation");
	   String name = (String) map.get("name");
	   
	   
	   String query = "INSERT INTO service(meal, stay_price, price_I_I_I, price_I_I_II, price_I_II_I, "
	   		+ "price_I_II_II, price_I_III_I, price_I_III_II, price_II_I_I, price_II_I_II, price_II_II_I, "
	   		+ "price_II_II_II, price_II_III_I, price_II_III_II, price_per_km, description, name) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	   
	   Object[] data = new Object[] {meal, stayPrice, price_I_I_I, price_I_I_II, price_I_II_I, price_I_II_II,
			   price_I_III_I, price_I_III_II, price_II_I_I, price_II_I_II, price_II_II_I, price_II_II_II,
			   price_II_III_I, price_II_III_II, pricePerKm, description, name};
	   
	   dataBase.executeUpdate(query, data);
    }

	public Service getServiceByID(int serviceID) {
		
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM service")) {
			if (rs.next()) {
				Service s = new Service();
				s.setId(rs.getInt("id"));
				s.setMeal(rs.getDouble("meal"));
				s.setName(rs.getString("name"));
				s.setObservation(rs.getString("description"));
				s.setPrice_I_I_I(rs.getDouble("price_I_I_I"));
				s.setPrice_I_I_II(rs.getDouble("price_I_I_II"));
				s.setPrice_I_II_I(rs.getDouble("price_I_II_I"));
				s.setPrice_I_II_II(rs.getDouble("price_I_II_II"));
				s.setPrice_I_III_I(rs.getDouble("price_I_III_I"));
				s.setPrice_I_III_II(rs.getDouble("price_I_III_II"));
				s.setPrice_II_I_I(rs.getDouble("price_II_I_I"));
				s.setPrice_II_I_II(rs.getDouble("price_II_I_II"));
				s.setPrice_II_II_I(rs.getDouble("price_II_II_I"));
				s.setPrice_II_II_II(rs.getDouble("price_II_II_II"));
				s.setPrice_II_III_I(rs.getDouble("price_II_III_I"));
				s.setPrice_II_III_II(rs.getDouble("price_II_III_II"));
				s.setPricePerKm(rs.getDouble("price_per_km"));
				s.setStayValue(rs.getString("stay_price"));
				return s;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    }

	public void registerOutSourcedService(HashMap<String, Object> map) {
	    String name = (String) map.get("name");
	    String observation = (String) map.get("observation");
	    String sql = "insert into outsourced_services(name, observation) values (?,?)";
	    Object[] data = new Object[] {name, observation};
	    dataBase.executeUpdate(sql, data);
	    dataBase.close();
    }

	public List<OutSourcedServices> getAllOutSourcedService() {
	    String sql = "select * from outsourced_services";
	    List<OutSourcedServices> list = new ArrayList<OutSourcedServices>();
	    try(ResultSet rs = dataBase.executeQuery(sql)){
	    	while(rs.next()) {
	    		String name = rs.getString("name");
	    		int id = rs.getInt("id");
	    		String observation = rs.getString("observation");
	    		OutSourcedServices oss = new OutSourcedServices(name, observation);
	    		oss.setId(id);
	    		list.add(oss);
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return list;
    }

	public List<String> getShippingCompany() {
	    String sql = "select * from supplier_service_association where service = "
	    		+ "(select rs.id from outsourced_services as rs where rs.name = ?)";
	    List<String> shippingCompanyList = new ArrayList<String>();
	    String name = "Transportadora";
	    try(ResultSet rs = dataBase.executeQuery(sql, name)){
	    	while(rs.next()) {
	    		int supplierId = rs.getInt("supplier");
	    		Supplier supplier = new SuppliersDAO().getSupplierbyId(supplierId);
	    		String supplierName = supplier.getCompanyName();
	    		shippingCompanyList.add(supplierName);
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return shippingCompanyList;
    }
}
