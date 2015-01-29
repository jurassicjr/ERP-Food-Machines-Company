package sales.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JTextField;

import userInterface.components.FileChooser;
import userInterface.components.filters.PDFFilter;
import OfficeImport.ExcelXLSImport;
import OfficeImport.Import;
import OfficeImport.ImportFactory;

public class ExcelImportController {

	private ExcelXLSImport excelImport;

	public ExcelImportController() {
		excelImport = new ExcelXLSImport();
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
			File file = new File(txtReportFile.getText());
			ImportFactory iPF = new ImportFactory();
			Import ip = iPF.getImport(file);
			try {
	            ip.importExcel(file);
            } catch (IOException e) {
	           
	            e.printStackTrace();
            }
		}

	}

}
