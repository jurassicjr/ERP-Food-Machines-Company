package sales.view;

import userInterface.view.MainFrame;

public class SalesController {

	private MainFrame mainFrame;

	public SalesController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void ApprovalOfSuppliers() {
		ApprovalOfSuppliersFrame frame = new ApprovalOfSuppliersFrame();
		frame.setVisible(true);
		frame.pack();
		frame.setLocationRelativeTo(mainFrame);
	}
}
