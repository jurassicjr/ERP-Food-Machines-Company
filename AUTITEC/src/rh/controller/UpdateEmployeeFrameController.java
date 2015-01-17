package rh.controller;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import rh.view.UpdateEmployeeFrame;
import model.Bank;
import model.CBO;
import model.CNPJ;
import model.City;
import model.State;
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

}
