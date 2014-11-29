package database.dao;

import java.util.Date;
import java.util.Map;

import javax.swing.JTable;

import model.Bank;
import model.CBO;
import model.City;
import database.DataBase;

/**
 * Realiza a persistência de um empregado
 */
public class EmployeeDAO {
	
	private DataBase dataBase;
	
	/**
	 * Controi o objeto para a persistência de um empregado
	 * 
	 * @param data
	 */
	public EmployeeDAO(Map<String, Object> data) {
		dataBase = new DataBase();
		dataBase.connect();
		persist(data);		
	}
	
	/**
	 * Realiza a persistência do empregado
	 */
	public void persist(Map<String, Object> data) {
		
		String name = (String) data.get("name");
		Date birth = (Date) data.get("birth");
		String gender = (String) data.get("gender");
		String maritialStatus = (String) data.get("maritial_status");
		String nacionality = (String) data.get("nacionality");
		String birthPlace = (String) data.get("birth_place");
		String rg = (String) data.get("rg");
		String cpf = (String) data.get("cpf");
		String cpts = (String) data.get("cpts");
		String cptsCategory = (String) data.get("cpts_category");
		String voter = (String) data.get("voter");
		String driverLicense = (String) data.get("driver_license");
		String driverLicenseCategory = (String) data.get("driver_license_category");
		String schooling = (String) data.get("schooling");
		String reservist = (String) data.get("reservist");
		String reservistCategory = (String) data.get("reservist_category");
		String picuturePath = (String) data.get("picture");
		String address = (String) data.get("address");
		String neighborhood = (String) data.get("neighborhood");
		String cep = (String) data.get("cep");
		City city = (City) data.get("city");
		String phone = (String) data.get("phone");
		String cellphone = (String) data.get("cellphone");
		Date admissionDate = (Date) data.get("admission_date");
		CBO job = (CBO) data.get("job");
		String salary = (String) data.get("salary");
		String payment = (String) data.get("payment");
		Bank bank = (Bank) data.get("bank");
		String agency = (String) data.get("agency");
		String account = (String) data.get("account");
		Date optionDate = (Date) data.get("option_date");
		Bank depositaryBank = (Bank) data.get("depositary_bank");
		Date retractionDate = (Date) data.get("retraction_date");
		Date cadastreDate = (Date) data.get("cadastre_date");
		String socialIntegrationCadastreNumber = (String) data.get("social_integration_cadastre_number");
		Bank socialIntegrationBank = (Bank) data.get("social_integration_bank");
		String socialIntegrationAgency = (String) data.get("social_integration_agency");
		String socialIntegrationAddress = (String) data.get("social_integration_address");
		JTable dependentTable = (JTable) data.get("dependents");
		
		String sql;
		Object insertData[];
		
//		if(socialIntegrationCadastreNumber != null && !socialIntegrationCadastreNumber.isEmpty()){
//			registerSocialIntegrationProgram(soc, cadastreNumber, socialIntegrationBank);
//		}
		
		sql  = "INSERT INTO guarantee_fund (option_date, retraction_date, depositary_bank) VALUES (?, ?, ?)";
		insertData = new Object[]{new java.sql.Date(optionDate.getTime()), new java.sql.Date(retractionDate.getTime())};
		
		
//		String sql = "INSERT INTO employee (name, birth, gender, marital_status, nacionality, birth_place, rg, cpf, cpts, cpts_category,"
//				+ "voter, driver_license, driver_license_category, schooling, reservist, reservist_category, address, phone, cellphone, job, baking_data,"
//				+ "social_integration, picture";
//		
//		Object inserts[] = new Object[]{name, birth, gender, maritialStatus, nacionality, birthPlace, rg, cpf, cpts, cptsCategory, voter, driverLicense, driverLicenseCategory,
//				schooling, reservist, reservistCategory, address, phone, cellphone, job.getId(), bank.getId()};
		
	}	
	
	/**
	 * Registra o programa de inserção social 
	 * 
	 * @param registerDate A data de registro no programa
	 * @param cadastreNumber O número de registristro no programa
	 * @param bank O banco associado ao registro
	 */
	private void registerSocialIntegrationProgram(Date registerDate, String cadastreNumber, Bank bank) {
		
		String sql = "INSERT INTO  social_integration (dadastre_date, cadastre_number, baking_data) VALUES (?, ?, ?)";
		Object insertData[];
		
		int BankId = (bank != null) ? bank.getId() : null;
		java.sql.Date date = (registerDate != null) ? new java.sql.Date(registerDate.getTime()) : null;
		
		insertData = new Object[]{date, cadastreNumber, BankId};
		
		dataBase.executeQuery(sql, insertData);
		
		
	}

}
