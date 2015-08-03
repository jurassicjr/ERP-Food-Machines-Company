package maintenance.view.register;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import maintenance.controller.ToolRegisterFrameController;
import model.Tool;
import userInterface.components.FrameController;
import util.ClearFrame;
import util.Icon;
import util.ShowMessage;

public class ToolRegisterFrame extends JFrame
{
	
	private ToolRegisterFrameController controller;
	private ToolRegisterFrame thisFrame = this;
	private String frameName = "Registro de ferramentas"; 
	private static final long serialVersionUID = 1L;
	private JTextArea txtDescription;
	private JButton btnCancel;
	private JButton btnConfirm;
	private JTextField txtBrand;
	private JTextField txtModel;
	private JTextField txtSerialNumber;
	
	public ToolRegisterFrame()
	{
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 573, 260);
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
		btnCancel.setBounds(240, 25, 135, 25);
		panel_1.add(btnCancel);
		
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
		controller = new ToolRegisterFrameController();
		initialize();
		setListeners();
		
	}
	public void initialize()
	{
		
		Icon.setIcon(this);
		setTitle(frameName);
		setBounds(100, 100, 573, 300);
		setMinimumSize(new Dimension(573, 300));
		setPreferredSize(new Dimension(573, 300));
	}
	public void initializePrincipal()
	{
		
	}
	public void setListeners()
	{
		ActionListener buttonActions = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource().equals(btnCancel))
					FrameController.close(thisFrame, frameName);
				else
				if(e.getSource().equals(btnConfirm))
					register();
				
				
			}
		};
		
		btnConfirm.addActionListener(buttonActions);
		btnCancel.addActionListener(buttonActions);
		FrameController.addConfirmationOnClose(thisFrame, frameName);	
	}
	public void register()
	{
		if(verifyFields())
		{
			if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja mesmo inserir a ferramenta?")==
					JOptionPane.YES_OPTION)
			{
				Tool tool = new Tool();
				tool.setBrand(txtBrand.getText());
				tool.setModel(txtModel.getText());
				tool.setDescription(txtDescription.getText());
				tool.setSerialNumber(txtSerialNumber.getText());
				
				controller.persist(tool);
				ShowMessage.successMessage(null,"Mensagem","Ferramenta inserida com sucesso!");
				ClearFrame.clear(thisFrame);
			}
			
			
		}
		else
			ShowMessage.errorMessage(null,"Mensagem","Preencha todos os campos.");
		
	}
	public boolean verifyFields()
	{
		return !txtBrand.getText().isEmpty() && !txtModel.getText().isEmpty() && !txtSerialNumber.getText().isEmpty();		
	}
	
	
	
	
	
}
