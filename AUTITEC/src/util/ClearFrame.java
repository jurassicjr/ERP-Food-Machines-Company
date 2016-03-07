package util;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.sf.nachocalendar.components.DateField;

public class ClearFrame {
	
	@SuppressWarnings("rawtypes")
	public static void clear(Container c) {
		
		List<Component> components = getAllComponents(c);
		
		for (Component e : components) {
			if(e instanceof JSpinner) ((JSpinner) e).setValue(0);
			else if(e instanceof JTextField)((JTextField) e).setText(null);
	        else if(e instanceof JComboBox) ((JComboBox) e).setSelectedIndex(-1);
	        else if(e instanceof JFormattedTextField) ((JFormattedTextField) e).setText(null);
	        else if(e instanceof DateField) ((DateField) e).setValue(null);
	        else if(e instanceof JTextArea) ((JTextArea) e).setText(null);
	        else if(e instanceof JTable) {
	        	DefaultTableModel tbl = (DefaultTableModel) ((JTable) e).getModel();
	        	for(int i = tbl.getRowCount() -1; i>=0; i--) {
	        		tbl.removeRow(i);
	        	}
	        }
        }
		
	}
	

	@SuppressWarnings("rawtypes")
	public static void clearWithoutTable(Container c) {
		
		List<Component> components = getAllComponents(c);
		for (Component e : components) {
			if(e instanceof JSpinner) ((JSpinner) e).setValue(0);
			else if(e instanceof JTextField)((JTextField) e).setText(null);
	        else if(e instanceof JComboBox) ((JComboBox) e).setSelectedIndex(-1);
	        else if(e instanceof JFormattedTextField) ((JFormattedTextField) e).setText(null);
	        else if(e instanceof DateField) ((DateField) e).setValue(null);
	        else if(e instanceof JTextArea) ((JTextArea) e).setText(null);	       
        }
	}
	
	private static List<Component> getAllComponents(final Container c) {
		
	    Component[] comps = c.getComponents();
	    List<Component> compList = new ArrayList<Component>();
	    
	    for (Component comp : comps) {
	        compList.add(comp);
	        if (comp instanceof Container)
	            compList.addAll(getAllComponents((Container) comp));
	    }
	    return compList;
	}
	public static void clearTable(JTable table)
	{
		
			DefaultTableModel tbl = (DefaultTableModel) table.getModel();
    	for(int i = tbl.getRowCount() -1; i>=0; i--) 
    		tbl.removeRow(i);
		
	}
	
}
