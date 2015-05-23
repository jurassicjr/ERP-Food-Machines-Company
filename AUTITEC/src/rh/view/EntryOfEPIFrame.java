package rh.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import rh.controller.EntryOfEPIController;
import util.ClearFrame;
import util.ShowMessage;
import model.EPI;

public class EntryOfEPIFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9017483492910826759L;
	private JLabel lblCnpj;
	private JComboBox<String> cboCNPJ;
	private JLabel lblEpi;
	private JComboBox<EPI> cboEPI;
	private JLabel lblAmmount;
	private JScrollPane scrollPane;
	private JLabel lblObservation;
	private JSpinner ammountSpinner;
	private JTextArea txtObservation;
	private JPanel bottonPanel;
	private JButton btnAdd;
	private JButton btnCancel;
	private JButton btnClear;
	private EntryOfEPIController controller;

	public EntryOfEPIFrame() {
		
		controller = new EntryOfEPIController(this);
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Entrada de EPI's");
		setBounds(100, 100, 400, 400);
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
		JPanel principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblCnpj = new JLabel("CNPJ");
		
		cboCNPJ = new JComboBox<String>();
		
		lblEpi = new JLabel("EPI");
		
		cboEPI = new JComboBox<EPI>();
		
		lblAmmount = new JLabel("Quantidade");
		
		ammountSpinner = new JSpinner();
		
		lblObservation = new JLabel("Observação");
		
		scrollPane = new JScrollPane();
		
		JSeparator separator = new JSeparator();
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblCnpj)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboCNPJ, 0, 330, Short.MAX_VALUE))
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblEpi)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboEPI, 0, 341, Short.MAX_VALUE))
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblAmmount)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(ammountSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblObservation)))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addGap(13)
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCnpj)
						.addComponent(cboCNPJ, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEpi)
						.addComponent(cboEPI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAmmount)
						.addComponent(ammountSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblObservation)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(107, Short.MAX_VALUE))
		);
		
		txtObservation = new JTextArea();
		txtObservation.setLineWrap(true);
		txtObservation.setWrapStyleWord(true);
		scrollPane.setViewportView(txtObservation);
		principalPanel.setLayout(gl_principalPanel);
		
		intializeBotton();
	}

	private void intializeBotton() {
		bottonPanel = new JPanel();
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		
		btnAdd = new JButton("Adicionar");
		btnAdd.setIcon(new ImageIcon(EntryOfEPIFrame.class.getResource("/resources/plus.png")));
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(EntryOfEPIFrame.class.getResource("/resources/1419366170_17-16.png")));
		
		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(EntryOfEPIFrame.class.getResource("resources/ClearFrame.png")));
	
		bottonPanel.add(btnClear);
		bottonPanel.add(btnCancel);
		bottonPanel.add(btnAdd);
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
				if(e.getSource().equals(btnAdd))add();
				else if(e.getSource().equals(btnCancel))controller.close();
				else if(e.getSource().equals(btnClear))clear();
			}
		};
		btnAdd.addActionListener(buttonListener);
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
	}
	
	private void add(){
		if(cboCNPJ.getSelectedIndex() == -1){
			ShowMessage.errorMessage(this, "Erro", "Erro ao adicionar epi, insira o CNPJ de entrada");
			return;
		}
		if(cboEPI.getSelectedIndex() == -1){
			ShowMessage.errorMessage(this, "Erro", "Erro ao adicionar epi, selecione um epi");
			return;	
		}
		if(ammountSpinner.getValue().equals(0)){
			ShowMessage.errorMessage(this, "Erro", "Erro ao adicionar epi, insira a quantidade a ser adicionada");
			return;
		}
		int i = ShowMessage.questionMessage(this, "Adicionar", "Deseja realmente adicionar essa quantidade nesse epi ?");
		if(i == JOptionPane.NO_OPTION)return;
	}
	
	private void clear(){
		ClearFrame.clear(this);
	}
}
