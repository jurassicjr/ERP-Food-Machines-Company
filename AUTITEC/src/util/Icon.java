package util;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class Icon {
	
	public static void setIcon(JFrame frame) {
		
		URL url = Icon.class.getResource("/resources/logo.png");  
		Image image = Toolkit.getDefaultToolkit().getImage(url);  
		frame.setIconImage(image);  
		
	}
	
	public static void setIcon(JDialog dialog) {
		
		URL url = Icon.class.getResource("/resources/logo.png");  
		Image image = Toolkit.getDefaultToolkit().getImage(url);  
		dialog.setIconImage(image);
		
	}

}
