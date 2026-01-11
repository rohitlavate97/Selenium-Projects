package com.alchemist.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    private ExcelUtils(){}

    public static Object[][] readBooksFromResource(String excelResourceName, String sheetName) {
        try (InputStream is = ExcelUtils.class.getClassLoader().getResourceAsStream(excelResourceName)) {
            if (is == null) throw new RuntimeException("Excel not found: " + excelResourceName);

            Workbook wb = new XSSFWorkbook(is);
            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) {
                wb.close();
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            DataFormatter fmt = new DataFormatter();
            int lastRow = sheet.getLastRowNum();

            List<Object[]> rows = new ArrayList<>();

            for (int r = 1; r <= lastRow; r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                String run = fmt.formatCellValue(row.getCell(0)).trim();
                if (!run.equalsIgnoreCase("Y")) continue;

                String isbn = fmt.formatCellValue(row.getCell(1)).trim();
                String aisle = fmt.formatCellValue(row.getCell(2)).trim();

                rows.add(new Object[]{isbn, aisle});
            }

            wb.close();
            return rows.toArray(new Object[0][0]);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel: " + excelResourceName + " sheet: " + sheetName, e);
        }
    }
}
