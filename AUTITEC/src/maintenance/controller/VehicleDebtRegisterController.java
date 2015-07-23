package maintenance.controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import database.DataBase;
import database.dao.VehicleDAO;
import database.dao.VehicleDebtDAO;
import model.Employee;
import model.Vehicle;
import model.VehicleDebt;

public class VehicleDebtRegisterController {
	
	
	private DataBase dataBase;
	
	
	
	public VehicleDebtRegisterController() {
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
	public void fillFuelTypeCbo(JComboBox<String> cboFuel)
	{
		cboFuel.removeAllItems();
		ArrayList<String> fuelTypes = new ArrayList<String>(){{
		add("Biodiesel");
		add("Diesel Comum");
		add("Etanol");
		add("Gasolina");
		add("Gasolina Aditivada");
		}};
		for(String fuelType : fuelTypes)
		{
			cboFuel.addItem(fuelType);
		}
	}
	public void insertVehicleDebt(VehicleDebt vehicleDebt)
	{
		VehicleDebtDAO dao = new VehicleDebtDAO();
		dao.persist(vehicleDebt);
	}
}
