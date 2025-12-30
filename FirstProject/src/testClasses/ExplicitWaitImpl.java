package testClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWaitImpl {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = null;
		driver.manage().window().maximize();
		driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
		WebElement addText = driver.findElement(By.xpath("//button[text()='Add Textbox1']"));
		addText.click();
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//article/div/h3/input"))));
		driver.findElement(By.xpath("//article/div/h3/input")).sendKeys("rohit");	
	}

}
