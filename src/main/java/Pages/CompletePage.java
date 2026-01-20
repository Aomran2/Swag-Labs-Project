package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.findWebElement;


public class CompletePage {
    private final WebDriver driver;
    private final By completeMessage = By.className("complete-header");


    public CompletePage(WebDriver driver) {
        this.driver = driver;
    }

//    public String getCompleteMessage() {
//        return getText(driver, completeMessage);
//    }

    public boolean checkVisibilityOfCompleteMessage() {
        return findWebElement(driver, completeMessage).isDisplayed();
    }
}
