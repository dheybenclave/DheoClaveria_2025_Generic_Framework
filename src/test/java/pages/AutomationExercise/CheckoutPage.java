package pages.AutomationExercise;

import net.serenitybdd.core.pages.WebElementFacade;
import pages.AutomationExercise.CommonAutomationExercisePage;

/**
 * Checkout Page elements for Automation Exercise website
 */
public class CheckoutPage extends CommonAutomationExercisePage {

    // ========== CHECKOUT PAGE ==========
    public WebElementFacade SECTION_CHECKOUT() {
        return $("//div[@class='checkout-options']");
    }

    // ========== DELIVERY ADDRESS ==========
    public WebElementFacade DIV_ADDRESS_DELIVERY() {
        WebElementFacade address = $("//div[@class='address_delivery']");
        if (address == null || !address.isPresent()) {
            address = $("//div[contains(@class,'address')]");
        }
        if (address == null || !address.isPresent()) {
            address = $("//div[@class='checkout-payment']");
        }
        if (address == null || !address.isPresent()) {
            address = $("//h2[contains(.,'Checkout')]");
        }
        if (address == null || !address.isPresent()) {
            address = $("//a[@href='/payment']");
        }
        return address;
    }

    public WebElementFacade DIV_ADDRESS_INVOICE() {
        return $("//div[@class='address_invoice']");
    }

    // ========== ORDER COMMENT ==========
    public WebElementFacade TEXTAREA_COMMENT() {
        return $("//textarea[@name='message']");
    }

    // ========== TOTAL AMOUNTS ==========
    public WebElementFacade TXT_CHECKOUT_TOTAL() {
        WebElementFacade total = $("//td[@class='cart_total']//p");
        if (total == null || !total.isPresent()) {
            total = $("//h3[@id='total']");
        }
        if (total == null || !total.isPresent()) {
            total = $("//span[@id='total']");
        }
        if (total == null || !total.isPresent()) {
            total = $("//div[@class='total']//p");
        }
        return total;
    }

    public WebElementFacade TXT_ORDER_TOTAL() {
        WebElementFacade total = $("//td[@class='cart_total']//p");
        if (total == null || !total.isPresent()) {
            total = $("//h3[contains(@class,'total')]");
        }
        if (total == null || !total.isPresent()) {
            total = $("//*[contains(text(),'Total:')]");
        }
        return total;
    }

    // ========== ACTIONS ==========
    public WebElementFacade BTN_PLACE_ORDER() {
        return $("//a[@href='/payment']");
    }
}