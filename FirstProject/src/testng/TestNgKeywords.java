package testng;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNgKeywords {
	@BeforeMethod
	public void beforeMethod() {
		Reporter.log("beforeMethod");
	}
	
	@AfterMethod
	public void afterMethod() {
		Reporter.log("afterMethod");
	}
	
	@Test(priority = 1,invocationCount=2,groups="sanity")
	public void testA() {
		Reporter.log("Executing first test");
	}
	
	@Test(priority = 2,groups="regression",enabled=true)
	public void testB() {
		Reporter.log("Executing second test");
	}
	
	@Test(priority = 3,groups= {"sanity","regression"})
	public void testC() {
		Reporter.log("Executing third test");
	}
}
