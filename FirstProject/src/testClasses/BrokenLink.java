package testClasses;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenLink {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.tutorialspoint.com/selenium/practice/broken-links.php");
		Thread.sleep(3000);
		//WebElement brokenLink = driver.findElement(By.xpath("//a[@href='broken-link.php']"));
		URL url = new URL("https://www.tutorialspoint.com/selenium/practice/broken-link.php");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		System.out.println(con.getResponseCode());
		System.out.println(con.getResponseMessage());
	}
}
