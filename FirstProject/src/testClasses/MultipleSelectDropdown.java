package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class MultipleSelectDropdown {

	public static void main(String[] args) throws InterruptedException {
		// https://demo.mobiscroll.com/select/multiple-select
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.mobiscroll.com/select/multiple-select");
		Thread.sleep(2000);
		WebElement muldrp = driver.findElement(By.xpath("//select[@id='multiple-select-select']"));
		Select select = new Select(muldrp);
		select.selectByValue("1");
		select.selectByVisibleText("Toys, Kids & Baby");
		select.selectByIndex(3);
		
	}

}
