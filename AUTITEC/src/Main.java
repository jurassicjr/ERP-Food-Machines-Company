import javax.swing.SwingUtilities;

import rh.view.ListEmployesFrame;
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
		
		//new UpdateSoftware(loadingFrame);
		//if(!new VerifySerial().verify()) System.exit(0);
		
		new ListEmployesFrame().setVisible(true);
										
		loadingFrame.dispose();
				
	}

}
