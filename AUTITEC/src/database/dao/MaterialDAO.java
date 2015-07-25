package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Material;
import util.ShowMessage;
import database.DataBase;

public class MaterialDAO {

	private DataBase dataBase;
	private Object[] insertData;

	public MaterialDAO(Map<String, Object> data) {
		this.dataBase = new DataBase();
		dataBase.connect();
		persist(data);
	}

	public MaterialDAO() {
		this.dataBase = new DataBase();
		dataBase.connect();
    }

	private void persist(Map<String, Object> data) {
		String descrition = (String) data.get("descricao");
		String name = (String) data.get("name");
		String intenalCode = (String) data.get("internalCode");
		String ncm = (String) data.get("ncm");
		double ammount = (double) data.get("ammount");
		insertData = new Object[] { descrition, name, intenalCode, ncm, ammount };
		String sql = "INSERT INTO Product(descricao, name, internal_code, ncm, quantidade) VALUES (?, ?, ?, ?, ?)";
		dataBase.executeUpdate(sql, insertData);
		ShowMessage.successMessage(null, "GRAVAÇÂO", "Gravação concluida com sucesso!");
	}

	public List<Material> getAllMaterials() {
		List<Material> mList = new ArrayList<Material>();
		try {

			ResultSet rs = dataBase.executeQuery("SELECT *FROM Product");

			while (rs.next()) {

				Material material = new Material();
				material.setName(rs.getString("name"));
				material.setDescrition(rs.getString("descricao"));
				material.setId(rs.getInt("id"));
				material.setAmmount(rs.getInt("quantidade"));
				mList.add(material);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mList;
	}
}