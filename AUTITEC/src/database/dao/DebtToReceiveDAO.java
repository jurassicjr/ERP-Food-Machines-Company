package database.dao;

import java.sql.Date;

import database.DataBase;
import model.DebtToReceive;

public class DebtToReceiveDAO {
	
	private DataBase dataBase;
	
	public DebtToReceiveDAO(DebtToReceive debt) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
		persist(debt);
		
	}
	
	public void persist(DebtToReceive d) {
		
		String debt = d.getDebt();
		String debtor = d.getDebtor();
		String observation = d.getObservation();
		Date date = new Date(d.getDate().getTime());
		double value = d.getValue();
		
		String sql = "INSERT INTO debt (debt, debtor, observation, date, paid, value) VALUES (?, ?, ?, ?, ?, ?)";
		Object data[] = new Object[]{debt, debtor, observation, date, 0, value};
		
		dataBase.executeUpdate(sql, data);
		
	}

}
