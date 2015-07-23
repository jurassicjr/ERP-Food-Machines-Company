package rh.controller;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TechnicalStandard;
import rh.view.RegisterTechnicalStandardFrame;
import userInterface.components.FileChooser;
import userInterface.components.filters.DocumentFilter;
import util.FTP;
import util.ShowMessage;
import database.DataBase;
import database.dao.TechnicalStandardDAO;
import database.dao.TechnicalStandardVersionDAO;

/**
 * Realiza o controle do frame de registro de Normas Técnicas
 */
public class RegisterTechnicalStandardFrameController {
	
	private RegisterTechnicalStandardFrame frame;
	private FileChooser fileChooser;
	private DataBase dataBase;
	
	/**
	 * Cria o controlador para o registro de Normas Técnicas
	 */
	public RegisterTechnicalStandardFrameController(RegisterTechnicalStandardFrame frame) {
		
		this.frame = frame;
		
		fileChooser = new FileChooser();
				
		dataBase = new DataBase();
		dataBase.connect();
		
	}
	
	/**
	 * Registra uma nova norma técnica
	 * 
	 * @return true se a norma técnica foi registrada e false se a norma técnica não foi registrada
	 */
	public boolean register(String technicalStandard, File file) {
				
		if(!verifyTechnicalStandard(technicalStandard, file)) return false;
			
		new TechnicalStandardDAO().pesist(technicalStandard, file);
				
		Date date = new java.sql.Date(System.currentTimeMillis());
		String tokens[] = file.getName().split("\\.");    
				
		String fileName = file.getName().substring(0, 3).toUpperCase() + date.toString() + "." + tokens[tokens.length - 1] ;
		
		FTP ftp = new FTP();
		ftp.upload(file, fileName, "Normas_Tecnicas/");
		
		ShowMessage.successMessage(frame.getFrame(), "Norma Técnica Registrada", "A Norma Técnica " + technicalStandard + "\nfoi registrada com sucesso");
		
		return true;
						
	}
	
	/**
	 * Veficica se os dados para registro da Norma Técnica estão corretos para registro
	 * 
	 * @param technicalStandard O nome da Norma Técnica
	 * @param file O arquivo que contém a primeira versão da Norma Técnica
	 * 
	 * @return true se os dados são válidos e false se os dados são inválidos
	 */
	private boolean verifyTechnicalStandard(String technicalStandard, File file) {
		
		String title = "Dados inválidos para registro de Norma Técnica";
		boolean flag = true;
		
		if(technicalStandard == null || technicalStandard.isEmpty()) {
			String message = "Por favor insira o nome da Norma Técnica";
			ShowMessage.errorMessage(frame.getFrame(), title, message);
			flag = false;
		}
		else if(file == null || !file.exists()) {
			String message = "Por favor selecione o arquivo da Norma Técnica";
			ShowMessage.errorMessage(frame.getFrame(), title, message);
			flag = false;
		}
		else {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM technical_standard WHERE upper(technical_standard) = ?", technicalStandard.toUpperCase());
			try {
				
				if(resultSet.next()) {
					ShowMessage.errorMessage(frame.getFrame(), "Norma Técnica Já Registrada", "A Norma Técnica " + technicalStandard + "\njá está registrada");
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
	 * Atualiza a versão de uma norma técnica
	 * 
	 * @return true se a norma técnica foi atualizada e false se a norma técnica não foi atualizada
	 */
	public boolean update(TechnicalStandard technicalStandard, File file) {
		
		if(file == null || !file.exists()) {
			ShowMessage.errorMessage(null, "Dados inválidos para registro de Norma Técnica", "Por favor selecione o arquivo da norma técnica");
			return false;
		}
		
		new TechnicalStandardVersionDAO().persist(technicalStandard, file);
		
		Date date = new java.sql.Date(System.currentTimeMillis());
		String tokens[] = file.getName().split("\\.");    
				
		String fileName = file.getName().substring(0, 3).toUpperCase() + date.toString() + "." + tokens[tokens.length - 1] ;
		
		FTP ftp = new FTP();
		boolean upload = ftp.upload(file, fileName, "docs");
		
		if(!upload) return false;
		
		ShowMessage.successMessage(frame.getFrame(), "Norma Técnica Atualizada", "A Norma Técnica " + technicalStandard + "\nfoi atualizada com sucesso");
		
		return true;
		
	}
	
	/**
	 * Seleciona o arquivo da norma técnica
	 */
	public void selectFile() {
		
		fileChooser.showOpenDialog(new DocumentFilter());
		
		if(fileChooser.hasSelectedFile()) {
			
			frame.setFilePath(fileChooser.getSelectedPathFile());
			
		}
		
	}	

}
