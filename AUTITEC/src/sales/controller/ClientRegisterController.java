package sales.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;

import model.City;
import model.Client;
import model.State;
import database.DataBase;
import database.dao.ClientDAO;

public class ClientRegisterController extends SalesController{
	
	private ClientDAO cDAO;
	private DataBase dataBase; 
	
	public ClientRegisterController() {
		cDAO = new ClientDAO();
    }

	public void doClienteRegistration(Client client) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("name", client.getName());
	    map.put("street", client.getStreet());
	    map.put("neighborhood", client.getNeighborhood());
	    map.put("city", client.getCity().getId());
	    map.put("state", client.getState().getId());
	    map.put("cep", client.getCep());
	    map.put("phone", client.getPhone());
	    map.put("email", client.getEmail());
	    map.put("company_name", client.getCompanyNAme());
	    map.put("cpf",client.getCpf());
	    map.put("rg",client.getRg());
	    map.put("cnpj",client.getCnpj());
	    map.put("ie",client.getIe());
	    map.put("contact",client.getContactName());
	    map.put("sex",client.getSex());
	    map.put("birth_Date",client.getBirthDate());
	    
	    
	    cDAO.persistRegister(map);
    }
	
}

	


