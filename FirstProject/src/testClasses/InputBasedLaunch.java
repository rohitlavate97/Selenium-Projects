package testClasses;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class InputBasedLaunch {

	public static void main(String[] args) {
		WebDriver driver = null;
		Scanner sc = new Scanner(System.in);
		String browser = sc.next();
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver","D:\\Testing Prep\\geckodriver.exe");
			driver = new FirefoxDriver();
		}else {
			System.out.println("Invalid browser");
		}
		
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		//driver.close();
		driver.quit();

	}

}
