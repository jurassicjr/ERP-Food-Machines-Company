package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class VerifySerial {
	
	private String cpuId;
	
	public VerifySerial() {
		cpuId = CPUID.cpuId();
	}
	
	@SuppressWarnings("resource")
	public boolean verify() {
		
		try {
			
			File file = new File("cpu_id.conf");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			StringBuffer buffer = new StringBuffer();
			
			String line;
			while((line = bufferedReader.readLine()) != null) { 
				buffer.append(line);
			}
			
			if(!buffer.toString().trim().equals(cpuId)) {
				
				String title = "Erro ao inicilizar o software";
				String message = "Houve um erro ao inicializar o software.\nPor favor, repita a instalação";
				
				ShowMessage.errorMessage(null, title, message);
				
				return false;
				
			}
			
			return true;
						
		} catch (IOException e) {
			
			String title = "Erro ao inicilizar o software";
			String message = "Houve um erro ao inicializar o software.\nPor favor, instale-o novamente";
			
			ShowMessage.errorMessage(null, title, message);
			
			e.printStackTrace();
		}		
		
		return false;
	}

}
