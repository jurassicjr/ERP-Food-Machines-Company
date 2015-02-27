//package financial.controller;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.swing.JOptionPane;
//
//import model.Bill;
//import util.ShowMessage;
//import database.DataBase;
//import financial.view.PayBillFrame;
//
//public class PayBillFrameController {
//	
//	private PayBillFrame frame;
//	
//	private DataBase dataBase;
//	
//	public PayBillFrameController(PayBillFrame frame) {
//		
//		dataBase = new DataBase();
//		dataBase.connect();
//		
//		this.frame = frame;
//	}
//	
//	public void cancel() {
//		
//		String title = "Cancelar o pagamento da Conta";
//		String message = "Deseja realmente cancelar?\nA conta não será paga";
//		
//		int response = ShowMessage.questionMessage(frame, title, message); 
//		
//		if(response == JOptionPane.YES_OPTION) {
//			frame.dispose();			
//		}
//		
//	}
//	
//	public void pay(Bill bill, double payedValue, double value) {
//		
//		if(!verifyPayment(payedValue, value)) return;
//		if(!confirmPayment()) return;
//		
//		try {
//		
//			int billId = bill.getId();
//						
//			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM installment "
//					+ "WHERE date = (SELECT MIN(date) FROM installment WHERE paid = 0 AND bill = ?) AND bill = ?;", new Object[]{billId, billId});
//			resultSet.next();
//			int installmentId = resultSet.getInt("id");
//												
//			String sql = "UPDATE installment SET payment_date = ?, payed_value = ?, paid = '1' WHERE id = ?";
//			dataBase.executeUpdate(sql, new Object[]{new java.sql.Date(System.currentTimeMillis()), payedValue, installmentId});
//			
//			resultSet = dataBase.executeQuery("SELECT EXISTS (SELECT * FROM installment WHERE paid = 0 AND bill = ?);", billId);
//			resultSet.next();
//			boolean flag = (resultSet.getInt(1) == 1) ? true : false;
//			
//			if(!flag) {
//				dataBase.executeUpdate("UPDATE bill SET payed = ? WHERE id = ?", new Object[]{1, billId});
//			}
//			
//			String title = "Conta paga";
//			String message = "A conta foi paga com sucesso";
//			
//			ShowMessage.successMessage(frame, title, message);
//			
//			frame.dispose();
//		
//		} catch(SQLException e) {
//			e.printStackTrace();
//			DataBase.showDataBaseErrorMessage();
//		}
//				
//	}
//	
//	private boolean confirmPayment() {
//		
//		String title = "Confirmar o pagamento da Conta";
//		String message = "Deseja realizar o pagamento da conta?";
//		
//		int response = ShowMessage.questionMessage(frame, title, message);
//		
//		return response == JOptionPane.YES_OPTION; 
//		
//	}
//	
//	private boolean verifyPayment(double payedValue, double value) {
//		
//		boolean flag = true;
//		
//		String title = "Erro ao registrar o pagamento de conta";
//		String message = null;
//		
//		if(payedValue <= 0) {
//			flag = false;
//			message = "Por favor indique o valor pago";
//		}
//		else if(payedValue < value) {
//			flag = false;
//			message = "O valor pago não pode ser inferior ao valor da conta";
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
//}
