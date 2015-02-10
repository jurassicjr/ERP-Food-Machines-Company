package rh.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import rh.view.RegisterCBOFrame;
import util.ClearFrame;
import util.ShowMessage;
import model.CBO;
import database.DataBase;
import database.dao.CboDAO;

public class RegisterCBOFrameController {
	
	private RegisterCBOFrame frame;
	private DataBase dataBase;
	
	private CBO cbo;
	
	public RegisterCBOFrameController(RegisterCBOFrame frame) {
		
		this.frame = frame;
		
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void clear() {
		
		String title = "Limpar os Dados Inseridos";
		String message = "Deseja realmente limpar os dados inseridos?";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
			ClearFrame.clear(frame);
		}
	}
	
	public void closeFrame() {
		
		String title = "Sair do Registro de CBO";
		String message = "Deseja realmente sair?\nO CBO não será registrado.";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
			frame.dispose();
			dataBase.close();			
		}
		
	}

	public void register(String title, String code) {
		
		cbo = null;
		
		if(!validateData(title, code)) return;
		
		try {
			
			int intCode = Integer.parseInt(code);
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM cbo WHERE code = ?", intCode);
			
			if(resultSet.next()) {
				ShowMessage.errorMessage(frame, "Erro ao Registrar CBO", "Já existe CBO registrado com este código");
				return;
			}
			
			int idCbo = dataBase.getAutoIncrementValue("cbo");
			
			new CboDAO(title, code);
			cbo = new CBO(idCbo, code, title);
			
			ShowMessage.successMessage(frame, "CBO Registrado", "O CBO foi Registrado com sucesso");
			frame.dispose();
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
	}
	
	private boolean validateData(String title, String code) {
		
		String label = "";
		boolean valid = true;
		
		if(title == null || title.isEmpty()) {
			label = "Cargo";
			valid = false;
		}
		else if(code == null || code.isEmpty()) {
			label = "Código CBO";
			valid = false;
		}
				
		if(!valid) {
			ShowMessage.errorMessage(frame, "Erro ao Registrar CBO", "Por favor verifique preencha o seguinte campo:\n" + label);
		}
				
		return valid;
	}

	public CBO getCBO() {
		return cbo;
	}
}
