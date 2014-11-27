package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.Properties;
import util.ShowMessage;

/**
 * Classe responsável por conexão e transãções com o banco de dados mysql.
 * Os parâmetros para a conexão são encontrados no arquivo de propriedas obtidos através da classe util.Properties
 * 
 *  @see util.Properties;
 */
public class DataBase {
	
	private String server;
	private String user;
	private String password;
	private String database;
	
	private Connection connection;
	
	/**
	 * Cria o objeto para manipulação do banco de dados
	 */
	public DataBase() {
		
		Properties properties = new Properties();
		
		server = properties.getPropertie("DataBaseServer");
		user = properties.getPropertie("DataBaseUser");
		password = properties.getPropertie("DataBasePass");
		database = properties.getPropertie("DataBaseName");
				
		connection = null;
		
	}
	
	/**
	 * Conecta-se ao banco de dados
	 */
	public void connect() {
		
		try {
			
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);
			
			String dns = "jdbc:mysql://" + server + "/" + database;
			
			connection = DriverManager.getConnection(dns, user, password);
			
			if(connection == null) {
				showDataBaseErrorMessage();
				return;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			
			showDataBaseErrorMessage();			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Executa uma consulta SQL, passando os parâmetros de consulta
	 * 
	 * @param query A query da consulta sql 
	 * @param data Os dados de parametros da consulta
	 * 
	 * @return O ResultSet resultante da consulta
	 * 
	 * @see java.sql.ResultSet
	 */
	public ResultSet executeQuery(String query, Object data[]) {
		
		try {
			
			PreparedStatement statement = connection.prepareStatement(query);			
						
			for(int i = 0; data!= null && i < data.length; ++i) {
				
				if(data[i] instanceof Integer) statement.setInt(i + 1, (int) data[i]);
				else if(data[i] instanceof Double) statement.setDouble(i + 1, (double) data[i]);
				else if(data[i] instanceof String) statement.setString(i + 1, (String) data[i]);
				
			}
			
			ResultSet resultSet = statement.executeQuery();
			
			return resultSet;	
			
		} catch (SQLException e) {
			showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Executa uma consulta SQL em que não há parâmetros para a consulta
	 * 
	 * @param query A query da consulta sql 
	 * 
	 * @return O ResultSet resultante da consulta
	 * 
	 * @see java.sql.ResultSet
	 */
	public ResultSet executeQuery(String query) {
		
		PreparedStatement statement;
		
		try {
			
			statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
						
			return resultSet;
			
		} catch (SQLException e) {
			showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
		return null;	
		
	}	
	
	/**
	 * Finaliza a conexão com o banco de dados
	 */
	public void close() {
		
		try {
			connection.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Exibe mensagem de erro na conexão com o banco de dados
	 */
	public static void showDataBaseErrorMessage() { 
		
		String title = "Erro ao comunicar-se ao banco de dados";
		String message = "Ocorreu um erro ao comunicar-se ao Banco de Dados.\n";
		message = message + "Por favor consulte o suporte.";
		
		ShowMessage.errorMessage(null, title, message);
		
	}
		
}
