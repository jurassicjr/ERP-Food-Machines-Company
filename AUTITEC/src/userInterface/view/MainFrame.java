package userInterface.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
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
	
	private JMenuItem mntmAbout;
	private JMenuItem mntmRegisterIssue;
	
	
	private JMenuItem mntmApprovalOfSuppliers;
	private JMenuItem mntmSalesRequisition;
	private JMenuItem mntmResgisterOfSuppliers;
	private JMenuItem mntmSalesOrder;
	private JMenuItem mntmRegisterOfProduct;
	private JMenuItem mntmSupplierUpdate;
	private JMenuItem mntmProductUpdate;
	private JMenuItem mntmSupplierReportFrame;
	
	private JMenu mnSales;
	private JMenu mnRegister;
	private JMenu mnUpdates;
	private JMenu mnReports;
	
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
		
		
		
		mnSales.add(mnRegister);
		mntmApprovalOfSuppliers = new JMenuItem("Homologar Fornecedores");
		mnRegister.add(mntmApprovalOfSuppliers);
		mntmResgisterOfSuppliers = new JMenuItem("Cadastro de Fornecedores");
		mnRegister.add(mntmResgisterOfSuppliers);
		mntmSalesRequisition = new JMenuItem("Requisição de Compra");
		mnSales.add(mntmSalesRequisition);
		mntmSalesOrder = new JMenuItem("Pedido de Compra");
		mnSales.add(mntmSalesOrder);
		mntmRegisterOfProduct = new JMenuItem("Cadastro de Material");
		mnRegister.add(mntmRegisterOfProduct);
		mnSales.add(mnUpdates);
		mntmProductUpdate = new JMenuItem("Material");
		mnUpdates.add(mntmProductUpdate);			
		mntmSupplierUpdate = new JMenuItem("Atualização/Remoção de Fornecedores");
		mnUpdates.add(mntmSupplierUpdate);
		mnReports = new JMenu("Relátorios");
		mnSales.add(mnReports);
		mntmSupplierReportFrame = new JMenuItem("Relátorio de Fornecedores");
		mnReports.add(mntmSupplierReportFrame);
		
		mnAbout = new JMenu("Sobre");
		menuBar.add(mnAbout);
		
		mntmRegisterIssue = new JMenuItem("Informar Problema");
		mnAbout.add(mntmRegisterIssue);
		
		mntmAbout = new JMenuItem("Sobre o Software");
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
				
				else if(e.getSource().equals(mntmApprovalOfSuppliers)) controller.Sales(MainFrameController.approvalOfSupliers);	
				else if (e.getSource().equals(mntmSalesRequisition)) controller.Sales(MainFrameController.salesRequisition);
				else if (e.getSource().equals(mntmResgisterOfSuppliers))controller.Sales(MainFrameController.registerOfSuppliers);
				else if (e.getSource().equals(mntmSalesOrder)) controller.Sales(MainFrameController.salesOrder);
				else if (e.getSource().equals(mntmRegisterOfProduct)) controller.Sales(MainFrameController.registerOfProduct);
				else if (e.getSource().equals(mntmProductUpdate)) controller.Sales(MainFrameController.updateOfProduct);
				else if (e.getSource().equals(mntmSupplierUpdate)) controller.Sales(MainFrameController.updateOfSupplier);
				else if (e.getSource().equals(mntmSupplierReportFrame)) controller.Sales(MainFrameController.supplierReport);
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
		
		mntmResgisterOfSuppliers.addActionListener(menuListeners);
		mntmApprovalOfSuppliers.addActionListener(menuListeners);
		mntmSalesRequisition.addActionListener(menuListeners);	
		mntmSalesOrder.addActionListener(menuListeners);
		mntmRegisterOfProduct.addActionListener(menuListeners);
		mntmProductUpdate.addActionListener(menuListeners);
		mntmSupplierUpdate.addActionListener(menuListeners);
		mntmSupplierReportFrame.addActionListener(menuListeners);
	}

}
