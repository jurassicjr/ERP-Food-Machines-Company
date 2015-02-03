package product.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Material;
import model.Product;
import sales.controller.SalesController;
import util.ShowMessage;
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
					if (rsName.next()) {
						Material m = new Material();
						m.setName(rsName.getString("name"));
						m.setAmmount(rsName.getDouble("quantidade"));
						m.setId(rsName.getInt("id"));
						m.setNCM(rsName.getString("ncm"));
						m.setInternalCode(rsName.getString("internal_code"));
						table.setValueAt(m, row, 0);
					}
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
		String sqlUpdate = "UPDATE compost_product SET product = ?, description = ? WHERE id = ?";
		Object[] obj = new Object[] {product.getName(), product.getDescription(), product.getId()};
		dataBase.executeUpdate(sqlUpdate, obj);
	}
	public void removeProduct(Product product) {
		String invetorySql = "DELETE FROM kit_relationship WHERE product = ?";
		String materialRelationShipSql = "DELETE FROM material_relationship WHERE product = ?";
		String compostProductsql = "DELETE FROM compost_product WHERE id = ?";
		dataBase.executeUpdate(invetorySql, product.getId());
		dataBase.executeUpdate(materialRelationShipSql, product.getId());
		dataBase.executeUpdate(compostProductsql, product.getId());
	}

	public void addMaterial(Material material, JTable table, JSpinner spinnerAmount, Product product) {
	    DefaultTableModel tbl = (DefaultTableModel) table.getModel();
	    boolean hasItem = hasItem(material, table);
	    if(!hasItem && product != null) {
	    String sql = "INSERT INTO material_relationship(product, material, ammount) VALUES(?,?,?)";
	    dataBase.executeUpdate(sql, new Object[] {product.getId(), material.getId(), spinnerAmount.getValue()});
	    tbl.addRow(new Object[] {material, spinnerAmount.getValue()});
	    }else {
	    	ShowMessage.errorMessage(null, "Material", "Esse material já pertence a este produto");
	    }
    }

	private boolean hasItem(Material material, JTable table) {
		int rowCount = table.getRowCount();
		DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		for(int i = 0; i <rowCount; i++) {
			Material m = (Material) tblModel.getValueAt(i, 0);
			if(m.getName().equalsIgnoreCase(material.getName())) return true;
		}
	    return false;
    }

	public void deleteMaterial(Material material, Product product) {
	    String sql ="DELETE FROM material_relationship WHERE material = ? AND product = ?";
	    dataBase.executeUpdate(sql, new Object[] {material.getId(), product.getId()});
    }

}
