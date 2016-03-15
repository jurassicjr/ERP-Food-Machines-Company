package userInterface.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import model.Session;
import userInterface.controller.RegisterIssueFrameController;
import util.Icon;

public class RegisterIssueFrame extends JFrame {

	private static final long serialVersionUID = 7469878965465678287L;
	
	private JTextField txEmployee;
	private JTextField txDate;
	private JTextArea txIssue;
	
	private JButton btnCancel;
	private JButton btnClear;
	private JButton btnRegister;
	
	private RegisterIssueFrameController controller;

	public RegisterIssueFrame() {
		
		controller = new RegisterIssueFrameController(this);
		
		initialize();
		setListeners();
		
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 420);
		setTitle("Informar Problema");
		Icon.setIcon(this);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel buttonsPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonsPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(buttonsPanel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(getClass().getResource("/resources/cancel.png")));
		buttonsPanel.add(btnCancel);
		
		btnClear = new JButton("Limpar Dados");
		btnClear.setIcon(new ImageIcon(getClass().getResource("/resources/clear.png")));
		buttonsPanel.add(btnClear);
		
		btnRegister = new JButton("Ok");
		btnRegister.setIcon(new ImageIcon(getClass().getResource("/resources/ok.png")));
		buttonsPanel.add(btnRegister);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblEmployee = new JLabel("Funcionário:");
		txEmployee = new JTextField();
		txEmployee.setText(Session.getInstance().getUser().getEmployee().getName());
		txEmployee.setCaretPosition(0);
		txEmployee.setEditable(false);
		
		JLabel lblDate = new JLabel("Data");
		txDate = new JTextField();
		txDate.setEditable(false);
		txDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout layout = new GroupLayout(panel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblEmployee)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txEmployee, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblDate)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txDate, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmployee)
						.addComponent(txEmployee, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDate))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		txIssue = new JTextArea();
		txIssue.setLineWrap(true);
		
		scrollPane.setViewportView(txIssue);
		scrollPane.setBorder(new TitledBorder("Descrição"));
		panel.setLayout(layout);
		
	}

	private void setListeners() {
		
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(btnCancel)) controller.closeFrame();
				else if(e.getSource().equals(btnClear)) resetFrame();
				else if(e.getSource().equals(btnRegister)) register();
				
			}
		};
		
		btnCancel.addActionListener(btnListener);
		btnClear.addActionListener(btnListener);
		btnRegister.addActionListener(btnListener);
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame();
			}
			
		});
		
	}
	
	private void resetFrame() {
		controller.clear();
		txEmployee.setText(Session.getInstance().getUser().getEmployee().getName());
		txDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
	}
	
	private void register() {
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		controller.register(txIssue.getText(), txEmployee.getText());
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

}
