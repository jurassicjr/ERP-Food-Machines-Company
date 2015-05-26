package model;

import java.util.Date;

public class RemoveOfEPI {

	private EPI epi;
	private Employee e;
	private Date date;
	private String motive;
	private int ammount;
	
	public EPI getEpi() {
		return epi;
	}
	
	public void setEpi(EPI epi) {
		this.epi = epi;
	}
	
	public Employee getE() {
		return e;
	}
	
	public void setE(Employee e) {
		this.e = e;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getMotive() {
		return motive;
	}
	
	public void setMotive(String motive) {
		this.motive = motive;
	}

	public int getAmmount() {
		return ammount;
	}

	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}
}
