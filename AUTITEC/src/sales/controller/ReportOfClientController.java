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

import model.City;
import model.Client;
import model.State;
import userInterface.components.FileChooser;
import userInterface.components.filters.PDFFilter;
import util.HandlesFile;
import util.Html;
import util.HtmlToPdf;
import util.ShowMessage;
import database.DataBase;

public class ReportOfClientController extends SalesController {

	private List<Client> clientList;
	private JFrame frame;
	private DataBase dataBase;

	public ReportOfClientController(JFrame frame) {
		this.frame = frame;
		dataBase = new DataBase();
		dataBase.connect();
		setClients();
	}

	private void setClients() {
		clientList = new ArrayList<Client>();
		try (ResultSet rs = dataBase.executeQuery("Select *from client")) {
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				String neighborhood = rs.getString("neighborhood");
				String street = rs.getString("street");
				int idState = rs.getInt("state");
				int idCity = rs.getInt("city");
				try (ResultSet stateRs = dataBase.executeQuery("SELECT *FROM state WHERE id = ?", idState)) {
					if (stateRs.next()) {
						State state = new State(rs.getInt("id"), rs.getString("name"));
						try (ResultSet cityId = dataBase.executeQuery("SELECT *FROM city WHERE id = ?", idCity)) {
							if (cityId.next()) {
								City city = new City(cityId.getInt("id"), rs.getString("name"), state);
								Client c = new Client();
								c.setName(name);
								c.setNeighborhood(neighborhood);
								c.setState(state);
								c.setCity(city);
								c.setCep(rs.getString("cep"));
								//c.setCnpj(rs.getString("cnpj"));
								c.setEmail(rs.getString("email"));
								c.setPhone(rs.getString("phone"));
								c.setId(id);
								clientList.add(c);
							}
						}
					}
				}
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
		for (Client p : clientList) {

			content.append("<div style='page-break-after: always'>");

			content.append("<h2>Client: " + p.getName() + "</h2>");
			content.append("<h3>Informaçoes de Cadastro: </h3>");
			content.append("<h4>CNPJ: " + p.getCnpj() + "</h4>");
			content.append("<h4>Inscrição Estadual: " + p.getStateInscrition() + "</h4>");
			content.append("<h3>Contato: </h3>");
			content.append("<h4>Telefone: " + p.getPhone() + "</h4>");
			content.append("<h4>E-mail: " + p.getEmail() + "</h4>");
			content.append("<h4>Rua: " + p.getStreet() + "</h4>");
			content.append("<h4>Bairro: " + p.getNeighborhood() + "</h4>");
			content.append("<h4>Cidade: " + p.getCity().getName() + "</h4>");
			content.append("<h4>Estado: " + p.getState().getName() + "</h4>");
			content.append("<h4>CEP: " + p.getCep() + "</h4>");

			if (p.equals(clientList.get(clientList.size() - 1))) {
				Date now = new Date();
				String formattedDate = new SimpleDateFormat().format(now);
				content.append("<br/><br/><hr /><small><i>Relatório criado em: " + formattedDate + "</i></small>");
			}
			content.append("</div>");
		}
		createPdf(reportFilePath, "Relatório de Clientes", content.toString());

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
