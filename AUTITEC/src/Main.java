import javax.swing.SwingUtilities;

import financial.view.RegisterBillFrame;
import financial.view.RegisterDebtsToReceiveFrame;
import login.view.LoginFrame;
import userInterface.view.LoadingFrame;

/**
 * Classe principal, responsável por inicializar a aplicação.
 */
public class Main {

	public static void main(String[] args) {
		
		LoadingFrame loadingFrame = new LoadingFrame();  
	
		SwingUtilities.invokeLater(new Runnable(){  
			@Override public void run() { loadingFrame.setVisible(true); }  
		});
		
		//new LoginFrame().setVisible(true);
		//new MainFrame().setVisible(true);
		//new RegisterDebtsToReceiveFrame().setVisible(true);
		  
		loadingFrame.dispose();
		
	}

}
