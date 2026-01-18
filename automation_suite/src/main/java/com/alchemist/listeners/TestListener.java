package com.alchemist.listeners;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;
import com.aventstack.extentreports.*;
import com.alchemist.utils.*;

public class TestListener implements ITestListener {

	private static final Logger log = LogManager.getLogger(TestListener.class);

	private static ExtentReports extent = ExtentManager.getReport();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onStart(ITestContext context) {

		log.info("==========================================");
		log.info("TEST SUITE STARTED");
		log.info("Suite Name   : {}", context.getName());
		log.info("Start Time   : {}", context.getStartDate());
		log.info("==========================================");

		log.info("Starting cleanup of previous execution artifacts");
		CleanupUtil.cleanAll();

		log.info("Creating Extent Report instance");
		extent = ExtentManager.getReport();
	}

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

	/*
	 * @Override public void onTestFailure(ITestResult result) {
	 * log.error("TEST FAILED: {}", result.getName(), result.getThrowable()); String
	 * path = ScreenshotUtil.capture(result.getName());
	 * test.get().fail(result.getThrowable()) .addScreenCaptureFromPath(path); }
	 */

	@Override
	public void onTestFailure(ITestResult result) {
		log.error("TEST FAILED: {}", result.getName(), result.getThrowable());

		String screenshotPath = ScreenshotUtil.capture(result.getName());
		String errorLogPath = LogUtil.getErrorLogPath();

		// 🔥 Attach screenshot to Allure
		ScreenshotUtil.attachToAllure();

		test.get().fail(result.getThrowable());
		test.get().addScreenCaptureFromPath(screenshotPath);

		test.get().info("Error log attached below:");
		test.get().info(errorLogPath);
	}

	@Override
	public void onFinish(ITestContext context) {

		log.info("===== TEST SUITE FINISHED =====");
		log.info("Suite Name   : {}", context.getName());
		log.info("Passed Tests : {}", context.getPassedTests().size());
		log.info("Failed Tests : {}", context.getFailedTests().size());
		log.info("Skipped Tests: {}", context.getSkippedTests().size());

		// Flush Extent
		log.info("Flushing Extent Report");
		extent.flush();

		// Email Subject
		String subject = context.getFailedTests().size() > 0 ? "❌ Automation FAILED - " + context.getName()
				: "✅ Automation PASSED - " + context.getName();

		// HTML Email Body
		String htmlBody = EmailContentBuilder.buildHtmlSummary(context);

		// Collect Attachments
		File[] attachments = AttachmentUtil.collectAttachments();

		log.info("Sending HTML execution email with attachments");
		EmailUtil.sendEmail(subject, htmlBody, attachments);

		log.info("===== EMAIL SENT SUCCESSFULLY =====");
	}

}
