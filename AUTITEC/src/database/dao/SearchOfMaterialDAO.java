package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Material;
import model.MeasureUnit;
import util.ClearFrame;
import database.DataBase;

public class SearchOfMaterialDAO {

	private DataBase dataBase;

	public SearchOfMaterialDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void simpleSearch(JTable table, String name, List<MeasureUnit> muList) {
		String sql = "SELECT *FROM Product WHERE name LIKE ?";
		name = name + "%";
		Object[] obj = new Object[] { name };
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		try (ResultSet rs = dataBase.executeQuery(sql, obj)) {
			while (rs.next()) {
				String n = rs.getString("name");
				double ammount = rs.getDouble("quantidade");
				MeasureUnit mu = muList.get(rs.getInt("measure_unit") - 1);
				tbl.addRow(new Object[] { n, ammount, mu });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dataBase.close();
	}

	public void queryAll(JTable table, List<MeasureUnit> muList) {
		String sql = "SELECT * FROM Product ORDER BY NAME";
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		ClearFrame.clearTable(table);
		try (ResultSet rs = dataBase.executeQuery(sql)) {
			while (rs.next()) {
				String n = rs.getString("name");
				double ammount = rs.getDouble("quantidade");
				MeasureUnit mu = muList.get(rs.getInt("measure_unit") - 1);
				tbl.addRow(new Object[] { n, ammount, mu });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dataBase.close();
	}

	public Material getMaterialByName(String materialName) {
		String sql = "SELECT * FROM Product where name = ?";
		Material material = new Material();
		try {
			ResultSet rs = dataBase.executeQuery(sql, materialName);
			if (rs.next()) {
				material.setId(rs.getInt("id"));
				material.setName(rs.getString("name"));
				material.setDescrition(rs.getString("descricao"));
				material.setAmmount(rs.getInt("quantidade"));
				material.setInternalCode(rs.getString("internal_code"));
				material.setNCM(rs.getString("ncm"));
				material.setMaterialType(rs.getInt("material_type"));
				material.setHeigth(rs.getDouble("z"));
				material.setLength(rs.getDouble("y"));
				material.setMeasureUnit(rs.getInt("measure_unit"));
				material.setModel(rs.getInt("model"));
				material.setWidth(rs.getDouble("x"));
			}
			dataBase.close();
			return material;
		} catch (SQLException e) {
			e.printStackTrace();
			dataBase.close();
			return new Material();
		}
	}

	public void search(JTable table, int max, int min, List<MeasureUnit> muList) {
		String sql = "SELECT *FROM Product WHERE quantidade > ? AND quantidade<?";
		Object[] obj = new Object[] { min, max };
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		try (ResultSet rs = dataBase.executeQuery(sql, obj)) {
			while (rs.next()) {
				String n = rs.getString("name");
				double ammount = rs.getDouble("quantidade");
				MeasureUnit mu = muList.get(rs.getInt("measure_unit") - 1);
				tbl.addRow(new Object[] { n, ammount, mu });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void search(JTable table, Integer max, Integer min, String name, int materialModel, int materialType,
	        int measureUnit, List<MeasureUnit> muList) {
		int parameterCount = 0;

		List<Object> obj = new ArrayList<Object>();
		String sql = "SELECT * FROM Product WHERE ";

		if (name != null && !name.isEmpty()) {
			sql += " name LIKE ? ";
			name = name + "%";
			obj.add(name);
			parameterCount++;
		}
		if (max >= 1 && name != null && name.isEmpty()) {
			if (parameterCount > 0)
				sql += " AND ";

			sql += " quantidade <= ?";
			obj.add(max);
			parameterCount++;
		}
		if (min >= 1) {
			if (parameterCount > 0)
				sql += " AND ";

			sql += " quantidade >= ? ";
			obj.add(min);
			parameterCount++;

		}
		if (measureUnit != 0) {
			if (parameterCount > 0)
				sql += " AND ";
			sql += " measure_unit = ? ";
			obj.add(measureUnit);
			parameterCount++;
		}
		if (materialModel != 0) {
			if (parameterCount > 0)
				sql += " AND ";
			sql += " model = ? ";
			obj.add(materialModel);
			parameterCount++;
		}
		if (materialType != 0) {
			if (parameterCount > 0)
				sql += " AND ";
			sql += " material_type = ? ";
			obj.add(materialType);
			parameterCount++;
		}
		if (parameterCount == 0)
			sql = sql.replace("WHERE", "");

		sql += " order by name";

		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		ClearFrame.clearTable(table);
		try (ResultSet rs = dataBase.executeQuery(sql, obj)) {
			while (rs.next()) {
				String n = rs.getString("name");
				double ammount = rs.getDouble("quantidade");
				MeasureUnit mu = muList.get(rs.getInt("measure_unit") - 1);
				tbl.addRow(new Object[] { n, ammount, mu });
			}
			dataBase.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dataBase.close();
	}
}
