package maintenance.view.register;

import java.awt.Dimension;
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

import maintenance.controller.ToolBoxUpdateFrameController;
import model.Employee;
import model.Tool;
import model.ToolBox;
import userInterface.components.FrameController;
import util.Icon;
import util.ShowMessage;

public class ToolBoxUpdateFrame extends JFrame
{
	/**
	 * 
	 */
	private ToolBoxUpdateFrameController controller;
	private ToolBoxUpdateFrame thisFrame = this;
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
	private JComboBox<ToolBox> cboToolBoxes;
	private JButton btnRemove;
	
	public ToolBoxUpdateFrame()
	{

		controller = new ToolBoxUpdateFrameController();
		initialize();
		setListeners();
		
	}
	public void initialize()
	{
		Icon.setIcon(this);
		setTitle(frameName);
		setBounds(100, 100, 573, 370);
		setMinimumSize(new Dimension(573, 370));
		setPreferredSize(new Dimension(573, 370));
		initializePrincipal();
		controller.fillResponsibleCbo(cboResponsible);
		controller.fillToolCbo(cboTools);
		controller.fillToolBoxCbo(cboToolBoxes);
		toolList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fillToolBox();
	
	}
	public void initializePrincipal()
	{
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descrição");
		lblNewLabel.setBounds(12, 54, 70, 15);
		getContentPane().add(lblNewLabel);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(12, 74, 257, 19);
		getContentPane().add(txtDescription);
		txtDescription.setColumns(10);
		
		cboResponsible = new JComboBox<Employee>();
		cboResponsible.setBounds(298, 71, 253, 24);
		getContentPane().add(cboResponsible);
		
		JLabel lblNewLabel_1 = new JLabel("Responsável");
		lblNewLabel_1.setBounds(298, 54, 120, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ferramentas disponíveis");
		lblNewLabel_2.setBounds(12, 107, 213, 15);
		getContentPane().add(lblNewLabel_2);
		
		cboTools = new JComboBox();
		cboTools.setBounds(12, 126, 427, 24);
		getContentPane().add(cboTools);
		
		btnAddTool = new JButton("Adicionar");
		btnAddTool.setIcon(new ImageIcon(ToolBoxUpdateFrame.class.getResource("/resources/plus.png")));
		btnAddTool.setBounds(451, 126, 100, 25);
		getContentPane().add(btnAddTool);
		
		btnRemoveTool = new JButton("Remover");
		btnRemoveTool.setBounds(12, 248, 147, 25);
		getContentPane().add(btnRemoveTool);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 163, 539, 64);
		getContentPane().add(scrollPane);
		
		toolList = new JList<Tool>();
		scrollPane.setViewportView(toolList);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 268, 549, 53);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnConfirm = new JButton("Confirmar");
		btnConfirm.setBounds(396, 28, 141, 25);
		btnConfirm.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/ok.png")));
		panel.add(btnConfirm);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/cancel.png")));
		btnCancel.setBounds(97, 28, 141, 25);
		
		panel.add(btnCancel);
		
		btnRemove = new JButton("Remover");
		btnRemove.setBounds(250, 28, 132, 25);
		btnRemove.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/clear.png")));
		panel.add(btnRemove);
	
		cboToolBoxes = new JComboBox<ToolBox>();
		cboToolBoxes.setBounds(12, 18, 539, 24);
		getContentPane().add(cboToolBoxes);
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
					updateToolBoxAndTools();
				else
				if(e.getSource().equals(cboToolBoxes))
					fillToolBox();
				if(e.getSource().equals(btnRemove))
					removeToolBox();
					
				
			}
		};
		btnCancel.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		btnAddTool.addActionListener(buttonListener);
		btnRemoveTool.addActionListener(buttonListener);
		btnRemove.addActionListener(buttonListener);
		cboToolBoxes.addActionListener(buttonListener);
		
		FrameController.addConfirmationOnClose(thisFrame,frameName);
	}
	public void removeToolBox()
	{
		ToolBox toolBox = cboToolBoxes.getItemAt(cboToolBoxes.getSelectedIndex());
		if(toolBox!=null)
		{	
			if(ShowMessage.questionMessage(thisFrame, "REMOVER", "Deseja mesmo remover a caixa de ferramentas?")==
					JOptionPane.YES_OPTION)
			{
				controller.removeToolBoxAndTools(toolBox);
				controller.fillToolCbo(cboTools);
				controller.fillToolBoxCbo(cboToolBoxes);
				clearFields();
				ShowMessage.successMessage(null,"Mensagem","Caixa de ferramentas removida com sucesso");
			}
		}
	}
	public void fillToolBox()
	{
		ToolBox toolBox = cboToolBoxes.getItemAt(cboToolBoxes.getSelectedIndex());
		if(toolBox!=null)
		{	
			txtDescription.setText(toolBox.getDescription());
			cboResponsible.setSelectedItem(controller.getEmployeeRegister(toolBox.getResponsible()));
			controller.getTools(toolList,toolBox.getId());
			
		}
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
	public void updateToolBoxAndTools()
	{
		if(!txtDescription.getText().isEmpty() && cboResponsible.getSelectedItem()!=null)
		{
			if(ShowMessage.questionMessage(thisFrame, "REGISTRO", "Deseja mesmo atualizar a caixa de ferramentas?")==
					JOptionPane.YES_OPTION)
			{
				ToolBox toolBox = cboToolBoxes.getItemAt(cboToolBoxes.getSelectedIndex());
				
				toolBox.setDescription(txtDescription.getText());
				toolBox.setResponsible(cboResponsible.getItemAt(cboResponsible.getSelectedIndex()).getId());
				
				Integer toolBoxId = controller.updateToolBox(toolBox);
				controller.updateToolBoxTools(toolBoxId);
				controller.fillToolCbo(cboTools);
				clearFields();
				ShowMessage.successMessage(null,"Mensagem","Caixa de ferramentas atualizada com sucesso");
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
		cboToolBoxes.setSelectedItem(null);
	}
}
