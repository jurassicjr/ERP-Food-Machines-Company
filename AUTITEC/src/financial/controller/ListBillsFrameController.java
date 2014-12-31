package financial.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import util.ShowMessage;
import model.Bill;
import model.Installment;
import database.DataBase;
import financial.view.ListBillsFrame;
import financial.view.PayBillFrame;

public class ListBillsFrameController {
	
	private DataBase dataBase;
	private ListBillsFrame frame;
	
	public ListBillsFrameController(ListBillsFrame frame) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
		this.frame = frame;
	}
	
	public boolean setBills(JTable bills) {
		
		try {
			
			String sql = "SELECT installment.*, bill.bill as 'bill_name', bill.creditor, bill.observation "
					+ "FROM bill, installment "
					+ "WHERE installment.bill = bill.id AND installment.date = (SELECT MIN(date) FROM installment) AND bill.payed = 0;";
			
			ResultSet resultSet = dataBase.executeQuery(sql);
			
			int row = 0;
			
			while(resultSet.next()) {
								
				addTableRow(bills);		
				
				String bill = resultSet.getString("bill_name");
				String creditor = resultSet.getString("creditor");
				String observation = resultSet.getString("observation");
				int billId = resultSet.getInt("bill");
				Bill b = new Bill(bill, creditor, observation, billId);
				
				Date date = resultSet.getDate("date");
				double value = resultSet.getDouble("value");
				Installment i = new Installment(date, value);
				
				sql = "SELECT (SELECT COUNT(*) FROM installment WHERE paid = 1 AND bill = ?) as 'paid_bill', "
						+ "(SELECT COUNT(*) FROM installment WHERE bill = ?) as 'total_bill'";
				
				ResultSet rs = dataBase.executeQuery(sql, new Object[]{billId, billId});
				rs.next();
				
				int paidBill = rs.getInt("paid_bill");
				int totalBill = rs.getInt("total_bill");
				
				bills.setValueAt(b, row, 0);
				bills.setValueAt(b.getCreditor(), row, 1);
				bills.setValueAt("R$ " + String.format("%.2f", i.getValue()), row, 2);
				bills.setValueAt((paidBill + 1) + "/" + totalBill , row, 3);

				++row;				
			}
			
			if(row == 0) {
				
				String title = "Não existem contas a pagar";
				String message = "Não existe nenhuma conta a pagar";
				
				ShowMessage.successMessage(frame, title, message);
				
				return false;
				
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
		return true;
		
	}
	
	private void addTableRow(JTable table) {
		((DefaultTableModel) table.getModel()).addRow(new Object[]{null, null, null, null});
	}

	public void payBill(Bill bill, double value) {
		
		frame.setVisible(false);
				
		PayBillFrame payBillFrame = new PayBillFrame(bill, value);
		payBillFrame.setVisible(true);
		payBillFrame.setLocationRelativeTo(frame);
		
		ListBillsFrame listBillsFrame = new ListBillsFrame(true);
		listBillsFrame.setVisible(true);
		listBillsFrame.setLocationRelativeTo(frame);
		listBillsFrame.requestFocus();
		
		frame.dispose();
		
	}
	
}
