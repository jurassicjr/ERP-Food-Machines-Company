package model;

import java.util.List;

import javax.swing.ButtonGroup;

public class AssessmentOfCompetence {

	private int id;
	private Employee e;
	private FunctionDescription funDes;
	private String experience;
	private List<ButtonGroup> btnGroupList;
	private List<String> funtion;
	private String point;
	private boolean isEnable;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return e;
	}
	public void setEmployee(Employee e) {
		this.e = e;
	}
	public FunctionDescription getFunDes() {
		return funDes;
	}
	public void setFunDes(FunctionDescription funDes) {
		this.funDes = funDes;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public List<ButtonGroup> getBtnGroupList() {
		return btnGroupList;
	}
	public void setBtnGroupList(List<ButtonGroup> btnGroupList) {
		this.btnGroupList = btnGroupList;
	}
	public List<String> getFuntion() {
		return funtion;
	}
	public void setFuntion(List<String> funtion) {
		this.funtion = funtion;
	}
	public String getPoint() {
	    return point;
    }
	public void setPoint(String point) {
	    this.point = point;
    }
	public boolean isEnable() {
	    return isEnable;
    }
	public void setEnable(boolean isEnable) {
	    this.isEnable = isEnable;
    }
}
