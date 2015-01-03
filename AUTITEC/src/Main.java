import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.swing.SwingUtilities;

import financial.view.GenerateReportFrame;
import financial.view.ListBillsFrame;
import financial.view.ListDebtsFrame;
import financial.view.PayBillFrame;
import login.view.LoginFrame;
import userInterface.view.LoadingFrame;
import userInterface.view.MainFrame;
import util.Html;
import util.HtmlToPdf;
import util.HandlesFile;

/**
 * Classe principal, responsável por inicializar a aplicação.
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
//		Html html = new Html(new File("teste.pdf"));
//		html.createFile("TITULO", "<p>CONTEUDO</p>");
//		
//		String s = html.getHtml();
//		
//		System.out.println(s);
//		
//		UUID id = UUID.randomUUID();
//		File f = new File(id.toString());
//		f.createNewFile();
//		HandlesFile.writeFile(f, s);
//		
//		HtmlToPdf.convert(f, new File("pdf.pdf"));
				
		LoadingFrame loadingFrame = new LoadingFrame();  
	
		SwingUtilities.invokeLater(new Runnable(){  
			@Override public void run() { loadingFrame.setVisible(true); }  
		});
		
		//new LoginFrame().setVisible(true);
		new MainFrame().setVisible(true);
		//new GenerateReportFrame().setVisible(true);
		  
		loadingFrame.dispose();
				
	}

}
