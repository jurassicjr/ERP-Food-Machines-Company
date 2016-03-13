package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DataBase;
import model.CNPJ;

public class CnpjDAO {
	
	public List<CNPJ> getAll() {
		
		List<CNPJ> cnpjs = new ArrayList<>();
		
		DataBase dataBase = new DataBase();
		dataBase.connect();
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM cnpj");
		
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String cnpj = resultSet.getString("cnpj");
				String corporateName = resultSet.getString("corporate_name");
				
				cnpjs.add(new CNPJ(id, cnpj, corporateName));
			
			}
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();	
			e.printStackTrace();
		}
		
		return cnpjs;
	}
	
	public CNPJ getByID(int id) {
		
		CNPJ cnpj = null;
		
		DataBase dataBase = new DataBase();
		dataBase.connect();
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM cnpj WHERE id = " + id);
			
			if(resultSet.next()) {
				
				String cnpjNumber = resultSet.getString("cnpj");
				String corporateName = resultSet.getString("corporate_name");
				
				cnpj = new CNPJ(id, cnpjNumber, corporateName);
				
			}
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();	
			e.printStackTrace();
		}
		
		return cnpj;
		
	}

}
