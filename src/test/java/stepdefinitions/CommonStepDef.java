package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.pages.PageComponent;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.actions.UnknownPageException;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import org.openqa.selenium.JavascriptExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CommonPage;
import pages.DemoASPNETAwesome.CommonDemoASPAwesomePage;
import pages.DemoASPNETAwesome.DemoASPAwesomePage;
import pages.ProDinnerPage.CommonProDinnerPage;
import utils.BaseClass;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CommonStepDef extends BaseClass {
    public static Logger logger = LoggerFactory.getLogger(CommonStepDef.class);
    private int CLIENT_CODE_STACK_INDEX;
    public CommonPage commonPage;
    public CommonProDinnerPage commonProDinnerPage;
    public CommonDemoASPAwesomePage commonDEMOAWESOMEPage;
    public DemoASPAwesomePage demoASPAwesemoPage;
    public String mainwindow;
    public Set<String> s1;
    public Iterator<String> i1;

    @Given("Tester is navigating in {}")
    public void navigatePage(String page) {
        this.thePage(page);
        Ensure.thatTheCurrentPage().currentUrl();
        this.injectAdBlocker();
        dismissCookieBanner();

    }

    @Given("I go to {} > {} > {} modules")
    public void iNavigateToSubGroup(String group, String subPage, String subPageItem) {
        waitABit(2000);
        dismissCookieBanner();

        if (!group.isEmpty()) {
            testStep(String.format("the element %s in the page", group));
            WebElementFacade groupElement = commonDEMOAWESOMEPage.NAVIGATE_MODULE_PARENT(group);
            groupElement.waitUntilVisible().withTimeoutOf(15, java.util.concurrent.TimeUnit.SECONDS);
            clickElement(groupElement);
            CollapaseMenu(groupElement);
        }
        if (!subPage.isEmpty()) {
            testStep(String.format("the element %s in the page", subPage));
            WebElementFacade subElement = commonDEMOAWESOMEPage.NAVIGATE_MODULE_SUB(subPage);
            subElement.waitUntilVisible().withTimeoutOf(15, java.util.concurrent.TimeUnit.SECONDS);
            clickElement(subElement);
            CollapaseMenu(subElement);
        }
        if (!subPageItem.isEmpty()) {
            testStep(String.format("the element %s in the page", subPageItem));
            WebElementFacade itemElement = commonDEMOAWESOMEPage.NAVIGATE_MODULE_SUB(subPageItem);
            itemElement.waitUntilVisible().withTimeoutOf(15, java.util.concurrent.TimeUnit.SECONDS);
            clickElement(itemElement);
        }

    }

    @When("I verify the element navigation search box in the page")
    public void iTheElementNavigation_search_txtboxInThePage() {
        waitABit(2000);
        verifyVisibilityofElement(demoASPAwesemoPage.NAVIGATION_SEARCH_TXTBOX());
    }

    @And("I verify the following text in the page :")
    public void verifyTextListedinPage(DataTable dataTable) {
        List<String> expectedElementTextList = dataTable.asList();
        testStep(String.format("I verify the following text in the page : %s", expectedElementTextList));
        for (String expectedElementText : expectedElementTextList) {
            verifyVisibilityofElement(commonPage.PAGE_CONTROL_LABEL(expectedElementText));
        }
    }

    public void thePage(String pageName) {
        String pageUrl = EnvironmentSpecificConfiguration.from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getOptionalProperty("pages." + pageName.toLowerCase())
                .orElse(SystemEnvironmentVariables.currentEnvironmentVariables()
                        .getProperty("pages." + pageName.toLowerCase()));
        if (pageUrl == null) {
            throw new UnknownPageException("No page defined with the name '" + pageUrl + "'");
        }
        testStep(String.format("Navigate Page to %s", pageName));
        this.getDriver().get(pageUrl);
        this.injectAdBlocker();
    }

    public void waitForPageInSecond(int timeInMilliseconds) {
        testStep(String.format("Wait For Page In Second(s) %s", timeInMilliseconds));
        waitABit(timeInMilliseconds);
    }

    public void generatedSwitchHandler() {
        testStep(String.format("Generated Switch Handler"));
        mainwindow = this.getDriver().getWindowHandle();
        s1 = this.getDriver().getWindowHandles();
        i1 = s1.iterator();
    }

    public void switchToMainWindow(String windowHandle) {
        testStep(String.format("Switch to Main Window"));
        this.getDriver().switchTo().window(windowHandle);
    }

    public void switchToParentFrame() {
        testStep(String.format("Switch to Parent Frame"));
        this.getDriver().switchTo().parentFrame();
        this.getDriver().switchTo().frame(0);
        this.getDriver().switchTo().defaultContent();
    }

    public void verifyVisibilityofElement(WebElementFacade element) {
        testStep(String.format("Verify the Visibility of the element %s in the page", element));
        testStep(String.format("the element %s is %s", element, element.isDisplayed()));
        shouldBeVisible(element);
    }

    public void clickBackPage() {
        testStep(String.format("Click Back/Previous Button Page"));
        this.getDriver().navigate().back();
    }

    public void clickRefreshPage() {
        testStep(String.format("Click Back/Previous Button Page"));
        this.getDriver().navigate().refresh();
    }

    public void verifyPresenceofElement(WebElementFacade element) {
        testStep(String.format(String.format("Verify the Presence of the element %s in the page", element)));
        testStep(String.format("the element %s is %s", element, element.isDisplayed()));
        element.isPresent();
    }

    public void clickElement(WebElementFacade element) {
        testStep(String.format("Click for Element '%s'", element));
        verifyVisibilityofElement(element);
        waitABit(3000);

        try {
            JavascriptExecutor js = (JavascriptExecutor) this.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", element.getWrappedElement());
            waitABit(500);

            element.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            testStep("Click intercepted, using JavaScript executor");
            JavascriptExecutor js = (JavascriptExecutor) this.getDriver();
            js.executeScript("arguments[0].click();", element.getWrappedElement());
        }
    }

    public void clickElementIfExist(WebElementFacade element) {
        testStep(String.format("Click for Element if Exist '%s'", element));

        if (element.isVisible() && element.isPresent()) {
            verifyVisibilityofElement(element);
            waitABit(2000);
            element.click();
        }

    }

    public void clickTextIfExist(String elementText) {
        testStep(String.format("Click for Element if Exist '%s'", elementText));
        clickElementIfExist(commonPage.LBL_FIELD(elementText));
    }

    public void clickTextWithParentSelectorIfExist(String parentSelector, String elementText) {
        testStep(String.format("Click for Element if Exist '%s'", elementText));
        clickElementIfExist(commonPage.LBL_FIELD_WITH_PARENT_SELECTOR(parentSelector, elementText));
    }

    public void enterText(WebElementFacade element, String value, int waitForMilliSec) {
        testStep(String.format("Enter Text '%s' with Value %s", element, value));
        verifyVisibilityofElement(element);
        element.type(value);
        waitABit(waitForMilliSec);
    }

    public void setAttibute(WebElementFacade element, String attName, String attValue, int waitForMilliSec) {
        testStep(String.format("Set Attribute  '%s' | Name : '%s' | Value : '%s'", element, attName, attValue));
        element.shouldBePresent();
        JavascriptExecutor js = (JavascriptExecutor) this.getDriver();
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attName, attValue);
        waitABit(waitForMilliSec);
    }

    public void setInputValue(WebElementFacade element, String value, int waitForMilliSec) {
        testStep(String.format("Set Value '%s' with Value %s", element, value));
        this.setAttibute(element, "value", value, waitForMilliSec);
        waitABit(waitForMilliSec);
    }

    public void NavigateToUIPage(WebElementFacade element) {
        testStep(String.format("Navigate to : '%s'", element));
        verifyVisibilityofElement(element);
        element.click();
    }

    public void verifyTextInPage(String... textList) {
        for (String currText : textList) {
            testStep(String.format("verify the text in the page :%s", textList));
            verifyVisibilityofElement(commonPage.LBL_FIELD(currText));
        }
    }

    public void verifyTextInPageWithParentSelector(String parentSelector, String... textList) {
        for (String currText : textList) {
            testStep(String.format("verify the text in the page :%s", textList));
            verifyVisibilityofElement(commonPage.LBL_FIELD_WITH_PARENT_SELECTOR(parentSelector, currText));
        }
    }

    public void CollapaseMenu(WebElementFacade element, String identifierValue) {
        testStep(String.format("Collapse Menu : '%s'", element));
        identifierValue = identifierValue.length() > 0 ? identifierValue : "collapsed";
        element.shouldBePresent();
        waitABit(1500);
        String getClassValue = element.getAttribute("class");
        if (!getClassValue.contains(identifierValue)) {
            element.click();
            waitABit(2000);
        } else {
            testStep(String.format("Element : '%s' already expanded/opened", element));
        }
        waitABit(2000);
    }

    public void CollapaseMenu(WebElementFacade element) {
        testStep(String.format("Collapse Menu : '%s'", element));
        element.shouldBePresent();
        String getClassValue = element.getAttribute("class");
        if (getClassValue.contains("collapsed")) {
            element.click();
            waitABit(2000);
        } else {
            testStep(String.format("Element : '%s' already expanded", element));
        }
        waitABit(2000);
    }

    public void acceptAllCookiesPage() {
        testStep("AccepAllCookiesPage");
        clickTextWithParentSelectorIfExist("//div[@id='onetrust-button-group-parent']", "Accept all");
        clickElementIfExist(commonPage.LBL_FIELD("Accept all"));
    }

    public void dismissCookieBanner() {
        testStep("Dismissing cookie banner if present");
        try {
            JavascriptExecutor js = (JavascriptExecutor) this.getDriver();

            try {
                js.executeScript(
                        "document.querySelectorAll('a[href*=\"PrivacyPolicy\"]').forEach(el => el.style.display='none');");
            } catch (Exception e) {
                testStep("Privacy policy link not found");
            }

            if (commonDEMOAWESOMEPage.BTN_COOKIE_PRIVACY_POLICY().isVisible()) {
                js.executeScript("arguments[0].style.display='none';",
                        commonDEMOAWESOMEPage.BTN_COOKIE_PRIVACY_POLICY().getWrappedElement());
                waitABit(500);
            }
        } catch (Exception e) {
            testStep("Cookie banner not found or already dismissed");
        }
    }

    @Step
    public void testStep(String message) {
        logger.info(" : {}", message);
        logger.debug(" : {}", message);
    }

}
