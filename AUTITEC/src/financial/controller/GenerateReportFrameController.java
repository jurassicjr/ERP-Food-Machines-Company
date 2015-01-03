package financial.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import userInterface.components.FileChooser;
import userInterface.components.filters.PDFFilter;
import util.HandlesFile;
import util.Html;
import util.HtmlToPdf;
import util.ShowMessage;
import database.DataBase;
import financial.view.GenerateReportFrame;

public class GenerateReportFrameController {
	
	public static final int BILLS_TO_PAY_AND_DEBTS_TO_RECEIVE = 0;
	public static final int BILLS_TO_PAY = 1;
	public static final int DEBTS_TO_RECEIVE = 2;
	
	private GenerateReportFrame frame;
	
	private DataBase dataBase = new DataBase();
	
	public GenerateReportFrameController(GenerateReportFrame frame) {
		
		this.frame = frame;
		
		dataBase = new DataBase();
		dataBase.connect();
	}
	
	public void closeFrame() {
		
		String title = "Cancelar o Relatório";
		String message = "Deseja realmente cancelar?\nO relatório não será criado";
		
		int response = ShowMessage.questionMessage(frame, title, message); 
		
		if(response == JOptionPane.YES_OPTION) {
			frame.dispose();			
		}		
		
	}

	public void selectOutput(FileChooser fileChooser, JTextField txReportFile) {
		
		fileChooser.showOpenDialog(new PDFFilter());
		
		if(fileChooser.hasSelectedFile()) {
			txReportFile.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}
					
	}
	
	public void generateReport(String reportFilePath, int bills, boolean includeOpenBills, boolean openFile, Date startDate, Date endDate) {
		
		if(!validateData(reportFilePath, bills, startDate, endDate)) return;
		
		String title = "";
		String content = "";
		
		if(bills == BILLS_TO_PAY) {
			
			title = "Relatório de Contas Pagas";
			content = setBillsToPay(includeOpenBills, startDate, endDate);
			
		}
				
		File output = new File(reportFilePath);
		Html html = new Html(output);
		html.createFile(title, content.toString());
		
		String s = html.getHtml();
				
		UUID id = UUID.randomUUID();
		File f = new File(id.toString());
		try { f.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
		HandlesFile.writeFile(f, s);
		
		HtmlToPdf.convert(f, output);
		
		f.delete();
		
		
		try { Desktop.getDesktop().open(output); } catch (IOException e) { e.printStackTrace(); }
		
		System.exit(0);
		
	}
	
	private String setBillsToPay(boolean includeOpenBills, Date startDate, Date endDate) {
				
		StringBuffer content = new StringBuffer();	
		
		String sql;
		Object data[] = null;
		
		if(includeOpenBills) sql = "SELECT * FROM bill, installment WHERE installment.bill = bill.id ORDER BY bill.id, installment.date;";
		else {
			
			sql = "SELECT * FROM bill, installment "
					+ "WHERE bill.id IN(SELECT bill FROM installment WHERE date BETWEEN ? AND ?) "
					+ "AND bill.id = installment.bill AND bill.payed = 0 "
					+ "ORDER BY bill.id, installment.date;";
			
			data = new Object[]{new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime())};
						
		}
				
		try{
			
			ResultSet resultSet = dataBase.executeQuery(sql, data);
			
			int lastId = -1;
			
			while(resultSet.next()) {
								
				int id = resultSet.getInt("id");
				String bill = resultSet.getString("bill");
				String creditor = resultSet.getString("creditor");
				String observations = resultSet.getString("observation");
								
				if(observations.isEmpty()) observations = "---";
				
				
				if(lastId != id) {
					
					ResultSet rs = dataBase.executeQuery("SELECT SUM(installment.value) as 'original_value', SUM(installment.payed_value) as 'payed_value' "
							+ "FROM bill, installment WHERE installment.bill = bill.id AND bill.id = ?;", id);
					rs.next();
					
					double originalValue = rs.getDouble("original_value");
					double payedValue = rs.getDouble("payed_value");
										
					content.append("<h2>Conta a Pagar: " + bill + "</h2>");
					content.append("<h3>Credor: " + creditor + "</h3>");
//					content.append("<h3>Status: Totalmente Paga</h3>");
//					content.append("<h3>Observações:</h3><p>" + observations + "</p>");
//					content.append("<h3>Valor Original: " +  NumberFormat.getCurrencyInstance().format(originalValue) + "</h3>");
//					content.append("<h3>Valor Pago: R$" + NumberFormat.getCurrencyInstance().format(payedValue) + "</h3>");
//					
				}
				
				//content.append("<p>Passou</p>");
				
				lastId = id;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
		return content.toString();
		
	}
	
	private boolean validateData(String reportFilePath, int bills, Date startDate, Date endDate) {
		
		boolean validate = false;
		String label = "";
		
		if(reportFilePath == null || reportFilePath.isEmpty()) label = "Conta a Pagar";
		else if(bills < 0) label = "Contas para o Relatório";
		else if(startDate == null) label = "Data Inicial";
		else if(endDate == null) label = "Data Final";
		else if(startDate.after(endDate) && !startDate.equals(endDate)) label = "Data Final deve ser posterior a data incial";
		else validate = true;
		
		if(!validate) {
			
			String title = "Erro criar relatório";
			String message = "Por favor verifique o seguinte campo para a criação do relatório:\n" + label;
			ShowMessage.errorMessage(frame, title, message);
			
		}
		
		return validate;
		
	}
	
}
