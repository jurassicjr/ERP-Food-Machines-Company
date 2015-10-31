package financial.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.Bank;
import model.BankAccount;
import util.ShowMessage;
import database.DataBase;
import database.dao.registerBankAccountDAO;
import financial.view.RegisterBankAccountFrame;

public class RegisterBankAccountController {

	
	private RegisterBankAccountFrame frame;
	private DataBase dataBase;

	public RegisterBankAccountController(RegisterBankAccountFrame frame) {
		this.frame = frame;
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Encerrar", "Deseja realmente fechar o registro de contas bancárias? ");
		if(i == JOptionPane.YES_OPTION) {
			frame.dispose();
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

	public void register(BankAccount ba) {
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    map.put("account", ba.getAccount());
	    map.put("bank", ba.getBank());
	    map.put("SafeAccount", ba.getSafeMoneyAccount());
	    map.put("inicialValue", ba.getInicialValue());
	    map.put("agency", ba.getAgency());
	    
	    new registerBankAccountDAO().register(map);
    }
}
