package gridTests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SeleniumGridDemo {
	@Test
	@Parameters({"node","browser"})
	public void LaunchBrowsers(String node, String browser) throws MalformedURLException {
		System.out.println(browser);
		URL whichSystem = new URL(node);
		DesiredCapabilities whichBrowser = new DesiredCapabilities();
		whichBrowser.setBrowserName(browser);
		WebDriver driver = new RemoteWebDriver(whichSystem,whichBrowser);
		System.out.println("Launched :"+browser);
		driver.quit();
	}
}
