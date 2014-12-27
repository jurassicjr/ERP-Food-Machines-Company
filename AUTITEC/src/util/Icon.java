package util;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;

public class Icon {
	
	public static void setIcon(JFrame frame) {
		
		URL url = Icon.class.getResource("/resources/logo.png");  
		Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);  
		frame.setIconImage(imagemTitulo);  
		
	}

}
