package testClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class GmailLogin {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = null;
		try {
			driver = new ChromeDriver();
			driver.get("https://www.google.com");
			Thread.sleep(2000);
			WebElement element = driver.findElement(By.xpath("//a[text()='Gmail']"));
			Dimension d = element.getSize();
			System.out.println("Width: "+d.getWidth()+" Height: "+d.getHeight());
			Point location = element.getLocation();
			System.out.println("X: "+location.getX()+" Y: "+location.getY());
			element.click();
			System.out.println("The logo is available: "+driver.findElement(By.xpath("//img[contains(@title,'Google Gmail')]")).isDisplayed());
			driver.findElement(By.xpath("//a[@data-g-action='sign in'][2]")).click();
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys("avengerssquadwebdm@gmail.com");
			driver.findElement(By.xpath("//span[text()='Next']")).click();
			//System.out.println(driver.findElement(By.xpath("//a[text()='Learn more']")).getText());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();

	}

}
