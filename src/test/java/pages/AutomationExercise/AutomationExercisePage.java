package pages.AutomationExercise;

import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import pages.CommonPage;

public class AutomationExercisePage extends CommonPage {

    // ========== HEADER ELEMENTS ==========
    public WebElementFacade LOGO() {
        return $("//img[@src='/images/n-logo.png']");
    }

    public WebElementFacade LOGO_TEXT() {
        return $("//a[@href='/']//span[@class='brand-name']");
    }

    // ========== LOGIN / SIGNUP ==========
    public WebElementFacade LOGIN_SIGNUP_LINK() {
        return $("//a[@href='/login']");
    }

    public WebElementFacade SIGNUP_FORM() {
        return $("//div[@class='signup-form']");
    }

    public WebElementFacade SIGNUP_NAME_TXTBOX() {
        return $("//input[@placeholder='Name']");
    }

    public WebElementFacade SIGNUP_EMAIL_TXTBOX() {
        return $("//input[@data-qa='signup-email']");
    }

    public WebElementFacade SIGNUP_BTN() {
        return $("//button[@data-qa='signup-button']");
    }

    public WebElementFacade LOGIN_EMAIL_TXTBOX() {
        return $("//input[@data-qa='login-email']");
    }

    public WebElementFacade LOGIN_PASSWORD_TXTBOX() {
        return $("//input[@placeholder='Password']");
    }

    public WebElementFacade LOGIN_BTN() {
        return $("//button[@data-qa='login-button']");
    }

    public WebElementFacade LOGGED_IN_AS() {
        return $("//a[contains(text(),'Logged in as')]");
    }

    public WebElementFacade LOGOUT_LINK() {
        return $("//a[@href='/logout']");
    }

    // ========== ACCOUNT CREATION ==========
    public WebElementFacade ACCOUNT_INFO_FORM() {
        return $("//div[@class='account-info']");
    }

    public WebElementFacade TITLE_MR_RADIO() {
        return $("//input[@id='id_gender1']");
    }

    public WebElementFacade TITLE_MRS_RADIO() {
        return $("//input[@id='id_gender2']");
    }

    public WebElementFacade NAME_TXTBOX() {
        return $("//input[@id='name']");
    }

    public WebElementFacade EMAIL_TXTBOX() {
        return $("//input[@id='email']");
    }

    public WebElementFacade PASSWORD_TXTBOX() {
        return $("//input[@id='password']");
    }

    public WebElementFacade DAYS_SELECT() {
        return $("//select[@id='days']");
    }

    public WebElementFacade MONTHS_SELECT() {
        return $("//select[@id='months']");
    }

    public WebElementFacade YEARS_SELECT() {
        return $("//select[@id='years']");
    }

    public WebElementFacade NEWSLETTER_CHECKBOX() {
        return $("//input[@id='newsletter']");
    }

    public WebElementFacade SPECIAL_OFFERS_CHECKBOX() {
        return $("//input[@id='specialoffers']");
    }

    // ========== ADDRESS ==========
    public WebElementFacade FIRST_NAME_TXTBOX() {
        return $("//input[@id='first_name']");
    }

    public WebElementFacade LAST_NAME_TXTBOX() {
        return $("//input[@id='last_name']");
    }

    public WebElementFacade COMPANY_TXTBOX() {
        return $("//input[@id='company']");
    }

    public WebElementFacade ADDRESS1_TXTBOX() {
        return $("//input[@id='address1']");
    }

    public WebElementFacade ADDRESS2_TXTBOX() {
        return $("//input[@id='address2']");
    }

    public WebElementFacade COUNTRY_SELECT() {
        return $("//select[@id='country']");
    }

    public WebElementFacade STATE_TXTBOX() {
        return $("//input[@id='state']");
    }

    public WebElementFacade CITY_TXTBOX() {
        return $("//input[@id='city']");
    }

    public WebElementFacade ZIPCODE_TXTBOX() {
        return $("//input[@id='zipcode']");
    }

    public WebElementFacade MOBILE_NUMBER_TXTBOX() {
        return $("//input[@id='mobile_number']");
    }

    public WebElementFacade CREATE_ACCOUNT_BTN() {
        return $("//button[@data-qa='create-account']");
    }

    public WebElementFacade CONTINUE_BTN() {
        return $("//a[@data-qa='continue-button']");
    }

    public WebElementFacade ACCOUNT_CREATED_MSG() {
        return $("//h2[@data-qa='account-created']");
    }

    // ========== HOME PAGE ==========
    public WebElementFacade HOME_SLIDER() {
        return $("//div[@id='slider']");
    }

    public WebElementFacade CATEGORIES_ITEMS() {
        return $("//div[@class='category-products']//h2");
    }

    public WebElementFacade WOMEN_CATEGORY_LINK() {
        return $("//a[@href='#Women']");
    }

    public WebElementFacade MEN_CATEGORY_LINK() {
        return $("//a[@href='#Men']");
    }

    // ========== NAVIGATION LINKS ==========
    public WebElementFacade PRODUCTS_NAV_LINK() {
        return $("//a[@href='/products']");
    }

    // ========== PRODUCT SEARCH ==========
    public WebElementFacade SEARCH_INPUT_TXTBOX() {
        return $("//input[@id='search_product']");
    }

    public WebElementFacade SEARCH_BTN() {
        return $("//button[@id='submit_search']");
    }

    public WebElementFacade PRODUCTS_LIST() {
        return $("//div[@class='features_items']");
    }

    public ListOfWebElementFacades PRODUCT_ITEMS() {
        return findAll(By.xpath("//div[@class='product-image-wrapper']"));
    }

    // ========== FIRST PRODUCT ==========
    public WebElementFacade FIRST_PRODUCT_IMAGE() {
        return $("(//div[@class='product-image-wrapper'])[1]//img");
    }

    public WebElementFacade FIRST_PRODUCT_NAME() {
        // Try multiple xpaths for product name
        WebElementFacade name = $("(//div[@class='product-image-wrapper'])[1]//p");
        if (!name.isPresent()) {
            name = $("(//div[@class='product-image-wrapper'])[1]//h4");
        }
        if (!name.isPresent()) {
            name = $("(//div[@class='product-image-wrapper'])[1]//a");
        }
        return name;
    }

    public WebElementFacade FIRST_PRODUCT_PRICE() {
        // Try multiple xpaths for product price - website may use different elements
        WebElementFacade price = $("(//div[@class='product-image-wrapper'])[1]//h5");
        if (!price.isPresent()) {
            price = $("(//div[@class='product-image-wrapper'])[1]//span[@class='price']");
        }
        if (!price.isPresent()) {
            price = $("(//div[@class='product-image-wrapper'])[1]//div[@class='productinfo']//p");
        }
        if (!price.isPresent()) {
            // Fallback: search for any price-related element
            price = $("(//div[@class='product-image-wrapper'])[1]//b");
        }
        return price;
    }

    public WebElementFacade FIRST_PRODUCT_ADD_TO_CART_BTN() {
        return $("(//div[@class='product-image-wrapper'])[1]//a[contains(text(),'Add to cart')]");
    }

    public WebElementFacade FIRST_PRODUCT_VIEW_PRODUCT_BTN() {
        return $("(//div[@class='product-image-wrapper'])[1]//a[contains(text(),'View Product')]");
    }

    // ========== MODAL ==========
    public WebElementFacade MODAL_DIALOG() {
        return $("//div[@class='modal-content']");
    }

    public WebElementFacade MODAL_CONTINUE_BTN() {
        return $("//button[contains(text(),'Continue')]");
    }

    public WebElementFacade MODAL_VIEW_CART_LINK() {
        return $("//a[@href='/view_cart']//u");
    }

    // ========== CART ==========
    public WebElementFacade CART_PAGE() {
        return $("//li[@href='/view_cart']");
    }

    public ListOfWebElementFacades CART_ITEMS() {
        // Try multiple xpaths to handle different page structures
        ListOfWebElementFacades items = findAll(By.xpath("//tbody[@id='cart_info']//tr"));
        if (items.isEmpty()) {
            items = findAll(By.xpath("//div[@class='cart_items']//div[@class='product-info']"));
        }
        if (items.isEmpty()) {
            items = findAll(By.xpath("//table[@class='table table-condensed']//tbody//tr"));
        }
        return items;
    }

    public WebElementFacade CART_PRODUCT_NAME(String productName) {
        return $("//a[contains(text(),'" + productName + "')]");
    }

    // Cart product name and price for verification
    public WebElementFacade CART_ITEM_PRODUCT_NAME() {
        // Try multiple xpaths for cart product name
        WebElementFacade name = $$("//td[@class='cart_description']//h4//a").get(0);
        if (!name.isPresent()) {
            name = $$("//td[@class='cart_description']//p//a").get(0);
        }
        if (!name.isPresent()) {
            name = $$("//div[@class='product-info']//h4").get(0);
        }
        return name;
    }

    public WebElementFacade CART_ITEM_PRODUCT_PRICE() {
        // Try multiple xpaths for cart product price
        WebElementFacade price = $$("//td[@class='cart_price']//p").get(0);
        if (!price.isPresent()) {
            price = $$("//td[@class='cart_total']//p").get(0);
        }
        if (!price.isPresent()) {
            price = $$("//div[@class='product-info']//h5").get(0);
        }
        return price;
    }

    public WebElementFacade CART_PRODUCT_PRICE() {
        return $$("//td[@class='cart_price']//p").get(0);
    }

    public WebElementFacade CART_PRODUCT_QUANTITY() {
        return $$("//td[@class='cart_quantity']//button").get(0);
    }

    public WebElementFacade CART_TOTAL() {
        return $("//td[@class='cart_total']//p");
    }

    // ========== CHECKOUT TOTAL ==========
    public WebElementFacade CHECKOUT_TOTAL() {
        // Total amount shown on checkout page
        WebElementFacade total = $("//td[@class='cart_total']//p");
        if (!total.isPresent()) {
            total = $("//h3[@id='total']");
        }
        if (!total.isPresent()) {
            total = $("//span[@id='total']");
        }
        if (!total.isPresent()) {
            total = $("//div[@class='total']//p");
        }
        return total;
    }

    public WebElementFacade ORDER_TOTAL() {
        // Total amount on order confirmation page
        WebElementFacade total = $("//td[@class='cart_total']//p");
        if (!total.isPresent()) {
            total = $("//h3[contains(@class,'total')]");
        }
        if (!total.isPresent()) {
            total = $("//*[contains(text(),'Total:')]");
        }
        return total;
    }

    public WebElementFacade PROCEED_TO_CHECKOUT_BTN() {
        return $("//a[@class='btn btn-default check_out']");
    }

    // ========== CHECKOUT ==========
    public WebElementFacade CHECKOUT_PAGE() {
        return $("//div[@class='checkout_options']");
    }

    public WebElementFacade CHECKOUT_DELIVERY_ADDRESS() {
        // Try multiple xpaths for checkout address
        WebElementFacade address = $("//div[@class='address_delivery']");
        if (!address.isPresent()) {
            address = $("//div[contains(@class,'address')]");
        }
        if (!address.isPresent()) {
            address = $("//h2[contains(text(),'Checkout')]");
        }
        return address;
    }

    public WebElementFacade CHECKOUT_BILLING_ADDRESS() {
        return $("//div[@class='address_invoice']");
    }

    public WebElementFacade COMMENT_TXTAREA() {
        return $("//textarea[@name='message']");
    }

    public WebElementFacade PLACE_ORDER_BTN() {
        return $("//a[@href='/payment']");
    }

    // ========== PAYMENT ==========
    public WebElementFacade PAYMENT_PAGE() {
        return $("//div[@class='payment-method']");
    }

    public WebElementFacade PAYMENT_NAME_TXTBOX() {
        return $("//input[@name='name_on_card']");
    }

    public WebElementFacade PAYMENT_CARD_NUMBER_TXTBOX() {
        return $("//input[@name='card_number']");
    }

    public WebElementFacade PAYMENT_CVC_TXTBOX() {
        return $("//input[@name='cvc']");
    }

    public WebElementFacade PAYMENT_EXPIRY_MONTH_TXTBOX() {
        return $("//input[@name='expiry_month']");
    }

    public WebElementFacade PAYMENT_EXPIRY_YEAR_TXTBOX() {
        return $("//input[@name='expiry_year']");
    }

    public WebElementFacade PAYMENT_PAY_NOW_BTN() {
        return $("//button[@data-qa='pay-button']");
    }

    // ========== ORDER CONFIRMATION ==========
    public WebElementFacade ORDER_CONFIRMED_MSG() {
        return $("//div[@class='alert-success']//h2");
    }

    public WebElementFacade ORDER_NUMBER() {
        return $("//p[@class='order_attr']");
    }

    public WebElementFacade DOWNLOAD_INVOICE_BTN() {
        // Try multiple xpaths for download invoice button
        WebElementFacade btn = $("//a[@href='/download_invoice/']");
        if (!btn.isPresent()) {
            btn = $("//a[contains(text(),'Download Invoice')]");
        }
        if (!btn.isPresent()) {
            btn = $("//button[contains(text(),'Invoice')]");
        }
        return btn;
    }

    public WebElementFacade INVOICE_CONTENT() {
        // Invoice content displayed on the page after download
        return $("//div[@class='invoice']");
    }

    public WebElementFacade INVOICE_ORDER_ID() {
        return $("//p[contains(text(),'Invoice')]");
    }

    public WebElementFacade INVOICE_PRODUCT_NAME() {
        return $$("//td[@class='cart_description']//h4").get(0);
    }

    public WebElementFacade INVOICE_TOTAL_AMOUNT() {
        return $("//td[@class='cart_total']//p");
    }

    // ========== AD OVERLAY HANDLING ==========
    public WebElementFacade AD_CLOSE_BTN() {
        // Common ad close button selectors - try multiple patterns
        WebElementFacade closeBtn = $("//button[contains(@class,'close') and not(contains(@style,'display: none'))]");
        if (!closeBtn.isPresent()) {
            closeBtn = $("//a[contains(@class,'close') and not(contains(@style,'display: none'))]");
        }
        if (!closeBtn.isPresent()) {
            closeBtn = $("//span[contains(@class,'close') and not(contains(@style,'display: none'))]");
        }
        if (!closeBtn.isPresent()) {
            closeBtn = $("//div[contains(@class,'close') and not(contains(@style,'display: none'))]");
        }
        if (!closeBtn.isPresent()) {
            closeBtn = $("//button[contains(text(),'×')]");
        }
        return closeBtn;
    }

    public WebElementFacade AD_MODAL_OVERLAY() {
        // Ad modal overlay background - clicking dismisses modal
        return $("//div[contains(@class,'modal') and contains(@class,'show')]");
    }

    public WebElementFacade AD_POPUP() {
        // Any visible popup/ad overlay - more aggressive detection
        WebElementFacade popup = $("//div[contains(@class,'popup') and not(contains(@style,'display: none'))]");
        if (!popup.isPresent()) {
            popup = $("//div[contains(@class,'modal') and contains(@class,'show')]");
        }
        if (!popup.isPresent()) {
            popup = $("//div[contains(@class,'overlay') and not(contains(@style,'display: none'))]");
        }
        if (!popup.isPresent()) {
            popup = $("//div[contains(@class,'ad-') and not(contains(@style,'display: none'))]");
        }
        return popup;
    }

    // ========== ERROR MESSAGES ==========
    public WebElementFacade SIGNUP_ERROR_MSG() {
        return $("//p[contains(text(),'Email Address already exist!')]");
    }

    public WebElementFacade LOGIN_ERROR_MSG() {
        return $("//p[contains(text(),'Your email or password is incorrect!')]");
    }
}