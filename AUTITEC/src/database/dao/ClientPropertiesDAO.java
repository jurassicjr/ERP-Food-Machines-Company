package database.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import model.ClientPropertiesMaterial;
import model.Material;
import database.DataBase;

public class ClientPropertiesDAO {

	
	private DataBase dataBase;

	public ClientPropertiesDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(Map<String, Object> map) {
	    Date entryDate = (Date) map.get("entryDate");
	    String fiscalNote = (String) map.get("fiscalNote");
	    @SuppressWarnings("unchecked")
        List<ClientPropertiesMaterial> cpmList = ((List<ClientPropertiesMaterial>) map.get("cpmList"));
	    java.sql.Date entryDateSQL = new java.sql.Date(entryDate.getTime());
	    int clientPropertieID = dataBase.getAutoIncrementValue("client_properties");
	    String query = "INSERT INTO client_properties(entry_date, fiscal_note) VALUES (?,?)";
	    Object[] data = new Object[] {entryDateSQL, fiscalNote};
	    dataBase.executeUpdate(query, data);
	    registerClientPropertiesMaterial(clientPropertieID, cpmList);
	    }

	private void registerClientPropertiesMaterial(int clientPropertieID, List<ClientPropertiesMaterial> cpmList) {
	    String query = "INSERT INTO client_properties_material(ammount, material, client_properties) VALUES (?,?,?)";
		for (ClientPropertiesMaterial cpm : cpmList) { 
	        int ammount = cpm.getAmmount();
	        Material m = cpm.getMaterial();
	        int materialID = m.getId();
	        Object[] data = new Object[] {ammount, materialID, clientPropertieID};
	        dataBase.executeUpdate(query, data);
        }	
    }	
}
