package rh.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import database.dao.EpiDAO;
import model.EPI;
import util.ShowMessage;

public class UpdateOfEPiController {

	
	private JFrame frame;

	public UpdateOfEPiController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close(){
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar essa janelas?\n as informações não salvas serão perdidas!");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}

	public void update(EPI epi) {
		String name = epi.getName();
		String useDescription = epi.getUseDescription();
		int id = epi.getId();
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("name", name);
		map.put("useDescription", useDescription);
		map.put("id", id);
		new EpiDAO().update(map);
	}

	public void delete(EPI epi) {
		new EpiDAO().delete(epi);
	}

	public void fillEPIs(JComboBox<EPI> cboEPI) {
		ArrayList<EPI> list = new EpiDAO().fillEPIs();
		list.forEach(e -> cboEPI.addItem(e));
	}
}
