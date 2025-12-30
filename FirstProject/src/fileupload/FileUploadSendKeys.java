package fileupload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUploadSendKeys {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/upload");
        
        Thread.sleep(3000);

        driver.findElement(By.id("file-upload"))
              .sendKeys("C:\\Users\\Admin\\Downloads\\wsdl.pdf");

        driver.findElement(By.id("file-submit")).click();
        
        Thread.sleep(3000);
        driver.quit();
    }
}

