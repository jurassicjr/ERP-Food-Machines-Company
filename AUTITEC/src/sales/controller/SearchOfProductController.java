package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Product;
import model.Session;
import model.User;
import util.ClearFrame;
import database.DataBase;

public class SearchOfProductController extends SalesController {

	private DataBase dataBase;
	public SearchOfProductController() {
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	
	
	public void simpleSearch(JTable table, String name) {
		String sql = "SELECT *FROM compost_product WHERE product LIKE ?";
		name = name + "%";
		Object[] obj = new Object[] { name };
		ClearFrame.clearTable(table);
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		try (ResultSet rs = dataBase.executeQuery(sql, obj)) {
			while (rs.next()) {
				String n = rs.getString("product");
				String description = rs.getString("description");
				tbl.addRow(new Object[] { n, description });
			}
		} catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}
	public Product getRegister(Integer id) {
		
		String sql = "SELECT * FROM compost_product WHERE product = ?";

		try (ResultSet rs = dataBase.executeQuery(sql,id)) {
			 Product product = new Product();
			   
			if (rs.next()) {
				String prod = rs.getString("product");
				String description = rs.getString("description");
				Integer retId = rs.getInt("id");
				product.setId(retId);
				product.setName(prod);
				product.setDescription(description);
			}
		   return product;
		} catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        return new Product();
        }
	}
	public void queryAll(JTable table)
	{
		String sql = "SELECT * FROM compost_product";
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		ClearFrame.clearTable(table);
		try (ResultSet rs = dataBase.executeQuery(sql)) {
			while (rs.next()) {
				String n = rs.getString("product");
				String description = rs.getString("description");
				tbl.addRow(new Object[] { n, description });
			}
		} catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}
	@SuppressWarnings("finally")
	public Product getProductByName(String name)
	{
		
		String sql = "SELECT * FROM compost_product where product = ?";
		Product product = new Product();
		try (ResultSet rs = dataBase.executeQuery(sql,name)) {
			if (rs.next()) {
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("product"));
				product.setDescription(rs.getString("description"));
			}
			
		} catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		finally
		{
			return product;
		}
		
	}
}
