package product.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Material;
import product.view.RegisterProductFrame;
import util.ClearFrame;
import util.ShowMessage;
import database.DataBase;

public class RegisterProductFrameController {
	
	private RegisterProductFrame frame;
	
	private DataBase dataBase;
	
	public RegisterProductFrameController(RegisterProductFrame frame) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
		
		this.frame = frame;
	}
	
	public void fillMaterials(JComboBox<Material> materials) {
		
		materials.removeAllItems();
		
		try {
			
			ResultSet rs = dataBase.executeQuery("SELECT *FROM Product");
			
			while (rs.next()) {
				
				Material material = new Material();
				material.setName(rs.getString("name"));
				material.setDescrition(rs.getString("descricao"));
				material.setId(rs.getInt("id"));
				material.setAmmount(rs.getInt("quantidade"));
				materials.addItem(material);
			}
			
			materials.setSelectedIndex(-1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeFrame() {
		
		String title = "Cancelar o registro de Produto";
		String message = "Deseja realmente cancelar?\nO produto não será registrado";
		
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
	
	public void insertMaterial(Material material, int amount, JTable table) {
				
		for(int i = 0; i < table.getRowCount(); ++i) {
			
			Material m = (Material) table.getValueAt(i, 0);
			
			if(m.equals(material)) {
				ShowMessage.errorMessage(frame, "Material já presente", material.getName() + " já é um material deste produto");
				return;
			}
		}
		
		((DefaultTableModel) table.getModel()).addRow(new Object[]{null, null, null});
		
		int row = table.getRowCount() - 1;
		table.setValueAt(material, row, 0);
		table.setValueAt(amount, row, 1);
				
	}
	
	public void register(String name, String description, JTable table) {
		
		if(name == null || name.isEmpty()) {
			String title = "Erro registrar Produto";
			String message = "Por favor, indique o nome do Produto";
			ShowMessage.errorMessage(frame, title, message);
			return;
		}
		
		if(table.getRowCount() == 0) {
			String title = "Erro registrar Produto";
			String message = "Por favor, indique a lista de materiais";
			ShowMessage.errorMessage(frame, title, message);
			return;
		}	
		
		dataBase.executeUpdate("INSERT INTO compost_product (product, description) VALUES (?, ?)", new Object[]{name, description});
		
		int idProduct = -1;
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT max(id) as 'id' FROM compost_product;");
			resultSet.next();
			idProduct = resultSet.getInt("id");
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
			return;
		}
		
		for(int row = 0; row < table.getRowCount(); ++row) {
			
			Material material = (Material) table.getValueAt(row, 0);
			
			dataBase.executeUpdate("INSERT INTO material_relationship (product, material, ammount) VALUES (?, ?, ?)", new Object[]{idProduct, material.getId(), table.getValueAt(row, 1)});
			
		}
		
		ShowMessage.successMessage(null, "Produto adicionado", "Produto adicionado com sucesso");
		
		frame.dispose();
		
	}
	

}
