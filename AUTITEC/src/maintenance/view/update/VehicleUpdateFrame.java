package maintenance.view.update;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import maintenance.controller.VehicleRegisterController;
import maintenance.controller.VehicleUpdateController;
import maintenance.view.register.VehicleRegisterFrame;
import model.Vehicle;
import net.sf.nachocalendar.components.DateField;
import userInterface.components.FrameController;
import userInterface.components.UpperTextField;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class VehicleUpdateFrame extends JFrame 
{

	private VehicleUpdateController controller;
	private JPanel panelData;
	private JPanel panelActions;
	private JComboBox<String> cboType;
	private JComboBox<String> cboBrand;
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
	private JButton btnUpdate;
	private JButton btnRemove;
	private JButton btnCancel;
	private JComboBox<Vehicle> cboVehicles;
	private final VehicleUpdateFrame thisFrame = this;
	private static final long serialVersionUID = 1L;
	private JLabel lblTipo;
	private JLabel lblMarca;
	private JLabel lblNewLabel;
	private JLabel lblDescrio;
	private JLabel lblChassi;
	private JLabel lblNewLabel_1;
	private JLabel lblPlaca;
	private JLabel lblKmInicial;
	private JLabel lblltimaTrocaDe;
	private JLabel lblTrocaDeleo;
	private JLabel lblVencimentoDoIpva;
	private JLabel lblVencimentoDoLicenciamento;
	private String frameName = "Atualização de veículos";
	

	public VehicleUpdateFrame()
	{
		controller = new VehicleUpdateController();
		initialize();
		setListeners();
		controller.fillCboVehicles(cboVehicles);
		GroupLayout gl_panelData = new GroupLayout(panelData);
		gl_panelData.setHorizontalGroup(
			gl_panelData.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addComponent(cboVehicles, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addComponent(lblTipo, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(135)
					.addComponent(lblMarca, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(111)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addComponent(cboType, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(cboBrand, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(txtModel, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addComponent(lblDescrio, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addComponent(txtDescription, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(14)
					.addComponent(lblChassi, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(135)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addGap(109)
					.addComponent(lblPlaca, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addComponent(txtChassi, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtRenavan, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(txtPlaca, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addComponent(lblKmInicial, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(104)
					.addComponent(lblltimaTrocaDe, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addComponent(lblTrocaDeleo, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addComponent(txtInitialKm, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(txtLastOilChange, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(txtOilChangeInterval, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addComponent(lblVencimentoDoIpva, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addGap(23)
					.addComponent(lblVencimentoDoLicenciamento))
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addComponent(txtIpvaDate, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(txtLicenseDate, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(12)
					.addComponent(panelActions, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelData.setVerticalGroup(
			gl_panelData.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelData.createSequentialGroup()
					.addGap(37)
					.addComponent(cboVehicles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTipo)
						.addComponent(lblMarca)
						.addComponent(lblNewLabel))
					.addGap(3)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(cboType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(cboBrand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelData.createSequentialGroup()
							.addGap(3)
							.addComponent(txtModel, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.addGap(12)
					.addComponent(lblDescrio)
					.addGap(1)
					.addComponent(txtDescription, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(lblChassi)
						.addComponent(lblNewLabel_1)
						.addComponent(lblPlaca))
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(txtChassi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRenavan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPlaca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(lblKmInicial)
						.addComponent(lblltimaTrocaDe)
						.addComponent(lblTrocaDeleo))
					.addGap(2)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(txtInitialKm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLastOilChange, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtOilChangeInterval, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVencimentoDoIpva)
						.addComponent(lblVencimentoDoLicenciamento))
					.addGap(2)
					.addGroup(gl_panelData.createParallelGroup(Alignment.LEADING)
						.addComponent(txtIpvaDate, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLicenseDate, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addComponent(panelActions, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panelData.setLayout(gl_panelData);
		
	}
	private void initialize() 
	{
		Icon.setIcon(this);
		setTitle(frameName);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setBounds(100, 100, 600,300);
		setMinimumSize(new Dimension(600, 400));
		setPreferredSize(new Dimension(600, 400));
		initializePrincipal();
		controller.fillCboType(cboType);
		controller.fillCboBrand(cboBrand);
	}
	private void setListeners()
	{
		ActionListener ButtonActions = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnCancel))
					FrameController.close(thisFrame,frameName);
				
				if(e.getSource().equals(btnRemove))
				{
					if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja mesmo apagar o veículo ?")==
							JOptionPane.YES_OPTION)
					{
						Vehicle selectedVehicle = (Vehicle) cboVehicles.getSelectedItem();
						controller.deleteVehicle(selectedVehicle.getId());
						controller.fillCboVehicles(cboVehicles);
						ClearFrame.clear(thisFrame);
						if(cboVehicles.getItemCount() > 0)
							cboVehicles.setSelectedIndex(0);
					}
				}
				else
				if(e.getSource().equals(btnUpdate))
				{
					if(verifyFields())
					{
						if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja mesmo alterar veículo ?")==
								JOptionPane.YES_OPTION)
						{
							Vehicle vehicle = getVehicleData();
							Vehicle selectedVehicle = (Vehicle) cboVehicles.getSelectedItem(); 
							vehicle.setId(selectedVehicle.getId());
							if(controller.updateVehicle(vehicle))
							{
								
									ShowMessage.successMessage(null,"RESULTADO","Veículo alterado com sucesso.");
									controller.fillCboVehicles(cboVehicles);
								
							}
							else				
								ShowMessage.errorMessage(null,"RESULTADO","Houve um erro ao alterar veículo.");	
						}
					}
					else
						ShowMessage.errorMessage(null,"VERIFIQUE","Preencha todos os campos.");
				}
				
			}
		};
		ActionListener stateChangeListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cboVehicles))
				{
					if(cboVehicles.getSelectedItem()!=null)
					{
						Vehicle selectedVehicle = (Vehicle) cboVehicles.getSelectedItem();
						Vehicle register = controller.getRegister(selectedVehicle.getId());
						setVehicleData(register);
					}
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
		
		btnUpdate.addActionListener(ButtonActions);
		btnRemove.addActionListener(ButtonActions);
		btnCancel.addActionListener(ButtonActions);
		txtInitialKm.addKeyListener(OnlyDigitsKeyListener);
		txtOilChangeInterval.addKeyListener(OnlyDigitsKeyListener);
		cboVehicles.addActionListener(stateChangeListener);
		FrameController.addConfirmationOnClose(thisFrame,frameName);
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
	private void setVehicleData(Vehicle vehicle)
	{
		txtDescription.setText(vehicle.getDescription());
		cboType.setSelectedItem(vehicle.getType());
		cboBrand.setSelectedItem(vehicle.getBrand());
		txtModel.setText(vehicle.getModel());
		txtChassi.setText(vehicle.getChassi());
		txtRenavan.setText(vehicle.getRenavan());
		txtPlaca.setText(vehicle.getPlaca());
		txtInitialKm.setText(String.valueOf(vehicle.getInitialKm().intValue()));
		txtLastOilChange.setValue(vehicle.getLastOilChange());
		txtOilChangeInterval.setText(String.valueOf(vehicle.getOilChangeInterval().intValue()));
		txtIpvaDate.setValue(vehicle.getIpvaDate());
		txtLicenseDate.setValue(vehicle.getLicenseDate());
		
	}

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
	private void initializePrincipal()
	{
		panelData = new JPanel();
		panelData.setBounds(12, 0, 584, 319);
		
		cboType = new JComboBox();
		getContentPane().add(panelData);
		
		lblTipo = new JLabel("Tipo");
		
		lblMarca = new JLabel("Marca");
		
		cboBrand = new JComboBox();
		
		txtModel = new UpperTextField();
		txtModel.setColumns(10);
		
		lblNewLabel = new JLabel("Modelo");
		
		lblChassi = new JLabel("Chassi");
		
		txtChassi = new UpperTextField();
		txtChassi.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Renavan");
		
		txtRenavan = new UpperTextField();
		txtRenavan.setColumns(10);
		
		txtPlaca = new UpperTextField();
		txtPlaca.setColumns(10);
		
		lblPlaca = new JLabel("Placa");
		
		txtInitialKm = new UpperTextField();
		txtInitialKm.setColumns(10);
		
		lblKmInicial = new JLabel("Km. Inicial");
		
		txtDescription = new UpperTextField();
		txtDescription.setColumns(10);
		
		lblDescrio = new JLabel("Descrição");
		
		txtLastOilChange = new DateField();

		
		lblltimaTrocaDe = new JLabel("Última troca de óleo");
		
		txtOilChangeInterval = new UpperTextField();
		txtOilChangeInterval.setColumns(10);
		
		lblTrocaDeleo = new JLabel("Troca de óleo a cada");
		
		txtIpvaDate = new DateField();
		
		lblVencimentoDoIpva = new JLabel("Vencimento do IPVA");
		
		txtLicenseDate = new DateField();
		
		lblVencimentoDoLicenciamento = new JLabel("Vencimento do licenciamento");
		
		panelActions = new JPanel();
		btnUpdate = new JButton("Atualizar");
		btnUpdate.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/ok.png")));
		
		
		cboVehicles = new JComboBox();
		
		btnRemove = new JButton("Apagar");
		btnRemove.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/clear.png")));
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/cancel.png")));
		
		GroupLayout gl_panelActions = new GroupLayout(panelActions);
		gl_panelActions.setHorizontalGroup(
			gl_panelActions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelActions.createSequentialGroup()
					.addGap(192)
					.addComponent(btnCancel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUpdate)
					.addGap(1))
		);
		gl_panelActions.setVerticalGroup(
			gl_panelActions.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelActions.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelActions.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnRemove)
						.addComponent(btnUpdate))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelActions.setLayout(gl_panelActions);
	}
	
	
	/**
	 * 
	 */
	
}
