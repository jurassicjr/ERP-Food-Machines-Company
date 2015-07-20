package database.dao;

import java.util.List;
import java.util.Map;

import model.Employee;
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

}
