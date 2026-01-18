package com.alchemist.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.alchemist.base.BaseTest;
import com.alchemist.pages.LoginPage;
import com.alchemist.retry.RetryAnalyzer;
import com.alchemist.utils.ExcelUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import com.alchemist.utils.AssertUtil;
import com.alchemist.base.DriverFactory;

public class LoginTest extends BaseTest {
	@Epic("Authentication")
	@Feature("Login Feature")
	@Story("Valid Login")
	@Severity(SeverityLevel.CRITICAL)
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void validLoginTest() {
        LoginPage page = new LoginPage();
        AssertUtil assertUtil = new AssertUtil();

        String user = ExcelUtil.getData("ValidLogin",1,0);
        String pass = ExcelUtil.getData("ValidLogin",1,1);
        String expectedTitle = ExcelUtil.getData("ValidLogin",1,2);

        page.login(user, pass);

        String actualTitle = DriverFactory.getDriver().getTitle();

        // Soft assertion
        assertUtil.assertEqualsSoft(actualTitle, expectedTitle, "Page title validation");
        assertUtil.assertAll();

        // Hard assertion example
        Assert.assertEquals(actualTitle, expectedTitle, "Final check on page title");
    }
}
