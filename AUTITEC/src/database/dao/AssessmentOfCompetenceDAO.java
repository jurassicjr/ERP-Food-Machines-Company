package database.dao;

import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;

import model.Employee;
import model.FunctionDescription;
import database.DataBase;

public class AssessmentOfCompetenceDAO {

	
	private DataBase dataBase;

	public AssessmentOfCompetenceDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(Map<String, Object> map) {
	    String experience = (String) map.get("experience");
	    FunctionDescription funDes = (FunctionDescription) map.get("funDes");
	    int funDesID = funDes.getId();
	    Employee e = (Employee) map.get("employee");
	    int eID = e.getId();
	    String points = (String) map.get("points");
	    boolean isEnable = (boolean) map.get("isEnable");
	    List<ButtonGroup> btngroupList = (List<ButtonGroup>) map.get("btnGroupList");
	    List<String> functions = (List<String>) map.get("functions");
	    int assessmentOfCompetenceID = dataBase.getAutoIncrementValue("assessment_of_competence");
	    String query = "INSERT INTO assessment_of_competence(experience, function_description, employee, points, is_enable) VALUES(?,?,?,?,?)";
	    Object[] obj = new Object[] {experience, funDesID, eID, points, isEnable};
	    dataBase.executeUpdate(query, obj);
	    registerFunctionsAssociations(btngroupList, functions, assessmentOfCompetenceID);
	    
    }

	private void registerFunctionsAssociations(List<ButtonGroup> btngroupList, List<String> functions, int assessmentOfCompetenceID) {
	    String query = "INSERT INTO assessment_of_competence_association(is_ok, functions, assessment_of_competence) VALUES (?,?,?)";
		for(int i = 0; i<btngroupList.size(); i++) {
			if(btngroupList.get(i).getSelection().getActionCommand().equalsIgnoreCase("Yes")) {
				String s = functions.get(i);
				dataBase.executeUpdate(query, new Object[] {true, s, assessmentOfCompetenceID});
			}else if(btngroupList.get(i).getSelection().getActionCommand().equalsIgnoreCase("No")) {
				String s = functions.get(i);
				dataBase.executeUpdate(query, new Object[] {false, s, assessmentOfCompetenceID});
			}
		}
    }
}
