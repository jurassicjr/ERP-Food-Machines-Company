package maintenance.view.update;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import maintenance.controller.ToolUpdateFrameController;
import maintenance.view.register.VehicleRegisterFrame;
import model.Tool;
import userInterface.components.FrameController;
import util.Icon;
import util.ShowMessage;

public class ToolUpdateFrame extends JFrame {
	
	
	private static final long serialVersionUID = 1L;
	private String frameName = "Atualização de ferramentas";
	private ToolUpdateFrame thisFrame = this;
	private ToolUpdateFrameController controller;
	private JTextField txtModel;
	private JTextArea txtDescription;
	private JButton btnUpdate;
	private JButton btnCancel;
	private JTextField txtBrand;
	private JTextField txtSerialNumber;
	private JButton btnRemove;
	private JComboBox<Tool> cboTools ;
	private JPanel panelData;
	
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
		setMinimumSize(new Dimension(573, 361));
		setPreferredSize(new Dimension(573, 361));
		initializePrincipal();
		controller.fillCboTools(cboTools);
		fillFields();
		
		
	}
	public void initializePrincipal()
	{
		getContentPane().setLayout(null);
		
		 panelData = new JPanel();
		panelData.setBounds(0, 63, 573, 261);
		getContentPane().add(panelData);
		panelData.setLayout(null);
		
		JLabel lblMarca = new JLabel("Marca");
		lblMarca.setBounds(12, 23, 70, 15);
		panelData.add(lblMarca);
		
		JLabel lblNewLabel = new JLabel("Modelo");
		lblNewLabel.setBounds(240, 23, 70, 15);
		panelData.add(lblNewLabel);
		
		txtModel = new JTextField();
		txtModel.setBounds(240, 50, 304, 19);
		panelData.add(txtModel);
		txtModel.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 166, 532, 35);
		panelData.add(scrollPane);
		
		txtDescription = new JTextArea();
		scrollPane.setViewportView(txtDescription);
		
		JLabel lblNewLabel_1 = new JLabel("Descrição");
		lblNewLabel_1.setBounds(12, 139, 70, 15);
		panelData.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 198, 532, 50);
		panelData.add(panel_1);
		panel_1.setLayout(null);
		
		btnUpdate = new JButton("Atualizar");
		
		btnUpdate.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/ok.png")));
		btnUpdate.setBounds(387, 25, 133, 25);
		panel_1.add(btnUpdate);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/cancel.png")));
		btnCancel.setBounds(57, 25, 135, 25);
		panel_1.add(btnCancel);
		
		btnCancel.setBounds(87, 25,135, 25);
		
		btnRemove = new JButton("Remover");
		btnRemove.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/clear.png")));
		btnRemove.setBounds(234, 25, 143, 25);
		panel_1.add(btnRemove);
		
		txtBrand = new JTextField();
		txtBrand.setBounds(12, 51, 216, 19);
		panelData.add(txtBrand);
		txtBrand.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Número de série");
		lblNewLabel_2.setBounds(12, 76, 216, 15);
		panelData.add(lblNewLabel_2);
		
		txtSerialNumber = new JTextField();
		txtSerialNumber.setBounds(12, 103, 216, 19);
		panelData.add(txtSerialNumber);
		
		cboTools = new JComboBox<Tool>();
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
				else
				if(e.getSource().equals(cboTools))
					fillFields();
				else
				if(e.getSource().equals(btnUpdate))
				{
					Tool tool = (Tool)cboTools.getSelectedItem();
					if(tool!=null)
					{
						if(ShowMessage.questionMessage(thisFrame, "Atualizar", "Deseja mesmo atualizar a ferramenta selecionado?")==
								JOptionPane.YES_OPTION)
						{
							getFields(tool);
							controller.updateTool(tool);
							ShowMessage.successMessage(null,"Mensagem","Ferramenta atualizada com sucesso" );
							clearFields();
							controller.fillCboTools(cboTools);
						}
					}
					else
						ShowMessage.errorMessage(null,"Mensagem","Selecione uma ferramenta");
				}	
				else
				if(e.getSource().equals(btnRemove))
				{
					Tool tool = (Tool)cboTools.getSelectedItem();
					if(tool!=null)
					{
						if(ShowMessage.questionMessage(thisFrame, "Atualizar", "Deseja mesmo remover a ferramenta selecionado?")==
								JOptionPane.YES_OPTION)
						{
							controller.removeTool(tool);
							ShowMessage.successMessage(null,"Mensagem","Ferramenta removida com sucesso" );
							clearFields();
							controller.fillCboTools(cboTools);
						}
					}
					else
					ShowMessage.errorMessage(null,"Mensagem","Selecione uma ferramenta");
				}	
				
			}
		};
		btnCancel.addActionListener(buttonActions);
		btnRemove.addActionListener(buttonActions);
		btnUpdate.addActionListener(buttonActions);
		cboTools.addActionListener(buttonActions);
		FrameController.addConfirmationOnClose(thisFrame, frameName);
		
	}
	public void clearFields()
	{
		txtBrand.setText("");
		txtDescription.setText("");
		txtModel.setText("");
		txtSerialNumber.setText("");
		cboTools.setSelectedItem(null);
	}
   
	public void fillFields()
	{
		Tool tool = (Tool)cboTools.getSelectedItem();
		if(tool!=null)
		{
			txtBrand.setText(tool.getBrand());
			txtModel.setText(tool.getModel());
			txtDescription.setText(tool.getDescription());
			txtSerialNumber.setText(tool.getSerialNumber());
		}
	}
	public void getFields(Tool tool)
	{
		tool.setBrand(txtBrand.getText());
		tool.setModel(txtModel.getText());
		tool.setDescription(txtDescription.getText());
		tool.setSerialNumber(txtSerialNumber.getText());
	}
	
}
