package fileupload;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUploadRobot {

    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/upload");

        driver.findElement(By.id("file-upload")).click();

        Robot robot = new Robot();
        robot.delay(2000);

        StringSelection file = new StringSelection("C:\\Users\\Public\\testfile.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        driver.findElement(By.id("file-submit")).click();
        driver.quit();
    }
}
