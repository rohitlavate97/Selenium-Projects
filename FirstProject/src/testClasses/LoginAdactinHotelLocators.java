package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginAdactinHotelLocators {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://adactin.com/HotelApp/index.php");
		driver.manage().window().maximize();
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("vengatram");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("vengat@123445");
		WebElement loginButton = driver.findElement(By.className("login_button"));
		//driver.findElement(By.cssSelector("input.login_button")).click();
		loginButton.click();
	}

}
