package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Inventory;
import model.Material;
import util.ShowMessage;
import database.DataBase;

public class InvetoryDAO {

	private DataBase dataBase;

	public InvetoryDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void persist(Map<String, Object> map) {
		int material = (int) map.get("material");
		int supplier = (int) map.get("supplier");
		int cnpj = (int) map.get("cnpj");
		String fiscalNote = (String) map.get("fiscalNote");
		int ammount = (int) map.get("ammount");
		double icms = (double) map.get("icms");
		double pis = (double) map.get("pis");
		double noteValue = (double) map.get("noteValue");
		double entryValue = (double) map.get("entryValue");
		
		Object[] persist = new Object[] {material, supplier, cnpj, fiscalNote, ammount, icms, pis, noteValue, entryValue};
		String sql = "INSERT INTO inventory(material, supplier, cnpj, fiscal_note, ammount, icms, pis, note_value, entry_value) VALUES(?,?,?,?,?,?,?,?,?)";
		try{
			dataBase.executeUpdate(sql, persist);
			try(ResultSet rs = dataBase.executeQuery("SELECT *FROM Product WHERE id = ?", material)){
				if(rs.next()) {
				int quantidade = rs.getInt("quantidade");
				ammount = ammount + quantidade;
				}
			}
			Object[] obj = new Object[] {ammount, material};
			dataBase.executeUpdate("UPDATE Product SET quantidade = ? WHERE id = ?", obj);
			ShowMessage.successMessage(null, "Sucesso!", "inserção no estoque com sucesso!");
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public Inventory getInventory(int id) {
		
	    String sql = "SELECT *FROM inventory WHERE material = ?";
	    try(ResultSet rs = dataBase.executeQuery(sql, id)){
	    	if(rs.next()) {
	    		Inventory i = new Inventory();
	    		i.setAmmount(rs.getInt("ammount"));
	    		i.setCofins(rs.getDouble("cofins"));
	    		String query = "SELECT MAX(entry_value) from inventory WHERE material = ?";
	    		try(ResultSet rs2 = dataBase.executeQuery(query, id)){
	    			if(rs2.next()) {
	    				i.setEntryValue(rs2.getDouble(1));
	    			}
	    		}
	    		i.setFiscalNote(rs.getString("fiscal_note"));
	    		i.setIcms(rs.getDouble("icms"));
	    		i.setId(rs.getInt("id"));
	    		i.setIr(rs.getDouble("ir"));
	    		i.setNoteValue(rs.getDouble("note_value"));
	    		i.setPis(rs.getDouble("pis"));
	    		return i;
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return null;
    }

	public List<Inventory> getInventoryList(List<Material> materialList) {
	    List<Inventory> list = new ArrayList<Inventory>();
	    String sql = "SELECT *FROM inventory WHERE material = ?";
	    for (Material m : materialList) {
	  	    try(ResultSet rs = dataBase.executeQuery(sql, m.getId())){
	  	    	if(rs.next()) {
	  	    		Inventory i = new Inventory();
	  	    		i.setAmmount(rs.getInt("ammount"));
	  	    		i.setCofins(rs.getDouble("cofins"));
	  	    		String query = "SELECT MAX(entry_value) from inventory WHERE material = ?";
	  	    		try(ResultSet rs2 = dataBase.executeQuery(query, m.getId())){
	  	    			if(rs2.next()) {
	  	    				i.setEntryValue(rs2.getDouble(1)*m.getAuxAmmount());
	  	    			}else {
	  	    				i.setEntryValue(0.00);
	  	    			}
	  	    		}
	  	    		i.setFiscalNote(rs.getString("fiscal_note"));
	  	    		i.setIcms(rs.getDouble("icms"));
	  	    		i.setId(rs.getInt("id"));
	  	    		i.setIr(rs.getDouble("ir"));
	  	    		i.setNoteValue(rs.getDouble("note_value"));
	  	    		i.setPis(rs.getDouble("pis"));
	  	    		list.add(i);
	  	    	}
	  	    } catch (SQLException e) {
	  	        e.printStackTrace();
	          }
        }
		return list;
    }
}
