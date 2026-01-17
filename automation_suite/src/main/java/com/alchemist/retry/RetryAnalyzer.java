package com.alchemist.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.alchemist.constants.FrameworkConstants;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int count = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (count < FrameworkConstants.RETRY_COUNT) {
            count++;
            return true;
        }
        return false;
    }
}
