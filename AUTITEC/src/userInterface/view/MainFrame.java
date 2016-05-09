package userInterface.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
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
	private JMenu mnSalesRegister;
	private JMenu mnSalesUpdates;
	private JMenu mnReports;

	private JMenuItem mntmProductsReport;

	private JMenuItem mntmClientRegister;

	private JMenuItem mntmAddMaterialToInvetory;

	private JMenuItem mntmKitRegister;

	private JMenuItem mntmUpdateProduct;

	private JMenu mnInventory;

	private ArrayList<JMenuItem> menuItens;

	private JMenu mnBuy;

	private JMenu mnBuyReports;

	private JMenuItem mntmClientReport;

	private JMenu mnSalesSearch;

	private JMenuItem mntmMaterialSearch;

	private JMenuItem mntmProductSearch;
    
	private JMenuItem mntmClientSearch;
	
	private JMenu mnRegisterBuy;

	private JMenu mnUpdateBuy;

	private JMenuItem mntmEpiUpdate;

	private JMenuItem mntmInsertoOfEPI;

	private JMenuItem mntmRemoveOfEPI;

	private JMenuItem mntmRegisterOfTraning;

	private JMenuItem mntmUpdateOfTraining;

	private JMenu mnSubRegisterRH;

	private JMenu mnSubUpdateRH;

	private JMenuItem mntmUpdateOfEmployee;

	private JMenuItem mntmUpdateOfKit;

	private JMenuItem mntmUpdateOfClient;

	private JMenuItem mntmSearchOfKit;

	private JMenuItem mntmInternalSatisfactionResearch;

	private JMenu mnMaintenance;

	private JMenu mnMaintenanceRegister;

	private JMenuItem mntmVehicleRegister;

	private JMenu mnMaitenanceUpdate;

	private JMenu mnMaintenanceSearch;

	private JMenuItem mntmVehicleUpdate;

	private JMenuItem mntmVehicleSearch;

	private JMenuItem mntmExternalSatisfactionResearch;

	private JMenuItem mntmRegisterOfFunctionDescription;

	private JMenuItem mntmRouteRegister;

	private JMenuItem mntmUpdateRoute;

	private JMenuItem mntmAssessmentOfCompetence;

	private JMenuItem mntmVehicleDebtRegisterFrame;

	private JMenuItem mntmRNCRegister;

	private JMenuItem mntmClientProperties;

	private JMenuItem mntmMonitoringImplementation;

	private JMenu mnSubSearchRh;

	private JMenuItem mntmSearchOfNC;

	private JMenuItem mntmExitOfClientProperties;

	private JMenuItem mntmToolsRegister;

	private JMenuItem mntmToolsUpdate;

	private JMenu mnRHReport;

	private JMenuItem mntmExternalSatisfactionReport;

	private JMenuItem mntmInternalSatisfactionReport;

	private JMenuItem mntmToolBoxRegister;

	private JMenuItem mntmProcedures;

	private JMenuItem mntmManualQuality;

	private JMenuItem mntmToolBoxUpdate;

	private JMenuItem mntmRegisterBankAccount;

	private JMenuItem mntmServiceRegister;

	private JMenuItem mntmPTCRegister;

	private JMenuItem mntmPTCUpdate;

	private JMenuItem mntmPTCApprovation;

	private JMenu mnBuySearch;

	private JMenuItem mntmSalesRequisitionSearch;

	private JMenu mnRhDocuments;

	private JMenu mnRhEpi;

	private JMenu mnRhResearch;
	private JLabel logo;

	
	/**
	 * Cria a janela principal da aplicação
	 */	
	public MainFrame() {
				
		controller = new MainFrameController(this);
		
		menuItens = new ArrayList<>();
		
		initialize();
		setListeners();
		createMenu();
		
		//controller.setFinancialNotifications(notificationPanel);
		
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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Icon.setIcon(this);
		
		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(5, 5, 5, 5));
		setJMenuBar(menuBar);
		
		
		//Recursos Humanos (RH)
		mnRh = new JMenu("RH");
		mnRh.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/rh.png")));
		menuBar.add(mnRh);
		mnRh.setVisible(false);
		
		mnSubRegisterRH = new JMenu("Registros");
		mnRh.add(mnSubRegisterRH);
		
		mntmRegisterEmployee = new JMenuItem("Registrar Funcionário");
		mnSubRegisterRH.add(mntmRegisterEmployee);
		mntmRegisterEmployee.setVisible(false);
		mntmRegisterEmployee.setName("REG_EMP");
		
		mntmRegisterUser = new JMenuItem("Registrar Usuário");
		mnSubRegisterRH.add(mntmRegisterUser);
		mntmRegisterUser.setVisible(false);
		mntmRegisterUser.setName("REG_USER");
				
		mntmRegisterOfTraning = new JMenuItem("Registro de Treinamento");
		mnSubRegisterRH.add(mntmRegisterOfTraning);
		mntmRegisterOfTraning.setVisible(false);
		mntmRegisterOfTraning.setName("REG_TRA");
				
		mntmRegisterOfFunctionDescription = new JMenuItem("Registro de Descrição de Cargo");
		mnSubRegisterRH.add(mntmRegisterOfFunctionDescription);
		mntmRegisterOfFunctionDescription.setVisible(false);
		mntmRegisterOfFunctionDescription.setName("REG_FUD");
				
				
		mntmAssessmentOfCompetence = new JMenuItem("Registra Avaliação de Competência");
		mnSubRegisterRH.add(mntmAssessmentOfCompetence);
		mntmAssessmentOfCompetence.setVisible(false);
		mntmAssessmentOfCompetence.setName("REG_ASS");
				
		mntmRNCRegister = new JMenuItem("Registro de Não Conformidade");
		mnSubRegisterRH.add(mntmRNCRegister);
		mntmRNCRegister.setVisible(false);
		mntmRNCRegister.setName("REG_RNC");
		
		mnSubUpdateRH = new JMenu("Atualizações e Remoções");
		mnRh.add(mnSubUpdateRH);
		
		mnSubSearchRh = new JMenu("Consultas");
		mnRh.add(mnSubSearchRh);
		mnSubSearchRh.setVisible(false);
		
		mnRHReport = new JMenu("Relatórios");
		mnRh.add(mnRHReport);
		mnRHReport.setVisible(false);

		mnRhDocuments = new JMenu("Importar Documentos");
		mnRh.add(mnRhDocuments);
		mnRhDocuments.setVisible(false);
		
		mnRhEpi = new JMenu("Epis");
		mnRh.add(mnRhEpi);
		mnRhEpi.setVisible(false);
		
		mnRhResearch = new JMenu("Pesquisas");
		mnRh.add(mnRhResearch);
		mnRhResearch.setVisible(false);
		
		mntmTechnicalStandard = new JMenuItem("Normas Técnicas");
		mnRhDocuments.add(mntmTechnicalStandard);
		mntmTechnicalStandard.setVisible(false);
		mntmTechnicalStandard.setName("TEC_STD");
		
		mntmProcedures = new JMenuItem("Procedimentos");
		mnRhDocuments.add(mntmProcedures);
		mntmProcedures.setVisible(false);
		mntmProcedures.setName("PROC");
		
		mntmManualQuality = new JMenuItem("Manual de Qualidade");
		mnRhDocuments.add(mntmManualQuality);
		mntmManualQuality.setVisible(false);
		mntmManualQuality.setName("MAN_QUAL");
		
		mntmUpdateUser = new JMenuItem("Atualizar Permissões");
		mnSubUpdateRH.add(mntmUpdateUser);
		mntmUpdateUser.setVisible(false);
		mntmUpdateUser.setName("UPD_PERM");
		
		mntmEmployeeReport = new JMenuItem("Relatório de Funcionários");
		mnRHReport.add(mntmEmployeeReport);
		mntmEmployeeReport.setVisible(false);
		mntmEmployeeReport.setName("EMP_REP");
		
		mntmEpiUpdate = new JMenuItem("Atualização/Remoção de EPI's");
		mnSubUpdateRH.add(mntmEpiUpdate);
		mntmEpiUpdate.setVisible(false);
		mntmEpiUpdate.setName("EPI_UPD");
		
		mntmInsertoOfEPI = new JMenuItem("Inserção de EPI's");
		mnRhEpi.add(mntmInsertoOfEPI);
		mntmInsertoOfEPI.setVisible(false);
		mntmInsertoOfEPI.setName("IST_EPI");
		
		mntmRemoveOfEPI = new JMenuItem("Retirada de EPI's");
		mnRhEpi.add(mntmRemoveOfEPI);
		mntmRemoveOfEPI.setVisible(false);
		mntmRemoveOfEPI.setName("RMV_EPI");
		
		mntmUpdateOfTraining = new JMenuItem("Atualização/Remoção de Treinamento");
		mnSubUpdateRH.add(mntmUpdateOfTraining);
		mntmUpdateOfTraining.setVisible(false);
		mntmUpdateOfTraining.setName("UPD_TRA");
		
		mntmUpdateOfEmployee = new JMenuItem("Atualização/Remoção de Funcionário");
		mnSubUpdateRH.add(mntmUpdateOfEmployee);
		mntmUpdateOfEmployee.setVisible(false);
		mntmUpdateOfEmployee.setName("UPD_EMP");
		
		mntmInternalSatisfactionResearch = new JMenuItem("Pesquisa de Satisfação Interna");
		mnRhResearch.add(mntmInternalSatisfactionResearch);
		mntmInternalSatisfactionResearch.setVisible(false);
		mntmInternalSatisfactionResearch.setName("INT_RES");
		
		mntmExternalSatisfactionResearch = new JMenuItem("Pesquisa de Satisfação Externa");
		mnRhResearch.add(mntmExternalSatisfactionResearch);
		mntmExternalSatisfactionResearch.setVisible(false);
		mntmExternalSatisfactionResearch.setName("EXT_RES");
		
		mntmMonitoringImplementation = new JMenuItem("Acompanhamento de Não Conformidade");
		mnSubSearchRh.add(mntmMonitoringImplementation);
		mntmMonitoringImplementation.setVisible(false);
		mntmMonitoringImplementation.setName("MON_IMP");
		
		mntmSearchOfNC = new JMenuItem("Pesquisa de Não Conformidade");
		mnSubSearchRh.add(mntmSearchOfNC);
		mntmSearchOfNC.setVisible(false);
		mntmSearchOfNC.setName("SEA_RNC");

		mntmExternalSatisfactionReport = new JMenuItem("Relatório de Pesquisa de Satisfação Externa");
		mnRHReport.add(mntmExternalSatisfactionReport);
		mntmExternalSatisfactionReport.setVisible(false);
		mntmExternalSatisfactionReport.setName("REP_EXT_SEA");
		
		mntmInternalSatisfactionReport = new JMenuItem("Relatório de Pesquisa de Satisfação Interna");
		mnRHReport.add(mntmInternalSatisfactionReport);
		mntmInternalSatisfactionReport.setVisible(false);
		mntmInternalSatisfactionReport.setName("REP_INT_SEA");
		
		//Financeiro(Gestão)
		mnFinancial = new JMenu("Financeiro");
		mnFinancial.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/finance.png")));
		menuBar.add(mnFinancial);
		mnFinancial.setVisible(false);

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
		
		mntmRegisterBankAccount = new JMenuItem("Registro de conta bancária");
		mnFinancial.add(mntmRegisterBankAccount);
		mntmRegisterBankAccount.setVisible(false);
		mntmRegisterBankAccount.setName("REG_BAK_ACC");

		//Vendas
		mnSales = new JMenu("Vendas");
		mnSales.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/sales.png")));
		menuBar.add(mnSales);
		mnSales.setVisible(false);
		
		mnSalesRegister = new JMenu("Registros");
		mnSales.add(mnSalesRegister);
		mnSalesRegister.setVisible(false);

		mnSalesUpdates = new JMenu("Atualizar/Remover Registros");
		mnSales.add(mnSalesUpdates);
		mnSalesUpdates.setVisible(false);

		mnSalesSearch = new JMenu("Consultas");
		mnSales.add(mnSalesSearch);
		mnSales.setVisible(false);

		mnReports = new JMenu("Relátorios");
		mnSales.add(mnReports);
		mnReports.setVisible(false);
		
		mnInventory = new JMenu("Estoque");
		mnSales.add(mnInventory);
		mnInventory.setVisible(false);
		
		mntmClientRegister = new JMenuItem("Registro de Cliente");
		mnSalesRegister.add(mntmClientRegister);
		mntmClientRegister.setVisible(false);
		mntmClientRegister.setName("REG_CLI");
		
		mntmRegisterOfMaterial = new JMenuItem("Registro de Material");
		mnSalesRegister.add(mntmRegisterOfMaterial);
		mntmRegisterOfMaterial.setVisible(false);
		mntmRegisterOfMaterial.setName("REG_MAT");		
		
		mntmRegisterProduct = new JMenuItem("Registro de Produto");
		mnSalesRegister.add(mntmRegisterProduct);
		mntmRegisterProduct.setVisible(false);
		mntmRegisterProduct.setName("REG_PROD");		
		
		mntmKitRegister = new JMenuItem("Registro de KIT");
		mnSalesRegister.add(mntmKitRegister);
		mntmKitRegister.setVisible(false);
		mntmKitRegister.setName("REG_KIT");
		
		mntmProductUpdate = new JMenuItem("Atualizar/Remover Registro de Material");
		mnSalesUpdates.add(mntmProductUpdate);
		mntmProductUpdate.setVisible(false);
		mntmProductUpdate.setName("UPD_MAT");
		
		mntmUpdateProduct = new JMenuItem("Atualizar/Remover Registro de Produtos");
		mnSalesUpdates.add(mntmUpdateProduct);
		mntmUpdateProduct.setVisible(false);
		mntmUpdateProduct.setName("UPD_PROD");

		mntmClientReport = new JMenuItem("Gerar Relatório de Cliente");
		mnReports.add(mntmClientReport);
		mntmClientReport.setVisible(false);
		mntmClientReport.setName("CLI_REP");
		
		mntmMaterialSearch = new JMenuItem("Consulta de Materiais");
		mnSalesSearch.add(mntmMaterialSearch);
		mntmMaterialSearch.setName("SEA_MAT");
		mntmMaterialSearch.setVisible(false);
		
		mntmProductSearch = new JMenuItem("Consulta de Produtos");
		mnSalesSearch.add(mntmProductSearch);
		mntmProductSearch.setName("SEA_PROD");
		mntmProductSearch.setVisible(false);
		
		mntmClientSearch = new JMenuItem("Consulta de Clientes");
		mnSalesSearch.add(mntmClientSearch);
		mntmClientSearch.setName("SEA_CLI");
		mntmClientSearch.setVisible(false);
		
		mntmUpdateOfKit = new JMenuItem("Atualizar/Remover Registro de Kit");
		mnSalesUpdates.add(mntmUpdateOfKit);
		mntmUpdateOfKit.setName("UPD_KIT");
		mntmUpdateOfKit.setVisible(false);
		
		mntmUpdateOfClient = new JMenuItem("Atualizar/Remover Registro de Cliente");
		mnSalesUpdates.add(mntmUpdateOfClient);
		mntmUpdateOfClient.setName("UPD_CLI");
		mntmUpdateOfClient.setVisible(false);
		
		mntmSearchOfKit = new JMenuItem("Consulta de Kits");
		mnSalesSearch.add(mntmSearchOfKit);
		mntmSearchOfKit.setName("SEA_KIT");
		mntmSearchOfKit.setVisible(false);
		
		mntmClientProperties = new JMenuItem("Registro de Propriedades do Cliente");
		mnSalesRegister.add(mntmClientProperties);
		mntmClientProperties.setVisible(false);
		mntmClientProperties.setName("REG_CLI_PROP");
		
		mntmExitOfClientProperties = new JMenuItem("Registro de Saída de propriedades do Cliente");
		mnSalesRegister.add(mntmExitOfClientProperties);
		mntmExitOfClientProperties.setVisible(false);
		mntmExitOfClientProperties.setName("EXI_CLI_PROP");
		
		mntmServiceRegister = new JMenuItem("Registro de Serviços");
		mnSalesRegister.add(mntmServiceRegister);
		mntmServiceRegister.setVisible(false);
		mntmServiceRegister.setName("SER_REG");
		
		mntmPTCRegister = new JMenuItem("Registro de Proposta Técnica Comercial");
		mnSalesRegister.add(mntmPTCRegister);
		mntmPTCRegister.setVisible(false);
		mntmPTCRegister.setName("PTC_REG");
		
		mntmPTCUpdate = new JMenuItem("Atualização de Proposta Técnica Comercial");
		mnSalesUpdates.add(mntmPTCUpdate);
		mntmPTCUpdate.setVisible(false);
		mntmPTCUpdate.setName("UPD_PTC");
		
		mntmPTCApprovation = new JMenuItem("Aprovação de P.T.C");
		mnSales.add(mntmPTCApprovation);
		mntmPTCApprovation.setVisible(false);
		mntmPTCApprovation.setName("PTC_APP");
		

		//Compras
		
		mnBuy = new JMenu("Compras");
		mnBuy.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/buy_icon.png")));
		menuBar.add(mnBuy);
		mnBuy.setVisible(false);
		
		
		mnRegisterBuy = new JMenu("Registros");
		mnBuy.add(mnRegisterBuy);
		mnRegisterBuy.setVisible(false);
		
		mnUpdateBuy = new JMenu("Autalizar/Remover Registros");
		mnBuy.add(mnUpdateBuy);
		mnUpdateBuy.setVisible(false);

		mnBuySearch = new JMenu("Buscas");
		mnBuy.add(mnBuySearch);
		mnBuySearch.setVisible(false);

		mnBuyReports = new JMenu("Relatórios");
		mnBuy.add(mnBuyReports);
		mnBuy.setVisible(false);
		

		mntmResgisterOfSuppliers = new JMenuItem("Registrar Fornecedores");
		mnRegisterBuy.add(mntmResgisterOfSuppliers);
		mntmResgisterOfSuppliers.setVisible(false);
		mntmResgisterOfSuppliers.setName("REG_SUP");
		
		mntmApprovalOfSuppliers = new JMenuItem("Homologar Fornecedores");
		mnBuy.add(mntmApprovalOfSuppliers);
		mntmApprovalOfSuppliers.setVisible(false);
		mntmApprovalOfSuppliers.setName("HOM_SUP");
		
		mntmSalesRequisition = new JMenuItem("Registrar Requisição de Compra");
		mnRegisterBuy.add(mntmSalesRequisition);
		mntmSalesRequisition.setVisible(false);
		mntmSalesRequisition.setName("SALE_REQ");
		
		mntmSalesOrder = new JMenuItem("Pedido de Compra");
		mnBuy.add(mntmSalesOrder);
		mntmSalesOrder.setVisible(false);
		mntmSalesOrder.setName("SALE_DEM");

		mntmSupplierUpdate = new JMenuItem("Atualizar/Remover Registro de Fornecedores");
		mnUpdateBuy.add(mntmSupplierUpdate);
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
		
		mntmSalesRequisitionSearch = new JMenuItem("Acompanhamento de Requisição de Compra");
		mnBuySearch.add(mntmSalesRequisitionSearch);
		mntmSalesRequisitionSearch.setVisible(false);
		mntmSalesRequisitionSearch.setName("SAL_REQ_SEA");

		//Produção
		
		mnProduction = new JMenu("Produção");
		mnProduction.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/production.png")));
		menuBar.add(mnProduction);
		mnProduction.setVisible(false);
		
		mntmProductionStage = new JMenuItem("Estágios de Produção");
		mnProduction.add(mntmProductionStage);
		mntmProductionStage.setVisible(false);
		mntmProductionStage.setName("PROD_STG");
		
		//Manutenção
		
		mnMaintenance = new JMenu("Manutenção");
		mnMaintenance.setIcon(new ImageIcon(MainFrame.class.getResource("/resources/icon_maintenance.png")));
		menuBar.add(mnMaintenance);
		mnMaintenance.setVisible(false);
		
		mnMaintenanceRegister = new JMenu("Registros");
		mnMaintenance.add(mnMaintenanceRegister);
		mnMaintenanceRegister.setVisible(false);
		
		mnMaitenanceUpdate = new JMenu("Atualização/Remoção de Registro");
		mnMaintenance.add(mnMaitenanceUpdate);
		mnMaitenanceUpdate.setVisible(false);
		
		mnMaintenanceSearch = new JMenu("Consulta");
		mnMaintenance.add(mnMaintenanceSearch);
		mnMaintenanceSearch.setVisible(false);
		
		mntmToolsRegister = new JMenuItem("Registro de Ferramentas");
		mnMaintenanceRegister.add(mntmToolsRegister);
		mntmToolsRegister.setVisible(false);
		mntmToolsRegister.setName("TOO_REG");
		
		mntmToolsUpdate = new JMenuItem("Atualização/Remoção de Registro de Ferramentas");
		mnMaitenanceUpdate.add(mntmToolsUpdate);
		mntmToolsUpdate.setVisible(true);
		mntmToolsUpdate.setName("UPD_TOO");
		
		mntmToolBoxRegister = new JMenuItem("Registro de Caixa de Ferramenta");
		mnMaintenanceRegister.add(mntmToolBoxRegister);
		mntmToolBoxRegister.setVisible(false);
		mntmToolBoxRegister.setName("REG_BOX_TOO");
		
		mntmToolBoxUpdate = new JMenuItem("Atualização/Remoção de Registro de Caixa de Ferramenta");
		mnMaitenanceUpdate.add(mntmToolBoxUpdate);
		mntmToolBoxUpdate.setVisible(false);
		mntmToolBoxUpdate.setName("UPD_TOO_BOX");
		
		mntmVehicleRegister = new JMenuItem("Registro de Veiculos");
		mnMaintenanceRegister.add(mntmVehicleRegister);
		mntmVehicleRegister.setVisible(false);
		mntmVehicleRegister.setName("REG_VEH");
		
		mntmVehicleUpdate = new JMenuItem("Atualizar/Remover Registro de Veículos");
		mnMaitenanceUpdate.add(mntmVehicleUpdate);
		mntmVehicleUpdate.setVisible(false);
		mntmVehicleUpdate.setName("UPD_VEH");
		
		mntmVehicleSearch = new JMenuItem("Consulta de Veículos");
		mnMaintenanceSearch.add(mntmVehicleSearch);
		mntmVehicleSearch.setVisible(false);
		mntmVehicleSearch.setName("SEA_VEH");
		
		mntmRouteRegister = new JMenuItem("Registro de Trajeto");
		mnMaintenanceRegister.add(mntmRouteRegister);
		mntmRouteRegister.setVisible(false);
		mntmRouteRegister.setName("ROU_REG");
		
		mntmUpdateRoute = new JMenuItem("Atualização/Finalização de Trajeto");
		mnMaitenanceUpdate.add(mntmUpdateRoute);
		mntmUpdateRoute.setVisible(false);
		mntmUpdateRoute.setName("UPD_ROU");
	
		mntmVehicleDebtRegisterFrame = new JMenuItem("Registro de Contas com veículos");
		mnMaintenanceRegister.add(mntmVehicleDebtRegisterFrame);
		mntmVehicleDebtRegisterFrame.setVisible(false);
		mntmVehicleDebtRegisterFrame.setName("DEB_VEH");
		
		
		
		//Sobre
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
		notificationPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));

		scrollPane.setViewportView(notificationPanel);
		
		logo = new JLabel("");
		setLogo(logo);
		GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(logo, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addGap(11)
							.addComponent(logo, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
					.addContainerGap())
		);
		getContentPane().setLayout(layout);
	}
	
	private void createMenu() {
				
		menuItens.add(mntmRegisterEmployee);
		menuItens.add(mntmTechnicalStandard);
		menuItens.add(mntmRegisterUser);
		menuItens.add(mntmEmployeeReport);
		menuItens.add(mntmUpdateUser);
		menuItens.add(mntmEpiUpdate);
		menuItens.add(mntmInsertoOfEPI);
		menuItens.add(mntmRemoveOfEPI);
		menuItens.add(mntmRegisterOfTraning);
		menuItens.add(mntmUpdateOfTraining);
		menuItens.add(mntmUpdateOfEmployee);
		menuItens.add(mntmInternalSatisfactionResearch);
		menuItens.add(mntmExternalSatisfactionResearch);
		menuItens.add(mntmRegisterOfFunctionDescription);
		menuItens.add(mntmAssessmentOfCompetence);
		menuItens.add(mntmRNCRegister);
		menuItens.add(mntmMonitoringImplementation);
		menuItens.add(mntmSearchOfNC);
		menuItens.add(mntmExternalSatisfactionReport);
		menuItens.add(mntmInternalSatisfactionReport);
		menuItens.add(mntmProcedures);
		menuItens.add(mntmManualQuality);
		
		menuItens.add(mntmRegisterBill);
		menuItens.add(mntmPayBill);
		menuItens.add(mntmListBills);
		menuItens.add(mntmRegisterDebtsToReceive);
		menuItens.add(mntmReceiveDebt);
		menuItens.add(mntmListDebts);
		menuItens.add(mntmGenerateReport);
		menuItens.add(mntmRegisterBankAccount);
		
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
		menuItens.add(mntmSalesRequisitionSearch);
		menuItens.add(mntmSalesOrder);
		menuItens.add(mntmClientReport);
		menuItens.add(mntmMaterialSearch);
		menuItens.add(mntmProductSearch);
		menuItens.add(mntmClientSearch);
		menuItens.add(mntmUpdateOfKit);
		menuItens.add(mntmProductionStage);
		menuItens.add(mntmUpdateOfClient);
		menuItens.add(mntmSearchOfKit);
		menuItens.add(mntmClientProperties);
		menuItens.add(mntmExitOfClientProperties);
		menuItens.add(mntmServiceRegister);
		menuItens.add(mntmPTCRegister);
		menuItens.add(mntmPTCUpdate);
		menuItens.add(mntmPTCApprovation);
		
		menuItens.add(mntmVehicleRegister);
		menuItens.add(mntmVehicleUpdate);
		menuItens.add(mntmVehicleSearch);
		menuItens.add(mntmRouteRegister);
		menuItens.add(mntmUpdateRoute);
		menuItens.add(mntmVehicleDebtRegisterFrame);
		menuItens.add(mntmToolsRegister);
		menuItens.add(mntmToolBoxRegister);
		menuItens.add(mntmToolBoxUpdate);
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
				else if(e.getSource().equals(mntmEpiUpdate))controller.epiUpdate();
				else if(e.getSource().equals(mntmInsertoOfEPI))controller.insertOfEPI();
				else if(e.getSource().equals(mntmRemoveOfEPI))controller.removeOfEPI();
				else if(e.getSource().equals(mntmRegisterOfTraning))controller.registerOfTraining();
				else if(e.getSource().equals(mntmUpdateOfTraining))controller.updateOfTraining();
				else if(e.getSource().equals(mntmUpdateOfEmployee))controller.updateOfEmployee();
				else if(e.getSource().equals(mntmInternalSatisfactionResearch))controller.internalSatisfactionResearch();
				else if(e.getSource().equals(mntmExternalSatisfactionResearch))controller.externalSatisfactionResearch();
				else if(e.getSource().equals(mntmRegisterOfFunctionDescription))controller.registerFunctionDescription();
				else if(e.getSource().equals(mntmAssessmentOfCompetence))controller.assessmentOfCompetence();
				else if(e.getSource().equals(mntmMonitoringImplementation))controller.monitoringImplementation();
				else if(e.getSource().equals(mntmSearchOfNC))controller.searchOfNC();
				else if(e.getSource().equals(mntmExitOfClientProperties))controller.exitOfClientProperties();
				else if(e.getSource().equals(mntmExternalSatisfactionReport))controller.externalSatisfactionReport();
				else if(e.getSource().equals(mntmInternalSatisfactionReport))controller.internalSatisfactionReport();
				else if(e.getSource().equals(mntmProcedures))controller.procedures();
				else if(e.getSource().equals(mntmManualQuality))controller.manualQuality();
				
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
				else if (e.getSource().equals(mntmUpdateOfKit))controller.Sales(MainFrameController.updateOfKit);
				else if (e.getSource().equals(mntmClientReport))controller.Sales(MainFrameController.clientReport);
				else if (e.getSource().equals(mntmMaterialSearch))controller.Sales(MainFrameController.materialSearch);
				else if (e.getSource().equals(mntmProductSearch))controller.Sales(MainFrameController.productSearch);
				else if (e.getSource().equals(mntmClientSearch))controller.Sales(MainFrameController.clientSearch);
				else if (e.getSource().equals(mntmUpdateOfClient))controller.Sales(MainFrameController.clientUpdate);
				else if (e.getSource().equals(mntmSearchOfKit))controller.Sales(MainFrameController.kitSearch);
				else if (e.getSource().equals(mntmClientProperties))controller.Sales(MainFrameController.clientPropertiesRegister);
				else if (e.getSource().equals(mntmRegisterBankAccount))controller.registerBankAccount();
				else if (e.getSource().equals(mntmServiceRegister))controller.registerService();
				else if (e.getSource().equals(mntmPTCRegister))controller.registerPTC();
				else if (e.getSource().equals(mntmPTCUpdate))controller.updatePTC();
				else if (e.getSource().equals(mntmPTCApprovation))controller.pTCApprovation();
				else if (e.getSource().equals(mntmSalesRequisitionSearch))controller.salesRequisitionSearch();
				
				else if(e.getSource().equals(mntmVehicleRegister))controller.vehicleRegister();
				else if(e.getSource().equals(mntmVehicleUpdate))controller.vehicleUpdate();
				else if(e.getSource().equals(mntmVehicleSearch))controller.vehicleSearch();
				else if(e.getSource().equals(mntmRouteRegister))controller.routeRegister();
				else if(e.getSource().equals(mntmUpdateRoute))controller.updateRoute();
				else if(e.getSource().equals(mntmVehicleDebtRegisterFrame))controller.vechileDebtRegister();
				else if(e.getSource().equals(mntmRNCRegister))controller.rnc();
				else if(e.getSource().equals(mntmToolsRegister))controller.toolsRegister();
				else if(e.getSource().equals(mntmToolsUpdate))controller.toolsUpdate();
				else if(e.getSource().equals(mntmToolBoxRegister))controller.toolsBoxRegister();
				else if(e.getSource().equals(mntmToolBoxUpdate))controller.toolBoxUpdate();
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
		mntmEpiUpdate.addActionListener(menuListeners);
		mntmInsertoOfEPI.addActionListener(menuListeners);
		mntmRemoveOfEPI.addActionListener(menuListeners);
		mntmRegisterOfTraning.addActionListener(menuListeners);
		mntmUpdateOfTraining.addActionListener(menuListeners);
		mntmInternalSatisfactionResearch.addActionListener(menuListeners);
		mntmExternalSatisfactionResearch.addActionListener(menuListeners);
		mntmRegisterOfFunctionDescription.addActionListener(menuListeners);
		mntmAssessmentOfCompetence.addActionListener(menuListeners);
		mntmMonitoringImplementation.addActionListener(menuListeners);
		mntmSearchOfNC.addActionListener(menuListeners);
		mntmExternalSatisfactionReport.addActionListener(menuListeners);
		mntmInternalSatisfactionReport.addActionListener(menuListeners);
		mntmProcedures.addActionListener(menuListeners);
		mntmManualQuality.addActionListener(menuListeners);
		
		mntmSalesRequisitionSearch.addActionListener(menuListeners);
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
		mntmUpdateOfKit.addActionListener(menuListeners);
		mntmClientReport.addActionListener(menuListeners);
		mntmProductSearch.addActionListener(menuListeners);
		mntmMaterialSearch.addActionListener(menuListeners);
		mntmClientSearch.addActionListener(menuListeners);
		mntmUpdateOfClient.addActionListener(menuListeners);
		mntmSearchOfKit.addActionListener(menuListeners);
		mntmClientProperties.addActionListener(menuListeners);
		mntmExitOfClientProperties.addActionListener(menuListeners);
		mntmRegisterBankAccount.addActionListener(menuListeners);
		mntmServiceRegister.addActionListener(menuListeners);
		mntmPTCRegister.addActionListener(menuListeners);
		mntmPTCUpdate.addActionListener(menuListeners);
		mntmPTCApprovation.addActionListener(menuListeners);
		
		mntmVehicleRegister.addActionListener(menuListeners);
		mntmVehicleUpdate.addActionListener(menuListeners);
		mntmVehicleSearch.addActionListener(menuListeners);
		mntmRouteRegister.addActionListener(menuListeners);
		mntmUpdateRoute.addActionListener(menuListeners);
		mntmVehicleDebtRegisterFrame.addActionListener(menuListeners);
		mntmRNCRegister.addActionListener(menuListeners);
		mntmToolsRegister.addActionListener(menuListeners);
		mntmToolsUpdate.addActionListener(menuListeners);
		mntmToolBoxRegister.addActionListener(menuListeners);
		mntmToolBoxUpdate.addActionListener(menuListeners);
		
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

	private void setLogo(JLabel logo) {
		
		ImageIcon image = new ImageIcon(getClass().getResource("/resources/logo_splash.png"));
		ImageIcon thumbnail = null;
		
		if(image.getIconWidth() > logo.getWidth()){       
			thumbnail = new ImageIcon(image.getImage().getScaledInstance(750, -1, Image.SCALE_DEFAULT));
		}
		else thumbnail = image;
			
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setVerticalAlignment(SwingConstants.CENTER);
		logo.setIcon(thumbnail);
				
	}
}
