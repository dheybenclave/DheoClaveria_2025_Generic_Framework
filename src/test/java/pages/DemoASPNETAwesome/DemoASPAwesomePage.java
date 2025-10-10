package pages.DemoASPNETAwesome;

import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import pages.ProDinnerPage.CommonProDinnerPage;


public class DemoASPAwesomePage extends CommonProDinnerPage {

    String PARENT_HEADER = "//div[@id='demoPage']//*[@class='conthead']";
    public WebElementFacade NAVIGATION_SEARCH_TXTBOX() { return $("//div[@id='demoMenu']//input[@id='msearch']");}

    public WebElementFacade HEADER() { return $("//div[@id='demoPage']//*[@class='conthead']");}

    public WebElementFacade FILER_GRID_USING_PARENT_CONTROL_GRID() {
        return $(PARENT_HEADER + "/following::*[@id='Grid1']");
    }
    public ListOfWebElementFacades FILER_GRID_USING_PARENT_CONTROL_GRID_ROW() {
        String selector =  PARENT_HEADER + "//*/following::*[@id='Grid1']//div[@class='awe-content awe-tablc']//tbody/tr";
        return findAll(By.xpath(selector));
    }
    public WebElementFacade SEARCH_PERSON_TXTBOX() {
        return $(PARENT_HEADER + "/following::*[@id='txtPerson-awed']");
    }

    public WebElementFacade SEARCH_FOOD_TXTBOX() {
        return $(PARENT_HEADER + "/following::*[@id='txtFood-awed']");
    }

    public WebElementFacade SEARCH_COUNTRY_DRPDOWN() {
        return $(PARENT_HEADER + "/following::*[@id='selCountry']/..");
    }

    public WebElementFacade SEARCH_COUNTRY_DRPDOWN_ITEM() {
        //*[@id="selCountry-dropmenu"]//input[@class="o-src awe-txt"]
        return $("//*[@id='selCountry-dropmenu']//input[@class='o-src awe-txt']");
    }


}
