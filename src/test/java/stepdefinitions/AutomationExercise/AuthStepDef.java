package stepdefinitions.AutomationExercise;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import org.fluentlenium.core.annotation.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AutomationExercise.AuthPage;
import pages.AutomationExercise.AccountPage;
import stepdefinitions.CommonStepDef;
import utils.Utilities;

/**
 * Step definitions for Authentication (Login/Signup/Account Creation) 
 */
public class AuthStepDef {

    private static final Logger logger = LoggerFactory.getLogger(AuthStepDef.class);

    @Steps
    CommonStepDef commonStepDef;

    @Page
    AuthPage authPage;

    @Page
    AccountPage accountPage;

    private Utilities utilities = new Utilities();
    private String userEmail;
    private String userPassword;

    // ========== NAVIGATION ==========
    @Given("Tester is navigating to Automation Exercise homepage")
    public void navigateToHomepage() {
        logger.info("Navigating to Automation Exercise homepage");
        commonStepDef.navigatePage("automationexercise");
        commonStepDef.waitForPageInSecond(2000);
    }

    // ========== LOGIN / SIGNUP LINK ==========
    @When("I click on LoginSignup link")
    public void clickLoginSignupLink() {
        logger.info("Clicking on Login/Signup link");
        commonStepDef.clickElement(authPage.LINK_LOGIN_SIGNUP());
    }

    // ========== SIGNUP ==========
    @And("I enter signup name and email with random data")
    public void enterSignupDetails() {
        logger.info("Entering signup name and email with random data");
        String random = utilities.generateRandomString();
        userEmail = "testuser" + random.substring(0, 8) + "@example.com";
        userPassword = "Test@123456";

        commonStepDef.enterText(authPage.TXT_SIGNUP_NAME(), "Test User", 0);
        commonStepDef.enterText(authPage.TXT_SIGNUP_EMAIL(), userEmail, 0);
        logger.info("Signup email created: {}", userEmail);
    }

    @And("I click Signup button")
    public void clickSignupButton() {
        logger.info("Clicking Signup button");
        commonStepDef.clickElement(authPage.BTN_SIGNUP());
        commonStepDef.waitForPageInSecond(2000);
    }

    @And("I fill account information")
    public void fillAccountInformation() {
        logger.info("Filling account information");
        commonStepDef.clickElement(accountPage.RADIO_TITLE_MR());
        commonStepDef.enterText(accountPage.TXT_PASSWORD(), userPassword, 0);

        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(
            accountPage.SELECT_DAYS().getWrappedElement());
        select.selectByValue("15");

        org.openqa.selenium.support.ui.Select selectMonth = new org.openqa.selenium.support.ui.Select(
            accountPage.SELECT_MONTHS().getWrappedElement());
        selectMonth.selectByValue("6");

        org.openqa.selenium.support.ui.Select selectYear = new org.openqa.selenium.support.ui.Select(
            accountPage.SELECT_YEARS().getWrappedElement());
        selectYear.selectByValue("1990");
    }

    @And("I fill address information")
    public void fillAddressInformation() {
        logger.info("Filling address information");
        commonStepDef.enterText(accountPage.TXT_FIRST_NAME(), "Test", 0);
        commonStepDef.enterText(accountPage.TXT_LAST_NAME(), "User", 0);
        commonStepDef.enterText(accountPage.TXT_COMPANY(), "Test Company", 0);
        commonStepDef.enterText(accountPage.TXT_ADDRESS1(), "123 Test Street", 0);
        commonStepDef.enterText(accountPage.TXT_ADDRESS2(), "Suite 100", 0);

        org.openqa.selenium.support.ui.Select selectCountry = new org.openqa.selenium.support.ui.Select(
            accountPage.SELECT_COUNTRY().getWrappedElement());
        selectCountry.selectByValue("United States");

        commonStepDef.enterText(accountPage.TXT_STATE(), "New York", 0);
        commonStepDef.enterText(accountPage.TXT_CITY(), "New York", 0);
        commonStepDef.enterText(accountPage.TXT_ZIPCODE(), "10001", 0);
        commonStepDef.enterText(accountPage.TXT_MOBILE_NUMBER(), "1234567890", 0);
    }

    @And("I click Create Account button")
    public void clickCreateAccountButton() {
        logger.info("Clicking Create Account button");
        commonStepDef.clickElement(accountPage.BTN_CREATE_ACCOUNT());
        commonStepDef.waitForPageInSecond(3000);
    }

    @Then("I should see Account Created message")
    public void verifyAccountCreated() {
        logger.info("Verifying Account Created message is displayed");
        commonStepDef.verifyVisibilityofElement(accountPage.TXT_ACCOUNT_CREATED());
    }

    @And("I click Continue button")
    public void clickContinueButton() {
        logger.info("Clicking Continue button");
        commonStepDef.clickElement(accountPage.BTN_CONTINUE());
        commonStepDef.waitForPageInSecond(2000);
    }

    @Then("I should be logged in as Test User")
    public void verifyLoggedIn() {
        logger.info("Verifying user is logged in as Test User");
        commonStepDef.waitForPageInSecond(3000);  // Wait for main page to fully load after login
        commonStepDef.verifyVisibilityofElement(authPage.LINK_LOGGED_IN_AS());
    }

    // ========== LOGIN ==========
    @And("I enter login credentials")
    public void enterLoginCredentials() {
        logger.info("Entering login credentials");
        commonStepDef.enterText(authPage.TXT_LOGIN_EMAIL(), userEmail, 0);
        commonStepDef.enterText(authPage.TXT_LOGIN_PASSWORD(), userPassword, 0);
    }

    @And("I click Login button")
    public void clickLoginButton() {
        logger.info("Clicking Login button");
        commonStepDef.clickElement(authPage.BTN_LOGIN());
        commonStepDef.waitForPageInSecond(2000);
    }

    @When("I logout")
    public void logout() {
        logger.info("Logging out");
        commonStepDef.clickElement(authPage.LINK_LOGOUT());
        commonStepDef.waitForPageInSecond(1000);
    }

    // ========== ERROR HANDLING ==========
    @Then("I should see signup error message")
    public void verifySignupError() {
        logger.info("Verifying signup error message is displayed");
        commonStepDef.verifyVisibilityofElement(authPage.TXT_SIGNUP_ERROR());
    }

    @Then("I should see login error message")
    public void verifyLoginError() {
        logger.info("Verifying login error message is displayed");
        commonStepDef.verifyVisibilityofElement(authPage.TXT_LOGIN_ERROR());
    }

    // ========== GETTERS FOR OTHER STEP DEFS ==========
    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setCredentials(String email, String password) {
        this.userEmail = email;
        this.userPassword = password;
    }
}
