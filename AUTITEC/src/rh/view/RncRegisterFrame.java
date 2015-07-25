package rh.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Employee;
import model.Rnc;
import net.sf.nachocalendar.components.DateField;
import rh.controller.RncRegisterController;
import userInterface.components.FrameController;
import util.Icon;
import util.ShowMessage;

public class RncRegisterFrame extends JFrame {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String frameName = "Registro de Não Conformidade";
	private final RncRegisterFrame thisFrame = this;
	private RncRegisterController controller;
	private JTextField txtSequenceNumber;
	private DateField txtDate;
	private DateField txtDeadLine;
	private JTextArea txtDescription;
	private JPanel panelRncRegister;
	private JRadioButton rdbtnCorretiveAction;
	private JRadioButton rdbtnPreventiveAction;
	private JRadioButton rdbtnProductNC;
	private JComboBox<Employee> cboEmitter;
	private JRadioButton rdbtIntern;
	private JRadioButton rdbtnExtern;
	private JRadioButton rdbtnAuditing;
	private JRadioButton rdbtnOthersType;
	private JRadioButton rdbtnAccept; 
	private JRadioButton rdbtnReturn;
	private JRadioButton rdbtnRework;
	private JRadioButton rdbtnOtherAction;
	private JTextArea txtImediateAction ;
	private JTextArea txtCause;
	private JCheckBox chkbActionPlanNeed;
	private JTextArea txtCorretiveAction;
	private JComboBox<Employee> cboResponsible;
	private JPanel subPanel;
	private JButton btnCancel;
	private JButton btnConfirm;
	private ButtonGroup rncType;
	private ButtonGroup rncOrigin;
	private ButtonGroup rncAction;
	private JScrollPane scrollPane_3;
	public RncRegisterFrame()
	{
		
		initialize();
		controller = new RncRegisterController();
		setListeners();
	}
	public void initialize()
	{
		Icon.setIcon(this);
		setTitle(frameName);
		setBounds(100, 100, 608, 664);
		setMinimumSize(new Dimension(608, 664));
		setPreferredSize(new Dimension(608, 664));
		initializePrincipal();
		controller.fillEmployeeCbo(cboEmitter);
		controller.fillEmployeeCbo(cboResponsible);
		txtSequenceNumber.setEnabled(false);
		txtSequenceNumber.setText(controller.getSequenceNumber());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 327, 270, 76);
		panelRncRegister.add(scrollPane);
		
		txtImediateAction = new JTextArea();
		scrollPane.setViewportView(txtImediateAction);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(307, 327, 249, 76);
		panelRncRegister.add(scrollPane_1);
		
		txtCause = new JTextArea();
		scrollPane_1.setViewportView(txtCause);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(18, 465, 538, 35);
		panelRncRegister.add(scrollPane_2);
		
		txtCorretiveAction = new JTextArea();
		scrollPane_2.setViewportView(txtCorretiveAction);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 190, 558, 47);
		panelRncRegister.add(scrollPane_3);
		
		txtDescription = new JTextArea();
		scrollPane_3.setViewportView(txtDescription);
		setActionPlanDataState(false);
	}
	public void setListeners()
	{	
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e){
				if(e.getSource().equals(btnCancel))
					FrameController.close(thisFrame, frameName);
				else
				if(e.getSource().equals(chkbActionPlanNeed))
					setActionPlanDataState(chkbActionPlanNeed.isSelected());
				if(e.getSource().equals(btnConfirm))
				{
					if(verifyFields())
					{
						if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja mesmo registrar a RNC ?")==
								JOptionPane.YES_OPTION)
						{
							controller.insertRnc(getRncData());
							ShowMessage.successMessage(null,"Mensagem", "RNC inserida com sucesso!");
							clearFields();
						}
					}
					else
						ShowMessage.errorMessage(null,"Mensagem","Preencha todos os campos");
				}
			}
		};
		btnCancel.addActionListener(buttonListener);
		chkbActionPlanNeed.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		FrameController.addConfirmationOnClose(thisFrame,frameName);
	}
	private boolean verifyFields()
	{
		try {
			 Boolean res = cboEmitter.getSelectedItem()!=null &&
					 rncType.getSelection()!= null && 
					 rncOrigin.getSelection()!=null && 
					 rncAction.getSelection()!=null && 
					 txtDate.getValue()!=null && 
					 !txtDescription.getText().isEmpty() &&
					 !txtImediateAction.getText().isEmpty() &&
					 !txtCause.getText().isEmpty();
			 
			 if(chkbActionPlanNeed.isSelected())
				 res = res && !txtCorretiveAction.getText().isEmpty() 
				 && cboResponsible.getSelectedItem()!=null
				 && txtDeadLine.getValue()!=null;			
			 
			 return res;
		} catch (Exception e) {			
			return false;			
		}
	}
	private Rnc getRncData()
	{
		 Rnc rnc = new Rnc();
		 rnc.setActiontype(rncType.getSelection().getActionCommand());
		 rnc.setSequenceNumber(txtSequenceNumber.getText());
		 rnc.setEmitter((Employee)cboEmitter.getSelectedItem());
		 rnc.setDate((Date) txtDate.getValue());
		 rnc.setOrigin(rncOrigin.getSelection().getActionCommand());
		 rnc.setDescription(txtDescription.getText());
		 rnc.setPosaction(rncAction.getSelection().getActionCommand());
		 rnc.setActionDescription(txtImediateAction.getText());
		 rnc.setCause(txtCause.getText());
		 rnc.setActionPlanNeed(chkbActionPlanNeed.isSelected());
		 rnc.setActionPlanDescription(txtCorretiveAction.getText());
		 rnc.setResponsible((Employee)cboResponsible.getSelectedItem());
		 rnc.setDeadLine((Date) txtDeadLine.getValue());
		 return rnc;
	}
	private void clearFields()
	{
		rncAction.clearSelection();
		rncOrigin.clearSelection();
		rncType.clearSelection();
		txtDescription.setText("");
		setActionPlanDataState(false);
		chkbActionPlanNeed.setSelected(false);
		txtCorretiveAction.setText("");
		txtCause.setText("");
		txtImediateAction.setText("");
		txtSequenceNumber.setText(controller.getSequenceNumber());
			
	}
	private void setActionPlanDataState(Boolean flag)
	{
		txtCorretiveAction.setEnabled(flag);
		cboResponsible.setEnabled(flag);
		txtDeadLine.setEnabled(flag);
		cboResponsible.setEnabled(flag);	
		txtCorretiveAction.setText("");	
	}
	public void initializePrincipal()
	{
		getContentPane().setLayout(null);

		panelRncRegister = new JPanel();
		panelRncRegister.setBounds(12, 0, 582, 628);
		getContentPane().add(panelRncRegister);
		panelRncRegister.setLayout(null);
		
		rdbtnCorretiveAction = new JRadioButton("Ação Corretiva");
		rdbtnCorretiveAction.setBounds(8, 22, 149, 23);
		panelRncRegister.add(rdbtnCorretiveAction);
		
		rdbtnPreventiveAction = new JRadioButton("Ação preventiva");
		rdbtnPreventiveAction.setBounds(164, 22, 149, 23);
		panelRncRegister.add(rdbtnPreventiveAction);
		
		rdbtnProductNC = new JRadioButton("Produto NC");
		rdbtnProductNC.setBounds(324, 22, 149, 23);
		panelRncRegister.add(rdbtnProductNC);
		
		txtSequenceNumber = new JTextField();
		txtSequenceNumber.setBounds(12, 80, 101, 25);
		panelRncRegister.add(txtSequenceNumber);
		txtSequenceNumber.setColumns(10);
		
		JLabel lblSequenceNumber = new JLabel("N. de sequência");
		lblSequenceNumber.setBounds(8, 53, 132, 23);
		panelRncRegister.add(lblSequenceNumber);
		
		cboEmitter = new JComboBox<Employee>();
		cboEmitter.setBounds(142, 80, 302, 25);
		panelRncRegister.add(cboEmitter);
		
		JLabel lblNewLabel = new JLabel("Emitente");
		lblNewLabel.setBounds(142, 57, 93, 15);
		panelRncRegister.add(lblNewLabel);
		
		txtDate = new DateField();
		txtDate.setBounds(456, 80, 114, 25);
		panelRncRegister.add(txtDate);
		
		JLabel lblDate = new JLabel("Data");
		lblDate.setBounds(456, 57, 70, 15);
		panelRncRegister.add(lblDate);
		
		rdbtIntern = new JRadioButton("Interno");
		rdbtIntern.setBounds(8, 121, 114, 23);
		panelRncRegister.add(rdbtIntern);
		
		rdbtnExtern = new JRadioButton("Externo");
		rdbtnExtern.setBounds(142, 121, 114, 23);
		panelRncRegister.add(rdbtnExtern);
		
		rdbtnAuditing = new JRadioButton("Auditoria");
		rdbtnAuditing.setBounds(297, 121, 114, 23);
		panelRncRegister.add(rdbtnAuditing);
		
		rdbtnOthersType = new JRadioButton("Outros");
		rdbtnOthersType.setBounds(456, 121, 114, 23);
		panelRncRegister.add(rdbtnOthersType);
		
		JLabel lblDescription = new JLabel("Descrição");
		lblDescription.setBounds(12, 163, 70, 15);
		panelRncRegister.add(lblDescription);
		
		rdbtnAccept = new JRadioButton("Aceitar");
		
		rdbtnAccept.setBounds(8, 255, 114, 23);
		panelRncRegister.add(rdbtnAccept);
		
		rdbtnReturn = new JRadioButton("Devolver");
		rdbtnReturn.setBounds(155, 255, 101, 23);
		panelRncRegister.add(rdbtnReturn);
		
		rdbtnRework = new JRadioButton("Retrabalhar");
		rdbtnRework.setBounds(297, 255, 149, 23);
		panelRncRegister.add(rdbtnRework);
		
		rdbtnOtherAction = new JRadioButton("Outros");
		rdbtnOtherAction.setBounds(469, 255, 101, 23);
		panelRncRegister.add(rdbtnOtherAction);
		
		JLabel lbImediateAction = new JLabel("Ação imediata ou disposição produto NC");
		lbImediateAction.setFont(new Font("Dialog", Font.BOLD, 10));
		lbImediateAction.setBounds(18, 300, 239, 15);
		panelRncRegister.add(lbImediateAction);
		
		JLabel lblCause = new JLabel("Causa");
		lblCause.setBounds(307, 299, 60, 15);
		panelRncRegister.add(lblCause);
		
		chkbActionPlanNeed = new JCheckBox("Necessita de plano de ação");
		chkbActionPlanNeed.setHorizontalAlignment(SwingConstants.CENTER);
		chkbActionPlanNeed.setBounds(18, 411, 538, 23);
		panelRncRegister.add(chkbActionPlanNeed);
		
		JLabel lblNewLabel_1 = new JLabel("Ação Corretiva");
		lblNewLabel_1.setBounds(18, 438, 199, 15);
		panelRncRegister.add(lblNewLabel_1);
		
		cboResponsible = new JComboBox<Employee>();
		cboResponsible.setBounds(18, 541, 357, 24);
		panelRncRegister.add(cboResponsible);
		
		JLabel lblNewLabel_2 = new JLabel("Responsável");
		lblNewLabel_2.setBounds(21, 514, 101, 15);
		panelRncRegister.add(lblNewLabel_2);
		
		JLabel lblPrazo = new JLabel("Prazo");
		lblPrazo.setBounds(397, 512, 76, 15);
		panelRncRegister.add(lblPrazo);
		
		txtDeadLine = new DateField();
		txtDeadLine.setBounds(397, 539, 159, 25);
		panelRncRegister.add(txtDeadLine);
		
		subPanel = new JPanel();
		subPanel.setBounds(18, 569, 552, 47);
		panelRncRegister.add(subPanel);
		
		btnCancel = new JButton("Cancelar");
		
		btnConfirm = new JButton("Confirmar");
		
		GroupLayout gl_subPanel = new GroupLayout(subPanel);
		gl_subPanel.setHorizontalGroup(
			gl_subPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_subPanel.createSequentialGroup()
					.addGap(204)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
		);
		gl_subPanel.setVerticalGroup(
			gl_subPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_subPanel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_subPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancel)
						.addComponent(btnConfirm)))
		);
		subPanel.setLayout(gl_subPanel);
		controller = new RncRegisterController();
		
		for(Component comp:panelRncRegister.getComponents())
		{
			if(comp instanceof JRadioButton)
			{
				JRadioButton rdbtn = (JRadioButton) comp;
				rdbtn.setActionCommand(rdbtn.getText());
			}
		}
		
		rncType = new ButtonGroup();
		rncType.add(rdbtnCorretiveAction);
		rncType.add(rdbtnPreventiveAction);
		rncType.add(rdbtnProductNC);
		
		rncOrigin = new ButtonGroup();
		rncOrigin.add(rdbtIntern);
		rncOrigin.add(rdbtnExtern);
		rncOrigin.add(rdbtnAuditing);
		rncOrigin.add(rdbtnOthersType);
		
		rncAction = new ButtonGroup();
		rncAction.add(rdbtnAccept);
		rncAction.add(rdbtnReturn);
		rncAction.add(rdbtnRework);
		rncAction.add(rdbtnOtherAction);
		
	}
}
