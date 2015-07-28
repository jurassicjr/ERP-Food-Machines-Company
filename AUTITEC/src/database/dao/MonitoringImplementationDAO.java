package database.dao;

import java.util.Date;
import java.util.Map;

import model.Employee;
import model.Rnc;
import database.DataBase;

public class MonitoringImplementationDAO {
	
	private DataBase dataBase;

	public MonitoringImplementationDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(Map<String, Object> map) {
	    Rnc rnc = (Rnc) map.get("rnc");
	    int rncID = rnc.getId();
	    Date date = (Date) map.get("monitoringDate");
	    java.sql.Date monitoringDate = new java.sql.Date(date.getTime());
	    String monitoringDescription = (String) map.get("monitoringDescription");
	    Employee approvedBy = (Employee) map.get("approvedBy");
	    int approveByID = approvedBy.getId();
	    
	    String query = "INSERT INTO monitoring_implementation(non_compliance, monitoring_date, monitoring_description, approved_by) VALUES(?,?,?,?)";
	    dataBase.executeUpdate(query, new Object[] {rncID, monitoringDate, monitoringDescription, approveByID});
	    
	    String updateQuery="UPDATE rnc SET deadline = ?, isActive = ? WHERE id = ?";
	    java.sql.Date deadLineSql = new java.sql.Date(rnc.getDeadLine().getTime());
	    dataBase.executeUpdate(updateQuery, new Object[] {deadLineSql	, rnc.getIsActive(), rncID});
	    dataBase.close();
    }

}
