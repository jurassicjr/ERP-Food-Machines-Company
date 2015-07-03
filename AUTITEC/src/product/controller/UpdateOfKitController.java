package product.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Kit;
import model.Product;
import sales.controller.SalesController;
import util.ShowMessage;
import database.DataBase;

public class UpdateOfKitController extends SalesController {

	private DataBase dataBase;

	public UpdateOfKitController() {
		dataBase = new DataBase();
		dataBase.connect();
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

	public void fillKit(JComboBox<Kit> cboKit) {
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM kit")) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("kit_name");
				String description = rs.getString("descrition");
				Kit kit = new Kit(id, name, description);
				cboKit.addItem(kit);
			}
			cboKit.setSelectedIndex(-1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeKit(Kit kit) {
		String invetorySql = "DELETE FROM kit_relationship WHERE kit = ?";
		String compostProductsql = "DELETE FROM kit WHERE id = ?";
		dataBase.executeUpdate(invetorySql, kit.getId());
		dataBase.executeUpdate(compostProductsql, kit.getId());	
	}

	public void update(Kit kit, JTable table) {
		String sqlUpdate = "UPDATE kit SET kit_name =?, descrition = ? WHERE id = ?";
		Object[] obj = new Object[] {kit.getName(), kit.getDescription(), kit.getId()};
		dataBase.executeUpdate(sqlUpdate, obj);	
		sqlUpdate = "delete from kit_relationship WHERE kit = ?";
		dataBase.executeUpdate(sqlUpdate,kit.getId());	
		
		for(int i = 0;i<table.getRowCount();i++)
		{
			String productName = table.getModel().getValueAt(i,0).toString();
			Product product = getProductByName(productName);
			Integer quant = Integer.parseInt(table.getModel().getValueAt(i,1).toString());
			obj = new Object[]{kit.getId(),product.getId(),quant};
			
			sqlUpdate = "INSERT into kit_relationship(kit,product,ammount) VALUES(?,?,?)";
			dataBase.executeUpdate(sqlUpdate,obj);	
		}
		
	}

	public void addProduct(Product product, JTable table, JSpinner spinnerAmount, Kit kit) {
		 DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		    boolean hasItem = hasItem(product, table);
		    if(!hasItem) {
		    String sql = "INSERT INTO kit_relationship(product, kit, ammount) VALUES(?,?,?)";
		    dataBase.executeUpdate(sql, new Object[] {product.getId(), kit.getId(), spinnerAmount.getValue()});
		    tbl.addRow(new Object[] {product, spinnerAmount.getValue()});
		    }else {
		    	ShowMessage.errorMessage(null, "Material", "Esse material já pertence a este produto");
		    }
	}

	private boolean hasItem(Product product, JTable table) {
		int rowCount = table.getRowCount();
		DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		for(int i = 0; i <rowCount; i++) {
			Product p = (Product) tblModel.getValueAt(i, 0);
			if(p.getName().equalsIgnoreCase(product.getName())) return true;
		}
	    return false;
    }

	public void deleteProduct(Product product, Kit kit) {
		String sql = "DELETE FROM kit_relationship WHERE kit = ? AND product = ?";
		dataBase.executeUpdate(sql, new Object[] { product.getId(), kit.getId() });
	}

	public void fillProductTable(Kit kit, JTable table) {
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM kit_relationship WHERE kit = ?", kit.getId())) {
			while (rs.next()) {
				((DefaultTableModel) table.getModel()).addRow(new Object[] { null, null, null });
				int row = table.getRowCount() - 1;
				table.setValueAt(rs.getString("ammount"), row, 1);
				try (ResultSet rsName = dataBase.executeQuery("SELECT *From compost_product WHERE id = ?",
				        rs.getInt("product"))) {
					if (rsName.next()) {
						Product p = new Product();
						p.setName(rsName.getString("product"));
						// m.setAmmount(rsName.getDouble("ammount"));
						p.setId(rsName.getInt("id"));
						table.setValueAt(p, row, 0);
					}
				}
			}
		} catch (SQLException e) {
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
