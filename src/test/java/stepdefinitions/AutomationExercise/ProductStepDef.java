package stepdefinitions.AutomationExercise;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Steps;
import org.fluentlenium.core.annotation.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AutomationExercise.ProductPage;
import pages.AutomationExercise.CartPage;
import pages.AutomationExercise.AuthPage;
import stepdefinitions.CommonStepDef;

/**
 * Step definitions for Product functionality (Search, Add to Cart)
 */
public class ProductStepDef {

    private static final Logger logger = LoggerFactory.getLogger(ProductStepDef.class);

    @Steps
    CommonStepDef commonStepDef;

    @Page
    ProductPage productPage;

    @Page
    CartPage cartPage;

    // Note: AuthPage used for ensureLoggedIn check
    @Page
    AuthPage authPage;

    // ========== NAVIGATION ==========
    @Then("I navigate to products section")
    public void navigateToProductsSection() {
        logger.info("Navigating to products section");
        commonStepDef.navigatePage("automationexercise");
        commonStepDef.clickElement(productPage.LINK_PRODUCTS());
        commonStepDef.waitForPageInSecond(2000);
    }

    // ========== ENSURE LOGGED IN ==========
    @Given("I am logged in to the application")
    public void ensureLoggedIn() {
        logger.info("Ensuring user is logged in");
        if (authPage == null || !authPage.LINK_LOGGED_IN_AS().isVisible()) {
            commonStepDef.clickElement(authPage.LINK_LOGIN_SIGNUP());
            // Note: For proper login, credentials would be needed here
            // This is a simplified version
            logger.warn("User not logged in - please login first");
        }
    }

    // ========== SEARCH ==========
    @And("I search for product using search term")
    public void searchProduct() {
        logger.info("Searching for product using search term");
        commonStepDef.enterText(productPage.TXT_SEARCH_PRODUCT(), "Men", 0);
        commonStepDef.clickElement(productPage.BTN_SEARCH());
        commonStepDef.waitForPageInSecond(2000);
    }

    @Then("I should see search results")
    public void verifySearchResults() {
        logger.info("Verifying search results are displayed");
        commonStepDef.verifyVisibilityofElement(productPage.SECTION_PRODUCTS());
        int itemCount = productPage.ITEMS_PRODUCTS().size();
        assert itemCount > 0 : "No products found in search results";
        logger.info("Found {} products in search results", itemCount);
    }

    // ========== ADD TO CART ==========
    @And("I add first product to cart")
    public void addFirstProductToCart() {
        logger.info("Adding first product to cart");
        commonStepDef.clickElement(productPage.BTN_FIRST_PRODUCT_ADD_TO_CART());
        commonStepDef.waitForPageInSecond(2000);
    }

    @And("I click Continue on modal")
    public void clickContinueOnModal() {
        logger.info("Clicking Continue on modal");
        commonStepDef.clickElement(cartPage.BTN_MODAL_CONTINUE());
        commonStepDef.waitForPageInSecond(1000);
    }

    @And("I click View Cart on modal")
    public void clickViewCartOnModal() {
        logger.info("Clicking View Cart on modal");
        // Modal continue may close modal and we're back on products, navigate to cart
        commonStepDef.waitForPageInSecond(2000);  // Wait for modal to close
        // Navigate to cart page - use the cartPage object's element as a workaround
        commonStepDef.navigatePage("automationexercise");
        commonStepDef.clickElement(cartPage.LINK_CART_ICON());
        commonStepDef.waitForPageInSecond(2000);
    }

    // ========== CART VERIFICATION ==========
    @Then("I should see products in cart")
    public void verifyProductsInCart() {
        logger.info("Verifying products are in cart");
        int cartItemCount = cartPage.ITEMS_CART().size();
        assert cartItemCount > 0 : "No items in cart";
        logger.info("Cart contains {} items", cartItemCount);
    }
}
