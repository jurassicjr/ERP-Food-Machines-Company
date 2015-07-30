package database.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import model.ClientProperties;
import model.ClientPropertiesMaterial;
import database.DataBase;

public class ClientPropertiesOutputDAO {

	
	private DataBase dataBase;

	public ClientPropertiesOutputDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(Map<String, Object> map, boolean propertiesEnd) {
		String motive = (String) map.get("motive");
		String exitFiscalNote = (String) map.get("exitFiscalNote");
		List<ClientPropertiesMaterial> cpmList = ((List<ClientPropertiesMaterial>) map.get("cpm"));
		Date exitDate = (Date) map.get("exitDate");
		java.sql.Date exitDateSql = new java.sql.Date(exitDate.getTime());
		ClientProperties cp = (ClientProperties) map.get("clientProperties");
		int cpID = cp.getId();
		Object[] insert = new Object[] {motive, exitFiscalNote, exitDateSql, cpID};
		String query= "INSERT INTO client_properties_output(motive, exit_fiscal_note, exit_date, client_property) VALUES (?,?,?,?)";
		dataBase.executeUpdate(query, insert);
		updateClientPropertiesMaterial(cpmList, cpID, exitFiscalNote);
		if(propertiesEnd)inativeProperties(cpID);
		dataBase.close();
		
    }

	private void inativeProperties(int cpID) {
	    String query = "UPDATE client_properties SET isActive = false WHERE id = ?";
	    dataBase.executeUpdate(query, cpID);
    }

	private void updateClientPropertiesMaterial(List<ClientPropertiesMaterial> cpmList, int cpID, String exitFiscalNote) {
	    String query = "UPDATE client_properties_material SET is_in_loco = false, exit_fiscal_note = ? WHERE client_properties = ? AND id = ?";
	    for (ClientPropertiesMaterial cpm : cpmList) {
	    	int id = cpm.getId();
	    	dataBase.executeUpdate(query, new Object[] {exitFiscalNote, cpID, id});
        }
   }
	
}
