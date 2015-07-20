package rh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Employee;
import model.FunctionDescription;
import util.ShowMessage;
import core.CBO;
import database.dao.EmployeeDAO;
import database.dao.FunctionDescriptionDAO;

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

	public void register(FunctionDescription funDes) {
	    
		Employee e = funDes.getEmployee();
		CBO Function = funDes.getFunction();
		String functionAtribuition = funDes.getFunctionAtribuition();
		List<String> knowledgementList = funDes.getKnowledgementList();
		String minimalGraduation = funDes.getMinimalGraduation();
		int needExperiences = funDes.getNeedExperiences();
		String period = funDes.getPeriod();
		List<String> personalHabilitiesList = funDes.getPersonalHabilitiesList();
		String sector = funDes.getSector();
		
		Map<String, Object> map = new HashMap<String, Object>();
	    map.put("employee", e);
	    map.put("function", Function);
	    map.put("functionAtribuition", functionAtribuition);
	    map.put("knowledgementList", knowledgementList);
	    map.put("minimalGraduation", minimalGraduation);
	    map.put("needExperience", needExperiences);
	    map.put("period", period);
	    map.put("personalHabilitiesList", personalHabilitiesList);
	    map.put("sector", sector);
	    
	    new FunctionDescriptionDAO().register(map);
    }
}
