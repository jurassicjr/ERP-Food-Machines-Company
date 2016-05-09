package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.City;
import model.Material;
import model.OutSourcedServices;
import model.State;
import model.Supplier;
import database.DataBase;

public class SuppliersDAO {
	private DataBase dataBase;

	/**
	 * Controi o objeto para a persistência de um fornecedor
	 * 
	 * @param data
	 * @throws SQLException
	 */

	public SuppliersDAO(Map<String, Object> data) throws SQLException {
		dataBase = new DataBase();
		dataBase.connect();
		persist(data);
	}

	public SuppliersDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	private void persist(Map<String, Object> data) throws SQLException {

		Object insertData[];
		String companyName = (String) data.get("companyName");
		String CNPJ = (String) data.get("CNPJ");
		int city = (int) data.get("city");
		int state = (int) data.get("state");
		String street = (String) data.get("street");
		String neighborhood = (String) data.get("neighborhood");
		boolean certificate = (boolean) data.get("certificate");
		String email = (String) data.get("email");
		String stateRegistration = (String) data.get("stateRegistration");
		Date dt = (Date) data.get("registerDate");
		java.sql.Date registerDate = (dt != null) ? new java.sql.Date(dt.getTime()) : null;
		;
		String fiscalClassification = (String) data.get("fiscalClassification");
		boolean materialCertificate = (boolean) data.get("materialCertification");
		String justificative = (String) data.get("justificative");
		String cep = (String) data.get("cep");
		String phone = (String) data.get("phone");
		java.sql.Date expireCertificateDate = (java.sql.Date) data.get("expirationDate");

		insertData = new Object[] { companyName, CNPJ, city, state, street, neighborhood, certificate, email,
		        stateRegistration, registerDate, fiscalClassification, materialCertificate, justificative, cep, phone,
		        expireCertificateDate };
		String sql = "INSERT INTO suppliers (corporate_name, CNPJ, city, state, street"
		        + ", neighborhood, certificate, email, state_registration, register_data,"
		        + " fical_classification, material_certificate,"
		        + " justificative, cep, phone, expireCertificationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		dataBase.executeUpdate(sql, insertData);
	}

	public void updatePersist(Map<String, Object> data) {
		Object[] insertData;
		int id = (int) data.get("id");
		String companyName = (String) data.get("companyName");
		String CNPJ = (String) data.get("CNPJ");
		int city = (int) data.get("city");
		int state = (int) data.get("state");
		String street = (String) data.get("street");
		String neighborhood = (String) data.get("neighborhood");
		boolean certificate = (boolean) data.get("certificate");
		String email = (String) data.get("email");
		String stateRegistration = (String) data.get("stateRegistration");
		String fiscalClassification = (String) data.get("fiscalClassification");
		boolean materialCertificate = (boolean) data.get("materialCertification");
		String justificative = (String) data.get("justificative");
		String cep = (String) data.get("cep");
		String phone = (String) data.get("phone");
		java.sql.Date expireCertificateDate = (java.sql.Date) data.get("expirationDate");

		insertData = new Object[] { companyName, CNPJ, city, state, street, neighborhood, certificate, email,
		        stateRegistration, fiscalClassification, materialCertificate, justificative, phone, cep,
		        expireCertificateDate, id };

		String sql = "UPDATE suppliers SET corporate_name = ?, CNPJ = ?, city = ?, state = ?, street = ?, neighborhood = ?, certificate = ?, email = ?, state_registration = ?, fical_classification = ?, material_certificate = ?, justificative = ?, phone = ?, cep = ?, expireCertificationDate = ? WHERE id = ?";
		dataBase.executeUpdate(sql, insertData);
	}

	public void makeProductAssociation(List<Material> list, Supplier supplier) {
		int supplierID = 0;
		if (!list.isEmpty()) {
			ResultSet rs = dataBase.executeQuery("SELECT *FROM suppliers WHERE corporate_name = ?",
			        supplier.getCompanyName());
			try {
				if (rs.next()) {
					supplierID = rs.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (supplierID != 0) {
				for (Material produto : list) {
					String sql = "INSERT INTO supplier_product_association(product, supplier) VALUES(?, ?)";
					Object[] data = new Object[] { produto.getId(), supplierID };
					dataBase.executeUpdate(sql, data);
				}
			}
		}
	}

	public List<Supplier> getAllSupliers() {
		City city = null;
		List<Supplier> suppliers = new ArrayList<Supplier>();
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
				suppliers.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return suppliers;
	}

	public Supplier getSupplierbyId(int supplierID) {
		City city = null;
		try (ResultSet rs = dataBase.executeQuery("SELECT *FROM suppliers WHERE id = ?", supplierID)) {
			if (rs.next()) {
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
				return supplier;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void makeSupplierServiceAssociation(List<OutSourcedServices> serviceList, Supplier supplier) {
		int supplierID = 0;
		if (!serviceList.isEmpty()) {
			ResultSet rs = dataBase.executeQuery("SELECT *FROM suppliers WHERE corporate_name = ?",
			        supplier.getCompanyName());
			try {
				if (rs.next()) {
					supplierID = rs.getInt("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (supplierID != 0) {
				for (OutSourcedServices oss : serviceList) {
					String sql = "INSERT INTO supplier_service_association(supplier, service) VALUES(?, ?)";
					Object[] data = new Object[] {supplierID, oss.getId()};
					dataBase.executeUpdate(sql, data);
				}
			}
		}
    }
}
