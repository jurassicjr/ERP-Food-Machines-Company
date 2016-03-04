package model;

import java.util.Date;

public class Bill {
	
	public static int TO_PAY = 0;
	
	private Integer id;
	
	private BillSubGroup subGroup;
	private BillName billName;
	private double value;
	private Date expiration;
	private String creditor;
	private double entryValue;
	private String observation;
	private int nInstallments;
	private User user;
	private CNPJ cnpj;
	private int type;

	public Bill(BillSubGroup subGroup, BillName billName, double value, Date expiration, String creditor, 
			double entryValue, String observation, int nInstallments, User user, CNPJ cnpj, int type) {
		this.subGroup = subGroup;
		this.billName = billName;
		this.value = value;
		this.expiration = expiration;
		this.creditor = creditor;
		this.entryValue = entryValue;
		this.observation = observation;
		this.nInstallments = nInstallments;
		this.user = user;
		this.cnpj = cnpj;
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public CNPJ getCnpj() {
		return cnpj;
	}

	public void setCnpj(CNPJ cnpj) {
		this.cnpj = cnpj;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public int getnInstallments() {
		return nInstallments;
	}

	public void setnInstallments(int nInstallments) {
		this.nInstallments = nInstallments;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public BillSubGroup getSubGroup() {
		return subGroup;
	}
	
	public void setSubGroup(BillSubGroup subGroup) {
		this.subGroup = subGroup;
	}
	
	public BillName getBillName() {
		return billName;
	}
	
	public void setBillName(BillName billName) {
		this.billName = billName;
	}
	
	public double getValue() {
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public Date getExpiration() {
		return expiration;
	}
	
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
	public String getCreditor() {
		return creditor;
	}
	
	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}
	
	public double getEntryValue() {
		return entryValue;
	}
	
	public void setEntryValue(double entryValue) {
		this.entryValue = entryValue;
	}
	
	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	
}
