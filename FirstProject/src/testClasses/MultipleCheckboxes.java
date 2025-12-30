package testClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MultipleCheckboxes {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.get("https://proleed.academy/exercises/selenium/automation-practice-form-with-radio-button-check-boxes-and-drop-down.php");
		Thread.sleep(2000);
		List<WebElement> elements = driver.findElements(By.xpath("//input[@type='checkbox']"));
//		elements.forEach(ele -> {
//            String id = ele.getAttribute("id");
//
//            if ("passport".equals(id) || "votercard".equals(id)) {
//                ele.click();
//            }
//        });
		for(WebElement ele : elements) {
			if(ele.getAttribute("id").equals("passport") || ele.getAttribute("id").equals("votercard")) {
				js.executeScript("arguments[0].scrollIntoView(true);", ele);
                Thread.sleep(500);
                if (!ele.isSelected()) {
                    ele.click();
                }
			}
		}
		Thread.sleep(2000);
		driver.quit();
	}
	
	

}
