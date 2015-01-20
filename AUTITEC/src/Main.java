import javax.swing.SwingUtilities;

import product.view.RegisterProductFrame;
import userInterface.view.LoadingFrame;
import userInterface.view.MainFrame;

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
		new MainFrame().setVisible(true);
		
		//new TechnicalStandardFrame().setVisible(true);
		//new RegisterIssueFrame().setVisible(true);
		
		//new RegisterProductFrame().setVisible(true);

		
		loadingFrame.dispose();
				
	}

}
