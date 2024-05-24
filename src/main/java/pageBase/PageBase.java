package pageBase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageBase {
    public WebDriver driver;
    public static boolean flag = true;

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public static String date;
    public final By dismiss = By.xpath("//*[@aria-label='Dismiss sign-in info.']");

    public void clickOnElement(By by) {
        waitForVisibilityOfElement(by);
        driver.findElement(by).click();

    }

    public void dismissLoginPopup() {
        if (flag) {
            waitForTime(5000);
            driver.findElement(dismiss).click();
            System.out.println("popup dismissed once");
            flag = false;
        }
    }

    public void waitForVisibilityOfElement(By by) {
        WebDriverWait wait;
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            dismissLoginPopup();
            wait = new WebDriverWait(driver, Duration.ofSeconds(120));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        }

    }

    public Boolean assertElementDisplayed(By by) {
        waitForVisibilityOfElement(by);
        return driver.findElement(by).isDisplayed();
    }

    public void scrollToElement(By element) {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        try {
            jsExec.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", driver.findElement(element));
        } catch (Exception e) {
            dismissLoginPopup();
            jsExec.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", driver.findElement(element));
        }
    }

    public void sendTextToInputField(String text, By by) {
        waitForVisibilityOfElement(by);
        clearInputField(by);
        driver.findElement(by).sendKeys(text);
    }

    public void clearInputField(By by) {
        driver.findElement(by).clear();
    }


    public void waitForTime(int timeIntoMilSec) {
        try {
            Thread.sleep(timeIntoMilSec);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void ElementsValidator(By... screenLocators) {
        for (By screenLocator : screenLocators) {
            scrollToElement(screenLocator);
            Assert.assertTrue(assertElementDisplayed(screenLocator));
        }
    }

    public void switchScreen(By redirectElement, By elementToClickOnIt) {
        String originalWindow = driver.getWindowHandle();
        try {
            scrollToElement(redirectElement);
            driver.findElement(redirectElement).findElement(elementToClickOnIt).click();
        } catch (Exception e) {
            e.getStackTrace();
            dismissLoginPopup();
            scrollToElement(redirectElement);
            driver.findElement(redirectElement).findElement(elementToClickOnIt).click();
        }
        waitForNumberOfWindowsToBe(2);
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                System.out.println(driver.getCurrentUrl());
                waitForPageToLoad();
                break;
            }
        }
    }

    private void waitForNumberOfWindowsToBe(int numberOfWindows) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver ->
                driver.getWindowHandles().size() == numberOfWindows);
    }

    private void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    public void selectOptionByIndex(By selectLocator, int index) {
        WebElement selectElement = driver.findElement(selectLocator);
        Select select = new Select(selectElement);
        select.selectByIndex(index);
    }


    public String extractDatesSplit(String dateString, int whichDate1ForCheckInAnd2ForCheckout) {
        String[] dates = dateString.split("—");
        String firstDate = dates[0].trim();
        String secondDate = dates[1].trim();
        if (whichDate1ForCheckInAnd2ForCheckout == 1)
            return firstDate;
        else {
            return secondDate;
        }


    }


}
