package pages.AutomationExercise;

import org.openqa.selenium.By;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.WebElementFacade;
import pages.AutomationExercise.CommonAutomationExercisePage;

/**
 * Cart Page elements for Automation Exercise website
 */
public class CartPage extends CommonAutomationExercisePage {

    // ========== CART PAGE ==========
    public WebElementFacade LINK_CART_PAGE() {
        return $("//li[@href='/view_cart']");
    }

    // ========== CART ITEMS ==========
    public ListOfWebElementFacades ITEMS_CART() {
        ListOfWebElementFacades items = findAll(By.xpath("//tbody[@id='cart_info']//tr"));
        if (items.isEmpty()) {
            items = findAll(By.xpath("//div[@class='cart_items']//div[@class='product-info']"));
        }
        if (items.isEmpty()) {
            items = findAll(By.xpath("//table[@class='table table-condensed']//tbody//tr"));
        }
        return items;
    }

    public WebElementFacade LINK_CART_PRODUCT(String productName) {
        return $("//a[contains(text(),'" + productName + "')]");
    }

    // ========== FIRST CART ITEM ==========
    public WebElementFacade TXT_CART_ITEM_NAME() {
        WebElementFacade name = $$("//td[@class='cart_description']//h4//a").get(0);
        if (name == null || !name.isPresent()) {
            name = $$("//td[@class='cart_description']//p//a").get(0);
        }
        if (name == null || !name.isPresent()) {
            name = $$("//div[@class='product-info']//h4").get(0);
        }
        return name;
    }

    public WebElementFacade TXT_CART_ITEM_PRICE() {
        WebElementFacade price = $$("//td[@class='cart_price']//p").get(0);
        if (price == null || !price.isPresent()) {
            price = $$("//td[@class='cart_total']//p").get(0);
        }
        if (price == null || !price.isPresent()) {
            price = $$("//div[@class='product-info']//h5").get(0);
        }
        return price;
    }

    public WebElementFacade TXT_CART_PRODUCT_PRICE() {
        return $$("//td[@class='cart_price']//p").get(0);
    }

    public WebElementFacade TXT_CART_QUANTITY() {
        return $$("//td[@class='cart_quantity']//button").get(0);
    }

    public WebElementFacade TXT_CART_TOTAL() {
        return $("//td[@class='cart_total']//p");
    }

    // ========== ACTIONS ==========
    public WebElementFacade BTN_PROCEED_TO_CHECKOUT() {
        return $("//a[@class='btn btn-default check_out']");
    }

    // ========== ADD TO CART MODAL ==========
    public WebElementFacade DIV_MODAL_DIALOG() {
        return $("//div[@class='modal-content']");
    }

    public WebElementFacade BTN_MODAL_CONTINUE() {
        return $("//button[contains(text(),'Continue')]");
    }

    public WebElementFacade LINK_MODAL_VIEW_CART() {
        return $("//a[@href='/view_cart']//u");
    }

    public WebElementFacade LINK_CART_ICON() {
        return $("//a[@href='/view_cart']");
    }
}