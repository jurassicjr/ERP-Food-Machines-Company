package database.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DataBase;
import model.Bill;
import model.BillGroup;
import model.BillName;
import model.BillSubGroup;
import model.CNPJ;
import model.User;

public class BillDAO {
	
	private DataBase dataBase;
	
	public BillDAO() {
		
		dataBase = new DataBase();
		dataBase.connect();
		
	}
	
	public boolean persist(Bill bill) {
		
		Integer billSubGroup = bill.getSubGroup().getId();
		Integer billName = (bill.getBillName() == null) ? null : bill.getBillName().getId();
		Double value = bill.getValue();
		Date expirationDate = new Date(bill.getExpiration().getTime());
		String creditor = bill.getCreditor();
		Double entryValue = (bill.getEntryValue() > 0.0) ? bill.getEntryValue() : 0.0;
		String observation = bill.getObservation();
		Integer nInstallments = bill.getnInstallments();
		Integer user = bill.getUser().getId();
		Integer cnpj = bill.getCnpj().getId();
		Integer type = bill.getType();		
				
		String sql = "INSERT INTO bill (bill_subgroup, bill_name, value, expiration, creditor, entry_value, observation, n_installments, user, cnpj, type) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		Object data[] = new Object[]{billSubGroup, billName, value, expirationDate, creditor, entryValue, observation, nInstallments, user, cnpj, type};
				
		return dataBase.executeUpdate(sql, data);
								
	}
	
	public Date getNextPaymentDate(Bill bill) {
		
		Date date = null;
		Integer id = new Integer(bill.getId());
		
		String sql = "SELECT case " +
				"WHEN n_installments = 1 THEN expiration " +
				"ELSE (SELECT MIN(date) FROM installment WHERE bill = ? AND paid_date IS NULL) " +
				"END " +
				"FROM bill " +
				"WHERE id = ?";
		
		ResultSet resultSet = dataBase.executeQuery(sql, new Object[]{id, id});
		
		try {
			if(resultSet.next()) { date = resultSet.getDate(1); }
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
			
		return date;
		
	}
	
	public List<Bill> getAllUnpaid() {
		
		List<Bill> bills = new ArrayList<>();
		
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
				
				bills.add(bill);
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
		return bills;
		
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
	
}
