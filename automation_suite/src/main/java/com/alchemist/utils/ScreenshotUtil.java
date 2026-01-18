package com.alchemist.utils;

import java.io.File;
import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import com.alchemist.base.DriverFactory;
import com.alchemist.constants.FrameworkConstants;

import io.qameta.allure.Allure;

public class ScreenshotUtil {

    public static String capture(String testName) {
        try {
            String timestamp =
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            String path =
                FrameworkConstants.SCREENSHOT_PATH + testName + "_" + timestamp + ".png";

            // Ensure directory exists
            new File(FrameworkConstants.SCREENSHOT_PATH).mkdirs();

            File src =
                ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(src, new File(path));
            return path;

        } catch (Exception e) {
            LogUtil.error("Screenshot failed", e);
            return null;
        }
    }

    // ================= ALLURE ATTACHMENT =================

    public static void attachToAllure() {
        try {
            byte[] screenshot =
                ((TakesScreenshot) DriverFactory.getDriver())
                .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                "Failure Screenshot",
                new ByteArrayInputStream(screenshot)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
