package financial.controller;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.NumberFormat;
//import java.util.Date;
//
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//import util.ShowMessage;
//import model.DebtToReceive;
//import database.DataBase;
//import financial.view.ListDebtsFrame;
//import financial.view.ReceiveDebtFrame;
//
public class ListDebtsFrameController {
//	
//	private ListDebtsFrame frame;
//	
//	private DataBase dataBase;
//	
//	public ListDebtsFrameController(ListDebtsFrame frame) {
//		
//		this.frame = frame;
//		
//		dataBase = new DataBase();
//		dataBase.connect();
//	}
//	
//	public boolean setDebts(JTable table) {
//		
//		try {
//			
//			int row = 0;
//			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM debt WHERE paid = 0;");
//			
//			while(resultSet.next()) {
//												
//				int id = resultSet.getInt("id");
//				String debt = resultSet.getString("debt");
//				String debtor = resultSet.getString("debtor");
//				String observation = resultSet.getString("observation");
//				Date date = resultSet.getDate("date");
//				double value = resultSet.getDouble("value");
//				
//				DebtToReceive d = new DebtToReceive(id, debt, debtor, observation, date, false, value);
//				
//				addTableRow(table);
//				
//				table.setValueAt(d, row, 0);
//				table.setValueAt(debtor, row, 1);
//				table.setValueAt(NumberFormat.getCurrencyInstance().format(value) , row, 2);
//				
//				++row;
//			}
//						
//			if(row == 0) {				
//				String title = "Não existem contas a receber";
//				String message = "Não existe nenhuma conta a receber";
//				
//				ShowMessage.successMessage(frame, title, message);
//				
//				return false;
//				
//			}
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//			DataBase.showDataBaseErrorMessage();
//		}
//		
//		return true;
//		
//	}
//	
//	private void addTableRow(JTable table) {
//		((DefaultTableModel) table.getModel()).addRow(new Object[]{null, null, null, null});
//	}
//	
//	public void receiveDebt(DebtToReceive debt, double value) {
//		
//		frame.setVisible(false);
//		
//		ReceiveDebtFrame receiveDebtFrame = new ReceiveDebtFrame(debt, value);
//		receiveDebtFrame.setVisible(true);
//		receiveDebtFrame.setLocationRelativeTo(frame);
//		
//		ListDebtsFrame debtsFrame = new ListDebtsFrame(true);
//		debtsFrame.setVisible(true);
//		debtsFrame.setLocationRelativeTo(frame);
//		debtsFrame.requestFocus();
//				
//		frame.dispose();
//		
//	}
//
}
