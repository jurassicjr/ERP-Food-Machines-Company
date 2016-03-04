package database.dao;

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
			
			Object data[] = new Object[]{(Integer) installment.getBill().getId(), installment.getDate(), (Double) installment.getValue()};
			
			valid &= dataBase.executeUpdate(sql, data);
			
		}
		
		return valid;
	}

}
