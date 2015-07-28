package rh.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Employee;
import model.MonitoringImplementationRNC;
import model.Rnc;
import util.ShowMessage;
import database.dao.EmployeeDAO;
import database.dao.MonitoringImplementationDAO;
import database.dao.RncDAO;
import database.dao.UserDAO;

public class MonitoringImplementationController {
	
	private JFrame frame;

	public MonitoringImplementationController(JFrame frame) {
		this.frame = frame;
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Fechar deseja realmente fechar o monitoramento de implementação ?\nAs informações não salva serão perdidas!");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}

	public void fillcboNonCompliance(JComboBox<Rnc> cboNonCompliance) {
	    List<Rnc> rncList = new RncDAO().getAllRncNotActive();
	    rncList.forEach(r -> cboNonCompliance.addItem(r));
    }

	public void fillcboEmployee(JComboBox<Employee> cboApprovedBy) {
	    List<Employee> eList = new EmployeeDAO().getEmployees();
	    eList.forEach(e -> cboApprovedBy.addItem(e));
    }

	public void register(MonitoringImplementationRNC mIRNC) {
	    Employee approvedBy = mIRNC.getApprovedBy();
	    Date monitoringDate = mIRNC.getMonitoringDate();
	    String monitoringDescription = mIRNC.getMonitoringDescription();
	    Rnc rnc = mIRNC.getNonCompliance(); 
	    
	    Map<String, Object> map = new HashMap<String, Object>();
	    
	    map.put("approvedBy", approvedBy);
	    map.put("monitoringDate", monitoringDate);
	    map.put("monitoringDescription", monitoringDescription);
	    map.put("rnc", rnc);
	    
	    new MonitoringImplementationDAO().register(map);
    }

	public boolean verifyUser(Employee e, String pass) {
	    boolean isOk = new UserDAO().verify(e, pass);
		if(isOk)return true;
		else return false;
    }

}
