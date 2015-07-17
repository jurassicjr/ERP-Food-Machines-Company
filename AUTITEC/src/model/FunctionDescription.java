package model;

import java.util.List;

import core.CBO;

public class FunctionDescription {
	
	private int id;
	private int needExperiences;
	private List<String> knowledgementList;
	private List<String> personalHabilitiesList;
	private CBO function;
	private Employee employee;
	private String sector;
	private String minimalGraduation;
	private String functionAtribuition;
	private String period;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNeedExperiences() {
		return needExperiences;
	}
	public void setNeedExperiences(int needExperiences) {
		this.needExperiences = needExperiences;
	}
	public List<String> getKnowledgementList() {
		return knowledgementList;
	}
	public void setKnowledgementList(List<String> knowledgementList) {
		this.knowledgementList = knowledgementList;
	}
	public List<String> getPersonalHabilitiesList() {
		return personalHabilitiesList;
	}
	public void setPersonalHabilitiesList(List<String> personalHabilitiesList) {
		this.personalHabilitiesList = personalHabilitiesList;
	}
	public CBO getFunction() {
		return function;
	}
	public void setFunction(CBO cbo) {
		this.function = cbo;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getMinimalGraduation() {
		return minimalGraduation;
	}
	public void setMinimalGraduation(String minimalGraduation) {
		this.minimalGraduation = minimalGraduation;
	}
	public String getFunctionAtribuition() {
		return functionAtribuition;
	}
	public void setFunctionAtribuition(String functionAtribuition) {
		this.functionAtribuition = functionAtribuition;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	

}
