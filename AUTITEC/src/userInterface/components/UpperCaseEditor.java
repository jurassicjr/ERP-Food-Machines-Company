package userInterface.components;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 * Editor usado pelo Jtable para um editar uma célula utilizando apenas caracteres maiusculos.
 * O editor pode ser qualquer componente swing.
 */
public class UpperCaseEditor extends AbstractCellEditor implements TableCellEditor {
	
	private static final long serialVersionUID = 2207445360540348199L;
	
	JTextField editor = new JTextField();  

	/**
	 * Cria o objeto para a edição do Jtable
	 */
	public UpperCaseEditor() {  
      super();  
      editor.setDocument(new UpperCaseDocument());   
	} 

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex) {  
		editor.setText((String)value);  
		return editor ;  
	}  

	@Override
	public Object getCellEditorValue() {  
		return editor.getText();  
	} 
    
}
