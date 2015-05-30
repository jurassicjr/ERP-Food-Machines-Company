package rh.controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Employee;
import database.dao.EmployeeDAO;
import util.ShowMessage;

public class UpdateOfTrainingController {

	private JFrame frame;

	public UpdateOfTrainingController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close(){
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar essa janela?\n Os Dados não salvos serão perdidos");
		
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}

	public void fillEmployees(JComboBox<Employee> cboEmployee){
		List<Employee> list = new EmployeeDAO().getEmployees();
		list.forEach(e -> cboEmployee.addItem(e));
	}
}