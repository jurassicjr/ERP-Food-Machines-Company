package userInterface.components;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Client;
import model.Kit;

public class ClientTableModel extends AbstractTableModel {
	
	ArrayList<Client> lines;
	String[] headers =  new String[]{"Nome/Razão"};
	
	public ClientTableModel(ArrayList<Client> kits)
	{
		lines  = new ArrayList<Client>(kits);
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
	public void addKit(Client client)
	{
			
		lines.add(client);
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
