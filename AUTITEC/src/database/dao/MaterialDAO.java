package database.dao;

import java.util.Map;

import util.ShowMessage;
import database.DataBase;

public class MaterialDAO {

	private DataBase database;
	private Object[] insertData;

	public MaterialDAO(Map<String, Object> data) {
		this.database = new DataBase();
		database.connect();
		persist(data);
	}
	private void persist(Map<String, Object> data) {
		String descrition = (String) data.get("descricao");
		String name = (String) data.get("name");
		String intenalCode = (String) data.get("internalCode");
		String ncm = (String) data.get("ncm");
		double ammount = (double) data.get("ammount");
		insertData = new Object[] {descrition, name, intenalCode, ncm, ammount};
		String sql = "INSERT INTO Product(descricao, name, internal_code, ncm, quantidade) VALUES (?, ?, ?, ?, ?)";
		database.executeUpdate(sql, insertData);
		ShowMessage.successMessage(null, "GRAVAÇÂO", "Gravação concluida com sucesso!");
	}
}