package database.dao;

import java.util.Date;
import java.util.Map;

import database.DataBase;

public class ApprovalOfSupplierDAO {

	private DataBase dataBase;

	public ApprovalOfSupplierDAO() {
	    dataBase = new DataBase();
	    dataBase.connect();
    }
	public void persistRegister(Map<String, Object> map) {
		Date date = (Date) map.get("qualificationDate");
		java.sql.Date qualificationDate = new java.sql.Date(date.getTime());
		int supplier = (int) map.get("supplier");
		String qualificationType = (String) map.get("qualificationType");
		String material = (String) map.get("material");
		String service = (String) map.get("service");
		int qualificationSystem = (int) map.get("qualitySystem");
		String qualificationEvidence = (String) map.get("qualityEvidence");
		String qualificationObservation = (String) map.get("qualityObservation");
		int recordOfDelivering = (int) map.get("recordOfDelivering");
		String recordOfDeliveringEvidence = (String) map.get("recordOfDeliveringEvidence");
		String recordOfDeliveringObservation = (String) map.get("recordOfDeliveringObservation");
		int supplierCapacity = (int) map.get("supplierCapacity");
		String supplierCapacityEvidence = (String) map.get("supplierCapacityEvidence");
		String supplierCapacityObservation = (String) map.get("supplierCapacityObservation");
		String descrition = (String) map.get("descrition");
		int priceRate = (int) map.get("priceRate");
		int qualityRate = (int) map.get("qualityRate");
		int serviceRate = (int) map.get("Service");
		boolean approved = (boolean) map.get("approved");
		
		
	}
}
