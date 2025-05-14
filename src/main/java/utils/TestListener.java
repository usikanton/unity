package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log.info(String.format("Test '%s' started", result.getMethod().getMethodName().toUpperCase()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.step(result.getMethod().getMethodName().toUpperCase() + " passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            String errorMessage = throwable.getMessage();
            Allure.addAttachment("Test Failure Logs", new ByteArrayInputStream(errorMessage.getBytes(StandardCharsets.UTF_8)));
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.step(result.getMethod().getMethodName().toUpperCase() + " was skipped", Status.SKIPPED);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        try {
            Process p = Runtime.getRuntime().exec("allure serve");
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
