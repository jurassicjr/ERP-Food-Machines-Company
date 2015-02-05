package userInterface.view;

import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import model.Session;
import model.User;

public class MenuBarFactory {
	
	public static void build(ArrayList<JMenuItem> menuItens) {
		
		User u = Session.getInstance().getUser();
		
		ArrayList<String> permissions = u.getPermissions();		
		
		for(JMenuItem menuItem : menuItens) {
			
			if(permissions.contains(menuItem.getName())) {
			showMenu(menuItem);
			}
			
		}		
		
	}
	
	public static void showMenu(JMenuItem menu) {
		
		menu.setVisible(true);
						
		Object parent = menu.getParent();
		while(parent instanceof JPopupMenu) {
			
			JMenu actMenu = (JMenu) ((JPopupMenu)parent).getInvoker();
			actMenu.setVisible(true);
						
			parent = actMenu.getParent();
						
		}
	
	}
}
