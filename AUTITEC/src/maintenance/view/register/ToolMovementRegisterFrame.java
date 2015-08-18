package maintenance.view.register;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import maintenance.controller.ToolMovementRegisterFrameController;
import model.Employee;
import model.Tool;
import model.ToolBox;
import userInterface.components.FrameController;
import util.Icon;

public class ToolMovementRegisterFrame extends JFrame
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ToolMovementRegisterFrameController controller;
	private String frameName = "Registro de movimentação de ferramentas";
	private ToolMovementRegisterFrame thisFrame = this;
	private JComboBox<Employee> cboEmployees;
	private JComboBox<ToolBox> cboToolBoxes;
	private JRadioButton rdbtnwithDraw;
	private JRadioButton rdbtnReturn;
	private JComboBox<Tool> cboTool;
	private JButton btnConfirm;
	private JButton btnCancel;
	private ButtonGroup grpToolMovementType;
	
	public ToolMovementRegisterFrame()
	{
	
		controller = new ToolMovementRegisterFrameController();
		setListeners();
		initialize();
	}
	public void initialize()
	{
		Icon.setIcon(this);
		setTitle(frameName);
		setBounds(100, 100, 540,350);
		setMinimumSize(new Dimension(540, 350));
		setPreferredSize(new Dimension(540, 350));
		initializePrincipal();
		cboTool.setEnabled(false);
	}
	public void initializePrincipal()
	{
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 238, 516, 47);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		btnConfirm = new JButton("Confimar");
		btnConfirm.setBounds(367, 22, 117, 25);
		panel.add(btnConfirm);
		btnConfirm.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/ok.png")));
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(231, 22, 117, 25);
		panel.add(btnCancel);
		btnCancel.setIcon(new ImageIcon(VehicleRegisterFrame.class.getResource("/resources/cancel.png")));
		
		JLabel lblEmployee = new JLabel("Funcionário");
		lblEmployee.setBounds(12, 26, 157, 15);
		getContentPane().add(lblEmployee);
		
		cboEmployees = new JComboBox<Employee>();
		cboEmployees.setBounds(12, 44, 506, 24);
		getContentPane().add(cboEmployees);
		
		cboToolBoxes = new JComboBox<ToolBox>();
		cboToolBoxes.setBounds(12, 95, 506, 24);
		getContentPane().add(cboToolBoxes);
		
		JLabel lblNewLabel = new JLabel("Caixa de ferramentas");
		lblNewLabel.setBounds(12, 80, 211, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblTool = new JLabel("Tool");
		lblTool.setBounds(12, 197, 70, 15);
		getContentPane().add(lblTool);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 122, 516, 47);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		rdbtnwithDraw = new JRadioButton("Retirada");
		rdbtnwithDraw.setBounds(0, 24, 112, 23);
		panel_1.add(rdbtnwithDraw);
		
		rdbtnReturn = new JRadioButton("Devolução");
		rdbtnReturn.setBounds(409, 24, 119, 23);
		panel_1.add(rdbtnReturn);
		grpToolMovementType = new ButtonGroup();
		grpToolMovementType.add(rdbtnReturn);
		grpToolMovementType.add(rdbtnwithDraw);
		
		cboTool = new JComboBox<Tool>();
		cboTool.setBounds(12, 214, 506, 24);
		getContentPane().add(cboTool);
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
		btnCancel.addActionListener(buttonActions);
	}
}
