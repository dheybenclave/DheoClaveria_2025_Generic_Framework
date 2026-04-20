package pages.AutomationExercise;

import net.serenitybdd.core.pages.WebElementFacade;
import pages.AutomationExercise.CommonAutomationExercisePage;

/**
 * Home Page elements for Automation Exercise website
 */
public class HomePage extends CommonAutomationExercisePage {

    // ========== HOME PAGE SLIDER ==========
    public WebElementFacade HOME_SLIDER() {
        return $("//div[@id='slider']");
    }

    // ========== CATEGORIES (shared from common) ==========
    // Inherited: CATEGORIES_ITEMS(), WOMEN_CATEGORY_LINK(), MEN_CATEGORY_LINK()

    // ========== FEATURED PRODUCTS SECTION ==========
    public WebElementFacade FEATURED_ITEMS_SECTION() {
        return $("//div[@class='features_items']");
    }

    public WebElementFacade BRAND_PRODUCTS_SECTION(String brandName) {
        return $("//div[@class='brands_products']//h2[contains(text(),'" + brandName + "')]");
    }
}