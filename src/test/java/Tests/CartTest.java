package Tests;

import Listeners.ITestListenerClass;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getJsonData;
import static Utilities.DataUtils.getPropertyData;

@Listeners(ITestListenerClass.class)
public class CartTest {

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
    public void validatePriceBetweenSelectedProductsAndCart() throws FileNotFoundException {
        String totalPrice = new LoginPage(getDriver())
                .enterUsername(getJsonData("LoginTestData", "username"))
                .enterPassword(getJsonData("LoginTestData", "password"))
                .clickOnLoginButton().addRandomProducts(2, 6)
                .getTotalPricesForSelectedProductsInHomePage();

        new HomePage(getDriver()).moveToCartPage();
        soft.assertTrue(new CartPage(getDriver()).compareTotalPrice(totalPrice));
        soft.assertAll();
    }

    @AfterMethod
    public void closeBrowser() {
//        driver.quit();
        quitDriver();
    }
}
