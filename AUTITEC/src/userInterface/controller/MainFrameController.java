package userInterface.controller;

import java.awt.EventQueue;

import rh.view.RegisterEmployeeFrame;
import rh.view.TechnicalStandardFrame;
import sales.controller.SalesController;
import userInterface.view.MainFrame;

/**
 * Classe controladora do frame principal do sistema
 */
public class MainFrameController {
	
	public static final int approvalOfSupliers = 0;
	public static final int salesRequisition = 1;
	public static final int registerOfSuppliers = 2;
	public static final int salesOrder = 3;
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
	
	/**
	 * Inicializa o frame para registro de Norma Técnica
	 */
	public void technicalStandard() {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
							
				TechnicalStandardFrame frame = new TechnicalStandardFrame();
				frame.setVisible(true);
				frame.setLocationRelativeTo(mainFrame);
				
			}
		});		
		
	}

	public void Sales(int i) {
		SalesController controller = new SalesController(mainFrame);
		if(i == 0) {
			controller.ApprovalOfSuppliers();
		}else if(i == 1) {
			controller.salesRequisition();
		}else if(i == 2) {
			controller.registerOfSuppliers();
		}else if(i == 3) {
			controller.salesOrder();
		}
	}

}
