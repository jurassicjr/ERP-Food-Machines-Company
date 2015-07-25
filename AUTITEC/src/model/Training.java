package model;

import java.util.Date;
import java.util.List;

public class Training {
	
	private String title;
	private String objective;
	private String eventType;
	private String period;
	private String duration;
	private Date date;
	private List<Employee> employeeList;
	private String motive;
	private int id;
	private String place;
	private String otherMotive;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<Employee> getEmployeeList() {
		if(employeeList == null)return null;
		if(employeeList.isEmpty())return null;
		return employeeList;
	}
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	public String getMotive() {
		return motive;
	}
	public void setMotive(String motive) {
		this.motive = motive;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return this.title;
	}
	public String getOtherMotive() {
	    return otherMotive;
    }
	public void setOtherMotive(String otherMotive) {
	    this.otherMotive = otherMotive;
    }

}
