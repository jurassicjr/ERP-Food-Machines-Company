package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Employee;
import model.Rnc;
import database.DataBase;

public class RncDAO {
	
	private DataBase dataBase;
	
	public RncDAO()
	{
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void persist(Rnc rnc)
	{
		java.sql.Date date = new java.sql.Date(rnc.getDate().getTime());
		java.sql.Date deadLine = null;
		if(rnc.getDeadLine()!=null)
			deadLine = new java.sql.Date(rnc.getDeadLine().getTime());
		
		try {
			String sql = " insert into rnc("
					+ "actiontype,"
					+ "sequencenumber,"
					+ "emitter,"
					+ "date,"
					+ "origin,"
					+ "description,"
					+ "posaction,"
					+ "actiondescription,"
					+ "cause,"
					+ "actionplanneed,"
					+ "actionplandescription,"
					+ "responsible,"
					+ "deadline) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			Object[] parameters = {
					
				rnc.getActiontype(),
				rnc.getSequenceNumber(),
				rnc.getEmitter().getId(),
				date,
				rnc.getOrigin(),
				rnc.getDescription(),
				rnc.getPosaction(),
				rnc.getActionDescription(),
				rnc.getCause(),
				rnc.getActionPlanNeed(),
				rnc.getActionDescription(),
				rnc.getResponsible().getId(),
				deadLine
			};
			dataBase.executeUpdate(sql,parameters);
		
		} catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	public ArrayList<Rnc> getListByArguments(Employee emitter,Date initialDate,Date finalDate,Boolean showInactives)
	{
		ArrayList<Rnc> rncList = new ArrayList<>();
		try {
			 String searchParameters = "";
			 Integer parameterCount = 0 ;
			 if(emitter!=null)
			 {
				 Integer empId = emitter.getId();
				 searchParameters = " emitter = "+empId;
			 }
			 if(initialDate!=null)
			 {
				
				 
			 }
			 String sql = "select id,date,sequencenumber,isActive from rnc where "+searchParameters+" order by date  ";
			ResultSet rs =  dataBase.executeQuery(sql);
			while(rs.next())
			{
				Rnc rnc = new Rnc();
				rnc.setId(rs.getInt("id"));
				rnc.setDate(rs.getDate("date"));
				rnc.setSequenceNumber(rs.getString("sequencenumber"));
				rnc.setIsActive(rs.getBoolean("isActive"));
			    rncList.add(rnc);
			}
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rncList;
	}
	
	public ArrayList<Rnc> getList()
	{
		ArrayList<Rnc> rncList = new ArrayList<>();
		try {
			 String sql = "select id,date,sequencenumber,isActive from rnc order by date  ";
			ResultSet rs =  dataBase.executeQuery(sql);
			while(rs.next())
			{
				Rnc rnc = new Rnc();
				rnc.setId(rs.getInt("id"));
				rnc.setDate(rs.getDate("date"));
				rnc.setSequenceNumber(rs.getString("sequencenumber"));
				rnc.setIsActive(rs.getBoolean("isActive"));
			    rncList.add(rnc);
			}
			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return rncList;
	}
	
	public List<Rnc> getAllRncNotActive() {
	    List<Rnc> rncList = new ArrayList<Rnc>();
	    String query = " SELECT *FROM rnc WHERE isActive = false";
	    try(ResultSet rs = dataBase.executeQuery(query)){
	    	while(rs.next()) {	    		
	    		String actionType = rs.getString("actionType");
	    		String sequenceNumber = rs.getString("sequencenumber");
	    		Integer emitterID = rs.getInt("emitter");
	    		new EmployeeDAO();
	    		Employee emitter = EmployeeDAO.getEmployeeById(emitterID);
	    		Date date = rs.getDate("date");
	    		String origin = rs.getString("origin");
	    		String description = rs.getString("description");
	    		String posAction = rs.getString("posaction");
	    		String actionDescription = rs.getString("actiondescription");
	    		String cause = rs.getString("cause");
	    		Boolean actionPlanNeed = rs.getBoolean("actionplanneed");
	    		String actionPlanDescription = rs.getString("actionplandescription");
	    		int responsibleID = rs.getInt("responsible");
	    		Employee responsible = EmployeeDAO.getEmployeeById(responsibleID);
	    		Date deadLine = rs.getDate("deadline");
	    		int id = rs.getInt("id");
	    		
	    		Rnc rnc = new Rnc();
	    		rnc.setActionDescription(actionDescription);
	    		rnc.setActionPlanDescription(actionPlanDescription);
	    		rnc.setActionPlanNeed(actionPlanNeed);
	    		rnc.setActiontype(actionType);
	    		rnc.setCause(cause);
	    		rnc.setDate(date);
	    		rnc.setDeadLine(deadLine);
	    		rnc.setDescription(description);
	    		rnc.setEmitter(emitter);
	    		rnc.setId(id);
	    		rnc.setOrigin(origin);
	    		rnc.setPosaction(posAction);
	    		rnc.setResponsible(responsible);
	    		rnc.setSequenceNumber(sequenceNumber);
	    		
	    		rncList.add(rnc);
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		
		return rncList;
    }
}
