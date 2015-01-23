package database.dao;

import java.sql.ResultSet;
import java.util.Map;

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
		Object[] persist = new Object[] {material, supplier, cnpj, fiscalNote, ammount};
		String sql = "INSERT INTO inventory(material, supplier, cnpj, fiscal_note, ammount) VALUES(?,?,?,?,?)";
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
}
