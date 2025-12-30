package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptExecutorExamples {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.selenium.dev/downloads/");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement ele = driver.findElement(By.xpath("//button[@aria-controls='supported-browsers']"));
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,-2000)");
		Thread.sleep(2000);
		WebElement ele2 = driver.findElement(By.xpath("//button[@aria-controls='supported-os']"));
		ele2.sendKeys(Keys.PAGE_DOWN);
		Thread.sleep(2000);
		driver.quit();
	}

}
