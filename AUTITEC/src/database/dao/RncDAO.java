package database.dao;

import database.DataBase;
import model.Rnc;

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
}
