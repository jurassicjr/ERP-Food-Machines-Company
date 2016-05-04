package maintenance.view.register;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import maintenance.controller.ToolBoxRegisterFrameController;
import model.Employee;
import model.Tool;
import model.ToolBox;
import userInterface.components.FrameController;
import util.Icon;
import util.ShowMessage;

public class ToolBoxRegisterFrame extends JFrame {

	/**
	 * 
	 */
	private ToolBoxRegisterFrameController controller;
	private ToolBoxRegisterFrame thisFrame = this;
	private String frameName = "Registro de caixa de ferramentas";
	private static final long serialVersionUID = 1L;
	private JTextField txtDescription;
	private JComboBox<Employee> cboResponsible;
	private JComboBox<Tool> cboTools;
	private JButton btnAddTool;
	private JButton btnRemoveTool;
	private JButton btnCancel;
	private JButton btnConfirm;
	private JList<Tool> toolList;
	
	public ToolBoxRegisterFrame()
	{

		controller = new ToolBoxRegisterFrameController();
		initialize();
		setListeners();
		
	}
	public void initialize()
	{
		Icon.setIcon(this);
		setTitle(frameName);
		setBounds(100, 100, 573, 329);
		setMinimumSize(new Dimension(573, 329));
		setPreferredSize(new Dimension(573, 329));
		initializePrincipal();
		controller.fillResponsibleCbo(cboResponsible);
		controller.fillToolCbo(cboTools);
		toolList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	public void initializePrincipal()
	{
		getContentPane().setLayout(new BorderLayout());
		JPanel principalPanel = new JPanel();
		principalPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		principalPanel.setLayout(null);
		getContentPane().add(principalPanel, BorderLayout.CENTER);
		JLabel lblNewLabel = new JLabel("Descrição");
		lblNewLabel.setBounds(12, 12, 70, 15);
		principalPanel.add(lblNewLabel);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(12, 29, 257, 24);
		principalPanel.add(txtDescription);
		txtDescription.setColumns(10);
		
		cboResponsible = new JComboBox();
		cboResponsible.setBounds(298, 29, 253, 24);
		principalPanel.add(cboResponsible);
		
		JLabel lblNewLabel_1 = new JLabel("Responsável");
		lblNewLabel_1.setBounds(298, 12, 120, 15);
		principalPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ferramentas disponíveis");
		lblNewLabel_2.setBounds(12, 63, 213, 15);
		principalPanel.add(lblNewLabel_2);
		
		cboTools = new JComboBox();
		cboTools.setBounds(12, 82, 427, 24);
		principalPanel.add(cboTools);
		
		btnAddTool = new JButton("Adicionar");
		btnAddTool.setIcon(new ImageIcon(ToolBoxRegisterFrame.class.getResource("/resources/plus.png")));
		btnAddTool.setBounds(451, 82, 100, 25);
		principalPanel.add(btnAddTool);
		
		btnRemoveTool = new JButton("Remover");
		btnRemoveTool.setIcon(new ImageIcon(ToolBoxRegisterFrame.class.getResource("/resources/clear.png")));
		btnRemoveTool.setBounds(12, 201, 147, 25);
		principalPanel.add(btnRemoveTool);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 122, 539, 70);
		principalPanel.add(scrollPane);
		
		toolList = new JList<Tool>();
		scrollPane.setViewportView(toolList);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnConfirm = new JButton("Confirmar");
		btnConfirm.setBounds(396, 28, 141, 25);
		btnConfirm.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/ok.png")));
		panel.add(btnConfirm);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/cancel.png")));
		btnCancel.setBounds(243, 28, 141, 25);
		
		panel.add(btnCancel);
	}
	public void setListeners()
	{
		ActionListener buttonListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnCancel))
					FrameController.close(thisFrame, frameName);
				else
				if(e.getSource().equals(btnAddTool))
				   addTool();
				else
				if(e.getSource().equals(btnRemoveTool))
				{
				   removeTool();
				}
				if(e.getSource().equals(btnConfirm))
					persistToolBoxAndTools();
			}
		};
		btnCancel.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		btnAddTool.addActionListener(buttonListener);
		btnRemoveTool.addActionListener(buttonListener);
		
		FrameController.addConfirmationOnClose(thisFrame,frameName);
	}
	public void addTool()
	{
		Object objTool =  cboTools.getSelectedItem();
		if(objTool!=null)
		{
			Tool tool = (Tool) objTool;
			controller.addTool(tool, toolList,cboTools);
		}
		else
			ShowMessage.errorMessage(null,"Mensagem", "Selecione uma ferramenta");
	}
	public void removeTool()
	{
		Tool tool =  toolList.getSelectedValue();
		if(tool!=null)
			controller.removeTool(tool, toolList,cboTools);	
		else
		ShowMessage.errorMessage(null,"Mensagem", "Selecione uma ferramenta");
	}
	public void persistToolBoxAndTools()
	{
		if(!txtDescription.getText().isEmpty() && cboResponsible.getSelectedItem()!=null)
		{
			if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja mesmo inserir a caixa de ferramentas?")==
					JOptionPane.YES_OPTION)
			{
				ToolBox toolBox = new ToolBox();
				toolBox.setDescription(txtDescription.getText());
				toolBox.setResponsible(cboResponsible.getItemAt(cboResponsible.getSelectedIndex()).getId());
				
				Integer toolBoxId = controller.persistToolBox(toolBox);
				controller.persistToolBoxTools(toolBoxId);
				controller.fillToolCbo(cboTools);
				clearFields();
				ShowMessage.successMessage(null,"Mensagem","Caixa de ferramentas inserida com sucesso");
			}
		}
		else
		ShowMessage.errorMessage(null,"Mensagem", "Preencha os dados da caixa de ferramentas");
	}
	public void clearFields()
	{
		txtDescription.setText(null);
		cboResponsible.setSelectedItem(null);
		controller.clearToolList(toolList);
	}
}
