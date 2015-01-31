package userInterface.view;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import userInterface.controller.MainFrameController;


public class SplashFrame extends JFrame{

	private MainFrameController controller;
	private JFrame frame = this;
	public SplashFrame() {
	    initialize();
    }
	private void initialize() {
		setTitle("AUTITEC");
		getContentPane().setLayout(new BorderLayout(0,0));
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		initializePrincipal();
	}
	private void initializePrincipal() {
		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		
	}
	private void setListeners() {
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
			    controller.closeFrame(frame);
			}
		});
	}
}
