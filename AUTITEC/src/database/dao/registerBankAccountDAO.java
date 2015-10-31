package database.dao;

import java.util.HashMap;

import model.Bank;
import database.DataBase;

public class registerBankAccountDAO {
	
	private DataBase dataBase;

	public registerBankAccountDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void register(HashMap<String, Object> map) {
		String account = (String) map.get("account");
		String agency = (String) map.get("agency");
		String safeMoneyAccount = (String) map.get("SafeAccount");
		double inicialValue = (double) map.get("inicialValue");
		Bank bank = (Bank) map.get("bank");
		int bankID = bank.getId();
		
		String query = "INSERT INTO bank_account(account, agency, safe_account, inicial_value, bank) VALUE(?,?,?,?,?)";
		Object[] obj = new Object[] {account, agency, safeMoneyAccount, inicialValue, bankID};
		
		dataBase.executeUpdate(query, obj);
	}

}
