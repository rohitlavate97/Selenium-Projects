package testClasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchBrowser {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrom.driver", "D:\\Testing\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.ibm.com/docs/en/integration-bus/9.0.0?topic=soap-structure-message");
		System.out.println(driver.getPageSource());
		System.out.println(driver.getCurrentUrl());
		driver.quit();

	}

}
