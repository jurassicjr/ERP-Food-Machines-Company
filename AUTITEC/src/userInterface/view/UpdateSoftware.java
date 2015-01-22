package userInterface.view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UpdateSoftware {

	public void download() throws IOException {
		URL url = new URL("http://localhost/catalog.xml");
		String destino = "C:\\Temp\\catalog_java.xml";
		InputStream is = url.openStream();
		 
		FileOutputStream fos = new FileOutputStream(destino);
		 
		int bytes = 0;
		 
		while ((bytes = is.read()) != -1) {
		 fos.write(bytes);
		 }
		 
		 is.close();
		 fos.close();
	}
}