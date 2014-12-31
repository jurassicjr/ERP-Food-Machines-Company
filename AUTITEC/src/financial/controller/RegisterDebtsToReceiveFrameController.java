package financial.controller;

import java.util.Date;

import javax.swing.JOptionPane;

import model.DebtToReceive;
import util.ClearFrame;
import util.ShowMessage;
import database.DataBase;
import database.dao.DebtToReceiveDAO;
import financial.view.RegisterDebtsToReceiveFrame;

public class RegisterDebtsToReceiveFrameController {
	
	private RegisterDebtsToReceiveFrame frame;
	private DataBase dataBase;
	
	public RegisterDebtsToReceiveFrameController(RegisterDebtsToReceiveFrame frame) {
		
		this.frame = frame;
		
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void closeFrame() {
		
		String title = "Sair do Registro de Contas a Receber";
		String message = "Deseja realmente sair?\nA conta não será registrada";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
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

	public void register(String debt, String debtor, Date dueDate, String observation, double value) {
		
		if(!validateData(debt, debtor, dueDate, observation, value)) return;
		
		DebtToReceive d = new DebtToReceive(debt, debtor, observation, dueDate, false, value);
		
		new DebtToReceiveDAO(d);
		
		ShowMessage.successMessage(null, "Conta Registrada", "A Conta a Receber foi Registrada com sucesso");
		
	}
	
	public boolean validateData(String debt, String debtor, Date dueDate, String observation, double value) {
		
		boolean validate = false;
		String label = "";
		
		if(debt == null || debt.isEmpty()) label = "Conta a Receber";
		else if(dueDate == null) label = "Data de Recebimento";
		else if(value <= 0) label = "Valor";
		else if(debtor == null || debtor.isEmpty()) label = "Devedor";
		else validate = true;
		
		if(!validate) {
			
			String title = "Erro ao registrar conta a receber";
			String message = "Por favor verifique o seguinte campo para registro da conta a receber:\n" + label;
			ShowMessage.errorMessage(frame, title, message);
			
		}
		
		return validate;
		
	}
	
}
