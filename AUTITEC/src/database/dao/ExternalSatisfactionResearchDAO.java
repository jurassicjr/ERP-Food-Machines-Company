package database.dao;

import java.util.Date;
import java.util.Map;

import model.Client;
import database.DataBase;

public class ExternalSatisfactionResearchDAO {

	private DataBase dataBase;

	public ExternalSatisfactionResearchDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(Map<String, Object> map) {
	    int comunication = (int) map.get("comunication");
	    int courtesy = (int) map.get("courtesy");
	    int effectiveness = (int) map.get("effectiveness");
	    int credibility = (int) map.get("credibility");
	    int quality = (int) map.get("quality");
	    int tecnicalQuality = (int) map.get("tecnicalQuality");
	    Date date = (Date) map.get("researchDate");
	    int general = (int) map.get("general");
	    java.sql.Date researchDate = new java.sql.Date(date.getTime());
	    String criticismsAndSuggestions = (String) map.get("criticismsAndSuggestions");
	    Client c = (Client) map.get("client");
	    int clientID =  c.getId();
	    
	    Object[] obj = new Object[] {comunication, courtesy, effectiveness, credibility, quality, tecnicalQuality, general, researchDate, criticismsAndSuggestions, clientID};
	    String sql = "INSERT INTO external_satisfaction_research(comunication, courtesy, effectiveness, credibility, quality, tecnical_quality, general, research_date, criticisms_and_suggestions, client) VALUES (?,?,?,?,?,?,?,?,?,?)";
		dataBase.executeUpdate(sql, obj);
	    dataBase.close();
    }
}
