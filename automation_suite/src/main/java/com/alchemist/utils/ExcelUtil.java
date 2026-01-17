package com.alchemist.utils;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import com.alchemist.constants.FrameworkConstants;

public class ExcelUtil {

    public static String getData(String sheet, int row, int col) {
        try {
            Workbook wb =
                WorkbookFactory.create(new FileInputStream(FrameworkConstants.EXCEL_PATH));
            return wb.getSheet(sheet).getRow(row).getCell(col).toString();
        } catch (Exception e) {
            throw new RuntimeException("Excel read failed");
        }
    }
}
