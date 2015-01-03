package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HandlesFile {
	
	public static String readFile(File file) {
		
		StringBuffer buffer = new StringBuffer();
		
		try {
			
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			while((line = bufferedReader.readLine()) != null) {
				buffer.append(line + "\n");
			}
			
			bufferedReader.close();
			fileReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}		
		
		return buffer.toString();
		
	}
	
	public static boolean writeFile(File file, String content) {
		
		try {
			
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.write(content);
			bufferedWriter.flush();
			
			bufferedWriter.close();
			fileWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}

}
