package sales.view;

import java.awt.Dimension;

import userInterface.view.MainFrame;

public class SalesController {

	private MainFrame mainFrame;

	public SalesController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void ApprovalOfSuppliers() {
		ApprovalOfSuppliersFrame frame = new ApprovalOfSuppliersFrame();
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(mainFrame);
	}
	public void salesRequisition() {
		SalesRequisitionFrame frameReq = new SalesRequisitionFrame();
		frameReq.setMinimumSize(new Dimension(720,450));
		frameReq.setVisible(true);
		frameReq.setLocationRelativeTo(mainFrame);
	}
	public void purchaseOrder() {
		PurchaseOrderFrame frame = new PurchaseOrderFrame();
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(mainFrame);
	}
}
