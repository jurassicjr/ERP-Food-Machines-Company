import javax.swing.SwingUtilities;

import product.view.search.SearchOfKitFrame;
import login.view.LoginFrame;
import rh.view.RegisterOfEPIFrame;
import sales.view.search.SearchOfClientFrame;
import sales.view.search.SearchOfMaterialFrame;
import sales.view.search.SearchOfProductFrame;
import userInterface.view.LoadingFrame;
import util.UpdateSoftware;

/**
 * Classe principal, responsável por inicializar a aplicação.
 */
public class Main {
	
	public static void main(String[] args) {
		
		
		LoadingFrame loadingFrame = new LoadingFrame();  
	
		SwingUtilities.invokeLater(new Runnable(){  
			@Override public void run() { loadingFrame.setVisible(true); }  
		});
		
		new UpdateSoftware(loadingFrame);
		//if(!new VerifySerial().verify()) System.exit(0);
		
		new LoginFrame().setVisible(true);
		loadingFrame.dispose();
		
	}

}
