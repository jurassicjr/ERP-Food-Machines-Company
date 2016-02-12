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

import userInterface.components.UpperTextField;
import util.Icon;
import util.ShowMessage;

public class RegisterOfMaterialTypeView extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = 2761993095691908427L;
	private JTextField txtName;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JLabel lblName;
	private RegisterOfMaterialTypeController controller;
	private JFrame frame;

	
    public RegisterOfMaterialTypeView() {
    	controller = new RegisterOfMaterialTypeController(this);
    	initialize();
    	setListeners();
    }


	public RegisterOfMaterialTypeView(RegisterOfMaterialFrame frame) {
		this.frame = frame;
		controller = new RegisterOfMaterialTypeController(this);
    	initialize();
    	setListeners();
	    
    }


	private void initialize() {
		this.setTitle("Cadastro de Tipo de Material");
		Icon.setIcon(this);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 450, 112);
		setPreferredSize(new Dimension(450, 84));
		setMinimumSize(new Dimension(450, 84));
		initializePrincipal();
    }


	private void initializePrincipal() {
		JPanel principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		
		lblName = new JLabel("Nome");
		
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
					.addContainerGap(364, Short.MAX_VALUE))
		);
		principalPanel.setLayout(gl_principalPanel);
		initializeSub();
    }


	private void initializeSub() {
	    JPanel subPanel = new JPanel();
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(RegisterOfMaterialTypeView.class.getResource("/resources/ok.png")));
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(RegisterOfMaterialTypeView.class.getResource("/resources/cancel.png")));
	    
	    
	    subPanel.add(btnCancel);
	    subPanel.add(btnConfirm);
    }
	
	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				((RegisterOfMaterialFrame) frame).refresh();
				controller.close();
			}
		});
		
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnConfirm))confirm();
				else if(e.getSource().equals(btnCancel))controller.close();
			}
		};
		btnCancel.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
	}
	
	private void confirm() {
		
		int i = ShowMessage.questionMessage(this, "Registrar", "Deseja realmente realizar este registro ? ");
		if(i == JOptionPane.NO_OPTION)return;
		if(txtName.getText().isEmpty()) {
			ShowMessage.questionMessage(this, "Erro", "Insira o nome do tipo!");
			return;
		}
		String name = txtName.getText();
		controller.register(name);
		ShowMessage.successMessage(this, "Sucesso!", "Registro realizado com sucesso!");
		((RegisterOfMaterialFrame) frame).refresh();
		this.dispose();
	}
	
}
