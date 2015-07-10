package database.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import database.DataBase;
import model.Vehicle;

public class VehicleDAO {
	
	private DataBase dataBase;
	
	
	public VehicleDAO()
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	public boolean delete(Integer vehicleId)
	{
		try {
			String sql = "delete from vehicle where id = ?";
			dataBase.executeUpdate(sql,vehicleId);
			return true;
		} catch (Exception e) {
			return false;
		}
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
	public boolean update(Vehicle vehicle)
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
					vehicle.getLicenseDate(),
					vehicle.getId()
				};
			
			String sql = "update vehicle set "
					+ "description=?,"
					+ "type=?,"
					+ "model=?,"
					+ "brand=?,"
					+ "chassi=?,"
					+ "renavan=?,"
					+ "placa=?,"
					+ "initialkm=?,"
					+ "lastoilchange=?,"
					+ "oilchangeinterval=?,"
					+ "ipvadate=?,"
					+ "licensedate=? "
					+ " where id = ?";
			
			dataBase.executeUpdate(sql,fields);
			return true;
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	public List getList()
	{
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		try{ 
			ResultSet result = dataBase.executeQuery("select id,description from vehicle");
			while(result.next())
			{
				Vehicle vehicle = new Vehicle();
				vehicle.setId(result.getInt("id"));
				vehicle.setDescription(result.getString("description"));
				vehicles.add(vehicle);
			}
		}catch(Exception ex)
		{
			
		}
		return vehicles;
	}
	public List getListByDescription(String description)
	{
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		try{ 
			description+="%";
			ResultSet result = dataBase.executeQuery("select id,description from vehicle where description like ?",description);
			while(result.next())
			{
				Vehicle vehicle = new Vehicle();
				vehicle.setId(result.getInt("id"));
				vehicle.setDescription(result.getString("description"));
				vehicles.add(vehicle);
			}
		}catch(Exception ex)
		{
			
		}
		return vehicles;
	}
	public Vehicle getRegister(Integer vehicleId)
	{
		Vehicle vehicle = new Vehicle();
		try{ 
			ResultSet result = dataBase.executeQuery("select * from vehicle where id = ?",vehicleId);
			if(result.next())
			{
				vehicle.setId(result.getInt("id"));
				vehicle.setDescription(result.getString("description"));
				vehicle.setType(result.getString("type"));
				vehicle.setModel(result.getString("model"));
				vehicle.setBrand(result.getString("brand"));
				vehicle.setRenavan(result.getString("renavan"));
				vehicle.setChassi(result.getString("chassi"));
				vehicle.setPlaca(result.getString("placa"));
				vehicle.setInitialKm(result.getDouble("initialkm"));
				vehicle.setLastOilChange(result.getDate("lastoilchange"));
				vehicle.setOilChangeInterval(result.getDouble("oilchangeinterval"));
				vehicle.setIpvaDate(result.getDate("ipvadate"));
				vehicle.setLicenseDate(result.getDate("licensedate"));
			}
		}catch(Exception ex)
		{
			
		}
		return vehicle;
	}
		
}
