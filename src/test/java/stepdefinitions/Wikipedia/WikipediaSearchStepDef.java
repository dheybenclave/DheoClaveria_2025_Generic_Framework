package stepdefinitions.Wikipedia;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.pages.WebElementFacade;
import org.fluentlenium.core.annotation.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.Wikipedia.WikipediaPage;
import stepdefinitions.CommonStepDef;

public class WikipediaSearchStepDef {

    private static final Logger logger = LoggerFactory.getLogger(WikipediaSearchStepDef.class);

    @Steps
    CommonStepDef commonStepDef;

    @Page
    WikipediaPage wikipediaPage;

    @When("I search for {string}")
    public void iSearchFor(String searchTerm) {
        logger.info("Searching for: {}", searchTerm);
        commonStepDef.testStep(String.format("I search for '%s'", searchTerm));

        // Wait for search input to be visible
        WebElementFacade searchInput = wikipediaPage.SEARCH_INPUT();
        commonStepDef.verifyVisibilityofElement(searchInput);

        // Clear and enter search term
        commonStepDef.enterText(searchInput, searchTerm, 1000);

        // Click search button or press Enter
        wikipediaPage.SEARCH_BUTTON().waitUntilVisible().click();
        commonStepDef.waitForPageInSecond(2000);
    }

    @Then("I verify the search results are displayed")
    public void iVerifyTheSearchResultsAreDisplayed() {
        logger.info("Verifying search results are displayed");
        commonStepDef.testStep("I verify the search results are displayed");

        // Verify search results container or first heading is visible
        WebElementFacade resultElement = wikipediaPage.FIRST_HEADING();
        commonStepDef.verifyVisibilityofElement(resultElement);
    }

    // Note: "I verify the following text in the page :" is already defined in CommonStepDef
    // Use commonStepDef.verifyTextInPage() for text verification
}