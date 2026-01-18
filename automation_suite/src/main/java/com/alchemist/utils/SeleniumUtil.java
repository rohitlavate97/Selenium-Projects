package com.alchemist.utils;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.*;

import com.alchemist.base.DriverFactory;
import com.alchemist.constants.FrameworkConstants;

public final class SeleniumUtil {

    private SeleniumUtil() {}

    // ===== Core Accessors (Thread Safe) =====

    private static WebDriver driver() {
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) {
            throw new IllegalStateException(
                "WebDriver is null. Ensure DriverFactory.initDriver() is called before using SeleniumUtil."
            );
        }
        return driver;
    }

    private static WebDriverWait explicitWait() {
        return new WebDriverWait(
                driver(),
                Duration.ofSeconds(FrameworkConstants.EXPLICIT_WAIT));
    }

    private static Wait<WebDriver> fluentWait(int timeout, int polling) {
        return new FluentWait<>(driver())
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(polling))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    private static JavascriptExecutor js() {
        return (JavascriptExecutor) driver();
    }

    private static Actions actions() {
        return new Actions(driver());
    }

    // ===== Basic Actions =====

    public static void click(WebElement element) {
        explicitWait()
                .until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }

    public static void type(WebElement element, String text) {
        explicitWait()
                .until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public static String getText(WebElement element) {
        return explicitWait()
                .until(ExpectedConditions.visibilityOf(element))
                .getText();
    }

    // ===== Fluent Wait =====

    public static void fluentWait(By locator, int timeout, int polling) {
        fluentWait(timeout, polling)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ===== Alerts =====

    public static void acceptAlert() {
        explicitWait()
                .until(ExpectedConditions.alertIsPresent())
                .accept();
    }

    public static void dismissAlert() {
        explicitWait()
                .until(ExpectedConditions.alertIsPresent())
                .dismiss();
    }

    // ===== Frames =====

    public static void switchToFrame(WebElement frame) {
        explicitWait()
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    public static void switchToDefaultContent() {
        driver().switchTo().defaultContent();
    }

    // ===== Windows =====

    public static void switchToNewWindow() {
        String parent = driver().getWindowHandle();
        for (String window : driver().getWindowHandles()) {
            if (!window.equals(parent)) {
                driver().switchTo().window(window);
                return;
            }
        }
        throw new NoSuchWindowException("No new window found to switch");
    }

    // ===== JavaScript Actions =====

    public static void jsClick(WebElement element) {
        js().executeScript("arguments[0].click();", element);
    }

    public static void scrollIntoView(WebElement element) {
        js().executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // ===== Actions Class =====

    public static void hover(WebElement element) {
        actions().moveToElement(element).perform();
    }

    public static void doubleClick(WebElement element) {
        actions().doubleClick(element).perform();
    }

    public static void rightClick(WebElement element) {
        actions().contextClick(element).perform();
    }

    public static void dragAndDrop(WebElement src, WebElement target) {
        actions().dragAndDrop(src, target).perform();
    }

    // ===== Robot =====

    public static void pressEnterKey() {
        try {
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            throw new RuntimeException("Robot key press failed", e);
        }
    }

    // ===== File Upload =====

    public static void uploadFile(WebElement element, String path) {
        explicitWait()
                .until(ExpectedConditions.visibilityOf(element))
                .sendKeys(path);
    }

    // ===== Browser Navigation =====

    public static void refreshPage() {
        driver().navigate().refresh();
    }

    public static void navigateBack() {
        driver().navigate().back();
    }

    public static void addCookie(String name, String value) {
        driver().manage().addCookie(new Cookie(name, value));
    }

    // ===== Shadow DOM (Selenium 4+) =====

    public static WebElement getShadowElement(WebElement host, String css) {
        return host.getShadowRoot().findElement(By.cssSelector(css));
    }

    // ===== Relative Locators =====

    public static WebElement findElementBelow(WebElement element, By locator) {
        return driver().findElement(
                RelativeLocator.with(locator).below(element));
    }
}
