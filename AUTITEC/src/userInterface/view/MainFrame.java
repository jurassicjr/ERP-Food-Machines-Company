package userInterface.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import userInterface.controller.MainFrameController;

/**
 * Classe responsável pela janela principal do sistema
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1016369432346974527L;
	
	private MainFrameController controller;
	
	private JPanel notificationPanel;
				
	private JMenuItem mntmRegisterEmployee;
	private JMenuItem mntmTechnicalStandard;
	private JMenuItem mntmRegisterUser;
	
	private JMenuItem mntmRegisterBill;
	
	private JMenuItem mntmApprovalOfSuppliers;
	private JMenuItem mntmSalesRequisition;
	private JMenuItem mntmResgisterOfSuppliers;
	private JMenuItem mntmSalesOrder;
	private JMenuItem mntmRegisterOfProduct;
	
	private JMenuBar menuBar;

	private JMenu mnRh;
	private JMenu mnFinancial;
	
	private JMenu mnSales;

	private JMenu mnRegister;

	private JMenu mnUpdates;

	private JMenuItem mntmProductUpdate;
	
	/**
	 * Cria a janela principal da aplicação
	 */	
	public MainFrame() {
		
		controller = new MainFrameController(this);
		
		setLookAndFell();
		initialize();
		setListeners();
		
		controller.setFinancialNotifications(notificationPanel);
	}
	
	/**
	 * Inicializa os elemento gráficos da aplicação
	 */	
	private void initialize() {
		
		this.setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 850, 500);
		setMinimumSize(new Dimension(850, 500));
		setTitle("Sistema de Gestão Empresarial - AUTITEC");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnRh = new JMenu("RH");
		menuBar.add(mnRh);
		
		mnFinancial = new JMenu("Financeiro");
		menuBar.add(mnFinancial);
		
		mnSales = new JMenu("Vendas");
		menuBar.add(mnSales);
		
		mnRegister = new JMenu("Cadastros");
		
		mnUpdates = new JMenu("Atualizar/Deletar");
		mntmRegisterEmployee = new JMenuItem("Registrar Funcionário");
		mnRh.add(mntmRegisterEmployee);
		
		mntmTechnicalStandard = new JMenuItem("Normas Técnicas");
		mnRh.add(mntmTechnicalStandard);
		
		mntmRegisterUser = new JMenuItem("Registrar Usuário");
		mnRh.add(mntmRegisterUser);
		
		mntmRegisterBill = new JMenuItem("Registrar Conta a Pagar");
		mnFinancial.add(mntmRegisterBill);
		
		
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
		mnSales.add(mnUpdates);
		mntmProductUpdate = new JMenuItem("Produtos/Material");
		mnUpdates.add(mntmProductUpdate);			
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(150, 150));
		scrollPane.setBorder(new TitledBorder("Notificações"));
		getContentPane().add(scrollPane, BorderLayout.WEST);
	
		notificationPanel = new JPanel();
		notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(notificationPanel);					
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
				else if(e.getSource().equals(mntmRegisterBill)) controller.registerBill();
				
				else if(e.getSource().equals(mntmApprovalOfSuppliers)) controller.Sales(controller.approvalOfSupliers);	
				else if (e.getSource().equals(mntmSalesRequisition)) controller.Sales(controller.salesRequisition);
				else if (e.getSource().equals(mntmResgisterOfSuppliers))controller.Sales(controller.registerOfSuppliers);
				else if (e.getSource().equals(mntmSalesOrder)) controller.Sales(controller.salesOrder);
				else if (e.getSource().equals(mntmRegisterOfProduct)) controller.Sales(controller.registerOfProduct);
				else if (e.getSource().equals(mntmProductUpdate)) controller.Sales(controller.updateOfProduct);
			}
		};
		
		mntmRegisterEmployee.addActionListener(menuListeners);
		mntmTechnicalStandard.addActionListener(menuListeners);
		mntmRegisterUser.addActionListener(menuListeners);
		mntmRegisterBill.addActionListener(menuListeners);
		
		mntmResgisterOfSuppliers.addActionListener(menuListeners);
		mntmApprovalOfSuppliers.addActionListener(menuListeners);
		mntmSalesRequisition.addActionListener(menuListeners);	
		mntmSalesOrder.addActionListener(menuListeners);
		mntmRegisterOfProduct.addActionListener(menuListeners);
		mntmProductUpdate.addActionListener(menuListeners);
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
