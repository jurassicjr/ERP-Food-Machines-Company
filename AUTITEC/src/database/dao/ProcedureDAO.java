package database.dao;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Procedure;
import model.ProcedureVersion;
import database.DataBase;

public class ProcedureDAO {

private DataBase dataBase;
	
	/**
	 * Cria o objeto para a persistência de uma versão de um procedimento.
	 */
	public ProcedureDAO() {
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	/**
	 * Realiza a persistência no banco de dados da versão do procedimento.
	 * 
	 * @param technicalStandard O procedimento que deve receber a nova versão
	 * @param File file O arquivo da nova versão do Procedimento.
	 */
	public void persist(Procedure procedure, File file) {
		
		Date date = new Date(System.currentTimeMillis());
		
		String tokens[] = file.getName().split("\\.");    
		String fileName = file.getName().substring(0, 3).toUpperCase() + date.toString() + "." + tokens[tokens.length - 1] ;
		
		String sql = "INSERT INTO procedure_version (file, date_update, procedure) VALUES (?, ?, ?)";
		dataBase.executeUpdate(sql, new Object[]{fileName, date, procedure.getId()});	
		
	}
	
	/**
	 * Recupera do banco e cria o objeto de reserva técnica com o id indicado
	 * 
	 * @param id O id no banco do procedimento
	 * 
	 * @return O Objeto do procedimento resgatada do banco de dados
	 */
	public Procedure factory(int id) {
		
		Procedure pc = null;
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM `procedure` WHERE id = ?", id);
			resultSet.next();
			
			String procedure = resultSet.getString("`procedure`");
			pc = new Procedure(id, procedure);
			
			resultSet = dataBase.executeQuery("SELECT * FROM procedure_version WHERE `procedure` = ?", id);
			while(resultSet.next()) {
				
				int idVersion = resultSet.getInt("id");
				String file = resultSet.getString("file");
				java.util.Date dateUpdate = resultSet.getDate("date_update");
				
				ProcedureVersion version = new ProcedureVersion(idVersion, file, dateUpdate);
				pc.addVersion(version);
				
			}
			
		} catch(SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
		
		
		return pc;
		
	}
	

}
