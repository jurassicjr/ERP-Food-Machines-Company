package rh.controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;

import database.DataBase;
import database.dao.RncDAO;
import model.Employee;
import model.Rnc;
import userInterface.components.RncTableModel;

public class RncSearchFrameController
{
	DataBase dataBase;
	RncTableModel rncModel ;
	
	public RncSearchFrameController()
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	public void fillRncTable(JTable tableRnc,Boolean showInactives)
	{
		try {
			ArrayList<Rnc> rncs = new RncDAO().getList(showInactives);
			if(!rncs.isEmpty())
				rncModel = new RncTableModel(rncs);
			else
				rncModel = new RncTableModel();
			
			
			tableRnc.setModel(rncModel);
			rncModel.fireTableDataChanged();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void fillRncTableBySearch(JTable tableRnc,Employee emitter
			, Date initialDate,Date finalDate,Boolean showInactives)
	{
		try {
			ArrayList<Rnc> rncs = new RncDAO().getListByArguments(emitter,
					initialDate, finalDate, showInactives);
			
			if(!rncs.isEmpty())
				rncModel = new RncTableModel(rncs);
			else
				rncModel = new RncTableModel();
			
			
			tableRnc.setModel(rncModel);
			rncModel.fireTableDataChanged();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void fillEmployeeCbo(JComboBox<Employee> cboEmployee)
	{
		 String sql = "SELECT id,name FROM employee order by name";
		 cboEmployee.removeAllItems();
		 
		 try{
			 ResultSet rs = dataBase.executeQuery(sql);
				while(rs.next()) {
					
					Employee employee  = new Employee();
					employee.setId(rs.getInt("id"));
					employee.setName(rs.getString("name"));
					cboEmployee.addItem(employee);
			}
		 }catch(Exception ex)
		 {
			 
		 }
	}
}
