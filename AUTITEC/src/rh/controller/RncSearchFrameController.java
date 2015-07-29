package rh.controller;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTable;

import model.Employee;
import model.Rnc;
import userInterface.components.RncTableModel;
import database.DataBase;
import database.dao.RncDAO;

public class RncSearchFrameController
{
	DataBase dataBase;
	RncTableModel rncModel ;
	
	public RncSearchFrameController()
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	public void fillRncTable(JTable tableRnc)
	{
		try {
			ArrayList<Rnc> rncs = new RncDAO().getList();
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
