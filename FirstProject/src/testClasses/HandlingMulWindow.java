package testClasses;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandlingMulWindow {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","D:\\Testing Prep\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.get("https://www.hdfcbank.com/");
				//driver.findElement(By.xpath(".//*[@id='cee_closeBtn']/img")).click();
				String parentWindowId = driver.getWindowHandle();
				System.out.println("Parent Window ID:" + parentWindowId);
				driver.findElement(By.xpath("//a[@title='NetBanking']")).click();
				Set<String> allWindowId = driver.getWindowHandles();
				for (String x : allWindowId) {
				if (!parentWindowId.equals(x)) {
				System.out.println("Child Window ID:" + x);
				driver.switchTo().window(x);
				Thread.sleep(3000);
//				WebElement input = driver.findElement(By.xpath("//input[@name='fldLoginUserId']"));
//				JavascriptExecutor js = (JavascriptExecutor)driver;
//				js.executeScript("arguments[0].setAttribute('value','123')", input);
//				Thread.sleep(3000);
				driver.findElement(By.xpath("//div[2]/div[2]/a")).click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				}
				}
				Thread.sleep(3000);
				driver.quit(); 
	}

}
