package rh.controller;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Bank;
import model.CBO;
import model.City;
import model.State;
import rh.view.RegisterEmployeeFrame;
import util.ShowMessage;
import database.DataBase;

/**
 * Classe controladora do frame de registro de funcionários
 */
public class RegisterEmployeeController {
	
	private RegisterEmployeeFrame frame;
	private DataBase dataBase; 
	
	/**
	 * Cria o registrador para o frame de registro de funcionários
	 * 
	 * @param frame O frame a ser controlado
	 */
	public RegisterEmployeeController(RegisterEmployeeFrame frame) {
		
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
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM CITY WHERE city.state = ?", new Object[]{state.getId()});
			
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
	
	/**
	 * Fecha o frame, encerrando sua execução
	 */
	public void closeFrame() {
		
		String title = "Sair do Registro de Funcionário";
		String message = "Deseja realmente sair?\nO funcionário não será registrado.";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
			frame.dispose();
			dataBase.close();			
		}
		
	}
	
	/**
	 * Registra o Funcionário no Banco de Dados
	 */
	public void registerEmployee(Map<String, Object> data) {
				
		if(validateDataEmployee(data)) {
			
			String title = "Registro Funcionário";
			String message = "Deseja registrar o funcionário com estes dados?";
			
			int response = ShowMessage.questionMessage(frame, title, message);
			
			if(response == JOptionPane.YES_OPTION) {
				
			}
			
		}				
		
	}
	
	/**
	 * Verifica se os dados inseridos para o registro do usuário são válidos
	 * 
	 * @return true se os dados são válidos e false se alguma das informações é inválida
	 */
	public boolean validateDataEmployee(Map<String, Object> data) {
		
		boolean flag = false;
		String label = "";
		
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
		String address = (String) data.get("address");
		String neighborhood = (String) data.get("neighborhood");
		String cep = (String) data.get("cep");
		City city = (City) data.get("city");
		String phone = (String) data.get("phone");
		Date admissionDate = (Date) data.get("admission_date");
		CBO job = (CBO) data.get("job");
		String salary = (String) data.get("salary");
		String payment = (String) data.get("payment");
		Bank bank = (Bank) data.get("bank");
		String agency = (String) data.get("agency");
		String account = (String) data.get("account");
		Date optionDate = (Date) data.get("option_date");
		Bank depositaryBank = (Bank) data.get("depositary_bank");
		Date cadastreDate = (Date) data.get("cadastre_date");
		String socialIntegrationCadastreNumber = (String) data.get("social_integration_cadastre_number");
		String socialIntegrationBank = (String) data.get("social_integration_bank");
		String socialIntegrationAgency = (String) data.get("social_integration_agency");
		String socialIntegrationAddress = (String) data.get("social_integration_address");
		
		Matcher matcherCpf = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}").matcher(cpf);
		Matcher matcherRg = Pattern.compile("\\d{2}\\.\\d{3}\\.\\d{3}-\\w{1}").matcher(rg);
		Matcher matcherVoter = Pattern.compile("\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}").matcher(voter);
		Matcher matcherDriverLicense = Pattern.compile("\\d{11}").matcher(driverLicense);
		Matcher matcherCep = Pattern.compile("\\d{2}\\.\\d{3}-\\d{3}").matcher(cep);
		Matcher matcherPhone = Pattern.compile("\\(\\d{2}\\)\\s\\d{4}-\\d{4}").matcher(phone);
		
		if(name == null || name.isEmpty()) label = "Nome do Funcionário";
		else if(birth == null) label = "Data de Nascimento";
		else if(gender == null || gender.isEmpty()) label = "Sexo";
		else if(maritialStatus == null || maritialStatus.isEmpty()) label = "Estado Civil";
		else if(nacionality == null || nacionality.isEmpty()) label = "Nacionalidade";
		else if(birthPlace == null || birthPlace.isEmpty()) label = "Local de Nascimento";
		else if(rg == null || rg.isEmpty() || !matcherRg.find()) label = "RG";
		else if(cpf == null || cpf.isEmpty() || !matcherCpf.find()) label = "CPF";
		else if(cpts == null || cpts.isEmpty()) label = "CPTS";
		else if(cptsCategory == null || cptsCategory.isEmpty()) label = "Categoria do CPTS";
		else if(voter == null || voter.isEmpty() || !matcherVoter.find()) label = "Título de Eleitor";
		else if(matcherDriverLicense.find()) {
			if(driverLicenseCategory == null || driverLicenseCategory.isEmpty()) label = "Categoria da Habilitação";
		}
		else if(schooling == null || schooling.isEmpty()) label = "Escolaridade";
		else if(gender.toUpperCase().equals("MASCULINO")) {
			if(reservist == null || reservist.isEmpty()) label = "Carteira de Reservista";
		}
		else if(address == null || address.isEmpty()) label = "Endereço";
		else if(neighborhood == null || neighborhood.isEmpty()) label = "Bairro";
		else if(cep == null || cep.isEmpty() || !matcherCep.find()) label = "CEP";
		else if(city == null) label = "Cidade";
		else if(phone == null || phone.isEmpty() || !matcherPhone.find()) label = "Telefone";
		else if(admissionDate == null) label = "Data de Admissão";
		else if(job == null) label = "Natureza do Cargo";
		else if(salary == null || salary.isEmpty()) label = "Salário Inicial";
		else if(payment == null || payment.isEmpty()) label = "Forma de Pagamento";
		else if(bank == null) label = "Banco";
		else if(agency == null || agency.isEmpty()) label = "Agência";
		else if(account == null || account.isEmpty()) label = "Conta";
		else if(optionDate == null) label = "Data de Opção";
		else if(depositaryBank == null) label = "Banco Depositário";
		else if(cadastreDate != null || (socialIntegrationBank != null && !socialIntegrationBank.isEmpty()) || 
				(socialIntegrationAgency != null && !socialIntegrationAgency.isEmpty()) || (socialIntegrationAddress != null && !socialIntegrationAddress.isEmpty()) ||
				(socialIntegrationCadastreNumber != null && !socialIntegrationCadastreNumber.isEmpty())) {
			label = "Dados do Programa de Inclusão social";
		}		
		else flag = true;
		
		if(!flag) {
			String title = "Erro ao registrar funcionário";
			String message = "Por favor verifique o seguinte campo para registro do funcionário:\n" + label;
			ShowMessage.errorMessage(frame, title, message);
		}
		
		
		return flag;		
	}
	
	public void loadProfilePicture() {
		System.out.println("teste");
	}
	
}
