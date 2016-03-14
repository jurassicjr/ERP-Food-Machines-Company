package financial.controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.DataBase;
import database.dao.BillDAO;
import database.dao.CnpjDAO;
import financial.view.ListBillsFrame;
import financial.view.PayBillFrame;
import model.Bill;
import model.BillGroup;
import model.BillName;
import model.BillSubGroup;
import model.CNPJ;
import model.User;

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
			
			String sql = "SELECT * FROM bill WHERE bill.paid = 0;";
			
			ResultSet resultSet = dataBase.executeQuery(sql);
			
			while(resultSet.next()) {
				
				BillGroup billGroup = getBillGroup(resultSet.getInt("bill_subgroup"));
				BillSubGroup billSubGroup = billGroup.getSubGroups().get(0);
				BillName billName = getBillName(resultSet.getInt("bill_name"));
				
				Integer id = resultSet.getInt("id");
				double value = resultSet.getDouble("value");
				Date expiration = resultSet.getDate("expiration");
				String creditor = resultSet.getString("creditor");
				double entryValue = resultSet.getDouble("entry_value");
				String observation = resultSet.getString("observation");
				int nInstallments = resultSet.getInt("n_installments");
				User user = new User(resultSet.getInt("user"));
				CNPJ cnpj = new CnpjDAO().getByID(resultSet.getInt("cnpj"));
				int type = resultSet.getInt("type");
				boolean paid = resultSet.getBoolean("paid");
				
				Bill bill = new Bill(id, billGroup, billSubGroup, billName, value, expiration, creditor, 
						entryValue, observation, nInstallments, user, cnpj, type, paid);
				
				addBillToTable(bills, bill);
				
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
		return true;
		
	}
	
	private void addBillToTable(JTable table, Bill bill) {
		
		int row = table.getRowCount();
		
		((DefaultTableModel) table.getModel()).addRow(new Object[]{null, null, null, null});
		
		table.setValueAt(bill.toString(), row, 0);
		table.setValueAt(bill.getCreditor(), row, 1);
		table.setValueAt(bill.getCnpj().getFormattedCnpj(), row, 2);
		
		String installments = "";
		
		if(Double.compare(bill.getEntryValue(), 0.0) != 0)
			installments = "1 + ";
		
		installments = installments + bill.getnInstallments();
		
		table.setValueAt(installments, row, 3);
		
		Date nextPayment = new BillDAO().getNextPaymentDate(bill);
		
		table.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(nextPayment), row, 4);
		
		table.setValueAt(NumberFormat.getCurrencyInstance().format(bill.getValue()), row, 5);
		
	}
	
	private BillGroup getBillGroup(int subGroupId) throws SQLException {
		
		String sql = "SELECT subgroup.cod AS 'subgroup_cod', subgroup.id AS 'subgroup_id', subgroup.subgroup, b_group.cod AS 'group_cod', b_group.group_name, b_group.id AS 'group_id' " +
				"FROM bill_subgroup subgroup " +
				"JOIN bill_group b_group " +
				"ON subgroup.group_id = b_group.id " +
				"WHERE subgroup.id = " + subGroupId;
		
		ResultSet resultSet = dataBase.executeQuery(sql);
		resultSet.next();
		
		String subGroupCode = resultSet.getString("subgroup_cod");
		String subGroupName = resultSet.getString("subgroup");
		
		String groupCode = resultSet.getString("group_cod");
		String groupName = resultSet.getString("group_name");
		int groupId = resultSet.getInt("group_id");
		
		BillSubGroup billSubGroup = new BillSubGroup(subGroupId, subGroupCode, subGroupName);
		
		BillGroup billGroup = new BillGroup(groupId, groupCode, groupName);
		billGroup.getSubGroups().add(billSubGroup);
		
		return billGroup;
	}
	
	public BillName getBillName(int billNameId) throws SQLException {
		
		BillName billName = null;
		 
		if(billNameId != 0) {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM bill_name WHERE id = " + billNameId);
			resultSet.next();
			
			billName = new BillName(billNameId, resultSet.getString("cod"), resultSet.getString("bill_name"));
			
		}
		
		return billName;
		
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
