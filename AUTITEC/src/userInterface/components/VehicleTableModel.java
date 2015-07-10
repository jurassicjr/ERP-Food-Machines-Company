package userInterface.components;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Vehicle;

public class VehicleTableModel extends AbstractTableModel {
	
	ArrayList<Vehicle> lines;
	String[] headers =  new String[]{"Descrição"};
	
	public VehicleTableModel(ArrayList<Vehicle> vehicles)
	{
		lines  = new ArrayList<Vehicle>(vehicles);
	}
	public VehicleTableModel() {
		lines  = new ArrayList<Vehicle>();
	}
	@Override
	public int getColumnCount() {
		return headers.length;
	}
	@Override
	public int getRowCount() {
		return lines.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
	  return lines.get(arg0).getDescription();
	}
	   public String getColumnName(int column) {
	        return headers[column];
	    }
	public void addVehicle(Vehicle Vehicle)
	{	
		lines.add(Vehicle);
	}
	public Vehicle getVehicle(Integer index)
	{
		return lines.get(index);
		
	}
	public void removeVehicle(Integer line)
	{
		lines.remove(line);
		
	}
	public void clear()
	{
		lines.clear();
		
	}
	
	
}
