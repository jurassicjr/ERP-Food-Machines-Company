package product.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

			while (resultSet.next()) {

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

		if (response == JOptionPane.YES_OPTION) {
			frame.dispose();
		}

	}

	public void clear() {

		String title = "Limpar os Dados Inseridos";
		String message = "Deseja realmente limpar os dados inseridos?";

		int response = ShowMessage.questionMessage(frame, title, message);

		if (response == JOptionPane.YES_OPTION) {
			ClearFrame.clear(frame);
		}

	}

	public void addProduct(Product product, int ammount, JTable table) {

		for (int i = 0; i < table.getRowCount(); ++i) {

			Product p = (Product) table.getValueAt(i, 0);

			if (p.equals(product)) {
				ShowMessage.errorMessage(frame, "Material já presente", product.getName()
				        + " já é um material deste produto");
				return;
			}
		}

		DefaultTableModel tbl = (DefaultTableModel) table.getModel();

		tbl.addRow(new Object[] { product, ammount });

	}

	public void register(String name, String description, JTable table) {

		if (name == null || name.isEmpty()) {
			String title = "Erro registrar Kit";
			String message = "Por favor, indique o nome do Produto";
			ShowMessage.errorMessage(frame, title, message);
			return;
		}

		if (table.getRowCount() == 0) {
			String title = "Erro registrar Kit";
			String message = "Por favor, indique a lista de materiais";
			ShowMessage.errorMessage(frame, title, message);
			return;
		}

		dataBase.executeUpdate("INSERT INTO kit (kit_name, descrition) VALUES (?, ?)", new Object[] { name,
		        description });

		int idKit = -1;

		try {

			ResultSet resultSet = dataBase.executeQuery("SELECT max(id) as 'id' FROM kit;");
			resultSet.next();
			idKit = resultSet.getInt("id");

		} catch (SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
			return;
		}

		for (int row = 0; row < table.getRowCount(); ++row) {

			Product product = (Product) table.getValueAt(row, 0);

			dataBase.executeUpdate("INSERT INTO kit_relationship (kit, product, ammount) VALUES (?, ?, ?)", new Object[] {
			        idKit, product.getId(), table.getValueAt(row, 1) });

		}

		ShowMessage.successMessage(null, "Produto adicionado", "Produto adicionado com sucesso");

		frame.dispose();

	}
}
