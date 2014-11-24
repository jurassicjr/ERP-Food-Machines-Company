package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import core.Bank;

/**
 * Responsável pelo preenchimento dos dados bancários recuperados do banco de dados em um combobox
 */
public class FillBanks {
	
	private JComboBox<Bank> banks;
	private DataBase dataBase;
	
	/**
	 * Cria o objeto para preencher e gerenciar os bancos contidos no combobox
	 * 
	 * @param banks O ComboBox responsável pelos bancos presentes no banco de dados
	 */
	public FillBanks(JComboBox<Bank> banks) {
		
		this.banks = banks;
		
		dataBase = new DataBase();
		dataBase.connect();
		
		setBanks();
		
	}
	
	/**
	 * Recupera do banco e define no combobox os valores dos bancos
	 */
	private void setBanks() {
		
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
