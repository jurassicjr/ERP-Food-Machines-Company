package rh.controller;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Procedure;
import rh.view.RegisterProcedureFrame;
import userInterface.components.FileChooser;
import userInterface.components.filters.DocumentFilter;
import util.FTP;
import util.ShowMessage;
import database.DataBase;
import database.dao.ProcedureDAO;
import database.dao.TechnicalStandardDAO;

public class RegisterProcedureFrameController {


	private RegisterProcedureFrame frame;
	private FileChooser fileChooser;
	private DataBase dataBase;
	
	/**
	 * Cria o controlador para o registro de Procedimentos
	 */
	public RegisterProcedureFrameController(RegisterProcedureFrame frame) {
		
		this.frame = frame;
		
		fileChooser = new FileChooser();
				
		dataBase = new DataBase();
		dataBase.connect();
		
	}
	
	/**
	 * Registra uma novo procedimento
	 * 
	 * @return true se o procedimento foi registrado e false se o procedimento não foi registradao
	 */
	public boolean register(String technicalStandard, File file) {
				
		if(!verifyTechnicalStandard(technicalStandard, file)) return false;
			
		new TechnicalStandardDAO().pesist(technicalStandard, file);
				
		Date date = new java.sql.Date(System.currentTimeMillis());
		String tokens[] = file.getName().split("\\.");    
				
		String fileName = file.getName().substring(0, 3).toUpperCase() + date.toString() + "." + tokens[tokens.length - 1] ;
		
		FTP ftp = new FTP();
		ftp.upload(file, fileName, "/Procedimento/");
		
		ShowMessage.successMessage(frame.getFrame(), "Procedimento Registrado", "O procedimento " + technicalStandard + "\nfoi registrado com sucesso");
		
		return true;
						
	}
	
	/**
	 * Veficica se os dados para registro do Procedimento estão corretos para registro
	 * 
	 * @param procedure O nome do procedimento
	 * @param file O arquivo que contém a primeira versão do Procedimento
	 * 
	 * @return true se os dados são válidos e false se os dados são inválidos
	 */
	private boolean verifyTechnicalStandard(String procedure, File file) {
		
		String title = "Dados inválidos para registro do Procedimento";
		boolean flag = true;
		
		if(procedure == null || procedure.isEmpty()) {
			String message = "Por favor insira o nome do Procedimento";
			ShowMessage.errorMessage(frame.getFrame(), title, message);
			flag = false;
		}
		else if(file == null || !file.exists()) {
			String message = "Por favor selecione o arquivo do Procedimento";
			ShowMessage.errorMessage(frame.getFrame(), title, message);
			flag = false;
		}
		else {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM procedure WHERE upper(procedure) = ?", procedure.toUpperCase());
			try {
				
				if(resultSet.next()) {
					ShowMessage.errorMessage(frame.getFrame(), "Procedimento Já Registrado", "O Procedimento " + procedure + "\njá está registrado");
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
	 * Atualiza a versão de um procedimento
	 * 
	 * @return true se o procedimento foi atualizado e false se o procedimento não foi atualizado.
	 */
	public boolean update(Procedure procedure, File file) {
		
		if(file == null || !file.exists()) {
			ShowMessage.errorMessage(null, "Dados inválidos para registro do Procedimento", "Por favor selecione o arquivo do procedimento");
			return false;
		}
		
		new ProcedureDAO().persist(procedure, file);
		
		Date date = new java.sql.Date(System.currentTimeMillis());
		String tokens[] = file.getName().split("\\.");    
				
		String fileName = file.getName().substring(0, 3).toUpperCase() + date.toString() + "." + tokens[tokens.length - 1] ;
		
		FTP ftp = new FTP();
		boolean upload = ftp.upload(file, fileName, "docs");
		
		if(!upload) return false;
		
		ShowMessage.successMessage(frame.getFrame(), "Procedimento Atualizada", "O procedimento " + procedure + "\nfoi atualizada com sucesso");
		
		return true;
		
	}
	
	/**
	 * Seleciona o arquivo do procedimento.
	 */
	public void selectFile() {
		
		fileChooser.showOpenDialog(new DocumentFilter());
		
		if(fileChooser.hasSelectedFile()) {
			
			frame.setFilePath(fileChooser.getSelectedPathFile());
			
		}
		
	}	

}
