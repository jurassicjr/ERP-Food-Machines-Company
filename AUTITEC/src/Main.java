import java.awt.EventQueue;

import userInterface.view.MainFrame;

/**
 * Classe principal, responsável por inicializar a aplicação.
 */
public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainFrame frame = new MainFrame();
				//RegisterEmployeeFrame frame = new RegisterEmployeeFrame();
				frame.setVisible(true);
			}
		});

	}

}
