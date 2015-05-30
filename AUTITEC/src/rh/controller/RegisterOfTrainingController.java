package rh.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Employee;
import model.Training;
import util.ShowMessage;
import database.dao.EmployeeDAO;
import database.dao.TrainingDAO;

public class RegisterOfTrainingController {

	private JFrame frame;

	public RegisterOfTrainingController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close(){
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar essa janela?\n As informações não salvas serão perdidas");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}
	
	public void fillEmployees(JComboBox<Employee> cboEmployee){
		List<Employee> list = new EmployeeDAO().getEmployees();
		list.forEach(e -> cboEmployee.addItem(e));
	}

	public void register(Training t) {
		Date date = t.getDate();
		String duration =t.getDuration();
		List<Employee> list = t.getEmployeeList();
		String eventType = t.getEventType();
		String motive = t.getMotive();
		String objective = t.getObjective();
		String period = t.getPeriod();
		String title = t.getTitle();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("date", date);
		map.put("duration", duration);
		map.put("list", list);
		map.put("eventType", eventType);
		map.put("motive", motive);
		map.put("objective", objective);
		map.put("period", period);
		map.put("title", title);
		
		new TrainingDAO().register(map);
	}
}
