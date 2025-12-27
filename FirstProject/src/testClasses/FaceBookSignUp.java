package testClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FaceBookSignUp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String browser = sc.next();
		WebDriver driver = null;
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Testing Prep\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		try {
			driver.get("https://www.facebook.com/r.php?entry_point=login");
			Thread.sleep(3000);
			WebElement fname = driver.findElement(By.xpath("//input[@name='firstname']"));
			WebElement lname = driver.findElement(By.xpath("//input[@name='lastname']"));
			List<WebElement> ele = new ArrayList();
			ele = driver.findElements(By.xpath("//label[@class='_58mt']"));
			for(int i=0;i<=2;i++) {
				System.out.println(ele.get(i).getText());
			}
			WebElement maleRadio = driver.findElement(By.xpath("//input[@value='2']"));
			fname.sendKeys("Rahul");
			lname.sendKeys("Gandi");
			maleRadio.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}

}
