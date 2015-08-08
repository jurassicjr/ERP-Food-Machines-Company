package userInterface.components;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.Tool;
import model.ToolBox;

public class ToolTableModel  extends AbstractTableModel{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1501797898564236821L;
	ArrayList<Tool> lines;
	String[] headers =  new String[]{"Ferramenta"};
		
	public ToolTableModel()
	{
		lines = new ArrayList<Tool>();
	}
	
	public ToolTableModel(ArrayList<Tool> tools)
	{
		lines  = new ArrayList<Tool>(tools);
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
    public void setValueAt(Object object,int rowIndex, int columnIndex) {
       Tool tool =new Tool();
        String value = (String)object;
    
        switch(columnIndex){
            case 0:
                 tool.setDescription(value);
           
            default:
            {}
        }
         
       fireTableRowsInserted(rowIndex, rowIndex);

    }
	 @Override
	    public Class<?> getColumnClass(int columnIndex) {
	        switch(columnIndex){
	        case 0:
	            return String.class;


	        default:
	            return null;
	        }
	    }

	@Override
	public Object getValueAt(int arg0, int arg1) {
	    return  lines.get(arg0).toString();
	  
	}
	   public String getColumnName(int column) {
	        return headers[column];
	    }
	public void addTool(Tool Tool)
	{
			
		lines.add(Tool);
		fireTableRowsInserted(lines.size()-1,lines.size());
	}
	public Tool getTool(Integer index)
	{
		return lines.get(index);
		
	}
	public void removeTool(Integer line)
	{
		lines.remove(line);
		fireTableRowsDeleted(line,line);
		
	}
	public void clear()
	{
		lines.clear();	
		fireTableRowsUpdated(0, lines.size()-1);
	}
	public ArrayList<Tool> getAsArrayList()
	{
		return lines;
	}
	
}
