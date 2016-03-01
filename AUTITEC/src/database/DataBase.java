package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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
	
	public Connection getConnection()
	{
		return connection;
		
	}
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
				System.exit(1);
				return;
			}
			
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
			showDataBaseErrorMessage();			
			System.exit(1);
			
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
				else if(data[i] instanceof Date) statement.setDate(i + 1, (Date) data[i]);
				else if(data[i] instanceof Boolean) statement.setBoolean(i + 1, (boolean) data[i]);
				else if(data[i] == null) statement.setNull(i + 1, Types.NULL);
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
	 * Executa uma consulta SQL, passando os parâmetros de consulta
	 * 
	 * @param query A query da consulta sql 
	 * @param data O dado de parametro da consulta
	 * 
	 * @return O ResultSet resultante da consulta
	 * 
	 * @see java.sql.ResultSet
	 */
	public ResultSet executeQuery(String query, Object data) {
		
		try {
			
			PreparedStatement statement = connection.prepareStatement(query);			
										
			if(data instanceof Integer) statement.setInt(1, (int) data);
			else if(data instanceof Double) statement.setDouble(1, (double) data);
			else if(data instanceof String) statement.setString(1, (String) data);
			else if(data instanceof Date) statement.setDate(1, (Date) data);
			else if(data instanceof Boolean) statement.setBoolean(1, (boolean) data);
			else if(data == null) statement.setNull(1, Types.NULL);
			
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
	 * Executa uma consulta de atualização SQL, passando os parâmetros de consulta
	 * 
	 * @param query A query da consulta sql 
	 * @param data Os dados de parametros da consulta
	 */
	public void executeUpdate(String query, Object data[]) {
		
		try {
			
			PreparedStatement statement = connection.prepareStatement(query);
						
			for(int i = 0; data!= null && i < data.length; ++i) {
				
				if(data[i] instanceof Integer) statement.setInt(i + 1, (int) data[i]);
				else if(data[i] instanceof Double) statement.setDouble(i + 1, (double) data[i]);
				else if(data[i] instanceof String) statement.setString(i + 1, (String) data[i]);
				else if(data[i] instanceof Date) statement.setDate(i + 1, (Date) data[i]);
				else if(data[i] instanceof Boolean) statement.setBoolean(i + 1, (boolean) data[i]);
				else if(data[i] == null) statement.setNull(i + 1, Types.NULL);
				
			}
			
			statement.executeUpdate();
			
	
		} catch (SQLException e) {
			showDataBaseErrorMessage();
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
				
	}
	
	/**
	 * Executa uma consulta de atualização SQL em que não há parâmetros para a consulta
	 * 
	 * @param query A query da consulta sql 
	 */
	public void executeUpdate(String query) {
		
		PreparedStatement statement;
		
		try {
			
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
								
		} catch (SQLException e) {
			showDataBaseErrorMessage();
			e.printStackTrace();
		}
				
	}
	
	/**
	 * Executa uma consulta de atualização SQL, passando os parâmetros de consulta
	 * 
	 * @param query A query da consulta sql 
	 * @param data O dado de parametro da consulta
	 */
	public void executeUpdate(String query, Object data) {
		
		try {
			
			PreparedStatement statement = connection.prepareStatement(query);			
										
			if(data instanceof Integer) statement.setInt(1, (int) data);
			else if(data instanceof Double) statement.setDouble(1, (double) data);
			else if(data instanceof String) statement.setString(1, (String) data);
			else if(data instanceof Date) statement.setDate(1, (Date) data);
			else if(data instanceof Boolean) statement.setBoolean(1, (boolean) data);
			else if(data == null) statement.setNull(1, Types.NULL);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			showDataBaseErrorMessage();
			e.printStackTrace();
		}
				
	}
	
	/**
	 * Retorna o valor do auto_increment de uma determinada tabela
	 * 
	 * @param table A tabela que se deseja o auto_increment
	 * @return O valor do auto_increment da tabela
	 */
	public int getAutoIncrementValue(String table) {
		
		int id = -1;
		
		try {
			
			ResultSet resultSet = executeQuery("SHOW TABLE STATUS LIKE ?;", table);
			resultSet.next();
			id = resultSet.getInt("Auto_increment");
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
		return id;		
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
	
public void executeUpdate(String query, ArrayList<Object> list) {
		
		try {
			
			PreparedStatement statement = connection.prepareStatement(query);
						
			for(int i = 0; list!= null && i < list.size() ; ++i) {
				
				if(list.get(i) instanceof Integer) statement.setInt(i + 1, (int) list.get(i));
				else if(list.get(i) instanceof Double) statement.setDouble(i + 1, (double) list.get(i));
				else if(list.get(i) instanceof String) statement.setString(i + 1, (String) list.get(i));
				else if(list.get(i) instanceof Date) statement.setDate(i + 1, (Date) list.get(i));
				else if(list.get(i) instanceof Boolean) statement.setBoolean(i + 1, (boolean) list.get(i));
				else if(list.get(i) == null) statement.setNull(i + 1, Types.NULL);
				
			}
			
			statement.executeUpdate();
			
	
		} catch (SQLException e) {
			showDataBaseErrorMessage();
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
				
	}

	public ResultSet executeQuery(String query, List<Object> list) {
	
	try {
		
		PreparedStatement statement = connection.prepareStatement(query);			
					
		for(int i = 0; list!= null && i < list.size(); ++i) {
			
			if(list.get(i) instanceof Integer) statement.setInt(i + 1, (int) list.get(i));
			else if(list.get(i) instanceof Double) statement.setDouble(i + 1, (double) list.get(i));
			else if(list.get(i) instanceof String) statement.setString(i + 1, (String) list.get(i));
			else if(list.get(i) instanceof Date) statement.setDate(i + 1, (Date) list.get(i));
			else if(list.get(i) instanceof Boolean) statement.setBoolean(i + 1, (boolean) list.get(i));
			else if(list.get(i) == null) statement.setNull(i + 1, Types.NULL);
		}
	
		ResultSet resultSet = statement.executeQuery();
		
		return resultSet;	
		
	} catch (SQLException e) {
		showDataBaseErrorMessage();
		e.printStackTrace();
	}
	
	return null;
	
}
		
}
