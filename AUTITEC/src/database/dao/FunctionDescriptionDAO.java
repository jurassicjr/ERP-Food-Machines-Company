package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.Employee;
import model.FunctionDescription;
import core.CBO;
import database.DataBase;

public class FunctionDescriptionDAO {
	
	private DataBase dataBase;

	public FunctionDescriptionDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(Map<String, Object> map) {
	    List<String> knowledgementList = (List<String>) map.get("knowledgementList");
	    List<String> personalHabilitiesList = (List<String>) map.get("personalHabilitiesList");
	    int needExperience = (int) map.get("needExperience");
	    CBO function = (CBO) map.get("function");
	    int functionID = function.getId();
	    String functionAttribuition = (String) map.get("functionAtribuition");
	    String minimalGraduation = (String) map.get("minimalGraduation");
	    String period = (String) map.get("period");
	    String sector = (String) map.get("sector");
	    Employee e = (Employee) map.get("employee");
	    int eID = e.getId();
	    int functionDescriptionID = dataBase.getAutoIncrementValue("function_description"); 
	    String query = "INSERT INTO function_description(need_experience, function, function_attribuition, minimal_graduation, period, sector, employee) VALUES(?,?,?,?,?,?,?)";
	    Object[] obj = new Object[] {needExperience, functionID, functionAttribuition, minimalGraduation, period, sector, eID};
	    dataBase.executeUpdate(query, obj);
	    registerKnowledgement(knowledgementList, functionDescriptionID);
	    registerPersonalHabilities(personalHabilitiesList, functionDescriptionID);
	    dataBase.close();
	}

	private void registerKnowledgement(List<String> knowledgementList, int id) {
	    if(knowledgementList == null) return;
	    String query = "INSERT INTO function_description_knowledgement(function_description, knowledgement) VALUES(?,?)";
	    for (String s : knowledgementList) {
	        dataBase.executeUpdate(query, new Object[] {id, s});
        }
    }
	
	private void registerPersonalHabilities(List<String> personalHabilitiesList, int id) {
		if(personalHabilitiesList == null)return;
		String query = "INSERT INTO function_description_personal_habilities(function_description, personal_habilities) VALUES (?,?)";
		for (String s : personalHabilitiesList) {
	        dataBase.executeUpdate(query, new Object[] {id, s});
        }
	}

	public FunctionDescription getFunctionDescriptionByCBO(model.CBO cbo) {
		int id = cbo.getId();
		String query = "SELECT *FROM function_description WHERE function = ?";
		FunctionDescription funDes = new FunctionDescription();
		try(ResultSet rs = dataBase.executeQuery(query, id)){
			if(rs.next()) {				
				funDes.setId(rs.getInt("id"));
				int employeeID = rs.getInt("employee");
				new EmployeeDAO();
				funDes.setEmployee(EmployeeDAO.getEmployeeById(employeeID));
				funDes.setFunctionAtribuition(rs.getString("function_attribuition"));
				funDes.setKnowledgementList(getKnowledgementList(funDes.getId()));
				funDes.setMinimalGraduation(rs.getString("minimal_graduation"));
				funDes.setNeedExperiences(rs.getInt("need_experience"));
				funDes.setPeriod(rs.getString("period"));
				funDes.setPersonalHabilitiesList(getPersonalHabilitiesList(funDes.getId()));
				funDes.setSector(rs.getString("sector"));
			}
		} catch (SQLException e) {
	        e.printStackTrace();
        }
		return funDes;
	}
	
	private List<String> getPersonalHabilitiesList(int id) {
		String query = "SELECT *FROM function_description_personal_habilities WHERE function_description = ?";
		List<String> list = new ArrayList<String>();
		try(ResultSet rs = dataBase.executeQuery(query, id)){
			while (rs.next()) {
				list.add(rs.getString("personal_habilities"));
            }
		} catch (SQLException e) {
	        e.printStackTrace();
        }
	    return list;
    }

	public List<String> getKnowledgementList(int id) {
		String query = "SELECT *FROM function_description_knowledgement WHERE function_description = ?";
		List<String> list = new ArrayList<String>();
		try(ResultSet rs = dataBase.executeQuery(query, id)){
			while (rs.next()) {
				list.add(rs.getString("knowledgement"));
            }
		} catch (SQLException e) {
	        e.printStackTrace();
        }
		return list;
	}

}
