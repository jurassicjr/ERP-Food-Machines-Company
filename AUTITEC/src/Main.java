import java.awt.EventQueue;

import rh.view.RegisterEmployeeFrame;

/**
 * Classe principal, responsável por inicializar a aplicação.
 */
public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				RegisterEmployeeFrame window = new RegisterEmployeeFrame();
				window.setVisible(true);
			}
		});
		
	}

}
