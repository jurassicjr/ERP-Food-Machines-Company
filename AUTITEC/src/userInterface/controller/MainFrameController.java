package userInterface.controller;

import java.awt.Dimension;

import rh.view.RegisterEmployeeFrame;
import sales.view.ApprovalOfSuppliersFrame;
import sales.view.SalesRequisitionFrame;
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

		RegisterEmployeeFrame frame = new RegisterEmployeeFrame();
		frame.setVisible(true);
		frame.setLocationRelativeTo(mainFrame);

	}

	public void ApprovalOfSuppliers() {
		ApprovalOfSuppliersFrame frame = new ApprovalOfSuppliersFrame();
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(mainFrame);
	}
	public void SalesRequisition() {
		SalesRequisitionFrame frameReq = new SalesRequisitionFrame();
		frameReq.setVisible(true);
		frameReq.setMinimumSize(new Dimension(750,320));
		frameReq.setLocationRelativeTo(mainFrame);
	}

}
