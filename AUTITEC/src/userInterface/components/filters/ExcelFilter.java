package userInterface.components.filters;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

public class ExcelFilter extends FileFilter {

	private ArrayList<String> extensions;

	public ExcelFilter() {
		super();
		extensions = new ArrayList<String>();
		setExtensions();
	}

	private void setExtensions() {
		extensions.add("xls");
		extensions.add("xlsx");
	}

	@Override
	public boolean accept(File f) {
		String fileName = f.getName().toLowerCase();

		String tokens[] = fileName.split("\\.");
		String extension = tokens[tokens.length - 1];

		return extensions.contains(extension);
	}

	@Override
	public String getDescription() {
		return "Documentos Excel";
	}

}
