package testClasses;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertsDemo {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		Thread.sleep(2000);
		WebElement lgnBtn = driver.findElement(By.xpath("//button[@type='submit']"));
		lgnBtn.click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		alert.accept(); 
		driver.switchTo().defaultContent();
	}

}
