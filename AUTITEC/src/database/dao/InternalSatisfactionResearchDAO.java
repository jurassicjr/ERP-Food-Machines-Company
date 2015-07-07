package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.Employee;
import model.InternalSatisfactionResearch;
import database.DataBase;

public class InternalSatisfactionResearchDAO {

	private DataBase dataBase;

	public InternalSatisfactionResearchDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public InternalSatisfactionResearch getResearch(Employee e, Date date) {
		int employeeID = e.getId();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	    String query = "SELECT * FROM internal_satisfaction_research WHERE employee = ? AND research_date = ?";
	    Object[] obj = new Object[] {employeeID, sqlDate};
	    try (ResultSet rs = dataBase.executeQuery(query, obj)){
	    	if(rs.next()) {
	    		InternalSatisfactionResearch iSR = new InternalSatisfactionResearch();
	    		iSR.setComunicationFacility(rs.getInt("comunication_facility"));
	    		iSR.setDate(date);
	    		new EmployeeDAO();
				iSR.setEmployee(EmployeeDAO.getEmployeeById(rs.getInt("employee")));
				iSR.setEPIs(rs.getInt("epis"));
				iSR.setFormers(rs.getInt("formers"));
				iSR.setFunctionPerfomed(rs.getInt("function_performed"));
				iSR.setId(rs.getInt("id"));
				iSR.setIndustryIntenalLighting(rs.getInt("industry_internal_lighting"));
				iSR.setRelationGroup(rs.getInt("relation_group"));
				iSR.setTooling(rs.getInt("tooling"));
				iSR.setUniform(rs.getInt("uniform"));
				iSR.setWCs(rs.getInt("wc"));
				iSR.setWorkingArea(rs.getInt("working_area"));
				return iSR;
	    	}
	    } catch (SQLException e1) {
	        e1.printStackTrace();
        }
		return null;
    }
	
	
}
