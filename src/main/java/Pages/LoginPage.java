package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class LoginPage {

    private final WebDriver driver;
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage enterUsername(String username) {
        Utility.sendKeysToElement(driver, usernameField, username);
        return this;
    }


    public LoginPage enterPassword(String password) {
        Utility.sendKeysToElement(driver, passwordField, password);
        return this;
    }

    public HomePage clickOnLoginButton() {
        Utility.clickOnElement(driver, loginButton);
        return new HomePage(driver);
    }

    public boolean assertLogin(String expected) {
//      return driver.getCurrentUrl().equals(expected);
        return Objects.equals(driver.getCurrentUrl(), expected);
    }
}
