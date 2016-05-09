package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.City;
import model.Material;
import model.OutSourcedServices;
import model.State;
import model.Supplier;
import database.DataBase;
import database.dao.SuppliersDAO;

public class SupplierUpdateController extends SalesController {
	private DataBase dataBase;
	private SuppliersDAO sDAo;

	public SupplierUpdateController() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void updateSupplier(Supplier supplier, List<Material> list, List<OutSourcedServices> serviceList) {
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("companyName", supplier.getCompanyName());
		mapa.put("CNPJ", supplier.getCNPJ());
		mapa.put("city", supplier.getCity().getId());
		mapa.put("state", supplier.getState().getId());
		mapa.put("street", supplier.getStreet());
		mapa.put("neighborhood", supplier.getNeighborhood());
		mapa.put("certificate", supplier.isCertificated());
		mapa.put("stateRegistration", supplier.getStateRegistration());
		mapa.put("fiscalClassification", supplier.getFiscalClassification());
		mapa.put("materialCertification", supplier.isMaterialCertication());
		mapa.put("justificative", supplier.getJustificative());
		mapa.put("email", supplier.getEmail());
		mapa.put("phone", supplier.getPhone());
		mapa.put("cep", supplier.getCep());
		mapa.put("expirationDate", supplier.getExpireCertificateDate());
		mapa.put("id", supplier.getId());
		sDAo = new SuppliersDAO();
		sDAo.updatePersist(mapa);
		sDAo.makeProductAssociation(list, supplier);
		sDAo.makeSupplierServiceAssociation(serviceList, supplier);
		
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

	public void deleteSupplier(Supplier supplier) {
		String sql = "Delete FROM suppliers WHERE id = ?";
		int i = supplier.getId();
		String query = "DELETE FROM supplier_product_association where supplier = ?";
		dataBase.executeUpdate(query, i);		
		dataBase.executeUpdate(sql, i);
	}
	
	public void deleteSupplierProductAssociation(int i, int a) {
		String query = "DELETE FROM supplier_product_association where supplier = ? AND product = ?";
		Object[] obj = new Object[] {i, a};
		dataBase.executeUpdate(query, obj);
	}
	
	public void fillProductTable(JTable table, Supplier supplier) {
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		for(int i = tbl.getRowCount() -1; i>=0; i--) {
    		tbl.removeRow(i);
    	}
		int id = supplier.getId();
		ResultSet rs = dataBase.executeQuery("SELECT *FROM supplier_product_association where supplier = ? order by product", id);
		try {
			while (rs.next()) {
				int productId = rs.getInt("product");
				ResultSet rs2 = dataBase.executeQuery("SELECT *FROM Product WHERE id = ?", productId);
				while (rs2.next()) {
					Material product = new Material();
					product.setAmmount(rs2.getInt("quantidade"));
					product.setName(rs2.getString("name"));
					product.setId(rs2.getInt("id"));
					product.setDescrition(rs2.getString("descricao"));
					Object[] obj = new Object[] {product, product.getDescrition()};
					tbl.addRow(obj);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fillServiceTable(JTable table, Supplier supplier) {
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		int id = supplier.getId();
		ResultSet rs = dataBase.executeQuery("SELECT *FROM supplier_service_association where supplier = ?", id);
		try {
			while (rs.next()) {
				int serviceId = rs.getInt("service");
				ResultSet rs2 = dataBase.executeQuery("SELECT *FROM outsourced_services WHERE id = ?", serviceId);
				while (rs2.next()) {
					String name = rs2.getString("name");
					String observation = rs2.getString("observation");
					int outSourcedServiceId = rs2.getInt("id");
					OutSourcedServices oss = new OutSourcedServices(name, observation);
					oss.setId(outSourcedServiceId);
					Object[] obj = new Object[] {oss, oss.getObservation()};
					tbl.addRow(obj);
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

	public void deleteSupplierServiceAssociation(int i, int a) {
		String query = "DELETE FROM supplier_service_association where supplier = ? AND service = ?";
		Object[] obj = new Object[] {i, a};
		dataBase.executeUpdate(query, obj);
    }
}
