package maintenance.view.update;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import maintenance.controller.ToolUpdateFrameController;
import maintenance.view.register.VehicleRegisterFrame;
import userInterface.components.FrameController;
import util.Icon;
import javax.swing.JComboBox;

public class ToolUpdateFrame extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private String frameName = "Atualização de ferramentas";
	private ToolUpdateFrame thisFrame = this;
	private ToolUpdateFrameController controller;
	private JTextField txtModel;
	private JTextArea txtDescription;
	private JButton btnConfirm;
	private JButton btnCancel;
	private JTextField txtBrand;
	private JTextField txtSerialNumber;
	private JButton btnRemove;
	
	
	public ToolUpdateFrame() {
		getContentPane().setLayout(null);
		controller = new ToolUpdateFrameController();
		initialize();
		setListeners();
		
	}
	public void initialize()
	{
		Icon.setIcon(this);
		setTitle(frameName);
		setBounds(100, 100, 573, 361);
		setMinimumSize(new Dimension(573, 300));
		setPreferredSize(new Dimension(573, 300));
		initializePrincipal();
		
	}
	public void initializePrincipal()
	{
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 63, 573, 261);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(12, 23, 70, 15);
		panel.add(lblMarca);
		
		JLabel lblNewLabel = new JLabel("Modelo");
		lblNewLabel.setBounds(240, 23, 70, 15);
		panel.add(lblNewLabel);
		
		txtModel = new JTextField();
		txtModel.setBounds(240, 50, 304, 19);
		panel.add(txtModel);
		txtModel.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 166, 532, 35);
		panel.add(scrollPane);
		
		txtDescription = new JTextArea();
		scrollPane.setViewportView(txtDescription);
		
		JLabel lblNewLabel_1 = new JLabel("Descrição");
		lblNewLabel_1.setBounds(12, 139, 70, 15);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 198, 532, 50);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		btnConfirm = new JButton("Confirmar");
		
		btnConfirm.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/ok.png")));
		btnConfirm.setBounds(387, 25, 133, 25);
		panel_1.add(btnConfirm);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/cancel.png")));
		btnCancel.setBounds(57, 25, 135, 25);
		panel_1.add(btnCancel);
		
		btnCancel.setBounds(87, 25,135, 25);
		panel_1.add(btnRemove);
		
		btnRemove = new JButton("Cancelar");
		btnRemove.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/clear.png")));
		btnRemove.setBounds(234, 25, 143, 25);
		panel_1.add(btnRemove);
		
		txtBrand = new JTextField();
		txtBrand.setBounds(12, 51, 216, 19);
		panel.add(txtBrand);
		txtBrand.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Número de série");
		lblNewLabel_2.setBounds(12, 76, 216, 15);
		panel.add(lblNewLabel_2);
		
		txtSerialNumber = new JTextField();
		txtSerialNumber.setBounds(12, 103, 216, 19);
		panel.add(txtSerialNumber);
		
		JComboBox cboTools = new JComboBox();
		cboTools.setBounds(12, 40, 549, 24);
		getContentPane().add(cboTools);
		
		JLabel lblNewLabel_3 = new JLabel("Ferramenta");
		lblNewLabel_3.setBounds(12, 0, 144, 39);
		getContentPane().add(lblNewLabel_3);
	}
	public void setListeners()
	{
		ActionListener buttonActions = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnCancel))
					FrameController.close(thisFrame, frameName);
				
			}
		};
		
		
		
		FrameController.addConfirmationOnClose(thisFrame, frameName);
		
	}
}
