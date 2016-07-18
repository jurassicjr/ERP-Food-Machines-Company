package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.Material;
import model.PurchaseOrder;
import model.PurchaseOrderAssociation;
import model.PurchaseRequisition;
import model.Supplier;
import database.DataBase;

public class PurchaseOrderDAO {

	private DataBase dataBase;

	public PurchaseOrderDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(Map<String, Object> map, int status) {
	    String paymentMethod = (String) map.get("paymentMethod");
	    PurchaseRequisition purchaseRequisition = (PurchaseRequisition) map.get("purchaseRequisition");
	    int purchaseRequisitionId = purchaseRequisition.getId();
	    String shippingCompany = (String) map.get("shippingCompany");
	    Supplier supplier = (Supplier) map.get("supplier");
	    int supplierId = supplier.getId();
	    Date od  = (Date) map.get("orderDate");
	    java.sql.Date orderDate = new java.sql.Date(od.getTime());
	    Date dd = (Date) map.get("deliveryDate");
	    java.sql.Date deliveryDate = new java.sql.Date(dd.getTime());
	    String contactPhone = (String) map.get("contactPhone");
	    double freight = (double) map.get("freight");
	    String salesMan = (String) map.get("salesMan");
	    double totalValue = (double) map.get("totalValue");
	    List<PurchaseOrderAssociation> list = (List<PurchaseOrderAssociation>) map.get("purchaseOrderAssociationList");
	    int purchaseOrderId = dataBase.getAutoIncrementValue("purchase_order");
	    
	    Object[] purchaseOrderData = new Object[] {paymentMethod, purchaseRequisitionId, shippingCompany, supplierId, orderDate,
	    		deliveryDate, contactPhone, freight, salesMan, totalValue};
	    
	    String purchaseOrderSql = "insert into purchase_order(payment_method, purchase_requisition, shipping_company, supplier, order_date, "
	    		+ "delivery_date, contact_phone, freight, sales_man, total_value) values(?,?,?,?,?,?,?,?,?,?)";
	    
	    boolean purchaseOrderConcluded = dataBase.executeUpdate(purchaseOrderSql, purchaseOrderData);
	    if(purchaseOrderConcluded)registerPurchaseOrderAssociation(list, purchaseOrderId, purchaseRequisitionId);
	    
	    updateRequisition(status, purchaseRequisitionId);
	    
	    dataBase.close();
	    
    }

	private void updateRequisition(int status, int purchaseRequisitionId) {
	    int updStatus = 0;
		if(status == 0) {
	    	updStatus = 2;
	    }else if(status == 1) {
	    	updStatus = 4;
	    }else if(status == 2) {
	    	updStatus = 3;
	    }
		String updatePurchaseRequisitionSql = "update sales_requisition set status = ? where id = ?";
		Object[] updatePurchaseRequisitionData = new Object[] {updStatus, purchaseRequisitionId};
		dataBase.executeUpdate(updatePurchaseRequisitionSql, updatePurchaseRequisitionData);
    }

	private void registerPurchaseOrderAssociation(List<PurchaseOrderAssociation> list, int purchaseOrderId, int salesRequisitionID) {
	    for (PurchaseOrderAssociation purchaseOrderAssociation : list) {
	        double ammount = purchaseOrderAssociation.getAmmount();
	        double compostPrice = purchaseOrderAssociation.getCompostPrice();
	        double ipi = purchaseOrderAssociation.getIpi();
	        Material material = purchaseOrderAssociation.getMaterial();
	        int materialId = material.getId();
	        double unitPrice = purchaseOrderAssociation.getUnitPrice();
	        
	        Object[] purchaseOrderAssociationData = new Object[] {ammount, compostPrice, ipi, materialId, unitPrice, purchaseOrderId};
	        
	        String purchaseOrderAssociationSql = "insert into purchase_order_association(ammount, compost_price, ipi, material, unit_price, purchase_order) values (?,?,?,?,?,?)";
	        boolean purchaseOrdeAssociationConcluded = dataBase.executeUpdate(purchaseOrderAssociationSql, purchaseOrderAssociationData);
	        if(purchaseOrdeAssociationConcluded) {
	        	upgradePurchaseRequisition(materialId, salesRequisitionID);
	        }
        }
    }

	private void upgradePurchaseRequisition(int materialId, int salesRequisitionID) {
	    String updatePurchaseRequisitionAssociationSql = "update sales_requisition_association set is_bought = 1 where material = ? AND sales_requisition = ?";
	    Object[] data = new Object[] {materialId, salesRequisitionID};
	    dataBase.executeUpdate(updatePurchaseRequisitionAssociationSql, data );
    }

	public List<PurchaseOrder> getCompletePurchaseOrder() {
	    List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
		String sql = "SELECT * FROM purchase_order where isConcluded = ?";
	    boolean answer = false;
	    try(ResultSet rs = dataBase.executeQuery(sql, answer)){
	    	while(rs.next()) {
	    		String paymentMethod = rs.getString("payment_method");
	    		int purchaseRequisitionId = rs.getInt("purchase_requisition");
	    		PurchaseRequisition purchaseRequisition = new PurchaseRequisitionDAO().getRequisitionById(purchaseRequisitionId);
	    		String shippingCompany = rs.getString("shipping_company");
	    		int supplierId = rs.getInt("supplier");
	    		Supplier supplier = new SuppliersDAO().getSupplierbyId(supplierId);
	    		Date orderDate = rs.getDate("order_date");
	    		Date deliveryDate = rs.getDate("delivery_date");
	    		String contactPhone = rs.getString("contact_phone");
	    		double freight = rs.getDouble("freight");
	    		String salesMan = rs.getString("sales_man");
	    		double totalValue = rs.getDouble("total_value");
	    		int id = rs.getInt("id");
	    		List<PurchaseOrderAssociation> purchaseOrderAssociationList = getAssociatedList(id);
	    		PurchaseOrder po = new PurchaseOrder(paymentMethod, purchaseRequisition, shippingCompany, supplier, orderDate, deliveryDate, contactPhone, freight, salesMan, totalValue, purchaseOrderAssociationList, answer);
	    		po.setId(id);
	    		list.add(po);
	    	}
	    	return list;
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
	    return null;
    }

	private List<PurchaseOrderAssociation> getAssociatedList(int id) {
		List<PurchaseOrderAssociation> list = new ArrayList<PurchaseOrderAssociation>();
		String sql = "select * from purchase_order_association where purchase_order = ?";
	    try(ResultSet rs = dataBase.executeQuery(sql, id)){
	    	while(rs.next()) {
	    		int poaId = rs.getInt("id");
	    		double unitPrice = rs.getDouble("unit_price");
	    		double ipi = rs.getDouble("ipi");
	    		double compostPrice = rs.getDouble("compost_price");
	    		double ammount = rs.getDouble("ammount");
	    		int materialId = rs.getInt("material");
	    		Material material = new MaterialDAO().getMaterialById(materialId);
	    		PurchaseOrderAssociation poa = new PurchaseOrderAssociation(material, unitPrice, ipi, compostPrice, ammount);
	    		poa.setId(poaId);
	    		list.add(poa);
	    	}
	    	return list;
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return null;
    }

	public PurchaseOrder getCompletePurchaseById(int purchaseOrderId) {
			String sql = "SELECT * FROM purchase_order where id = ?";
		    try(ResultSet rs = dataBase.executeQuery(sql, purchaseOrderId)){
		    	if(rs.next()) {
		    		String paymentMethod = rs.getString("payment_method");
		    		int purchaseRequisitionId = rs.getInt("purchase_requisition");
		    		PurchaseRequisition purchaseRequisition = new PurchaseRequisitionDAO().getRequisitionById(purchaseRequisitionId);
		    		String shippingCompany = rs.getString("shipping_company");
		    		int supplierId = rs.getInt("supplier");
		    		Supplier supplier = new SuppliersDAO().getSupplierbyId(supplierId);
		    		Date orderDate = rs.getDate("order_date");
		    		Date deliveryDate = rs.getDate("delivery_date");
		    		String contactPhone = rs.getString("contact_phone");
		    		double freight = rs.getDouble("freight");
		    		String salesMan = rs.getString("sales_man");
		    		double totalValue = rs.getDouble("total_value");
		    		int id = rs.getInt("id");
		    		List<PurchaseOrderAssociation> purchaseOrderAssociationList = getAssociatedList(id);
		    		boolean answer = rs.getBoolean("isConcluded");
		    		PurchaseOrder po = new PurchaseOrder(paymentMethod, purchaseRequisition, shippingCompany, supplier, orderDate, deliveryDate, contactPhone, freight, salesMan, totalValue, purchaseOrderAssociationList, answer);
		    		po.setId(id);
		    		return po;
		    	}
		    } catch (SQLException e) {
		        e.printStackTrace();
	        }
		    return null;
    }

	public String getBuyNumber() {
	    String buyNumber = String.valueOf(dataBase.getAutoIncrementValue("purchase_order"));
	    dataBase.close();
		return buyNumber;
    }

	public List<PurchaseOrder> getPurchaseOrderWithoutNfe() {
		   	List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
			String sql = "SELECT * FROM purchase_order where isConcluded = ? and has_nfe = ?";
		    boolean answer = false;
		    boolean has_nfe = false;
		    Object[] data = new Object[] {answer, has_nfe};
		    try(ResultSet rs = dataBase.executeQuery(sql, data)){
		    	while(rs.next()) {
		    		String paymentMethod = rs.getString("payment_method");
		    		int purchaseRequisitionId = rs.getInt("purchase_requisition");
		    		PurchaseRequisition purchaseRequisition = new PurchaseRequisitionDAO().getRequisitionById(purchaseRequisitionId);
		    		String shippingCompany = rs.getString("shipping_company");
		    		int supplierId = rs.getInt("supplier");
		    		Supplier supplier = new SuppliersDAO().getSupplierbyId(supplierId);
		    		Date orderDate = rs.getDate("order_date");
		    		Date deliveryDate = rs.getDate("delivery_date");
		    		String contactPhone = rs.getString("contact_phone");
		    		double freight = rs.getDouble("freight");
		    		String salesMan = rs.getString("sales_man");
		    		double totalValue = rs.getDouble("total_value");
		    		int id = rs.getInt("id");
		    		List<PurchaseOrderAssociation> purchaseOrderAssociationList = getAssociatedList(id);
		    		PurchaseOrder po = new PurchaseOrder(paymentMethod, purchaseRequisition, shippingCompany, supplier, orderDate, deliveryDate, contactPhone, freight, salesMan, totalValue, purchaseOrderAssociationList, answer);
		    		po.setId(id);
		    		list.add(po);
		    	}
			    dataBase.close();
		    	return list;
		    } catch (SQLException e) {
		        e.printStackTrace();
	        }
		    dataBase.close();
		    return null;
    }
	
	
}
