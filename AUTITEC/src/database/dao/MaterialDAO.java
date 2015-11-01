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
		String model = (String) data.get("model");
		String materialType = (String) data.get("materialType");
		double ammount = (double) data.get("ammount");
		insertData = new Object[] { descrition, name, intenalCode, ncm, ammount, model, materialType };
		String sql = "INSERT INTO Product(descricao, name, internal_code, ncm, quantidade, model, material_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
				material.setModel(rs.getString("model"));
				material.setMaterialType(rs.getString("material_type"));
				mList.add(material);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mList;
	}

	public Material getMaterialById(int materialID) {
		try {

			ResultSet rs = dataBase.executeQuery("SELECT *FROM Product WHERE id = ?", materialID);

			Material material = new Material();
			if (rs.next()) {
				material.setName(rs.getString("name"));
				material.setDescrition(rs.getString("descricao"));
				material.setId(rs.getInt("id"));
				material.setAmmount(rs.getInt("quantidade"));
				material.setModel(rs.getString("model"));
				material.setMaterialType(rs.getString("materialType"));
			}

			dataBase.close();
			return material;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Material> getMaterialAssociationWithProduct(int id) {
		List<Material> list = new ArrayList<Material>();
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM material_relationship WHERE product = ?", id)) {
			while (rs.next()) {
				Material m = new Material();
				m.setAuxAmmount(rs.getInt("ammount"));
				try (ResultSet rsName = dataBase.executeQuery("SELECT *From Product WHERE id = ?",
				        rs.getInt("material"))) {
					if (rsName.next()) {
						m.setName(rsName.getString("name"));
						m.setAmmount(rsName.getDouble("quantidade"));
						m.setId(rsName.getInt("id"));
						m.setNCM(rsName.getString("ncm"));
						m.setInternalCode(rsName.getString("internal_code"));
						list.add(m);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}