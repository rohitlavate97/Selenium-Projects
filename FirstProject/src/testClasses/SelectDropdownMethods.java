package testClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectDropdownMethods {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://proleed.academy/exercises/selenium/automation-practice-form-with-radio-button-check-boxes-and-drop-down.php");
		Thread.sleep(2000);
		WebElement months = driver.findElement(By.xpath("//select[@id='dob_month']"));
		Select select1 = new Select(months);
		System.out.println(select1.getOptions()+"  "+select1.isMultiple());
		boolean multiple = select1.isMultiple();
		System.out.println(multiple);
		select1.selectByIndex(11);
		Thread.sleep(2000);
		WebElement date = driver.findElement(By.xpath("//select[@id='dob_date']"));
		Select select2 = new Select(date);
		select2.selectByVisibleText("7");
		Thread.sleep(2000);
		WebElement year = driver.findElement(By.xpath("//select[@id='dob_year']"));
		Select select3 = new Select(year);
		select3.selectByVisibleText("1999");
		Thread.sleep(2000);
		WebElement nationality = driver.findElement(By.xpath("//select[@id='nationality']"));
		Select select4 = new Select(nationality);
		select4.selectByValue("indian");
		Thread.sleep(2000);
		select4.deselectByValue("indian");;
		List<WebElement> web = select4.getAllSelectedOptions();
		web.forEach(ele->
		System.out.println(ele.getText())
		);
		Thread.sleep(2000);
		driver.quit();

	}

}
