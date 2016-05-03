package model;

import java.util.Date;
import java.util.List;

public class PurchaseRequisition {

	private int id;
	private Employee requisitor;
	private String requisitionNumber;
	private PTC ptc;
	private Date date;
	private int status = 1; // Por padrão o status = 1(PENDENT);
	private List<PurchaseRequisitionAssociation> associationList;
	private String prority;
	private String justification;
	
	public static final int PENDENT = 1;
	public static final int CONCLUDED = 2;
	public static final int HALFCONCLUDEDOPENED = 3;
	public static final int HALFCONCLUDEDCLOSED = 4;
	public static final int DENIED = 5;
	
	public PurchaseRequisition(Employee requisitor, String requisitionNumber, Date date, String priority,PTC ptc) {
		this.requisitor = requisitor;
		this.requisitionNumber = requisitionNumber;
		this.date = date;
		prority = priority;
		this.ptc = ptc;
	}

	public String getPrority() {
		return prority;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<PurchaseRequisitionAssociation> getAssociationList() {
		return associationList;
	}

	public void setAssociationList(List<PurchaseRequisitionAssociation> associationList) {
		this.associationList = associationList;
	}

	public Employee getRequisitor() {
		return requisitor;
	}

	public String getRequisitionNumber() {
		return requisitionNumber;
	}

	public PTC getPtc() {
		return ptc;
	}

	public Date getDate() {
		return date;
	}


	public int getId() {
	    return id;
    }

	public void setId(int id) {
	    this.id = id;
    }
	
	@Override
	public String toString() {
		return this.requisitionNumber;
	}

	public String getJustification() {
	    return justification;
    }

	public void setJustification(String justification) {
	    this.justification = justification;
    }
}
