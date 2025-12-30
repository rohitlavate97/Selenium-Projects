package testClasses;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertsTypes {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/alerts");
		Thread.sleep(2000);
		WebElement confirmationAlert = driver.findElement(By.xpath("//button[@id='confirmButton']"));
		confirmationAlert.click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(2000);
		confirmationAlert.click();
		Thread.sleep(2000);
		alert = driver.switchTo().alert();
		alert.dismiss();
		Thread.sleep(2000);
		
		WebElement promptAlert = driver.findElement(By.xpath("//button[@id='promtButton']"));
		promptAlert.click();
		Thread.sleep(2000);
		alert = driver.switchTo().alert();
		alert.sendKeys("Testing");
		Thread.sleep(3000);
		alert.getText();
		alert.accept();
	}

}
