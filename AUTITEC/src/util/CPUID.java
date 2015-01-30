package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CPUID {
	
	public static String cpuId() {
    	
        String result = "";
        
        try {
        	
            File file = File.createTempFile("tmp", ".vbs");   
            file.deleteOnExit();   
            FileWriter fw = new java.io.FileWriter(file);   
  
            String vbs =   
                "On Error Resume Next \r\n\r\n" +   
                "strComputer = \".\"  \r\n" +   
                "Set objWMIService = GetObject(\"winmgmts:\" _ \r\n" +   
                "    & \"{impersonationLevel=impersonate}!\\\\\" & strComputer & \"\\root\\cimv2\") \r\n" +   
                "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_Processor\")  \r\n " +   
                "For Each objItem in colItems\r\n " +   
                "    Wscript.Echo objItem.ProcessorId  \r\n " +   
                "    exit for  ' do the first cpu only! \r\n" +   
                "Next                    ";   
  
  
            fw.write(vbs);   
            fw.close();   
            
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());   
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            String line;   
            while ((line = input.readLine()) != null) result += line;   
            
            input.close();
            
        } catch (IOException e) { e.printStackTrace(); } 
        
        if (result.trim().length() < 1 || result == null) result = "NO_CPU_ID";    
            
        return result.trim();   
    }

}
