package rh.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.City;
import model.Client;
import model.ExternalSatisfactionResearch;
import model.State;
import util.ShowMessage;
import database.DataBase;
import database.dao.ExternalSatisfactionResearchDAO;

public class ExternalSatisfactionResearchController {

	
	private JFrame frame;
	private DataBase dataBase;

	public ExternalSatisfactionResearchController(JFrame frame) {
		this.frame = frame;
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar essa janela ?\n Os dados não salvos serão perdidos!");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}

	public void register(ExternalSatisfactionResearch esr) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    int comunication = esr.getComunication();
	    int courtesy = esr.getCourtesy();
	    int credibility = esr.getCredibility();
	    String criticismsAndSuggestions = esr.getCriticismsAndSuggestions();
	    int effectiveness = esr.getEffectiviness();
	    int general = esr.getGeneral();
	    int quality =esr.getQuality();
	    int tecnicalQuality = esr.getTecnicalQuality();
	    Date researchDate = esr.getResearchDate();
	    Client c = esr.getClient();
	   
	    map.put("comunication", comunication);
	    map.put("courtesy", courtesy);
	    map.put("credibility", credibility);
	    map.put("criticismsAndSuggestions", criticismsAndSuggestions);
	    map.put("effectiveness", effectiveness);
	    map.put("general", general);
	    map.put("quality", quality);
	    map.put("tecnicalQuality", tecnicalQuality);
	    map.put("researchDate", researchDate);
	    map.put("client", c);
	    
	    new ExternalSatisfactionResearchDAO().register(map);
    }

	public void fillClient(JComboBox<Client> cboClient) {
	    String sql = "SELECT * FROM client order by name";
	    
	    DefaultComboBoxModel<Client>  clients = new DefaultComboBoxModel<Client>();
	    cboClient.removeAllItems();
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
				clients.addElement(client);
			}
			cboClient.setModel(clients);
		dataBase.close();
		} catch (SQLException e) {
	        e.printStackTrace();
        }
    }
}
