package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.City;
import model.Material;
import model.PurchaseRequisition;
import model.PurchaseRequisitionAssociation;
import model.State;
import model.Supplier;
import sales.view.PurchaseOrderFrame;
import util.ShowMessage;
import database.DataBase;
import database.dao.MaterialDAO;
import database.dao.PurchaseRequisitionDAO;

public class PurchaseOrderController {

	
	private PurchaseOrderFrame frame;
	private DataBase dataBase;

	public PurchaseOrderController(PurchaseOrderFrame frame) {
		this.frame = frame;
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente sair de pedido de compras ?");
		if(i == JOptionPane.YES_OPTION) {
			frame.dispose();
		}
	}
	
	public void fillSalesRequisitionCbo(JComboBox<PurchaseRequisition> cbo) {
		List<PurchaseRequisition> list = new PurchaseRequisitionDAO().getAllOpenRequisition();
		list.forEach(s -> cbo.addItem(s));
	}
	
	public void fillSuppliers(JComboBox<Supplier> cboSupplier, PurchaseRequisition sr) {
		cboSupplier.removeAllItems();
		List<PurchaseRequisitionAssociation> list = sr.getAssociationList();
		City city = null;
		List<Supplier> sList = new ArrayList<Supplier>();
		for (PurchaseRequisitionAssociation purchaseRequisitionAssociation : list) {
			int materialId = purchaseRequisitionAssociation.getMaterial().getId();
			boolean bought = purchaseRequisitionAssociation.isBought();
			if(!bought) {
				try (ResultSet rs = dataBase.executeQuery("select * from suppliers where id in (select distinct t1.supplier from"
						+ " supplier_product_association as t1 where t1.product = ?)", materialId)){
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
						}try(ResultSet rsMaterial = dataBase.executeQuery("select * from product where id in (select "
								+ "distinct t1.product from supplier_product_association as t1 where t1.supplier = ?)", 
								rs.getInt("id"))){
							List<Material> mList = new ArrayList<Material>();
							while (rsMaterial.next()) {
								Material material = new Material();
								material.setName(rsMaterial.getString("name"));
								material.setDescrition(rsMaterial.getString("descricao"));
								material.setId(rsMaterial.getInt("id"));
								material.setModel(rsMaterial.getInt("model"));
								material.setMaterialType(rsMaterial.getInt("material_type"));
								mList.add(material);
							}							
							supplier.setMaterial(mList);
						}
						if(sList.contains(supplier))return;
						cboSupplier.addItem(supplier);
						sList.add(supplier);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
        }

		cboSupplier.setSelectedIndex(-1);
	}

	public void fillMaterial(JComboBox<Material> cboMaterial,PurchaseRequisition sr, Supplier supplier) {
	    cboMaterial.removeAllItems();
	    List<Material> materialList = supplier.getMaterial();
	    List<PurchaseRequisitionAssociation> list = sr.getAssociationList();
	    boolean hasMaterial;
	    for (PurchaseRequisitionAssociation purchaseRequisitionAssociation : list) {
	    	int materialId = purchaseRequisitionAssociation.getMaterial().getId();
	    	hasMaterial = materialList.contains(purchaseRequisitionAssociation.getMaterial());
			boolean bought = purchaseRequisitionAssociation.isBought();
			MaterialDAO mDAO = new MaterialDAO();
			if(!bought && hasMaterial) {
				Material m = mDAO.getMaterialById(materialId);
				cboMaterial.addItem(m);
			}
        }
	    cboMaterial.setSelectedIndex(-1);
    }
}
