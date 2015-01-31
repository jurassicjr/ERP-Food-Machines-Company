package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import core.CBO;

/**
 * Responsável pelo preenchimento das funções e código CBO recuperados no banco de dados
 */
public class FillCBO {
	
	private JComboBox<CBO> cbo;
	private DataBase dataBase;
	
	/**
	 * Cria o objeto responsável por preencher as funções CBO
	 * 
	 * @param cbo O ComboBox responsável pelos cargos presentes na CBO
	 */
	public FillCBO(JComboBox<CBO> cbo) {
		
		this.cbo = cbo;
		
		dataBase = new DataBase();
		dataBase.connect();
		
		setCBO();		
	}
	
	/**
	 * Define no combobox os valores do CBO
	 */
	private void setCBO() {
		
		try {
		
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM cbo");
			
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String code = resultSet.getString("code");
				String title = resultSet.getString("title");
								
				cbo.addItem(new CBO(id, code, title));
			
			}
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
	}

}
