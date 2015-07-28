package rh.view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.Employee;
import model.MonitoringImplementationRNC;
import model.Rnc;
import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import rh.controller.MonitoringImplementationController;
import userInterface.components.ComboBoxAutoCompletion;
import util.ClearFrame;
import util.Icon;
import util.MD5;
import util.ShowMessage;

public class MonitoringImplementationFrame extends JFrame{

	/**
	 * 
	 */
    private static final long serialVersionUID = 8442893759386910295L;
	private JPanel principalPanel;
	private JPanel subPanel;
	
	private DateField txtMonitoringDate;
	private DateField txtNewDeadLine;
	
	private JButton btnConfirm;
	private JButton btnCancel;
	private JButton btnSign;
	private JButton btnClear;

	private JLabel lblApprovedBy;
	private JLabel lblMonitoringDate;
	private JLabel lblNonCompliance;
	private JLabel lblNewDeadLine;
	
	private JRadioButton rdbtnImplemented;
	private JRadioButton rdbtnNotImplemented;

	private JComboBox<Employee> cboApprovedBy;
	private JComboBox<Rnc> cboNonCompliance;
	private MonitoringImplementationController controller;
	private JTextArea txtMonitoringDescription;
	private boolean isSigned = false;

    public MonitoringImplementationFrame() {
    	controller = new MonitoringImplementationController(this);
    	initialize();
    	setListeners();
    }

	private void initialize() {
	    setTitle("Acompanhamento de Implementação");
	    Icon.setIcon(this);
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    setBounds(100, 100, 573, 347);
	    setMinimumSize(new Dimension(573, 347));
	    setPreferredSize(new Dimension(573,  347));
	    getContentPane().setLayout(new BorderLayout(0,0));
	    initializePrincipal();
    }

	private void initializePrincipal() {
	    principalPanel = new JPanel();
	    getContentPane().add(principalPanel, BorderLayout.CENTER);
	    
	    lblNonCompliance = new JLabel("Não Conformidade");
	    
	    cboNonCompliance = new JComboBox<Rnc>();
	    controller.fillcboNonCompliance(cboNonCompliance);
	    new ComboBoxAutoCompletion(cboNonCompliance);
	    cboNonCompliance.setSelectedIndex(-1);
	    
	    lblMonitoringDate = new JLabel("Data da verificação");
	    
	    txtMonitoringDate = CalendarFactory.createDateField();
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setViewportBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Acompanhamento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    rdbtnImplemented = new JRadioButton("Implementado");
	    
	    rdbtnNotImplemented = new JRadioButton("Não Implementado");
	    
	    lblNewDeadLine = new JLabel("Novo prazo:");
	    
	    txtNewDeadLine = CalendarFactory.createDateField();
	    txtNewDeadLine.setValue(null);
	    
	    lblApprovedBy = new JLabel("Aprovado por");
	    
	    cboApprovedBy = new JComboBox<Employee>();
	    controller.fillcboEmployee(cboApprovedBy);
	    
	    btnSign = new JButton("Assinar");
	    
	    btnSign.setIcon(new ImageIcon(MonitoringImplementationFrame.class.getResource("/resources/assign.png")));
	    GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
	    gl_principalPanel.setHorizontalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addComponent(lblNonCompliance)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(cboNonCompliance, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
	    					.addGap(18)
	    					.addComponent(lblMonitoringDate)
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(txtMonitoringDate, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
	    				.addGroup(gl_principalPanel.createSequentialGroup()
	    					.addGroup(gl_principalPanel.createParallelGroup(Alignment.TRAILING, false)
	    						.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
	    							.addComponent(lblApprovedBy)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(cboApprovedBy, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    						.addGroup(Alignment.LEADING, gl_principalPanel.createSequentialGroup()
	    							.addComponent(rdbtnImplemented)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(rdbtnNotImplemented)
	    							.addPreferredGap(ComponentPlacement.UNRELATED)
	    							.addComponent(lblNewDeadLine)
	    							.addPreferredGap(ComponentPlacement.RELATED)
	    							.addComponent(txtNewDeadLine, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
	    					.addPreferredGap(ComponentPlacement.RELATED)
	    					.addComponent(btnSign)))
	    			.addContainerGap())
	    );
	    gl_principalPanel.setVerticalGroup(
	    	gl_principalPanel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_principalPanel.createSequentialGroup()
	    			.addContainerGap()
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblNonCompliance)
	    				.addComponent(cboNonCompliance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(lblMonitoringDate)
	    				.addComponent(txtMonitoringDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(rdbtnImplemented)
	    				.addComponent(rdbtnNotImplemented)
	    				.addComponent(lblNewDeadLine)
	    				.addComponent(txtNewDeadLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addGap(18)
	    			.addGroup(gl_principalPanel.createParallelGroup(Alignment.BASELINE)
	    				.addComponent(lblApprovedBy)
	    				.addComponent(cboApprovedBy, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    				.addComponent(btnSign))
	    			.addContainerGap(120, Short.MAX_VALUE))
	    );
	    
	    txtMonitoringDescription = new JTextArea();
	    txtMonitoringDescription.setLineWrap(true);
	    scrollPane.setViewportView(txtMonitoringDescription);
	    principalPanel.setLayout(gl_principalPanel);
	    initializeSub();
	    
    }

	private void initializeSub() {
	    subPanel = new JPanel();
	    subPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    getContentPane().add(subPanel, BorderLayout.SOUTH);
	    
	    btnConfirm = new JButton("Confirmar");
	    btnConfirm.setIcon(new ImageIcon(MonitoringImplementationFrame.class.getResource("/resources/ok.png")));
	    btnCancel = new JButton("Cancelar");
	    btnCancel.setIcon(new ImageIcon(MonitoringImplementationFrame.class.getResource("/resources/cancel.png")));
	    btnClear = new JButton("Limpar");
	    btnClear.setIcon(new ImageIcon(MonitoringImplementationFrame.class.getResource("/resources/Clearframe.png")));
	    
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
		
		ActionListener buttonLister = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnConfirm))confirm();
				else if(e.getSource().equals(btnClear))clearFrame();
				else if(e.getSource().equals(btnCancel))controller.close();
				else if(e.getSource().equals(btnSign))sign();
			}
		};
		
		btnConfirm.addActionListener(buttonLister);
		btnClear.addActionListener(buttonLister);
		btnCancel.addActionListener(buttonLister);
		btnSign.addActionListener(buttonLister);
	}
	
	private void confirm() {
		int i = ShowMessage.questionMessage(this, "Cadastro", "Deseja realemente realizar esse registro?");
		if(i == JOptionPane.NO_OPTION)return;
		if(cboNonCompliance.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione a não conformidade referente ao monitoramento!");
			return;
		}
		Date monitoringDate = (Date) txtMonitoringDate.getValue();
		if(monitoringDate.getTime() > new Date().getTime()) {
			ShowMessage.errorMessage(this, "Erro", "A data não pode ser maior que o dia atual");
			return;
		}
		Date dead = (Date) txtNewDeadLine.getValue();
		if(dead.getTime() > new Date().getTime()) {
			ShowMessage.errorMessage(this, "Erro", "O novo prazo não pode ser menor que o dia atual");
			return;
		}
		if(txtMonitoringDescription.getText().isEmpty()) {
			ShowMessage.errorMessage(this, "Erro", "Insira a descrição desse monitoramento!");
			return;
		}
		Date deadLine = (Date) txtNewDeadLine.getValue();
		if(rdbtnNotImplemented.isSelected() && deadLine == null) {
			ShowMessage.errorMessage(this, "Erro", "Insira o novo prazo!");
			return;
		}
		if(cboApprovedBy.getSelectedIndex() == -1 ) {
			ShowMessage.errorMessage(this, "Erro", "Selecione que fez essa aprovação!");
			return;
		}
		Rnc rnc = (Rnc) cboNonCompliance.getSelectedItem();
		String monitoringDescription = txtMonitoringDescription.getText();
		Employee e = (Employee) cboApprovedBy.getSelectedItem();
		MonitoringImplementationRNC mIRNC = new MonitoringImplementationRNC();
		mIRNC.setMonitoringDate(monitoringDate);
		mIRNC.setMonitoringDescription(monitoringDescription);
		rnc.setDeadLine(deadLine);	
		if(rdbtnImplemented.isSelected())rnc.setIsActive(true);
		else rnc.setIsActive(false);
		mIRNC.setApprovedBy(e);
		mIRNC.setNonCompliance(rnc);
		if(!isSigned) {
			ShowMessage.errorMessage(this, "Erro", "Precisa que assinatura esteja correto para continuar!");
			return;
		}
		controller.register(mIRNC);
		ShowMessage.successMessage(this, "Sucesso", "Registro realizado com sucesso!");
		ClearFrame.clear(this);
		}
	
	private void clearFrame() {
		int i = ShowMessage.questionMessage(this, "Limpar", "Deseja realmente Limpar os dados ?");
		if(i == JOptionPane.YES_OPTION)ClearFrame.clear(this);
	}
	
	private void sign() {
//		if(!rdbtnImplemented.isSelected()) {
//			ShowMessage.errorMessage(this, "Erro", "A ação deve ser aprovada para poder se assinar");
//			return;
//		}
		if(cboApprovedBy.getSelectedIndex() == -1) {
			ShowMessage.errorMessage(this, "Erro", "Selecione por quem foi aprovado a ação!");
			return;
		}
		
		Employee e = (Employee) cboApprovedBy.getSelectedItem();
		String pass = JOptionPane.showInputDialog(this, "Insira sua senha");
		pass = MD5.getMD5Code(pass);
		System.out.println(pass);
		if(controller.verifyUser(e, pass)) {
			isSigned = true;
			ShowMessage.successMessage(this, "Sucesso", "Assinatura digital concluida com sucesso!");
		}
		else {
			isSigned = false;
			ShowMessage.errorMessage(this, "Erro", "Senha ou usuário inválido!");
		}
		
	}
}
