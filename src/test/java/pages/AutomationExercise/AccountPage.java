package pages.AutomationExercise;

import net.serenitybdd.core.pages.WebElementFacade;
import pages.AutomationExercise.CommonAutomationExercisePage;

/**
 * Account Creation Page elements for Automation Exercise website
 */
public class AccountPage extends CommonAutomationExercisePage {

    // ========== TITLE (GENDER) ==========
    public WebElementFacade RADIO_TITLE_MR() {
        return $("//input[@id='id_gender1']");
    }

    public WebElementFacade RADIO_TITLE_MRS() {
        return $("//input[@id='id_gender2']");
    }

    // ========== PERSONAL INFORMATION ==========
    public WebElementFacade TXT_NAME() {
        return $("//input[@id='name']");
    }

    public WebElementFacade TXT_EMAIL() {
        return $("//input[@id='email']");
    }

    public WebElementFacade TXT_PASSWORD() {
        return $("//input[@id='password']");
    }

    // ========== DATE OF BIRTH ==========
    public WebElementFacade SELECT_DAYS() {
        return $("//select[@id='days']");
    }

    public WebElementFacade SELECT_MONTHS() {
        return $("//select[@id='months']");
    }

    public WebElementFacade SELECT_YEARS() {
        return $("//select[@id='years']");
    }

    // ========== NEWSLETTER / SPECIAL OFFERS ==========
    public WebElementFacade CHECKBOX_NEWSLETTER() {
        return $("//input[@id='newsletter']");
    }

    public WebElementFacade CHECKBOX_SPECIAL_OFFERS() {
        return $("//input[@id='specialoffers']");
    }

    // ========== ADDRESS INFORMATION ==========
    public WebElementFacade TXT_FIRST_NAME() {
        return $("//input[@id='first_name']");
    }

    public WebElementFacade TXT_LAST_NAME() {
        return $("//input[@id='last_name']");
    }

    public WebElementFacade TXT_COMPANY() {
        return $("//input[@id='company']");
    }

    public WebElementFacade TXT_ADDRESS1() {
        return $("//input[@id='address1']");
    }

    public WebElementFacade TXT_ADDRESS2() {
        return $("//input[@id='address2']");
    }

    public WebElementFacade SELECT_COUNTRY() {
        return $("//select[@id='country']");
    }

    public WebElementFacade TXT_STATE() {
        return $("//input[@id='state']");
    }

    public WebElementFacade TXT_CITY() {
        return $("//input[@id='city']");
    }

    public WebElementFacade TXT_ZIPCODE() {
        return $("//input[@id='zipcode']");
    }

    public WebElementFacade TXT_MOBILE_NUMBER() {
        return $("//input[@id='mobile_number']");
    }

    // ========== ACTION BUTTONS ==========
    public WebElementFacade BTN_CREATE_ACCOUNT() {
        return $("//button[@data-qa='create-account']");
    }

    public WebElementFacade BTN_CONTINUE() {
        return $("//a[@data-qa='continue-button']");
    }

    // ========== SUCCESS MESSAGE ==========
    public WebElementFacade TXT_ACCOUNT_CREATED() {
        return $("//h2[@data-qa='account-created']");
    }
}
