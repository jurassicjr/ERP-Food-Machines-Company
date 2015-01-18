package database.dao;

import java.util.Map;

import util.ShowMessage;
import database.DataBase;

public class ProductDAO {

	private DataBase database;
	private Object[] insertData;

	public ProductDAO(Map<String, Object> data) {
		this.database = new DataBase();
		database.connect();
		persist(data);
	}
	private void persist(Map<String, Object> data) {
		String descrition = (String) data.get("descricao");
		String name = (String) data.get("name");
		insertData = new Object[] {descrition, name};
		String sql = "INSERT INTO Product(descricao, name) VALUES (?, ?)";
		database.executeUpdate(sql, insertData);
		ShowMessage.successMessage(null, "GRAVAÇÂO", "Gravação concluida com sucesso!");
	}
}
