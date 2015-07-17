package rh.controller;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Employee;
import util.ShowMessage;
import database.dao.EmployeeDAO;

public class RegisterOfFunctionDescriptionController {

	
	private JFrame frame;

	public RegisterOfFunctionDescriptionController(JFrame frame) {
		this.frame = frame;
	}

	public void fillEmployeeCBO(JComboBox<Employee> cboReportTo) {
	    cboReportTo.removeAllItems();
		List<Employee> list = new EmployeeDAO().getEmployees();
	    list.forEach(e -> cboReportTo.addItem(e));
    }

	public void fillSectorComboBox(JComboBox<String> cboSector) {
	    cboSector.removeAllItems();
		cboSector.addItem("Administrativo");
	    cboSector.addItem("Recursos Humanos");
	    cboSector.addItem("Financeiro");
	    cboSector.addItem("Manutenção");
	    cboSector.addItem("Produção");
	    cboSector.addItem("Diretoria");
	    cboSector.addItem("Vendas");
	    cboSector.addItem("Compras");
	    cboSector.addItem("Marketing");
    }

	public void close() {
	    int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar a descrição de cargo?\nOs dados não salvos serão perdidos!");
	    if(i == JOptionPane.YES_OPTION) {
	    	frame.dispose();
	    }
    }
}
