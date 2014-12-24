package userInterface.components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Document para a representação de componentes que só aceitam strins upper case
 */
class UpperCaseDocument extends PlainDocument {  
    
	private static final long serialVersionUID = 2303368654327675495L;

	@Override  
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {  
      super.insertString(offset, str.toUpperCase(), attr);  
    }
	
} 