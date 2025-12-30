package excel;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcel {

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("D:\\Testing Prep\\TestData.xlsx");
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet1 = wb.getSheet("Sheet1");
		// Row index starts from 0
		Row row = sheet1.getRow(1);
		Cell cell = row.getCell(0);
		String stringCellValue = cell.getStringCellValue();
		System.out.println(stringCellValue);
		wb.close();
		fis.close();
	}

}
