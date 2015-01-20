package production.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.ProductionStage;
import production.view.StagesProductionFrame;
import database.DataBase;

public class StagesProductionFrameController {
	
	private StagesProductionFrame frame;
	
	private DataBase dataBase;
	
	public StagesProductionFrameController(StagesProductionFrame frame) {
		
		this.frame = frame;
		
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void setProduction(JTextField productionName, JTable openStages, JTable closeStages) {
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery("SELECT * FROM production");
			
			while(resultSet.next()) {
				
				String production = resultSet.getString("production");				
				productionName.setText(production);
			}
			
			resultSet = dataBase.executeQuery("SELECT * FROM stage");
				
			while(resultSet.next()) {
				
				int id = resultSet.getInt("id");
				String stage = resultSet.getString("stage");
				boolean finalized = resultSet.getBoolean("finalized");
				java.sql.Date finalizedDate = resultSet.getDate("finalized_date");
				
				ProductionStage productionStage = new ProductionStage(id, stage, finalized, finalizedDate);
				
				if(productionStage.isFinalized()) {
					
					int row = closeStages.getRowCount();
					
					((DefaultTableModel) closeStages.getModel()).addRow(new Object[]{null, null});
					
					closeStages.setValueAt(productionStage.getStage(), row, 0);
					closeStages.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(productionStage.getFinalizedDate()), row, 1);
				}
				else {
					
					int row = openStages.getRowCount();
					
					((DefaultTableModel) openStages.getModel()).addRow(new Object[]{null, null});
					
					openStages.setValueAt(productionStage.getStage(), row, 0);
				}
												
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
			return;
		}	
				
	}
	
	public void finalizeStage(JTable openStages, JTable closeStages, int row) {
		
		int r = closeStages.getRowCount();
		
		((DefaultTableModel) closeStages.getModel()).addRow(new Object[]{null, null});
		
		String s = (String) openStages.getValueAt(row, 0);
		
		closeStages.setValueAt(s, r, 0);
		closeStages.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(new Date()), r, 1);

		((DefaultTableModel) openStages.getModel()).removeRow(row);	
		
	}

}
