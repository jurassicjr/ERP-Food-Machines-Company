package rh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.EPI;
import model.Employee;
import model.EntryOfEPI;
import util.ShowMessage;
import database.dao.EmployeeDAO;
import database.dao.EntryOfEPIDAO;
import database.dao.EpiDAO;

public class EntryOfEPIController {

	private JFrame frame;

	public EntryOfEPIController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close(){
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente Sair?\n As informações não salvas serão perdidas");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}

	public void register(EntryOfEPI eoe) {
		Map<String, Object> map = new HashMap<String, Object>();
		String cnpj = eoe.getCnpj();
		EPI epi = eoe.getEpi();
		Date date = eoe.getDate();
		int ammount = eoe.getAmmount();
		map.put("cnpj", cnpj);
		map.put("epi", epi);
		map.put("ammount", ammount);
		map.put("date", date);
		new EntryOfEPIDAO().register(map);
	}

	public void fillEPI(JComboBox<EPI> cboEPI) {
		ArrayList<EPI> list = new EpiDAO().fillEPIs();
		list.forEach(e -> cboEPI.addItem(e));
	}

}
