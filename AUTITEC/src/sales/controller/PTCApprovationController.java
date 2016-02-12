package sales.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.ApprovedPTC;
import model.Employee;
import model.PTC;
import sales.view.PTCApprovationFrame;
import util.ShowMessage;
import database.dao.EmployeeDAO;
import database.dao.PTCDAO;
import database.dao.UserDAO;

public class PTCApprovationController {

	
	private PTCApprovationFrame frame;

	public PTCApprovationController(PTCApprovationFrame frame) {
		this.frame = frame;
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar a aprovação de P.T.C?");
		if(i == JOptionPane.YES_OPTION) {
			frame.dispose();
		}
	}

	public void fillApprovedTable(JTable table) {
		List<PTC> list = new PTCDAO().getAllPTC();
		for (PTC p : list) {
	        if(p.getIsApproved() == 1) {
	        	((DefaultTableModel) table.getModel()).addRow(new Object[] { null, null, null});
	    		int row = table.getRowCount() - 1;
	    		table.setValueAt(p, row, 0);
	    		table.setValueAt(p.getRastreabilityCode(), row, 1);
	    		table.setValueAt(p.getCreationDate(), row, 2);
	        }
        }
		
    }

	public void fillOpenTable(JTable table) {
		List<PTC> list = new PTCDAO().getAllPTC();
		SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
		for (PTC p : list) {
	        if(p.getIsApproved() == 0) {
	        	((DefaultTableModel) table.getModel()).addRow(new Object[] { null, null, null});
	    		int row = table.getRowCount() - 1;
	    		table.setValueAt(p, row, 0);
	    		table.setValueAt(p.getRastreabilityCode(), row, 1);
	    		table.setValueAt(sdt.format(p.getCreationDate()), row, 2);
	        }
        }
    }
	
	public boolean verifyUser(Employee e, String pass) {
	    boolean isOk = new UserDAO().verify(e, pass);
		if(isOk)return true;
		else return false;
    }
	
	public void fillcboEmployee(JComboBox<Employee> cboApprovedBy) {
	    List<Employee> eList = new EmployeeDAO().getEmployees();
	    eList.forEach(e -> cboApprovedBy.addItem(e));
    }

	public void register(ApprovedPTC aPTC) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    
	    map.put("approvedBy", aPTC.getApprovedBy());
	    map.put("date", aPTC.getDate());
	    map.put("observation", aPTC.getObservation());
	    map.put("ptc", aPTC.getPtc());
	    
	    new PTCDAO().approve(map);
    }
}
