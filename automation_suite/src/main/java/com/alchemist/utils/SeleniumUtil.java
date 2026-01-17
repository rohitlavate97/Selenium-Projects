package com.alchemist.utils;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.*;
import com.alchemist.base.DriverFactory;

public class SeleniumUtil {

    private static WebDriver driver = DriverFactory.getDriver();
    private static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    private static JavascriptExecutor js = (JavascriptExecutor) driver;

    // Basic Actions
    public static void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void type(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public static String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    // Fluent Wait
    public static void fluentWait(By locator, int timeout, int polling) {
        new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(timeout))
            .pollingEvery(Duration.ofSeconds(polling))
            .ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class)
            .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Alerts
    public static void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public static void dismissAlert() {
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    // Frames
    public static void switchToFrame(WebElement frame) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    public static void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // Windows
    public static void switchToNewWindow() {
        String parent = driver.getWindowHandle();
        for (String w : driver.getWindowHandles())
            if (!w.equals(parent)) driver.switchTo().window(w);
    }

    // JS Actions
    public static void jsClick(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    public static void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Actions Class
    public static void hover(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    public static void doubleClick(WebElement element) {
        new Actions(driver).doubleClick(element).perform();
    }

    public static void rightClick(WebElement element) {
        new Actions(driver).contextClick(element).perform();
    }

    public static void dragAndDrop(WebElement src, WebElement target) {
        new Actions(driver).dragAndDrop(src, target).perform();
    }

    // Robot Class
    public static void pressEnterKey() {
        try {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // File Upload
    public static void uploadFile(WebElement element, String path) {
        element.sendKeys(path);
    }

    // Browser Navigation & Cookies
    public static void refreshPage() {
        driver.navigate().refresh();
    }

    public static void navigateBack() {
        driver.navigate().back();
    }

    public static void addCookie(String name, String value) {
        driver.manage().addCookie(new Cookie(name, value));
    }

    // Shadow DOM
    public static WebElement getShadowElement(WebElement host, String css) {
        return host.getShadowRoot().findElement(By.cssSelector(css));
    }

    // Relative Locators
    public static WebElement findElementBelow(WebElement element, By locator) {
        return driver.findElement(RelativeLocator.with(locator).below(element));
    }
}
