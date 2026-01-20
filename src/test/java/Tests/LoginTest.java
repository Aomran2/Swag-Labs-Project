package Tests;

import Listeners.ITestListenerClass;
import Pages.LoginPage;
import Utilities.DataUtils;
import Utilities.Utility;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyData;

@Listeners(ITestListenerClass.class)
public class LoginTest {

    private final SoftAssert soft = new SoftAssert();
//    private WebDriver driver;


    @BeforeMethod
    public void setup() throws IOException {
//        driver = new EdgeDriver();
        setupDriver(getPropertyData("Environment", "browser"));
        getDriver().manage().window().maximize();
        getDriver().get(getPropertyData("Environment", "url"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void validLoginTest() throws IOException {
        new LoginPage(getDriver())
                .enterUsername(DataUtils.getJsonData("LoginTestData", "username"))
                .enterPassword(DataUtils.getJsonData("LoginTestData", "password"))
                .clickOnLoginButton();
        Utility.takeScreenshot(getDriver(), "Home page");


        soft.assertEquals(getDriver().getCurrentUrl(), getPropertyData("Environment", "home"));
        soft.assertTrue(new LoginPage(getDriver()).assertLogin(getPropertyData("Environment", "home")));
        soft.assertAll();


    }

    @AfterMethod
    public void closeBrowser() {
//        driver.quit();
        quitDriver();
    }
}
