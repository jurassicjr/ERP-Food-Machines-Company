package model;

import java.util.Date;

public class EntryOfEPI {

	private String cnpj;
	private Date date;
	private EPI epi;
	private int ammount;
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public EPI getEpi() {
		return epi;
	}
	public void setEpi(EPI epi) {
		this.epi = epi;
	}
	public int getAmmount() {
		return ammount;
	}
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}
}
