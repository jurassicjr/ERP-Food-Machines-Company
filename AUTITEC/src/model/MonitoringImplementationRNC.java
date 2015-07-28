package model;

import java.util.Date;

public class MonitoringImplementationRNC {

	private int id;
	private Date monitoringDate;
	private String monitoringDescription;
	private Employee approvedBy;
	private Rnc nonCompliance;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getMonitoringDate() {
		return monitoringDate;
	}
	public void setMonitoringDate(Date monitoringDate) {
		this.monitoringDate = monitoringDate;
	}
	public String getMonitoringDescription() {
		return monitoringDescription;
	}
	public void setMonitoringDescription(String monitoringDescription) {
		this.monitoringDescription = monitoringDescription;
	}
	public Employee getApprovedBy() {
	    return approvedBy;
    }
	public void setApprovedBy(Employee approvedBy) {
	    this.approvedBy = approvedBy;
    }
	public Rnc getNonCompliance() {
	    return nonCompliance;
    }
	public void setNonCompliance(Rnc nonCompliance) {
	    this.nonCompliance = nonCompliance;
    }
}
