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
import model.RemoveOfEPI;
import util.ShowMessage;
import database.dao.EmployeeDAO;
import database.dao.EpiDAO;
import database.dao.RemoveOfEPIDAO;

public class RemoveOfEPIController {

	private JFrame frame;

	public RemoveOfEPIController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close(){
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar essa janela?\n Os dados não salvos serão perdidos");	
		if(i==JOptionPane.YES_OPTION)frame.dispose();
	}
	
	public void fillEPI(JComboBox<EPI> cboEPI) {
		ArrayList<EPI> list = new EpiDAO().fillEPIs();
		list.forEach(e -> cboEPI.addItem(e));
	}
	
	
	public void fillEmployees(JComboBox<Employee> cboEmployee){
		List<Employee> list = new EmployeeDAO().getEmployees();
		list.forEach(e -> cboEmployee.addItem(e));
	}

	public void removeEPI(RemoveOfEPI rEPI) {
		Map<String, Object> map = new HashMap<String, Object>();
		EPI epi = rEPI.getEpi();
		int ammount = rEPI.getAmmount();
		String motive = rEPI.getMotive();
		Date date = rEPI.getDate();
		map.put("epi", epi);
		map.put("ammount", ammount);
		map.put("motive", motive);
		map.put("date", date);
		new RemoveOfEPIDAO().remove(map);
	}
}
