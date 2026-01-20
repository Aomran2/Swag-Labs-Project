package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.clickOnElement;
import static Utilities.Utility.getText;

public class OverviewPage {

    private final WebDriver driver;
    private final By subtotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By total = By.className("summary_total_label");
    private final By finish = By.xpath("//button[contains(@class,'btn_action btn_medium cart_button')]");

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public float getSubTotal() {
        String subTotal = getText(driver, subtotal);
        return Float.parseFloat(subTotal.replace("Item total: $", ""));
    }

    public float getTax() {
        String taxation = getText(driver, tax);
        return Float.parseFloat(taxation.replace("Tax: $", ""));
    }

    public float getTotal() {
        return Float.parseFloat(getText(driver, total).replace("Total: $", ""));
    }

    public String calculateTotalPrice() {
        return String.valueOf(getSubTotal() + getTax());
    }

    public boolean comparingPrices() {
        //comparing string with string by casting getTotal to string using String.valueOf
        return calculateTotalPrice().equals(String.valueOf(getTotal()));
    }

    public CompletePage finishOrder() {
        clickOnElement(driver, finish);
        return new CompletePage(driver);
    }

}
