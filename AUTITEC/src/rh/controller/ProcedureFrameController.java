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

import model.Procedure;
import model.ProcedureVersion;
import rh.view.ProcedureFrame;
import rh.view.RegisterProcedureFrame;
import util.FTP;
import util.ShowMessage;
import database.DataBase;
import database.dao.ProcedureDAO;

public class ProcedureFrameController {

	private ProcedureFrame frame;
	private DataBase dataBase;
	private boolean hasRegistrationFrame;
	
	/**
	 * Cria o objeto para controle do frame de procedimentos
	 * 
	 * @param frame O frame do procedimento
	 */
	public ProcedureFrameController(ProcedureFrame frame) {
		
		this.frame = frame;
		
		hasRegistrationFrame = false;
		
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	/**
	 * Retorna as normas técnicas registradas no sistema
	 */
	public Procedure[] getTechnicalStandards() {
		
		ArrayList<Procedure> Procedures = new ArrayList<Procedure>();
		
		try {
			
			String sql = "SELECT procedure.id, procedure.procedure, "
					+ "procedure_version.id as 'id_version', procedure_version.file, procedure_version.date_update "
					+ "FROM `procedure`, procedure_version "
					+ "where procedure.id = procedure_version.procedure "
					+ "order by(procedure_version.procedure);";
			
			ResultSet resultSet = dataBase.executeQuery(sql);
						
			while(resultSet.next()) {
								
				int id = resultSet.getInt("id");
				String procedure = resultSet.getString("procedure");
				
				Procedure pc = new Procedure(id, procedure);
				Procedures.add(pc);
				
				while(resultSet.getInt("id") == id) {
										
					int idVersion = resultSet.getInt("id_version");
					String filePath = resultSet.getString("file");
					Date date = resultSet.getDate("date_update");
					
					pc.addVersion(new ProcedureVersion(idVersion, filePath, date));
					
					if(!resultSet.next()) break;
				}
				
				resultSet.previous();
				
			}
						
			Procedure procedure[] = new Procedure[Procedures.size()];
			return Procedures.toArray(procedure);
			
		} catch (SQLException e) {
			DataBase.showDataBaseErrorMessage();
			e.printStackTrace();
		}
		
		return new Procedure[0];
		
	}

	/**
	 * Cria o frame para o registro de um novo procedimento.
	 */
	public void addProcedure(JTable table) {
		
		if(hasRegistrationFrame) return;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				RegisterProcedureFrame registerFrame = new RegisterProcedureFrame();
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
						ResultSet resultSet = dataBase.executeQuery("SELECT * FROM `procedure` WHERE id = (SELECT max(id) FROM `procedure`);");
						resultSet.next();
						id = resultSet.getInt("id");					
					} catch(SQLException e) {
						e.printStackTrace();
						DataBase.showDataBaseErrorMessage();
					}
					
					Procedure ts = new ProcedureDAO().factory(id);
					
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
		else if(column == 3) updateProcedure(row, table);
		else if(column == 4) deleteProcedure((Procedure) table.getValueAt(row, 0), table, row);				
		
	}
	
	/**
	 * Abre o arquivo do procedimento para o usuário
	 * 
	 * @param row A linha clicada
	 * @param column A coluna clicada
	 * @param table A tabela do evento
	 */
	private void showFile(int row, int column, JTable table) {
				
		File f = new File("tmp/");
		if(!f.isDirectory()) f.mkdirs();
		
		Procedure pc = (Procedure) table.getValueAt(row, 0);
		String fileName = pc.getLastUpdate().getFileName();
					
		File document = new File("tmp/" + fileName);
		
		if(!document.exists()) {
			FTP ftp = new FTP();
			boolean b = ftp.download(document, fileName, "/Procedimento/");
			System.out.println("aqui " + b);
		}
				
		try {			
			Desktop desktop = Desktop.getDesktop();
			desktop.open(document);
		} catch (IOException e) {
			ShowMessage.errorMessage(frame, "Erro ao Abrir Procedimento", "Houve um erro ao abrir o procedimento\nPor favor consulte o suporte");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Remove um procedimento
	 * 
	 * @param procedure O procedimento a ser removido
	 * @param table A tabela que contém os procedimentos
	 */
	private void deleteProcedure(Procedure procedure, JTable table, int row) {
		
		if(!confirmDeleteProcedure()) return;
				
		int id = procedure.getId();
		
		dataBase.executeUpdate("DELETE FROM procedure_version WHERE `procedure` = ?", id);
		dataBase.executeUpdate("DELETE FROM `procedure` WHERE id = ?", id);
		
		String fileName = procedure.getLastUpdate().getFileName();
		
		FTP ftp = new FTP();
		ftp.deleteFile(fileName, "/autitec/docs/");
		
		((DefaultTableModel) table.getModel()).removeRow(row);
		
		ShowMessage.successMessage(frame, "Procedimento Removido", "Procedimento removido com sucesso");
		
	}
	
	/**
	 * Confirma a remoção de um procedimento
	 * 
	 * @return true caso a remoção seja confirmada e false se a remoção não foi confirmada
	 */
	private boolean confirmDeleteProcedure() {
		
		int respose = ShowMessage.questionMessage(frame, "Remover Procedimento", "Deseja realmente remover este procedimento?");
		return respose == JOptionPane.YES_OPTION;
				
	}
	
	/**
	 * Registra uma nova versão do procedimento
	 * 
	 * @return true se uma nova versão foi registrada e false caso contrário
	 */
	private void updateProcedure(int row, JTable table) {
		
		if(hasRegistrationFrame) return;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				Procedure procedure = (Procedure) table.getValueAt(row, 0);
				
				RegisterProcedureFrame registerFrame = new RegisterProcedureFrame(procedure);
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
