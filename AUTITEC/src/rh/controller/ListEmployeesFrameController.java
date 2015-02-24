package rh.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Address;
import model.Bank;
import model.BankingData;
import model.CBO;
import model.CNPJ;
import model.City;
import model.Dependent;
import model.Employee;
import model.GuaranteeFund;
import model.Job;
import model.SocialIntegration;
import model.State;
import rh.view.ListEmployesFrame;
import rh.view.UpdateEmployeeFrame;
import database.DataBase;

public class ListEmployeesFrameController {
	
	private ListEmployesFrame frame;
	
	private DataBase dataBase;
	
	public ListEmployeesFrameController(ListEmployesFrame frame) {
		
		this.frame = frame;
		
		dataBase = new DataBase();
		dataBase.connect();
		
	}
	
	public void setEmployees(JTable table) {
		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM employee");
			
			while(resultSet.next()) {
				
				int idEmployee = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Date birth = resultSet.getDate("birth");
				int gender = resultSet.getInt("gender");
				int maritalStatus = resultSet.getInt("marital_status");
				String nacionality = resultSet.getString("nacionality");
				String birthPlace = resultSet.getString("birth_place");
				String rg = resultSet.getString("rg");
				String cpf = resultSet.getString("cpf");
				String cpts = resultSet.getString("cpts");
				String cptsCategory = resultSet.getString("cpts_category");
				String voter = resultSet.getString("voter");
				String driverLicense = resultSet.getString("driver_license");
				int driverLicenseCategory = resultSet.getInt("driver_license_category");
				int schooling = resultSet.getInt("schooling");
				String reservist = resultSet.getString("reservist");
				String reservistCategory = resultSet.getString("reservist_category");
				int addressId = resultSet.getInt("address");
				String phone = resultSet.getString("phone");
				String cellphone = resultSet.getString("cellphone");
				int jobId = resultSet.getInt("job");
				int bankingDataId = resultSet.getInt("banking_data");
				int socialIntegrationId = resultSet.getInt("social_integration");
				int guaranteeFoundId = resultSet.getInt("guarantee_fund");
				String picture = resultSet.getString("picture");
				int active = resultSet.getInt("active");
				int cnpjId = resultSet.getInt("register_cnpj");
				
				if(active == 0) continue;
				
				Address address = getAddress(addressId);
				Job job = getJob(jobId);
				BankingData bankingData = getBankingData(bankingDataId);
				SocialIntegration socialIntegration = null;
				if(socialIntegrationId != 0) socialIntegration = getSocialIntegration(socialIntegrationId);
				GuaranteeFund guaranteeFund = getGuaranteeFund(guaranteeFoundId);
				CNPJ cnpj = getCnpj(cnpjId);
				ArrayList<Dependent> dependents = getDependents(idEmployee); 
				
				
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("id_employee", idEmployee);
				data.put("name", name);
				data.put("birth", birth);
				data.put("gender", gender);
				data.put("marital_status", maritalStatus);
				data.put("nacionality", nacionality);
				data.put("birth_place", birthPlace);
				data.put("rg", rg);
				data.put("cpf", cpf);
				data.put("cpts", cpts);
				data.put("cpts_category", cptsCategory);
				data.put("voter", voter);
				data.put("driver_license", driverLicense);
				data.put("driver_license_category", driverLicenseCategory);
				data.put("schooling", schooling);
				data.put("reservist", reservist);
				data.put("reservist_category", reservistCategory);
				data.put("phone", phone);
				data.put("cellphone", cellphone);
				data.put("picture", picture);
				data.put("active", active);
				data.put("address", address);
				data.put("job", job);
				data.put("banking_data", bankingData);
				data.put("social_integration", socialIntegration);
				data.put("guarantee_fund", guaranteeFund);
				data.put("cnpj", cnpj);
				data.put("dependents", dependents);
				
				employees.add(new Employee(data));
																
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
		int row = 0;
		for(Employee e : employees) {
			
			addTableRow(table);
			
			table.setValueAt(e, row, 0);
			table.setValueAt(e.getCpf(), row, 1);
			
			row++;
			
		}		
				
	}
	
	private City getCity(int cityId) throws SQLException {
					
		ResultSet resultSet = dataBase.executeQuery("SELECT * FROM city WHERE id = ?", cityId);
		resultSet.next();
			
		String cityName = resultSet.getString("name");
		int stateId = resultSet.getInt("state");
			
		resultSet = dataBase.executeQuery("SELECT * FROM state WHERE id = ?", stateId);
		resultSet.next();
		
		String stateName = resultSet.getString("name");
				
		State state = new State(stateId, stateName);
		City city = new City(cityId, cityName, state);
				
		return city;
		
	}
	
	private Address getAddress(int addressId) throws SQLException {
		
		ResultSet resultSet = dataBase.executeQuery("SELECT * FROM address WHERE id = ?", addressId);
		resultSet.next();
		
		String address = resultSet.getString("address");
		String neighborhood = resultSet.getString("neighborhood");
		String cep = resultSet.getString("cep");
		int cityId = resultSet.getInt("city");
		
		City city = getCity(cityId);
		
		return new Address(addressId, address, neighborhood, cep, city);
		
	}

	private Job getJob(int jobId) throws SQLException {
		
		ResultSet resultSet = dataBase.executeQuery("SELECT * FROM job WHERE id = ?", jobId);
		resultSet.next();
		
		java.sql.Date admissionDate = resultSet.getDate("admission_date");
		int cboId = resultSet.getInt("cbo");
		double initialSalary = resultSet.getDouble("initial_salary");
		int payment = resultSet.getInt("payment");
		
		resultSet = dataBase.executeQuery("SELECT * FROM cbo WHERE id = ?", cboId);
		resultSet.next();
		
		String code = resultSet.getString("code");
		String title = resultSet.getString("title");
		
		CBO cbo = new CBO(cboId, code, title);
				
		return new Job(admissionDate, cbo, initialSalary, payment);
	}

	private BankingData getBankingData(int bankingDataId) throws SQLException {
		
		ResultSet resultSet = dataBase.executeQuery("SELECT * FROM banking_data WHERE id = ?", bankingDataId);
		resultSet.next();
		
		int bankId = resultSet.getInt("bank");
		String agency = resultSet.getString("agency");
		String account = resultSet.getString("account");
		
		Bank bank = getBank(bankId);
				
		return new BankingData(bankId, bank, agency, account);
	}

	private SocialIntegration getSocialIntegration(int socialIntegrationId) throws SQLException {
				
		ResultSet resultSet = dataBase.executeQuery("SELECT * FROM social_integration WHERE id = ?", socialIntegrationId);
		resultSet.next();
		
		String cadastreNumber = resultSet.getString("cadastre_number");
		Date cadastreDate = resultSet.getDate("cadastre_date");
		String address = resultSet.getString("address");
		int bankingDataId = resultSet.getInt("baking_data");
		
		BankingData bankingData = getBankingData(bankingDataId);
				
		return new SocialIntegration(socialIntegrationId, cadastreDate, cadastreNumber, address, bankingData);
	}

	private Bank getBank(int bankId) throws SQLException {
		
		ResultSet resultSet = dataBase.executeQuery("SELECT * FROM bank WHERE id = ?", bankId);
		resultSet.next();
		
		String bankName = resultSet.getString("bank");
		String febrabanCode = resultSet.getString("febraban_code");
		
		return new Bank(bankId, febrabanCode, bankName); 
		
	}
	
	private GuaranteeFund getGuaranteeFund(int guaranteeFundId) throws SQLException {
		
		ResultSet resultSet = dataBase.executeQuery("SELECT * FROM guarantee_fund WHERE id = ?", guaranteeFundId);
		resultSet.next();
		
		Date optionDate = resultSet.getDate("option_date");
		Date retractionDate = resultSet.getDate("retraction_date");
		int bankId = resultSet.getInt("depositary_bank");
		
		Bank bank = getBank(bankId);
		
		return new GuaranteeFund(guaranteeFundId, optionDate, retractionDate, bank);
		
	}

	private CNPJ getCnpj(int cnpjId) throws SQLException {
		
		ResultSet resultSet = dataBase.executeQuery("SELECT * FROM cnpj WHERE id = ?", cnpjId);
		resultSet.next();
		
		String cnpj = resultSet.getString("cnpj");
		String corporateName = resultSet.getString("corporate_name");
		
		return new CNPJ(cnpjId, cnpj, corporateName);
		
	}	

	private ArrayList<Dependent> getDependents(int employeeId) throws SQLException {
		
		ArrayList<Dependent> dependents = new ArrayList<Dependent>();
		
		ResultSet resultSet = dataBase.executeQuery("SELECT * FROM dependents WHERE employee = ?", employeeId);
		
		while(resultSet.next()) {
						
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String relationship = resultSet.getString("relationship");
			Date birthWeddingDate = resultSet.getDate("birth_wedding_date");
			
			dependents.add(new Dependent(id, name, relationship, birthWeddingDate));
			
		}		
		
		return dependents;
	}
	
	private void addTableRow(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[]{null, null, null});
	}

	public void updateEmployee(Employee employee) {
		
		frame.setVisible(false);
		
		UpdateEmployeeFrame updateEmployeeFrame = new UpdateEmployeeFrame(employee);
		updateEmployeeFrame.setVisible(true);
		updateEmployeeFrame.setLocationRelativeTo(frame);
				
		frame.dispose();
		
	}
	
}
