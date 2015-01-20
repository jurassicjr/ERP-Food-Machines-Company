package model;

import java.util.Date;

public class ProductionStage {
	
	private int id;
	private String stage;
	private boolean finalized;
	private Date finalizedDate;
	
	public ProductionStage(int id, String stage, boolean finalized, Date finalizedDate) {
		this.id = id;
		this.stage = stage;
		this.finalized = finalized;
		this.finalizedDate = finalizedDate;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getStage() {
		return stage;
	}
	
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	public boolean isFinalized() {
		return finalized;
	}
	
	public void setFinalized(boolean finalized) {
		this.finalized = finalized;
	}
	
	public Date getFinalizedDate() {
		return finalizedDate;
	}
	
	public void setFinalizedDate(Date finalizedDate) {
		this.finalizedDate = finalizedDate;
	}
	
}
