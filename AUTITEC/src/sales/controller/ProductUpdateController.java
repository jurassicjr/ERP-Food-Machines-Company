package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import model.Material;
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
	
	public void fillProducts(JComboBox<Material> product) {
		product.removeAllItems();
		try {
			ResultSet rs = dataBase.executeQuery("SELECT *FROM Product");
			while (rs.next()) {
				Material Material = new Material();
				Material.setName(rs.getString("name"));
				Material.setDescrition(rs.getString("descricao"));
				Material.setId(rs.getInt("id"));
				Material.setAmmount(rs.getInt("quantidade"));
				product.addItem(Material);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
