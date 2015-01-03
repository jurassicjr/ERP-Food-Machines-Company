package userInterface.components.filters;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

public class PDFFilter extends FileFilter {
	
	private ArrayList<String> extensions;
	
	public PDFFilter() {
		super();
		extensions = new ArrayList<String>();
		setExtensions();
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
		return "Documentos PDF";
	}
	
	private void setExtensions() {
		extensions.add("pdf");
	}

}
