package userInterface.components;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class RouteDestinationTreeNodeRenderer extends DefaultTreeCellRenderer 
{	
	private final ImageIcon BASENODE_ICON = new ImageIcon(this.getClass().getResource("/resources/truck.png"));
	private final ImageIcon LEAF_ICON = new ImageIcon(this.getClass().getResource("/resources/product.png"));
	public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

		super.getTreeCellRendererComponent(
		            tree, value, sel,
		            expanded, leaf, row,
		            hasFocus);
		if (leaf) 
			setIcon(LEAF_ICON);
		else
			setIcon(BASENODE_ICON);

		return this;
}

}
