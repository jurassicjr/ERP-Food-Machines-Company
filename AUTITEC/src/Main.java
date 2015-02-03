import javax.swing.SwingUtilities;

import rh.view.RegisterUserFrame;
import userInterface.view.LoadingFrame;
import userInterface.view.MainFrame;
import userInterface.view.UpdateSoftware;

/**
 * Classe principal, responsável por inicializar a aplicação.
 */
public class Main {
	
	public static void main(String[] args) {
							
		LoadingFrame loadingFrame = new LoadingFrame();  
	
		SwingUtilities.invokeLater(new Runnable(){  
			@Override public void run() { loadingFrame.setVisible(true); }  
		});
		
		new RegisterUserFrame().setVisible(true);
		
//		new UpdateSoftware(loadingFrame);
		//new MainFrame().setVisible(true);
				
		loadingFrame.dispose();
				
	}

}
