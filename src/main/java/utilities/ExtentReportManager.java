package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;


public class ExtentReportManager {
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static String reportName;
    public static void startReporting() throws MalformedURLException, InterruptedException {
        String currentDate = getTime().toString();
        currentDate = currentDate.replaceAll(":","_");
        currentDate = currentDate.replaceAll("\\.","-");
        reportName = System.getProperty("user.dir") + "//reports//extentReports//" + "extentReport" + "_" + currentDate + ".html";
        htmlReporter = new ExtentHtmlReporter(reportName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }
    private static Date getTime() {
        Calendar calender = Calendar.getInstance();
        return calender.getTime();
    }
    public static void beforeMethod(Method method) throws IOException {
        test = extent.createTest(method.getName());

    }
    public static void takeScreenshot(ITestResult result, WebDriver driver)
            throws IOException, InterruptedException {

        String baseDir = System.getProperty("user.dir") + "//reports//";
        String pathFail = baseDir + "screenshotsFailure//" + result.getName() + ".png";
        String PathSkip = baseDir + "screenshotsSkip//" + result.getName() + ".png";
        String pathSuccess = baseDir + "screenshotsSuccess//" + result.getName() + ".png";

        // Create directories if they don't exist
        createDirectory(baseDir + "screenshotsFailure");
        createDirectory(baseDir + "screenshotsSkip");
        createDirectory(baseDir + "screenshotsSuccess");

        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                test.fail(result.getThrowable().getMessage(), MediaEntityBuilder
                        .createScreenCaptureFromPath(Utilities.captureScreenshot(pathFail, driver)).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ITestResult.SUCCESS == result.getStatus()) {
            try {
                test.pass("test has passed: ", MediaEntityBuilder
                        .createScreenCaptureFromPath(Utilities.captureScreenshot(pathSuccess, driver)).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ITestResult.SKIP == result.getStatus()) {
            try {
                test.skip(result.getThrowable().getMessage(), MediaEntityBuilder
                        .createScreenCaptureFromPath(Utilities.captureScreenshot(PathSkip, driver)).build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + path);
            } else {
                System.err.println("Failed to create directory: " + path);
            }
        }
    }
    public static void flushReport(){
        extent.flush();
    }
}
