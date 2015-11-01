package database.dao;

import java.util.HashMap;

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
}
