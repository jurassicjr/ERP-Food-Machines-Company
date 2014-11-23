package util;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ShowMessage {
	
	public static void showError(Component c, String title, String message) {
		
		if(c == null) c = JFrame.getFrames()[0];
		
		JOptionPane.showMessageDialog(c, message, title, JOptionPane.ERROR_MESSAGE, null);
	}
	
	public static void showSuccess(Component c, String title, String message) {
		
		if(c == null) c = JFrame.getFrames()[0];
		
		JOptionPane.showMessageDialog(c, message, title, JOptionPane.PLAIN_MESSAGE, null);
		
	}
	
}
