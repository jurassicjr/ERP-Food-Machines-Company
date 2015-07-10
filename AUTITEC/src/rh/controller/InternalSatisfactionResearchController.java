package rh.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Employee;
import model.InternalSatisfactionResearch;
import util.ShowMessage;
import database.dao.EmployeeDAO;
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

	public void fillEmployee(JComboBox<Employee> cboEmployee) {
	    List<Employee> employeeList = new EmployeeDAO().getEmployees();
	    employeeList.forEach(e -> cboEmployee.addItem(e));
    }

	public void register(InternalSatisfactionResearch isr) {

		int tooling = isr.getTooling();
		int workingArea = isr.getWorkingArea();
		int uniform = isr.getUniform();
		int relationGroup = isr.getRelationGroup();
		int comunicatioFacility = isr.getComunicationFacility();
		int epi = isr.getEPIs();
		int howYouAvaibleTheFormer = isr.getFormers();
		int internalLighting = isr.getIndustryIntenalLighting();
		int functionPerformed = isr.getFunctionPerfomed();
		int wc = isr.getWCs();
		Date date = isr.getDate();
		Employee e = isr.getEmployee();
		String criticismsAndSuggetions = isr.getCriticismsAndSuggestions();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tooling", tooling);
		map.put("workingArea", workingArea);
		map.put("uniform", uniform);
		map.put("relationGroup", relationGroup);
		map.put("comunicationFacility", comunicatioFacility);
		map.put("epi", epi);
		map.put("howYouAvaiableTheFormer", howYouAvaibleTheFormer);
		map.put("internalLighting", internalLighting);
		map.put("functionPerformed", functionPerformed);
		map.put("wc", wc);
		map.put("date", date);
		map.put("employee", e);
		map.put("criticismsAndSuggestions", criticismsAndSuggetions);
		
		new InternalSatisfactionResearchDAO().register(map);
		
    }

	
}
