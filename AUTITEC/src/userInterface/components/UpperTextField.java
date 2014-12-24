package userInterface.components;

import javax.swing.JTextField;

/**
 * Campo de texto para letras maúsculas   
 */
public class UpperTextField extends JTextField {
	
	private static final long serialVersionUID = 418261943302407652L;

	/**
	 * Cria o objeto de campo de texto com letras maiusculas
	 */
	public UpperTextField() {
		super();
        setDocument(new UpperCaseDocument());
    }
	
}
