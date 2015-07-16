package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Representação de um empregado
 */
public class Employee {
	
	public static final int MALE = 0;
	public static final int FEMALE = 1;
	
	public static final int SINGLE = 0;
	public static final int MARRIED = 1; 
	public static final int DIVORCED = 2;
	public static final int WIDOWER = 3;
	
	public static final int CATEGORY_A = 0;
	public static final int CATEGORY_B = 1;
	public static final int CATEGORY_AB = 2;
	public static final int CATEGORY_AC = 3;
	public static final int CATEGORY_AD = 4;
	public static final int CATEGORY_AE = 5;
	public static final int CATEGORY_C = 6;
	public static final int CATEGORY_D = 7;
	public static final int CATEGORY_E = 8;
	
	public static final int INCOMPLETE_BASIC_SCHOOL = 0;
	public static final int BASIC_SCHOOL = 1;
	public static final int INCOMPLETE_HIGH_SCHOOL = 2;
	public static final int HIGH_SCHOOL = 3;
	public static final int INCOMPLETE_TECHNICAL_SCHOLL = 4;
	public static final int TECHNICAL_SCHOLL = 5;
	public static final int INCOMPLETE_HIGHER_EDUCATION = 6;
	public static final int HIGHER_EDUCATION = 7;
	public static final int POSTGRADUATE = 8;
	
	public static final int MONTHLY = 0;
	public static final int HOURLY = 1;
	public static final int CONTRACT = 2;
	public static final int WEEKLY = 3;
	public static final int FORTNIGHTLY = 4;
		
	private int id;
	private String name;
	private Date birth;
	private int gender;
	private int maritalStatus;
	private String nacionality;
	private String birthPlace;
	private String rg;
	private String cpf;
	private String cpts;
	private String cptsCategory;
	private String voter;
	private String driverLicense;
	private int driverLicenseCategory;
	private int schooling;
	private String reservist;
	private String reservistCategory;
	private int addressId;
	private String phone;
	private String cellphone;
	private String picture;
	private boolean active;
	private int cnpjId;
	private Address address;
	private Job job;
	private BankingData bankingData;
	private SocialIntegration socialIntegration;
	private GuaranteeFund guaranteeFund;
	private CNPJ cnpj;
	private ArrayList<Dependent> dependents;
	
	public Employee(int id, String name, String cpf, Job job) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.job = job;
	}
	
	public Employee() {
		super();
	
	}

	@SuppressWarnings("unchecked")
	public Employee(Map<String, Object> data) {
		
		id = (int) data.get("id_employee");
		name = (String) data.get("name");
		birth = (Date) data.get("birth");
		gender = (int) data.get("gender");
		maritalStatus = (int) data.get("marital_status");
		nacionality = (String) data.get("nacionality");
		birthPlace = (String) data.get("birth_place");
		rg = (String) data.get("rg");
		cpf = (String) data.get("cpf");
		cpts = (String) data.get("cpts");
		cptsCategory = (String) data.get("cpts_category");
		voter = (String) data.get("voter");
		driverLicense = (String) data.get("driver_license");
		driverLicenseCategory = (int) data.get("driver_license_category");
		schooling = (int) data.get("schooling");
		reservist = (String) data.get("reservist");
		reservistCategory = (String) data.get("reservist_category");
		phone = (String) data.get("phone");
		cellphone = (String) data.get("cellphone");
		picture = (String) data.get("picture");
		active = ((int) data.get("active") == 1);
		address = (Address) data.get("address");
		job = (Job) data.get("job");
		bankingData = (BankingData) data.get("banking_data");
		socialIntegration = (SocialIntegration) data.get("social_integration");
		guaranteeFund = (GuaranteeFund) data.get("guarantee_fund");
		cnpj = (CNPJ) data.get("cnpj");
		dependents = (ArrayList<Dependent>) data.get("dependents");
				
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(int maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getNacionality() {
		return nacionality;
	}

	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpts() {
		return cpts;
	}

	public void setCpts(String cpts) {
		this.cpts = cpts;
	}

	public String getCptsCategory() {
		return cptsCategory;
	}

	public void setCptsCategory(String cptsCategory) {
		this.cptsCategory = cptsCategory;
	}

	public String getVoter() {
		return voter;
	}

	public void setVoter(String voter) {
		this.voter = voter;
	}

	public String getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public int getDriverLicenseCategory() {
		return driverLicenseCategory;
	}

	public void setDriverLicenseCategory(int driverLicenseCategory) {
		this.driverLicenseCategory = driverLicenseCategory;
	}

	public int getSchooling() {
		return schooling;
	}

	public void setSchooling(int schooling) {
		this.schooling = schooling;
	}

	public String getReservist() {
		return reservist;
	}

	public void setReservist(String reservist) {
		this.reservist = reservist;
	}

	public String getReservistCategory() {
		return reservistCategory;
	}

	public void setReservistCategory(String reservistCategory) {
		this.reservistCategory = reservistCategory;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getCnpjId() {
		return cnpjId;
	}

	public void setCnpjId(int cnpjId) {
		this.cnpjId = cnpjId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public BankingData getBankingData() {
		return bankingData;
	}

	public void setBankingData(BankingData bankingData) {
		this.bankingData = bankingData;
	}

	public SocialIntegration getSocialIntegration() {
		return socialIntegration;
	}

	public void setSocialIntegration(SocialIntegration socialIntegration) {
		this.socialIntegration = socialIntegration;
	}

	public GuaranteeFund getGuaranteeFund() {
		return guaranteeFund;
	}

	public void setGuaranteeFund(GuaranteeFund guaranteeFund) {
		this.guaranteeFund = guaranteeFund;
	}

	public CNPJ getCnpj() {
		return cnpj;
	}

	public void setCnpj(CNPJ cnpj) {
		this.cnpj = cnpj;
	}
	
	public ArrayList<Dependent> getDependents() {
		return dependents;
	}
	
	public void setDependents(ArrayList<Dependent> dependents) {
		this.dependents = dependents;
	}

	public String toString() {
		return name;
	}
	
	public String getGenderStr() {
		if(gender == MALE) return "Masculino";
		else return "Feminino";
	}
	
	public String getMaritalStatusStr() {
		if(maritalStatus == SINGLE) return "Solteiro";
		else if(maritalStatus == MARRIED) return "Casado";
		else if(maritalStatus == DIVORCED) return "Divorciado";
		else if(maritalStatus == WIDOWER) return "Viúvo";
		return null;
	}
	
	public String getDriverLicenseCategoryStr() {
		if(driverLicenseCategory == CATEGORY_A) return "A";
		else if(driverLicenseCategory == CATEGORY_AB) return "AB";
		else if(driverLicenseCategory == CATEGORY_AC) return "AC";
		else if(driverLicenseCategory == CATEGORY_AD) return "AD";
		else if(driverLicenseCategory == CATEGORY_B) return "B";
		else if(driverLicenseCategory == CATEGORY_C) return "C";
		else if(driverLicenseCategory == CATEGORY_D) return "D";
		else if(driverLicenseCategory == CATEGORY_E) return "E";
		return null;
	}
	
	public String getSchoolingStr() {
		if(schooling == INCOMPLETE_BASIC_SCHOOL) return "Fundamental Incompleto";
		else if(schooling == BASIC_SCHOOL) return "Fundamental Completo";
		else if(schooling == INCOMPLETE_HIGH_SCHOOL) return "Médio Incompleto";
		else if(schooling == HIGH_SCHOOL) return "Médio Completo";
		else if(schooling == INCOMPLETE_TECHNICAL_SCHOLL) return "Técnico Incompleto";
		else if(schooling == TECHNICAL_SCHOLL) return "Técnico Completo";
		else if(schooling == INCOMPLETE_HIGHER_EDUCATION) return "Superior Incompleto";
		else if(schooling == HIGHER_EDUCATION) return "Superior Completo";
		else if(schooling == POSTGRADUATE) return "Pós-Graduado"; 
		return null;
	}
	
	public String getFormattedRG() {
		return rg.substring(0, 2) + "." + rg.substring(2, 5) + "." + rg.substring(5, 8) + "-" + rg.substring(8, 9);
	}
	
	public String getFormattedCpf() {
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
	}
	
	public String getFormattedVoter() {
		return voter.substring(0, 4) + " " + voter.substring(4, 8) + " " + voter.substring(8, 12) + " " + voter.substring(12, 16);
	}
	
	public String getFormattedPhone() {
		return "(" + phone.substring(0, 2) + ") " + phone.substring(2, 6) + "-" + phone.substring(6, 10);
	}
	
	public String getFormattedCellPhone() {
		return "(" + cellphone.substring(0, 2) + ") " + cellphone.substring(2, 7) + "-" + cellphone.substring(7, 11);
	}
	
	
}
