package rh.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import model.Employee;
import model.User;
import rh.view.UpdatePermissionsFrame;
import util.ShowMessage;
import database.DataBase;
import database.dao.EmployeeDAO;

public class UpdatePermissionsFrameController {
	
	private UpdatePermissionsFrame frame;
	
	private DataBase dataBase;
	
	public UpdatePermissionsFrameController(UpdatePermissionsFrame frame) {
		
		this.frame = frame;
		
		dataBase = new DataBase();
		dataBase.connect();
		
	}
	
	public void setUsers(JComboBox<User> users) {
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM user");
			while(resultSet.next()) {
				
				int idUser = resultSet.getInt("id");
				int idEmployee = resultSet.getInt("employee");
				
				Employee employee = EmployeeDAO.getEmployeeById(idEmployee);
				
				ResultSet rs = dataBase.executeQuery("SELECT * FROM permission WHERE user = ?", idUser);
				ArrayList<String> permissions = new ArrayList<String>();
				while(rs.next()) permissions.add(rs.getString("permission"));

				User user = new User(idUser, employee, permissions);
				users.addItem(user);
				
			}
			
			resultSet.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
				
	}
	
	public void setPermissions(User user, JTree permissions) {
		
		Object root = permissions.getModel().getRoot();

		for(int i = 0; i < permissions.getModel().getChildCount(root); i++) {
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) permissions.getModel().getChild(root, i);
			
			for(int k = 0; k < node.getChildCount(); ++k) {
				
				DefaultMutableTreeNode leaf = (DefaultMutableTreeNode) permissions.getModel().getChild(node, k);
				UpdatePermissionsFrame.CheckBoxNode c = (UpdatePermissionsFrame.CheckBoxNode) leaf.getUserObject();
				
				if(user.getPermissions().contains(c.getMenuTag())) c.setSelected(true);
				
			}			
			
		}
		
		permissions.revalidate();
		permissions.repaint();
		
	}

	public void closeFrame() {
		 
		String title = "Cancelar a atualização de permissão";
		String message = "Deseja cancelar atualização de permissão?\nAs informações serão perdidas";
		
		int response = ShowMessage.questionMessage(frame, title, message);
		
		if(response == JOptionPane.YES_OPTION) {
			dataBase.close();
			frame.dispose();
		}
				
	}
	
	public boolean resetPermissions(User user, JTree permissions) {
		
		String title = "Cancelar a atualização de permissão";
		String message = "Deseja cancelar atualização de permissão?\nAs informações serão perdidas";
		
		int response = ShowMessage.questionMessage(frame, title, message);
		
		if(response == JOptionPane.YES_OPTION) {
			resetPermissions(permissions);
			setPermissions(user, permissions);
			return true;
		}
		
		return false;
		
	}
	
	public boolean hasModification(User user, JTree permissions) {
		
		Object root = permissions.getModel().getRoot();

		for(int i = 0; i < permissions.getModel().getChildCount(root); i++) {
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) permissions.getModel().getChild(root, i);
			
			for(int k = 0; k < node.getChildCount(); ++k) {
				
				DefaultMutableTreeNode leaf = (DefaultMutableTreeNode) permissions.getModel().getChild(node, k);
				UpdatePermissionsFrame.CheckBoxNode c = (UpdatePermissionsFrame.CheckBoxNode) leaf.getUserObject();
				
				if(c.isSelected()) {
					if(!user.getPermissions().contains(c.getMenuTag())) return true;
				}
				else {
					if(user.getPermissions().contains(c.getMenuTag())) return true;
				}
								
			}
		}
		
		return false;
	}
	
	public void updatePermissions(User user, JTree permissions) {
		
		dataBase.executeUpdate("DELETE FROM permission WHERE user = ?", user.getId());
		
		ArrayList<UpdatePermissionsFrame.CheckBoxNode> p = getPermissions(permissions);
		for(UpdatePermissionsFrame.CheckBoxNode c : p) {
			Object o[] = new Object[]{user.getId(), c.getMenuTag()};
			dataBase.executeUpdate("INSERT INTO permission (user, permission) VALUES(?, ?)", o);
		}
		
		ShowMessage.successMessage(frame, "Permissões atualizadas", "Permissões atualizadas com sucesso");
		
	}
	
	private ArrayList<UpdatePermissionsFrame.CheckBoxNode> getPermissions(JTree tree) {
		
		ArrayList<UpdatePermissionsFrame.CheckBoxNode> permissions = new ArrayList<>();

		Object root = tree.getModel().getRoot();

		for(int i = 0; i < tree.getModel().getChildCount(root); i++) {
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getModel().getChild(root, i);
			
			for(int k = 0; k < node.getChildCount(); ++k) {
				
				DefaultMutableTreeNode leaf = (DefaultMutableTreeNode) tree.getModel().getChild(node, k);
				UpdatePermissionsFrame.CheckBoxNode c = (UpdatePermissionsFrame.CheckBoxNode) leaf.getUserObject();
				
				if(c.isSelected()) permissions.add(c);
			}			
			
		} 
		
		return permissions;
	}
	
	public void resetPermissions(JTree permissions) {
				
		Object root = permissions.getModel().getRoot();

		for(int i = 0; i < permissions.getModel().getChildCount(root); i++) {
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) permissions.getModel().getChild(root, i);
			
			for(int k = 0; k < node.getChildCount(); ++k) {
				
				DefaultMutableTreeNode leaf = (DefaultMutableTreeNode) permissions.getModel().getChild(node, k);
				UpdatePermissionsFrame.CheckBoxNode c = (UpdatePermissionsFrame.CheckBoxNode) leaf.getUserObject();
				c.setSelected(false);
				
			}
			
		}
		
		permissions.revalidate();
		permissions.repaint();
	}
}
