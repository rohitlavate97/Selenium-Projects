package testClasses;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class FluentWaitDemo {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.hyrtutorials.com/p/waits-demo.html");
        
        WebElement addText = driver.findElement(By.xpath("//button[text()='Add Textbox1']"));
		addText.click();

        // ✅ Fluent Wait declaration
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))      // Max wait time
                .pollingEvery(Duration.ofSeconds(2))      // Check every 2 seconds
                .ignoring(NoSuchElementException.class);   // Ignore exception

        // ✅ Wait until element is visible
        WebElement textbox = wait.until(d ->
                d.findElement(By.id("txt1")));

        textbox.sendKeys("Rohit");
        Thread.sleep(3000);

        driver.quit();
    }
}

