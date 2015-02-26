package rh.controller;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ComboBoxEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import model.Bank;
import model.CBO;
import model.CNPJ;
import model.City;
import model.Employee;
import model.State;
import rh.view.RegisterCBOFrame;
import rh.view.UpdateEmployeeFrame;
import userInterface.components.FileChooser;
import userInterface.components.filters.ImageFilter;
import util.FTP;
import util.ShowMessage;
import database.DataBase;

public class UpdateEmployeeFrameController {
	
	private UpdateEmployeeFrame frame;
	
	private DataBase dataBase;
	
	public UpdateEmployeeFrameController(UpdateEmployeeFrame frame) {
		
		this.frame = frame;
		
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	/**
	 * Preenche combobox de estados e cidades com valores recuperados do banco de dados
	 * 
	 * @param states O Combobox que representa os estados
	 * @param cities O Combobox que representa as cidades
	 */
	public void fillStateAndCity(JComboBox<State> states, JComboBox<City> cities) {
				
		setStates(states, cities);
		
		ItemListener comboboxListener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == 1) setCities((State) states.getSelectedItem(), cities);
			}
		};
			
		states.addItemListener(comboboxListener);
		
	}
	
	/**
	 * Define no combobox todos os estados registrados no banco
	 * 
	 * @param states O combobox que representa os estados
	 * @param cities O combobox que representa as cidades
	 */
	private void setStates(JComboBox<State> states, JComboBox<City> cities) {		
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM state");
			
			while(resultSet.next()) {
				
				State state = new State(resultSet.getInt("id"), resultSet.getString("name"));
				states.addItem(state);
				
			}
			
			resultSet.close();
			states.setSelectedIndex(-1);
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Define no combobox todos as cidades registrados no banco para o estado
	 * 
	 * @param state O estado selecionado no combobox
	 * @param cities O combobox que representa as cidades
	 */
	private void setCities(State state, JComboBox<City> cities) {
		
		cities.removeAllItems();
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM city WHERE city.state = ?", new Object[]{state.getId()});
			
			while(resultSet.next()) {
				
				City city = new City(resultSet.getInt("id"), resultSet.getString("name"), state);
				cities.addItem(city);
				
			}
			
			resultSet.close();
			
			cities.setSelectedIndex(-1);
			cities.setEnabled(true);
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Define no combobox os valores do CBO
	 * 
	 * @param cbo O ComboBox responsável pelos cargos presentes na CBO
	 */
	public void fillCBO(JComboBox<CBO> cbo, JTextField cboCode) {
		
		try {
		
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM cbo ORDER BY title");
			
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String code = resultSet.getString("code");
				String title = resultSet.getString("title");
								
				cbo.addItem(new CBO(id, code, title));
			
			}
			
			resultSet.close();
			cbo.setSelectedIndex(-1);
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
			
		ComboBoxEditor editor = cbo.getEditor();  
		Component component = editor.getEditorComponent();  
		  
		component.addFocusListener(new FocusAdapter() {
			
			@Override
		    public void focusLost(FocusEvent e) {  
		        				
				CBO cboItem = (CBO) cbo.getSelectedItem();
				
				if(cboItem != null)
					cboCode.setText(cboItem.getCode());				
		    }
		    
		});  
		
	}
	
	/**
	 * Recupera do banco e define no combobox os valores dos cnpjs
	 * 
	 * @param cnpjs O ComboBox responsável pelos cnpjs presentes no banco de dados
	 */
	public void fillCnpj(JComboBox<CNPJ> cnpjs) {
				
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM cnpj");
			
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String cnpj = resultSet.getString("cnpj");
				String corporateName = resultSet.getString("corporate_name");
				
				cnpjs.addItem(new CNPJ(id, cnpj, corporateName));
								
			}
			
			cnpjs.setSelectedIndex(-1);
			
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
	}
	
	/**
	 * Recupera do banco e define no combobox os valores dos bancos
	 * 
	 * @param banks O ComboBox responsável pelos bancos presentes no banco de dados
	 */
	public void fillBanks(JComboBox<Bank> banks) {
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM bank");
			
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String code = resultSet.getString("febraban_code");
				String bank = resultSet.getString("bank");
				
				banks.addItem(new Bank(id, code, bank));
				
			}
			
			banks.setSelectedIndex(-1);
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
	}
	
	public String loadProfilePicture(FileChooser fileChooser, JLabel picture) {
		
		fileChooser.showOpenDialog(new ImageFilter());
		
		if(fileChooser.hasSelectedFile()) {
			
			File file = fileChooser.getSelectedFile();
			String tokens[] = file.getName().split("\\.");
			String fileName = UUID.randomUUID().toString() + "." + tokens[tokens.length - 1];
									
			new Thread(new Runnable() {
				
				@Override
				public void run() {					
					
					FTP ftp = new FTP();					
					boolean up = ftp.upload(file, fileName, "pictures");
					
					if(!up) {
						ShowMessage.errorMessage(null, "Erro ao subir o arquivo", "Houve um erro ao subir o arquivo.\nPor favor, consulte o suporte");
					}
					
					ImageIcon image = new ImageIcon(fileChooser.getSelectedPathFile());
					ImageIcon thumbnail = null;
					
					if(image.getIconWidth() > picture.getWidth()){       
						thumbnail = new ImageIcon(image.getImage().getScaledInstance(picture.getWidth(), -1, Image.SCALE_DEFAULT));  
					}
					else thumbnail = image;
					
					picture.setIcon(thumbnail);
										
				}
				
			}).start();
			
			return fileName;
		
		}
		
		return null;
		
	}
	
	public void closeFrame() {
		
		String title = "Sair da atualização de Funcionário";
		String message = "Deseja realmente sair?\nO funcionário não será atualizado.";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
			frame.dispose();
			dataBase.close();			
		}
		
	}
	
	public boolean clear() {
		
		String title = "Limpar os Dados Inseridos";
		String message = "Deseja realmente limpar os dados inseridos?";
		
		int response = ShowMessage.questionMessage(frame, title, message);
		
		return response == JOptionPane.YES_OPTION; 
		
	}
	
	public void addCBO(JComboBox<CBO> cbCBO) {
		
		SwingUtilities.invokeLater(new Runnable(){  
			
			@Override
			public void run() { 
				
				RegisterCBOFrame registerCBO = new RegisterCBOFrame();
				registerCBO.setVisible(true);
				registerCBO.setLocationRelativeTo(frame);
				
				CBO cbo = registerCBO.getCBO(); 
				
				if(cbo != null) {
					cbCBO.insertItemAt(cbo, 0);
				}				
			}
			
		});		
				
	}

	public void updateEmployee(Map<String, Object> data, Employee employee) {
		
		String name = (String) data.get("name");
		Date birth = (Date) data.get("birth");
		int gender = (int) data.get("gender");
		int maritialStatus = (int) data.get("maritial_status");
		String nacionality = (String) data.get("nacionality");
		String birthPlace = (String) data.get("birth_place");
		String cpts = (String) data.get("cpts");
		String cptsCategory = (String) data.get("cpts_category");
		String voter = (String) data.get("voter");
		String driverLicense = (String) data.get("driver_license");
		int driverLicenseCategory = (int) data.get("driver_license_category");
		int schooling = (int) data.get("schooling");
		String reservist = (String) data.get("reservist");
		String reservistCategory = (String) data.get("reservist_category");
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
		String picture = (String) data.get("picture");
		
		if(validateDataEmployee(data)) {
			
			String title = "Atualizar Funcionário";
			String message = "Deseja atualizar o registro do funcionário com estes dados?";
			
			int response = ShowMessage.questionMessage(frame, title, message);
			
			if(response == JOptionPane.YES_OPTION) {
				
				if(employee.getSocialIntegration() != null) {
					
					int socialIntegrationBankingDataId = employee.getSocialIntegration().getBankingData().getId();
					Object socialIntegrationBankData[] = new Object[]{socialIntegrationBank.getId(), socialIntegrationAgency, socialIntegrationBankingDataId};
					dataBase.executeUpdate("UPDATE banking_data SET bank = ?, agency = ? WHERE id = ?", socialIntegrationBankData);
					
					int socialIntegrationId = employee.getSocialIntegration().getId();
					Object socialIntegration[] = new Object[]{new java.sql.Date(cadastreDate.getTime()), socialIntegrationCadastreNumber, socialIntegrationAddress, socialIntegrationId};
					dataBase.executeUpdate("UPDATE social_integration SET cadastre_date = ?, cadastre_number = ?, address = ? WHERE id = ?", socialIntegration);
					
				}
				
				int guaranteeFoundId = employee.getGuaranteeFund().getId();								
				Object guaranteeFound[] = new Object[]{new java.sql.Date(optionDate.getTime()), new java.sql.Date(retractionDate.getTime()), depositaryBank.getId(), guaranteeFoundId};
				dataBase.executeUpdate("UPDATE guarantee_fund SET option_date = ?, retraction_date = ?, depositary_bank = ? WHERE id = ?", guaranteeFound);
				
				int addressId = employee.getAddress().getId();
				Object addressData[] = new Object[]{address, neighborhood, cep.replaceAll("\\.|-", ""), city.getId(), addressId};
				dataBase.executeUpdate("UPDATE address SET address = ?, neighborhood = ?, cep = ?, city = ? WHERE id = ?", addressData);
				
				int jobId = employee.getJob().getId();
				Object jobData[] = new Object[]{new java.sql.Date(admissionDate.getTime()), job.getId(), Double.parseDouble(salary), payment, jobId};
				dataBase.executeUpdate("UPDATE job SET admission_date = ?, cbo = ?, initial_salary = ?, payment = ? WHERE id = ?", jobData);
				
				int bankingDataId = employee.getBankingData().getId();
				Object bankData[] = new Object[]{bank.getId(), agency, account, bankingDataId};
				dataBase.executeUpdate("UPDATE banking_data SET bank = ?, agency = ?, account = ? WHERE id = ?", bankData);
				
				dataBase.executeUpdate("DELETE FROM dependents WHERE employee = ?", employee.getId());
				DefaultTableModel model = (DefaultTableModel) dependents.getModel();
				for(int i = 0; i < dependents.getRowCount(); ++i) {
					
					String n = (String) model.getValueAt(i, 0);
					String r = (String) model.getValueAt(i, 1);
					Object d =  model.getValueAt(i, 2);
					
					if((n == null || n.isEmpty()) && (r == null || r.isEmpty()) && (d == null)) continue;
					if(d instanceof String) continue;
					
					java.sql.Date birthWeddinDate = (d != null) ? new java.sql.Date(((Date) d).getTime()) : null;
					
					String sql = "INSERT INTO dependents (employee, name, relationship, birth_wedding_date) VALUES (?, ?, ?, ?)";
					Object dependentData[] = new Object[]{employee.getId(), n, r, birthWeddinDate};
					dataBase.executeUpdate(sql, dependentData);
					
				}
				
				java.sql.Date birthDate = (birth != null) ? new java.sql.Date(birth.getTime()) : null;
				voter = voter.replaceAll(" ", "");
				phone = phone.replaceAll(" |\\(|\\)|-", "");
				cellphone = cellphone.replaceAll(" |\\(|\\)|-", "");
				if(picture == null || picture.isEmpty()) picture = null;
				
				Object employeeData[] = new Object[]{name, birthDate, gender, maritialStatus, nacionality, birthPlace, cpts, cptsCategory, 
						voter, driverLicense, driverLicenseCategory, schooling, reservist, reservistCategory, phone, cellphone, picture, registrationCnpj.getId(), employee.getId()};
				dataBase.executeUpdate("UPDATE employee SET name = ?, birth = ?, gender = ?, marital_status = ?, nacionality = ?, birth_place = ?, cpts = ?, cpts_category = ?, "
						+ "voter = ?, driver_license = ?, driver_license_category = ?, schooling = ?, reservist = ?, reservist_category = ?, phone = ?, cellphone = ?, picture = ?, register_cnpj = ? "
						+ "WHERE id = ?", 
						employeeData);
								
			}
			
			ShowMessage.successMessage(frame, "Funcionário atualizado", "Funcionário atualizado com sucesso");
			
		}		
		
	}
	
	public boolean validateDataEmployee(Map<String, Object> data) {
		
		boolean flag = false;
		String label = "";
		
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
		String address = (String) data.get("address");
		String neighborhood = (String) data.get("neighborhood");
		String cep = (String) data.get("cep");
		City city = (City) data.get("city");
		String phone = (String) data.get("phone");
		Date admissionDate = (Date) data.get("admission_date");
		CBO job = (CBO) data.get("job");
		CNPJ registrationCnpj = (CNPJ) data.get("registration_cnpj");
		String salary = (String) data.get("salary");
		int payment = (int) data.get("payment");
		Bank bank = (Bank) data.get("bank");
		String agency = (String) data.get("agency");
		String account = (String) data.get("account");
		Date optionDate = (Date) data.get("option_date");
		Bank depositaryBank = (Bank) data.get("depositary_bank");
		Date cadastreDate = (Date) data.get("cadastre_date");
		String socialIntegrationCadastreNumber = (String) data.get("social_integration_cadastre_number");
		Bank socialIntegrationBank = (Bank) data.get("social_integration_bank");
		JTable dependents = (JTable) data.get("dependents");
						
		Matcher matcherCpf = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}").matcher(cpf);
		Matcher matcherRg = Pattern.compile("\\d{2}\\.\\d{3}\\.\\d{3}-\\w{1}").matcher(rg);
		Matcher matcherVoter = Pattern.compile("\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}").matcher(voter);
		Matcher matcherDriverLicense = Pattern.compile("\\d{11}").matcher(driverLicense);
		Matcher matcherCep = Pattern.compile("\\d{2}\\.\\d{3}-\\d{3}").matcher(cep);
		Matcher matcherPhone = Pattern.compile("\\(\\d{2}\\)\\s\\d{4}-\\d{4}").matcher(phone);
		
		if(name == null || name.isEmpty()) label = "Nome do Funcionário";
		else if(birth == null) label = "Data de Nascimento";
		else if(gender == -1) label = "Sexo";
		else if(maritialStatus == -1) label = "Estado Civil";
		else if(nacionality == null || nacionality.isEmpty()) label = "Nacionalidade";
		else if(birthPlace == null || birthPlace.isEmpty()) label = "Local de Nascimento";
		else if(rg == null || rg.isEmpty() || !matcherRg.find()) label = "RG";
		else if(cpf == null || cpf.isEmpty() || !matcherCpf.find()) label = "CPF";
		else if(cpts == null || cpts.isEmpty()) label = "CPTS";
		else if(cptsCategory == null || cptsCategory.isEmpty()) label = "Categoria do CPTS";
		else if(voter == null || voter.isEmpty() || !matcherVoter.find()) label = "Título de Eleitor";
		else if(driverLicense != null && matcherDriverLicense.matches() && driverLicenseCategory == -1) {
			label = "Categoria da Habilitação";
		}
		else if(matcherDriverLicense.reset() != null && driverLicenseCategory != -1 && (driverLicense == null || !matcherDriverLicense.matches())) {
			label = "Carteira de Habilitação";
		}
		else if(schooling == -1) label = "Escolaridade";
		else if(gender == Employee.MALE && (reservist == null || reservist.isEmpty())) label = "Carteira de Reservista";
		else if(address == null || address.isEmpty()) label = "Endereço";
		else if(neighborhood == null || neighborhood.isEmpty()) label = "Bairro";
		else if(cep == null || cep.isEmpty() || !matcherCep.find()) label = "CEP";
		else if(city == null) label = "Cidade";
		else if(phone == null || phone.isEmpty() || !matcherPhone.find()) label = "Telefone";
		else if(admissionDate == null) label = "Data de Admissão";
		else if(job == null) label = "Natureza do Cargo";
		else if(registrationCnpj == null) label = "CNPJ de Registro";
		else if(salary == null || salary.isEmpty()) label = "Salário Inicial";
		else if(payment == -1) label = "Forma de Pagamento";
		else if(bank == null) label = "Banco";
		else if(agency == null || agency.isEmpty()) label = "Agência";
		else if(account == null || account.isEmpty()) label = "Conta";
		else if(optionDate == null) label = "Data de Opção";
		else if(depositaryBank == null) label = "Banco Depositário";
		else if((cadastreDate != null || socialIntegrationBank != null || (socialIntegrationCadastreNumber != null && !socialIntegrationCadastreNumber.isEmpty())) &&
				(cadastreDate == null || socialIntegrationBank == null || socialIntegrationCadastreNumber == null || socialIntegrationCadastreNumber.isEmpty())) {
			label = "Dados do Programa de Inclusão social";
		}
		else flag = true;		
		
		DefaultTableModel model = (DefaultTableModel) dependents.getModel();
		for(int i = 0; i < model.getRowCount(); ++i) {
			String dependentName = (String) model.getValueAt(i, 0); 
			String relationship = (String) model.getValueAt(i, 1);
			Object date = model.getValueAt(i, 2);
						
			if((dependentName == null || dependentName.isEmpty()) || (relationship == null || relationship.isEmpty()) || date == null) {
				if(!((dependentName == null || dependentName.isEmpty()) && (relationship == null || relationship.isEmpty()) && (date == null || !(date instanceof Date)))) {
					
					label = "Dados dos Dependentes";
					flag = false;
					break;
				}
			}	
			
		}
						
		if(!flag) {
			String title = "Erro ao registrar funcionário";
			String message = "Por favor verifique o seguinte campo para registro do funcionário:\n" + label;
			ShowMessage.errorMessage(frame, title, message);
		}		
		
		return flag;		
	}

}
