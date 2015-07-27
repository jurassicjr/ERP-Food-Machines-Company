package database.dao;

import java.sql.SQLException;
import java.util.Map;

import model.Brand;
import database.DataBase;

public class BrandDAO {

	private DataBase dataBase;

	public BrandDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	// public void persistBrand(Map<String,Object>map)
	// {
	// String name = (String) map.get("name");
	// Object[] persist = new Object[] {name};
	// String sql = "INSERT INTO BRAND (name) values (?)";
	// dataBase.executeQuery(sql,persist);
	//
	// }
	public boolean persisteBrand(Brand brand) {
		try {
			Object obj[] = { brand.getName()

			};
			String sql = "INSERT INTO BRAND (name) values (?)";
			dataBase.executeUpdate(sql, obj);
			dataBase.close();
			return true;
		} catch (Exception ex) {
			return false;
		}

	}
}
