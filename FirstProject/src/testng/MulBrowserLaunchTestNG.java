package testng;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MulBrowserLaunchTestNG {
	static {
		System.setProperty("webdriver.chrome.driver", "D:\\Testing Prep\\chromedriver.exe");
		System.setProperty("webdriver.edge.driver", "D:\\\\Testing Prep\\\\msedgedriver.exe");
	}
	WebDriver driver;
	
	@Test
	@Parameters({"browser"})
	public void lauchMulBroserTest(String browser) throws IOException {
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		}else {
			driver = new EdgeDriver();
		}
		
		try {
			FileInputStream config = new FileInputStream(".\\src\\config.properties");
			Properties prop = new Properties();
			prop.load(config);
			String url = prop.getProperty("URL");
			driver.get(url);
			driver.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
