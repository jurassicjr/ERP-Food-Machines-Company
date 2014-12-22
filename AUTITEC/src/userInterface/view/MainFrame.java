package userInterface.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import userInterface.controller.MainFrameController;

/**
 * Classe responsável pela janela principal do sistema
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1016369432346974527L;
	
	private MainFrameController controller;
	
	private JMenuItem mntmRegisterEmployee;
	private JMenuItem mntmTechnicalStandard;
	private JMenuItem mntmRegisterUser;
	
	private JMenuItem mntmApprovalOfSuppliers;
	private JMenuItem mntmSalesRequisition;
	private JMenuItem mntmResgisterOfSuppliers;
	private JMenuItem mntmSalesOrder;
	private JMenuItem mntmRegisterOfProduct;
	
	/**
	 * Cria a janela principal da aplicação
	 */
	
	public MainFrame() {
		controller = new MainFrameController(this);
		setLookAndFell();
		initialize();
		setListeners();
	}
	
	/**
	 * Inicializa os elemento gráficos da aplicação
	 */
	
	private void initialize() {
				
		setBounds(100, 100, 850, 500);
		setMinimumSize(new Dimension(850, 500));
		setTitle("Sistema de Gestão Empresarial - AUTITEC");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnRh = new JMenu("RH");
		menuBar.add(mnRh);
		
		JMenu mnSales = new JMenu("Vendas");
		menuBar.add(mnSales);
		
		JMenu mnRegister = new JMenu("Cadastros");
		
		mntmRegisterEmployee = new JMenuItem("Registrar Funcionário");
		mnRh.add(mntmRegisterEmployee);
		
		mntmTechnicalStandard = new JMenuItem("Normas Técnicas");
		mnRh.add(mntmTechnicalStandard);
		
		mntmRegisterUser = new JMenuItem("Registrar Usuário");
		mnRh.add(mntmRegisterUser);
		
		
		mnSales.add(mnRegister);
		mntmApprovalOfSuppliers = new JMenuItem("Homologar Fornecedores");
		mnRegister.add(mntmApprovalOfSuppliers);
		mntmResgisterOfSuppliers = new JMenuItem("Cadastro de fornecedores");
		mnRegister.add(mntmResgisterOfSuppliers);
		mntmSalesRequisition = new JMenuItem("Requisição de Compra");
		mnSales.add(mntmSalesRequisition);
		mntmSalesOrder = new JMenuItem("Pedido de Compra");
		mnSales.add(mntmSalesOrder);
		mntmRegisterOfProduct = new JMenuItem("Cadastro de Material/Produto");
		mnRegister.add(mntmRegisterOfProduct);
	}

	/**
	 * Define os listeners dos menus e botões da interface
	 */
	
	private void setListeners() {
				
		ActionListener menuListeners = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					
				if(e.getSource().equals(mntmRegisterEmployee)) controller.registerEmployee();
				else if(e.getSource().equals(mntmTechnicalStandard)) controller.technicalStandard();
				else if(e.getSource().equals(mntmRegisterUser)) controller.registerUser();
				
				else if(e.getSource().equals(mntmApprovalOfSuppliers)) controller.Sales(controller.approvalOfSupliers);	
				else if (e.getSource().equals(mntmSalesRequisition)) controller.Sales(controller.salesRequisition);
				else if (e.getSource().equals(mntmResgisterOfSuppliers))controller.Sales(controller.registerOfSuppliers);
				else if (e.getSource().equals(mntmSalesOrder)) controller.Sales(controller.salesOrder);
				else if (e.getSource().equals(mntmRegisterOfProduct)) controller.Sales(controller.registerOfProduct);
			}
		};
		
		mntmRegisterEmployee.addActionListener(menuListeners);
		mntmTechnicalStandard.addActionListener(menuListeners);
		mntmRegisterUser.addActionListener(menuListeners);
		
		mntmResgisterOfSuppliers.addActionListener(menuListeners);
		mntmApprovalOfSuppliers.addActionListener(menuListeners);
		mntmSalesRequisition.addActionListener(menuListeners);	
		mntmSalesOrder.addActionListener(menuListeners);
		mntmRegisterOfProduct.addActionListener(menuListeners);
	}
	
	/**
	 * Define o look and fell (aparência) da aplicação para a aparência do sistema operacional 
	 */
	
	private void setLookAndFell() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}	
	}
}
