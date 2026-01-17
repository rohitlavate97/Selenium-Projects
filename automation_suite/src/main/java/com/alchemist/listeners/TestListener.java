package com.alchemist.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import com.aventstack.extentreports.*;
import com.alchemist.utils.*;

public class TestListener implements ITestListener {

    private static final Logger log =
            LogManager.getLogger(TestListener.class);

    private static ExtentReports extent = ExtentManager.getReport();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        log.info("TEST STARTED: {}", result.getName());
        test.set(extent.createTest(result.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("TEST PASSED: {}", result.getName());
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("TEST FAILED: {}", result.getName(), result.getThrowable());
        String path = ScreenshotUtil.capture(result.getName());
        test.get().fail(result.getThrowable())
                  .addScreenCaptureFromPath(path);
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("TEST SUITE FINISHED: {}", context.getName());
        extent.flush();
    }
}
