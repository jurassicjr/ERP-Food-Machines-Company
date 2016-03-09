package financial.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import database.DataBase;
import database.dao.BillDAO;
import database.dao.InstallmentDAO;
import financial.view.RegisterBillFrame;
import model.Bill;
import model.BillGroup;
import model.BillName;
import model.BillSubGroup;
import model.CNPJ;
import model.Employee;
import model.Installment;
import model.User;
import util.ClearFrame;
import util.ShowMessage;

public class RegisterBillFrameController {
	
	private DataBase dataBase;
	private RegisterBillFrame frame;
	
	public RegisterBillFrameController(RegisterBillFrame frame) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
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
	
	public void closeFrame() {
		
		String title = "Sair do Registro de Contas a Pagar";
		String message = "Deseja realmente sair?\nA conta não será registrada";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
			frame.dispose();			
		}
		
	}

	public void register(double value, Date date, String observation, String creditor, 
			BillName billName, BillSubGroup subGroup, BillGroup billGroup, boolean hasName,
			CNPJ cnpj, boolean hasEntry, double entryValue, int nInstallments, List<Installment> installments) {
		
		if(!validateData(value, date, observation, creditor, billName, subGroup, billGroup, hasName, cnpj, hasEntry, entryValue, nInstallments)) return;
		
		int respose = ShowMessage.questionMessage(frame, "Confirmar dados", "Deseja registrar esta conta a pagar?");
		
		if(respose != JOptionPane.YES_OPTION) return;
		
		Employee e = new Employee();
		User user = new User(e, null);
		user.setId(123);
		
		Bill bill = new Bill(subGroup, billName, value, date, creditor, entryValue, observation, nInstallments, user, cnpj, Bill.TO_PAY);
		bill.setId(dataBase.getAutoIncrementValue("bill"));
		boolean resultBill = new BillDAO().persist(bill);
		
		boolean resultInstallments = true;
		
		if(nInstallments > 1) {
			resultInstallments = new InstallmentDAO().persist(installments, bill);
		}
		
		if(resultBill && resultInstallments) 
			ShowMessage.successMessage(frame, "Conta Registrada", "A Conta a Pagar foi Registrada com sucesso");
		
		frame.dispose();
		
	}
	
	private boolean validateData(double value, Date date, String observation, String creditor, 
			BillName billName, BillSubGroup subGroup, BillGroup billGroup, boolean hasName,
			CNPJ cnpj, boolean hasEntry, double entryValue, int nInstallments) {
		
		boolean validate = true;
		String label = "";
		
		if(billGroup == null) {
			label = "Grupo da Conta";
			validate = false;
		}
		else if(subGroup == null) {
			label = "SubGrupo da Conta";
			validate = false;
		}
		else if(hasName && billName == null) {
			label = "Conta";
			validate = false;
		}
		else if(value <= 0.0) {
			label = "Valor da Conta";
			validate = false;
		}
		else if(creditor == null || creditor.isEmpty()) {
			label = "Credor";
			validate = false;
		}
		else if(cnpj == null) {
			label = "CNPJ";
			validate = false;
		}
		else if(nInstallments == 1) {
			if(date == null) label = "Data de Vencimento";
			else validate = true;
		}
		else if(validate) {
			
			if(hasEntry && entryValue <= 0.0) {
				label = "Valor de Entrada";
				validate = false;
			}
			
		}
				
		if(!validate) {
			
			String title = "Erro ao registrar conta a pagar";
			String message = "Por favor verifique o seguinte campo para registro da conta a pagar:\n" + label;
			ShowMessage.errorMessage(frame, title, message);
			
		}
		
		return validate;
		
	}
	
	public void setBills(JComboBox<BillGroup> groups) {
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM bill_group");
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String code = resultSet.getString("cod");
				String name = resultSet.getString("group_name");
								
				ArrayList<BillSubGroup> subgroups = getSubgroups(id);
				BillGroup billGroup = new BillGroup(id, code, name, subgroups);
				groups.addItem(billGroup);
				
			}
			
			groups.setSelectedIndex(-1);
			
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
	}
	
	public void setSubGroups(BillGroup group, JComboBox<BillSubGroup> subgroups) {
		
		if(group == null) return;
		
		subgroups.removeAllItems();
		
		for(BillSubGroup subGroup : group.getSubGroups()) {
			subgroups.addItem(subGroup);
		}
		
		subgroups.setEnabled(true);
		subgroups.setSelectedIndex(0);
		
	}
	
	public void setBillnames(BillSubGroup subGroup, JComboBox<BillName> names) {	
		
		names.removeAllItems();
		
		for(BillName name : subGroup.getBillNames()) {
			names.addItem(name);
		}
		
		names.setSelectedIndex(-1);
		names.setEnabled(names.getItemCount() != 0);
		
	}
	
	private ArrayList<BillSubGroup> getSubgroups(int idBillGroup) {
		
		ArrayList<BillSubGroup> subGroups = new ArrayList<>();
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM bill_subgroup WHERE group_id = ?", idBillGroup);
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String code = resultSet.getString("cod");
				String name = resultSet.getString("subgroup");
				
				ArrayList<BillName> billNames = getBillNames(id);
				subGroups.add(new BillSubGroup(id, code, name, billNames));
								
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
		return subGroups;
		
	}
	
	private ArrayList<BillName> getBillNames(int idBillSubGroup) {
		
		ArrayList<BillName> billNames = new ArrayList<>();
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM bill_name WHERE subgroup = ?", idBillSubGroup);
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String code = resultSet.getString("cod");
				String name = resultSet.getString("bill_name");
				
				billNames.add(new BillName(id, code, name));		
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
		return billNames;
		
	}
	
}
