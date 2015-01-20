package product.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Material;
import model.Product;
import product.view.RegisterKitFrame;
import util.ClearFrame;
import util.ShowMessage;
import database.DataBase;

public class RegisterKitFrameController {
	
	private RegisterKitFrame frame;
	
	private DataBase dataBase;
	
	public RegisterKitFrameController(RegisterKitFrame frame) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
		this.frame = frame;
	}
	
	public void setProducts(JComboBox<Product> products) {
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM compost_product");
			
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String product = resultSet.getString("product");
				String description = resultSet.getString("description");
				
				Product p = new Product(id, product, description);
				
				products.addItem(p);
			}
			
			products.setSelectedIndex(-1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
			return;
		}
		
	}

	public void closeFrame() {
		
		String title = "Cancelar o registro de Kit";
		String message = "Deseja realmente cancelar?\nO kit não será registrado";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
			frame.dispose();			
		}
		
	}
	
	public void clear() {
		
		String title = "Limpar os Dados Inseridos";
		String message = "Deseja realmente limpar os dados inseridos?";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
			ClearFrame.clear(frame);
		}		
		
	}
	
	public void addProduct(Product product, int amount, JTable table) {
		
		for(int i = 0; i < table.getRowCount(); ++i) {
			
			
			
//			Material m = (Material) table.getValueAt(i, 0);
//			
//			if(m.equals(material)) {
//				ShowMessage.errorMessage(frame, "Material já presente", material.getName() + " já é um material deste produto");
//				return;
//			}
		}
//		
//		((DefaultTableModel) table.getModel()).addRow(new Object[]{null, null, null});
//		
//		int row = table.getRowCount() - 1;
//		table.setValueAt(material, row, 0);
//		table.setValueAt(amount, row, 1);
				
	}
	
}
