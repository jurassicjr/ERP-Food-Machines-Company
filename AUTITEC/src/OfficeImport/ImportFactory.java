package OfficeImport;

import java.io.File;

public class ImportFactory {

	public Import getImport(File file){
		if(file.getAbsolutePath().endsWith(".xls")) {
			return new ExcelXLSImport();
		}else if(file.getAbsolutePath().endsWith(".xlsx")){
			return new ExcelXLSXImport();
		}else {
			return null;
		}
		
	}
}
