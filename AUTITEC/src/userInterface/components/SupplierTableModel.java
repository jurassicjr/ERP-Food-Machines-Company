package userInterface.components;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Supplier;

public class SupplierTableModel extends AbstractTableModel
{
	ArrayList<Supplier> lines;
	String[] headers =  new String[]{"Razão social"};
	
	public SupplierTableModel(ArrayList<Supplier> kits)
	{
		lines  = new ArrayList<Supplier>(kits);
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
		return lines.get(arg0).getCorporateName();
	}
	   public String getColumnName(int column) {
	        return headers[column];
	    }
	public void addClient(Supplier supplier)
	{
		lines.add(supplier);
	}
	public Supplier getClient(Integer index)
	{
		return lines.get(index);
		
	}
	public void removeClient(Integer line)
	{
		lines.remove(line);
		
	}
	public void clear()
	{
		lines.clear();
		
	}

}
