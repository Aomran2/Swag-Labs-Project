package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

import static Utilities.Utility.*;

public class HomePage {

    private final By addToCartBtn = By.xpath("//button[@class]");
    private final By numberOfItemsInCart = By.className("shopping_cart_badge");
    private final By numberOfSelectedProducts = By.xpath("//button[.='Remove']");
    private final By shoppingCartLink = By.className("shopping_cart_link");
    private final By productPrice = By.xpath("//button[.='Remove']//preceding-sibling::div[@class='inventory_item_price']");
    private final WebDriver driver;
    private float totalPrice = 0;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage addAllProductsToCart() {
        List<WebElement> allProducts = driver.findElements(addToCartBtn);

        for (int i = 1; i <= allProducts.size(); i++) {
            //dynamic locator to click on all buttons
            By addToCartBtn = By.xpath("(//button[@class])[ " + i + "]");
            clickOnElement(driver, addToCartBtn);
//            clickOnElement(driver, (By) allProducts.get(2));
        }
        return this;
    }

    public String getNumberOfItemsInCart() {
        try {
            return getText(driver, numberOfItemsInCart);
        } catch (Exception e) {
            e.getStackTrace();
            return "0";
        }
    }

    public String getNumberOfSelectedProducts() {

        try {
            List<WebElement> selectedProducts = driver.findElements(numberOfSelectedProducts);
            //selectedProducts.size() return int, String.valueOf is used to make it String
            return String.valueOf(selectedProducts.size());
        } catch (Exception e) {
            e.getStackTrace();
            //return 0 in case no selected products
            return "0";
        }

    }


    public boolean compareNumberOfSelectedProductsWithCart() {
        return getNumberOfItemsInCart().equals(getNumberOfSelectedProducts());
    }

    // Adds random products to the cart by generating unique positions and interacting with buttons located via indexed XPath
    public HomePage addRandomProducts(int numbersNeeded, int totalNumbers) {
        Set<Integer> randomNumbers = generateUniqueNumber(numbersNeeded, totalNumbers);
        for (Integer random : randomNumbers) {
            By addToCartBtn = By.xpath("(//button[@class])[ " + random + "]");
            clickOnElement(driver, addToCartBtn);
        }
        return this;
    }

    public CartPage moveToCartPage() {
        clickOnElement(driver, shoppingCartLink);
        return new CartPage(driver);
    }


    public String getTotalPricesForSelectedProductsInHomePage() {
        List<WebElement> prices = driver.findElements(productPrice);

        try {
            for (int i = 1; i < prices.size(); i++) {
                //dynamic locator
                By element = By.xpath("(//button[.='Remove']//preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                //get full text and store it as String -> $25
                String fullText = getText(driver, element);
                //convert it to float and remove the $ to be able to calculate the numbers
                totalPrice += Float.parseFloat(fullText.replace("$", ""));
            }
            return String.valueOf(totalPrice);
        } catch (Exception e) {
            return "0";
        }
    }
}
