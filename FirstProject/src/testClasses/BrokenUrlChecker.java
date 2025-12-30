package testClasses;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrokenUrlChecker {

	public static void main(String[] args) throws IOException, InterruptedException {
		int broken = 0;
		int notbroken = 0;
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		Thread.sleep(2000);
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		for(int i=0;i<links.size();i++) {
		        String href = links.get(i).getAttribute("href");
		        System.out.println(href);
				URL url = new URL(href);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				int responseCode = con.getResponseCode();
				String responseMessage = con.getResponseMessage();
				if(responseCode==200) {
					System.out.println(href+":Not Broken");
					notbroken++;
				}else {
					System.out.println(href+":Not Broken");
					broken++;
				}
		}
		System.out.println("Number of broken links: "+broken);
		System.out.println("Number of working links: "+notbroken);
		driver.quit();
		
	}

}
