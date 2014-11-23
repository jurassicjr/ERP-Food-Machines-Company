package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Properties {
	
	private java.util.Properties properties;
	
	public Properties () {
		
		properties = new java.util.Properties();
		
		try {
			
			File file = new File(getClass().getResource("/conf/conf.prop").getPath());
			FileInputStream fis = new FileInputStream(file);
			
			properties.load(fis);
			fis.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPropertie(String key) {
		return properties.getProperty(key);
	}
	
	public void setPropertie(String key, String value) {
		properties.setProperty(key, value);
	}

}