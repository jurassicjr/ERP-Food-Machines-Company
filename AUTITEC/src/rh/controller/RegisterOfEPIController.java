package rh.controller;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import database.dao.EpiDAO;
import model.EPI;
import util.ShowMessage;

public class RegisterOfEPIController {

	private JFrame frame;

	public RegisterOfEPIController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close(){
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar essa janela?\n As informções não salvas serão perdidas");
		if(i == JOptionPane.YES_OPTION){
			frame.dispose();
		}
	}

	public void register(EPI epi) {
		String name = epi.getName();
		String useDescription = epi.getUseDescription();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("useDescription", useDescription);
		new EpiDAO().register(map);
	}
}
