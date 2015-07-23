package userInterface.components.filters;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

/**
 * Implementa um filtro para a seleção de documentos em JFileChooser
 * 
 * @see javax.swing.JFileChooser
 */
public class DocumentFilter extends FileFilter {
	
	private ArrayList<String> extensions;
	
	/**
	 * Cria o objeto de filtros para imagem
	 */
	public DocumentFilter() {
		super();
		extensions = new ArrayList<String>();
		setExtensions();
	}

	@Override
	public boolean accept(File f) {
		
		if(f.isDirectory()) return true;
				
		String fileName = f.getName().toLowerCase();
		
		String tokens[] = fileName.split("\\.");		
		String extension = tokens[tokens.length - 1];
				
		return extensions.contains(extension);
	}

	@Override
	public String getDescription() {
		return "Documentos [docx, docx, docx, odf, pdf]";
	}
	
	/**
	 * Define as extenções permitidas para a seleção do arquivo
	 */
	private void setExtensions() {
		extensions.add("docx");
		extensions.add("docx");
		extensions.add("docx");
		extensions.add("odf");
		extensions.add("pdf");
	}

}
