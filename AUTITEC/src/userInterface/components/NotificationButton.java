package userInterface.components;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JButton;

public class NotificationButton extends JButton {
	
	private static final long serialVersionUID = -788661648052939959L;

	public NotificationButton(String text, boolean urgent) {
		
		text = text.replaceAll("\n", "<br>");
		text = "<html><p align='center'>" + text + "</p></html>";
		
		if(urgent) setForeground(Color.red);
		
		setText(text);
		
	}
	
	public NotificationButton(String text, Icon icon, boolean urgent) {
		
		text = text.replaceAll("\n", "<br>");
		text = "<html><p align='center'>" + text + "</p></html>";
		
		if(urgent) setForeground(Color.red);
		
		setText(text);
		setIcon(icon);
		
	}
	
}
