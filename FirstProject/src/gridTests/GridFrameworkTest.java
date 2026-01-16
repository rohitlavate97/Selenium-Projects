package gridTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.URL;

public class GridFrameworkTest {

    WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setup(String browser) throws Exception {

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"), options);
        }
        else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"), options);
        }
        else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"), options);
        }
    }

    @Test
    public void openGoogle() {
        driver.get("https://online.actitime.com/mstar/login.do");
        System.out.println("Title: " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
