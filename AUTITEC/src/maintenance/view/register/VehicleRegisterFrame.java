package maintenance.view.register;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import maintenance.controller.VehicleRegisterController;
import model.Vehicle;
import net.sf.nachocalendar.components.DateField;
import userInterface.components.FrameController;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class VehicleRegisterFrame extends JFrame {

	private VehicleRegisterController controller;
	private JPanel panelData;
	private JPanel panelActions;
	private JComboBox cboType;
	private JComboBox cboBrand;
	private JTextField txtModel;
	private JTextField txtChassi;
	private JTextField txtRenavan;
	private JTextField txtPlaca;
	private JTextField txtInitialKm;
	private JTextField txtDescription;
	private DateField txtLastOilChange;
	private JTextField txtOilChangeInterval;
	private DateField txtIpvaDate;
	private DateField txtLicenseDate;
	private JButton btnCancel;
	private JButton btnConfirm;
	private final VehicleRegisterFrame thisFrame = this;
	

	public VehicleRegisterFrame()
	{
		controller = new VehicleRegisterController();
		initialize();
		setListeners();
		
	}
	private void initialize() 
	{
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		Icon.setIcon(this);
		setTitle("Registro De Veículos");
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 650, 350);
		setMinimumSize(new Dimension(650, 350));
		setPreferredSize(new Dimension(650, 350));
		initializePrincipal();
		controller.fillCboType(cboType);
		controller.fillCboBrand(cboBrand);
	}
	private void setListeners()
	{
		btnCancel.addActionListener(ButtonActions);
		btnConfirm.addActionListener(ButtonActions);
		txtInitialKm.addKeyListener(OnlyDigitsKeyListener);
		txtOilChangeInterval.addKeyListener(OnlyDigitsKeyListener);
		FrameController.addConfirmationOnClose(this);
		
	}
	private Vehicle getVehicleData()
	{
		Vehicle vehicle = new Vehicle();
		vehicle.setDescription(txtDescription.getText());
		vehicle.setType(cboType.getSelectedItem().toString());
		vehicle.setBrand(cboBrand.getSelectedItem().toString());
		vehicle.setModel(txtModel.getText());
		vehicle.setChassi(txtChassi.getText());
		vehicle.setRenavan(txtRenavan.getText());
		vehicle.setPlaca(txtPlaca.getText());
		vehicle.setInitialKm(Double.valueOf(txtInitialKm.getText()));
		java.util.Date lastOilChange = (java.util.Date) txtLastOilChange.getValue();
		vehicle.setLastOilChange(new java.sql.Date(lastOilChange.getTime()));
		vehicle.setOilChangeInterval(Double.valueOf(txtOilChangeInterval.getText()));
		java.util.Date ipvaDate = (java.util.Date) txtIpvaDate.getValue();
		vehicle.setIpvaDate(new java.sql.Date(ipvaDate.getTime()));
		java.util.Date licenseDate = (java.util.Date) txtLicenseDate.getValue();
		vehicle.setLicenseDate( new java.sql.Date(licenseDate.getTime()));
		
		return vehicle;
	}
	ActionListener ButtonActions = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(btnCancel))
				FrameController.close(thisFrame);
			else
			if(e.getSource().equals(btnConfirm)){
				if(verifyFields())
				{
					if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja mesmo registrar veículo ?")==
							JOptionPane.YES_OPTION)
					{
						Vehicle vehicle = getVehicleData();
						if(controller.insertVehicle(vehicle))
						{
							
								ShowMessage.successMessage(null,"RESULTADO","Veículo inserido com sucesso.");
								clearFields();
							
						}
						else				
							ShowMessage.errorMessage(null,"RESULTADO","Houve um erro ao inserir veículo.");	
					}
				}
				else
					ShowMessage.errorMessage(null,"VERIFIQUE","Preencha todos os campos.");
			}
		}
	};
	KeyListener OnlyDigitsKeyListener = new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			if(e.getSource().equals(txtInitialKm))
			{
				if(!Character.isDigit(e.getKeyChar()))
					e.setKeyChar('\0');
			}
			else
			if(e.getSource().equals(txtOilChangeInterval))
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
			
		}
			
	};
	private boolean verifyFields()
	{
		try {
			 Boolean result = true;
			 Integer i = 0 ;
			 while(i<panelData.getComponentCount() && result)
			 {
				 if(panelData.getComponent(i) instanceof JTextField)
				 {
					 JTextField txt = (JTextField) panelData.getComponent(i);
					 result = result && !txt.getText().isEmpty();
				 }
				i++;	 
			 }
			return result;
		} catch (Exception e) {
			return false;
		}
		
	}
	private void clearFields()
	{
		ClearFrame.clear(this);
		controller.fillCboBrand(cboBrand);
		controller.fillCboBrand(cboType);
		txtIpvaDate.setValue(new Date());
		txtLicenseDate.setValue(new Date());
		txtLastOilChange.setValue(new Date());
		txtModel.grabFocus();
	}
	private void initializePrincipal()
	{
		panelData = new JPanel();
		panelData.setBounds(12, 0, 584, 319);
		
		cboType = new JComboBox();
		getContentPane().add(panelData);
		
		JLabel lblTipo = new JLabel("Tipo");
		
		JLabel lblMarca = new JLabel("Marca");
		
		cboBrand = new JComboBox();
		
		txtModel = new UpperTextField();
		txtModel.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Modelo");
		
		JLabel lblChassi = new JLabel("Chassi");
		
		txtChassi = new UpperTextField();
		txtChassi.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Renavan");
		
		txtRenavan = new UpperTextField();
		txtRenavan.setColumns(10);
		
		txtPlaca = new UpperTextField();
		txtPlaca.setColumns(10);
		
		JLabel lblPlaca = new JLabel("Placa");
		
		txtInitialKm = new UpperTextField();
		txtInitialKm.setColumns(10);
		
		JLabel lblKmInicial = new JLabel("Km. Inicial");
		
		txtDescription = new UpperTextField();
		txtDescription.setColumns(10);
		
		JLabel lblDescrio = new JLabel("Descrição");
		
		txtLastOilChange = new DateField();

		
		JLabel lblltimaTrocaDe = new JLabel("Última troca de óleo");
		
		txtOilChangeInterval = new UpperTextField();
		txtOilChangeInterval.setColumns(10);
		
		JLabel lblTrocaDeleo = new JLabel("Troca de óleo a cada");
		
		txtIpvaDate = new DateField();
		
		JLabel lblVencimentoDoIpva = new JLabel("Vencimento do IPVA");
		
		txtLicenseDate = new DateField();
		
		JLabel lblVencimentoDoLicenciamento = new JLabel("Vencimento do licenciamento");
		
		panelActions = new JPanel();
		
		 btnCancel = new JButton("Cancelar");
		 btnCancel.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/cancel.png")));
		 btnConfirm = new JButton("Confirmar");
		 btnConfirm.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/ok.png")));
		
		separator = new JSeparator();
		GroupLayout gl_panelData = new GroupLayout(panelData);
		gl_panelData.setHorizontalGroup(
			gl_panelData.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelData.createSequentialGroup()
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelData.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panelData.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
								.addComponent(panelActions, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
								.addGroup(gl_panelData.createSequentialGroup()
									.addComponent(lblTipo, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addGap(135)
									.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addGap(111)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelData.createSequentialGroup()
									.addComponent(cboType, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(cboBrand, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
									.addGap(13)
									.addComponent(txtModel, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblDescrio, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtDescription, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelData.createSequentialGroup()
									.addComponent(lblChassi, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addGap(135)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addGap(109)
									.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelData.createSequentialGroup()
									.addComponent(txtChassi, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(txtRenavan, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
									.addGap(16)
									.addComponent(txtPlaca, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelData.createSequentialGroup()
									.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
										.addComponent(lblKmInicial, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtInitialKm, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
									.addGap(12)
									.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
										.addComponent(lblltimaTrocaDe, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtLastOilChange, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
									.addGap(3)
									.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTrocaDeleo, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_panelData.createSequentialGroup()
											.addGap(3)
											.addComponent(txtOilChangeInterval, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))))
								.addGroup(gl_panelData.createSequentialGroup()
									.addComponent(lblVencimentoDoIpva, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
									.addGap(23)
									.addComponent(lblVencimentoDoLicenciamento))
								.addGroup(gl_panelData.createSequentialGroup()
									.addComponent(txtIpvaDate, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(txtLicenseDate, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())))
		);
		gl_panelData.setVerticalGroup(
			gl_panelData.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTipo)
						.addComponent(lblMarca)
						.addComponent(lblNewLabel))
					.addGap(2)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(cboType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cboBrand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelData.createSequentialGroup()
							.addGap(3)
							.addComponent(txtModel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(9)
					.addComponent(lblDescrio)
					.addGap(1)
					.addComponent(txtDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(lblChassi)
						.addComponent(lblNewLabel_1)
						.addComponent(lblPlaca))
					.addGap(1)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(txtChassi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRenavan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelData.createSequentialGroup()
							.addGap(2)
							.addComponent(lblKmInicial)
							.addComponent(txtInitialKm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelData.createSequentialGroup()
							.addComponent(lblltimaTrocaDe)
							.addComponent(txtLastOilChange, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelData.createSequentialGroup()
							.addComponent(lblTrocaDeleo)
							.addGap(1)
							.addComponent(txtOilChangeInterval, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(11)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVencimentoDoIpva)
						.addComponent(lblVencimentoDoLicenciamento))
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(txtIpvaDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLicenseDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelActions, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		GroupLayout gl_panelActions = new GroupLayout(panelActions);
		gl_panelActions.setHorizontalGroup(
			gl_panelActions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelActions.createSequentialGroup()
					.addGap(351)
					.addComponent(btnCancel)
					.addGap(5)
					.addComponent(btnConfirm))
		);
		gl_panelActions.setVerticalGroup(
			gl_panelActions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelActions.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panelActions.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancel)
						.addComponent(btnConfirm)))
		);
		panelActions.setLayout(gl_panelActions);
		
		panelData.setLayout(gl_panelData);
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSeparator separator;
	
}
