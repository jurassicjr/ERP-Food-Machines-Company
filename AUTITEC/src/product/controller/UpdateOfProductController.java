package product.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Material;
import model.Product;
import sales.controller.SalesController;
import database.DataBase;

public class UpdateOfProductController extends SalesController {

	private DataBase dataBase;

	public UpdateOfProductController() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void fillMaterialTable(Product product, JTable table) {
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM material_relationship WHERE product = ?",
		        product.getId())) {
			while (rs.next()) {
				((DefaultTableModel) table.getModel()).addRow(new Object[] { null, null, null });
				int row = table.getRowCount() - 1;
				table.setValueAt(rs.getString("ammount"), row, 1);
				try (ResultSet rsName = dataBase.executeQuery("SELECT *From Product WHERE id = ?",
				        rs.getInt("material"))) {
					table.setValueAt(rsName.getString("name"), row, 0);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fillProduct(JComboBox<Product> cboProduct) {
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM compost_product")) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("product");
				String description = rs.getString("description");
				Product product = new Product(id, name, description);
				cboProduct.addItem(product);
			}
			cboProduct.setSelectedIndex(-1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void fillMaterial(JComboBox<Material> cbMaterial) {
		cbMaterial.removeAllItems();
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM Product")) {
			while (rs.next()) {
				Material material = new Material();
				material.setName(rs.getString("name"));
				material.setDescrition(rs.getString("descricao"));
				material.setId(rs.getInt("id"));
				material.setAmmount(rs.getInt("quantidade"));
				cbMaterial.addItem(material);
			}
			cbMaterial.setSelectedIndex(-1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateProduct(Product product, JTable table) {
	    
	    
    }

}
