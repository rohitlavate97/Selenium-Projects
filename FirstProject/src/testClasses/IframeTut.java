package testClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class IframeTut {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		driver.manage().window().maximize();
		driver.get("https://www.hyrtutorials.com/p/frames-practice.html");
		Thread.sleep(2000);
		WebDriver frame1 = driver.switchTo().frame("frm1");
		//js.executeScript("arguments[0].scrollIntoView(true);", frame1);
		WebElement drp = frame1.findElement(By.xpath("//select[@id='selectnav1']"));
		Select select = new Select(drp);
		select.selectByIndex(3);
		Thread.sleep(2000);
		
		driver.switchTo().defaultContent();

		WebElement iframe2 = driver.findElement(By.xpath("//iframe[@id='frm2']"));
		WebDriver frame2 = driver.switchTo().frame(iframe2);
		frame2.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Rohit");
		
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//input[@class='frmTextBox']")).sendKeys("End of the Automation");
		Thread.sleep(2000);
		driver.quit();
	}
}
