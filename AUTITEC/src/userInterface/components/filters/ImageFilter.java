package userInterface.components.filters;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

/**
 * Implementa um filtro para a seleção de imagens em JFileChooser
 * 
 * @see javax.swing.JFileChooser
 */
public class ImageFilter extends FileFilter {
	
	private ArrayList<String> extensions;
	
	/**
	 * Cria o objeto de filtros para imagem
	 */
	public ImageFilter() {
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
		return "Arquivos de imagem [jpg, png]";
	}
	
	/**
	 * Define as extenções permitidas para a seleção do arquivo
	 */
	private void setExtensions() {
		extensions.add("jpg");
		extensions.add("png");
	}

}
