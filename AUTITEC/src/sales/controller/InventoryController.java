package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;

import model.CNPJ;
import model.City;
import model.Inventory;
import model.Material;
import model.State;
import model.Supplier;
import database.DataBase;

public class InventoryController extends SalesController {

	private DataBase dataBase;

	public InventoryController() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void fillCnpj(JComboBox<CNPJ> cnpjs) {

		try {

			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM cnpj");

			while (resultSet.next()) {

				int id = resultSet.getInt("id");
				String cnpj = resultSet.getString("cnpj");
				String corporateName = resultSet.getString("corporate_name");

				cnpjs.addItem(new CNPJ(id, cnpj, corporateName));

			}

			cnpjs.setSelectedIndex(-1);

		} catch (SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
	}

	public void fillSuppliers(JComboBox<Supplier> cboSupplier) {
		cboSupplier.removeAllItems();
		City city = null;
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM suppliers")) {
			while (rs.next()) {
				String razaoSocial = rs.getString("corporate_name");
				String CNPJ = rs.getString("CNPJ");

				Supplier supplier = new Supplier(razaoSocial, CNPJ);

				supplier.setCep(rs.getString(15));
				supplier.setCertificated(rs.getBoolean("certificate"));
				supplier.setEmail(rs.getString("email"));
				supplier.setExpireCertificateDate(rs.getDate("expireCertificationDate"));
				supplier.setFiscalClassification(rs.getString("fical_classification"));
				supplier.setId(rs.getInt("id"));
				supplier.setJustificative(rs.getString("justificative"));
				supplier.setMaterialCertication(rs.getBoolean("material_certificate"));
				supplier.setNeighborhood(rs.getString("neighborhood"));
				supplier.setPhone(rs.getString("phone"));
				supplier.setStateRegistration(rs.getString("state_registration"));
				supplier.setStreet(rs.getString("street"));
				try {
					ResultSet rsState = dataBase.executeQuery("SELECT *FROM state WHERE id = ?", rs.getInt("state"));
					while (rsState.next()) {
						int id = rsState.getInt("id");
						String name = rsState.getString("name");
						State state = new State(id, name);
						try {
							ResultSet rsCity = dataBase.executeQuery("SELECT *FROM city where id = ?",
							        rs.getInt("city"));
							while (rsCity.next()) {
								int idCity = rsCity.getInt("id");
								String nameCity = rsCity.getString("name");
								city = new City(idCity, nameCity, state);
							}
							rsCity.close();
							supplier.setCityState(city, state);
						} catch (SQLException e3) {
							e3.printStackTrace();
						}
					}
					rsState.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				cboSupplier.addItem(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		cboSupplier.setSelectedIndex(-1);
	}

	public void fillProducts(JComboBox<Material> product) {
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

	public void addToInventory(Inventory i) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cnpj", i.getCnpj().getId());
		map.put("ammount", i.getAmmount());
		map.put("supplier", i.getSupplier().getId());
		map.put("material", i.getMaterial().getId());
		map.put("fiscalNote", i.getFiscalNote());

	}
}
