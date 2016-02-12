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
		int model = (int) data.get("model");
		int materialType = (int) data.get("materialType");
		int measureUnit = (int) data.get("measureUnit");
		double x = (double) data.get("x");
		double y = (double) data.get("y");
		double z = (double) data.get("z");
		insertData = new Object[] { descrition, name, intenalCode, ncm, model, materialType, measureUnit, x, y, z };
		String sql = "INSERT INTO Product(descricao, name, internal_code, ncm, model, material_type, measure_unit, x, y, z) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
				material.setModel(rs.getInt("model"));
				material.setMaterialType(rs.getInt("material_type"));
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
				material.setModel(rs.getInt("model"));
				material.setMaterialType(rs.getInt("material_type"));
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

	public ArrayList<Material> getAllEpisMaterials() {
		List<Material> mList = new ArrayList<Material>();
		try {

			ResultSet rs = dataBase.executeQuery("SELECT *FROM Product where material_type = 1");

			while (rs.next()) {

				Material material = new Material();
				material.setName(rs.getString("name"));
				material.setDescrition(rs.getString("descricao"));
				material.setId(rs.getInt("id"));
				material.setModel(rs.getInt("model"));
				material.setMaterialType(rs.getInt("material_type"));
				material.setAmmount(rs.getDouble("quantidade"));
				mList.add(material);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (ArrayList<Material>) mList;
    }
}