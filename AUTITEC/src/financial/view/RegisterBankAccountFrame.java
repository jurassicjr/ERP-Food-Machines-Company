package financial.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Bank;
import model.BankAccount;
import userInterface.components.ComboBoxAutoCompletion;
import userInterface.components.JNumberFormatField;
import util.ClearFrame;
import util.ShowMessage;
import financial.controller.RegisterBankAccountController;

public class RegisterBankAccountFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8279291334400678335L;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	private JLabel lblBank;
	private JComboBox<model.Bank> cboBank;
	private JLabel lblAgency;
	private JTextField txtAgency;
	private JLabel lblAccount;
	private JLabel lblsafeMoneyAccount;
	private JTextField txtAccount;
	private JTextField txtSafeMoneyAccount;
	private JLabel lblInicialValue;
	private JTextField txtInicialValue;
	private RegisterBankAccountController controller;

	public RegisterBankAccountFrame() {
		controller = new RegisterBankAccountController(this);
		initialize();
		setListeners();
		
	}

	private void initialize() {
		setTitle("Registro de Conta Bancária");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 426, 306);
		setPreferredSize(new Dimension(400, 306));
		setMinimumSize(new Dimension(400, 300));
		initializePrincipal();

	}

	private void initializePrincipal() {
		JPanel principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblBank = new JLabel("Banco");
				
		cboBank = new JComboBox<Bank>();
		
		new ComboBoxAutoCompletion(cboBank);
		
		controller.fillBanks(cboBank);
		
		lblAgency = new JLabel("Agência");
		
		txtAgency = new JTextField();
		txtAgency.setColumns(10);
		
		lblAccount = new JLabel("Conta Corrente");
		
		lblsafeMoneyAccount = new JLabel("Conta Poupança");
		
		txtAccount = new JTextField();
		txtAccount.setColumns(10);
		
		txtSafeMoneyAccount = new JTextField();
		txtSafeMoneyAccount.setColumns(10);
		
		lblInicialValue = new JLabel("Valor Inicial");
		
		txtInicialValue = new JNumberFormatField(new DecimalFormat("R$ 0.00"));
		txtInicialValue.setColumns(10);
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblBank)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboBank, 0, 357, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblAgency)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtAgency, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblAccount)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtAccount, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblsafeMoneyAccount)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSafeMoneyAccount, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblInicialValue)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtInicialValue, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBank)
						.addComponent(cboBank, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAgency)
						.addComponent(txtAgency, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAccount)
						.addComponent(txtAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblsafeMoneyAccount)
						.addComponent(txtSafeMoneyAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInicialValue)
						.addComponent(txtInicialValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(214, Short.MAX_VALUE))
		);
		principalPanel.setLayout(gl_principalPanel);
		
		initializeSub();
	}

	private void initializeSub() {
	    JPanel subPanel = new JPanel();
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(RegisterBankAccountFrame.class.getResource("/resources/ok.png")));
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(RegisterBankAccountFrame.class.getResource("/resources/cancel.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(RegisterBankAccountFrame.class.getResource("/resources/ClearFrame.png")));
	    
	    subPanel.add(btnClear);
	    subPanel.add(btnCancel);
	    subPanel.add(btnConfirm);
    }
	
	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.close();
			}
		});
		
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnCancel))controller.close();
				else if(e.getSource().equals(btnClear))clearFrame();
				else if(e.getSource().equals(btnConfirm))register();
			}
		};
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
	}
	
	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar os campos?");
		if(i == JOptionPane.YES_OPTION) {
			ClearFrame.clear(this);
		}
	}
	
	private void register() {
		int i = ShowMessage.questionMessage(this, "Registro", "Deseja realmente registrar essa conta bancária?");
		if(i == JOptionPane.YES_OPTION) {
			boolean isOk = verifyFields();
			if(isOk) {
				BankAccount ba = new BankAccount();
				ba.setBank((Bank) cboBank.getSelectedItem());
				ba.setAccount(txtAccount.getText());
				ba.setAgency(txtAgency.getText());
				ba.setSafeMoneyAccount(txtSafeMoneyAccount.getText());
				ba.setInicialValue(Double.parseDouble(txtInicialValue.getText().replaceAll("R|\\$", "").replaceAll(",", ".")));
				controller.register(ba);
				ShowMessage.successMessage(this, "Sucesso", "Registro realizado com sucesso!");
				ClearFrame.clear(this);
			}
		}
	}

	private boolean verifyFields() {
	    if(cboBank.getSelectedIndex() == -1) {
	    	ShowMessage.errorMessage(this, "Erro", "Selecione um banco");
	    	return false;
	    }
	    if(txtAccount.getText().isEmpty()) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o numero da conta corrente");
	    	return false;
	    }
	    if(txtAgency.getText().isEmpty()) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira a agência!");
	    	return false;
	    }
	    if(txtSafeMoneyAccount.getText().replaceAll("R|\\$", "").replaceAll(",", "\\.").trim().isEmpty()) {
	    	ShowMessage.errorMessage(this, "Erro", "Insira o valor inicial da conta!");
	    }
	    return true;
    }
}
