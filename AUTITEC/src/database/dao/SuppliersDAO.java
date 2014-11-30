package database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Supplier;

public class SuppliersDAO {

	private Connection con;
	public SuppliersDAO(Connection connection) {
		this.con = connection;
	}
	
	public void registerSupplier(Supplier supplier) throws SQLException {
		String sql = "NSERT INTO suppliers (corporate_name, CNPJ, city, state, street, neighborhood, certificate, email, state_registration, register_data, fical_classification, material_certificate, justificative) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pst = con.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {
			pst.setString(1, supplier.getRazaoSocial());
			pst.setString(2, supplier.getCNPJ());
			pst.setInt(3, supplier.getCity().getId());
			pst.setInt(4, supplier.getState().getId());
			pst.setString(5, supplier.getRua());
			pst.setString(6, supplier.getBairro());
			pst.setBoolean(7, supplier.isCertificado());
			pst.setString(8, supplier.getEmail());
			pst.setString(9, supplier.getInscEstadual());
			pst.setDate(10, (Date)supplier.getRegisterDate());
			pst.setString(11, supplier.getFiscalClassification());
			pst.setBoolean(12, supplier.isMaterialCertication());
			pst.setString(13, supplier.getJustificative());
			pst.execute();
			try (ResultSet rs = pst.getGeneratedKeys()) {
				if (rs.next()) {
					int i = rs.getInt("id");
					supplier.setId(i);
				}
			}
		}
	}
}
