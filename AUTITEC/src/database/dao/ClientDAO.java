package database.dao;

import java.sql.Date;
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
		String razao = (String) map.get("company_name");
		String cpf = (String) map.get("cpf");
		String cnpj = (String) map.get("cnpj");
		String ie = (String) map.get("ie");
		String sex = (String) map.get("sex");
		String contact = (String) map.get("contact");
		Date date = (Date) map.get("birth_Date");
		String rg = (String) map.get("rg");
		
		
		Object[] persist = new Object[] {name, street, neighborhood, city, state, CEP, phone, email,
				razao,cpf,cnpj,ie,sex,contact,date,rg};
		String sql = "INSERT INTO client(name, street, neighborhood, city, state, cep, phone, email,"
				+ "companyNAme,cpf,cnpj,ie,sex,companycontactname,birthdate,rg) VALUES(?, ?, ? ,? , ?, ?, ?, ?,?,?,?,?,?,?,?,?)";
		dataBase.executeUpdate(sql, persist);
	}
}
