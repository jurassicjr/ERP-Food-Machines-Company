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
	
	public static final String TAB = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	
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
			
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			if(!path.toUpperCase().endsWith(".PDF")) path = path += ".pdf";
				
			txReportFile.setText(path);
		}
					
	}
	
	public void generateReport(String reportFilePath, int bills, boolean includeOpenBills, boolean openFile, Date startDate, Date endDate) {
		
		if(!validateData(reportFilePath, bills, startDate, endDate)) return;
				
		String title = "";
		String content = "";
		
		if(bills == BILLS_TO_PAY) {
			title = "Relatório de Contas a Pagar";
			content = setBillsToPay(includeOpenBills, startDate, endDate);
		}
		else if(bills == DEBTS_TO_RECEIVE) {
			title = "Relatório de Contas a Receber";
			content = setDebtsToPay(includeOpenBills, startDate, endDate);
		}
		else if(bills == BILLS_TO_PAY_AND_DEBTS_TO_RECEIVE) {
			
			title = "Relatório de Contas a Pagar e a Receber";
			
			content = setBillsToPay(includeOpenBills, startDate, endDate);
			content += setDebtsToPay(includeOpenBills, startDate, endDate);
		}
		
		Date now = new Date();
		String formattedDate = new SimpleDateFormat().format(now);
		content = content + "<br /><br /><hr /><small><i>Relatório criado em: " + formattedDate + "</i></small>";
		
		File output = createPdf(reportFilePath, title, content);
		
		if(openFile) {
			
			try {
				Desktop.getDesktop().open(output);
			} catch (IOException e) {
				e.printStackTrace(); 
			}
			
		}
		else {
			ShowMessage.successMessage(frame, "Relatório Criado", "O relatório " + reportFilePath + "\nfoi criado com sucesso");
		}
		
		frame.dispose();
				
	}
	
	private String setBillsToPay(boolean includeOpenBills, Date startDate, Date endDate) {
				
		StringBuffer content = new StringBuffer();	
		
		String sql;
		Object data[] = null;
		
		if(includeOpenBills) {
			sql = "SELECT * FROM bill, installment "
					+ "WHERE bill.id IN(SELECT bill FROM installment WHERE date BETWEEN ? AND ?) AND bill.id = installment.bill "
					+ "ORDER BY bill.id, installment.date;";
		}
		else {
			sql = "SELECT * FROM bill, installment "
					+ "WHERE bill.id IN(SELECT bill FROM installment WHERE date BETWEEN ? AND ?) "
					+ "AND bill.id = installment.bill AND bill.payed = 1 "
					+ "ORDER BY bill.id, installment.date;";				
		}
		
		data = new Object[]{new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime())};
				
		try{
			
			ResultSet resultSet = dataBase.executeQuery(sql, data);
			int lastId = -1;
			boolean first = true;
			int installmentId = 0;
			
			while(resultSet.next()) {
								
				int id = resultSet.getInt("id");
				String bill = resultSet.getString("bill");
				String creditor = resultSet.getString("creditor");
				String observations = resultSet.getString("observation");
								
				if(observations.isEmpty()) observations = "---";
				
				if(lastId != id) {
					
					ResultSet rs = dataBase.executeQuery("SELECT SUM(installment.value) as 'original_value', SUM(installment.payed_value) as 'payed_value', "
							+ "EXISTS(SELECT * FROM installment WHERE installment.bill = ? AND installment.paid = 0) as 'status' "
							+ "FROM bill, installment WHERE installment.bill = bill.id AND bill.id = ?;", new Object[]{id, id});
					rs.next();
					
					double originalValue = rs.getDouble("original_value");
					double payedValue = rs.getDouble("payed_value");
					boolean status = (rs.getInt("status") == 1) ? true : false;
					
					if(!first) content.append("</div>");
					
					content.append("<div style='page-break-after: always'>");
					content.append("<h2>Conta a Pagar: " + bill + "</h2>");
					content.append("<h3>Credor: " + creditor + "</h3>");
					if(status) content.append("<h3>Status: Parcialmente Paga</h3>");
					else content.append("<h3>Status: Totalmente Paga</h3>");
					content.append("<h3>Observações:</h3><p>" + observations + "</p>");
					content.append("<h3>Valor Original: " +  NumberFormat.getCurrencyInstance().format(originalValue) + "</h3>");
					content.append("<h3>Valor Pago: " + NumberFormat.getCurrencyInstance().format(payedValue) + "</h3>");
					content.append("<h3>Parcelas:</h3>");
					
					first = false;
					installmentId = 1;
					
				}
				
				double value = resultSet.getDouble("value");
				Date date = resultSet.getDate("date");
				boolean paid = resultSet.getInt("paid") == 1;
					
				content.append("<h4>Parcela " + installmentId + "</h4>");
				content.append("<p>Data de Vencimento: " + new SimpleDateFormat("dd/MM/yyyy").format(date) + TAB);
				content.append("Valor: " + NumberFormat.getCurrencyInstance().format(value) + "</p>");
				
				if(paid) {
					
					Date paymentDate = resultSet.getDate("payment_date");
					Double paymentValue = resultSet.getDouble("payed_value");
					
					content.append("<p>Data de Pagamento: " + new SimpleDateFormat("dd/MM/yyyy").format(paymentDate) + TAB);
					content.append("Valor Pago: " + NumberFormat.getCurrencyInstance().format(paymentValue) + "</p>");
					
				}
					
				++installmentId;
				lastId = id;
				
			}
			
			content.append("</div>");
			
		} catch (SQLException e) {
			e.printStackTrace();
			DataBase.showDataBaseErrorMessage();
		}
		
		return content.toString();
		
	}
	
	private String setDebtsToPay(boolean includeOpenBills, Date startDate, Date endDate) {
		
		StringBuffer content = new StringBuffer();
		
		String sql;
		Object data[] = null;
		
		if(includeOpenBills) sql = "SELECT * FROM debt WHERE id IN (SELECT id FROM debt WHERE date BETWEEN ? and ?)";
		else sql = "SELECT * FROM debt WHERE id IN (SELECT id FROM debt WHERE date BETWEEN ? and ?) AND paid = 1";
		
		data = new Object[]{new java.sql.Date(startDate.getTime()), new java.sql.Date(endDate.getTime())};
		
		try {
			
			ResultSet resultSet = dataBase.executeQuery(sql, data);
			
			while(resultSet.next()) {
				
				String debt = resultSet.getString("debt");
				String debtor = resultSet.getString("debtor");
				boolean paid = resultSet.getBoolean("paid");
				String observation = resultSet.getString("observation");
				double originalValue = resultSet.getDouble("value");
				Date date = resultSet.getDate("date");
				
				if(observation.isEmpty()) observation = "---";
				
				content.append("<h2>Conta a Receber: " + debt + "</h2>");
				content.append("<h3>Devedor: " + debtor + "</h3>");
				if(paid) content.append("<h3>Status: Conta Recebida</h3>");
				else content.append("<h3>Status: Conta a Receber</h3>");
				content.append("<h3>Observações:</h3><p>" + observation + "</p>");
				
				content.append("<p>Data de Vencimento: " + new SimpleDateFormat("dd/MM/yyyy").format(date) + TAB);
				content.append("Valor: " + NumberFormat.getCurrencyInstance().format(originalValue) + "</p>");
				
				if(paid) {
					
					Date paymentDate = resultSet.getDate("payment_date");
					double payedValue = resultSet.getDouble("payed_value");
					
					content.append("<p>Data de Recebimento: " + new SimpleDateFormat("dd/MM/yyyy").format(paymentDate) + TAB);
					content.append("Valor: " + NumberFormat.getCurrencyInstance().format(payedValue) + "</p>");
										
				}
				
			}
			
		} catch(SQLException e) {
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

	private File createPdf(String reportFilePath, String title, String content) {
		
		File output = new File(reportFilePath);
		Html html = new Html(output);
		html.createFile(title, content.toString());
		
		String s = html.getHtml();
				
		UUID id = UUID.randomUUID();
		File temp = new File(id.toString());
		
		try { temp.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
		HandlesFile.writeFile(temp, s);
		
		HtmlToPdf.convert(temp, output);
		
		temp.delete();	
		
		return output;
		
	}
	
}
