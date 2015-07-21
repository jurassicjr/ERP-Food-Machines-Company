package rh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

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

import model.EPI;
import model.Employee;
import model.RemoveOfEPI;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import rh.controller.RemoveOfEPIController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class RemoveOfEPIFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6428111686715328930L;
	private JPanel principalPanel;
	private JPanel bottonPanel;
	private JScrollPane scrollPane;
	
	private DateField txtDate;
	
	private JLabel lblEpi;
	private JLabel lblEmployee;
	private JLabel lblQuantidade;
	private JLabel lblMotive;
	private JLabel lblDate;

	private JComboBox<EPI> cboEPI;
	private JComboBox<Employee> cboEmployee;

	private JSpinner spinnerAmmount;
	
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnClear;
	
	private RemoveOfEPIController controller;
	private JTextArea txtMotive;

	public RemoveOfEPIFrame() {
		controller = new RemoveOfEPIController(this);
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Retirada de EPI's");
		setBounds(100, 100, 400, 348);
		setMinimumSize(new Dimension(400, 348));
		setPreferredSize(new Dimension(400, 348));
		Icon.setIcon(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblEpi = new JLabel("EPI");
		
		cboEPI = new JComboBox<EPI>();
		new ComboBoxAutoCompletion(cboEPI);
		controller.fillEPI(cboEPI);
		cboEPI.setSelectedIndex(-1);
		
		lblEmployee = new JLabel("Funcionário");
		
		cboEmployee = new JComboBox<Employee>();
		new ComboBoxAutoCompletion(cboEmployee);
		controller.fillEmployees(cboEmployee);
		cboEmployee.setSelectedIndex(-1);
		
		lblQuantidade = new JLabel("Quantidade");
		
		spinnerAmmount = new JSpinner();
		
		lblMotive = new JLabel("Motivo da Retirada");
		
		lblDate = new JLabel("Data");
		
		txtDate = CalendarFactory.createDateField();
		
		scrollPane = new JScrollPane();
		
		JSeparator separator = new JSeparator();
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(
			gl_principalPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(separator, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblEpi)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboEPI, 0, 341, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblEmployee)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboEmployee, 0, 279, Short.MAX_VALUE))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addComponent(lblQuantidade)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerAmmount, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblDate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblMotive))
					.addContainerGap())
		);
		gl_principalPanel.setVerticalGroup(
			gl_principalPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_principalPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEpi)
						.addComponent(cboEPI, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployee)
						.addComponent(cboEmployee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantidade)
						.addComponent(spinnerAmmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDate)
						.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblMotive)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(71, Short.MAX_VALUE))
		);
		
		txtMotive = new JTextArea();
		txtMotive.setLineWrap(true);
		txtMotive.setWrapStyleWord(true);
		scrollPane.setViewportView(txtMotive);
		principalPanel.setLayout(gl_principalPanel);
		initializeBotton();
	}

	private void initializeBotton() {
		bottonPanel = new JPanel();
		getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnConfirm = new JButton("Confirmar");
		btnConfirm.setIcon(new ImageIcon(RemoveOfEPIFrame.class.getResource("/resources/ok.png")));
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(RemoveOfEPIFrame.class.getResource("/resources/1419366170_17-16.png")));
		
		btnClear = new JButton("Limpar");
		btnClear.setIcon(new ImageIcon(RemoveOfEPIFrame.class.getResource("/resources/ClearFrame.png")));
		
		bottonPanel.add(btnClear);
		bottonPanel.add(btnCancel);
		bottonPanel.add(btnConfirm);
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
				if(e.getSource().equals(btnConfirm))confirm();
				else if(e.getSource().equals(btnClear))clear();
				else if(e.getSource().equals(btnCancel))controller.close();
			}
		};
		
		btnCancel.addActionListener(buttonListener);
		btnClear.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		
		ActionListener cboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboEPI))showAmmount();
			}
		};
		cboEPI.addActionListener(cboListener);
	}
	
	private void confirm(){
		int i = ShowMessage.questionMessage(this, "Retirada", "Deseja realmente realizar a retirada desse EPI ?");
		if(i == JOptionPane.NO_OPTION)return;
		if(cboEPI.getSelectedIndex() == -1){
			ShowMessage.errorMessage(this, "Erro", "Selecione o EPI a ser retirado");
			return;
		}
		if(cboEmployee.getSelectedIndex() == -1){
			ShowMessage.errorMessage(this, "Erro", "Selecione o funcionário que fará a retirada");
			return;
		}
		if(txtMotive.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Especifíque o motivo da retirada");
		}
		EPI epi = (EPI) cboEPI.getSelectedItem();
		Employee e = (Employee) cboEmployee.getSelectedItem();
		int ammount = (int) spinnerAmmount.getValue();
		String motive = txtMotive.getText();
		Date date = (Date) txtDate.getValue();
		RemoveOfEPI rEPI = new  RemoveOfEPI();
		rEPI.setMotive(motive);
		rEPI.setEpi(epi);
		rEPI.setE(e);
		rEPI.setDate(date);
		rEPI.setAmmount(ammount);
		if(epi.getAmmount() < ammount){
			ShowMessage.errorMessage(this, "Erro", "A quantidade para retirada é maior do que a do estoque atual!");
			return;
		}
		ShowMessage.successMessage(this, "Sucesso", "Retirada efetuada com sucesso!");
		controller.removeEPI(rEPI);
		ClearFrame.clear(this);
		cboEPI.removeAllItems();
		controller.fillEPI(cboEPI);
		cboEPI.setSelectedIndex(-1);
	}
	
	private void clear(){
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente limpar os dados?");
		if(i == JOptionPane.NO_OPTION)return;
		ClearFrame.clear(this);
	}
	
	private void showAmmount(){
		if(cboEPI.getSelectedIndex() == -1)return;
		EPI epi =(EPI) cboEPI.getSelectedItem();
		ShowMessage.successMessage(this, "Quantidade", "quantidade atual desse epi: " + epi.getAmmount());
	}
}
