package userInterface.components;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

public class RealNumberField extends JTextField {

	private static final long serialVersionUID = -5951493366733422200L;
	
	public RealNumberField() {
		super();
		setListerns();
	}
	
	public RealNumberField(String s) {
		super(s);
		setListerns();
	}
	
	public RealNumberField(int colums) {
		super(colums);
		setListerns();
	}
	
	public RealNumberField(String s, int colums) {
		super(s, colums);
		setListerns();
	}
	
	private void setListerns() {
		
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				typed(e);
			}
						
		});
		
		addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusLost(FocusEvent e) {
				format();
			}
			
		});
		
	}
	
	private void typed(KeyEvent e) {
		
		if(Character.isDigit(e.getKeyChar()) || e.getKeyChar() == ',') {
			
			String s = getText() + e.getKeyChar();
			Matcher m = Pattern.compile("\\d+|\\d+\\,(\\d{2}|\\d|)").matcher(s);
			
			if(!m.matches()) e.consume();
			
		}
		else e.consume();
		
	}
	
	public void format() {
		
		String text = getText();
		
		if(text.endsWith(",")) setText(text + "00");
		else if(Pattern.compile("\\d+").matcher(text).matches()) setText(text + ",00");
		else if(Pattern.compile("\\d+\\,\\d").matcher(text).matches()) setText(text + "0");
	}
	
	public double getValue() {
		
		String text = getText();
		
		if(text == null || text.isEmpty()) return -1.0;
		
		text = text.replaceAll(",", ".");
		
		return Double.parseDouble(text);
	}

}
