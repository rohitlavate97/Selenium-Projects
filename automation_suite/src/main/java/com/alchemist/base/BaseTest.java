package com.alchemist.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import com.alchemist.utils.ConfigReader;

public class BaseTest {

    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    @Parameters({"browser","env"})
    @BeforeMethod
    public void setup(String browser, String env) {
        log.info("========== Test Setup Started ==========");
        ConfigReader.loadEnv(env);  // Load env: QA/UAT/PROD
        DriverFactory.initDriver(browser);  // Cross-browser
        DriverFactory.getDriver().get(ConfigReader.get("URL"));
        log.info("Navigated to URL: " + ConfigReader.get("URL"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("========== Test Teardown ==========");
        DriverFactory.quitDriver();
    }
}
