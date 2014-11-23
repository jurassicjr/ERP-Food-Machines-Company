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
	private JMenuItem mntmApprovalOfSuppliers;
	private JMenuItem mntmSalesRequisition;
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
		
		
		mntmRegisterEmployee = new JMenuItem("Registrar Funcionário");
		mnRh.add(mntmRegisterEmployee);
		mntmApprovalOfSuppliers = new JMenuItem("Homologar Fornecedores");
		mnSales.add(mntmApprovalOfSuppliers);
		mntmSalesRequisition = new JMenuItem("Requisição de Compra");
		mnSales.add(mntmSalesRequisition);
	}

	/**
	 * Define os listeners dos menus e botões da interface
	 */
	private void setListeners() {
				
		ActionListener menuListeners = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(mntmRegisterEmployee)) controller.registerEmployee();			
				else if(e.getSource().equals(mntmApprovalOfSuppliers)) controller.ApprovalOfSuppliers();	
				else if (e.getSource().equals(mntmSalesRequisition)) controller.SalesRequisition();
			}
		};
		
		mntmRegisterEmployee.addActionListener(menuListeners);
		mntmApprovalOfSuppliers.addActionListener(menuListeners);
		mntmSalesRequisition.addActionListener(menuListeners);
		
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
