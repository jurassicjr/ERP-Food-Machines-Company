package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Kit;
import database.DataBase;

public class KitDAO {

	private DataBase dataBase;

	public KitDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public Kit getKitByID(int kitID) {
	    String query = "SELECT * FROM kit WHERE id = ?";
	    try(ResultSet rs  = dataBase.executeQuery(query, kitID)){
	    	if(rs.next()) {
	    		String name = rs.getString("kit_name");
	    		String description = rs.getString("descrition");
	    		Kit kit = new Kit();
	    		kit.setId(kitID);
	    		kit.setDescription(description);
	    		kit.setName(name);
	    		return kit;
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return null;
    }
}

