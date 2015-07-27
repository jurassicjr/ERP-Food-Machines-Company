package equipmenteMaintenance.controller;

import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import userInterface.components.FileChooser;
import userInterface.components.filters.DocumentFilter;
import userInterface.components.filters.PDFFilter;
import util.ClearFrame;
import util.ShowMessage;
import model.Equipment;
import model.Maintenance;
import database.dao.EquipmentDAO;
import database.dao.MaintenceDAO;
import equipmentMaintenance.view.register.SendCertificateFiles;

public class SendCertificateFilesController {
	private SendCertificateFiles frame;
	private MaintenceDAO dao;

	public SendCertificateFilesController(SendCertificateFiles frame) {

		this.frame = frame;
	}

	public void fillCbo(JComboBox cbo) {
		MaintenceDAO dao = new MaintenceDAO();
		dao.fillCbo(cbo);
	}

	public void fillCboComplete(JComboBox cbo) {
		// EquipmentDAO dao2 = new EquipmentDAO();
		dao = new MaintenceDAO();
		dao.fillCboComplete(cbo);
	}

	public Maintenance recoveryMaintenance(Equipment equipment) {
		dao = new MaintenceDAO();
		return dao.recoveryMaintenanceOfEquip(equipment);

	}

	public void clear() {

		String title = "Limpar os Dados Inseridos";
		String message = "Deseja realmente limpar os dados inseridos?";

		int response = ShowMessage.questionMessage(frame, title, message);
		ClearFrame.clear(frame);
		
	}

	public void selectOutput(FileChooser fileChooser, JTextField txtReportFile) {

		fileChooser.showOpenDialog(new DocumentFilter());

		if (fileChooser.hasSelectedFile()) {

			String path = fileChooser.getSelectedFile().getAbsolutePath();
			if (!path.toUpperCase().endsWith(".PDF"))
				path = path += ".pdf";

			txtReportFile.setText(path);

		}
	}

	public void closeFrame(JFrame frame) {

		String title = "Sair";
		String message = "Deseja realmente sair?\nOs dados não salvos serão perdidos.";

		int response = ShowMessage.questionMessage(frame, title, message);

		if (response == JOptionPane.YES_OPTION) {
			frame.dispose();

		}

	}

}
