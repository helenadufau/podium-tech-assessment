package com.podium.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    private static String getTestName(ITestResult result) {

        return result.getMethod().getConstructorOrMethod().getName();

    }

    @Override
    public void onStart(ITestContext context) {

        separationBarLog();
        driverInformationLog();

    }

    private void driverInformationLog() {
        logger.info("Driver information:");

    }

    @Override
    public void onFinish(ITestContext context) {

        logger.info("@|bold All tests finished!|@");
        separationBarLog();

    }

    @Override
    public void onTestStart(ITestResult result) {

        separationBarLog();
        logTestName(result);
        logStartedAt(result);

    }

    @Override
    public void onTestSuccess(ITestResult result) {

        logStatus(result);
        durationLog(result);

    }

    @Override
    public void onTestFailure(ITestResult result) {

        logStatus(result);
        durationLog(result);

    }

    @Override
    public void onTestSkipped(ITestResult result) {

        logStatus(result);
        durationLog(result);

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

        logStatus(result);
        durationLog(result);

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

        logStatus(result);

    }

    private void separationBarLog() {

        logger.info("---------------------------------------------------------------------------");

    }

    private void logTestName(ITestResult result) {

        logger.info("TEST: " + getTestName(result));

    }


    private void logStartedAt(ITestResult result) {

        logger.info("  STARTED: " + new Date(result.getStartMillis()));

    }

    private void logStatus(ITestResult result) {

        switch (result.getStatus()) {
            case 1:
                logger.info("   STATUS: @|green succeed|@");
                break;
            case 2:
                if (ITestResult.wasFailureDueToTimeout(result)) {
                    logger.info("   STATUS: @|red failed (timeout)|@");
                } else {
                    logger.info("   STATUS: @|red failed|@");
                }
                break;
            case 3:
                logger.info("   STATUS: @|magenta skipped|@");
                break;
            case 4:
                logger.info("   STATUS: @|yellow failed but within success percentage|@");
                break;
            default:
                logger.info("   STATUS: result undefined");

        }
    }

    private void durationLog(ITestResult result) {

        logger.info("    DURATION: " + testDurationCalculation(result) + "s");
        separationBarLog();

    }

    private long testDurationCalculation(ITestResult result) {

        return (result.getEndMillis() - result.getStartMillis()) / 1000;

    }

}
