package model;

import java.util.Date;

public class ExternalSatisfactionResearch {

	private int id;
	private int comunication;
	private int courtesy;
	private int effectiviness;
	private int credibility;
	private int quality;
	private int tecnicalQuality;
	private int general;
	private String criticismsAndSuggestions;
	private Date researchDate;
	private Client client;
	
	
	public ExternalSatisfactionResearch() {
		setResearchDate(new Date());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getComunication() {
		return comunication;
	}
	public void setComunication(int comunication) {
		this.comunication = comunication;
	}
	public int getCourtesy() {
		return courtesy;
	}
	public void setCourtesy(int courtesy) {
		this.courtesy = courtesy;
	}
	public int getEffectiviness() {
		return effectiviness;
	}
	public void setEffectiveness(int effectiviness) {
		this.effectiviness = effectiviness;
	}
	public int getCredibility() {
		return credibility;
	}
	public void setCredibility(int credibility) {
		this.credibility = credibility;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getTecnicalQuality() {
		return tecnicalQuality;
	}
	public void setTecnicalQuality(int tecnicalQuality) {
		this.tecnicalQuality = tecnicalQuality;
	}
	public int getGeneral() {
		return general;
	}
	public void setGeneral(int general) {
		this.general = general;
	}
	public String getCriticismsAndSuggestions() {
		return criticismsAndSuggestions;
	}
	public void setCriticismsAndSuggestions(String criticismsAndSuggestions) {
		this.criticismsAndSuggestions = criticismsAndSuggestions;
	}

	public Date getResearchDate() {
	    return researchDate;
    }

	public void setResearchDate(Date researchDate) {
	    this.researchDate = researchDate;
    }

	public Client getClient() {
	    return client;
    }

	public void setClient(Client client) {
	    this.client = client;
    }
	
	
}
