package database.dao;


import java.util.Date;
import java.util.Map;

import model.Material;
import database.DataBase;

public class RemoveOfEPIDAO {

	
	private DataBase dataBase;

	public RemoveOfEPIDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void remove(Map<String, Object> map) {
		String motive = (String) map.get("motive");
		Material epi = (Material) map.get("epi");
		int ammount = (int) map.get("ammount");
		Date date = (Date) map.get("date");
		java.sql.Date entryDate = new java.sql.Date(date.getTime());
		
		String registerSQL = "INSERT INTO exit_of_epi(epi, ammount, date, motive) VALUES (?,?,?,?)";
		String updateEPISQL = "UPDATE product SET quantidade = ? WHERE id = ?";
		
		Object[] registerObj = new Object[]{epi.getId(), ammount, entryDate, motive};
		Object[] updateEPIObj = new Object[]{epi.getAmmount() - ammount, epi.getId()};
		
		try{
			dataBase.executeUpdate(registerSQL, registerObj);
			dataBase.executeUpdate(updateEPISQL, updateEPIObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
