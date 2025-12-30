package testClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandlingMultipleElements {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("https://www.google.com");
			Thread.sleep(2000);
			List<WebElement> elements = driver.findElements(By.xpath("//a"));
			 elements.forEach(ele -> {
	                String link = ele.getAttribute("href");
	                if (link != null && !link.isEmpty()) {
	                    System.out.println(link);
	                }
	            });
//			 for (WebElement ele : elements) {
//	                String link = ele.getAttribute("href");
//	                if (link != null) {
//	                    System.out.println(link);
//	                }
//	            }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}

}
