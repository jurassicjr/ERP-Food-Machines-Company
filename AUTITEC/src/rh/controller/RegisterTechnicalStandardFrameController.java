package rh.controller;

import rh.view.RegisterTechnicalStandardFrame;
import userInterface.components.FileChooser;
import userInterface.components.filters.DocumentFilter;

/**
 * Realiza o controle do frame de registro de Normas Técnicas
 */
public class RegisterTechnicalStandardFrameController {
	
	private RegisterTechnicalStandardFrame frame;
	private FileChooser fileChooser;
	
	/**
	 * Cria o controlador para o registro de Normas Técnicas
	 */
	public RegisterTechnicalStandardFrameController(RegisterTechnicalStandardFrame frame) {
		this.frame = frame;		
		fileChooser = new FileChooser();
	}
	
	/**
	 * Registra uma nova norma técnica
	 */
	public void register() {
		
	}
	
	/**
	 * Seleciona o arquivo da norma técnica
	 */
	public void selectFile() {
		
		fileChooser.showOpenDialog(new DocumentFilter());
		
		if(fileChooser.hasSelectedFile()) {
			
			frame.setFilePath(fileChooser.getSelectedPathFile());
			
		}
		
	}	

}
