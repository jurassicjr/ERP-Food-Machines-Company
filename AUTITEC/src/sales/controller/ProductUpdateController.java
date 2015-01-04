package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import model.Product;
import database.DataBase;

public class ProductUpdateController extends SalesController{
	DataBase dataBase;
	
	public ProductUpdateController() {
		dataBase = new DataBase();
		dataBase.connect();
    }
	
	/**
	 * Popula o JComboBox<Produto> com produtos do banco.
	 */
	
	public void fillProducts(JComboBox<Product> product) {
		product.removeAllItems();
		try {
			ResultSet rs = dataBase.executeQuery("SELECT *FROM Product");
			while (rs.next()) {
				Product produto = new Product();
				produto.setName(rs.getString("name"));
				produto.setDescrition(rs.getString("descricao"));
				produto.setId(rs.getInt("id"));
				produto.setAmmount(rs.getInt("quantidade"));
				product.addItem(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
