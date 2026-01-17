package com.alchemist.utils;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import com.alchemist.base.DriverFactory;
import com.alchemist.constants.FrameworkConstants;

public class WaitUtil {

    public static WebElement waitForVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(
            DriverFactory.getDriver(),
            Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(
            DriverFactory.getDriver(),
            Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
