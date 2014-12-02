package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * Realiza manipulações FTP, possibilitando o upload e download de arquivos
 */
public class FTP {
	
	private FTPClient ftp;
	private Properties properties;
	
	private String server;
	private String user;
	private String password;
	private String FTPPath;
	
	/**
	 * Cria o objeto para manipulação de requisições FTP
	 */
	public FTP() {
		
		ftp = new FTPClient();
		
		properties = new Properties();
		server = properties.getPropertie("FTPServer");
		user = properties.getPropertie("FTPUser");
		password = properties.getPropertie("FTPPass");
		FTPPath = properties.getPropertie("FTPPath");
        
	}
	
	/**
	 * Conecta-se ao servidor ftp
	 * 
	 * 
	 * @param server O servidor FTP que será conectado
	 * @param user O usuário do servidor FTP
	 * @param password A senha do usuário FTP
	 * 
	 * @return true se a conexão foi bem sucedidade e false se houve erro na conexão
	 */
	private boolean connect() {
		
		try {
			
			ftp.connect(server);
			
			if(FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
				boolean b = ftp.login(user, password);
				ftp.enterLocalPassiveMode();
				return b;
	        }
			else {
	            ftp.disconnect();  
	            return false;  
	        }
			
		} catch (IOException e) {
			ShowMessage.errorMessage(null, "Erro ao conectar-se ao servidor", "Houve um erro ao conectar-se ao servidor.\nPor favor, consulte o suporte");
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * Encerra a sessão FTP
	 */
	private void disconnect() {
		try {
			ftp.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Realiza o upload de um arquivo ao servidor FTP 
	 * 
	 * @param file O arquivo a ser realizado o upload para o servidor
	 * @param fileName O nome que será armazenador no servidor
	 * @param folder A pasta do servidor em que o arquivo será armazenado
	 * 
	 * @return true caso o upload seja bem sucedido e false caso haja algum erro
	 */
	public boolean upload(File file, String fileName, String folder) {
		
		try {
			
			if(!connect()) return false;
			
			FileInputStream fis = new FileInputStream(file);
			ftp.changeWorkingDirectory(FTPPath + folder);
			boolean b = ftp.storeFile(fileName, fis);
			
			disconnect();
			
			return b;
			
		} catch (IOException e) {
			ShowMessage.errorMessage(null, "Erro ao subir o arquivo", "Houve um erro ao subir o arquivo.\nPor favor, consulte o suporte");
			e.printStackTrace();
			return false;
		}
				
	}

	/**
	 * Realiza o download de um arquivo armazenado no servidor FTP
	 * 
	 * @param file A entidade que conterá o arquivo a ser baixado
	 * @param fileName O nome do arquivo a ser baixado
	 * @param folder A pasta que contém o arquivo a ser baixado
	 * 
	 * @return true caso o download seja bem sucedido e false caso haja algum erro
	 */
	public boolean download(File file,  String fileName, String folder) {
		
		try {
			
			if(!connect()) return false;
			
			ftp.changeWorkingDirectory(FTPPath + folder);
			FileOutputStream fos = new FileOutputStream(file);
			
			boolean b = ftp.retrieveFile(fileName, fos);
			
			disconnect();
			
			return b;
			
		} catch (IOException e) {
			ShowMessage.errorMessage(null, "Erro ao subir o arquivo", "Houve um erro ao subir o arquivo.\nPor favor, consulte o suporte");
			e.printStackTrace();
			return false;
		}
				
	}


}
