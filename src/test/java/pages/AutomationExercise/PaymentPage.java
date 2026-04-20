package pages.AutomationExercise;

import net.serenitybdd.core.pages.WebElementFacade;
import pages.AutomationExercise.CommonAutomationExercisePage;

/**
 * Payment Page elements for Automation Exercise website
 */
public class PaymentPage extends CommonAutomationExercisePage {

    // ========== PAYMENT PAGE ==========
    public WebElementFacade SECTION_PAYMENT() {
        return $("//div[@class='payment-method']");
    }

    // ========== PAYMENT DETAILS ==========
    public WebElementFacade TXT_PAYMENT_NAME() {
        return $("//input[@name='name_on_card']");
    }

    public WebElementFacade TXT_CARD_NUMBER() {
        return $("//input[@name='card_number']");
    }

    public WebElementFacade TXT_CVC() {
        return $("//input[@name='cvc']");
    }

    public WebElementFacade TXT_EXPIRY_MONTH() {
        return $("//input[@name='expiry_month']");
    }

    public WebElementFacade TXT_EXPIRY_YEAR() {
        return $("//input[@name='expiry_year']");
    }

    // ========== ACTIONS ==========
    public WebElementFacade BTN_PAY_NOW() {
        return $("//button[@data-qa='pay-button']");
    }

    // ========== ORDER CONFIRMATION ==========
    public WebElementFacade TXT_ORDER_CONFIRMED() {
        WebElementFacade msg = $("//div[@class='alert-success']//h2");
        if (msg == null || !msg.isPresent()) {
            msg = $("//h2[contains(.,'Order Placed')]");
        }
        if (msg == null || !msg.isPresent()) {
            msg = $("//div[contains(@class,'success')]");
        }
        if (msg == null || !msg.isPresent()) {
            msg = $("//h2[contains(.,'Thank')]");
        }
        return msg;
    }

    public WebElementFacade TXT_ORDER_NUMBER() {
        return $("//p[@class='order_attr']");
    }

    // ========== INVOICE ==========
    public WebElementFacade BTN_DOWNLOAD_INVOICE() {
        WebElementFacade btn = $("//a[@href='/download_invoice/']");
        if (btn == null || !btn.isPresent()) {
            btn = $("//a[contains(text(),'Download Invoice')]");
        }
        if (btn == null || !btn.isPresent()) {
            btn = $("//button[contains(text(),'Invoice')]");
        }
        if (btn == null || !btn.isPresent()) {
            btn = $("//a[contains(@href,'download_invoice')]");
        }
        return btn;
    }

    public WebElementFacade DIV_INVOICE_CONTENT() {
        return $("//div[@class='invoice']");
    }

    // Invoice order ID - try multiple selectors
    public WebElementFacade TXT_INVOICE_ORDER_ID() {
        // Try to find order ID in invoice section
        WebElementFacade orderId = $("//p[contains(.,'Invoice')]");
        if (orderId != null && orderId.isPresent()) {
            return orderId;
        }
        
        // Try to find order number element
        orderId = $("//p[@class='order_attr']");
        if (orderId != null && orderId.isPresent()) {
            return orderId;
        }
        
        // Try to find b element with order info
        orderId = $("//b[contains(.,'Order')]");
        if (orderId != null && orderId.isPresent()) {
            return orderId;
        }
        
        // Try any element containing order number pattern
        orderId = $("//*[contains(.,'#')]");
        return orderId;
    }

    public WebElementFacade TXT_INVOICE_PRODUCT_NAME() {
        WebElementFacade product = $$("//td[@class='cart_description']//h4").first();
        if (product != null && product.isPresent()) {
            return product;
        }
        return $$("//td[contains(@class,'description')]//h4").first();
    }

    // Invoice total - try multiple selectors
    public WebElementFacade TXT_INVOICE_TOTAL() {
        // Try cart total first
        WebElementFacade total = $("//td[@class='cart_total']//p");
        if (total != null && total.isPresent()) {
            return total;
        }
        
        // Try total row
        total = $("//tr[contains(@class,'total')]//td[last()]");
        if (total != null && total.isPresent()) {
            return total;
        }
        
        // Try any total element
        total = $("//*[contains(.,'Rs.')]");
        if (total != null && total.isPresent()) {
            return total;
        }
        
        // Try order total
        total = $("//div[@class='cart-total']//p");
        return total;
    }

    // Get page content for invoice verification (with null safety)
    public String getInvoicePageContent() {
        WebElementFacade body = $("//body");
        if (body == null || !body.isPresent()) {
            return "";
        }
        return body.getText();
    }

    // Get invoice total amount for verification
    public String getInvoiceTotal() {
        WebElementFacade total = TXT_INVOICE_TOTAL();
        if (total == null || !total.isPresent()) {
            return "";
        }
        String text = total.getText();
        // Extract numeric value if possible
        if (text != null && text.matches(".*\\d+.*")) {
            return text.replaceAll("[^0-9.]", "");
        }
        return text;
    }

    // Check if invoice order ID exists
    public boolean hasInvoiceOrderId() {
        WebElementFacade orderId = TXT_INVOICE_ORDER_ID();
        if (orderId == null || !orderId.isPresent()) {
            return false;
        }
        String text = orderId.getText();
        // Check if it contains some meaningful content
        return text != null && !text.isEmpty();
    }
}
