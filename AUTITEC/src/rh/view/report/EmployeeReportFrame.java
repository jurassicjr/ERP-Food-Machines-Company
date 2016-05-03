package rh.view.report;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rh.controller.EmployeeReportFrameController;
import userInterface.components.FileChooser;
import util.Icon;

public class EmployeeReportFrame extends JFrame {
	
	private static final long serialVersionUID = 5225909404198325365L;
	
	private JTextField txReportFile;
	
	private JButton btnCancel;
	private JButton btnGenerate;
	private JButton btnSelectFile;
	
	private JCheckBox ckOpenFile;
		
	private FileChooser fileChooser;
	
	private EmployeeReportFrameController controller;

	public EmployeeReportFrame() {
		
		controller = new EmployeeReportFrameController(this);
		fileChooser = new FileChooser(this);
		
		initialize();
		setListeners();
	}
	
	private void initialize() {
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 595, 164);
		setMinimumSize(new Dimension(595, 195));
		Icon.setIcon(this);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(getClass().getResource("/resources/cancel.png")));
		panel.add(btnCancel);
		
		btnGenerate = new JButton("Criar Relatório");
		btnGenerate.setIcon(new ImageIcon(getClass().getResource("/resources/ok.png")));
		panel.add(btnGenerate);
		
		JPanel btnPanel = new JPanel();
		contentPane.add(btnPanel, BorderLayout.CENTER);
		
		JLabel lblReportFile = new JLabel("Arquivo de Relatório");
		txReportFile = new JTextField();
		txReportFile.setEditable(false);
		
		btnSelectFile = new JButton("Selecionar Arquivo");
		btnSelectFile.setIcon(new ImageIcon(getClass().getResource("/resources/open.png")));
		
		ckOpenFile = new JCheckBox("Abrir relatório criado");
				
		GroupLayout layout = new GroupLayout(btnPanel);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(lblReportFile)
							.addGap(18)
							.addComponent(txReportFile, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(btnSelectFile))
						.addComponent(ckOpenFile))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReportFile)
						.addComponent(txReportFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSelectFile))
					.addGap(18)
					.addComponent(ckOpenFile)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		btnPanel.setLayout(layout);
		
	}

	private void setListeners() {
		
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame();
			}
			
		});
		
		ActionListener btnListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource().equals(btnCancel)) controller.closeFrame();
				else if(e.getSource().equals(btnSelectFile)) controller.selectOutput(fileChooser, txReportFile);
				else if(e.getSource().equals(btnGenerate)) generateReport(); 
				
			}
		};
		
		btnCancel.addActionListener(btnListener);
		btnGenerate.addActionListener(btnListener);
		btnSelectFile.addActionListener(btnListener);
		
	}
	
	private void generateReport() {
		
		String reportFilePath = txReportFile.getText();
		boolean openFile = ckOpenFile.isSelected();
				
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		controller.generateReport(reportFilePath, openFile);
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
	}
}
