import javax.swing.SwingUtilities;

import rh.view.RegisterCBOFrame;
import rh.view.RegisterEmployeeFrame;
import login.view.LoginFrame;
import userInterface.view.LoadingFrame;
import util.UpdateSoftware;
import util.VerifySerial;

/**
 * Classe principal, responsável por inicializar a aplicação.
 */
public class Main {
	
	public static void main(String[] args) {
							
		LoadingFrame loadingFrame = new LoadingFrame();  
	
		SwingUtilities.invokeLater(new Runnable(){  
			@Override public void run() { loadingFrame.setVisible(true); }  
		});
		
//		new UpdateSoftware(loadingFrame);
//		if(!new VerifySerial().verify()) System.exit(0);
//		new LoginFrame().setVisible(true);
		
		new RegisterEmployeeFrame().setVisible(true);
		//new RegisterCBOFrame().setVisible(true);
								
		loadingFrame.dispose();
				
	}

}
