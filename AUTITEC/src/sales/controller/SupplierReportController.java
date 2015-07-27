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

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import model.City;
import model.State;
import model.Supplier;
import sales.view.report.SupplierReportFrame;
import userInterface.components.FileChooser;
import userInterface.components.filters.PDFFilter;
import util.HandlesFile;
import util.Html;
import util.HtmlToPdf;
import util.ShowMessage;
import database.DataBase;

public class SupplierReportController extends SalesController {
	private DataBase dataBase;
	private List<Supplier> s = new ArrayList<Supplier>();
	private City city;
	public static final String TAB = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	private SupplierReportFrame frame;

	public SupplierReportController(SupplierReportFrame frame) {
		this.frame = frame;
		dataBase = new DataBase();
		dataBase.connect();
		setSuppliers();
	}

	private void setSuppliers() {
		try {
			ResultSet rs = dataBase.executeQuery("SELECT *FROM suppliers");
			while (rs.next()) {
				String razaoSocial = rs.getString("corporate_name");
				String CNPJ = rs.getString("CNPJ");

				Supplier supplier = new Supplier(razaoSocial, CNPJ);

				supplier.setCep(rs.getString(15));
				supplier.setCertificated(rs.getBoolean("certificate"));
				supplier.setEmail(rs.getString("email"));
				supplier.setExpireCertificateDate(rs
						.getDate("expireCertificationDate"));
				supplier.setFiscalClassification(rs
						.getString("fical_classification"));
				supplier.setId(rs.getInt("id"));
				supplier.setJustificative(rs.getString("justificative"));
				supplier.setMaterialCertication(rs
						.getBoolean("material_certificate"));
				supplier.setNeighborhood(rs.getString("neighborhood"));
				supplier.setPhone(rs.getString("phone"));
				supplier.setStateRegistration(rs
						.getString("state_registration"));
				supplier.setStreet(rs.getString("street"));
				ResultSet rsState = dataBase.executeQuery(
						"SELECT *FROM state WHERE id = ?", rs.getInt("state"));
				while (rsState.next()) {
					int id = rsState.getInt("id");
					String name = rsState.getString("name");
					State state = new State(id, name);
					ResultSet rsCity = dataBase
							.executeQuery("SELECT *FROM city where id = ?",
									rs.getInt("city"));
					while (rsCity.next()) {
						int idCity = rsCity.getInt("id");
						String nameCity = rsCity.getString("name");
						city = new City(idCity, nameCity, state);
					}
					rsCity.close();
					supplier.setCityState(city, state);
				}
				s.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fillTable(JTable table) {
		DefaultTableModel tbl = (DefaultTableModel) table.getModel();
		String sql = "SELECT *FROM suppliers";
		ResultSet rs = dataBase.executeQuery(sql);
		try {
			while (rs.next()) {
				String companyName = rs.getString("corporate_name");
				String CNPJ = rs.getString("CNPJ");
				String stateRegistration = rs.getString("state_registration");
				String materialCertification;
				String certificate;
				if (rs.getBoolean("material_certificate")) {
					materialCertification = "Sim";
				} else {
					materialCertification = "Não";
				}
				if (rs.getBoolean("certificate")) {
					certificate = "Sim";
				} else {
					certificate = "Não";
				}
				tbl.addRow(new String[] { companyName, CNPJ, stateRegistration,
						materialCertification, certificate });
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

	public void generateReport(String reportPathFile, boolean openFile)
			throws JRException, IOException {
		// direcionar para a pasta resources/filesReportsTemplate 
		JasperPrint print = JasperFillManager.fillReport(
				"/resources/filesReportsTemplate/reportSuppliersHorizontal.jasper", null,
				dataBase.getConnection());
		if (openFile) {
			JasperViewer view = new JasperViewer(print);
			view.setVisible(true);
		} else {
			JasperExportManager.exportReportToPdfFile(print, reportPathFile);
			ShowMessage.successMessage(frame, "Relatório Criado",
					"O relatório foi salvo em " + reportPathFile
							+ "\ncom sucesso");
		}

	}

//	public void generateReport(String reportPathFile, boolean openFile) {
//		File file = new File(reportPathFile);
//		StringBuffer content = new StringBuffer();
//		for (Supplier supplier : s) {
//
//			content.append("<div style='page-break-after: always'>");
//
//			content.append("<h2>Fornecedor: " + supplier.getCompanyName()
//					+ "</h2>");
//			content.append("<h3>INFORMAÇÕES:</h3>");
//			content.append("<h4>Telefone: " + supplier.getFormattedPhone()
//					+ "</h4>");
//			content.append("<h4>email: " + supplier.getEmail() + "</h4>");
//			content.append("<h4>CNPJ: " + supplier.getFormattedCNPJ() + "</h4>");
//			content.append("<h4>Inscrição Estadual: "
//					+ supplier.getStateRegistration() + "</h4>");
//			content.append("<h4>Classificação Fiscal: "
//					+ supplier.getFiscalClassification() + "</h4>");
//			if (supplier.isCertificated()) {
//				content.append("<h4>Certificado: ISO9001:2008, "
//						+ TAB
//						+ "Data de Expiração: "
//						+ new SimpleDateFormat("dd/MM/yyyy").format(supplier
//								.getExpireCertificateDate()) + "</h4>");
//			} else {
//				content.append("<h4>Fornecedor sem certificado" + "</h4>");
//			}
//			if (supplier.isMaterialCertication()) {
//				content.append("<h4>Possui certificado do material: SIM"
//						+ "</h4>");
//			} else {
//				content.append("<h4>Possui certificado do material: NÃO"
//						+ "</h4>");
//			}
//			content.append("<h3>Endereço:</h3>");
//			content.append("<h4>");
//			content.append("<br/>" + TAB + "C.E.P: " + supplier.getCep());
//			content.append("<br/>" + TAB + "Cidade/UF: "
//					+ supplier.getCity().getName() + "/" + supplier.getState());
//			content.append("<br/>" + TAB + "Rua/Avenida: "
//					+ supplier.getStreet());
//			content.append("<br/>" + TAB + "Bairro: "
//					+ supplier.getNeighborhood());
//			content.append("</h4>");
//			int supplierId = supplier.getId();
//			content.append("<h3>PRODUTOS:</h3>");
//			content.append("<h4>");
//			try (ResultSet rs = dataBase
//					.executeQuery(
//							"SELECT *FROM supplier_product_association WHERE supplier = ?",
//							supplierId)) {
//				while (rs.next()) {
//					try (ResultSet resultSet = dataBase.executeQuery(
//							"SELECT *FROM Product WHERE id = ?",
//							rs.getInt("product"))) {
//						while (resultSet.next()) {
//							content.append("<br/>" + TAB + "- "
//									+ resultSet.getString("name"));
//						}
//					}
//				}
//			} catch (SQLException e) {
//
//				e.printStackTrace();
//			}
//			content.append("</h4>");
//			if (supplier.equals(s.get(s.size() - 1))) {
//				Date now = new Date();
//				String formattedDate = new SimpleDateFormat().format(now);
//				content.append("<br/><br/><hr /><small><i>Relatório criado em: "
//						+ formattedDate + "</i></small>");
//			}
//
//			content.append("</div>");
//		}
//
//		createPdf(reportPathFile, "Relatório de Fornecedores",
//				content.toString());
//
//		if (openFile) {
//			try {
//				Desktop.getDesktop().open(file);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} else {
//			ShowMessage.successMessage(frame, "Relatório Criado",
//					"O relatório foi salvo em " + reportPathFile
//							+ "\ncom sucesso");
//		}
//	}

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
