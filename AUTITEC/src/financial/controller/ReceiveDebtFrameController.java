package financial.controller;
//
//import java.sql.Date;
//
//import javax.swing.JOptionPane;
//
//import model.DebtToReceive;
//import util.ShowMessage;
//import database.DataBase;
//import financial.view.ReceiveDebtFrame;
//
public class ReceiveDebtFrameController {
//	
//	private ReceiveDebtFrame frame;
//	
//	private DataBase dataBase;
//	
//	public ReceiveDebtFrameController(ReceiveDebtFrame frame) {
//		
//		this.frame = frame;
//		
//		dataBase = new DataBase();
//		dataBase.connect();
//		
//	}
//	
//	public void cancel() {
//		
//		String title = "Cancelar o recebimento da Conta";
//		String message = "Deseja realmente cancelar?\nA conta não será recebida";
//		
//		int response = ShowMessage.questionMessage(frame, title, message); 
//		
//		if(response == JOptionPane.YES_OPTION) {
//			frame.dispose();			
//		}
//		
//	}
//	
//	public void receive(DebtToReceive debtToReceive, double value, double receivedValue) {
//		
//		if(!verifyPayment(receivedValue, value)) return;
//		if(!confirmReceipt()) return;
//		
//		String sql = "UPDATE debt SET paid = 1, payment_date = ?, payed_value = ? WHERE id = ?";
//		Object data[] = new Object[]{new Date(System.currentTimeMillis()), receivedValue, debtToReceive.getId()};
//
//		dataBase.executeUpdate(sql, data);
//		
//		String title = "Conta recebida";
//		String message = "A conta foi recebida com sucesso";
//		
//		ShowMessage.successMessage(frame, title, message);
//		
//		frame.dispose();
//		
//	}
//	
//	private boolean verifyPayment(double receivedValue, double value) {
//		
//		
//		
//		boolean flag = true;
//		
//		String title = "Erro ao registrar o pagamento de conta";
//		String message = null;
//		
//		if(receivedValue <= 0) {
//			flag = false;
//			message = "Por favor indique o valor recebido";
//		}
//		else if(receivedValue < value) {
//			flag = false;
//			message = "O valor recebido não pode ser inferior ao valor da conta";
//		}
//		
//		if(!flag) {
//			ShowMessage.errorMessage(frame, title, message);	
//		}
//		
//		return flag;
//		
//	}
//	
//	private boolean confirmReceipt() {
//		
//		String title = "Confirmar o recebimento da Conta";
//		String message = "Deseja realizar o recebimento da conta?";
//		
//		int response = ShowMessage.questionMessage(frame, title, message);
//		
//		return response == JOptionPane.YES_OPTION; 
//		
//	}
//
}
