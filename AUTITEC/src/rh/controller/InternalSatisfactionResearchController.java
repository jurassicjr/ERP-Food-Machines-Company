package rh.controller;

import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Employee;
import model.InternalSatisfactionResearch;
import util.ShowMessage;
import database.dao.InternalSatisfactionResearchDAO;

public class InternalSatisfactionResearchController {

	
	private JFrame frame;

	public InternalSatisfactionResearchController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Encerrar", "Deseja realmente encerrar essa janela?\nAs informações não salvas serão perdidas");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}

	public InternalSatisfactionResearch verifyResearchShock(Employee e, Date date) {
	    InternalSatisfactionResearch iSR = new InternalSatisfactionResearchDAO().getResearch(e,date);
	    if(iSR == null)return null;
	    return iSR;
    }
	
	
}
