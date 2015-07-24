package maintenance.controller;

import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JComboBox;

import database.DataBase;
import database.dao.RncDAO;
import model.Employee;
import model.Rnc;

public class RncRegisterController 
{	
	private DataBase dataBase;
	public RncRegisterController() {
		dataBase = new DataBase();
		dataBase.connect();
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
	public String getSequenceNumber()
	{
			 int lastInsertedId = dataBase.getAutoIncrementValue("rnc");
			 Calendar calNow = Calendar.getInstance();
			 return String.valueOf(lastInsertedId)+" - "+calNow.get(Calendar.YEAR); 
	}
	public void insertRnc(Rnc rnc)
	{
		RncDAO dao = new RncDAO();
		dao.persist(rnc);
	}
	
}
