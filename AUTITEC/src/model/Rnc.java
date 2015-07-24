package model;

import java.util.Date;

public class Rnc {
	
	private Integer id;
	private String actiontype; 
	private String sequenceNumber;
	private Employee emitter;
	private Date date;
	private String origin;
	private String description;
	private String posaction;
	private String actionDescription;
	private String cause;
	private Boolean actionPlanNeed;
	private String actionPlanDescription;
	private Employee responsible;
	private Date deadLine;
	
	
	
	
	public Rnc(Integer id, String actiontype, String sequenceNumber, Employee emitter, Date date, String origin,
			String description, String posaction, String actionDescription, String cause, Boolean actionPlanNeed,
			String actionPlanDescription, Employee responsible, Date deadLine) {
		super();
		this.id = id;
		this.actiontype = actiontype;
		this.sequenceNumber = sequenceNumber;
		this.emitter = emitter;
		this.date = date;
		this.origin = origin;
		this.description = description;
		this.posaction = posaction;
		this.actionDescription = actionDescription;
		this.cause = cause;
		this.actionPlanNeed = actionPlanNeed;
		this.actionPlanDescription = actionPlanDescription;
		this.responsible = responsible;
		this.deadLine = deadLine;
	}
	public Rnc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getActiontype() {
		return actiontype;
	}
	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public Employee getEmitter() {
		return emitter;
	}
	public void setEmitter(Employee emitter) {
		this.emitter = emitter;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPosaction() {
		return posaction;
	}
	public void setPosaction(String posaction) {
		this.posaction = posaction;
	}
	public String getActionDescription() {
		return actionDescription;
	}
	public void setActionDescription(String actionDescription) {
		this.actionDescription = actionDescription;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public Boolean getActionPlanNeed() {
		return actionPlanNeed;
	}
	public void setActionPlanNeed(Boolean actionPlanNeed) {
		this.actionPlanNeed = actionPlanNeed;
	}
	public String getActionPlanDescription() {
		return actionPlanDescription;
	}
	public void setActionPlanDescription(String actionPlanDescription) {
		this.actionPlanDescription = actionPlanDescription;
	}
	public Employee getResponsible() {
		return responsible;
	}
	public void setResponsible(Employee responsible) {
		this.responsible = responsible;
	}
	public Date getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public String toString() {
		return sequenceNumber+" "+date;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rnc other = (Rnc) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
