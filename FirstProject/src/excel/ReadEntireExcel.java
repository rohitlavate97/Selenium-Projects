package excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadEntireExcel {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("D:\\Testing Prep\\TestData.xlsx");
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet1 = wb.getSheet("Sheet1");
		int rows = sheet1.getPhysicalNumberOfRows();
		for(int i=0;i<rows;i++) {
		Row row = sheet1.getRow(i);
		int cols = row.getPhysicalNumberOfCells();
			for(int j=0;j<cols;j++) {
				Cell cell = row.getCell(j);
				switch (cell.getCellType()) {
				    case STRING:
				        System.out.print(cell.getStringCellValue());
				        break;
	
				    case NUMERIC:
				        System.out.print(cell.getNumericCellValue());
				        break;
	
				    case BOOLEAN:
				        System.out.print(cell.getBooleanCellValue());
				        break;
	
				    default:
				        System.out.print(" ");
				}
			}
			System.out.println();
		}

		wb.close();
		fis.close();
	}

}
