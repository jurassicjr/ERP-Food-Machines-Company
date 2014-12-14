package login.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import login.view.LoginFrame;
import userInterface.view.MainFrame;
import util.MD5;
import util.ShowMessage;
import database.DataBase;

/**
 * Classe para controlar o frame de login
 */
public class LoginFrameController {
	
	private LoginFrame frame;
	private DataBase dataBase;
	
	/**
	 * Cria o controlador para o frame de login
	 * 
	 * @param frame O frame de login
	 */
	public LoginFrameController(LoginFrame frame) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
		this.frame = frame;
	}
	
	/**
	 * Realiza o login, autorizando ou não o acesso ao sistema
	 * 
	 * @param cpf O CPF do usuário que deseja logar-se
	 * @param password A senha do usuário que deseja logar-se
	 */
	public void login(String cpf, String password) {
		
		password = MD5.getMD5Code(password);
		cpf = cpf.replaceAll("\\.|-", "");
		
		try {
			
			String sql = "SELECT * FROM user, employee WHERE user.employee = employee.id AND employee.cpf = ?;";
			ResultSet resultSet = dataBase.executeQuery(sql, cpf);
			
			if(resultSet.next()) {
				
				frame.dispose();
				
				MainFrame mainFrame = new MainFrame();
				mainFrame.setVisible(true);
				mainFrame.setLocationRelativeTo(frame);
				
			}
			else {
				ShowMessage.errorMessage(frame, "Erro ao logar", "Usuário ou senha incorreto");
			}
									
		} catch (SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
				
	}	

}
