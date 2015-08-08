package maintenance.view.register;

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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import maintenance.controller.ToolBoxRegisterFrameController;
import model.Employee;
import model.Tool;
import userInterface.components.FrameController;
import util.Icon;

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
	private JTable tableTools;
	
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
		controller.initToolsTable(tableTools);
		tableTools.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTools.setRowSelectionAllowed(true);
		tableTools.setFocusable(true);
	}
	public void initializePrincipal()
	{
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Descrição");
		lblNewLabel.setBounds(12, 12, 70, 15);
		getContentPane().add(lblNewLabel);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(12, 32, 257, 19);
		getContentPane().add(txtDescription);
		txtDescription.setColumns(10);
		
		cboResponsible = new JComboBox();
		cboResponsible.setBounds(298, 29, 253, 24);
		getContentPane().add(cboResponsible);
		
		JLabel lblNewLabel_1 = new JLabel("Responsável");
		lblNewLabel_1.setBounds(298, 12, 120, 15);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ferramentas disponíveis");
		lblNewLabel_2.setBounds(12, 63, 213, 15);
		getContentPane().add(lblNewLabel_2);
		
		cboTools = new JComboBox();
		cboTools.setBounds(12, 82, 427, 24);
		getContentPane().add(cboTools);
		
		btnAddTool = new JButton("Adicionar");
		btnAddTool.setBounds(451, 82, 100, 25);
		getContentPane().add(btnAddTool);
		
		btnRemoveTool = new JButton("Remover");
		btnRemoveTool.setBounds(12, 201, 147, 25);
		getContentPane().add(btnRemoveTool);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 122, 539, 70);
		getContentPane().add(scrollPane);
		
		tableTools = new JTable();
		scrollPane.setViewportView(tableTools);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 222, 549, 53);
		getContentPane().add(panel);
		panel.setLayout(null);
		
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
			controller.addTool(tool);
		}
	}
	public void removeTool()
	{
		int line = tableTools.getSelectedRow();
	
		if(line > -1)
		{
			controller.removeTool(line);			
		}

		
		
	}
}
