package database.dao;

import java.util.Map;

import database.DataBase;

public class ClientDAO {

	private DataBase dataBase;

	public ClientDAO() {
	    dataBase = new DataBase();
	    dataBase.connect();
    }
	public void persistRegister(Map<String, Object> map) {
		String name = (String) map.get("name");
		String street = (String) map.get("street");
		String neighborhood = (String) map.get("neighborhood");
		int city = (int) map.get("city");
		int state = (int) map.get("state");
		String CEP = (String) map.get("cep");
		String phone = (String) map.get("phone");
		String email = (String) map.get("email");
		
		Object[] persist = new Object[] {name, street, neighborhood, city, state, CEP, phone, email};
		String sql = "INSERT INTO client(name, street, neighborhood, city, state, cep, phone, email) VALUES(?, ?, ? ,? , ?, ?, ?, ?)";
		dataBase.executeUpdate(sql, persist);
	}
}
