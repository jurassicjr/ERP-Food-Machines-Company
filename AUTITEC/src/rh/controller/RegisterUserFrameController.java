package rh.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import model.Employee;
import model.Job;
import rh.view.RegisterUserFrame;
import rh.view.RegisterUserFrame.CheckBoxNode;
import util.MD5;
import util.ShowMessage;
import database.DataBase;
import database.dao.JobDAO;
import database.dao.UserDAO;

/**
 * Faz o controle do frma de registo de usuário
 */
public class RegisterUserFrameController {
	
	private DataBase dataBase;
	private RegisterUserFrame frame;
	
	/**
	 * Cria o objeto para o registo de usuários
	 */
	public RegisterUserFrameController(RegisterUserFrame frame) {
		
		this.frame = frame;
		
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	/**
	 * Fecha o frame do registro de usuário do sistema
	 */
	public void closeFrame() {
		 
		String title = "Cancelar registro de usuário";
		String message = "Deseja cancelar o registro do usuário?\nAs informações serão perdidas";
		
		int response = ShowMessage.questionMessage(frame, title, message);
		
		if(response == JOptionPane.YES_OPTION) {
			dataBase.close();
			frame.dispose();
		}
				
	}

	/**
	 * Define os empregados registrados no banco de dados em um JComboBox
	 */
	@SuppressWarnings("resource")
	public void setEmployees(JComboBox<Employee> employes) {
				
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT COUNT(*) FROM user");
			resultSet.next();
			int numUsers = resultSet.getInt(1);
						
			if(numUsers == 0) resultSet = dataBase.executeQuery("SELECT employee.* FROM employee WHERE employee.active = 1");
			
			else resultSet = dataBase.executeQuery("SELECT * FROM employee "
					+ "WHERE id NOT IN (SELECT employee FROM user) AND active = 1;");
			
			while(resultSet.next()) {
								
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String cpf = resultSet.getString("cpf");
				int jobId = resultSet.getInt("job");
								
				Job job = JobDAO.getJobById(jobId);
								
				Employee employee = new Employee(id, name, cpf, job);
				employes.addItem(employee);
				
			}
			
		} catch(SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Define em um JTextField o CPF vinculado ao empregado 
	 * 
	 * @param employee O empregado que se deseja o CPF
	 * @param txCpf O Campo de texto para armazenar o CPF
	 */
	public void setCpf(Employee employee, JTextField txCpf) {
		
		String cpf = employee.getCpf();
		cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);  
		
		txCpf.setText(cpf);
	}
		
	/**
	 * Registra o usuário no sistema definindo sua permissão de acesso
	 * 
	 * @param employee Os dados do funcionário
	 * @param accessLevel O nível de acesso ao funcionário
	 * @param password A senha de acesso para o funcionário
	 */
	public void register(Employee employee, JTree treePermissions, String password) {
		
		ArrayList<CheckBoxNode> permissions = getPermissions(treePermissions);
		
		if(!validateData(employee, permissions, password)) return;
		
		password = MD5.getMD5Code(password);
		
		new UserDAO(employee, permissions, password);
		
		ShowMessage.successMessage(frame, "Usuário Registrado", "O usuário foi registrado com sucesso");
		
		frame.dispose();
				
	}
	
	/**
	 * Verifica os dados para o registro do usuário
	 * 
	 * @param employee O empregado que será vinculado ao usuário do sistema
	 * @param accessLevel O nível de acesso vinculado ao usuário 
	 * @param password A senha do usuário
	 * 
	 * @return true se os dados são válidos e false caso contrário
	 */
	private boolean validateData(Employee employee, ArrayList<CheckBoxNode> permissions, String password) {
		
		String label = "";
		boolean valid = true;
		
		if(employee == null) {
			label = "Funcionário";
			valid = false;
		}
		else if(permissions.size() == 0) {
			label = "Nível de Acesso";
			valid = false;
		}
		else if(password == null || password.isEmpty()) {
			label = "Senha";
			valid = false;
		}
		
		if(!valid) {
			ShowMessage.errorMessage(frame, "Erro ao Registrar Usuário", "Por favor verifique preencha o seguinte campo:\n" + label);
		}
		
		return valid;
	}
	
	private ArrayList<CheckBoxNode> getPermissions(JTree tree) {
		
		ArrayList<CheckBoxNode> permissions = new ArrayList<>();

		Object root = tree.getModel().getRoot();

		for(int i = 0; i < tree.getModel().getChildCount(root); i++) {
			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getModel().getChild(root, i);
			
			for(int k = 0; k < node.getChildCount(); ++k) {
				
				DefaultMutableTreeNode leaf = (DefaultMutableTreeNode) tree.getModel().getChild(node, k);
				CheckBoxNode c = (CheckBoxNode) leaf.getUserObject();
				
				if(c.isSelected()) permissions.add(c);
			}			
			
		} 
		
		return permissions;
	}
	
}
