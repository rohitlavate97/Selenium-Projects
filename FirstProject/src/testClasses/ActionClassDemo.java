package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionClassDemo {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.greenstechnologys.com/");
		Thread.sleep(2000);
		WebElement courses = driver.findElement(By.xpath("//a[text()='COURSES']"));
		Actions act = new Actions(driver);
		act.moveToElement(courses).perform();
		Thread.sleep(2000);
		WebElement fullstack = driver.findElement(By.xpath("//li[3]/ul/li[19]/a/span"));
		act.moveToElement(fullstack).perform();
		act.click(fullstack).perform();
		Thread.sleep(2000);
		driver.get("https://demo.guru99.com/test/drag_drop.html");
		WebElement src = driver.findElement(By.xpath("//*[@id=\"credit2\"]/a"));
		WebElement dest = driver.findElement(By.xpath("(//li[@class='placeholder'])[1]"));
		Thread.sleep(2000);
		act.dragAndDrop(src, dest);
		Thread.sleep(2000);
		act.clickAndHold(src).moveToElement(dest).release(dest).perform();   //build().perform() == perform()
		//driver.quit();
	}

}
