package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import model.ClientProperties;
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
	    dataBase.close();
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

	public List<ClientProperties> getAllActivedClientsProperties() {
	    String query = "SELECT *FROM client_properties WHERE isActive = true";
	    List<ClientProperties> cpList = new ArrayList<ClientProperties>();
	    try(ResultSet rs = dataBase.executeQuery(query)){
	    	while(rs.next()) {
	    		ClientProperties cp = new ClientProperties();
	    		Date entryDate = rs.getDate("entry_date");
	    		String fiscalNote = rs.getString("fiscal_note");
	    		int id = rs.getInt("id");
	    		cp.setEntryDate(entryDate);
	    		cp.setFiscalNote(fiscalNote);
	    		cp.setId(id);
	    		List<ClientPropertiesMaterial> cpmList = getClientPropertiesMaterialByPropertiesID(id, cp);
	    		cp.setPropertiesList(cpmList);
	    		cpList.add(cp);
	    	}
	    	dataBase.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
	    dataBase.close();
		return cpList;
    }

	private List<ClientPropertiesMaterial> getClientPropertiesMaterialByPropertiesID(int id, ClientProperties cp) {
	    String query = "SELECT *FROM client_properties_material WHERE client_properties = ? AND is_in_loco = true";
	    List<ClientPropertiesMaterial> cpmList = new ArrayList<ClientPropertiesMaterial>();
	    try(ResultSet rs = dataBase.executeQuery(query, id)){
	    	while(rs.next()) {
	    		ClientPropertiesMaterial cpm = new ClientPropertiesMaterial();
	    		int ammount = rs.getInt("ammount");
	    		int materialID = rs.getInt("material");
	    		int clientPropestiesMaterialId = rs.getInt("id");
	    		Material material = new MaterialDAO().getMaterialById(materialID);
	    		if(material != null)cpm.setMaterial(material);
	    		cpm.setAmmount(ammount);
	    		cpm.setClientProrpeties(cp);
	    		cpm.setId(clientPropestiesMaterialId);
	    		cpmList.add(cpm);
	    	}
	    	dataBase.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
	    dataBase.close();
	    return cpmList;
    }	
}
