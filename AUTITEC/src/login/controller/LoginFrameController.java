package login.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import login.view.LoginFrame;
import model.Employee;
import model.Job;
import model.Session;
import model.User;
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
				
				//Employee e = new 
				
//				private int id;
//				private String name;
//				private String cpf;
//				private Job job;
//				
//				int employeeId = resultSet.getInt(columnLabel)
				
				
				int permission = resultSet.getInt("permission");
				
				createSession(new User(null, permission));
				
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
	
	/**
	 * Cria a sessão para o usuário logado
	 * 
	 * @param User O usuário logado
	 */
	private void createSession(User user) {
		
		//Session.getInstance().ser
		
	}

}
