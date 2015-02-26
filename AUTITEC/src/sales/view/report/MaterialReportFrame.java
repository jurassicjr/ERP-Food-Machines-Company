package sales.view.report;

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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import sales.controller.MaterialReportController;
import userInterface.components.FileChooser;

public class MaterialReportFrame extends JFrame {

	/**
	 * 
	 */
    private static final long serialVersionUID = -3711390641551677515L;
	
    private JFrame frame;
	
    private MaterialReportController controller;
	
    private JPanel principalPanel;
	private JPanel bottonPanel;
	
	private JButton btnCancel;
	private JButton btnGenerate;
	private JButton btnSelectFile;
	
	private JCheckBox ckOpenFile;
	
	private JTextField txtReportFile;
	
	private JLabel lblReportFile;
	
	private FileChooser fileChooser;

	public MaterialReportFrame() {
		controller = new MaterialReportController(this);
		fileChooser = new FileChooser(this);
		frame = this;
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 595, 187);
		setMinimumSize(new Dimension(595, 195));
		setTitle("Relátorios de materias");
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
		principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);

		lblReportFile = new JLabel("Arquivo de Relátorio");

		btnSelectFile = new JButton("Selecionar Arquivo");
		btnSelectFile.setIcon(new ImageIcon(SupplierReportFrame.class.getResource("/resources/open.png")));

		txtReportFile = new JTextField();
		txtReportFile.setEditable(false);
		txtReportFile.setColumns(10);

		ckOpenFile = new JCheckBox("Abrir relatório criado");

		JSeparator separator = new JSeparator();
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.LEADING)
		                                .addComponent(separator, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
		                                .addComponent(ckOpenFile)
		                                .addGroup(
		                                        gl_principalPanel
		                                                .createSequentialGroup()
		                                                .addComponent(lblReportFile)
		                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(txtReportFile, GroupLayout.DEFAULT_SIZE, 310,
		                                                        Short.MAX_VALUE)
		                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(btnSelectFile, GroupLayout.PREFERRED_SIZE, 141,
		                                                        GroupLayout.PREFERRED_SIZE))).addContainerGap()));
		gl_principalPanel.setVerticalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblReportFile)
		                                .addComponent(btnSelectFile, GroupLayout.PREFERRED_SIZE, 25,
		                                        GroupLayout.PREFERRED_SIZE)
		                                .addComponent(txtReportFile, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addGap(18)
		                .addComponent(ckOpenFile)
		                .addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
		                .addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                        GroupLayout.PREFERRED_SIZE).addContainerGap()));
		principalPanel.setLayout(gl_principalPanel);

		initializeBotton();
	}

	private void initializeBotton() {
		bottonPanel = new JPanel();
		getContentPane().add(bottonPanel, BorderLayout.SOUTH);
		bottonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(SupplierReportFrame.class.getResource("/resources/cancel.png")));
		bottonPanel.add(btnCancel);

		btnGenerate = new JButton("Criar Relatório");
		btnGenerate.setIcon(new ImageIcon(SupplierReportFrame.class.getResource("/resources/ok.png")));
		bottonPanel.add(btnGenerate);
	}

	private void setListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.closeFrame(frame);
			}
		});
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnCancel))
					controller.closeFrame(frame);
				else if (e.getSource().equals(btnSelectFile))
					controller.selectOutput(fileChooser, txtReportFile);
				else if(e.getSource().equals(btnGenerate))
					generateReport();
			}

		};
		btnCancel.addActionListener(buttonListener);
		btnGenerate.addActionListener(buttonListener);
		btnSelectFile.addActionListener(buttonListener);
	}

	private void generateReport() {
		
		String reportFilePath = txtReportFile.getText();
		boolean openFile = ckOpenFile.isSelected();
				
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		controller.generateReport(reportFilePath, openFile);
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
