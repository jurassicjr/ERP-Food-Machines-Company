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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.EPI;
import rh.controller.UpdateOfEPiController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class UpdateOfEPIFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2168417071031844771L;
	private UpdateOfEPiController controller;
	private JPanel principalPanel;
	private JTextField txtName;
	private JLabel lblEpi;
	private JComboBox<EPI> cboEPI;
	private JLabel lblName;
	private JScrollPane scrollPane;
	private JPanel bottonPanel;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnClose;
	private JLabel lbluseDescription;
	private JTextArea txtUseDescription;

	public UpdateOfEPIFrame() {
		controller = new UpdateOfEPiController(this);
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Atualização/Remoção de Equipamento de Proteção Individual");
		setBounds(100, 100, 470, 338);
		setMinimumSize(new Dimension(470, 338));
		setPreferredSize(new Dimension(470, 338));
		Icon.setIcon(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblEpi = new JLabel("EPI:");
		
		cboEPI = new JComboBox<EPI>();
		new ComboBoxAutoCompletion(cboEPI);
		controller.fillEPIs(cboEPI);
		cboEPI.setSelectedIndex(-1);
		
		lblName = new JLabel("Nome");
		
		txtName = new JTextField();
		txtName.setColumns(10);
		
		lbluseDescription = new JLabel("Descrição de Uso");
		
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
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblEpi)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cboEPI, 0, 406, Short.MAX_VALUE))
								.addGroup(gl_principalPanel.createSequentialGroup()
									.addComponent(lblName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE))
								.addComponent(lbluseDescription)))
						.addGroup(gl_principalPanel.createSequentialGroup()
							.addGap(13)
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)))
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
					.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lbluseDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(107, Short.MAX_VALUE))
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
		
		btnUpdate = new JButton("Atualizar");
		btnUpdate.setIcon(new ImageIcon(UpdateOfEPIFrame.class.getResource("/resources/update.png")));
		
		btnDelete = new JButton("Deletar");
		btnDelete.setIcon(new ImageIcon(UpdateOfEPIFrame.class.getResource("/resources/cancel.png")));
	
		btnClose = new JButton("Cancelar");
		btnClose.setIcon(new ImageIcon("/resources/clear.png"));
		
		bottonPanel.add(btnClose);
		bottonPanel.add(btnDelete);
		bottonPanel.add(btnUpdate);
		
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
				if(e.getSource().equals(btnUpdate))update();
				else if(e.getSource().equals(btnDelete))delete();
				else if(e.getSource().equals(btnClose))close();
			}
		};
		
		btnClose.addActionListener(buttonListener);
		btnDelete.addActionListener(buttonListener);
		btnUpdate.addActionListener(buttonListener);
		
		ActionListener cboListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboEPI))fillFields();
			}
		};
		
		cboEPI.addActionListener(cboListener);
	}
	
	private void update(){
		int i = ShowMessage.questionMessage(this, "Atualização", "deseja realmente atualizar esse epi");
		if(i == JOptionPane.NO_OPTION)return;
		if(txtName.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Erro ao atualizar insira um nome");
			return;
		}
		if(txtUseDescription.getText().isEmpty()){
			ShowMessage.errorMessage(this, "Erro", "Erro ao atualizar Insira a descrição de uso");
			return;
		}
		String name = txtName.getText();
		String useDescription = txtUseDescription.getText();
		EPI epi = (EPI) cboEPI.getSelectedItem();
		epi.setName(name);
		epi.setUseDescription(useDescription);
		controller.update(epi);
		ShowMessage.successMessage(this, "Sucesso", "Sucesso ao atualizar o Equipamento de Proteção Individual");
	}
	
	private void delete(){
		int i = ShowMessage.questionMessage(this, "Deletar", "Deseja realmente deletar esse produto?");
		if(i==JOptionPane.NO_OPTION)return;
		EPI epi = (EPI) cboEPI.getSelectedItem();
		controller.delete(epi);
		ClearFrame.clear(this);
		ShowMessage.successMessage(this, "Sucesso", "EPI excluida com sucesso!");
	}
	
	private void close(){
		controller.close();
	}
	
	private void fillFields(){
		if(cboEPI.getSelectedIndex() == -1)return;
		EPI epi = (EPI) cboEPI.getSelectedItem();
		String name = epi.getName();
		String useDescription = epi.getUseDescription();
		txtName.setText(name);
		txtUseDescription.setText(useDescription);
	}
}
