package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.fabric.xmlrpc.base.Data;

import model.Bank;
import model.CBO;
import model.CNPJ;
import model.City;
import model.Employee;
import model.Job;
import util.ShowMessage;
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
	
	public EmployeeDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	/**
	 * Realiza a persistência do empregado
	 */
	public void persist(Map<String, Object> data) {
		
		String name = (String) data.get("name");
		Date birth = (Date) data.get("birth");
		int gender = (int) data.get("gender");
		int maritialStatus = (int) data.get("maritial_status");
		String nacionality = (String) data.get("nacionality");
		String birthPlace = (String) data.get("birth_place");
		String rg = (String) data.get("rg");
		String cpf = (String) data.get("cpf");
		String cpts = (String) data.get("cpts");
		String cptsCategory = (String) data.get("cpts_category");
		String voter = (String) data.get("voter");
		String driverLicense = (String) data.get("driver_license");
		int driverLicenseCategory = (int) data.get("driver_license_category");
		int schooling = (int) data.get("schooling");
		String reservist = (String) data.get("reservist");
		String reservistCategory = (String) data.get("reservist_category");
		String picturePath = (String) data.get("picture");
		String address = (String) data.get("address");
		String neighborhood = (String) data.get("neighborhood");
		String cep = (String) data.get("cep");
		City city = (City) data.get("city");
		String phone = (String) data.get("phone");
		String cellphone = (String) data.get("cellphone");
		Date admissionDate = (Date) data.get("admission_date");
		CBO job = (CBO) data.get("job");
		CNPJ registrationCnpj = (CNPJ) data.get("registration_cnpj");
		String salary = (String) data.get("salary");
		int payment = (int) data.get("payment");
		Bank bank = (Bank) data.get("bank");
		String agency = (String) data.get("agency");
		String account = (String) data.get("account");
		Date optionDate = (Date) data.get("option_date");
		Date retractionDate = (Date) data.get("retraction_date");
		Bank depositaryBank = (Bank) data.get("depositary_bank");
		Date cadastreDate = (Date) data.get("cadastre_date");
		String socialIntegrationCadastreNumber = (String) data.get("social_integration_cadastre_number");
		Bank socialIntegrationBank = (Bank) data.get("social_integration_bank");
		String socialIntegrationAgency = (String) data.get("social_integration_agency");
		String socialIntegrationAddress = (String) data.get("social_integration_address");
		JTable dependents = (JTable) data.get("dependents");
						
		Integer socialIntegrationId = null;
		Integer guaranteeFundId = null;
		Integer addressId = null;
		Integer jobId = null;
		Integer employeeId = null;
		Integer bankingId = null;
		
		employeeId = dataBase.getAutoIncrementValue("employee");
				
		if(socialIntegrationCadastreNumber != null && !socialIntegrationCadastreNumber.isEmpty()){
			socialIntegrationId = registerSocialIntegrationProgram(cadastreDate, socialIntegrationCadastreNumber, socialIntegrationBank, socialIntegrationAgency, socialIntegrationAddress);
		}
		
		guaranteeFundId = registerGuaranteeFund(optionDate, retractionDate, depositaryBank);
		addressId = registerAddress(address, neighborhood, cep, city);
		jobId = registerJob(admissionDate, job, Double.parseDouble(salary), payment);
		bankingId = registerBankingData(bank, agency, account);
				
		java.sql.Date birthDate = (birth != null) ? new java.sql.Date(birth.getTime()) : null;
		rg = rg.replaceAll("\\.|-", "");
		cpf = cpf.replaceAll("\\.|-", "");
		voter = voter.replaceAll(" ", "");
		phone = phone.replaceAll(" |\\(|\\)|-", "");
		cellphone = cellphone.replaceAll(" |\\(|\\)|-", "");		
						
		String sql = "INSERT INTO employee (name, birth, gender, marital_status, nacionality, birth_place, rg, cpf, cpts, cpts_category, voter, driver_license, "
				+ "driver_license_category, schooling, reservist, reservist_category, address, phone, cellphone, job, banking_data, social_integration, guarantee_fund, "
				+ "picture, active, register_cnpj)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Object inserts[] = new Object[]{name, birthDate, gender, maritialStatus, nacionality, birthPlace, rg, cpf, cpts, cptsCategory, voter, driverLicense,
				driverLicenseCategory, schooling, reservist, reservistCategory, addressId, phone, cellphone, jobId, bankingId, socialIntegrationId, guaranteeFundId, 
				picturePath, 1, registrationCnpj.getId()};
		
		dataBase.executeUpdate(sql, inserts);
		
		registerDependets(dependents, employeeId);
		
		ShowMessage.successMessage(null, "Funcionário Registrado", "O Funcionário foi Registrado com sucesso");
		
		dataBase.close();
		
	}	
	
	/**
	 * Registra o programa de inserção social 
	 * 
	 * @param registerDate A data de registro no programa
	 * @param cadastreNumber O número de registristro no programa
	 * @param bank O banco associado ao registro
	 * @param agency A agência em que se recebe o benefício
	 * @param account A conta em que se recebe o benefício
	 * 
	 * @return O ID da tupla criada
	 */
	private int registerSocialIntegrationProgram(Date registerDate, String cadastreNumber, Bank bank, String agency, String address) {
		
		String sql = "INSERT INTO social_integration (dadastre_date, cadastre_number, baking_data) VALUES (?, ?, ?)";
		Object insertData[];
		
		java.sql.Date date = (registerDate != null) ? new java.sql.Date(registerDate.getTime()) : null;		
		int idBankinkData = registerBankingData(bank, agency, null);
				
		int socialIntegrationId = dataBase.getAutoIncrementValue("social_integration");
		
		insertData = new Object[]{date, cadastreNumber, idBankinkData};
		
		dataBase.executeUpdate(sql, insertData);	
		
		return socialIntegrationId;
	}
	
	/**
	 * Registra os dados bancários de uma conta
	 * 
	 * @param bank O banco em que pertence os dados
	 * @param agency A agência dos dados bancários
	 * @param account A conta dos dados bancários
	 * 
	 * @return O ID da tupla criada
	 */
	private int registerBankingData(Bank bank, String agency, String account) {
						
		String sql = "INSERT INTO banking_data (bank, agency, account) VALUES (?, ?, ?)";
		Object insertData[] = new Object[]{bank.getId(), agency, null};
		
		int id = dataBase.getAutoIncrementValue("banking_data");
								
		dataBase.executeUpdate(sql, insertData);
		
		return id;
		
	}

	/**
	 * Registra os dados de um fundo de garantia
	 * 
	 * @param optionDate A data de opção do fundo de garantia
	 * @param retractionDate A data de retratação do fundo de garantia
	 * @param depositaryBank O Banco depositário do fundo de garantia
	 * 
	 * @return O ID da tupla criada
	 */
	private int registerGuaranteeFund(Date optionDate, Date retractionDate, Bank depositaryBank) {
		
		int id = dataBase.getAutoIncrementValue("guarantee_fund");
		
		java.sql.Date opDate = (optionDate != null) ? new java.sql.Date(optionDate.getTime()) : null;
		java.sql.Date rtDate = (retractionDate != null) ? new java.sql.Date(retractionDate.getTime()) : null;
		int bankId = depositaryBank.getId();
		
		String sql  = "INSERT INTO guarantee_fund (option_date, retraction_date, depositary_bank) VALUES (?, ?, ?)";
		Object insertData[] = new Object[]{opDate, rtDate, bankId};
		
		dataBase.executeUpdate(sql, insertData);
		
		return id;
		
	}
	
	/**
	 * Registra os dados de um endereço
	 * 
	 * @param address O endereço a ser registrado
	 * @param neighborhood O bairro do endereço
	 * @param cep O CEP do endereço
	 * @param city A cidade em que o endereço se encontra
	 *  
	 * @return O ID da tupla criada
	 */
	private int registerAddress(String address, String neighborhood, String cep, City city) {
		
		int id = dataBase.getAutoIncrementValue("address");
		
		cep = cep.replaceAll("\\.|-", "");
		
		String sql = "INSERT INTO address (address, neighborhood, cep, city) VALUES (?, ?, ?, ?)";
		Object insertData[] = new Object[]{address, neighborhood, cep, city.getId()};
		
		dataBase.executeUpdate(sql, insertData);
				
		return id;
		
	}

	/**
	 * Registra os dados de um cargo
	 * 
	 * @param admissionDate A data de admissão
	 * @param cbo O Objeto contendo os dados CBO do cargo
	 * @param initialSalary O salário inicial do cargo
	 * @param payment A forma de pagamento do salário
	 * 
	 * @return O ID da tupla criada
	 */
	private int registerJob(Date admissionDate, CBO cbo, double initialSalary, int payment) {
		
		int id = dataBase.getAutoIncrementValue("job");
		
		java.util.Date adDate = (admissionDate != null) ? new java.sql.Date(admissionDate.getTime()) : null;
				
		String sql = "INSERT INTO job (admission_date, cbo, initial_salary, payment) VALUES (?, ?, ?, ?)";
		Object insertData[] = new Object[]{adDate, cbo.getId(), initialSalary, payment};
		
		dataBase.executeUpdate(sql, insertData);
		
		return id;
		
	}

	/**
	 * Registra os dados dos dependentes
	 * 
	 * @param dependents A tabela contendo os dependentes
	 * @param employeeId O id do usuario
	 */
	private void registerDependets(JTable dependents, int employeeId) {
		
		DefaultTableModel model = (DefaultTableModel) dependents.getModel();

		for(int i = 0; i < model.getRowCount(); ++i) {
			
			String name = (String) model.getValueAt(i, 0);
			String relationship = (String) model.getValueAt(i, 1);
			Object date =  model.getValueAt(i, 2);
			
			if((name == null || name.isEmpty()) && (relationship == null || relationship.isEmpty()) && (date == null)) continue;
			if(date instanceof String) continue;
						
			java.sql.Date birthWeddinDate = (date != null) ? new java.sql.Date(((Date) date).getTime()) : null;
			
			String sql = "INSERT INTO dependents (employee, name, relationship, birth_wedding_date) VALUES (?, ?, ?, ?)";
			Object inserData[] = new Object[]{employeeId, name, relationship, birthWeddinDate};
			
			dataBase.executeUpdate(sql, inserData);
			
		}
		
	}
	
	/**
	 * Retorna um objeto do empregado registrado no banco de dados de acordo com o id
	 * 
	 * @return id O id do empregado no banco de dados
	 */
	public Employee getEmployeeById(int id) {
		
		Employee employee = null;
		
		try {
		
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM employee WHERE id = ?", id);
			
			if(resultSet.next()) {
				
				String name = resultSet.getString("name");
				String cpf = resultSet.getString("cpf");
				//Job job =;
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
		return employee;
		
	}
	
}
