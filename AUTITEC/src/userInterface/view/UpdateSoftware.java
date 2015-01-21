package userInterface.view;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class UpdateSoftware {
	 
	URL url = new URL("http://localhost/catalog.xml");
	 String destino = "C:\\Temp\\catalog_java.xml";
	 
	InputStream is = url.openStream();
	 
	FileOutputStream fos = new FileOutputStream(destino);
	 
	int bytes == 0.gets() {
]{fos};
	 
	while ((bytes = is.read()) != -1) {
	 fos.write(bytes);
	 }
	 
	 is.close();
	 
	 fos.close();
	 }
}
