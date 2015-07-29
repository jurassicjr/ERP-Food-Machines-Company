package userInterface.components;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Rnc;

public class RncTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Rnc> lines;
	String[] headers =  new String[]{"Data","N.Sequência","Ativo"};
	
	public RncTableModel(ArrayList<Rnc> kits)
	{
		lines  = new ArrayList<Rnc>(kits);
	}
	
	public RncTableModel() {
		
		lines = new ArrayList<>();
		
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
		SimpleDateFormat spdt = new SimpleDateFormat("dd/MM/yyyy");
		switch (arg1) {
		case 0:
			return spdt.format(lines.get(arg0).getDate());
		case 1:
			return lines.get(arg0).getSequenceNumber();
		case 2:
			return lines.get(arg0).getIsActive();
		default:
			return null;
		}
	  
	}
	   public String getColumnName(int column) {
	        return headers[column];
	    }
	public void addRnc(Rnc client)
	{
			
		lines.add(client);
	}
	public Rnc getClient(Integer index)
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
