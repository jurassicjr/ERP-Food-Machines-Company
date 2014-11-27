package util;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Exibe mensagens em forma de popup
 */
public class ShowMessage {
	
	/**
	 * Exibe mensagem de erro em popup
	 * 
	 * @param c O componente para servir de posição relativa
	 * @param title O título da mensagem
	 * @param message A messagem a ser exibida
	 */
	public static void errorMessage(Component c, String title, String message) {
		
		if(c == null) c = JFrame.getFrames()[0];
		
		message = message.replaceAll("\n", "<br>");
		message = "<html><p align='center'>" + message + "</p></html>";
		
		JOptionPane.showMessageDialog(c, message, title, JOptionPane.ERROR_MESSAGE, null);
	}
	
	/**
	 * Exibe mensagem de sucesso em popup
	 * 
	 * @param c O componente para servir de posição relativa
	 * @param title O título da mensagem
	 * @param message A messagem a ser exibida
	 */
	public static void successMessage(Component c, String title, String message) {
		
		if(c == null) c = JFrame.getFrames()[0];
		
		message = message.replaceAll("\n", "<br>");
		message = "<html><p align='center'>" + message + "</p></html>";
		
		JOptionPane.showMessageDialog(c, message, title, JOptionPane.PLAIN_MESSAGE, null);
		
	}
	
	/**
	 * Exibe mensagem de sucesso em forma de popup
	 * 
	 * @param c O componente para servir de posição relativa
	 * @param title O título da mensagem
	 * @param message A messagem a ser exibida
	 * 
	 * @return A opção selecionada
	 */
	public static int questionMessage(Component c, String title, String message) {
		
		if(c == null) c = JFrame.getFrames()[0];
		
		message = message.replaceAll("\n", "<br>");
		message = "<html><p align='center'>" + message + "</p></html>";
		
		int response = JOptionPane.showConfirmDialog(c, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		
		return response;
		
	}
	
}
