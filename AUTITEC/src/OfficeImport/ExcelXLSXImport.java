package OfficeImport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import model.Client;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class ExcelXLSXImport extends Import{

	private ArrayList<Client> clientList;

	@Override
    public void importExcel(File file) throws IOException {
		FileInputStream input = new FileInputStream(file);
		
		clientList = new ArrayList<Client>();

		// Get the workbook instance for XLS file
		XSSFWorkbook workbook = new XSSFWorkbook(input);

		// Get first sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			Client client = new Client();
			
			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			cellIterator.next();
			int i = 0;
			while (cellIterator.hasNext()) {
				   
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					
					break;
				case Cell.CELL_TYPE_STRING:
					if(i == 0) {
						client.setCnpj(cell.getStringCellValue());
					}else if(i==1) {
						client.setStateInscrition(cell.getStringCellValue());
					}else if(i==2) {
						client.setName(cell.getStringCellValue());
					}else if(i==3) {
						client.setStreet(cell.getStringCellValue());
					}else if(i==4) {
						client.setNeighborhood(cell.getStringCellValue());
					}else if(i==9) {
						client.setCep(cell.getStringCellValue());
					}else if(i==10) {
						client.setPhone(cell.getStringCellValue());
					}
					clientList.add(client);					
					i++;
					break;
				}
				i = 0;
			}
		}
		try {
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void print() {
		clientList.forEach(s -> System.out.println(s));
	}
}
