package database.dao;

import java.sql.Date;

import model.Bill;
import database.DataBase;

public class BillDAO {
	
	private DataBase dataBase;
	
	public BillDAO(Bill bill) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
		persist(bill);
		
	}
	
	private void persist(Bill bill) {
		
		double value = bill.getValue();
		Date expirationDate = new Date(bill.getExpiration().getTime());
		Date payDate = (bill.getPayedDate() == null) ? null : new Date(bill.getPayedDate().getTime());
		String observation = bill.getObservation();
		String creditor = bill.getCreditor();
		Integer billName = (bill.getBillName() == null) ? null : bill.getBillName().getId();
		int billSubGroup = bill.getSubGroup().getId();
				
		String sql = "INSERT INTO bill (value, expiration, pay_date, observation, creditor, bill_name, bill_subgroup) VALUES (?, ?, ?, ?, ?, ?, ?);";
		Object data[] = new Object[]{value, expirationDate, payDate, observation, creditor, billName, billSubGroup};
				
		dataBase.executeUpdate(sql, data);
								
	}
	
}
