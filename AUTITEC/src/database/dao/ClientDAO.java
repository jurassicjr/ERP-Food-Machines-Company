package database.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.City;
import model.Client;
import model.State;
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
		dataBase.close();
	}
	public List<Client> getAllClients() {
	    String sql = "SELECT * FROM client order by name";
	    List<Client> cList = new ArrayList<Client>();
	    	    
		try(ResultSet rs = dataBase.executeQuery(sql)){
			while(rs.next()) {
				
				Client client  = new Client();
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setCompanyNAme(rs.getString("companyname"));
				client.setStreet(rs.getString("street"));
				client.setBirthDate(rs.getDate("birthdate"));
				client.setNeighborhood(rs.getString("neighborhood"));
				client.setState(new State(rs.getInt("state"),""));
				client.setCity(new City(rs.getInt("city"),"",client.getState()));
				client.setCep(rs.getString("cep"));
				client.setPhone(rs.getString("phone"));
				client.setEmail(rs.getString("email"));
				client.setCompanyNAme(rs.getString("companyname"));
				client.setCpf(rs.getString("cpf"));
				client.setCnpj(rs.getString("cnpj"));
				client.setStateInscrition(rs.getString("ie"));
				client.setSex(rs.getString("sex"));
				client.setContactName(rs.getString("companycontactname"));
				client.setRg(rs.getString("rg"));
				cList.add(client);
			}
				dataBase.close();
		} catch (SQLException e) {
	        e.printStackTrace();
        }
		dataBase.close();
		return cList;
    }
	public Client getClientByID(int id) {
		String sql = "SELECT * FROM client WHERE id = ?";

		try (ResultSet rs = dataBase.executeQuery(sql, id)) {
			if (rs.next()) {

				Client client = new Client();
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setCompanyNAme(rs.getString("companyname"));
				client.setStreet(rs.getString("street"));
				client.setBirthDate(rs.getDate("birthdate"));
				client.setNeighborhood(rs.getString("neighborhood"));
				client.setState(new State(rs.getInt("state"), ""));
				client.setCity(new City(rs.getInt("city"), "", client.getState()));
				client.setCep(rs.getString("cep"));
				client.setPhone(rs.getString("phone"));
				client.setEmail(rs.getString("email"));
				client.setCompanyNAme(rs.getString("companyname"));
				client.setCpf(rs.getString("cpf"));
				client.setCnpj(rs.getString("cnpj"));
				client.setStateInscrition(rs.getString("ie"));
				client.setSex(rs.getString("sex"));
				client.setContactName(rs.getString("companycontactname"));
				client.setRg(rs.getString("rg"));
				return client;
			}
			dataBase.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dataBase.close();
		return null;
    }
}
