package pages.AutomationExercise;

import net.serenitybdd.core.pages.WebElementFacade;
import pages.AutomationExercise.CommonAutomationExercisePage;

/**
 * Authentication Page elements for Automation Exercise website
 * Handles Login and Signup forms
 */
public class AuthPage extends CommonAutomationExercisePage {

    // ========== SIGNUP FORM ==========
    public WebElementFacade TXT_SIGNUP_NAME() {
        return $("//input[@placeholder='Name']");
    }

    public WebElementFacade TXT_SIGNUP_EMAIL() {
        return $("//input[@data-qa='signup-email']");
    }

    public WebElementFacade BTN_SIGNUP() {
        return $("//button[@data-qa='signup-button']");
    }

    // ========== LOGIN FORM ==========
    public WebElementFacade TXT_LOGIN_EMAIL() {
        return $("//input[@data-qa='login-email']");
    }

    public WebElementFacade TXT_LOGIN_PASSWORD() {
        return $("//input[@placeholder='Password']");
    }

    public WebElementFacade BTN_LOGIN() {
        return $("//button[@data-qa='login-button']");
    }

    // ========== INHERITED FROM COMMON ==========
    // LINK_LOGIN_SIGNUP(), LINK_LOGOUT(), LINK_LOGGED_IN_AS(), TXT_LOGIN_ERROR(), TXT_SIGNUP_ERROR()
}
