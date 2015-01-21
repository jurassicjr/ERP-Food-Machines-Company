import java.sql.ResultSet;
import java.sql.SQLException;

import userInterface.view.UpdateSoftware;
import database.DataBase;

/**
 * Classe principal, responsável por inicializar a aplicação.
 */
public class Main {
	
	public static final double VERSION = 0.1;

	public static void main(String[] args) {
			
//		LoadingFrame loadingFrame = new LoadingFrame();  
//	
//		SwingUtilities.invokeLater(new Runnable(){  
//			@Override public void run() { loadingFrame.setVisible(true); }  
//		});
		
		
		try {
			
			DataBase d = new DataBase();
			d.connect();
			
			ResultSet resultSet = d.executeQuery("select * from version where id = (select max(id) from version);");
			while(resultSet.next()) {
				double version = resultSet.getDouble("version");
				if(version < VERSION) {
					UpdateSoftware us = new UpdateSoftware();
				}
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		//new LoginFrame().setVisible(true);
		//new MainFrame().setVisible(true);
		//new RegisterKitFrame().setVisible(true);
		
		//new StagesProductionFrame().setVisible(true);
		
		//loadingFrame.dispose();
				
	}

}
