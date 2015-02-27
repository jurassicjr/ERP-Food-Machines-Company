package userInterface.view;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
	private JMenuItem mntmUpdateUser;
	
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

	private ArrayList<JMenuItem> menuItens;

	private JMenu mnBuy;

	private JMenu mnBuyReports;

	private JMenuItem mntmClientReport;

	private JMenu mnSearch;

	private JMenuItem mntmMaterialSearch;

	private JMenuItem mntmProductSearch;

	
	/**
	 * Cria a janela principal da aplicação
	 */	
	public MainFrame() {
				
		controller = new MainFrameController(this);
		
		menuItens = new ArrayList<>();
		
		initialize();
		setListeners();
		createMenu();
		
		controller.setFinancialNotifications(notificationPanel);
		
		MenuBarFactory.build(menuItens);
		
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
		Icon.setIcon(this);
		
		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(5, 5, 5, 5));
		setJMenuBar(menuBar);
						
		mnRh = new JMenu("RH");
		mnRh.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/rh.png")));
		menuBar.add(mnRh);
		mnRh.setVisible(false);
		
		mnFinancial = new JMenu("Financeiro");
		mnFinancial.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/finance.png")));
		menuBar.add(mnFinancial);
		mnFinancial.setVisible(false);
		
		mnSales = new JMenu("Vendas");
		mnSales.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/sales.png")));
		menuBar.add(mnSales);
		mnSales.setVisible(false);
				
		mntmRegisterEmployee = new JMenuItem("Registrar Funcionário");
		mnRh.add(mntmRegisterEmployee);
		mntmRegisterEmployee.setVisible(false);
		mntmRegisterEmployee.setName("REG_EMP");
				
		mntmTechnicalStandard = new JMenuItem("Normas Técnicas");
		mnRh.add(mntmTechnicalStandard);
		mntmTechnicalStandard.setVisible(false);
		mntmTechnicalStandard.setName("TEC_STD");
				
		mntmRegisterUser = new JMenuItem("Registrar Usuário");
		mnRh.add(mntmRegisterUser);
		mntmRegisterUser.setVisible(false);
		mntmRegisterUser.setName("REG_USER");
		
		mntmUpdateUser = new JMenuItem("Atualizar Permissões");
		mnRh.add(mntmUpdateUser);
		mntmUpdateUser.setVisible(false);
		mntmUpdateUser.setName("UPD_PERM");
		
		mntmEmployeeReport = new JMenuItem("Relatório de Funcionários");
		mnRh.add(mntmEmployeeReport);
		mntmEmployeeReport.setVisible(false);
		mntmEmployeeReport.setName("EMP_REP");
		
		mnBill = new JMenu("Contas a Pagar");
		mnFinancial.add(mnBill);
		mnBill.setVisible(false);
		
		mntmRegisterBill = new JMenuItem("Registrar Conta a Pagar");
		mnBill.add(mntmRegisterBill);
		mntmRegisterBill.setVisible(false);
		mntmRegisterBill.setName("REG_BILL");				
		
		mntmPayBill = new JMenuItem("Pagamento de Conta");
		mnBill.add(mntmPayBill);
		mntmPayBill.setVisible(false);
		mntmPayBill.setName("PAY_BILL");
	
		mntmListBills = new JMenuItem("Listar Contas a Pagar");
		mnBill.add(mntmListBills);
		mntmListBills.setVisible(false);
		mntmListBills.setName("LIST_BILL");
		
		mnDebtsToReceive = new JMenu("Contas a Receber");
		mnFinancial.add(mnDebtsToReceive);
		mnDebtsToReceive.setVisible(false);
		
		mntmRegisterDebtsToReceive = new JMenuItem("Registrar Conta a Receber");
		mnDebtsToReceive.add(mntmRegisterDebtsToReceive);
		mntmRegisterDebtsToReceive.setVisible(false);
		mntmRegisterDebtsToReceive.setName("REG_DEBT");
		
		mntmReceiveDebt = new JMenuItem("Recebimento de Conta");
		mnDebtsToReceive.add(mntmReceiveDebt);
		mntmReceiveDebt.setVisible(false);
		mntmReceiveDebt.setName("REC_DEBT");
		
		mntmListDebts = new JMenuItem("Listar Contas a Receber");
		mnDebtsToReceive.add(mntmListDebts);
		mntmListDebts.setVisible(false);
		mntmListDebts.setName("LIST_DEBT");
		
		mntmGenerateReport = new JMenuItem("Relatório Financeiro");
		mnFinancial.add(mntmGenerateReport);
		mntmGenerateReport.setVisible(false);
		mntmGenerateReport.setName("FIN_REP");
		
		mnRegister = new JMenu("Registros");
		mnSales.add(mnRegister);
		mnRegister.setVisible(false);

		mnUpdates = new JMenu("Atualizar/Remover Registros");
		mnSales.add(mnUpdates);
		mnUpdates.setVisible(false);

		mnReports = new JMenu("Relátorios");
		mnSales.add(mnReports);
		mnReports.setVisible(false);

		mnInventory = new JMenu("Estoque");
		mnSales.add(mnInventory);
		mnInventory.setVisible(false);
		
		mnBuy = new JMenu("Compras");
		mnBuy.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/buy_icon.png")));
		menuBar.add(mnBuy);
		mnBuy.setVisible(false);
		
		mnBuyReports = new JMenu("Relatórios");
		mnBuy.add(mnBuyReports);
		mnBuy.setVisible(false);
		
		mntmResgisterOfSuppliers = new JMenuItem("Registrar Fornecedores");
		mnBuy.add(mntmResgisterOfSuppliers);
		mntmResgisterOfSuppliers.setVisible(false);
		mntmResgisterOfSuppliers.setName("REG_SUP");

		mntmApprovalOfSuppliers = new JMenuItem("Homologar Fornecedores");
		mnBuy.add(mntmApprovalOfSuppliers);
		mntmApprovalOfSuppliers.setVisible(false);
		mntmApprovalOfSuppliers.setName("HOM_SUP");
		
		mntmSalesRequisition = new JMenuItem("Requisição de Compra");
		mnSales.add(mntmSalesRequisition);
		mntmSalesRequisition.setVisible(false);
		mntmSalesRequisition.setName("SALE_REQ");
		
		mntmSalesOrder = new JMenuItem("Pedido de Compra");
		mnBuy.add(mntmSalesOrder);
		mntmSalesOrder.setVisible(false);
		mntmSalesOrder.setName("SALE_DEM");
		
		mntmRegisterOfMaterial = new JMenuItem("Registrar Material");
		mnRegister.add(mntmRegisterOfMaterial);
		mntmRegisterOfMaterial.setVisible(false);
		mntmRegisterOfMaterial.setName("REG_MAT");		
		
		mntmRegisterProduct = new JMenuItem("Registrar Produto");
		mnRegister.add(mntmRegisterProduct);
		mntmRegisterProduct.setVisible(false);
		mntmRegisterProduct.setName("REG_PROD");		
		
		mntmKitRegister = new JMenuItem("Registrar KIT");
		mnRegister.add(mntmKitRegister);
		mntmKitRegister.setVisible(false);
		mntmKitRegister.setName("REG_KIT");
		
		mntmProductUpdate = new JMenuItem("Atualizar/Remover Registro de Material");
		mnUpdates.add(mntmProductUpdate);
		mntmProductUpdate.setVisible(false);
		mntmProductUpdate.setName("UPD_MAT");
		
		mntmUpdateKit = new JMenuItem("Atualiza/Remover Registro de Kit");
		mnUpdates.add(mntmUpdateKit);
		mntmUpdateKit.setVisible(false);
		mntmUpdateKit.setName("UPD_KIT");

		mntmUpdateProduct = new JMenuItem("Atualizar/Remover Registro de Produtos");
		mnUpdates.add(mntmUpdateProduct);
		mntmUpdateProduct.setVisible(false);
		mntmUpdateProduct.setName("UPD_PROD");

		mntmSupplierUpdate = new JMenuItem("Atualizar/Remover Registro de Fornecedores");
		mnUpdates.add(mntmSupplierUpdate);
		mntmSupplierUpdate.setVisible(false);
		mntmSupplierUpdate.setName("UPD_SUP");
		
		mntmSupplierReportFrame = new JMenuItem("Gerar Relatório de Fornecedores");
		mnBuyReports.add(mntmSupplierReportFrame);
		mntmSupplierReportFrame.setVisible(false);
		mntmSupplierReportFrame.setName("SUP_REP");
		
		mntmProductsReport = new JMenuItem("Gerar Relatório de Materiais");
		mnReports.add(mntmProductsReport);
		mntmProductsReport.setVisible(false);
		mntmProductsReport.setName("MAT_REP");
		
		mntmAddMaterialToInvetory = new JMenuItem("Inserir Material ao Estoque");
		mnInventory.add(mntmAddMaterialToInvetory);
		mntmAddMaterialToInvetory.setVisible(false);
		mntmAddMaterialToInvetory.setName("INS_STOCK");
		
		mntmClientRegister = new JMenuItem("Registrar Cliente");
		mnRegister.add(mntmClientRegister);
		mntmClientRegister.setVisible(false);
		mntmClientRegister.setName("REG_CLI");
		
		mnProduction = new JMenu("Produção");
		mnProduction.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/production.png")));
		menuBar.add(mnProduction);
		mnProduction.setVisible(false);
		
		mntmClientReport = new JMenuItem("Gerar Relatório de Cliente");
		mnReports.add(mntmClientReport);
		mntmClientReport.setVisible(false);
		mntmClientReport.setName("CLI_REP");
		
		mnSearch = new JMenu("Consultas");
		mnSales.add(mnSearch);
		mnSales.setVisible(false);
		
		mntmMaterialSearch = new JMenuItem("Consulta de Materiais");
		mnSearch.add(mntmMaterialSearch);
		mntmMaterialSearch.setName("SEA_MAT");
		mntmMaterialSearch.setVisible(false);
		
		mntmProductSearch = new JMenuItem("Consulta de Produtos");
		mnSearch.add(mntmProductSearch);
		mntmProductSearch.setName("SEA_PROD");
		mntmProductSearch.setVisible(false);
		
		mntmProductionStage = new JMenuItem("Estágios de Produção");
		mnProduction.add(mntmProductionStage);
		mntmProductionStage.setVisible(false);
		mntmProductionStage.setName("PROD_STG");
		
		mnAbout = new JMenu("Sobre");
		menuBar.add(mnAbout);
		
		mntmRegisterIssue = new JMenuItem("Informar Problema");
		mnAbout.add(mntmRegisterIssue);
		
		mntmAbout = new JMenuItem("Sobre o Software");
		mnAbout.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/about.png")));
		mnAbout.add(mntmAbout);
		
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
	
	private void createMenu() {
				
		menuItens.add(mntmRegisterEmployee);
		menuItens.add(mntmTechnicalStandard);
		menuItens.add(mntmRegisterUser);
		menuItens.add(mntmEmployeeReport);
		menuItens.add(mntmUpdateUser);
		
		menuItens.add(mntmRegisterBill);
		menuItens.add(mntmPayBill);
		menuItens.add(mntmListBills);
		menuItens.add(mntmRegisterDebtsToReceive);
		menuItens.add(mntmReceiveDebt);
		menuItens.add(mntmListDebts);
		menuItens.add(mntmGenerateReport);
		
		menuItens.add(mntmResgisterOfSuppliers);
		menuItens.add(mntmRegisterOfMaterial);
		menuItens.add(mntmRegisterProduct);
		menuItens.add(mntmKitRegister);
		menuItens.add(mntmClientRegister);
		menuItens.add(mntmProductUpdate);
		menuItens.add(mntmSupplierUpdate);
		menuItens.add(mntmUpdateProduct);
		menuItens.add(mntmSupplierReportFrame);
		menuItens.add(mntmProductsReport);
		menuItens.add(mntmAddMaterialToInvetory);
		menuItens.add(mntmApprovalOfSuppliers);
		menuItens.add(mntmSalesRequisition);
		menuItens.add(mntmSalesOrder);
		menuItens.add(mntmUpdateKit);
		menuItens.add(mntmClientReport);
		menuItens.add(mntmMaterialSearch);
		menuItens.add(mntmProductSearch);
		
		menuItens.add(mntmProductionStage);
		
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
				else if(e.getSource().equals(mntmUpdateUser)) controller.updateUser();
				
				else if (e.getSource().equals(mntmApprovalOfSuppliers)) controller.Sales(MainFrameController.approvalOfSupliers);	
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
				else if (e.getSource().equals(mntmClientReport))controller.Sales(MainFrameController.clientReport);
				else if (e.getSource().equals(mntmMaterialSearch))controller.Sales(MainFrameController.materialSearch);
				else if (e.getSource().equals(mntmProductSearch))controller.Sales(MainFrameController.productSearch);
			}
		};
		
		mntmRegisterEmployee.addActionListener(menuListeners);
		mntmTechnicalStandard.addActionListener(menuListeners);
		mntmRegisterUser.addActionListener(menuListeners);
		mntmUpdateUser.addActionListener(menuListeners);
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
		mntmClientReport.addActionListener(menuListeners);
		mntmProductSearch.addActionListener(menuListeners);
		mntmMaterialSearch.addActionListener(menuListeners);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				setExtendedState(MAXIMIZED_BOTH);
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame();
			}
			
		});
	}

}
