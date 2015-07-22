package rh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.AssessmentOfCompetence;
import model.CBO;
import model.Employee;
import model.FunctionDescription;
import model.Training;
import util.ShowMessage;
import database.dao.AssessmentOfCompetenceDAO;
import database.dao.EmployeeDAO;
import database.dao.FunctionDescriptionDAO;
import database.dao.TrainingDAO;

public class AssessmentOfCompetenceController {

	private JFrame frame;

	public AssessmentOfCompetenceController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar a avaliação de competência?\nOs dados não salvos serão perdidos!");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}

	public FunctionDescription getFunctionDescription(CBO cbo) {
	    FunctionDescription funDes = new FunctionDescriptionDAO().getFunctionDescriptionByCBO(cbo);
		return funDes;
    }

	public void fillEmployee(JComboBox<Employee> cboEmployee) {
	    List<Employee> eList =new EmployeeDAO().getEmployees();
	    eList.forEach(e -> cboEmployee.addItem(e));
    }

	public List<Training> getTraining(int id) {
	    List<Training> tList = new TrainingDAO().getTrainingsListByEmployee(id);
		return tList;
    }

	public void register(AssessmentOfCompetence assOfCom) {
	    List<ButtonGroup> btnGroupList = assOfCom.getBtnGroupList();
	    Employee e = assOfCom.getEmployee();
	    String experience = assOfCom.getExperience();
	    FunctionDescription funDes = assOfCom.getFunDes();
	    List<String> funtions = assOfCom.getFuntion();
	    String point = assOfCom.getPoint();
	    boolean isEnable = assOfCom.isEnable();
	    
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("btnGroupList", btnGroupList);
	    map.put("employee", e);
	    map.put("experience", experience);
	    map.put("funDes", funDes);
	    map.put("functions", funtions);
	    map.put("points", point);
	    map.put("isEnable", isEnable);
	    
	    new AssessmentOfCompetenceDAO().register(map);
    }
}
