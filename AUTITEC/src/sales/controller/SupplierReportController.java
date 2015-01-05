package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.DataBase;

public class SupplierReportController extends SalesController {
	private DataBase dataBase;
	
	public SupplierReportController() {
	    dataBase = new DataBase();
	    dataBase.connect();
    }
	
	public void fillTable(JTable table) {
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		String sql = "SELECT *FROM suppliers";
		ResultSet rs = dataBase.executeQuery(sql);
		try {
	        while(rs.next()) {
	        	String companyName = rs.getString("corporate_name");
	        	String CNPJ = rs.getString("CNPJ");
	        	String stateRegistration = rs.getString("state_registration");
	        	String materialCertification;
	        	String certificate;
	        	if(rs.getBoolean("material_certificate")) {
	        		materialCertification = "Sim";
	        	}else {
	        		materialCertification = "Não";
	        	}
	        	if(rs.getBoolean("certificate")) {
	        		certificate = "Sim";
	        	}else {
	        		certificate = "Não";
	        	}
	        	tbl.addRow(new String[] {companyName, CNPJ , stateRegistration, materialCertification, certificate});
	        }
        } catch (SQLException e) {
	        e.printStackTrace();
        }
	}

}
