package rh.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import util.ShowMessage;

public class ClientPropertiesOutputController {

	
	private JFrame frame;

	public ClientPropertiesOutputController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar?\nAs informações não salvas serão perdidas");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}
}
