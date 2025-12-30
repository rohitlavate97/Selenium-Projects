package testClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandlingWindows {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hdfc.bank.in/");
		String firstWindow = driver.getWindowHandle();
		Thread.sleep(2000);
		WebElement netBanking = driver.findElement(By.xpath("//a[@title='NetBanking']"));
		netBanking.click();
		String secondWindow = driver.getWindowHandle();
		System.out.println(secondWindow);
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println(allWindows);
		Thread.sleep(3000);
		List<String> al = new ArrayList(allWindows);
		driver.switchTo().window(al.get(0));
	}

}
