package com.alchemist.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import com.alchemist.utils.ConfigReader;

public class BaseTest {

    protected static final Logger log =
            LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setup() {
        log.info("========== Test Setup Started ==========");
        DriverFactory.initDriver();
        DriverFactory.getDriver().manage().window().maximize();
        String url = ConfigReader.get("URL");
        DriverFactory.getDriver().get(url);
        log.info("Navigated to URL: {}", url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        log.info("========== Test Teardown ==========");
        DriverFactory.quitDriver();
    }
}
