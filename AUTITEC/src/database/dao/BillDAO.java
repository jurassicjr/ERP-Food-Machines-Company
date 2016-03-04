package database.dao;

import java.sql.Date;

import database.DataBase;
import model.Bill;

public class BillDAO {
	
	private DataBase dataBase;
	
	public BillDAO() {
		
		dataBase = new DataBase();
		dataBase.connect();
		
	}
	
	public boolean persist(Bill bill) {
		
		Integer billSubGroup = bill.getSubGroup().getId();
		Integer billName = (bill.getBillName() == null) ? null : bill.getBillName().getId();
		Double value = bill.getValue();
		Date expirationDate = new Date(bill.getExpiration().getTime());
		String creditor = bill.getCreditor();
		Double entryValue = (bill.getEntryValue() > 0.0) ? bill.getEntryValue() : 0.0;
		String observation = bill.getObservation();
		Integer nInstallments = bill.getnInstallments();
		Integer user = bill.getUser().getId();
		Integer cnpj = bill.getCnpj().getId();
		Integer type = bill.getType();		
				
		String sql = "INSERT INTO bill (bill_subgroup, bill_name, value, expiration, creditor, entry_value, observation, n_installments, user, cnpj, type) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Object data[] = new Object[]{billSubGroup, billName, value, expirationDate, creditor, entryValue, observation, nInstallments, user, cnpj, type};
				
		return dataBase.executeUpdate(sql, data);
								
	}
	
}
