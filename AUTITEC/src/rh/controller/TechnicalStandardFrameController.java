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

import model.TechnicalStandard;
import model.TechnicalStandardVersion;
import rh.view.RegisterTechnicalStandardFrame;
import rh.view.TechnicalStandardFrame;
import util.FTP;
import util.ShowMessage;
import database.DataBase;
import database.dao.TechnicalStandardDAO;

/**
 * Responsável por controlar o frame de normas técnicas
 */
public class TechnicalStandardFrameController {
	
	private TechnicalStandardFrame frame;
	private DataBase dataBase;
	private boolean hasRegistrationFrame;
	
	/**
	 * Cria o objeto para controle do frame de Normas Técnicas
	 * 
	 * @param frame O frame de Normas Técnicas
	 */
	public TechnicalStandardFrameController(TechnicalStandardFrame frame) {
		
		this.frame = frame;
		
		hasRegistrationFrame = false;
		
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	/**
	 * Retorna as normas técnicas registradas no sistema
	 */
	public TechnicalStandard[] getTechnicalStandards() {
		
		ArrayList<TechnicalStandard> technicalStandards = new ArrayList<TechnicalStandard>();
		
		try {
			
			String sql = "SELECT technical_standard.id, technical_standard.technical_standard, "
					+ "technical_standard_version.id as 'id_version', technical_standard_version.file, technical_standard_version.date_update "
					+ "FROM technical_standard, technical_standard_version "
					+ "where technical_standard.id = technical_standard_version.technical_standard "
					+ "order by(technical_standard_version.technical_standard);";
			
			ResultSet resultSet = dataBase.executeQuery(sql);
						
			while(resultSet.next()) {
								
				int id = resultSet.getInt("id");
				String technicalStandard = resultSet.getString("technical_standard");
				
				TechnicalStandard ts = new TechnicalStandard(id, technicalStandard);
				technicalStandards.add(ts);
				
				while(resultSet.getInt("id") == id) {
										
					int idVersion = resultSet.getInt("id_version");
					String filePath = resultSet.getString("file");
					Date date = resultSet.getDate("date_update");
					
					ts.addVersion(new TechnicalStandardVersion(idVersion, filePath, date));
					
					if(!resultSet.next()) break;
				}
				
				resultSet.previous();
				
			}
						
			TechnicalStandard ts[] = new TechnicalStandard[technicalStandards.size()];
			return technicalStandards.toArray(ts);
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
		return new TechnicalStandard[0];
		
	}

	/**
	 * Cria o frame para o registro de uma nova norma técnica
	 */
	public void addTechnicalStandard(JTable table) {
		
		if(hasRegistrationFrame) return;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				RegisterTechnicalStandardFrame registerFrame = new RegisterTechnicalStandardFrame();
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
						ResultSet resultSet = dataBase.executeQuery("SELECT * FROM technical_standard WHERE id = (SELECT max(id) FROM technical_standard);");
						resultSet.next();
						id = resultSet.getInt("id");					
					} catch(SQLException e) {
						e.printStackTrace();
						DataBase.showDataBaseErrorMessage();
					}
					
					TechnicalStandard ts = new TechnicalStandardDAO().factory(id);
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int row = model.getRowCount();
					model.addRow(new Object[]{null, null, null, null, null});
							 
					table.setValueAt(ts, row, 0);
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
		else if(column == 3) updateTechnicalStandard(row, table);
		else if(column == 4) deleteTechnicalStandard((TechnicalStandard) table.getValueAt(row, 0), table, row);				
		
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
			ftp.download(document, fileName, "/autitec/docs/");
		}
		
		try {
			Desktop desktop = Desktop.getDesktop();
			desktop.open(document);
		} catch (IOException e) {
			ShowMessage.errorMessage(frame, "Erro ao Abrir Norma Técnica", "Houve um erro ao abrir a norma ténica\nPor favor consulte o suporte");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Remove uma norma técnica
	 * 
	 * @param technicalStandard A norma técnica a ser removida
	 * @param table A tabela que contém as normas técnicas
	 */
	private void deleteTechnicalStandard(TechnicalStandard technicalStandard, JTable table, int row) {
		
		if(!confirmDeleteTechnicalStandard()) return;
				
		int id = technicalStandard.getId();
		
		dataBase.executeUpdate("DELETE FROM technical_standard_version WHERE technical_standard = ?", id);
		dataBase.executeUpdate("DELETE FROM technical_standard WHERE id = ?", id);
		
		String fileName = technicalStandard.getLastUpdate().getFileName();
		
		FTP ftp = new FTP();
		ftp.deleteFile(fileName, "/autitec/docs/");
		
		((DefaultTableModel) table.getModel()).removeRow(row);
		
		ShowMessage.successMessage(frame, "Norma Técnica Removida", "Norma Técnica removida com sucesso");
		
	}
	
	/**
	 * Confirma a remoção de uma Norma Técnica
	 * 
	 * @return true caso a remoção seja confirmada e false se a remoção não foi confirmada
	 */
	private boolean confirmDeleteTechnicalStandard() {
		
		int respose = ShowMessage.questionMessage(frame, "Remover Norma Técnica", "Deseja realmente remover esta Norma Técnica?");
		return respose == JOptionPane.YES_OPTION;
				
	}
	
	/**
	 * Registra uma nova versão da norma técnica
	 * 
	 * @return true se uma nova versão foi registrada e false caso contrário
	 */
	private void updateTechnicalStandard(int row, JTable table) {
		
		if(hasRegistrationFrame) return;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				TechnicalStandard technicalStandard = (TechnicalStandard) table.getValueAt(row, 0);
				
				RegisterTechnicalStandardFrame registerFrame = new RegisterTechnicalStandardFrame(technicalStandard);
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
