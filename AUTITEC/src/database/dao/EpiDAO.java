package database.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.EPI;
import database.DataBase;

public class EpiDAO {

	private DataBase dataBase;

	public EpiDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(Map<String, Object> map) {
		String sql = "INSERT INTO epi(name, use_description, ammount) VALUES(?,?,?)";
		String name = (String) map.get("name");
		String useDescription = (String) map.get("useDescription");
		int ammount = 0;
		Object[] obj = new Object[]{name, useDescription, ammount};
		try{
			dataBase.executeUpdate(sql, obj);
			dataBase.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void update(Map<String, Object> map) {
		String sql = "UPDATE epi SET name = ?, use_description =? WHERE id = ?";
		String name = (String) map.get("name");
		String useDescription = (String) map.get("useDescription");
		int id = (int) map.get("id");
		Object[] obj = new Object[]{name, useDescription, id};
		try{
			dataBase.executeUpdate(sql, obj);
			dataBase.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	public void delete(EPI epi) {
		int id = epi.getId();
		String sql = "DELETE FROM epi WHERE id = ?";
		String entryOfEPISQL = "DELETE FROM entry_of epi WHERE epi = ?";
		String exitOfEPISQL = "DELETE FROM exit_of_epi WHERE epi = ?";
		try{
			dataBase.executeUpdate(entryOfEPISQL, id);
			dataBase.executeUpdate(exitOfEPISQL, id);
			dataBase.executeUpdate(sql, id);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public ArrayList<EPI> fillEPIs() {
		List<EPI> epiList = new java.util.ArrayList<EPI>();
		String sql = "SELECT *FROM epi";
		try(ResultSet rs = dataBase.executeQuery(sql)){
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String useDescription = rs.getString("use_description");
				int ammount = rs.getInt("ammount");
				EPI epi = new EPI();
				epi.setId(id);
				epi.setName(name);
				epi.setUseDescription(useDescription);
				epi.setAmmount(ammount);
				epiList.add(epi);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return (ArrayList<EPI>) epiList;
	}
}
