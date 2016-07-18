package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import model.Employee;
import model.InternalSatisfactionResearch;
import util.ShowMessage;
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
		Object[] obj = new Object[] { employeeID, sqlDate };
		try (ResultSet rs = dataBase.executeQuery(query, obj)) {
			if (rs.next()) {
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

	public void register(Map<String, Object> map) {

		int internalLighting = (int) map.get("internalLighting");
		int comunicationFacility = (int) map.get("comunicationFacility");
		int epi = (int) map.get("epi");
		int tooling = (int) map.get("tooling");
		int former = (int) map.get("howYouAvaiableTheFormer");
		int wc = (int) map.get("wc");
		int uniform = (int) map.get("uniform");
		int relationGroup = (int) map.get("relationGroup");
		int functionPerfomed = (int) map.get("functionPerformed");
		int workingArea = (int) map.get("workingArea");
		Employee e = (Employee) map.get("employee");
		int employeeID = e.getId();
		Date date = (Date) map.get("date");
		java.sql.Date researchDate = new java.sql.Date(date.getTime());

		String criticismsAndSuggestions = (String) map.get("criticismsAndSuggestions");
		String sql = "INSERT INTO internal_satisfaction_research(industry_internal_lighting, comunication_facility, epis, tooling, formers, wc, uniform, relation_group, function_performed, working_area, employee, research_date, criticisms_and_suggestions) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] obj = new Object[] { internalLighting, comunicationFacility, epi, tooling, former, wc, uniform,
		        relationGroup, functionPerfomed, workingArea, employeeID, researchDate, criticismsAndSuggestions };
		try {
			dataBase.executeUpdate(sql, obj);
			dataBase.close();
		} catch (Exception e2) {
			e2.printStackTrace();
			ShowMessage.errorMessage(null, "Erro", "Erro ao realizar o registro da pesquisa");
			dataBase.close();
		}
		dataBase.close();
	}
}
