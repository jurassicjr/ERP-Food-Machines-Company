package rh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;

import model.EPI;
import rh.controller.RegisterOfEPIController;
import util.ClearFrame;
import util.ShowMessage;

public class RegisterOfEPIFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1079844856555036040L;
	private JPanel principalPanel;
	private JTextField txtName;
	private JPanel bottonPanel;
	private JButton btnRegister;
	private JLabel lblName;
	private JLabel lblUseDescription;
	private JSeparator separator;
	private JButton btnCancel;
	private JButton btnClear;
	private RegisterOfEPIController controller;
	private JTextArea txtUseDescription;

	public RegisterOfEPIFrame() {
		controller = new RegisterOfEPIController(this);
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Registro de Equipamento de Proteção individual");
		setBounds(100, 100, 470, 257);
		setPreferredSize(new Dimension(470, 257));
		setMinimumSize(new Dimension(470,257));
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblName = new JLabel("Nome");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		lblUseDescription = new JLabel("Descrição de uso");
		
		JScrollPane scrollPane = new JScrollPane();
		
		separator = new JSeparator();
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addGap(15)
							.addComponent(lblUseDescription))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addGap(7)
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblUseDescription)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		
		txtUseDescription = new JTextArea();
		scrollPane.setViewportView(txtUseDescription);
		principalPanel.setLayout(gl_principalPanel);
		
		initializeBotton();
	}

	private void initializeBotton() {
		bottonPanel = new JPanel();
		getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnRegister = new JButton("Registrar");
		btnRegister.setIcon(new ImageIcon(RegisterOfEPIFrame.class.getResource("/resources/ok.png")));
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(RegisterOfEPIFrame.class.getResource("/resources/1419366170_17-16.png")));
		
		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(RegisterOfEPIFrame.class.getResource("/resources/ClearFrame.png")));
		
		bottonPanel.add(btnClear);
		bottonPanel.add(btnCancel);
		bottonPanel.add(btnRegister);
	}
	
	private void setListeners(){
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.close();
			}
		});
		
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnRegister))register();
				else if(e.getSource().equals(btnCancel))controller.close();
				else if(e.getSource().equals(btnClear))Clear();
			}
		};
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnRegister.addActionListener(buttonListener);
	}
	
	private void register(){
		int i = ShowMessage.questionMessage(this, "Registrar", "Deseja realmente registrar esse epi?");
		if(i == JOptionPane.NO_OPTION)return;
		if(txtName.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Erro ao registar insira o nome do epi");
			return;
		}
		if(txtUseDescription.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Erro ao registrar insira a descrição de uso do epi");
			return;
		}
		EPI epi = new EPI();
		String name = txtName.getText();
		String useDescription = txtUseDescription.getText();
		epi.setName(name);
		epi.setUseDescription(useDescription);
		controller.register(epi);
		ShowMessage.successMessage(this, "Sucesso", "Sucesso ao registrar Equipamento de Proteção Individual");
	}
	
	private void Clear(){
		ClearFrame.clear(this);
	}
}
