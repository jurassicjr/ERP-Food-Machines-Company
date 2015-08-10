package rh.controller;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import userInterface.components.FileChooser;
import userInterface.components.filters.PDFFilter;
import util.ShowMessage;
import database.DataBase;

public class InternalSatisfactionResearchReportController {

	
	private JFrame frame;
	private DataBase dataBase;

	public InternalSatisfactionResearchReportController(JFrame frame) {
		this.frame = frame;
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void close() {
		int i = ShowMessage.questionMessage(frame, "Fechar", "Deseja realmente fechar a janela de geração de relátório?");
		if(i == JOptionPane.YES_OPTION)frame.dispose();
	}
	
	public void selectOutput(FileChooser fileChooser, JTextField txtReportFile) {

		fileChooser.showSaveDialog(new PDFFilter());

		if (fileChooser.hasSelectedFile()) {

			String path = fileChooser.getSelectedFile().getAbsolutePath();
			if (!path.toUpperCase().endsWith(".PDF"))
				path = path += ".pdf";

			txtReportFile.setText(path);

		}
		
		
	}

	public void generateReport(String reportPathFile, boolean openFile, Map<String, Object> map)
			throws JRException, IOException {
		// direcionar para a pasta resources/filesReportsTemplate 
		java.io.InputStream jasperIS = getClass().getResourceAsStream("/resources/filesReportsTemplate/internal_satisfaction_research.jasper");
		JasperPrint print = JasperFillManager.fillReport(
				jasperIS, map,
				dataBase.getConnection());
		
		if (openFile) {
			JasperViewer view = new JasperViewer(print, false);
			view.setVisible(true);
			view.addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					view.setExtendedState(Frame.MAXIMIZED_BOTH);
				}
			});
			
			//JasperExportManager.exportReportToPdfFile(print, reportPathFile);
//			ShowMessage.successMessage(frame, "Relatório Criado",
//					"O relatório foi salvo em " + reportPathFile
//							+ "\ncom sucesso");
		} else {
			JasperExportManager.exportReportToPdfFile(print, reportPathFile);
			ShowMessage.successMessage(frame, "Relatório Criado",
					"O relatório foi salvo em " + reportPathFile
							+ "\ncom sucesso");
		}

	}
}
