package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DisplyedGoogleSearchEnabled {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		By xpath = By.xpath("//textarea[@title='Search']");
		driver.get("https://www.google.com");
		WebElement searchInput = driver.findElement(xpath);
		System.out.println("The input box is Enabled:"+searchInput.isEnabled());
		By cssSelector = By.cssSelector("div[class='FPdoLc lJ9FBc'] input[name='btnK']");
		WebElement searchButton = driver.findElement(cssSelector);
		System.out.println("The input box is displayed: "+searchButton.isDisplayed());
	}

}
