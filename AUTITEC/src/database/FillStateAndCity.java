package database;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import core.City;
import core.State;

/**
 * Responsável pelo preenchimento e gerenciamento de JComboBox para estados e cidades registrados na base de dados.
 *
 */
public class FillStateAndCity {
	
	private JComboBox<State> states;
	private JComboBox<City> cities;
	
	private DataBase dataBase;
	
	/**
	 * Cria objeto para gerenciamento de combobox de estados e cidades
	 * 
	 * @param states ComboBox para representar os estados
	 * @param cities Combobox para representar as cidades
	 */
	public FillStateAndCity(JComboBox<State> states, JComboBox<City> cities) {
		
		this.states = states;
		this.cities = cities;
		
		dataBase = new DataBase();
		dataBase.connect();
		
		setStates();
		
		setListeners();		
		
	}
	
	/**
	 * Define no combobox todos os estados registrados no banco
	 */
	private void setStates() {		
				
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM state");
			
			while(resultSet.next()) {
				
				State state = new State(resultSet.getInt("id"), resultSet.getString("name"));
				states.addItem(state);
				
			}
			
			states.setSelectedIndex(-1);
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Ação de troca de valor do combobox de estados para preenchimento do combobox de cidades
	 */
	private void setListeners() {
		
		ItemListener comboboxListener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == 1) setCities((State) states.getSelectedItem());
			}
		};
		
		states.addItemListener(comboboxListener);
		
	}
	
	/**
	 * Define no combobox todos as cidades registrados no banco para o estado
	 * 
	 * @param state O estado selecionado no combobox
	 */
	private void setCities(State state) {
		
		
		cities.removeAllItems();
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM CITY WHERE city.state = ?", new Object[]{state.getId()});
			
			while(resultSet.next()) {
				
				City city = new City(resultSet.getInt("id"), resultSet.getString("name"), state);
				cities.addItem(city);
				
			}
			
			cities.setSelectedIndex(-1);
			cities.setEnabled(true);
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
	}	

}
