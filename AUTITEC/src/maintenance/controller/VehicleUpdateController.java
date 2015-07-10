package maintenance.controller;

import java.util.ArrayList;

import javax.swing.JComboBox;

import database.dao.VehicleDAO;
import model.Vehicle;

public class VehicleUpdateController {
	
    
	public void fillCboVehicles(JComboBox<Vehicle> cboVehicle)
	{
		VehicleDAO dao = new VehicleDAO();
		ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) dao.getList();
		cboVehicle.removeAllItems();
		for(Vehicle regVehicle:vehicles)
			cboVehicle.addItem(regVehicle);
			
	}
	
	public void fillCboBrand(JComboBox cboBrands)
	{
		ArrayList<String> brandsList = new ArrayList<String>(){{
			add("Agrale");
			add("Aston Martin");
			add("Audi");
			add("Bentley");
			add("BMW");
			add("Chery");
			add("GM/Chevrolet");
			add("Chrysler");
			add("Citroën");
			add("Dodge");
			add("Effa");
			add("Ferrari");
			add("Fiat");
			add("Ford");
			add("Geely");
			add("Hafei");
			add("Honda");
			add("Hyundai");
			add("Iveco");
			add("Jac Motors");
			add("Jaguar");
			add("Jeep");
			add("Jinbei");
			add("Kia");
			add("Lamborghini");
			add("Land Rover");
			add("Lexus");
			add("Lifan");
			add("Mahindra");
			add("Maserati");
			add("Mercedes-Benz");
			add("MG Motors");
			add("Mini");
			add("Mitsubishi");
			add("Nissan");
			add("Peugeot");
			add("Porsche");
			add("Ram");
			add("Renault");
			add("Smart");
			add("Ssangyong");
			add("Subaru");
			add("Suzuki");
			add("Toyota");
			add("Troller");
			add("Volkswagen");
			add("Volvo");
		}};
		cboBrands.removeAllItems();
		
		for(String type:brandsList)
			cboBrands.addItem(type);
	}
	public void fillCboType(JComboBox cboTypes)
	{
		ArrayList<String> typesList = new ArrayList<String>(){{
			add("Convencional");
			add("Utilitário");
			add("Camioneta");
			add("Caminhonete");
			add("Caminhão");
			add("Motocicleta");
			
		}};
		cboTypes.removeAllItems();
		
		for(String type:typesList)
			cboTypes.addItem(type);
	}
	public Boolean updateVehicle(Vehicle vehicle)
	{
		VehicleDAO dao = new VehicleDAO();
		return dao.update(vehicle);
	}
	public boolean deleteVehicle(Integer vehicleId)
	{
		VehicleDAO dao = new VehicleDAO();
		return dao.delete(vehicleId);
		
	}
	public Vehicle getRegister(Integer vehicleId)
	{
		VehicleDAO dao = new VehicleDAO();
		return dao.getRegister(vehicleId);
	}
	
}
