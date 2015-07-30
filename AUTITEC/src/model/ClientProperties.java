package model;

import java.util.Date;
import java.util.List;

public class ClientProperties {

	
	private int id;
	private String fiscalNote;
	private Date entryDate;
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
	public List<ClientPropertiesMaterial> getPropertiesList() {
		return propertiesList;
	}
	public void setPropertiesList(List<ClientPropertiesMaterial> propertiesList) {
		this.propertiesList = propertiesList;
	}
	
	@Override
	public String toString() {
		return this.fiscalNote;
	}
	
	
}
