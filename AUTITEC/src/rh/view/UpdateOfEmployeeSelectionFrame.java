package rh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Employee;
import util.ShowMessage;
import database.dao.EmployeeDAO;

public class UpdateOfEmployeeSelectionFrame extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = -4954396668597104620L;
	private JButton btnConfirm;
	private JComboBox<Employee> cboEmployee;

    
    public UpdateOfEmployeeSelectionFrame() {
    	initialize();
    	setListeners();
    }


	private void initialize() {
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	    setTitle("Atualização de Funcionário");
	    setBounds(100, 100, 431, 113);
	    setPreferredSize(new Dimension(431, 113));
	    setMinimumSize(new Dimension(431, 113));
	    getContentPane().setLayout(new BorderLayout(0, 0));
	    initializePrincipal();
    }


	private void initializePrincipal() {
	    JPanel principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    JLabel lblFuncionrio = new JLabel("Funcionário");
	    
	    cboEmployee = new JComboBox<Employee>();
	    FillCBO(cboEmployee);
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(lblFuncionrio)
	    			.addPreferredGap(ComponentPlacement.UNRELATED)
	    			.addComponent(cboEmployee, 0, 330, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblFuncionrio)
	    				.addComponent(cboEmployee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap(82, Short.MAX_VALUE))
	    );
	    principalPanel.setLayout(gl_principalPanel);
	    
	    initializaSub();
    }


	private void initializaSub() {
	    JPanel subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(UpdateOfEmployeeSelectionFrame.class.getResource("/resources/ok.png")));
	    
	    subPanel.add(btnConfirm);
    }


	private void FillCBO(JComboBox<Employee> cbo) {
	   List<Employee> list = new EmployeeDAO().getEmployees();
	   list.forEach(e -> cbo.addItem(e));
    }
	
	private void setListeners() {
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnConfirm))createFrame();
			}
		};
		btnConfirm.addActionListener(buttonListener);
	}
	
	private void createFrame() {
		if(cboEmployee.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "erro", "Selecione um funcionario");
			return;
		}
		Employee e = (Employee) cboEmployee.getSelectedItem();
		
		new UpdateEmployeeFrame(e).setVisible(true);
		
		this.dispose();
	}
}
