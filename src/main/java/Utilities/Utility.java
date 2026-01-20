package Utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utility {

    private static final String SCREENSHOT_PATH = "test-outputs/screenshots/";

    public static void sendKeysToElement(WebDriver driver, By locator, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

        driver.findElement(locator).sendKeys(text);
    }


    public static void clickOnElement(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }


    public static WebElement byToWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public static void scrollToElement(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView();", byToWebElement(driver, locator));
    }

    public static void takeScreenshot(WebDriver driver, String name) throws IOException {

        try {
            //capture screenshot using TakeScreenshot
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            //saving screenshot to a file
            File destFile = new File(SCREENSHOT_PATH + name + "_" + getTimeStamp() + ".png");
            FileUtils.copyFile(file, destFile);

            //attach the screenshot to the Allure report
            Allure.addAttachment(name, Files.newInputStream(Path.of(destFile.getPath())));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static String getTimeStamp() {

        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }


    public static void selectFromDropdown(WebDriver driver, By locator, String option) {

        new Select(byToWebElement(driver, locator)).selectByVisibleText(option);
    }


    //this function wait for 5 seconds and get text of the desired element
    public static String getText(WebDriver driver, By locator) {

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    //this function generate random number, upperbound is ignored this is why I add 1
    public static int generateRandomNumber(int upperBound) {
        return new Random().nextInt(upperBound) + 1;
    }

    //this method generate unique numbers because Set<> accept only unique numbers
    public static Set<Integer> generateUniqueNumber(int numbersNeeded, int totalNumbers) {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < numbersNeeded) {
            /*
            if the numbers inside the set are less than needed numbers
             generate random numbers from generateRandomNumber()
             */
            generatedNumbers.add(generateRandomNumber(totalNumbers));
        }
        return generatedNumbers;
    }

    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public static boolean verifyUrl(WebDriver driver, String expectedUrl) {

        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedUrl));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static WebElement findWebElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

}
