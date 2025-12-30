package testClasses;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTables {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.tutorialspoint.com/selenium/practice/webtables.php");
		Thread.sleep(2000);
		List<WebElement> header = driver.findElements(By.xpath("//table/thead/tr/th"));
		header.forEach(h->System.out.println(h.getText()));
		
		List<WebElement> salary = driver.findElements(By.xpath("//table/tbody/tr/td[5]"));
		salary.forEach(s->System.out.println(s.getText()));
		
		List<WebElement> rows = driver.findElements(By.xpath("//tbody/tr"));
		for(int i=0;i<rows.size();i++) {
			List<WebElement> cells = driver.findElements(By.xpath("//tbody/tr["+i+"]/td"));
			for(int j=0;j<cells.size();j++) {
				System.out.println(cells.get(j).getText());
			}
		}
	}

}
