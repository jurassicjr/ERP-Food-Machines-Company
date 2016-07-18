package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import model.Employee;
import model.Material;
import model.PTC;
import model.PurchaseRequisition;
import model.PurchaseRequisitionAssociation;
import database.DataBase;

public class PurchaseRequisitionDAO {

	private DataBase dataBase;

	public PurchaseRequisitionDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(HashMap<String, Object> map) {
		Employee e = (Employee) map.get("requisitor");
		int requisitorID = e.getId();
		String priority = (String) map.get("priority");
		String requisitionNumber = (String) map.get("requisitionNumber");
		PTC ptc = (PTC) map.get("ptc");
		int ptcId = ptc.getId();
		String justification = (String) map.get("justification");
		Date d = (Date) map.get("date");
		java.sql.Date date = new java.sql.Date(d.getTime());
		int salesRequisitionId = dataBase.getAutoIncrementValue("sales_requisition");
		List<PurchaseRequisitionAssociation> associationList = (List<PurchaseRequisitionAssociation>) map.get("associationList");
		String salesRequisitionSql = "insert into sales_requisition(requisitor, priority, ptc, date, requisition_number, justification) values(?,?,?,?,?,?)";
		Object[] salesRequisitionData = new Object[] {requisitorID, priority, ptcId, date, requisitionNumber, justification};
		dataBase.executeUpdate(salesRequisitionSql, salesRequisitionData);
		
		String associationSql = "insert into sales_requisition_association(material, section, ammount, sales_requisition) values(?,?,?,?)";
		for (PurchaseRequisitionAssociation sra : associationList) {
			Material m = sra.getMaterial();
			int materialId = m.getId();
			String section = sra.getSection();
			double ammount = sra.getAmmount();
			Object[] associationData = new Object[] {materialId, section, ammount, salesRequisitionId};
			dataBase.executeUpdate(associationSql, associationData);
        }
    }

	public List<PurchaseRequisition> getAllRequisition() {
	    String query = "select * from sales_requisition";
	    List<PurchaseRequisition> srList = new ArrayList<PurchaseRequisition>();
	    try(ResultSet rs = dataBase.executeQuery(query)){
	    	while(rs.next()) {	    		
	    		int requisitionId = rs.getInt("id");
	    		int requisitorId = rs.getInt("requisitor");
	    		Employee requisitor = new EmployeeDAO().getEmployeeById(requisitorId);
	    		String priority = rs.getString("priority");
	    		String requisitionNumber = rs.getString("requisition_number");
	    		int ptcId = rs.getInt("ptc");
	    		PTC ptc = new PTCDAO().getPTCById(ptcId);
	    		java.sql.Date d = rs.getDate("date");
	    		Date date = new Date(d.getTime());
	    		int status = rs.getInt("status");
	    		String justification = rs.getString("justification");
	    		List<PurchaseRequisitionAssociation> sraList = new ArrayList<PurchaseRequisitionAssociation>();
	    		String associationSql = "select * from sales_requisition_association where sales_requisition = ?";
	    		try(ResultSet rsa = dataBase.executeQuery(associationSql, requisitionId)){
	    			while(rsa.next()) {
	    				int materialId = rsa.getInt("material");
	    				Material m = new MaterialDAO().getMaterialById(materialId);
	    				double ammount = rsa.getDouble("ammount");
	    				boolean bought = rsa.getBoolean("is_bought");
	    				String section = rsa.getString("section");
	    				int associationId = rsa.getInt("id");
	    				PurchaseRequisitionAssociation sra = new PurchaseRequisitionAssociation(m, ammount, section);
	    				sra.setBought(bought);
	    				sra.setId(associationId);
	    				sraList.add(sra);
	    			}
	    		}
	    		
	    		
	    		PurchaseRequisition sr = new PurchaseRequisition(requisitor, requisitionNumber, date, priority, ptc);
	    		sr.setAssociationList(sraList);
	    		sr.setId(requisitionId);
	    		sr.setStatus(status);
	    		sr.setJustification(justification);
	    		srList.add(sr);
	    	}
	    	return srList;
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return null;
    }

	public List<PurchaseRequisition> getAllOpenRequisition() {
		 String query = "select * from sales_requisition where status != 2 AND status != 4";
		    List<PurchaseRequisition> srList = new ArrayList<PurchaseRequisition>();
		    try(ResultSet rs = dataBase.executeQuery(query)){
		    	while(rs.next()) {	    		
		    		int requisitionId = rs.getInt("id");
		    		int requisitorId = rs.getInt("requisitor");
		    		Employee requisitor = new EmployeeDAO().getEmployeeById(requisitorId);
		    		String priority = rs.getString("priority");
		    		String requisitionNumber = rs.getString("requisition_number");
		    		int ptcId = rs.getInt("ptc");
		    		PTC ptc = new PTCDAO().getPTCById(ptcId);
		    		java.sql.Date d = rs.getDate("date");
		    		Date date = new Date(d.getTime());
		    		int status = rs.getInt("status");
		    		String justification = rs.getString("justification");
		    		List<PurchaseRequisitionAssociation> sraList = new ArrayList<PurchaseRequisitionAssociation>();
		    		String associationSql = "select * from sales_requisition_association where sales_requisition = ?";
		    		try(ResultSet rsa = dataBase.executeQuery(associationSql, requisitionId)){
		    			while(rsa.next()) {
		    				int materialId = rsa.getInt("material");
		    				Material m = new MaterialDAO().getMaterialById(materialId);
		    				double ammount = rsa.getDouble("ammount");
		    				boolean bought = rsa.getBoolean("is_bought");
		    				String section = rsa.getString("section");
		    				int associationId = rsa.getInt("id");
		    				PurchaseRequisitionAssociation sra = new PurchaseRequisitionAssociation(m, ammount, section);
		    				sra.setBought(bought);
		    				sra.setId(associationId);
		    				sraList.add(sra);
		    			}
		    		}
		    		PurchaseRequisition sr = new PurchaseRequisition(requisitor, requisitionNumber, date, priority, ptc);
		    		sr.setAssociationList(sraList);
		    		sr.setId(requisitionId);
		    		sr.setStatus(status);
		    		sr.setJustification(justification);
		    		srList.add(sr);
		    	}
		    	return srList;
		    } catch (SQLException e) {
		        e.printStackTrace();
	        }
			return null;	
	}

	public PurchaseRequisition getRequisitionById(int purchaseRequisitionId) {
		 String query = "select * from sales_requisition where id = ?";
		    
		    try(ResultSet rs = dataBase.executeQuery(query, purchaseRequisitionId)){
		    	if(rs.next()) {	    		
		    		int requisitionId = rs.getInt("id");
		    		int requisitorId = rs.getInt("requisitor");
		    		Employee requisitor = new EmployeeDAO().getEmployeeById(requisitorId);
		    		String priority = rs.getString("priority");
		    		String requisitionNumber = rs.getString("requisition_number");
		    		int ptcId = rs.getInt("ptc");
		    		PTC ptc = new PTCDAO().getPTCById(ptcId);
		    		java.sql.Date d = rs.getDate("date");
		    		Date date = new Date(d.getTime());
		    		int status = rs.getInt("status");
		    		String justification = rs.getString("justification");
		    		List<PurchaseRequisitionAssociation> sraList = new ArrayList<PurchaseRequisitionAssociation>();
		    		String associationSql = "select * from sales_requisition_association where sales_requisition = ?";
		    		try(ResultSet rsa = dataBase.executeQuery(associationSql, requisitionId)){
		    			while(rsa.next()) {
		    				int materialId = rsa.getInt("material");
		    				Material m = new MaterialDAO().getMaterialById(materialId);
		    				double ammount = rsa.getDouble("ammount");
		    				boolean bought = rsa.getBoolean("is_bought");
		    				String section = rsa.getString("section");
		    				int associationId = rsa.getInt("id");
		    				PurchaseRequisitionAssociation sra = new PurchaseRequisitionAssociation(m, ammount, section);
		    				sra.setBought(bought);
		    				sra.setId(associationId);
		    				sraList.add(sra);
		    			}
		    		}
		    		
		    		
		    		PurchaseRequisition sr = new PurchaseRequisition(requisitor, requisitionNumber, date, priority, ptc);
		    		sr.setAssociationList(sraList);
		    		sr.setId(requisitionId);
		    		sr.setStatus(status);
		    		sr.setJustification(justification);
		    		return sr;
		    	}
		    } catch (SQLException e) {
		        e.printStackTrace();
	        }
			return null;
    }

	public String getNewRequisitionNumber() {
	    String number = String.valueOf((dataBase.getAutoIncrementValue("sales_requisition")));
		return number;
    }
	
	
}
