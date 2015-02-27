package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.DataBase;

public class SearchOfProductController extends SalesController {

	private DataBase dataBase;

	public SearchOfProductController() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void simpleSearch(JTable table, String name) {
		String sql = "SELECT *FROM compost_product WHERE name LIKE ?";
		name = name + "%";
		Object[] obj = new Object[] { name };
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		try (ResultSet rs = dataBase.executeQuery(sql, obj)) {
			while (rs.next()) {
				String n = rs.getString("name");
				String description = rs.getString("description");
				tbl.addRow(new Object[] { n, description });
			}
		} catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}
}
