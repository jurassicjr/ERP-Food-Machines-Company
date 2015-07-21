package rh.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import util.ShowMessage;

public class AssessmentOfCompetenceController {

	private JFrame frame;

	public AssessmentOfCompetenceController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar a avaliação de competência?\nOs dados não salvos serão perdidos!");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}
}
