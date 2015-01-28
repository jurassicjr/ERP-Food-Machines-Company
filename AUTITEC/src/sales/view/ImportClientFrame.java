package sales.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import sales.controller.ExcelImportController;
import userInterface.components.FileChooser;

public class ImportClientFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8188201907168659328L;

	private JLabel lblImportFile;
	private JButton btnSelectFile;
	private JTextField txtReportFile;
	private JPanel bottonPanel;
	private JButton btnCancel;
	private JButton btnImport;
	private FileChooser fileChooser;
	private ExcelImportController controller;
	private static final int i = 0;

	public ImportClientFrame() {
		controller = new ExcelImportController();
		initialize();
		setListeners();
	}

	private void initialize() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 595, 120);
		setMinimumSize(new Dimension(595, 120));
		setTitle("Relátorio de Fornecedores");
		getContentPane().setLayout(new BorderLayout(0, 0));
		initializePrincipal();
	}

	private void initializePrincipal() {
		JPanel principalPanel = new JPanel();
		getContentPane().add(principalPanel, BorderLayout.CENTER);

		lblImportFile = new JLabel("Arquivo para Importação");

		btnSelectFile = new JButton("Selecionar Arquivo");
		btnSelectFile.setIcon(new ImageIcon(SupplierReportFrame.class.getResource("/resources/open.png")));

		txtReportFile = new JTextField();
		txtReportFile.setEditable(false);
		txtReportFile.setColumns(10);

		JSeparator separator = new JSeparator();
		GroupLayout gl_principalPanel = new GroupLayout(principalPanel);
		gl_principalPanel.setHorizontalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.LEADING)
		                                .addGroup(
		                                        gl_principalPanel
		                                                .createSequentialGroup()
		                                                .addComponent(lblImportFile)
		                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(txtReportFile, GroupLayout.DEFAULT_SIZE, 310,
		                                                        Short.MAX_VALUE)
		                                                .addPreferredGap(ComponentPlacement.RELATED)
		                                                .addComponent(btnSelectFile, GroupLayout.PREFERRED_SIZE, 141,
		                                                        GroupLayout.PREFERRED_SIZE))
		                                .addComponent(separator, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE))
		                .addContainerGap()));
		gl_principalPanel.setVerticalGroup(gl_principalPanel.createParallelGroup(Alignment.LEADING).addGroup(
		        gl_principalPanel
		                .createSequentialGroup()
		                .addContainerGap()
		                .addGroup(
		                        gl_principalPanel
		                                .createParallelGroup(Alignment.BASELINE)
		                                .addComponent(lblImportFile)
		                                .addComponent(btnSelectFile, GroupLayout.PREFERRED_SIZE, 25,
		                                        GroupLayout.PREFERRED_SIZE)
		                                .addComponent(txtReportFile, GroupLayout.PREFERRED_SIZE,
		                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		                .addPreferredGap(ComponentPlacement.UNRELATED)
		                .addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
		                        GroupLayout.PREFERRED_SIZE).addContainerGap(72, Short.MAX_VALUE)));
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

		btnImport = new JButton("Importar");
		btnImport.setIcon(new ImageIcon(SupplierReportFrame.class.getResource("/resources/ok.png")));
		bottonPanel.add(btnImport);
	}

	private void setListeners() {
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(btnSelectFile))
					controller.selectOutput(fileChooser, txtReportFile);
				else if (e.getSource().equals(btnImport)) {
					try {
						controller.importExcel(i, txtReportFile);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		};
	}
}
