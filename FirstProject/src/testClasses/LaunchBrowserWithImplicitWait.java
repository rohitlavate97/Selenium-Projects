package testClasses;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchBrowserWithImplicitWait {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrom.driver", "D:\\Testing\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.ibm.com/docs/en/integration-bus/9.0.0?topic=soap-structure-message");
		System.out.println(driver.getPageSource());
		System.out.println(driver.getCurrentUrl());
		driver.quit();

	}

}
