package maintenance.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import database.dao.VehicleDAO;
import model.Vehicle;
import userInterface.components.VehicleTableModel;

public class VehicleSearchController {
	
	VehicleTableModel vehicleModel;
	
	public void queryAll(JTable vehicleTable)
	{
		VehicleDAO dao = new VehicleDAO();
		List vehicleList = dao.getList();
		if(!vehicleList.isEmpty())
			vehicleModel = new VehicleTableModel((ArrayList<Vehicle>) vehicleList);
		else
			vehicleModel = new VehicleTableModel();
		
		vehicleTable.setModel(vehicleModel);
		vehicleModel.fireTableDataChanged();
	}
	public void searchByDescription(JTable vehicleTable,String description)
	{
		VehicleDAO dao = new VehicleDAO();
		List vehicleList = dao.getListByDescription(description);
		if(!vehicleList.isEmpty())
			vehicleModel = new VehicleTableModel((ArrayList<Vehicle>) vehicleList);
		else
			vehicleModel = new VehicleTableModel();
		
		vehicleTable.setModel(vehicleModel);
		vehicleModel.fireTableDataChanged();
	}
}
