package pages.AutomationExercise;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;

/**
 * Common page elements for Automation Exercise website
 * Contains shared header, navigation, footer, and error message elements
 */
public class CommonAutomationExercisePage extends UIInteractions {

    // ========== HEADER ==========
    public WebElementFacade IMG_LOGO() {
        return $("//img[@src='/images/n-logo.png']");
    }

    public WebElementFacade TXT_LOGO_TEXT() {
        return $("//a[@href='/']//span[@class='brand-name']");
    }

    // ========== NAVIGATION LINKS ==========
    public WebElementFacade LINK_HOME() {
        return $("//a[@href='/']");
    }

    public WebElementFacade LINK_PRODUCTS() {
        return $("//a[@href='/products']");
    }

    public WebElementFacade LINK_CART() {
        return $("//a[@href='/view_cart']");
    }

    public WebElementFacade LINK_LOGIN_SIGNUP() {
        return $("//a[@href='/login']");
    }

    public WebElementFacade LINK_LOGOUT() {
        return $("//a[@href='/logout']");
    }

    // ========== LOGGED IN STATE ==========
    public WebElementFacade LINK_LOGGED_IN_AS() {
        return $("//a[contains(.,'Logged in as')]");
    }

    // ========== CATEGORIES ==========
    public WebElementFacade SECTION_CATEGORIES() {
        return $("//div[@class='category-products']");
    }

    public WebElementFacade ITEMS_CATEGORIES() {
        return $("//div[@class='category-products']//h2");
    }

    public WebElementFacade LINK_WOMEN_CATEGORY() {
        return $("//a[@href='#Women']");
    }

    public WebElementFacade LINK_MEN_CATEGORY() {
        return $("//a[@href='#Men']");
    }

    // ========== AD OVERLAY HANDLING ==========
    public WebElementFacade BTN_AD_CLOSE() {
        WebElementFacade closeBtn = $("//button[contains(@class,'close') and not(contains(@style,'display: none'))]");
        if (closeBtn == null || !closeBtn.isPresent()) {
            closeBtn = $("//a[contains(@class,'close') and not(contains(@style,'display: none'))]");
        }
        if (closeBtn == null || !closeBtn.isPresent()) {
            closeBtn = $("//span[contains(@class,'close') and not(contains(@style,'display: none'))]");
        }
        if (closeBtn == null || !closeBtn.isPresent()) {
            closeBtn = $("//div[contains(@class,'close') and not(contains(@style,'display: none'))]");
        }
        if (closeBtn == null || !closeBtn.isPresent()) {
            closeBtn = $("//button[contains(text(),'×')]");
        }
        return closeBtn;
    }

    public WebElementFacade MODAL_AD_OVERLAY() {
        return $("//div[contains(@class,'modal') and contains(@class,'show')]");
    }

    public WebElementFacade DIV_AD_POPUP() {
        WebElementFacade popup = $("//div[contains(@class,'popup') and not(contains(@style,'display: none'))]");
        if (popup == null || !popup.isPresent()) {
            popup = $("//div[contains(@class,'modal') and contains(@class,'show')]");
        }
        if (popup == null || !popup.isPresent()) {
            popup = $("//div[contains(@class,'overlay') and not(contains(@style,'display: none'))]");
        }
        if (popup == null || !popup.isPresent()) {
            popup = $("//div[contains(@class,'ad-') and not(contains(@style,'display: none'))]");
        }
        return popup;
    }

    // ========== ERROR MESSAGES ==========
    public WebElementFacade TXT_SIGNUP_ERROR() {
        return $("//p[contains(text(),'Email Address already exist!')]");
    }

    public WebElementFacade TXT_LOGIN_ERROR() {
        return $("//p[contains(text(),'Your email or password is incorrect!')]");
    }
}
