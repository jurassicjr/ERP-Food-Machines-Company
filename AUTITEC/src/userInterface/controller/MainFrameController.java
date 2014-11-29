package userInterface.controller;

import java.awt.EventQueue;

import rh.view.RegisterEmployeeFrame;
import sales.controller.SalesController;
import userInterface.view.MainFrame;

/**
 * Classe controladora do frame principal do sistema
 */
public class MainFrameController {

	private MainFrame mainFrame;

	/**
	 * Cria o controlador para o frame principal da aplicação
	 * 
	 * @param mainFrame
	 *            O Frame a ser controlado
	 */
	public MainFrameController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	/**
	 * Exibe o frame para registro de funcionário
	 */
	public void registerEmployee() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				RegisterEmployeeFrame frame = new RegisterEmployeeFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});		

	}

	public void Sales(String s) {
		SalesController controller = new SalesController(mainFrame);
		if(s.equals("Homologar Fornecedores")) {
			controller.ApprovalOfSuppliers();
		}else if(s.equals("Requisição de Compra")) {
			controller.salesRequisition();
		}else if(s.equals("Cadastro de fornecedores")) {
			controller.registerOfSuppliers();
		}else if(s.equals("Pedido de Compra")) {
			controller.salesOrder();
		}
	}

}
