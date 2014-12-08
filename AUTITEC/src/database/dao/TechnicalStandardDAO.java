package database.dao;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TechnicalStandard;
import model.TechnicalStandardVersion;
import database.DataBase;

/**
 * Realiza a persistência de uma Norma Técnica
 */
public class TechnicalStandardDAO {
	
	private DataBase dataBase;
	
	/**
	 * Cria o objeto para realizar a persistência de uma Norma Técnica
	 */
	public TechnicalStandardDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	/**
	 * Realiza a pesistência de uma Norma Técnica
	 * 
	 * @param technicalStandard O nome da norma técnica
	 */
	public void pesist(String technicalStandard, File file) {
		
		int id =  dataBase.getAutoIncrementValue("technical_standard");
		Date date = new java.sql.Date(System.currentTimeMillis());
			
		String sql = "INSERT INTO technical_standard (technical_standard) VALUES (?)";
		dataBase.executeUpdate(sql, technicalStandard);
		
		String tokens[] = file.getName().split("\\.");    
		String fileName = file.getName().substring(0, 3).toUpperCase() + date.toString() + "." + tokens[tokens.length - 1] ;
						
		sql = "INSERT INTO technical_standard_version (file, date_update, technical_standard) VALUES (?, ?, ?)";
		dataBase.executeUpdate(sql, new Object[]{fileName, date, id});		
		
	}

	/**
	 * Recupera do banco e cria o objeto de reserva técnica com o id indicado
	 * 
	 * @param id O id no banco da norma técnica
	 * 
	 * @return O Objeto da norma técnica resgatada do banco de dados
	 */
	public TechnicalStandard factory(int id) {
		
		TechnicalStandard ts = null;
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM technical_standard WHERE id = ?", id);
			resultSet.next();
			
			String technicalStandard = resultSet.getString("technical_standard");
			ts = new TechnicalStandard(id, technicalStandard);
			
			resultSet = dataBase.executeQuery("SELECT * FROM technical_standard_version WHERE technical_standard = ?", id);
			while(resultSet.next()) {
				
				int idVersion = resultSet.getInt("id");
				String file = resultSet.getString("file");
				java.util.Date dateUpdate = resultSet.getDate("date_update");
				
				TechnicalStandardVersion version = new TechnicalStandardVersion(idVersion, file, dateUpdate);
				ts.addVersion(version);
				
			}
			
		} catch(SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
		
		
		return ts;
		
	}
	
}
