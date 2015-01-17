package rh.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
import rh.view.EmployeeReportFrame;
import userInterface.components.FileChooser;
import userInterface.components.filters.PDFFilter;
import util.HandlesFile;
import util.Html;
import util.HtmlToPdf;
import util.ShowMessage;
import database.DataBase;

public class EmployeeReportFrameController {
	
	public static final String TAB = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	
	private EmployeeReportFrame frame;
	
	private DataBase dataBase;
	
	private ArrayList<Employee> employees;
	
	public EmployeeReportFrameController(EmployeeReportFrame frame) {
		
		this.frame = frame;
		
		dataBase = new DataBase();
		dataBase.connect();
		
		employees = new ArrayList<Employee>();
		setEmployees();
	}
	
	public void closeFrame() {
		
		String title = "Cancelar o Relatório";
		String message = "Deseja realmente cancelar?\nO relatório não será criado";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
			frame.dispose();			
		}		
		
	}
	
	public void selectOutput(FileChooser fileChooser, JTextField txReportFile) {
		
		fileChooser.showSaveDialog(new PDFFilter());
		
		if(fileChooser.hasSelectedFile()) {
			
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			if(!path.toUpperCase().endsWith(".PDF")) path = path += ".pdf";
				
			txReportFile.setText(path);
		}
					
	}
	
	public void setEmployees() {
				
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
	
	public void generateReport(String reportPathFile, boolean openFile) {
				
		if(!validateData(reportPathFile));
		
		File file = new File(reportPathFile);
		StringBuffer content = new StringBuffer();
		
		for(Employee e : employees) {
			
			if(!e.isActive()) return;
			
			content.append("<div style='page-break-after: always'>");
			
			content.append("<h3>Funcionário: " + e.getName() + "</h3>");
			content.append("<h4>Data de Nascimento: " + new SimpleDateFormat("dd/MM/yyyy").format(e.getBirth()) + "</h4>");
			content.append("<h4>Sexo: " + e.getGenderStr() + "</h4>");
			content.append("<h4>Estado Civil: " + e.getMaritalStatusStr() + "</h4>");
			content.append("<h4>Nacionalidade: " + e.getNacionality() + "</h4>");
			content.append("<h4>Local de Nascimento: " + e.getBirthPlace() + "</h4>");
			content.append("<h4>RG: " + e.getFormattedRG() + "</h4>");
			content.append("<h4>CPF: " + e.getFormattedCpf() + "</h4>");
			content.append("<h4>CPTS: " + e.getCpts() + TAB + "Categoria: " + e.getCptsCategory() + "</h4>");
			content.append("<h4>Titulo de Eleitor: " + e.getFormattedVoter() + "</h4>");
			content.append("<h4>Carteira de Habilitação: " + e.getDriverLicense() + TAB + "Categoria " + e.getDriverLicenseCategoryStr() + "</h4>");
			content.append("<h4>Escolaridade: " + e.getSchoolingStr() + "</h4>");
			
			if(e.getGender() == Employee.MALE) content.append("<h4>Reservista: " + e.getReservist() + TAB + "Categoria: " + e.getReservistCategory() + "</h4>");
			if(!(e.getPhone() == null || e.getPhone().isEmpty())) content.append("<h4>Telefone: " + e.getFormattedPhone() + "</h4>");
			if(!(e.getCellphone() == null || e.getCellphone().isEmpty())) content.append("<h4>Celular: " + e.getFormattedCellPhone() + "</h4>");
			
			content.append("<h4>Endereço:");
			content.append("<br/>" + TAB + "Rua/Avenida: " + e.getAddress().getAddress());
			content.append("<br/>" + TAB + "Bairro: " + e.getAddress().getNeighborhood());
			content.append("<br/>" + TAB + "CEP: " + e.getAddress().getFormattedCep());
			content.append("<br/>" + TAB + "Cidade: " + e.getAddress().getCity().getName() + "/" + e.getAddress().getCity().getState().getName());
			content.append("</h4>");
			
			content.append("<h4>Dados Bancários:");
			content.append("<br/>" + TAB + "Banco: " + e.getBankingData().getBank().getTitle());
			content.append("<br/>" + TAB + "Agência: " + e.getBankingData().getAgency());
			content.append("<br/>" + TAB + "Conta: " + e.getBankingData().getAccount());
			content.append("</h4>");
			
			if(e.getSocialIntegration() != null) {
				
				content.append("<h4>Programa de Integração Social:");
				content.append("<br/>" + TAB + "Número de Cadastro: " + e.getSocialIntegration().getCadastreNumber());
				content.append("<br/>" + TAB + "Data de Cadastro: " + new SimpleDateFormat("dd/MM/yyyy").format(e.getSocialIntegration().getCadastreDate()));
				if(!(e.getSocialIntegration().getAddress() == null || e.getSocialIntegration().getAddress().isEmpty())) {
					content.append("<br/>" + TAB + "Endereço de Cadastro: " + e.getSocialIntegration().getAddress());	
				}
				content.append("<br/>" + TAB + "Banco: " + e.getSocialIntegration().getBankingData().getBank().getTitle());
				content.append("<br/>" + TAB + "Agência: " + e.getSocialIntegration().getBankingData().getAgency());
				content.append("</h4>");			
				
			}
			
			content.append("<h4>Fundo de Garantia:");
			content.append("<br/>" + TAB + "Banco Depositário: " + e.getGuaranteeFund().getBank().getTitle());
			content.append("<br/>" + TAB + "Data de Opção: " + new SimpleDateFormat("dd/MM/yyyy").format(e.getGuaranteeFund().getOptionDate()));
			if(e.getGuaranteeFund().getRetractionDate() != null) {
				content.append("<br/>" + TAB + "Data de Retratação: " + new SimpleDateFormat("dd/MM/yyyy").format(e.getGuaranteeFund().getRetractionDate()));	
			}
			content.append("</h4>");
			
			content.append("<h4>CNPJ de Registro: " + e.getCnpj() + "</h4>");
			
			if(e.getDependents().size() > 0) {
				
				content.append("<h4>Dependentes:</h4>");
				
				for(Dependent d : e.getDependents()) {
					content.append("<p>Nome: " + d.getName() + "<br/>");
					content.append("Relação: " + d.getRelationship() + "<br/>");
					content.append("Data de Nascimento/Casamento: " + new SimpleDateFormat("dd/MM/yyyy").format(d.getBirthWeddingDate()) + "<br/>");
					content.append("</p>");
				}
								
			}
			
			if(e.equals(employees.get(employees.size() - 1))) {
				Date now = new Date();
				String formattedDate = new SimpleDateFormat().format(now);
				content.append("<br/><br/><hr /><small><i>Relatório criado em: " + formattedDate + "</i></small>");
			}
			
			content.append("</div>");
			
		}
		
		createPdf(reportPathFile, "Relatório de Funcionários", content.toString());
		
		if(openFile) {
			try { 
				Desktop.getDesktop().open(file);
			} catch (IOException e) { e.printStackTrace(); }	
		}
		else {
			ShowMessage.successMessage(frame, "Relatório Criado", "O relatório foi salvo em " + reportPathFile + "\ncom sucesso");
		}
						
	}
	
	private File createPdf(String reportFilePath, String title, String content) {
		
		File output = new File(reportFilePath);
		Html html = new Html(output);
		html.createFile(title, content.toString());
		
		String s = html.getHtml();
				
		UUID id = UUID.randomUUID();
		File temp = new File(id.toString());
		
		try { temp.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
		HandlesFile.writeFile(temp, s);
		
		HtmlToPdf.convert(temp, output);
		
		temp.delete();	
		
		return output;
		
	}
	
	private boolean validateData(String pathFile) {
		
		if(pathFile == null || pathFile.isEmpty()) {
			
			String title = "Erro criar relatório";
			String message = "Por favor indique o caminho do arquivo a ser salvo o relatório";
			ShowMessage.errorMessage(frame, title, message);
			
			return false;
			
		}
		
		return true;
		
	}

}
