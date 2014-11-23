package sales.view;

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
		frameReq.pack();
		frameReq.setVisible(true);
		frameReq.setLocationRelativeTo(mainFrame);
		
	}
}
