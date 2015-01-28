package OfficeImport;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Client;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelImport {

	int i;

	public void importClient(FileInputStream file) throws IOException {
		List<Object> cnpjList = new ArrayList<Object>();
		List<Object> itemList = new ArrayList<Object>();
		List<Object> IEList = new ArrayList<Object>();
		List<Object> companyNameList = new ArrayList<Object>();
		List<Object> streetList = new ArrayList<Object>();
		List<Object> addressNumberList = new ArrayList<Object>();
		List<Object> neighborhoodList = new ArrayList<Object>();
		List<Object> cityList = new ArrayList<Object>();
		List<Object> UFList = new ArrayList<Object>();
		List<Object> CEPList = new ArrayList<Object>();
		List<Object> phoneList = new ArrayList<Object>();
		
		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		// Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue() + "\t\t");
					break;
				case Cell.CELL_TYPE_NUMERIC:
					double b = cell.getNumericCellValue();
					if (i == 0) {
						itemList.add(b);
					} else if (i == 1) {
						cnpjList.add(b);
					} else if (i == 2) {
						IEList.add(b);
					} else if (i == 3) {
						companyNameList.add(b);
					} else if (i == 4) {
						streetList.add(b);
					} else if (i == 5) {
						addressNumberList.add(b);
					} else if (i == 6) {
						neighborhoodList.add(b);
					} else if (i == 7) {
						cityList.add(b);
					}else if(i == 8) {
						UFList.add(b);
					}else if(i == 9) {
						CEPList.add(b);
					}else if(i ==10) {
						phoneList.add(b);
					}
					break;
				case Cell.CELL_TYPE_STRING:
					String verifica = cell.getStringCellValue();
					if (verifica.equalsIgnoreCase("Item")) {
						i = 0;
					} else if (verifica.equalsIgnoreCase("CNPJ")) {
						i = 1;
					} else if (verifica.equalsIgnoreCase("I.E.")) {
						i = 2;
					} else if (verifica.equalsIgnoreCase("Razão Social")) {
						i = 3;
					} else if (verifica.equalsIgnoreCase("Endereço")) {
						i = 4;
					} else if (verifica.equalsIgnoreCase("numero")) {
						i = 5;
					} else if (verifica.equalsIgnoreCase("Bairro")) {
						i = 6;
					} else if (verifica.equalsIgnoreCase("Cidade")) {
						i = 7;
					} else if (verifica.equalsIgnoreCase("UF")) {
						i = 8;
					}else if(verifica.equalsIgnoreCase("CEP")) {
						i = 9;
					}
					if (i == 0) {
						itemList.add(cell.getStringCellValue());
					} else if (i == 1) {
						cnpjList.add(cell.getStringCellValue());
					} else if (i == 2) {
						IEList.add(cell.getStringCellValue());
					} else if (i == 3) {
						companyNameList.add(cell.getStringCellValue());
					} else if (i == 4) {
						streetList.add(cell.getStringCellValue());
					} else if (i == 5) {
						addressNumberList.add(cell.getStringCellValue());
					} else if (i == 6) {
						neighborhoodList.add(cell.getStringCellValue());
					} else if (i == 7) {
						cityList.add(cell.getStringCellValue());
					}else if (i == 8) {
						UFList.add(cell.getStringCellValue());
					}else if(i == 9) {
						CEPList.add(cell.getStringCellValue());
					}else if(i==10) {
						phoneList.add(cell.getStringCellValue());
					}
					break;

				}
			}
		}
		try {
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cnpjList.forEach(s -> System.out.println(s));
		itemList.forEach(s -> System.out.println(s));
		for(int i = 0; i< itemList.size(); i++) {
			Client client = new Client();
			client.setName((String) companyNameList.get(i));
			
		}
	}
}
