package maintenance.view.register;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Date;

import model.Session;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import maintenance.controller.VehicleDebtRegisterController;
import model.Employee;
import model.Vehicle;
import model.VehicleDebt;
import net.sf.nachocalendar.components.DateField;
import userInterface.components.FrameController;
import userInterface.components.RealNumberField;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class VehicleDebtRegisterFrame extends JFrame {

	private VehicleDebtRegisterController controller;
	private UpperTextField txtFixDescription;
	private JTextField txtFuelQuantity;
	private DateField txtDueDate;
	private	DateField txtPayDate;
	private RealNumberField txtValue;
	private JComboBox<Vehicle> cboVehicle;
	private JComboBox<Employee> cboConductor;
	private JRadioButton rdbtnFix;
	private JRadioButton rdbtnFuelFillin;
	private JRadioButton rdbtnOilChange;
	private JRadioButton rdbtnOthers;
	private JComboBox<String> cboFuel;
	private JTextArea txtObs;
	private JButton btnConfirm;
	private JButton btnCancel ;
	private String frameName = "Registro de débito/encargos de veículos";
	private ButtonGroup vehicleDebtTypeGroup;
	private final VehicleDebtRegisterFrame thisFrame = this;
	private JCheckBox chkbPaid;
	
	public VehicleDebtRegisterFrame() {
		super();
		
		controller = new VehicleDebtRegisterController();
		initialize();
		setListeners();
		
	}
	private void initialize()
	{
		Icon.setIcon(this);
		setTitle(frameName);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 634, 580);
		setMinimumSize(new Dimension(634, 580));
		setPreferredSize(new Dimension(634, 580));
		initializePrincipal();
		controller.fillEmployeeCbo(cboConductor);
		controller.fillVehicleCbo(cboVehicle);
		controller.fillFuelTypeCbo(cboFuel);
	}
	private void setListeners()
	{
		ActionListener buttonActions = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnCancel))
					FrameController.close(thisFrame,frameName);
				else
				if(e.getSource().equals(chkbPaid))
					if(!chkbPaid.isSelected())
					{
						txtPayDate.setValue(null);
						txtPayDate.setEnabled(false);
					}
					else
					{
						txtPayDate.setValue(new Date());
						txtPayDate.setEnabled(true);
						txtPayDate.grabFocus();
					}
				else
				if(e.getSource().equals(btnConfirm))
				{
					if(verifyFields())
					{
						VehicleDebt vehicleDebt = new VehicleDebt();
						vehicleDebt.setVehicle((Vehicle)cboVehicle.getSelectedItem());
						vehicleDebt.setConductor((Employee)cboConductor.getSelectedItem());
						vehicleDebt.setDebtType(vehicleDebtTypeGroup.getSelection().getActionCommand().substring(0,2));
						vehicleDebt.setDescription(txtFixDescription.getText());
						if(cboFuel.getSelectedItem() != null)		
							vehicleDebt.setFuelType(cboFuel.getSelectedItem().toString());
						else
							vehicleDebt.setFuelType(null);
						if(!txtFuelQuantity.getText().isEmpty())	
							vehicleDebt.setFuelQuantity(Double.parseDouble(txtFuelQuantity.getText()));
						else
							vehicleDebt.setFuelQuantity(null);
						
						vehicleDebt.setObservations(txtObs.getText());
						Date dueDate  = (java.util.Date) txtDueDate.getValue();
						vehicleDebt.setDuedate(new java.sql.Date(dueDate.getTime()));
						
						vehicleDebt.setValue(Double.parseDouble(txtValue.getText().replace(",", ".")));
						vehicleDebt.setUser(Session.getInstance().getUser());
						
						if(txtPayDate.getValue()!=null)
						{
							Date payDate = (java.util.Date)  txtPayDate.getValue();
							vehicleDebt.setPaydate(new java.sql.Date(payDate.getTime()));
						}
						if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja mesmo remover o destino selecionado?")==
								JOptionPane.YES_OPTION)
						{
							controller.insertVehicleDebt(vehicleDebt);
							ShowMessage.successMessage(null,"Mensagem","Registro inserido com sucesso.");
					
						}
					}
					else
						ShowMessage.errorMessage(null,"Mensagem","Preencha todos os dados");
				}
			}
		};
		KeyListener keylistener = new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getSource().equals(txtFuelQuantity))
				{
					if(!Character.isDigit(e.getKeyChar()))
						e.setKeyChar('\0');
						
					
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		btnCancel.addActionListener(buttonActions);
		btnConfirm.addActionListener(buttonActions);
		chkbPaid.addActionListener(buttonActions);
		
		FrameController.addConfirmationOnClose(this, frameName);
	}
	private Boolean verifyFields()
	{
		try {
			Boolean res  = true;
			 if(rdbtnFix.isSelected())
			   res =  !txtFixDescription.getText().isEmpty();
			 return  res && 
					 cboVehicle.getSelectedItem()!=null && !txtValue.getText().isEmpty()
					 && vehicleDebtTypeGroup.getSelection()!=null ;
					 
			
			 
		} catch (Exception e) {
			return false;
		}
	}
	private void clearFields()
	{
		txtValue.setText("");
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH,30);
		txtDueDate.setValue(cal.getTime());
		
		txtPayDate.setValue(null);
		chkbPaid.setSelected(false);
	}
	private void initializePrincipal()
	{
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 607, 516);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbVeiculo = new JLabel("Veículo");
		lbVeiculo.setBounds(12, 12, 70, 15);
		panel.add(lbVeiculo);
		
		cboVehicle = new JComboBox<Vehicle>();
		cboVehicle.setBounds(12, 39, 493, 24);
		panel.add(cboVehicle);
		
		JLabel lbConductor = new JLabel("Condutor");
		lbConductor.setBounds(12, 74, 70, 15);
		panel.add(lbConductor);
		
		cboConductor = new JComboBox<Employee>();
		cboConductor.setBounds(12, 101, 493, 24);
		panel.add(cboConductor);
		
		rdbtnFix = new JRadioButton("Conserto/Reparo");
		rdbtnFix.setBounds(12, 141, 149, 23);
		panel.add(rdbtnFix);
		
		rdbtnFuelFillin = new JRadioButton("Abastecimento");
		rdbtnFuelFillin.setBounds(166, 141, 149, 23);
		panel.add(rdbtnFuelFillin);
		
		rdbtnOilChange = new JRadioButton("Troca de óleo");
		rdbtnOilChange.setBounds(319, 141, 149, 23);
		panel.add(rdbtnOilChange);
		
		rdbtnOthers = new JRadioButton("Outros");
		rdbtnOthers.setBounds(472, 141, 149, 23);
		panel.add(rdbtnOthers);
		
		JLabel lblFixDescription = new JLabel("Descrição consert/reparo");
		lblFixDescription.setBounds(12, 175, 247, 15);
		panel.add(lblFixDescription);
		
		txtFixDescription = new UpperTextField();
		txtFixDescription.setBounds(12, 202, 493, 24);
		panel.add(txtFixDescription);
		txtFixDescription.setColumns(10);
		
		cboFuel = new JComboBox();
		cboFuel.setBounds(12, 259, 332, 24);
		panel.add(cboFuel);
		
		JLabel lblFuelType = new JLabel("Combustível");
		lblFuelType.setBounds(12, 233, 149, 15);
		panel.add(lblFuelType);
		
		JLabel lblFuelQuant = new JLabel("Quantidade");
		lblFuelQuant.setBounds(361, 233, 107, 15);
		panel.add(lblFuelQuant);
		
		txtFuelQuantity = new JTextField();
		txtFuelQuantity.setBounds(354, 260, 114, 24);
		panel.add(txtFuelQuantity);
		txtFuelQuantity.setColumns(10);
		
		txtObs = new JTextArea();
		txtObs.setBounds(12, 329, 459, 55);
		panel.add(txtObs);
		
		JLabel lblObs = new JLabel("Observações");
		lblObs.setBounds(12, 308, 164, 15);
		panel.add(lblObs);
		
		txtDueDate = new DateField();
		txtDueDate.setBounds(12, 424, 149, 30);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH,30);
		panel.add(txtDueDate);
		txtDueDate.setValue(cal.getTime());

		
		JLabel lblDueDAte = new JLabel("Data de vencimento");
		lblDueDAte.setBounds(12, 397, 164, 30);
		panel.add(lblDueDAte);
		
		txtPayDate = new DateField();
		txtPayDate.setBounds(12, 485, 145, 30);
		txtPayDate.setValue(null);
		panel.add(txtPayDate);
		
		JLabel lblPayDate = new JLabel("Data de pagamento");
		lblPayDate.setBounds(12, 458, 164, 15);
		panel.add(lblPayDate);
		
		
		txtValue = new RealNumberField();
		txtValue.setBounds(201, 424, 114, 24);
		panel.add(txtValue);
		txtValue.setColumns(10);
		
		JLabel lblValue = new JLabel("Valor");
		lblValue.setBounds(201, 397, 70, 15);
		panel.add(lblValue);
		
		btnConfirm = new JButton("Confirmar");
		btnConfirm.setBounds(398, 491, 191, 25);
		btnConfirm.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/ok.png")));
		panel.add(btnConfirm);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(194, 491, 191, 25);
		btnCancel.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/cancel.png")));
		panel.add(btnCancel);
		
		chkbPaid = new JCheckBox("Pago");
		chkbPaid.setBounds(339, 422, 129, 23);
		panel.add(chkbPaid);
	
		vehicleDebtTypeGroup = new ButtonGroup();
		for(Component component:panel.getComponents())
		{
			if(component instanceof JRadioButton)
			{
				JRadioButton rdBtn = (JRadioButton) component;
				rdBtn.setActionCommand(rdBtn.getText());
				vehicleDebtTypeGroup.add(rdBtn);
			}
		}
	}
}
