import java.awt.EventQueue;

import rh.view.RegisterEmployeeFrame;

/**
 * Classe principal, respons�vel por inicializar a aplica��o.
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
