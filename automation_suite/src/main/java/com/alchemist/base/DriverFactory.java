package com.alchemist.base;

import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.alchemist.utils.ConfigReader;

public final class DriverFactory {

    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    private DriverFactory() {}

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {

        WebDriver drv;
        browser = browser.toLowerCase();

        boolean useGrid  = Boolean.parseBoolean(ConfigReader.get("GRID"));
        boolean headless = Boolean.parseBoolean(ConfigReader.get("HEADLESS"));

        log.info("Initializing WebDriver");
        log.info("Browser   : {}", browser);
        log.info("Use Grid  : {}", useGrid);
        log.info("Headless  : {}", headless);

        try {
            if (useGrid) {
                drv = createRemoteDriver(browser, headless);
            } else {
                drv = createLocalDriver(browser, headless);
            }

        } catch (Exception e) {
            log.error("Failed to initialize WebDriver for browser: " + browser, e);
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }

        driver.set(drv);

        // Implicit wait
        String implicit = ConfigReader.get("ImplicitTimeOut");
        if (implicit != null) {
            log.info("Setting implicit wait: {} seconds", implicit);
            driver.get().manage().timeouts()
                  .implicitlyWait(Duration.ofSeconds(Integer.parseInt(implicit)));
        }

        // Maximize only when not headless
        if (!headless) {
            driver.get().manage().window().maximize();
        }

        log.info("WebDriver initialized successfully");
    }

    /* ===================== DRIVER CREATION ===================== */

    private static WebDriver createRemoteDriver(String browser, boolean headless) throws Exception {

        String gridUrlStr = ConfigReader.get("GRID_URL");
        log.info("Connecting to Selenium Grid: {}", gridUrlStr);

        URL gridUrl = new URL(gridUrlStr);

        switch (browser) {

            case "chrome":
                return new RemoteWebDriver(gridUrl, getChromeOptions(headless));

            case "firefox":
                return new RemoteWebDriver(gridUrl, getFirefoxOptions(headless));

            case "edge":
                return new RemoteWebDriver(gridUrl, getEdgeOptions(headless));

            default:
                throw new RuntimeException("Unsupported browser for Grid: " + browser);
        }
    }

    private static WebDriver createLocalDriver(String browser, boolean headless) {

        switch (browser) {

            case "chrome":
                log.info("Launching Chrome locally");
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(getChromeOptions(headless));

            case "firefox":
                log.info("Launching Firefox locally");
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver(getFirefoxOptions(headless));

			/*
			 * case "edge": log.info("Launching Edge locally");
			 * WebDriverManager.edgedriver().setup(); return new
			 * EdgeDriver(getEdgeOptions(headless));
			 */
            
            case "edge":
                log.info("Launching Edge locally using manual driver");
                System.setProperty("webdriver.edge.driver",
                        System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe");
                return new EdgeDriver(getEdgeOptions(headless));
            
            default:
                throw new RuntimeException("Unsupported local browser: " + browser);
        }
    }

    /* ===================== OPTIONS ===================== */

    private static ChromeOptions getChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
        }

        return options;
    }

    private static FirefoxOptions getFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("-headless");
        }

        return options;
    }

    private static EdgeOptions getEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return options;
    }

    /* ===================== ACCESSORS ===================== */

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            log.info("Quitting WebDriver");
            driver.get().quit();
            driver.remove();
        }
    }
}
