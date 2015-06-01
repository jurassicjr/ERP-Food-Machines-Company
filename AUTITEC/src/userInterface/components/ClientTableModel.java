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
	  String value = lines.get(arg0).getName();
	  return  !value.isEmpty() ? value :lines.get(arg0).getCompanyNAme();
	}
	   public String getColumnName(int column) {
	        return headers[column];
	    }
	public void addClient(Client client)
	{
			
		lines.add(client);
	}
	public Client getClient(Integer index)
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
