package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import userInterface.view.LoadingFrame;
import database.DataBase;

public class UpdateSoftware {
	
	public static final double VERSION = 0.6;
	
	private DataBase dataBase;
	
	public UpdateSoftware(LoadingFrame frame) {
		
		dataBase = new DataBase();
		dataBase.connect();
		
		boolean update = isUpdate();
		if(!update) {
			
			String title = "Atualização Disponível";
			String message = "Existe uma atualização Disponível.\n O sistema será reiniciado assim que a atualização estiver concluída";
			ShowMessage.successMessage(frame, title, message);
			
			frame.dispose();
															
			File f = download();
			
			extract(f);
			f.delete();
			
			try { Runtime.getRuntime().exec("update.bat"); }
			catch (IOException e) { e.printStackTrace(); }
			
			System.exit(0);
			
		}
		
	}
	
	public boolean isUpdate() {
		
		try {
						
			ResultSet resultSet = dataBase.executeQuery("select * from version where id = (select max(id) from version);");
			
			while(resultSet.next()) {
				
				double version = resultSet.getDouble("version");
								
				if(version > VERSION) return false;
				else return true;
				
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	private File download() {
		
		File localFile = null;
		
		try {
			
			URL url = new URL("http://pedrohreis.me/autitec.zip");
			localFile = new File("tmp.zip");
						
			InputStream inputStream = url.openStream();
			FileOutputStream fileOutputStream = new FileOutputStream(localFile);
			
			int b;
			while((b = inputStream.read()) != -1) {
				fileOutputStream.write(b);
			}
			
			inputStream.close();
			fileOutputStream.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			String title = "Erro ao Atualizar";
			String message = "Erro ao realizar o Download.\nPor favor, tente atualizar novamente";
			
			ShowMessage.errorMessage(null, title, message);
			System.exit(0);			
		}
		
		return localFile;
		
	}	
	
	private void extract(File f) {
				
		String destination = System.getProperty("user.dir");
		destination = destination + "\\tmp";
								
		try {
			
			ZipFile zipFile = new ZipFile(f);
			zipFile.extractAll(destination);
			
		} catch (ZipException e) {
			
			e.printStackTrace();
			
			String title = "Erro ao descompactar";
			String message = "Erro ao descompactar arquivo.\nPor favor, tente novamente";
			
			ShowMessage.errorMessage(null, title, message);
			System.exit(0);

		}
        
	}
	
}