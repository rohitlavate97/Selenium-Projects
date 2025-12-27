package testClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MethodWebDriverInterface {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://lisapowers.co/reiki-the-unified-field/");
		System.out.println("--------------------------------------------");
		System.out.println("we are at: "+driver.getTitle());
		System.out.println("--------------------------------------------");
		System.out.println("The URL is: "+driver.getCurrentUrl());
		System.out.println("--------------------------------------------");
		//System.out.println("The Page Source is: "+driver.getPageSource());
		System.out.println("--------------------------------------------");
		System.out.println("The ID of Page is: "+driver.getWindowHandle());
		driver.close();
	}

}
