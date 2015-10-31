package model;

import java.util.Date;

public class Bill {
	
	
	private double value;
	private Date payedDate;
	private Date expiration;
	private String observation;
	private String creditor;
	private BillName billName; 
	private BillSubGroup subGroup;
	private Integer id;
	
	public Bill getBill() {
		return this;
	}
	
	public Bill(double value, Date payed_date, Date expiration, String observation, String creditor, BillName billName, BillSubGroup subGroup) {
		this.value = value;
		this.payedDate = payed_date;
		this.expiration = expiration;
		this.observation = observation;
		this.creditor = creditor;
		this.billName = billName;
		this.subGroup = subGroup;
	}
	
	public Bill(double value, Date expiration, String observation, String creditor, BillName billName, BillSubGroup subGroup) {
		this.value = value;
		this.payedDate = null;
		this.expiration = expiration;
		this.observation = observation;
		this.creditor = creditor;
		this.billName = billName;
		this.subGroup = subGroup;
	}

	public Bill(String billName, String creditor2, String observation2, int billId) {
		creditor = creditor2;
		observation = observation2;
		id = billId;
		this.billName.setName(billName);
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getPayedDate() {
		return payedDate;
	}

	public void setPayed_date(Date payed_date) {
		this.payedDate = payed_date;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getCreditor() {
		return creditor;
	}

	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}

	public BillName getBillName() {
		return billName;
	}

	public void setBillName(BillName billName) {
		this.billName = billName;
	}

	public BillSubGroup getSubGroup() {
		return subGroup;
	}

	public void setSubGroup(BillSubGroup subGroup) {
		this.subGroup = subGroup;
	}
		
	public Date getExpiration() {
		return expiration;
	}
	
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Integer getId() {
	    return id;
    }

	public void setId(Integer id) {
	    this.id = id;
    }
	
}
