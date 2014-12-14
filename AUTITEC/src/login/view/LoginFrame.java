package login.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import login.controller.LoginFrameController;
import util.ShowMessage;

/**
 * Frame para o login de usuários no sistema
 */
public class LoginFrame extends JFrame {

	private static final long serialVersionUID = -6894353761698590143L;
	
	private JPanel contentPane;
	
	private JFormattedTextField txCpf;
	private JPasswordField txPassword;
	
	private JButton btnExit;
	private JButton btnLogin;
	
	private LoginFrameController controller;

	/**
	 * Cria o frame de login no sistema
	 */
	public LoginFrame() {
		
		controller = new LoginFrameController(this);
		
		setLookAndFell();
		initialize();
		setListeners();
	}
	
	/**
	 * Define os eventos da interface gráfica do frame
	 */
	private void setListeners() {
		
		KeyListener keyListener = new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) controller.login(txCpf.getText(), String.valueOf(txPassword.getPassword()));
			}
			
		};
		
		txCpf.addKeyListener(keyListener);
		txPassword.addKeyListener(keyListener);
		
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(btnExit)) exit();
				else if(e.getSource().equals(btnLogin)) controller.login(txCpf.getText(), String.valueOf(txPassword.getPassword()));
				
			}
		};
		
		btnExit.addActionListener(btnListener);
		btnLogin.addActionListener(btnListener);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
			
		});
		
	}
	
	/**
	 * Inicializa os compoentes gráficos do frame
	 */
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 185);
		setLocationRelativeTo(null);
		setTitle("Login");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblCpf = new JLabel("CPF");
		try { txCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##")); }
		catch (ParseException e) { e.printStackTrace(); }
		
		JLabel lblPassword = new JLabel("Senha");
		txPassword = new JPasswordField();
		
		GroupLayout layout = new GroupLayout(panel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblCpf)
							.addGap(18)
							.addComponent(txCpf, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblPassword)
							.addGap(18)
							.addComponent(txPassword, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpf)
						.addComponent(txCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(txPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(155, Short.MAX_VALUE))
		);
		panel.setLayout(layout);
		
		JPanel btnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(btnPanel, BorderLayout.SOUTH);
		
		btnExit = new JButton("Sair");
		btnExit.setIcon(new ImageIcon(getClass().getResource("/resources/cancel.png")));
		btnPanel.add(btnExit);
		
		btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(getClass().getResource("/resources/ok.png")));
		btnPanel.add(btnLogin);
		
	}
	
	/**
	 * Encerra a aplicação
	 */
	private void exit() {
		
		int response = ShowMessage.questionMessage(this, "Encerrar o Sistema", "Deseja encerrar o sistema?");
		
		if(response == JOptionPane.YES_OPTION) {
			System.exit(0);
		}		
		
	}
	
	/**
	 * Define o look and fell (aparência) da aplicação para a aparência do sistema operacional 
	 */
	private void setLookAndFell() {
		try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { e.printStackTrace(); }	
	}

}
