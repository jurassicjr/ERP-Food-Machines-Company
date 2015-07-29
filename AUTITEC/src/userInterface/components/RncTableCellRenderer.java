package userInterface.components;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

public class RncTableCellRenderer extends DefaultTableCellRenderer {

 
	public RncTableCellRenderer()
	{
		setHorizontalAlignment(CENTER);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void setValue(Object value)
	{
		if(value instanceof Boolean)
		{
			Boolean bol = (Boolean) value;
			String imgPath;
			if(bol)
				imgPath = "/resources/ok.png";
			else
				imgPath = "/resources/clear.png";
				
				setIcon(new ImageIcon(getClass().getResource(imgPath)));
		}
	}
}
