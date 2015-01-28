package sales.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JTextField;

import userInterface.components.FileChooser;
import userInterface.components.filters.PDFFilter;
import OfficeImport.ExcelImport;

public class ExcelImportController {

	private ExcelImport excelImport;

	public ExcelImportController() {
		excelImport = new ExcelImport();
	}

	public void selectOutput(FileChooser fileChooser, JTextField txtReportFile) {
		fileChooser.showSaveDialog(new PDFFilter());

		if (fileChooser.hasSelectedFile()) {

			String path = fileChooser.getSelectedFile().getAbsolutePath();
			if (!path.toUpperCase().endsWith(".XLS"))
				path = path += ".xls";

			txtReportFile.setText(path);
		}
	}

	public void importExcel(int i, JTextField txtReportFile) throws FileNotFoundException {
		if (i == 0) {
			FileInputStream file = new FileInputStream(txtReportFile.getText());
			try {
				excelImport.importClient(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
