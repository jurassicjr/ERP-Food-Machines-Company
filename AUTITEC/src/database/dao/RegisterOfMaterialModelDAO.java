package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MaterialModel;
import database.DataBase;

public class RegisterOfMaterialModelDAO {

	
	private DataBase dataBase;

	public RegisterOfMaterialModelDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}

	public void register(MaterialModel materialModel) {
	    String name = materialModel.getName();
	    String query = "INSERT INTO mat_cat(name) values(?)";
	    dataBase.executeUpdate(query, name);
    }

	public List<MaterialModel> getModels() {
	    List<MaterialModel> list = new ArrayList<MaterialModel>();
	    String query = "SELECT * FROM mat_cat";
	    try(ResultSet rs = dataBase.executeQuery(query)){
	    	while(rs.next()) {
	    		int id = rs.getInt("id");
	    		String name = rs.getString("name");
	    		MaterialModel mm = new MaterialModel(name, id);
	    		list.add(mm);
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return list;
    }
}
