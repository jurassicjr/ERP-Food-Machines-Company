package sales.controller;

import java.util.HashMap;
import java.util.Map;

import model.Client;
import database.dao.ClientDAO;

public class ClientRegisterController extends SalesController{
	
	private ClientDAO cDAO;

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
	    cDAO.persistRegister(map);
    }

}
