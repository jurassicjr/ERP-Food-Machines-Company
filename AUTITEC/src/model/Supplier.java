package model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Supplier {
	
	private int id;
	private String corporateName;
	private String CNPJ;
	private List<Produto> material;
	private City city;
	private State state;
	private String street;
	private String neighborhood;
	private boolean certificate;
	private String email;
	private String stateRegistration;
	private Calendar registerDate;
	private String fiscalClassification;
	private boolean materialCertication;
	private Calendar expireCertificateDate;
	private String justificative;
	
	public Supplier(String razaoSocial, String CNPJ) {
		this.corporateName = razaoSocial;
		this.CNPJ = CNPJ;
		registerDate = new GregorianCalendar();
		registerDate.setTime(new Date());
	}
	
	public Date getRegisterDate() {
		return registerDate.getTime();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Produto> getMaterial() {
		return material;
	}

	public void setMaterial(List<Produto> material) {
		this.material = material;
	}

	public String getStreet() {
		return street;
	}

	public void setRua(String rua) {
		this.street = rua;
	}

	public boolean isCertificated() {
		return certificate;
	}

	public void setCertificado(boolean certificado) {
		this.certificate = certificado;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setBairro(String bairro) {
		this.neighborhood = bairro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStateRegistration() {
		return stateRegistration;
	}

	public void setInscEstadual(String inscEstadual) {
		this.stateRegistration = inscEstadual;
	}

	public String getCompanyName() {
		return corporateName;
	}

	public String getCNPJ() {
		return CNPJ;
	}
	public void setCityState(City cidade, State estado) {
		this.city = cidade;
		this.state = estado;
	}
	public City getCity() {
		return this.city;
	}
	public State getState() {
		return this.state;
	}

	public String getFiscalClassification() {
	    return fiscalClassification;
    }

	public void setFiscalClassification(String fiscalClassification) {
	    this.fiscalClassification = fiscalClassification;
    }

	public boolean isMaterialCertication() {
	    return materialCertication;
    }

	public void setMaterialCertication(boolean materialCertication) {
	    this.materialCertication = materialCertication;
    }

	public Calendar getExpireCertificateDate() {
	    return expireCertificateDate;
    }

	public void setExpireCertificateDate(Calendar expireCertificateDate) {
	    this.expireCertificateDate = expireCertificateDate;
    }

	public String getJustificative() {
	    return justificative;
    }

	public void setJustificative(String justificative) {
	    this.justificative = justificative;
    }
}
