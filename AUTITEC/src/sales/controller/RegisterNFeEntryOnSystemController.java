package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.CFOPExit;
import model.City;
import model.Material;
import model.Nfe;
import model.NfeMaterialRelation;
import model.PurchaseOrder;
import model.State;
import model.Supplier;
import sales.view.register.RegisterNFeEntryOnSystemView;
import util.ShowMessage;
import database.DataBase;
import database.dao.CFOPDAO;
import database.dao.CstDAO;
import database.dao.MaterialDAO;
import database.dao.NfeDAO;
import database.dao.PurchaseOrderDAO;

public class RegisterNFeEntryOnSystemController {

	
	private RegisterNFeEntryOnSystemView frame;
	private DataBase dataBase;

	public RegisterNFeEntryOnSystemController(RegisterNFeEntryOnSystemView frame) {
		this.frame = frame;
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar essa janela ?");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
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

	public CFOPExit getCFOP(String code) {
		String dig = code.substring(0, 1);
		String rest = code.substring(1);
		code = dig + "." + rest;
	    CFOPExit cfop = new CFOPDAO().getCFOPExitByCode(code);
	    return cfop;
    }

	
    public void fillMaterialCbo(JComboBox<Material> cboMaterial) {
	    List<Material> list = new MaterialDAO().getAllMaterials();
	    list.stream().sorted((a, b) -> a.getName().compareTo(b.getName())).forEach(e -> cboMaterial.addItem(e));
    }

	public boolean verifyIcmsCst(String cst) {
		cst = cst.substring(1, 3);
	    boolean isCst = new CstDAO().isIcmsCst(cst);
		return isCst;
    }

	public boolean verifyCofinsCst(String cst) {
		cst = cst.substring(1, 3);
	    boolean isCst = new CstDAO().isCofinsCst(cst);
		return isCst;
    }

	public boolean verifyIpiCst(String cst) { 
		cst = cst.substring(1, 3);
	    boolean isCst = new CstDAO().isIpiCst(cst);
		return isCst;
	}

	public boolean verifyPisCst(String cst) {
		cst = cst.substring(1, 3);
	    boolean isCst = new CstDAO().isIpiCst(cst);
		return isCst;
	}

	public void registerNFe(List<NfeMaterialRelation> nfeRelationList, Nfe nfe) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("nfe", nfe);
	    map.put("nfeRelationList", nfeRelationList);
	    boolean isRegistered = new NfeDAO().register(map);
    }

	public PurchaseOrder[] getAllPurchaseOrder() {
	    List<PurchaseOrder> poList = new PurchaseOrderDAO().getCompletePurchaseOrder();
	    PurchaseOrder[] po = new PurchaseOrder[poList.size()];
	    po = poList.toArray(po);
	    
		return po;
    }
	
	
}
