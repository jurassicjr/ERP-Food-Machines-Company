import javax.swing.SwingUtilities;

import sales.view.search.SearchOfMaterialFrame;
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
		
		//new LoginFrame().setVisible(true);
		new SearchOfMaterialFrame().setVisible(true);
		loadingFrame.dispose();
				
	}

}
