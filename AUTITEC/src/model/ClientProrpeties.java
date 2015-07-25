package model;

import java.util.Date;
import java.util.List;

public class ClientProrpeties {

	
	private int id;
	private String fiscalNote;
	private Date entryDate;
	private Date exitDate;
	private List<ClientPropertiesMaterial> propertiesList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFiscalNote() {
		return fiscalNote;
	}
	public void setFiscalNote(String fiscalNote) {
		this.fiscalNote = fiscalNote;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public Date getExitDate() {
		return exitDate;
	}
	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}
	public List<ClientPropertiesMaterial> getPropertiesList() {
		return propertiesList;
	}
	public void setPropertiesList(List<ClientPropertiesMaterial> propertiesList) {
		this.propertiesList = propertiesList;
	}
	
	
}
