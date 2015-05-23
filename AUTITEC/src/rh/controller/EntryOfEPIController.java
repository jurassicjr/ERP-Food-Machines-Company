package rh.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import util.ShowMessage;

public class EntryOfEPIController {

	private JFrame frame;

	public EntryOfEPIController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close(){
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente Sair?\n As informações não salvas serão perdidas");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}
}
