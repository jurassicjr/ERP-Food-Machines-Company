package rh.controller;

import javax.swing.JComboBox;

import model.User;
import rh.view.UpdatePermissionsFrame;
import database.DataBase;

public class UpdatePermissionsFrameController {
	
	private UpdatePermissionsFrame frame;
	
	private DataBase dataBase;
	
	public UpdatePermissionsFrameController(UpdatePermissionsFrame frame) {
		
		this.frame = frame;
		
		dataBase = new DataBase();
		dataBase.connect();
		
	}
	
	public void setUsers(JComboBox<User> users) {
		
		dataBase.executeQuery("SELECT * FROM user");
		
	}

}
