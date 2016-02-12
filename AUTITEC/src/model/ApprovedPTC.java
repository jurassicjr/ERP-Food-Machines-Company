package model;

import java.util.Date;


public class ApprovedPTC {
	
	private int id;
	private Date date;
	private PTC ptc;
	private String observation;
	private Employee approvedBy;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public PTC getPtc() {
		return ptc;
	}
	public void setPtc(PTC ptc) {
		this.ptc = ptc;
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	public Employee getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(Employee approvedBy) {
		this.approvedBy = approvedBy;
	}
}
