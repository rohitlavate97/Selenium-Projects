package testClasses;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Screenshot {
	public static void main(String args[]) throws InterruptedException, IOException {
	System.setProperty("webdriver.chrome.driver","D:\\Testing Prep\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.facebook.com");
	Thread.sleep(2000);
	TakesScreenshot ts = (TakesScreenshot)driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
	File des = new File("D:\\Testing Prep\\Screenshot\\s1.png");
	FileUtils.copyFile(src, des);
	driver.quit();
	}
	
}
