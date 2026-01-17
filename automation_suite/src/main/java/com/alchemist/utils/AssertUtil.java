package com.alchemist.utils;

import org.testng.asserts.SoftAssert;

public class AssertUtil {

    private SoftAssert softAssert = new SoftAssert();

    public void assertEqualsSoft(String actual, String expected, String msg) {
        softAssert.assertEquals(actual, expected, msg);
    }

    public void assertTrueSoft(boolean condition, String msg) {
        softAssert.assertTrue(condition, msg);
    }

    public void assertAll() {
        softAssert.assertAll();
    }
}
