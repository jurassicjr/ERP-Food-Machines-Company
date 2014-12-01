package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.swing.JOptionPane;

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
		Date registerDate = (Date) data.get("registerDate");
		String fiscalClassification = (String) data.get("fiscalClassification");
		boolean materialCertificate = (boolean) data.get("materialCertification");
		String justificative = (String) data.get("justificative");

		insertData = new Object[] {companyName, CNPJ, city, state, street, neighborhood, certificate, email, stateRegistration, registerDate, fiscalClassification, materialCertificate, justificative};
		String sql = "NSERT INTO suppliers (corporate_name, CNPJ, city, state, street"
		        + ", neighborhood, certificate, email, state_registration, register_data,"
		        + " fical_classification, material_certificate,"
		        + " justificative) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		JOptionPane.showMessageDialog(null, "teste");
		ResultSet rs = dataBase.executeQuery(sql, insertData);
		if(rs.next()) {
			rs.getInt(0);
		}
		
	}
}
