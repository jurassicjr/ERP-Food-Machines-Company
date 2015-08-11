package database.dao;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ManualQuality;
import model.ManualQualityVersion;
import database.DataBase;


public class ManualQualityDAO {
	
	private DataBase dataBase;
	/**
	 * Cria o objeto para a persistência de uma versão de um manual de qualidade.
	 */
	public ManualQualityDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	/** 
	 * Realiza a persistência no banco de dados da versão do manual de qualidade.
	 * 
	 * @param manualQuality O procedimento que deve receber a nova versão
	 * @param File file O arquivo da nova versão do manual de qualidade.
	 */
	public void persist(String manualQuality, File file) {
		int id = dataBase.getAutoIncrementValue("manual_quality");
		Date date = new Date(System.currentTimeMillis());
		
		String tokens[] = file.getName().split("\\.");    
		String fileName = file.getName().substring(0, 3).toUpperCase() + date.toString() + "." + tokens[tokens.length - 1] ;
		
		String sql = "INSERT INTO manual_quality (file, date_update, manual_quality) VALUES (?, ?, ?)";
		dataBase.executeUpdate(sql, new Object[]{fileName, date, id});	
		
	}
	
	/**
	 * Recupera do banco e cria o objeto de reserva técnica com o id indicado
	 * 
	 * @param id O id no banco do manual de qualidade
	 * 
	 * @return O Objeto do manual de qualidade resgatada do banco de dados
	 */
	public ManualQuality factory(int id) {
		
		ManualQuality mq = null;
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM manual_quality WHERE id = ?", id);
			resultSet.next();
			
			String procedure = resultSet.getString("`procedure`");
			mq = new ManualQuality(id, procedure);
			
			resultSet = dataBase.executeQuery("SELECT * FROM manual_quality WHERE `procedure` = ?", id);
			while(resultSet.next()) {
				
				int idVersion = resultSet.getInt("id");
				String file = resultSet.getString("file");
				java.util.Date dateUpdate = resultSet.getDate("date_update");
				
				ManualQualityVersion version = new ManualQualityVersion(idVersion, file, dateUpdate);
				mq.addVersion(version);
				
			}
			
		} catch(SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
		
		
		return mq;
		
	}

	public void persist(ManualQuality manualQuality, File file) {
		Date date = new Date(System.currentTimeMillis());
		
		String tokens[] = file.getName().split("\\.");    
		String fileName = file.getName().substring(0, 3).toUpperCase() + date.toString() + "." + tokens[tokens.length - 1] ;
		
		String sql = "INSERT INTO manual_quality (file, date_update, manual_quality) VALUES (?, ?, ?)";
		dataBase.executeUpdate(sql, new Object[]{fileName, date, manualQuality.getId()});	
		
    }
	
}
