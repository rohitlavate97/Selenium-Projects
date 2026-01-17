package com.alchemist.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.alchemist.base.*;
import com.alchemist.pages.LoginPage;
import com.alchemist.retry.RetryAnalyzer;
import com.alchemist.utils.ExcelUtil;

public class LoginTest extends BaseTest {

    private static final Logger log =
            LogManager.getLogger(LoginTest.class);

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void validLoginTest() {

        log.info("Starting Login Test");

        LoginPage page = new LoginPage();

        String user = ExcelUtil.getData("ValidLogin",1,0);
        String pass = ExcelUtil.getData("ValidLogin",1,1);
        String expectedTitle =
                ExcelUtil.getData("ValidLogin",1,2);

        log.info("Attempting login with user: {}", user);
        page.login(user, pass);

        String actualTitle = DriverFactory.getDriver().getTitle();
        log.info("Validating page title");

        Assert.assertEquals(actualTitle, expectedTitle);

        log.info("Login Test Completed Successfully");
    }
}
