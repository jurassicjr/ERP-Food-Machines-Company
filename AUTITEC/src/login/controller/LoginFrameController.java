package login.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import login.view.LoginFrame;
import model.Employee;
import model.Session;
import model.User;
import userInterface.view.MainFrame;
import util.MD5;
import util.ShowMessage;
import database.DataBase;
import database.dao.EmployeeDAO;

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
		Object loginData[] = {cpf,password}; 
		try {
			String sql = "SELECT user.*, employee.id as 'id_employee' FROM user, employee WHERE user.employee = employee.id AND employee.cpf = ? and password = ?";
			ResultSet resultSet = dataBase.executeQuery(sql,loginData);
												
			if(resultSet.next()) {
				
				int employeeId = resultSet.getInt("id_employee");
				Employee employee = EmployeeDAO.getEmployeeById(employeeId);
				int id = resultSet.getInt("id");
				
				ResultSet rs = dataBase.executeQuery("SELECT * FROM permission WHERE user = ?", id);
				ArrayList<String> permissions = new ArrayList<String>();
				
				while(rs.next()) {
					permissions.add(rs.getString("permission"));
				}
				rs.close();
				
				createSession(new User(employee, permissions));
												
				frame.dispose();
				
				MainFrame mainFrame = new MainFrame();
				mainFrame.setVisible(true);
				mainFrame.setLocationRelativeTo(frame);
				
			}
			else {
				ShowMessage.errorMessage(frame, "Erro ao logar", "Usuário ou senha incorretos");
			}
			
			resultSet.close();
									
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
		Session.getInstance().setUser(user);		
	}

}
