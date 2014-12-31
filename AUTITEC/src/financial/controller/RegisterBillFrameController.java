package financial.controller;

import java.util.Date;

import javax.swing.JOptionPane;

import database.dao.BillDAO;
import model.Bill;
import util.ClearFrame;
import util.ShowMessage;
import financial.view.RegisterBillFrame;

public class RegisterBillFrameController {
	
	private RegisterBillFrame frame;
	
	public RegisterBillFrameController(RegisterBillFrame frame) {
		this.frame = frame;
	}
	
	public void clear() {
		
		String title = "Limpar os Dados Inseridos";
		String message = "Deseja realmente limpar os dados inseridos?";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
			ClearFrame.clear(frame);
		}		
		
	}

	public void register(String bill, String creditor, Date dueDate, int installments, String observation, double value) {
		
		if(!validateData(bill, creditor, dueDate, installments, value)) return; 
		
		Bill b = new Bill(bill, creditor, dueDate, installments, observation, value);
		new BillDAO(b);
		
		ShowMessage.successMessage(null, "Conta Registrada", "A Conta a Pagar foi Registrada com sucesso");
		
		frame.dispose();
		
	}
	
	public void closeFrame() {
		
		String title = "Sair do Registro de Contas a Pagar";
		String message = "Deseja realmente sair?\nA conta não será registrada";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
			frame.dispose();			
		}
		
	}

	private boolean validateData(String bill, String creditor, Date dueDate, int installments, double value) {
		
		boolean validate = false;
		String label = "";
		
		if(bill == null || bill.isEmpty()) label = "Conta a Pagar";
		else if(dueDate == null) label = "Data de Vencimento";
		else if(installments == 0) label = "Número de Parcelas";
		else if(value <= 0.0) label = "Valor da Parcela";
		else if(creditor == null || creditor.isEmpty()) label = "Credor";
		else validate = true;
		
		if(!validate) {
			
			String title = "Erro ao registrar conta a pagar";
			String message = "Por favor verifique o seguinte campo para registro da conta a pagar:\n" + label;
			ShowMessage.errorMessage(frame, title, message);
			
		}
		
		return validate;
		
	}
	
}
