package testClasses;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ManageCookies {

	public static void main(String[] args) {
		//To open the browser
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		try {
			Thread.sleep(3000);
			//To delete cookies
		    driver.manage().deleteAllCookies();
		  //To set the size of the window
		    Dimension d = new Dimension(500,500);
		    driver.manage().window().setSize(d);
		  //To set the position of the window
		    Point p = new Point(250,250);
		    driver.manage().window().setPosition(p);
			Thread.sleep(3000);
			//To maximize the window
			driver.manage().window().maximize();
			driver.get("https://www.udemy.com/");
			Thread.sleep(3000);
		 }catch(Exception e){
			e.printStackTrace();
		}
		driver.quit();
	}

}
