package database.dao;

import java.sql.Date;
import java.util.ArrayList;

import model.Bill;
import model.Installment;
import database.DataBase;

public class BillDAO {
	
	private DataBase dataBase;
	
	public BillDAO(Bill bill) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
		persist(bill);
		
	}
	
	private void persist(Bill bill) {
		
		String sql = "INSERT INTO bill (bill, creditor, observation) VALUES (?, ?, ?);";
		Object data[] = new Object[]{bill.getBill(), bill.getCreditor(), bill.getObservation()};
		
		int billId = dataBase.getAutoIncrementValue("bill");
		
		dataBase.executeUpdate(sql, data);
		
		persistInstallments(bill.getInstallments(), billId);
						
	}
	
	private void persistInstallments(ArrayList<Installment> installments, int billId) {
				
		for(Installment installment : installments) {
			
			Date date = new Date(installment.getDate().getTime());
			int paid = (installment.isPaid()) ? 1 : 0;
			
			String sql = "INSERT INTO installment (date, paid, bill) VALUES(?, ?, ?);";
			Object data[] = new Object[]{date, paid, billId};
			
			dataBase.executeUpdate(sql, data);
		}
		
	}

}
