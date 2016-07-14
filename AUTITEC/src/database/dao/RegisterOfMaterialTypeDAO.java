package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MaterialType;
import database.DataBase;

public class RegisterOfMaterialTypeDAO {

	private DataBase dataBase;

	public RegisterOfMaterialTypeDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(String name) {
		String query = "INSERT INTO type_mat(name) VALUES(?)";
		dataBase.executeUpdate(query, name);
	}

	public List<MaterialType> getTypes() {
		List<MaterialType> list = new ArrayList<MaterialType>();
		String query = "select * from type_mat ORDER BY name";
		try (ResultSet rs = dataBase.executeQuery(query)) {
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				MaterialType type = new MaterialType(name, id);
				list.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
