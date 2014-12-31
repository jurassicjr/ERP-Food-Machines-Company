package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class HtmlToPdf {
	
	public static boolean htmlToPdf(File input, File output) {
		
        Document document = new Document();
        
        try {
        	
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(output));
			document.open();
			
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(input));
			
			document.close();
			
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
			return false;
		} 
        
		return true;
	}

}
