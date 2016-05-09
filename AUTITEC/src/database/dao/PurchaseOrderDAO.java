package database.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import model.Material;
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
	    if(purchaseOrderConcluded)registerPurchaseOrderAssociation(list, purchaseOrderId);
	    
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
		Object updatePurchaseRequisitionData = new Object[] {updStatus, purchaseRequisitionId};
		
		dataBase.executeUpdate(updatePurchaseRequisitionSql, updatePurchaseRequisitionData);
    }

	private void registerPurchaseOrderAssociation(List<PurchaseOrderAssociation> list, int purchaseOrderId) {
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
	        	upgradePurchaseRequisition(materialId);
	        }
        }
    }

	private void upgradePurchaseRequisition(int materialId) {
	    String updatePurchaseRequisitionAssociationSql = "update sales_requisition_association set is_bought = 1 where material = ? AND sales_requisition = ?";
	    dataBase.executeUpdate(updatePurchaseRequisitionAssociationSql, materialId);
    }
	
	
}
