package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MeasureUnit;
import database.DataBase;

public class MeasureUnitDAO {

	
	private DataBase dataBase;

	public MeasureUnitDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public List<MeasureUnit> getUnits(){
		List<MeasureUnit> list = new ArrayList<MeasureUnit>();
		String query = "select * from measure_unit";
		try(ResultSet rs = dataBase.executeQuery(query)){
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				MeasureUnit mu = new MeasureUnit(name, id);
				list.add(mu);
			}
		} catch (SQLException e) {
	        e.printStackTrace();
        }
		return list;
	}
}
