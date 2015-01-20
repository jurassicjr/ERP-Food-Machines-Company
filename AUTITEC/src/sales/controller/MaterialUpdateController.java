package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import model.Material;
import database.DataBase;

public class MaterialUpdateController extends SalesController{
	DataBase dataBase;
	
	public MaterialUpdateController() {
		dataBase = new DataBase();
		dataBase.connect();
    }
	
	/**
	 * Popula o JComboBox<Produto> com produtos do banco.
	 */
	
	public void fillProducts(JComboBox<Material> product) {
		product.removeAllItems();
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM Product")){
			while (rs.next()) {
				Material produto = new Material();
				produto.setName(rs.getString("name"));
				produto.setDescrition(rs.getString("descricao"));
				produto.setId(rs.getInt("id"));
				produto.setAmmount(rs.getInt("quantidade"));
				product.addItem(produto);
			}
			product.setSelectedIndex(-1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
