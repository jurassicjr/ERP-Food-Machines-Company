package OfficeImport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Client;
import model.State;
import model.Supplier;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import database.DataBase;

public class ExcelXLSImport extends Import {

	private int i;
	private List<Client> clientList;
	private List<Supplier> supplierList;
	private DataBase dataBase;
	
	public ExcelXLSImport() {
	    dataBase = new DataBase();
	    dataBase.connect();
    }

	@Override
	public void importExcelClient(File file) throws IOException {
		FileInputStream input = new FileInputStream(file);
		
		clientList = new ArrayList<Client>();

		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(input);

		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		// Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			Client client = new Client();
			
			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			int i = 0;
			while (cellIterator.hasNext()) {
				   
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					i++;
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
					}else if(i==8) {
						client.setCep(cell.getStringCellValue());
					}else if(i==9) {
						client.setPhone(cell.getStringCellValue());
					}
					i++;
					break;
				case Cell.CELL_TYPE_BLANK:
					i++;
				}
			}
			clientList.add(client);					
		}
		try {
			clientList.forEach(s -> System.out.println(s));
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void print() {
		clientList.forEach(s -> System.out.println(s));
	}
	

	@Override
    public void importExcelSupplier(File file) throws FileNotFoundException, IOException {
FileInputStream input = new FileInputStream(file);
		
		supplierList = new ArrayList<Supplier>();

		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(input);

		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		// Iterate through each rows from first sheet
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			Supplier s = new Supplier();
			
			// For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			int i = 0;
			while (cellIterator.hasNext()) {
				   
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_NUMERIC:
					if(i == 0) {
						s.setCorporateName(cell.getStringCellValue());
					}else if(i==1) {
						s.setCNPJ(cell.getStringCellValue());
					}else if(i==2) {
						s.setStateRegistration(cell.getStringCellValue());
					}else if(i==3) {
						s.setStreet(cell.getStringCellValue());
					}else if(i==4) {
						s.setNeighborhood(cell.getStringCellValue());
					}else if(i==5) {
						setCity(cell.getStringCellValue());
					}else if(i==6) {
						setState(cell.getStringCellValue());
					}else if(i==7) {
						s.setPhone(cell.getStringCellValue());
					}else if(i==8) {
						s.setEmail(cell.getStringCellValue());
					}else if(i == 9) {
						s.setCertificate(isCertificate(cell.getStringCellValue()));
					}
					i++;
					break;
				case Cell.CELL_TYPE_STRING:
					if(i == 0) {
						s.setCorporateName(cell.getStringCellValue());
					}else if(i==1) {
						s.setCNPJ(cell.getStringCellValue());
					}else if(i==2) {
						s.setStateRegistration(cell.getStringCellValue());
					}else if(i==3) {
						s.setStreet(cell.getStringCellValue());
					}else if(i==4) {
						s.setNeighborhood(cell.getStringCellValue());
					}else if(i==5) {
						setCity(cell.getStringCellValue());
					}else if(i==6) {
						s.setState(setState(cell.getStringCellValue()));
					}else if(i==7) {
						s.setPhone(cell.getStringCellValue());
					}else if(i==8) {
						s.setEmail(cell.getStringCellValue());
					}else if(i == 9) {
						s.setCertificate(isCertificate(cell.getStringCellValue()));
					}
					i++;
					break;
				case Cell.CELL_TYPE_BLANK:
					i++;
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					if(i == 9) {
						s.setCertificate(cell.getBooleanCellValue());
					}
					break;
				}
			}
			supplierList.add(s);					
		}
		try {
			clientList.forEach(s -> System.out.println(s));
			input.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	private boolean isCertificate(String stringCellValue) {
	    if(stringCellValue.equalsIgnoreCase("sim")) return true;
	    else if(stringCellValue.equalsIgnoreCase("não"))return false;
	    else return false;
    }

	private State setState(String UF) {
	    String stateSql = "SELECT *FROM state WHERE UF = ?";
	    UF = UF.toUpperCase();
	    try(ResultSet rs = dataBase.executeQuery(stateSql, UF)){
	    State state = new State(rs.getInt("id"), rs.getString("name"));
	    return state;
	    } catch (SQLException e) {
	        e.printStackTrace();
        }
		return null;
    }

	private void setCity(String stringCellValue) {
	    
    }
}
