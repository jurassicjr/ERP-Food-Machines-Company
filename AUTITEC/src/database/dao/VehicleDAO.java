package database.dao;

import database.DataBase;
import model.Vehicle;

public class VehicleDAO {
	
	private DataBase dataBase;
	
	
	public VehicleDAO()
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	public boolean persist(Vehicle vehicle)
	{
		try {
			Object[] fields = 
				{
					vehicle.getDescription(),
					vehicle.getType(),
					vehicle.getModel(),
					vehicle.getBrand(),
					vehicle.getChassi(),
					vehicle.getRenavan(),
					vehicle.getPlaca(),
					vehicle.getInitialKm(),
					vehicle.getLastOilChange(),
					vehicle.getOilChangeInterval(),
					vehicle.getIpvaDate(),
					vehicle.getLicenseDate()
				};
			
			String sql = "insert into vehicle("
					+ "description,"
					+ "type,"
					+ "model,"
					+ "brand,"
					+ "chassi,"
					+ "renavan,"
					+ "placa,"
					+ "initialkm,"
					+ "lastoilchange,"
					+ "oilchangeinterval,"
					+ "ipvadate,"
					+ "licensedate) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			
			dataBase.executeUpdate(sql,fields);
			return true;
		}
		catch (Exception e) 
		{
			return false;
		}
		
	}
}
