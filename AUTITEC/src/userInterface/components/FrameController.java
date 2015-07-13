package userInterface.components;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import util.ShowMessage;

public class FrameController {
	
	public static void addConfirmationOnClose(JFrame frame,String frameName)
	{
		frame.setDefaultCloseOperation(frame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar o "
						+ "registro de veiculos?\n Os dados não salvos serão perdidos") == JOptionPane.YES_OPTION)
						frame.dispose();
				
			}
		});
	}
	public static void close(JFrame frame,String frameName)
	{
		if(ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar o "
				+ "registro de veiculos?\n Os dados não salvos serão perdidos") == JOptionPane.YES_OPTION)
				frame.dispose();
	}
	
}
