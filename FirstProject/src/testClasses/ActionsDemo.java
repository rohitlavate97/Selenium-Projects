package testClasses;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsDemo {

	public static void main(String[] args) throws InterruptedException, AWTException {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		Thread.sleep(2000);
		WebElement gmail = driver.findElement(By.xpath("//a[@aria-label='Gmail ']"));
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.contextClick(gmail).perform();   //doubleClick() also can be used
		Robot robo = new Robot();
		Thread.sleep(2000);
		robo.keyPress(KeyEvent.VK_DOWN);
		robo.keyRelease(KeyEvent.VK_DOWN);
		Thread.sleep(2000);
		robo.keyPress(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_ENTER);
	}

}
