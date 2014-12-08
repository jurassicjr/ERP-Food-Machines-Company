package database.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import database.DataBase;

public class SuppliersDAO {
	private DataBase dataBase;

	/**
	 * Controi o objeto para a persistência de um fornecedor
	 * 
	 * @param data
	 * @throws SQLException 
	 */

	public SuppliersDAO(Map<String, Object> data, Date dt) throws SQLException {
		dataBase = new DataBase();
		dataBase.connect();
		persist(data, dt);
	}

	private void persist(Map<String, Object> data, Date dt) throws SQLException {

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
		java.sql.Date registerDate = (dt != null) ? new java.sql.Date(dt.getTime()) : null;;
		String fiscalClassification = (String) data.get("fiscalClassification");
		boolean materialCertificate = (boolean) data.get("materialCertification");
		String justificative = (String) data.get("justificative");
		String cep = (String) data.get("cep");
		String phone = (String) data.get("phone");
		//java.sql.Date expireCertificateDate = (java.sql.Date) data.get("expirationDate");

		insertData = new Object[] {companyName, CNPJ, city, state, street, neighborhood, certificate, email, stateRegistration, registerDate, fiscalClassification, materialCertificate, justificative, cep, phone};
		String sql = "INSERT INTO suppliers (corporate_name, CNPJ, city, state, street"
		        + ", neighborhood, certificate, email, state_registration, register_data,"
		        + " fical_classification, material_certificate,"
		        + " justificative, cep, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		dataBase.executeUpdate(sql, insertData);
	}
}
