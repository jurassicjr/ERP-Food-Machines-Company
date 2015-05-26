package database.dao;

import java.util.Date;
import java.util.Map;

import model.EPI;
import database.DataBase;

public class EntryOfEPIDAO {

	
	private DataBase dataBase;

	public EntryOfEPIDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(Map<String, Object> map) {
		String cnpj = (String) map.get("cnpj");
		EPI epi = (EPI) map.get("epi");
		int ammount = (int) map.get("ammount");
		Date date = (Date) map.get("date");
		java.sql.Date entryDate = new java.sql.Date(date.getTime());
		
		String entrySql = "INSERT INTO entry_of_epi(epi, ammount, entry_date, cnpj) VALUES(?,?,?,?)";
		String updateSQL = "UPDATE epi set ammount = ? WHERE id = ?";
		
		Object[] entryObject = new Object[]{epi.getId(), ammount, entryDate, cnpj};
		Object[] updateObject = new Object[]{epi.getAmmount() + ammount, epi.getId()};
		
		try {
			dataBase.executeUpdate(entrySql, entryObject);
			dataBase.executeUpdate(updateSQL, updateObject);
		} catch (Exception e) {
		
		}
	}
	
	
}
