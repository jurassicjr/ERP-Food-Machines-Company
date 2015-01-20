package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.Material;
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
}
