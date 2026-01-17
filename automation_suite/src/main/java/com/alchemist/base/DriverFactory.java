package com.alchemist.base;

import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.alchemist.utils.ConfigReader;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static void initDriver(String browser) {
		WebDriver drv = null;
		browser = browser.toLowerCase();
		boolean useGrid = Boolean.parseBoolean(ConfigReader.get("GRID"));

		try {
			if (useGrid) {
				URL gridUrl = new URL(ConfigReader.get("GRID_URL"));
				switch (browser) {
				case "chrome":
					drv = new RemoteWebDriver(gridUrl, new ChromeOptions());
					break;
				case "firefox":
					drv = new RemoteWebDriver(gridUrl, new FirefoxOptions());
					break;
				case "edge":
					drv = new RemoteWebDriver(gridUrl, new EdgeOptions());
					break;
				default:
					throw new RuntimeException("Unsupported browser for Grid: " + browser);
				}
			} else {
				// WebDriverManager first
				switch (browser) {
				case "chrome":
					WebDriverManager.chromedriver().setup();
					drv = new ChromeDriver();
					break;
				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					drv = new FirefoxDriver();
					break;
				case "edge":
					WebDriverManager.edgedriver().setup();
					drv = new EdgeDriver();
					break;
				default:
					throw new RuntimeException("Browser not supported: " + browser);
				}
			}
		} catch (Exception e) {
			System.out.println("WebDriverManager/Grid failed: " + e.getMessage());
			// Fallback to local drivers in 'drivers/' folder
			switch (browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
				drv = new ChromeDriver();
				break;
			case "firefox":
				System.setProperty("webdriver.gecko.driver", "drivers/geckodriver.exe");
				drv = new FirefoxDriver();
				break;
			case "edge":
				System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
				drv = new EdgeDriver();
				break;
			}
		}

		driver.set(drv);
		driver.get().manage().window().maximize();
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
