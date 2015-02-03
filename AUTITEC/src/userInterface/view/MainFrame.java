package userInterface.view;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import userInterface.controller.MainFrameController;
import util.Icon;

/**
 * Classe responsável pela janela principal do sistema
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1016369432346974527L;

	private MainFrameController controller;
	
	private JScrollPane scrollPane;
	private JPanel notificationPanel;
		
	private JMenuBar menuBar;
	
	private JMenu mnRh;
	private JMenu mnFinancial;
	private JMenu mnProduction;
	private JMenu mnAbout;
				
	private JMenuItem mntmRegisterEmployee;
	private JMenuItem mntmTechnicalStandard;
	private JMenuItem mntmRegisterUser;
	private JMenuItem mntmEmployeeReport;
	
	private JMenu mnBill;
	private JMenuItem mntmRegisterBill;
	private JMenuItem mntmPayBill;
	private JMenuItem mntmListBills;
	private JMenuItem mntmGenerateReport;
		
	private JMenu mnDebtsToReceive;
	private JMenuItem mntmRegisterDebtsToReceive;
	private JMenuItem mntmReceiveDebt;
	private JMenuItem mntmListDebts;
	
	private JMenuItem mntmProductionStage;
	
	private JMenuItem mntmAbout;
	private JMenuItem mntmRegisterIssue;
	
	
	private JMenuItem mntmApprovalOfSuppliers;
	private JMenuItem mntmSalesRequisition;
	private JMenuItem mntmResgisterOfSuppliers;
	private JMenuItem mntmSalesOrder;
	private JMenuItem mntmRegisterOfMaterial;
	private JMenuItem mntmSupplierUpdate;
	private JMenuItem mntmProductUpdate;
	private JMenuItem mntmSupplierReportFrame;
	private JMenuItem mntmRegisterProduct;
	
	private JMenu mnSales;
	private JMenu mnRegister;
	private JMenu mnUpdates;
	private JMenu mnReports;

	private JMenuItem mntmProductsReport;

	private JMenuItem mntmClientRegister;

	private JMenuItem mntmAddMaterialToInvetory;

	private JMenuItem mntmKitRegister;

	private JMenuItem mntmUpdateProduct;

	private JMenu mnInventory;

	private JMenuItem mntmUpdateKit;
	
	/**
	 * Cria a janela principal da aplicação
	 */	
	public MainFrame() {
				
		controller = new MainFrameController(this);
		
		initialize();
		setListeners();
		
		controller.setFinancialNotifications(notificationPanel);
	}
	
	/**
	 * Inicializa os elemento gráficos da aplicação
	 */	
	private void initialize() {
		
		setBounds(100, 100, 850, 500);
		setMinimumSize(new Dimension(850, 500));
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Sistema de Gestão Empresarial - AUTITEC");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Icon.setIcon(this);
		
		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(5, 5, 5, 5));
		setJMenuBar(menuBar);
		
		mnRh = new JMenu("RH");
		mnRh.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/rh.png")));
		menuBar.add(mnRh);
		
		mnFinancial = new JMenu("Financeiro");
		mnFinancial.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/finance.png")));
		menuBar.add(mnFinancial);
		
		mnSales = new JMenu("Vendas");
		mnSales.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/sales.png")));
		menuBar.add(mnSales);
		
		
		mntmRegisterEmployee = new JMenuItem("Registrar Funcionário");
		mnRh.add(mntmRegisterEmployee);
		
		mntmTechnicalStandard = new JMenuItem("Normas Técnicas");
		mnRh.add(mntmTechnicalStandard);
		
		mntmRegisterUser = new JMenuItem("Registrar Usuário");
		mnRh.add(mntmRegisterUser);
		
		mntmEmployeeReport = new JMenuItem("Relatório de Funcionários");
		mnRh.add(mntmEmployeeReport);
		
		mnBill = new JMenu("Contas a Pagar");
		mnFinancial.add(mnBill);
		
		mntmRegisterBill = new JMenuItem("Registrar Conta a Pagar");
		mnBill.add(mntmRegisterBill);
		
		mntmPayBill = new JMenuItem("Pagamento de Conta");
		mnBill.add(mntmPayBill);
		
		mntmListBills = new JMenuItem("Listar Contas a Pagar");
		mnBill.add(mntmListBills);
		
		mnDebtsToReceive = new JMenu("Contas a Receber");
		mnFinancial.add(mnDebtsToReceive);
		
		mntmRegisterDebtsToReceive = new JMenuItem("Registrar Conta a Receber");
		mnDebtsToReceive.add(mntmRegisterDebtsToReceive);
		
		mntmReceiveDebt = new JMenuItem("Recebimento de Conta");
		mnDebtsToReceive.add(mntmReceiveDebt);
		
		mntmListDebts = new JMenuItem("Listar Contas a Receber");
		mnDebtsToReceive.add(mntmListDebts);
		
		mntmGenerateReport = new JMenuItem("Relatório Financeiro");
		mnFinancial.add(mntmGenerateReport);
		
		mnRegister = new JMenu("Registros");
		mnSales.add(mnRegister);

		mnUpdates = new JMenu("Atualizar/Remover Registros");
		mnSales.add(mnUpdates);

		mnReports = new JMenu("Relátorios");
		mnSales.add(mnReports);

		mnInventory = new JMenu("Estoque");
		mnSales.add(mnInventory);
		
		mntmApprovalOfSuppliers = new JMenuItem("Homologar Fornecedores");
		mnSales.add(mntmApprovalOfSuppliers);
		
		mntmResgisterOfSuppliers = new JMenuItem("Registrar Fornecedores");
		mnRegister.add(mntmResgisterOfSuppliers);
		
		mntmSalesRequisition = new JMenuItem("Requisição de Compra");
		mnSales.add(mntmSalesRequisition);
		
		mntmSalesOrder = new JMenuItem("Pedido de Compra");
		mnSales.add(mntmSalesOrder);
		
		mntmRegisterOfMaterial = new JMenuItem("Registrar Material");
		mnRegister.add(mntmRegisterOfMaterial);
		
		mntmRegisterProduct = new JMenuItem("Registrar Produto");
		mnRegister.add(mntmRegisterProduct);
		
		mntmKitRegister = new JMenuItem("Registrar KIT");
		mnRegister.add(mntmKitRegister);
		
		mntmProductUpdate = new JMenuItem("Atualizar/Remover Registro de Material");
		mnUpdates.add(mntmProductUpdate);			
		
		mntmSupplierUpdate = new JMenuItem("Atualizar/Remover Registro de Fornecedores");
		mnUpdates.add(mntmSupplierUpdate);
		
		mntmSupplierReportFrame = new JMenuItem("Gerar Relatório de Fornecedores");
		mnReports.add(mntmSupplierReportFrame);
		
		mntmProductsReport = new JMenuItem("Gerar Relatório de Materiais");
		mnReports.add(mntmProductsReport);
		
		mntmAddMaterialToInvetory = new JMenuItem("Inserir Material ao Estoque");
		mnInventory.add(mntmAddMaterialToInvetory);
		
		mntmClientRegister = new JMenuItem("Registrar Cliente");
		mnRegister.add(mntmClientRegister);
		
		mnProduction = new JMenu("Produção");
		mnProduction.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/production.png")));
		menuBar.add(mnProduction);
		
		mntmProductionStage = new JMenuItem("Estágios de Produção");
		mnProduction.add(mntmProductionStage);
		
		mnAbout = new JMenu("Sobre");
		menuBar.add(mnAbout);
		
		mntmRegisterIssue = new JMenuItem("Informar Problema");
		mnAbout.add(mntmRegisterIssue);
		
		mntmAbout = new JMenuItem("Sobre o Software");
		mnAbout.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/about.png")));
		mnAbout.add(mntmAbout);
		
		mntmUpdateProduct = new JMenuItem("Atualizar/Remover Registro de Produtos");
		mnUpdates.add(mntmUpdateProduct);
		
		mntmUpdateKit = new JMenuItem("Atualizar/Remover Registro de Kit");
		mnUpdates.add(mntmUpdateKit);
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(150, 150));
		scrollPane.setBorder(new TitledBorder("Notificações"));
	
		notificationPanel = new JPanel();
		notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));

		scrollPane.setViewportView(notificationPanel);
		
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1178, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 684, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(layout);
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
				else if(e.getSource().equals(mntmEmployeeReport)) controller.employeeReport();
				else if(e.getSource().equals(mntmRegisterBill)) controller.registerBill();
				else if(e.getSource().equals(mntmRegisterDebtsToReceive)) controller.registerDebts();
				else if(e.getSource().equals(mntmPayBill)) controller.payBill();
				else if(e.getSource().equals(mntmListBills)) controller.listBills();
				else if(e.getSource().equals(mntmGenerateReport)) controller.generateReport();
				else if(e.getSource().equals(mntmReceiveDebt)) controller.receiveDebt();
				else if(e.getSource().equals(mntmListDebts)) controller.listDebts();
				else if(e.getSource().equals(mntmAbout)) controller.about();
				else if(e.getSource().equals(mntmRegisterIssue)) controller.registerIssue();
				else if(e.getSource().equals(mntmProductionStage)) controller.stagesProduction();
				
				else if(e.getSource().equals(mntmApprovalOfSuppliers)) controller.Sales(MainFrameController.approvalOfSupliers);	
				else if (e.getSource().equals(mntmSalesRequisition)) controller.Sales(MainFrameController.salesRequisition);
				else if (e.getSource().equals(mntmResgisterOfSuppliers))controller.Sales(MainFrameController.registerOfSuppliers);
				else if (e.getSource().equals(mntmSalesOrder)) controller.Sales(MainFrameController.salesOrder);
				else if (e.getSource().equals(mntmRegisterOfMaterial)) controller.Sales(MainFrameController.registerOfMaterial);
				else if (e.getSource().equals(mntmProductUpdate)) controller.Sales(MainFrameController.updateOfMaterial);
				else if (e.getSource().equals(mntmSupplierUpdate)) controller.Sales(MainFrameController.updateOfSupplier);
				else if (e.getSource().equals(mntmSupplierReportFrame)) controller.Sales(MainFrameController.supplierReport);
				else if (e.getSource().equals(mntmProductsReport))controller.Sales(MainFrameController.productReport);
				else if (e.getSource().equals(mntmClientRegister)) controller.Sales(MainFrameController.clientRegistration);
				else if (e.getSource().equals(mntmRegisterProduct)) controller.Sales(MainFrameController.registerProduct);
				else if	(e.getSource().equals(mntmAddMaterialToInvetory)) controller.Sales(MainFrameController.inventory);
				else if (e.getSource().equals(mntmKitRegister)) controller.Sales(MainFrameController.registerOfKit);
				else if (e.getSource().equals(mntmUpdateProduct))controller.Sales(MainFrameController.updateOfCompostProduct);
				else if (e.getSource().equals(mntmUpdateKit))controller.Sales(MainFrameController.updateOfKit);
			}
		};
		
		mntmRegisterEmployee.addActionListener(menuListeners);
		mntmTechnicalStandard.addActionListener(menuListeners);
		mntmRegisterUser.addActionListener(menuListeners);
		mntmRegisterBill.addActionListener(menuListeners);
		mntmRegisterDebtsToReceive.addActionListener(menuListeners);
		mntmPayBill.addActionListener(menuListeners);
		mntmListBills.addActionListener(menuListeners);
		mntmGenerateReport.addActionListener(menuListeners);
		mntmReceiveDebt.addActionListener(menuListeners);
		mntmListDebts.addActionListener(menuListeners);
		mntmAbout.addActionListener(menuListeners);
		mntmEmployeeReport.addActionListener(menuListeners);
		mntmRegisterIssue.addActionListener(menuListeners);
		mntmClientRegister.addActionListener(menuListeners);
		mntmRegisterProduct.addActionListener(menuListeners);
		mntmProductionStage.addActionListener(menuListeners);
		
		mntmResgisterOfSuppliers.addActionListener(menuListeners);
		mntmApprovalOfSuppliers.addActionListener(menuListeners);
		mntmSalesRequisition.addActionListener(menuListeners);	
		mntmSalesOrder.addActionListener(menuListeners);
		mntmRegisterOfMaterial.addActionListener(menuListeners);
		mntmProductUpdate.addActionListener(menuListeners);
		mntmSupplierUpdate.addActionListener(menuListeners);
		mntmSupplierReportFrame.addActionListener(menuListeners);
		mntmProductsReport.addActionListener(menuListeners);
		mntmAddMaterialToInvetory.addActionListener(menuListeners);
		mntmKitRegister.addActionListener(menuListeners);
		mntmUpdateProduct.addActionListener(menuListeners);
		mntmUpdateKit.addActionListener(menuListeners);
	}

}
