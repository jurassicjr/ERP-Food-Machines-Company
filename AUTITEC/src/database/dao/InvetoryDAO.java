package database.dao;

import java.util.Map;

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
		String sql = "INSERT INTO inventory(material, supplier, cnpj, fiscalNote, ammount) VALUES(?,?,?,?,?)";
		dataBase.executeUpdate(sql, persist);
	}
}
