package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.City;
import model.Client;
import model.Kit;
import model.Material;
import model.Product;
import model.Service;
import model.State;
import sales.view.PTC;
import util.ShowMessage;
import database.DataBase;

public class PTCController {

	private PTC frame;
	private DataBase dataBase;

	public PTCController(PTC frame) {
		this.frame = frame;
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar",
		        "Deseja realmente fechar a PTC ?\nAs Informações não salvas serão perdidas");
		if (i == JOptionPane.YES_OPTION) {
			frame.dispose();
		}
	}

	public void fillClient(JComboBox<Client> cboClients, boolean itemSelected) {

		String sql = "SELECT * FROM client order by name";

		DefaultComboBoxModel<Client> clients = new DefaultComboBoxModel<Client>();
		cboClients.removeAllItems();
		try (ResultSet rs = dataBase.executeQuery(sql)) {
			while (rs.next()) {

				Client client = new Client();
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setCompanyNAme(rs.getString("companyname"));
				client.setStreet(rs.getString("street"));
				client.setBirthDate(rs.getDate("birthdate"));
				client.setNeighborhood(rs.getString("neighborhood"));
				client.setState(new State(rs.getInt("state"), ""));
				client.setCity(new City(rs.getInt("city"), "", client.getState()));
				client.setCep(rs.getString("cep"));
				client.setPhone(rs.getString("phone"));
				client.setEmail(rs.getString("email"));
				client.setCompanyNAme(rs.getString("companyname"));
				client.setCpf(rs.getString("cpf"));
				client.setCnpj(rs.getString("cnpj"));
				client.setStateInscrition(rs.getString("ie"));
				client.setSex(rs.getString("sex"));
				client.setContactName(rs.getString("companycontactname"));
				client.setRg(rs.getString("rg"));
				clients.addElement(client);
			}
			cboClients.setModel(clients);

			if (!itemSelected)
				cboClients.setSelectedItem(null);

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

	public void fillMaterials(JComboBox<Material> product) {
		product.removeAllItems();
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM Product")) {
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

	public void fillService(JComboBox<Service> cboService) {
		cboService.removeAllItems();
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM service")) {
			while (rs.next()) {
				Service s = new Service();
				s.setId(rs.getInt("id"));
				s.setMeal(rs.getDouble("meal"));
				s.setName(rs.getString("name"));
				s.setObservation(rs.getString("description"));
				s.setPrice_I_I_I(rs.getDouble("price_I_I_I"));
				s.setPrice_I_I_II(rs.getDouble("price_I_I_II"));
				s.setPrice_I_II_I(rs.getDouble("price_I_II_I"));
				s.setPrice_I_II_II(rs.getDouble("price_I_II_II"));
				s.setPrice_I_III_I(rs.getDouble("price_I_III_I"));
				s.setPrice_I_III_II(rs.getDouble("price_I_III_II"));
				s.setPrice_II_I_I(rs.getDouble("price_II_I_I"));
				s.setPrice_II_I_II(rs.getDouble("price_II_I_II"));
				s.setPrice_II_II_I(rs.getDouble("price_II_II_I"));
				s.setPrice_II_II_II(rs.getDouble("price_II_II_II"));
				s.setPrice_II_III_I(rs.getDouble("price_II_III_I"));
				s.setPrice_II_III_II(rs.getDouble("price_II_III_II"));
				s.setPricePerKm(rs.getDouble("price_per_km"));
				s.setStayValue(rs.getString("stay_price"));
				cboService.addItem(s);
			}
			cboService.setSelectedIndex(-1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertMaterial(Material material, double amount, JTable table) {

		for (int i = 0; i < table.getRowCount(); ++i) {

			Material m = (Material) table.getValueAt(i, 0);

			if (m.equals(material)) {
				ShowMessage.errorMessage(frame, "Material já presente", material.getName()
				        + " já é um material desta PTC");
				return;
			}
		}

		((DefaultTableModel) table.getModel()).addRow(new Object[] { null, null, null });

		int row = table.getRowCount() - 1;
		table.setValueAt(material, row, 0);
		table.setValueAt(amount, row, 1);

	}

	public void insertProduct(Product product, double amount, JTable table) {

		for (int i = 0; i < table.getRowCount(); ++i) {

			Product m = (Product) table.getValueAt(i, 0);

			if (m.equals(product)) {
				ShowMessage
				        .errorMessage(frame, "Produto já presente", product.getName() + " já é um produto desta PTC");
				return;
			}
		}

		((DefaultTableModel) table.getModel()).addRow(new Object[] { null, null, null });

		int row = table.getRowCount() - 1;
		table.setValueAt(product, row, 0);
		table.setValueAt(amount, row, 1);

	}

	public void insertKit(Kit kit, double amount, JTable table) {

		for (int i = 0; i < table.getRowCount(); ++i) {

			Kit m = (Kit) table.getValueAt(i, 0);

			if (m.equals(kit)) {
				ShowMessage.errorMessage(frame, "Kit já presente", kit.getName() + " já é um Kit desta PTC");
				return;
			}
		}

		((DefaultTableModel) table.getModel()).addRow(new Object[] { null, null, null });

		int row = table.getRowCount() - 1;
		table.setValueAt(kit, row, 0);
		table.setValueAt(amount, row, 1);

	}

	public void insertService(Service service, JTable serviceTable) {
		for (int i = 0; i < serviceTable.getRowCount(); ++i) {

			Service m = (Service) serviceTable.getValueAt(i, 0);

			if (m.equals(service)) {
				ShowMessage.errorMessage(frame, "Serviço já presente", service.getName() + " já é um Serviço desta PTC");
				return;
			}
		}

		((DefaultTableModel) serviceTable.getModel()).addRow(new Object[] { null, null, null });

		int row = serviceTable.getRowCount() - 1;
		serviceTable.setValueAt(service, row, 0);
		serviceTable.setValueAt(service.getObservation(), row, 1);
	}

}