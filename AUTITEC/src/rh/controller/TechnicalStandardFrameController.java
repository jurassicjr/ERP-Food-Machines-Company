package rh.controller;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.TechnicalStandard;
import model.TechnicalStandardVersion;
import rh.view.RegisterTechnicalStandardFrame;
import rh.view.TechnicalStandardFrame;
import database.DataBase;

/**
 * Responsável por controlar o frame de normas técnicas
 */
public class TechnicalStandardFrameController {
	
	private TechnicalStandardFrame frame;
	private DataBase dataBase;
	
	/**
	 * Cria o objeto para controle do frame de Normas Técnicas
	 * 
	 * @param frame O frame de Normas Técnicas
	 */
	public TechnicalStandardFrameController(TechnicalStandardFrame frame) {
		
		this.frame = frame;
		
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	/**
	 * Retorna as normas técnicas registradas no sistema
	 */
	public TechnicalStandard[] getTechnicalStandards() {
		
		ArrayList<TechnicalStandard> technicalStandards = new ArrayList<TechnicalStandard>();
		
		try {
			
			String sql = "SELECT technical_standard.id, technical_standard.technical_standard, "
					+ "technical_standard_version.id as 'id_version', technical_standard_version.file, technical_standard_version.date_update "
					+ "FROM technical_standard, technical_standard_version "
					+ "where technical_standard.id = technical_standard_version.technical_standard;";
			
			ResultSet resultSet = dataBase.executeQuery(sql);
						
			while(resultSet.next()) {
								
				int id = resultSet.getInt("id");
				String technicalStandard = resultSet.getString("technical_standard");
				
				TechnicalStandard ts = new TechnicalStandard(id, technicalStandard);
				technicalStandards.add(ts);
				
				while(resultSet.getInt("id") == id) {
					
					int idVersion = resultSet.getInt("id_version");
					String filePath = resultSet.getString("file");
					Date date = resultSet.getDate("date_update");
					
					ts.addVersion(new TechnicalStandardVersion(idVersion, filePath, date));
					
					if(!resultSet.next()) break;
				}
				
				resultSet.previous();
				
			}
						
			TechnicalStandard ts[] = new TechnicalStandard[technicalStandards.size()];
			return technicalStandards.toArray(ts);
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
		return new TechnicalStandard[0];
		
	}

	/**
	 * Cria o frame para o registro de uma nova norma técnica
	 */
	public void addTechnicalStandard() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				RegisterTechnicalStandardFrame registerFrame = new RegisterTechnicalStandardFrame();
				registerFrame.setVisible(true);
				registerFrame.setLocationRelativeTo(frame);
				
			}
		});
		
	}
}
