package pages.AutomationExercise;

import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import pages.AutomationExercise.CommonAutomationExercisePage;

/**
 * Product Page elements for Automation Exercise website
 */
public class ProductPage extends CommonAutomationExercisePage {

    // ========== SEARCH ==========
    public WebElementFacade TXT_SEARCH_PRODUCT() {
        return $("//input[@id='search_product']");
    }

    public WebElementFacade BTN_SEARCH() {
        return $("//button[@id='submit_search']");
    }

    // ========== PRODUCTS LIST ==========
    public WebElementFacade SECTION_PRODUCTS() {
        return $("//div[@class='features_items']");
    }

    public ListOfWebElementFacades ITEMS_PRODUCTS() {
        return findAll(By.xpath("//div[@class='product-image-wrapper']"));
    }

    // ========== FIRST PRODUCT ==========
    public WebElementFacade IMG_FIRST_PRODUCT() {
        return $("(//div[@class='product-image-wrapper'])[1]//img");
    }

    public WebElementFacade TXT_FIRST_PRODUCT_NAME() {
        WebElementFacade name = $("(//div[@class='product-image-wrapper'])[1]//p");
        if (name == null || !name.isPresent()) {
            name = $("(//div[@class='product-image-wrapper'])[1]//h4");
        }
        if (name == null || !name.isPresent()) {
            name = $("(//div[@class='product-image-wrapper'])[1]//a");
        }
        return name;
    }

    public WebElementFacade TXT_FIRST_PRODUCT_PRICE() {
        WebElementFacade price = $("(//div[@class='product-image-wrapper'])[1]//h5");
        if (price == null || !price.isPresent()) {
            price = $("(//div[@class='product-image-wrapper'])[1]//span[@class='price']");
        }
        if (price == null || !price.isPresent()) {
            price = $("(//div[@class='product-image-wrapper'])[1]//div[@class='productinfo']//p");
        }
        if (price == null || !price.isPresent()) {
            price = $("(//div[@class='product-image-wrapper'])[1]//b");
        }
        return price;
    }

    public WebElementFacade BTN_FIRST_PRODUCT_ADD_TO_CART() {
        return $("(//div[@class='product-image-wrapper'])[1]//a[contains(text(),'Add to cart')]");
    }

    public WebElementFacade BTN_FIRST_PRODUCT_VIEW_PRODUCT() {
        return $("(//div[@class='product-image-wrapper'])[1]//a[contains(text(),'View Product')]");
    }

    // ========== PRODUCT DETAIL ==========
    public WebElementFacade TXT_PRODUCT_DETAIL_NAME() {
        return $("//div[@class='product-details']//h2");
    }

    public WebElementFacade TXT_PRODUCT_DETAIL_PRICE() {
        return $("//span[@class='price']//span");
    }

    public WebElementFacade BTN_PRODUCT_DETAIL_ADD_TO_CART() {
        return $("//button[contains(text(),'Add to cart')]");
    }
}