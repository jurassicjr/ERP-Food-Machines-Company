package database.dao;

import java.util.Map;

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
		String descricao = (String) data.get("descricao");
		int quantidade = (int) data.get("quantidade");
		String nome = (String) data.get("name");
		insertData = new Object[] {descricao, quantidade, nome};
		String sql = "INSERT INTO Product(descricao, quantidade, name) VALUES (?, ?, ?)";
		database.executeUpdate(sql, insertData);
	}
}
