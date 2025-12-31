package testng;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGAnnoDemo {
	@BeforeMethod
	public void beforeMethod() {
		Reporter.log("beforeMethod",true);
	}
	
	@AfterMethod
	public void afterMethod() {
		Reporter.log("afterMethod",true);
	}
	
	@Test
	public void testMethod() {
		Reporter.log("Test",true);
	}
}
