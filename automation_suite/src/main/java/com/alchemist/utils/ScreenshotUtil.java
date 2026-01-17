package com.alchemist.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import com.alchemist.base.DriverFactory;
import com.alchemist.constants.FrameworkConstants;

public class ScreenshotUtil {

    public static String capture(String testName) {
        try {
            String timestamp =
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String path =
                FrameworkConstants.SCREENSHOT_PATH + testName + "_" + timestamp + ".png";

            File src =
                ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(src, new File(path));
            return path;

        } catch (Exception e) {
            return "";
        }
    }
}
