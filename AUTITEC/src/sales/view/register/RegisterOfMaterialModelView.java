package sales.view.register;

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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import sales.controller.RegisterOfMaterialModelController;
import userInterface.components.UpperTextField;
import util.Icon;
import util.ShowMessage;

public class RegisterOfMaterialModelView extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = 1596127022728587123L;
	private JTextField txtName;
	private JButton btnConfirm;
	private JButton btnCancel;
	private RegisterOfMaterialModelController controller;
	private RegisterOfMaterialFrame frame;

    public RegisterOfMaterialModelView(RegisterOfMaterialFrame frame) {
    	this.frame = frame;
		controller = new RegisterOfMaterialModelController(this);
    	initialize();
    	setListeners();
    }

	private void initialize() {
	    Icon.setIcon(this);
	    setTitle("Registro de modelo de materiais");
	    setBounds(100, 100, 450, 112);
	    setPreferredSize(new Dimension(450, 112));
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    initializePrincipal();
    }

	private void initializePrincipal() {
	    JPanel principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    JLabel lblName = new JLabel("Nome");
	    
	    txtName = new UpperTextField();
	    txtName.setColumns(10);
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(lblName)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblName)
	    				.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap(48, Short.MAX_VALUE))
	    );
	    principalPanel.setLayout(gl_principalPanel);
	    
	    initializeSub();
    }

	private void initializeSub() {
	    JPanel subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(RegisterOfMaterialModelView.class.getResource("/resources/ok.png")));
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(RegisterOfMaterialModelView.class.getResource("/resources/cancel.png")));
	    
	    subPanel.add(btnCancel);
	    subPanel.add(btnConfirm);
    }
	
	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.refresh();
				controller.close();
			}
		});
		
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnConfirm))confirm();
				else if(e.getSource().equals(btnCancel))controller.close();
			}
		};
		btnConfirm.addActionListener(btnListener);
		btnCancel.addActionListener(btnListener);
	}
	
	private void confirm() {
		int i = ShowMessage.questionMessage(this, "Registrar", "Deseja realmente registrar esse modelo ?");
		if(i == JOptionPane.NO_OPTION)return;
		if(txtName.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Não foi possivel realizar o registro, por favor insira o nome do modelo!");
			return;
		}
		String name = txtName.getText();
		controller.register(name);
		ShowMessage.successMessage(this, "Sucesso!", "Registro realizado com sucesso!");
		frame.refresh();
		this.dispose();
	}
}
