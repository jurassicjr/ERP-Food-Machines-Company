package rh.controller;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ManualQuality;
import rh.view.RegisterManualQualityFrame;
import userInterface.components.FileChooser;
import userInterface.components.filters.DocumentFilter;
import util.FTP;
import util.ShowMessage;
import database.DataBase;
import database.dao.ManualQualityDAO;

public class RegisterManualQualityFrameController {
	private RegisterManualQualityFrame frame;
	private FileChooser fileChooser;
	private DataBase dataBase;
	
	/**
	 * Cria o controlador para o registro de Normas Técnicas
	 */
	public RegisterManualQualityFrameController(RegisterManualQualityFrame frame) {
		
		this.frame = frame;
		
		fileChooser = new FileChooser();
				
		dataBase = new DataBase();
		dataBase.connect();
		
	}
	
	/**
	 * Registra um novo manual de qualidade
	 * 
	 * @return true se o manual de qualidade foi registrado e false se o manual de qualidade não foi registrado
	 */
	public boolean register(String manualQuality, File file) {
				
		if(!verifyManualQuality(manualQuality, file)) return false;
			
		new ManualQualityDAO().persist(manualQuality, file);
				
		Date date = new java.sql.Date(System.currentTimeMillis());
		String tokens[] = file.getName().split("\\.");    
				
		String fileName = file.getName().substring(0, 3).toUpperCase() + date.toString() + "." + tokens[tokens.length - 1] ;
		
		FTP ftp = new FTP();
		ftp.upload(file, fileName, "/Manual_de_Qualidade/");
		
		ShowMessage.successMessage(frame.getFrame(), "Manual de Qualidade Registrado", "O Manual de Qualidade " + manualQuality + "\nfoi registrada com sucesso");
		
		return true;
						
	}
	
	/**
	 * Veficica se os dados para registro do Manual de Qualidade estão corretos para registro
	 * 
	 * @param manualQuality O nome do Manual de Qualidade
	 * @param file O arquivo que contém a primeira versão do Manual de Qualidade
	 * 
	 * @return true se os dados são válidos e false se os dados são inválidos
	 */
	private boolean verifyManualQuality(String manualQuality, File file) {
		
		String title = "Dados inválidos para registro do manual de qualidade";
		boolean flag = true;
		
		if(manualQuality == null || manualQuality.isEmpty()) {
			String message = "Por favor insira o nome da Manual de Qualidade";
			ShowMessage.errorMessage(frame.getFrame(), title, message);
			flag = false;
		}
		else if(file == null || !file.exists()) {
			String message = "Por favor selecione o arquivo do Manual de Qualidade";
			ShowMessage.errorMessage(frame.getFrame(), title, message);
			flag = false;
		}
		else {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM manual_quality WHERE upper(manual_quality) = ?", manualQuality.toUpperCase());
			try {
				
				if(resultSet.next()) {
					ShowMessage.errorMessage(frame.getFrame(), "Manual de Qualidade Já Registrado", "O Manual de Qualidade " + manualQuality + "\njá está registrado");
					flag = false;
				}
				
			} catch (SQLException e) {
				DataBase.showDataBaseErrorMessage();
				e.printStackTrace();
				flag = false;
			}
			
		}
		
		return flag;
		
	}

	
	/**
	 * Atualiza a versão de um manual de qualidade
	 * 
	 * @return true se o manual de qualidade foi atualizado e false se a manual de qualidade não foi atualizado.
	 */
	public boolean update(ManualQuality manualQuality, File file) {
		
		if(file == null || !file.exists()) {
			ShowMessage.errorMessage(null, "Dados inválidos para registro do Manual de Qualidade", "Por favor selecione o arquivo do Manual de Qualidade");
			return false;
		}
		
		new ManualQualityDAO().persist(manualQuality, file);
		
		Date date = new java.sql.Date(System.currentTimeMillis());
		String tokens[] = file.getName().split("\\.");    
				
		String fileName = file.getName().substring(0, 3).toUpperCase() + date.toString() + "." + tokens[tokens.length - 1] ;
		
		FTP ftp = new FTP();
		boolean upload = ftp.upload(file, fileName, "Manual_de_Qualidade");
		
		if(!upload) return false;
		
		ShowMessage.successMessage(frame.getFrame(), "Manual de Qualidade Atualizado", "O Manual de Qualidade " + manualQuality + "\nfoi atualizada com sucesso");
		
		return true;
		
	}
	
	/**
	 * Seleciona o arquivo do Manual de Qualidade
	 * S	 */
	public void selectFile() {
		
		fileChooser.showOpenDialog(new DocumentFilter());
		
		if(fileChooser.hasSelectedFile()) {
			
			frame.setFilePath(fileChooser.getSelectedPathFile());
			
		}
		
	}	
}
