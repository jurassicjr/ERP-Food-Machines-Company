package userInterface.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import userInterface.view.RegisterIssueFrame;
import util.ClearFrame;
import util.SendEmail;
import util.ShowMessage;
import database.DataBase;

public class RegisterIssueFrameController {
	
	private RegisterIssueFrame frame;
	private DataBase dataBase;
	
	public RegisterIssueFrameController(RegisterIssueFrame frame) {
		
		this.frame = frame;
		
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void closeFrame() {
		 
		String title = "Cancelar o informe de problema";
		String message = "Deseja cancelar a operação?\nO problema não será informado";
		
		int response = ShowMessage.questionMessage(frame, title, message);
		
		if(response == JOptionPane.YES_OPTION) {
			dataBase.close();
			frame.dispose();
		}
				
	}
	
	public void clear() {
		
		String title = "Limpar os Dados Inseridos";
		String message = "Deseja realmente limpar os dados inseridos?";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
			ClearFrame.clear(frame);
		}
				
	}

	public void register(String description, String user) {
		
		if(description == null || description.isEmpty()) {
			ShowMessage.errorMessage(frame, "Erro ao Informar problema", "Por favor detalhe o problela encontrado\npelo campo descrição");
			return;
		}
		
		String date = new SimpleDateFormat().format(new Date());
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html>");
		
		buffer.append("<p>Usuário: " + user + " - " + date + "</p></br></br>");
		
		for(String p : description.split("\n")) {
			if(p.isEmpty()) continue;
			buffer.append("<p>" + p + "</p>");
		}
		
		buffer.append("</html>");
				
		new SendEmail().send("Informação de Problema - Software AUTITEC", buffer.toString());
		
				
		ShowMessage.successMessage(frame, "Informar Problema", "O problema foi informado com sucesso.\nEm breve o mesmo será solucionado");
		frame.dispose();
		
	}
		
}
