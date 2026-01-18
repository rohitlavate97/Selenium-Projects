package com.alchemist.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import com.alchemist.utils.CleanupUtil;
import com.alchemist.utils.ConfigReader;

public class BaseTest {

	protected static final Logger log = LogManager.getLogger(BaseTest.class);
	
	/* as already done with help of listeners
	 * @BeforeSuite(alwaysRun = true) public void cleanUpBeforeSuite() {
	 * System.out.println("===== Cleaning old reports & screenshots =====");
	 * CleanupUtil.cleanAll(); }
	 */

	@Parameters({ "browser", "env" })
	@BeforeMethod
	public void setup(@Optional("chrome") String browser, @Optional("qa") String env) {
		log.info("========== Test Setup Started ==========");
		ConfigReader.loadEnv(env); // Load QA/UAT/PROD config
		DriverFactory.initDriver(browser); // Cross-browser + Grid support
		DriverFactory.getDriver().get(ConfigReader.get("URL"));
		log.info("Navigated to URL: " + ConfigReader.get("URL"));
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		log.info("========== Test Teardown ==========");
		DriverFactory.quitDriver();
	}
}
