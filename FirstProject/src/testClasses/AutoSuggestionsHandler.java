package testClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoSuggestionsHandler {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//textarea[@name='q']")).sendKeys("Sunny");
		Thread.sleep(2000);
		List<WebElement> autoSuggestions = driver.findElements(By.xpath("//span[contains(text(),'sunny')]"));
		int count = autoSuggestions.size();
//		for(WebElement auto : autoSuggestions) {
//			System.out.println(auto.getText());
//			if(auto.getText().equalsIgnoreCase("Sunny Deol")) {
//				auto.click();	
//			}
//		}
		for(int i=0;i<count;i++){ 
			System.out.println(autoSuggestions.get(i).getText());
		}
		autoSuggestions.get(count-5).click();
	}
}
