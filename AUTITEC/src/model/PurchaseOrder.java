package model;

import java.util.Date;
import java.util.List;

public class PurchaseOrder {

	private int id;
	private String paymentMethod;
	private PurchaseRequisition purchaseRequisition;
	private String shippingCompany;
	private Supplier supplier;
	private Date orderDate;
	private Date deliveryDate;
	private String contactPhone;
	private double freight;
	private String observation;
	private String salesMan;
	private double totalValue;
	private List<PurchaseOrderAssociation> purchaseOrderAssociationList;
	

	public PurchaseOrder(String paymentMethod, PurchaseRequisition purchaseRequisition,String shippingCompany, Supplier supplier
			, Date orderDate, Date deliveryDate, String contactPhone, double freight, String salesMan, double totalValue, List<PurchaseOrderAssociation> purchaseOrderAssociationList) {
				this.paymentMethod = paymentMethod;
				this.purchaseRequisition = purchaseRequisition;
				this.shippingCompany = shippingCompany;
				this.supplier = supplier;
				this.orderDate = orderDate;
				this.deliveryDate = deliveryDate;
				this.contactPhone = contactPhone;
				this.freight = freight;
				this.salesMan = salesMan;
				this.totalValue = totalValue;
				this.purchaseOrderAssociationList = purchaseOrderAssociationList;
	}

	public List<PurchaseOrderAssociation> getPurchaseOrderAssociationList() {
		return purchaseOrderAssociationList;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public int getId() {
		return id;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public PurchaseRequisition getPurchaseRequisition() {
		return purchaseRequisition;
	}

	public String getShippingCompany() {
		return shippingCompany;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public double getFreight() {
		return freight;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public double getTotalValue() {
		return totalValue;
	}
	
}
