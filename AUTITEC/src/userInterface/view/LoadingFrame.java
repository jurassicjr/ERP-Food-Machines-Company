package userInterface.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import util.Icon;

public class LoadingFrame extends JFrame {

	private static final long serialVersionUID = 2004844933713613289L;
	
	public LoadingFrame() {		
		initialize();
	}
	
	private void initialize() {
				
		setLookAndFell();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		setBackground(new Color(0, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		panel.setBackground(new Color(0, 0, 0, 0));
		getContentPane().add(panel, BorderLayout.CENTER);
				
		JLabel logo = new JLabel();
		panel.add(logo, BorderLayout.CENTER);
		
		setLogo(logo);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		getContentPane().add(progressBar, BorderLayout.SOUTH);
		
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		Icon.setIcon(this);
		
	}
	
	private void setLookAndFell() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}	
	}
	
	private void setLogo(JLabel logo) {
		
		ImageIcon image = new ImageIcon(getClass().getResource("/resources/logo_splash.png"));
		ImageIcon thumbnail = null;
		
		if(image.getIconWidth() > logo.getWidth()){       
			thumbnail = new ImageIcon(image.getImage().getScaledInstance(350, -1, Image.SCALE_DEFAULT));
		}
		else thumbnail = image;
				
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setVerticalAlignment(SwingConstants.CENTER);
		logo.setIcon(thumbnail);
				
	}

}
