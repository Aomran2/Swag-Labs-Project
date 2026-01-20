package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static Utilities.DataUtils.getPropertyData;
import static Utilities.Utility.clickOnElement;
import static Utilities.Utility.sendKeysToElement;

public class CheckoutPage {

    private final WebDriver driver;

    private final By checkoutFirstName = By.id("first-name");
    private final By checkoutLastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage fillCheckoutData() throws IOException {
        sendKeysToElement(driver, checkoutFirstName, getPropertyData("Environment", "firstName"));
        sendKeysToElement(driver, checkoutLastName, getPropertyData("Environment", "lastName"));
        sendKeysToElement(driver, postalCode, getPropertyData("Environment", "postalCode"));
        return this;
    }

    public OverviewPage clickContinue() {
        clickOnElement(driver, continueButton);
        return new OverviewPage(driver);
    }


}
