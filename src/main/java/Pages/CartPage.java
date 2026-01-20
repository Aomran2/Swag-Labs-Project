package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Utilities.Utility.clickOnElement;
import static Utilities.Utility.getText;

public class CartPage {

    private final WebDriver driver;
    private final By numberOfSelectedProductsInCart = By.xpath("//button[.='Remove']");
    private final By checkoutButton = By.id("checkout");
    private By productPrice = By.xpath("//button[.='Remove']//preceding-sibling::div[@class='inventory_item_price']");
    private float totalPrices = 0;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    //verify the cart page url
//    public boolean verifyCartPageUrl(String cartPageUrl) throws IOException {
//        try {
//            generalWait(driver)
//                    .until(ExpectedConditions
//                            .urlToBe(cartPageUrl));
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }


    public String numberOfSelectedProductsInCart() {

        try {
            List<WebElement> selectedProductsInCart = driver.findElements(numberOfSelectedProductsInCart);
            return String.valueOf(selectedProductsInCart.size());
        } catch (Exception e) {
            e.getStackTrace();
            return "0";
        }
    }

    public String getTotalPricesForProductsInCart() {

        try {
            List<WebElement> elements = driver.findElements(productPrice);
            for (int i = 1; i < elements.size(); i++) {
                //dynamic locator
                By element = By.xpath("(//button[.='Remove']//preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                //get full text and store it as String -> $25
                String fullText = getText(driver, element);
                //convert it to float and remove the $ to be able to calculate the numbers
                totalPrices += Float.parseFloat(fullText.replace("$", ""));
            }
            return String.valueOf(totalPrices);
        } catch (Exception e) {
            return "0";
        }
    }

    //compare between total price in cart and price in home page (which will be passed to method parameter)
    public boolean compareTotalPrice(String price) {
        return getTotalPricesForProductsInCart().equals(price);
    }

    public CheckoutPage clickOnCheckout() {
        clickOnElement(driver, checkoutButton);
        return new CheckoutPage(driver);
    }
}
