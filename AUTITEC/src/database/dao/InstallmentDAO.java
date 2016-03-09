package database.dao;

import java.sql.Date;
import java.util.List;

import database.DataBase;
import model.Bill;
import model.Installment;

public class InstallmentDAO {
	
	private DataBase dataBase;
	
	public InstallmentDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public boolean persist(List<Installment> installments, Bill bill) {
		
		boolean valid = true;
		
		for(Installment installment : installments) {
			
			installment.setBill(bill);
			
			String sql = "INSERT INTO installment (bill, date, value) "
					+ "VALUES (?, ?, ?);";
			
			Integer billId = bill.getId();
			Date date = new Date(installment.getDate().getTime());
			Double value = installment.getValue();
			
			Object data[] = new Object[]{billId, date, value};
			
			valid &= dataBase.executeUpdate(sql, data);
			
			if(!valid) break;
			
		}
		
		return valid;
	}

}
