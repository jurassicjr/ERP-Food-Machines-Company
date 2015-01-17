package userInterface.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import util.Icon;

public class AboutFrame extends JDialog {

	private static final long serialVersionUID = -5688364294674350348L;
	
	private JButton btnOk;
	
	public AboutFrame() {
		initialize();
		setListeners();
	}
	
	private void initialize() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setLocationRelativeTo(getParent());
		Icon.setIcon(this);
		
		JPanel contentPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel logo = new JLabel();
		setLogo(logo);
		contentPanel.add(logo, BorderLayout.CENTER);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		
		btnOk = new JButton("Ok");
		btnOk.setIcon(new ImageIcon(getClass().getResource("/resources/ok.png")));
		btnPanel.add(btnOk);
		
	}
	
	private void setListeners() {
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
	}
	
	private void setLogo(JLabel logo) {
		
		ImageIcon image = new ImageIcon(getClass().getResource("/resources/eureka.png"));
		ImageIcon thumbnail = null;
		
		if(image.getIconWidth() > logo.getWidth()){       
			thumbnail = new ImageIcon(image.getImage().getScaledInstance(420, -1, Image.SCALE_DEFAULT));
		}
		else thumbnail = image;
		
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setVerticalAlignment(SwingConstants.CENTER);
		logo.setIcon(thumbnail);
				
	}

}
