package com.alchemist.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AttachmentUtil {
//COLLECT LOGS & SCREENSHOTS DYNAMICALLY
    public static File[] collectAttachments() {

        List<File> files = new ArrayList<>();

        // Extent Report
        files.add(new File("reports/ExtentReport.html"));

        // Log File
        files.add(new File("logs/automation.log"));

        // Screenshots (attach folder zip OR key screenshots)
        File screenshotDir = new File("screenshots");
        if (screenshotDir.exists()) {
            File[] screenshots = screenshotDir.listFiles();
            if (screenshots != null) {
                for (File file : screenshots) {
                    files.add(file);
                }
            }
        }

        return files.toArray(new File[0]);
    }
}
