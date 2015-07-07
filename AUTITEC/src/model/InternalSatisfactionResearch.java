package model;

import java.util.Date;

public class InternalSatisfactionResearch {

	private int id;
	private Employee employee;
	private Date date;
	private int workingArea;
	private int functionPerfomed;
	private int relationGroup;
	private int comunicationFacility;
	private int WCs;
	private int IndustryIntenalLighting;
	private int formers;
	private int EPIs;
	private int uniform;
	private int tooling;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getWorkingArea() {
		return workingArea;
	}
	
	public void setWorkingArea(int workingArea) {
		this.workingArea = workingArea;
	}
	
	public int getFunctionPerfomed() {
		return functionPerfomed;
	}
	
	public void setFunctionPerfomed(int functionPerfomed) {
		this.functionPerfomed = functionPerfomed;
	}
	
	public int getRelationGroup() {
		return relationGroup;
	}
	
	public void setRelationGroup(int relationGroup) {
		this.relationGroup = relationGroup;
	}
	
	public int getComunicationFacility() {
		return comunicationFacility;
	}
	
	public void setComunicationFacility(int comunicationFacility) {
		this.comunicationFacility = comunicationFacility;
	}
	
	public int getWCs() {
		return WCs;
	}
	
	public void setWCs(int wCs) {
		WCs = wCs;
	}
	
	public int getIndustryIntenalLighting() {
		return IndustryIntenalLighting;
	}
	
	public void setIndustryIntenalLighting(int industryIntenalLighting) {
		IndustryIntenalLighting = industryIntenalLighting;
	}
	
	public int getFormers() {
		return formers;
	}
	
	public void setFormers(int formers) {
		this.formers = formers;
	}
	
	public int getEPIs() {
		return EPIs;
	}
	
	public void setEPIs(int ePIs) {
		EPIs = ePIs;
	}
	
	public int getUniform() {
		return uniform;
	}
	
	public void setUniform(int uniform) {
		this.uniform = uniform;
	}
	
	public int getTooling() {
		return tooling;
	}
	
	public void setTooling(int tooling) {
		this.tooling = tooling;
	}
}