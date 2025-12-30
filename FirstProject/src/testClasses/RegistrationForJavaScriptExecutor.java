package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class RegistrationForJavaScriptExecutor {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		driver.manage().window().maximize();
		driver.get("https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php");
		Thread.sleep(2000);
		WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
		WebElement inter = driver.findElement(By.xpath("//button[@aria-controls='collapseFive']"));
		WebElement login = driver.findElement(By.xpath("//input[@type='submit']"));
		js.executeScript("arguments[0].click()", inter);
		Thread.sleep(2000);
		js.executeScript("arguments[0].setAttribute('style','background-color: yellow;')", name);
		js.executeScript("arguments[0].setAttribute('value','rohit')", name);
		Thread.sleep(2000);
		Actions act = new Actions(driver);
		act.doubleClick(login).perform();
	}

}
