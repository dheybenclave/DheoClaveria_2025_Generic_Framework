package pages.Wikipedia;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;
import org.openqa.selenium.By;

public class WikipediaPage extends UIInteractions {

    public WebElementFacade SEARCH_INPUT() {
        return $("//input[@id='searchInput']");
    }

    public WebElementFacade SEARCH_BUTTON() {
        return $("//button[@type='submit']");
    }

    public WebElementFacade SEARCH_RESULT_CONTAINER() {
        return $("//div[@id='mw-content-text']");
    }

    public WebElementFacade FIRST_HEADING() {
        return $("//h1[@id='firstHeading']");
    }

    public WebElementFacade SEARCH_RESULTS_LIST() {
        return $("//ul[@class='mw-search-results']");
    }

    // Dynamic locator for search result items
    public WebElementFacade SEARCH_RESULT_ITEM(String searchTerm) {
        return $(String.format("//ul[@class='mw-search-results']//a[contains(text(),'%s')]", searchTerm));
    }
}