package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    // we do three steps with threadLocal List -> Set - get - remove


    public static void setupDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            driverThreadLocal.set(new ChromeDriver());
        } else {
            driverThreadLocal.set(new EdgeDriver());
        }
    }


    public static WebDriver getDriver() {

        return driverThreadLocal.get();
    }


    public static void quitDriver() {

        getDriver().quit();
        driverThreadLocal.remove();
    }
}
