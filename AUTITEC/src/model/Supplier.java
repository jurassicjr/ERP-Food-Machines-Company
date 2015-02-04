package model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Supplier {

	private int id;
	private String corporateName;
	private String CNPJ;
	private List<Material> material;
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
	private java.sql.Date expireCertificateDate;
	private String justificative;
	private String cep;
	private String phone;

	public Supplier(String razaoSocial, String CNPJ) {
		this.corporateName = razaoSocial;
		this.CNPJ = CNPJ;
		registerDate = new GregorianCalendar();
		registerDate.setTime(new Date());
	}
	
	public Supplier() {
		registerDate = new GregorianCalendar();
		registerDate.setTime(new Date());
    }

	public void setCity(City city) {
		this.city = city;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public boolean isCertificate() {
		return certificate;
	}

	public void setCertificate(boolean certificate) {
		this.certificate = certificate;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
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

	public List<Material> getMaterial() {
		return material;
	}

	public void setMaterial(List<Material> material) {
		this.material = material;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public boolean isCertificated() {
		return certificate;
	}

	public void setCertificated(boolean certificado) {
		this.certificate = certificado;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String bairro) {
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

	public void setStateRegistration(String inscEstadual) {
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

	public Date getExpireCertificateDate() {
		return expireCertificateDate;
	}

	public void setExpireCertificateDate(java.sql.Date expireCertificateDate) {
		this.expireCertificateDate = expireCertificateDate;
	}

	public String getJustificative() {
		return justificative;
	}

	public void setJustificative(String justificative) {
		this.justificative = justificative;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	@Override
	public String toString() {
	    return this.corporateName;
	}
	public String getFormattedPhone() {
		return "(" + phone.substring(0, 2) + ") " + phone.substring(2, 6) + "-" + phone.substring(6, 10);
	}
	public String getFormattedCNPJ() {
		return CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." + CNPJ.substring(5, 8) + "/" + CNPJ.substring(8, 12) + "-" + CNPJ.substring(12, 14);
	}
}
