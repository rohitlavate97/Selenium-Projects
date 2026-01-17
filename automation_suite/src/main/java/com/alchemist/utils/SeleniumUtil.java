package com.alchemist.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import com.alchemist.base.DriverFactory;

public class SeleniumUtil {

    private static final Logger log =
            LogManager.getLogger(SeleniumUtil.class);

    public static void click(WebElement element) {
        log.info("Clicking on element: {}", element);
        WaitUtil.waitForClickable(element).click();
    }

    public static void type(WebElement element, String value) {
        log.info("Typing value '{}' into element: {}", value, element);
        WebElement el = WaitUtil.waitForVisible(element);
        el.clear();
        el.sendKeys(value);
    }

    public static String getText(WebElement element) {
        log.info("Getting text from element: {}", element);
        return WaitUtil.waitForVisible(element).getText();
    }

    public static void jsClick(WebElement element) {
        log.warn("Performing JS click on element: {}", element);
        JavascriptExecutor js =
            (JavascriptExecutor) DriverFactory.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public static void moveTo(WebElement element) {
        log.info("Moving mouse to element: {}", element);
        new Actions(DriverFactory.getDriver())
                .moveToElement(element).perform();
    }
}
