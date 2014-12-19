package util;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.sf.nachocalendar.components.DateField;

public class ClearFrame {

	private JFrame frame;

	public ClearFrame(JFrame frame) {
	    this.frame = frame;
    }
	
	public void clear() {
		List<Component> components = this.getAllComponents(frame);
		for (Component e : components) {
	        if(e instanceof JTextField)((JTextField) e).setText(null);
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
}
