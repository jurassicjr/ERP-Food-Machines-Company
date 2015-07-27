package equipmentMaintenance.view.register;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Equipment;
import model.Maintenance;
import net.sf.nachocalendar.components.DateField;

import javax.swing.JButton;

import equipmenteMaintenance.controller.SendCertificateFilesController;
import financial.view.GenerateFinancialReportFrame;
import sales.view.report.SupplierReportFrame;
import userInterface.components.FileChooser;
import util.FTP;
import util.ShowMessage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.Dimension;

public class SendCertificateFiles extends JFrame {
	private DateField txtDateMain;
	private JPanel panel;
	private JComboBox cboMain;
	private JScrollPane scrollPane;
	private JTextField txtSerialNumber;
	private JButton btnSelectFile;
	private JTextField txtReportFile;
	private final SendCertificateFiles frame = this;
	private SendCertificateFilesController controller;
	private Maintenance maintenance;
	private Equipment equipment;
	private DateField txtWarranty;
	private JTextArea txtMainDesc;
	private JPanel panelBottom;
	private JButton btnConfirm;
	private JButton btnCancel;
	private FileChooser fileChooser;

	public SendCertificateFiles() {

		initialize();
		initializeSub();
		setListeners();
	}

	private void setListeners() {
		ActionListener ButtonActions = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(cboMain)) {
					verifySelection();
					updateCbo();
				} else if (e.getSource().equals(btnSelectFile)) {
					controller.selectOutput(fileChooser, txtReportFile);
				} else if (e.getSource().equals(btnConfirm)) {
					if (verify())
						upFile();
					else
						ShowMessage.errorMessage(frame,"Erro", "Preencha todos os campos.");
					
				} else if (e.getSource().equals(btnCancel))
					controller.closeFrame(frame);
			}
		};
		cboMain.addActionListener(ButtonActions);
		btnConfirm.addActionListener(ButtonActions);
		btnSelectFile.addActionListener(ButtonActions);
		btnCancel.addActionListener(ButtonActions);
	}
	private void updateCbo()
	{
		equipment = (Equipment) cboMain.getSelectedItem();
		maintenance = controller.recoveryMaintenance(equipment);
		if (maintenance != null)
			setFields();
		else {
			ShowMessage.errorMessage(frame, "Erro",
					"Não existem manuteções para esse equipamento");
			controller.clear();
		}
	}
	private boolean verifySelection() {
		if (cboMain.getSelectedIndex() != -1)
			return true;
		return false;
	}

	private boolean verify() {
		if(txtReportFile.getText().equals(""))
			return false;
		if (fileChooser == null &&  !verifySelection())
			return false;
		return true;
	}

	private void upFile() {
		FTP ftp = new FTP();
		if (ftp.upload(fileChooser.getSelectedFile(), fileChooser
				.getSelectedFile().getName(), fileChooser.getSelectedFile()
				.getAbsolutePath()))
			;
		ShowMessage.successMessage(this, "Aviso",
				"Arquivo enviado com sucesso.");
		controller.clear();
		

	}

	private void initializeSub() {
		maintenance = new Maintenance();
		equipment = new Equipment();
		controller = new SendCertificateFilesController(frame);
		controller.fillCboComplete(cboMain);
		fileChooser = new FileChooser();

	}

	private void setFields() {
		txtDateMain.setValue(maintenance.getDateMaintenance());
		txtSerialNumber.setText(equipment.getSerialNumber());
		txtWarranty.setValue(equipment.getWarrantyDate());
		txtMainDesc.setText(maintenance.getDescriptionMaintenance());

	}

	private void initialize() {

		panel = new JPanel();
		setBounds(100, 100, 450, 397);
		setMinimumSize(new Dimension(450, 397));
		setTitle("Envio de Arquivos de Laudo sobre Manuntenção");

		panelBottom = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(panelBottom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panelBottom, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		btnConfirm = new JButton("Confirmar");
		btnConfirm.setIcon(new ImageIcon(GenerateFinancialReportFrame.class
				.getResource("/resources/ok.png")));

		btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(GenerateFinancialReportFrame.class
				.getResource("/resources/cancel.png")));
		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(gl_panelBottom.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_panelBottom.createSequentialGroup()
						.addContainerGap(230, Short.MAX_VALUE)
						.addComponent(btnCancel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnConfirm).addContainerGap()));
		gl_panelBottom.setVerticalGroup(gl_panelBottom.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_panelBottom
								.createSequentialGroup()
								.addGroup(
										gl_panelBottom
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnConfirm)
												.addComponent(btnCancel))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		panelBottom.setLayout(gl_panelBottom);

		JLabel lblManuteno = new JLabel("Equipamento");

		cboMain = new JComboBox();

		scrollPane = new JScrollPane();

		JLabel lblNewLabel = new JLabel("Data Manutenção");

		txtDateMain = new DateField();
		txtDateMain.getFormattedTextField().setEditable(false);
		txtDateMain.setEnabled(false);

		JLabel lblNmeroDeSrie = new JLabel("N° de Série");

		txtSerialNumber = new JTextField();
		txtSerialNumber.setEditable(false);
		txtSerialNumber.setColumns(10);

		txtWarranty = new DateField();
		txtWarranty.getFormattedTextField().setEnabled(false);
		txtWarranty.getFormattedTextField().setEditable(false);

		JLabel lblGarantia = new JLabel("Garantia");

		JLabel lblDescrioDaManuteno = new JLabel("Descrição da Manutenção");

		btnSelectFile = new JButton("Selecionar Arquivo");

		btnSelectFile.setIcon(new ImageIcon(SupplierReportFrame.class
				.getResource("/resources/open.png")));

		txtReportFile = new JTextField();
		txtReportFile.setEditable(false);
		txtReportFile.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														scrollPane,
														GroupLayout.DEFAULT_SIZE,
														394, Short.MAX_VALUE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblManuteno)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		cboMain,
																		0,
																		328,
																		Short.MAX_VALUE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		txtReportFile,
																		GroupLayout.DEFAULT_SIZE,
																		247,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		btnSelectFile))
												.addComponent(
														lblDescrioDaManuteno)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblGarantia)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		txtWarranty,
																		GroupLayout.PREFERRED_SIZE,
																		113,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addComponent(
																		lblNewLabel)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		txtDateMain,
																		GroupLayout.DEFAULT_SIZE,
																		129,
																		Short.MAX_VALUE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		lblNmeroDeSrie)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		txtSerialNumber,
																		GroupLayout.DEFAULT_SIZE,
																		336,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblManuteno)
												.addComponent(
														cboMain,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(lblNmeroDeSrie)
												.addComponent(
														txtSerialNumber,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		txtWarranty,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(18)
																.addComponent(
																		lblDescrioDaManuteno))
												.addComponent(lblGarantia)
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.BASELINE)
																.addComponent(
																		lblNewLabel)
																.addComponent(
																		txtDateMain,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 80,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.BASELINE)
												.addComponent(btnSelectFile)
												.addComponent(
														txtReportFile,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		txtWarranty.setEnabled(false);
		txtMainDesc = new JTextArea();
		txtMainDesc.setEditable(false);
		scrollPane.setViewportView(txtMainDesc);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
}
