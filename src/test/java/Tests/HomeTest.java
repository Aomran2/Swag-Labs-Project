package Tests;

import Listeners.ITestListenerClass;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LoginPage;
import Utilities.DataUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.*;
import static Utilities.DataUtils.getPropertyData;
import static Utilities.Utility.verifyUrl;

@Listeners(ITestListenerClass.class)
public class HomeTest {

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

//    @Test
//    public void addAllProductsToCart() throws IOException {
//        new LoginPage(getDriver())
//                .enterUsername(DataUtils.getJsonData("LoginTestData", "username"))
//                .enterPassword(DataUtils.getJsonData("LoginTestData", "password"))
//                .clickOnLoginButton().addAllProductsToCart();
//
//        soft.assertTrue(new HomePage(getDriver()).compareNumberOfSelectedProductsWithCart(), "comparing assertion");
//        soft.assertAll();
//    }

    @Test
    public void addRandomProductsToCart() throws IOException {
        new LoginPage(getDriver())
                .enterUsername(DataUtils.getJsonData("LoginTestData", "username"))
                .enterPassword(DataUtils.getJsonData("LoginTestData", "password"))
                .clickOnLoginButton().addRandomProducts(3, 6);

        soft.assertTrue(new HomePage(getDriver()).compareNumberOfSelectedProductsWithCart(), "comparing assertion");
        soft.assertAll();
    }

    @Test
    public void addRandomProductsThenMoveToCart() throws IOException {
        new LoginPage(getDriver())
                .enterUsername(DataUtils.getJsonData("LoginTestData", "username"))
                .enterPassword(DataUtils.getJsonData("LoginTestData", "password"))
                .clickOnLoginButton().addRandomProducts(3, 6).moveToCartPage();

        soft.assertTrue(verifyUrl(getDriver(), getPropertyData("Environment", "cart")), "cart url assertion");
        soft.assertEquals(new CartPage(getDriver()).numberOfSelectedProductsInCart(), new HomePage(getDriver())
                .getNumberOfSelectedProducts(), "number of products in cart assertion");

        soft.assertEquals(new HomePage(getDriver()).getTotalPricesForSelectedProductsInHomePage(),
                new CartPage(getDriver()).getTotalPricesForProductsInCart(), "total price assertion between selected products in home page and cart");

        soft.assertTrue(new CartPage(getDriver())
                .compareTotalPrice(new HomePage(getDriver())
                        .getTotalPricesForSelectedProductsInHomePage()), "total price assertion between selected products in home page and cart");

        soft.assertAll();
    }

    @AfterMethod
    public void closeBrowser() {
//        driver.quit();
        quitDriver();
    }
}
