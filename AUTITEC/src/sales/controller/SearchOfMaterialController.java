package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.DataBase;

public class SearchOfMaterialController extends SalesController{

	private DataBase dataBase;

	public SearchOfMaterialController() {
	   dataBase = new DataBase();
	   dataBase.connect();
    }

	public void fullSearch(JTable table, int max, int min, String name) {
	   String sql = "SELECT *FROM Product WHERE name LIKE ? AND quantidade < ? AND quantidade > ?";
	   name = name +"%";
	   Object[] obj = new Object[] {name, max, min};
	   DefaultTableModel tbl = (DefaultTableModel) table.getModel();
	   try(ResultSet rs = dataBase.executeQuery(sql, obj)){
		   while(rs.next()) {
			   String n = rs.getString("name");
			   double ammount = rs.getDouble("quantidade");
			   tbl.addRow(new Object[]{n, ammount});
		   }
	   } catch (SQLException e) {
	    e.printStackTrace();
    }
			   
    }

	public void search(JTable table, int max, int min) {
		String sql = "SELECT *FROM Product WHERE quantidade > ? AND quantidade<?";
		Object[] obj = new Object[] {min, max};
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		try(ResultSet rs = dataBase.executeQuery(sql, obj)){
			while(rs.next()) {
				String n = rs.getString("name");
				double ammount = rs.getDouble("quantidade");
				tbl.addRow(new Object[] {n, ammount});
			}
		} catch (SQLException e) {
	        e.printStackTrace();
        }
    }

	public void simpleSearch(JTable table, String name) {
	    String sql = "SELECT *FROM Product WHERE name LIKE ?";
	    name = name+"%";
		Object[] obj = new Object[] {name};
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		try(ResultSet rs = dataBase.executeQuery(sql, obj)){
			while(rs.next()) {
				String n = rs.getString("name");
				double ammount = rs.getDouble("quantidade");
				tbl.addRow(new Object[] {n, ammount});
			}
		} catch (SQLException e) {
	        e.printStackTrace();
        }
    }
}
