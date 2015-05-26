package userInterface.components;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Kit;

public class KitTableModel extends AbstractTableModel {

	ArrayList<Kit> lines;
	String[] headers =  new String[]{"Nome do kit"} ;
	
	public KitTableModel(ArrayList<Kit> kits)
	{
		lines  = new ArrayList<Kit>(kits);
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
	    return lines.get(arg0);
	}
	   public String getColumnName(int column) {
	        return headers[column];
	    }
	public void addKit(Kit kit)
	{
			
		lines.add(kit);
	}
	public void removeKit(Integer line)
	{
		lines.remove(line);
		
	}
	public void clear()
	{
		lines.clear();
		
	}
	
}
