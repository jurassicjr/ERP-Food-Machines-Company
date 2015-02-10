package rh.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.CBO;
import rh.controller.RegisterCBOFrameController;

public class RegisterCBOFrame extends JDialog {

	private static final long serialVersionUID = -5774451874914508578L;
	
	private JTextField txTitle;
	private JTextField txCode;
	
	private JButton btnCancel;
	private JButton btnClear;
	private JButton btnRegister;
	
	private RegisterCBOFrameController controller;

	public RegisterCBOFrame() {
		
		controller = new RegisterCBOFrameController(this);
		
		initialize();
		setListeners();
	}
		
	public void initialize() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 499, 138);
		setMinimumSize(new Dimension(499, 138));
		setTitle("Registro de CBO");
		setModal(true);
		util.Icon.setIcon(this);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel btnPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(btnPanel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(RegisterCBOFrame.class.getResource("/resources/cancel.png")));
		btnPanel.add(btnCancel);
		
		btnClear = new JButton("Limpar Dados");
		btnClear.setIcon(new ImageIcon(RegisterCBOFrame.class.getResource("/resources/ClearFrame.png")));
		btnPanel.add(btnClear);
		
		btnRegister = new JButton("Registrar");
		btnRegister.setIcon(new ImageIcon(RegisterCBOFrame.class.getResource("/resources/ok.png")));
		btnPanel.add(btnRegister);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblCargo = new JLabel("Cargo");
		
		txTitle = new JTextField();
		txTitle.setColumns(10);
		
		JLabel lblCode = new JLabel("Código");
		
		txCode = new JTextField();
		txCode.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCargo)
					.addGap(18)
					.addComponent(txTitle, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(lblCode)
					.addGap(18)
					.addComponent(txCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCargo)
						.addComponent(txTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCode))
					.addContainerGap(193, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
	}
	
	private void setListeners() {
		
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(btnClear)) controller.clear();
				else if(e.getSource().equals(btnCancel)) controller.closeFrame();
				else if(e.getSource().equals(btnRegister)) register();
			}
		};
		
		btnCancel.addActionListener(actionListener);
		btnClear.addActionListener(actionListener);
		btnRegister.addActionListener(actionListener);
		
		txCode.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) e.consume();
				if(txCode.getText().length() > 5) e.consume();
			}
			
		});
		
		addWindowListener(new WindowAdapter() {
			@Override public void windowClosing(WindowEvent e) { controller.closeFrame(); }
		});
	}
	
	private void register() {
		
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));  
		controller.register(txTitle.getText(), txCode.getText());
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	public CBO getCBO() {
		return controller.getCBO();
	}
}
