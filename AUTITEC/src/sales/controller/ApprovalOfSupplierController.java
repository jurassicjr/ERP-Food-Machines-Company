package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import model.City;
import model.Client;
import model.Material;
import model.State;
import model.Supplier;
import database.DataBase;

public class ApprovalOfSupplierController extends SalesController {

	private DataBase dataBase;

	public ApprovalOfSupplierController() {
		dataBase = new DataBase();
		dataBase.connect();
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

	public void fillProducts(JComboBox<Material> materialList) {
		materialList.removeAllItems();
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM Product")){
			while (rs.next()) {
				Material material = new Material();
				material.setName(rs.getString("name"));
				material.setDescrition(rs.getString("descricao"));
				material.setId(rs.getInt("id"));
				material.setAmmount(rs.getInt("quantidade"));
				material.setInternalCode(rs.getString("internal_code"));
				material.setNCM(rs.getString("ncm"));
				materialList.addItem(material);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fillClient(JComboBox<Client> client) {
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM client;")) {
			while (rs.next()) {
				Client c = new Client();
				c.setName(rs.getString("name"));
				c.setStreet(rs.getString("street"));
				c.setCep(rs.getString("cep"));
				c.setEmail(rs.getString("email"));
				c.setId(rs.getInt("id"));
				c.setNeighborhood(rs.getString("neighborhood"));
				c.setPhone(rs.getString("phone"));
				try (ResultSet rsState = dataBase.executeQuery("SELECT *FROM state WHERE id = ?", rs.getInt("state"))) {
					if (rsState.next()) {
						String stateName = rsState.getString("name");
						int stateId = rsState.getInt("id");
						State state = new State(stateId, stateName);
						c.setState(state);
						try (ResultSet rsCity = dataBase.executeQuery("SELECT *FROM city WHERE id = ?",
						        rs.getInt("city"))) {
							if (rsCity.next()) {
								int cityId = rsCity.getInt("id");
								String cityName = rsCity.getString("name");
								City city = new City(cityId, cityName, state);
								c.setCity(city);
							}
						}
					}
				}
				client.addItem(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
