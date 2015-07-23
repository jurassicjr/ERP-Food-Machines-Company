package database.dao;

import database.DataBase;
import model.VehicleDebt;

public class VehicleDebtDAO 
{
	private DataBase dataBase;
	
	public VehicleDebtDAO()
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void persist(VehicleDebt vehicleDebt)
	{
		try 
		{
			String sql = "insert into vehicle_debt("
					+ "vehicle,"
					+ "conductor,"
					+ "debttype,"
					+ "description,"
					+ "fueltype,"
					+ "fuelquantity,"
					+ "obs,"
					+ "paydate,"
					+ "duedate,"
					+ "value,"
					+ "user"
					+ ") values(?,?,?,?,?,?,?,?,?,?,?)";
			Object[] paramaters = 
				{
					vehicleDebt.getVehicle().getId(),
					vehicleDebt.getConductor().getId(),
					vehicleDebt.getDebtType(),
					vehicleDebt.getDescription(),
					vehicleDebt.getFuelType(),
					vehicleDebt.getFuelQuantity(),
					vehicleDebt.getObservations(),
					vehicleDebt.getPaydate(),
					vehicleDebt.getDuedate(),
					vehicleDebt.getValue(),
					vehicleDebt.getUser().getId()
				};
			dataBase.executeUpdate(sql,paramaters);
		
		} catch (Exception e) 
		{
			System.err.println(e);
		}
	}
}
