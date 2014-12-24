import java.awt.EventQueue;

import login.view.LoginFrame;


/**
 * Classe principal, responsável por inicializar a aplicação.
 */
public class Main {

	public static void main(String[] args) {
										
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				//MainFrame frame = new MainFrame();
				LoginFrame frame = new LoginFrame();
				frame.setVisible(true);
			}
		});

	}

}
