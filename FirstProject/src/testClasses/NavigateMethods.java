package testClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigateMethods {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		try {
			driver.manage().window().maximize();
			Thread.sleep(1000);
			driver.manage().deleteAllCookies();
			driver.get("https://www.google.com");
			Thread.sleep(3000);
			driver.navigate().to("https://www.facebook.com");
			Thread.sleep(3000);
			driver.navigate().back();
			Thread.sleep(3000);
			driver.navigate().forward();
			Thread.sleep(3000);
			driver.navigate().refresh();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}

}
