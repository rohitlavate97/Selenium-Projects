package testClasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchWithSetProperty {

	public static void main(String[] args) {
		// STEP 1: Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver",
                "D:\\Testing Prep\\chromedriver.exe");

        // STEP 2: Create WebDriver instance
        WebDriver driver = new ChromeDriver();

        // STEP 3: Maximize browser
        driver.manage().window().maximize();

        // STEP 4: Open URL
        driver.get("https://www.google.com");

        // STEP 5: Print page title
        System.out.println("Page Title is: " + driver.getTitle());

        // STEP 6: Close browser
        driver.quit();

	}

}
