package fileupload;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUploadJSExecutor {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/upload");

        WebElement upload = driver.findElement(By.id("file-upload"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "arguments[0].style.display='block'; arguments[0].value=arguments[1];",
            upload,
            "C:\\Users\\Admin\\Downloads\\wsdl.pdf"
        );

        driver.findElement(By.id("file-submit")).click();
        driver.quit();
    }
}
