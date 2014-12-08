package database.dao;

import java.io.File;
import java.sql.Date;

import model.TechnicalStandard;
import database.DataBase;

/**
 * Realiza a persistência de uma versão da norma técnica
 */
public class TechnicalStandardVersionDAO {
	
	private DataBase dataBase;
	
	/**
	 * Cria o objeto para a persistência de uma versão de uma norma técnica
	 */
	public TechnicalStandardVersionDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	/**
	 * Realiza a persistência no banco de dados da versão da norma técnica
	 * 
	 * @param technicalStandard A Norma Técnica que deve receber a nova versão
	 * @param File file O arquivo da nova versão da Norma Técnica
	 */
	public void persist(TechnicalStandard technicalStandard, File file) {
		
		Date date = new Date(System.currentTimeMillis());
		
		String tokens[] = file.getName().split("\\.");    
		String fileName = file.getName().substring(0, 3).toUpperCase() + date.toString() + "." + tokens[tokens.length - 1] ;
		
		String sql = "INSERT INTO technical_standard_version (file, date_update, technical_standard) VALUES (?, ?, ?)";
		dataBase.executeUpdate(sql, new Object[]{fileName, date, technicalStandard.getId()});	
		
	}

}
