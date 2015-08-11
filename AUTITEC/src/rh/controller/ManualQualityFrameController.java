package rh.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.ManualQuality;
import model.ManualQualityVersion;
import model.TechnicalStandard;
import rh.view.ManualQualityFrame;
import rh.view.RegisterManualQualityFrame;
import util.FTP;
import util.ShowMessage;
import database.DataBase;
import database.dao.ManualQualityDAO;

public class ManualQualityFrameController {
	private ManualQualityFrame frame;
	private DataBase dataBase;
	private boolean hasRegistrationFrame;
	
	/**
	 * Cria o objeto para controle do frame do Manual de Qualidade
	 * 
	 * @param frame O frame do manual de qualidade
	 */
	public ManualQualityFrameController(ManualQualityFrame frame) {
		
		this.frame = frame;
		
		hasRegistrationFrame = false;
		
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	/**
	 * Retorna os manuais de qualidade registradas no sistema
	 */
	public ManualQuality[] getManualsQuality() {
		
		ArrayList<ManualQuality> manualQuality = new ArrayList<ManualQuality>();
		
		try {
			
			String sql = "SELECT manual_quality.id, manual_quality.manual_quality, "
					+ "manual_quality_version.id as 'id_version', manual_quality_version.file, manual_quality_version.date_update "
					+ "FROM manual_quality, manual_quality_version "
					+ "where manual_quality.id = manual_quality_version.manual_quality "
					+ "order by(manual_quality_version.manual_quality);";
			
			ResultSet resultSet = dataBase.executeQuery(sql);
						
			while(resultSet.next()) {
								
				int id = resultSet.getInt("id");
				String technicalStandard = resultSet.getString("manual_quality");
				
				ManualQuality mq = new ManualQuality(id, technicalStandard);
				manualQuality.add(mq);
				
				while(resultSet.getInt("id") == id) {
										
					int idVersion = resultSet.getInt("id_version");
					String filePath = resultSet.getString("file");
					Date date = resultSet.getDate("date_update");
					
					mq.addVersion(new ManualQualityVersion(idVersion, filePath, date));
					
					if(!resultSet.next()) break;
				}
				
				resultSet.previous();
				
			}
						
			ManualQuality mq[] = new ManualQuality[manualQuality.size()];
			return manualQuality.toArray(mq);
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
		return new ManualQuality[0];
		
	}

	/**
	 * Cria o frame para o registro de uma novo manual de qualidade
	 */
	public void addManualQuality(JTable table) {
		
		if(hasRegistrationFrame) return;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				RegisterManualQualityFrame registerFrame = new RegisterManualQualityFrame();
				registerFrame.getFrame().setVisible(true);
				registerFrame.getFrame().setLocationRelativeTo(frame);
				hasRegistrationFrame = true;
								
				synchronized (registerFrame) {
					
					try { registerFrame.wait(); }
					catch (InterruptedException e) { e.printStackTrace(); }
										
					registerFrame.getFrame().dispose();
					frame.requestFocus();
					hasRegistrationFrame = false;
					if(!registerFrame.isRegistered()) return;
					
					int id = -1;
					try {
						ResultSet resultSet = dataBase.executeQuery("SELECT * FROM manual_quality WHERE id = (SELECT max(id) FROM manual_quality);");
						resultSet.next();
						id = resultSet.getInt("id");					
					} catch(SQLException e) {
						e.printStackTrace();
						DataBase.showDataBaseErrorMessage();
					}
					
					ManualQuality mq = new ManualQualityDAO().factory(id);
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int row = model.getRowCount();
					model.addRow(new Object[]{null, null, null, null, null});
							 
					table.setValueAt(mq, row, 0);
					table.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())), row, 1);
					table.setValueAt("Visualizar", row, 2);
					table.setValueAt("Atualizar", row, 3);
					table.setValueAt("Remover", row, 4);
					
				}
				
			}
			
			
		}).start();
		
	}

	/**
	 * Evento para o click de um dos botões de célula do jtable
	 * 
	 * @param row A linha clicada
	 * @param column A coluna clicada
	 * @param table A tabela do evento
	 */
	public void tableButtonClick(int row, int column, JTable table) {
		
		if(column == 2) showFile(row, column, table);
		else if(column == 3) updateManualQuality(row, table);
		else if(column == 4) deleteManualQuality((ManualQuality) table.getValueAt(row, 0), table, row);				
		
	}
	
	/**
	 * Abre o arquivo da norma técnica para o usuário
	 * 
	 * @param row A linha clicada
	 * @param column A coluna clicada
	 * @param table A tabela do evento
	 */
	private void showFile(int row, int column, JTable table) {
				
		File f = new File("tmp/");
		if(!f.isDirectory()) f.mkdirs();
		
		TechnicalStandard ts = (TechnicalStandard) table.getValueAt(row, 0);
		String fileName = ts.getLastUpdate().getFileName();
					
		File document = new File("tmp/" + fileName);
		
		if(!document.exists()) {
			FTP ftp = new FTP();
			boolean b = ftp.download(document, fileName, "/Manual_de_Qualidade/");
			System.out.println("aqui " + b);
		}
				
		try {			
			Desktop desktop = Desktop.getDesktop();
			desktop.open(document);
		} catch (IOException e) {
			ShowMessage.errorMessage(frame, "Erro ao Abrir Manual de Qualidade", "Houve um erro ao abrir o Manual de Qualidade\nPor favor consulte o suporte");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Remove um Manual de Qualidade
	 * 
	 * @param manualQuality o manual de qualidade a ser removido
	 * @param table A tabela que contém os manuais de qualidade
	 */
	private void deleteManualQuality(ManualQuality manualQuality, JTable table, int row) {
		
		if(!confirmDeleteManualQuality()) return;
				
		int id = manualQuality.getId();
		
		dataBase.executeUpdate("DELETE FROM manual_quality_version WHERE manual_quality = ?", id);
		dataBase.executeUpdate("DELETE FROM manual_quality WHERE id = ?", id);
		
		String fileName = manualQuality.getLastUpdate().getFileName();
		
		FTP ftp = new FTP();
		ftp.deleteFile(fileName, "/autitec/docs/");
		
		((DefaultTableModel) table.getModel()).removeRow(row);
		
		ShowMessage.successMessage(frame, "Manual de Qualidade Removido", "Manual de qualidade removido com sucesso");
		
	}
	
	/**
	 * Confirma a remoção de um manual de qualidade
	 * 
	 * @return true caso a remoção seja confirmada e false se a remoção não foi confirmada
	 */
	private boolean confirmDeleteManualQuality() {
		
		int respose = ShowMessage.questionMessage(frame, "Remover Manual de Qualidade", "Deseja realmente remover este Manual de Qualidade?");
		return respose == JOptionPane.YES_OPTION;
				
	}
	
	/**
	 * Registra uma nova versão do manual de qualidade
	 * 
	 * @return true se uma nova versão foi registrada e false caso contrário
	 */
	private void updateManualQuality(int row, JTable table) {
		
		if(hasRegistrationFrame) return;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				ManualQuality manualQuality = (ManualQuality) table.getValueAt(row, 0);
				
				RegisterManualQualityFrame registerFrame = new RegisterManualQualityFrame(manualQuality);
				registerFrame.getFrame().setVisible(true);
				registerFrame.getFrame().setLocationRelativeTo(frame);
				hasRegistrationFrame = true;
								
				synchronized (registerFrame) {
					
					try { registerFrame.wait(); }
					catch (InterruptedException e) { e.printStackTrace(); }
					
					registerFrame.getFrame().dispose();
					frame.requestFocus();
					hasRegistrationFrame = false;
					
					if(!registerFrame.isRegistered()) return;
					table.setValueAt(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())), row, 1);		
				
				}
				
			}
			
		}).start();
				
	}
}
