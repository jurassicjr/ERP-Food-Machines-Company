package sales.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JTextField;

import model.Material;
import userInterface.components.FileChooser;
import userInterface.components.filters.PDFFilter;
import util.HandlesFile;
import util.Html;
import util.HtmlToPdf;
import util.ShowMessage;
import database.DataBase;

public class MaterialReportController extends SalesController {

	private List<Material> products;
	private DataBase dataBase;
	private JFrame frame;

	public MaterialReportController(JFrame frame) { 
		this.frame = frame;
		dataBase = new DataBase();
		dataBase.connect();
		setProducts();
	}

	private void setProducts() {
		products = new ArrayList<Material>();
		try (ResultSet rs = dataBase.executeQuery("Select *from Product")) {
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				String descrition = rs.getString("descricao");
				int ammount = rs.getInt("quantidade");
				Material p = new Material();
				p.setName(name);
				p.setDescrition(descrition);
				p.setId(id);
				p.setAmmount(ammount);
				products.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void selectOutput(FileChooser fileChooser, JTextField txtReportFile) {

		fileChooser.showSaveDialog(new PDFFilter());

		if (fileChooser.hasSelectedFile()) {

			String path = fileChooser.getSelectedFile().getAbsolutePath();
			if (!path.toUpperCase().endsWith(".PDF"))
				path = path += ".pdf";

			txtReportFile.setText(path);

		}
	}

	public void generateReport(String reportFilePath, boolean openFile) {
		File file = new File(reportFilePath);
		StringBuffer content = new StringBuffer();
		for (Material p : products) {

			content.append("<div style='page-break-after: always'>");
			
			content.append("<h2>Material: " + p.getName() + "</h2>");
			content.append("<h3>Descrição: " + p.getDescrition() + "</h3>");
			content.append("<h3>Quantidade em estoque: " + p.getAmmount() + "</h3>");

			if (p.equals(products.get(products.size() - 1))) {
				Date now = new Date();
				String formattedDate = new SimpleDateFormat().format(now);
				content.append("<br/><br/><hr /><small><i>Relatório criado em: " + formattedDate + "</i></small>");
			}
			content.append("</div>");
		}
		createPdf(reportFilePath, "Relatório de Funcionários", content.toString());

		if (openFile) {
			try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			ShowMessage.successMessage(frame, "Relatório Criado", "O relatório foi salvo em " + reportFilePath
			        + "\ncom sucesso");
		}
	}

	private File createPdf(String reportPathFile, String title, String content) {
		
		File output = new File(reportPathFile);
		Html html = new Html(output);
		html.createFile(title, content.toString());

		String s = html.getHtml();

		UUID id = UUID.randomUUID();
		File temp = new File(id.toString());

		try {
			temp.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		HandlesFile.writeFile(temp, s);

		HtmlToPdf.convert(temp, output);

		temp.delete();

		return output;

	}
}
