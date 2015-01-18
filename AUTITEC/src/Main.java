import javax.swing.SwingUtilities;

import login.view.LoginFrame;
import rh.view.TechnicalStandardFrame;
import userInterface.view.LoadingFrame;
import userInterface.view.MainFrame;
import userInterface.view.RegisterIssueFrame;
import util.SendEmail;

/**
 * Classe principal, responsável por inicializar a aplicação.
 */
public class Main {

	public static void main(String[] args) {
			
		LoadingFrame loadingFrame = new LoadingFrame();  
	
		SwingUtilities.invokeLater(new Runnable(){  
			@Override public void run() { loadingFrame.setVisible(true); }  
		});
		
		new LoginFrame().setVisible(true);
		//new MainFrame().setVisible(true);
		
		//new TechnicalStandardFrame().setVisible(true);
		//45282567970
		//new RegisterIssueFrame().setVisible(true);
				  
		loadingFrame.dispose();
				
	}

}
