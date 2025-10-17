package stepdefinitions.DemoASPNETAwesome;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.pages.PageComponent;
import net.serenitybdd.core.pages.WebElementFacade;
import org.assertj.core.api.AbstractBooleanAssert;
import org.fluentlenium.core.annotation.Page;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.DemoASPNETAwesome.CommonDemoASPAwesomePage;
import pages.DemoASPNETAwesome.DemoASPAwesomePage;
import stepdefinitions.CommonStepDef;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GridStepDef {

    @Steps
    CommonStepDef commonStepDef;
    @Page
    DemoASPAwesomePage demoASPAwesemoPage;
    public static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    @Then("I verify the grid filter using parent control element")
    public void verifyGridFilteringParentControl() {
        AbstractBooleanAssert<?> isHeaderDisplayed = assertThat(demoASPAwesemoPage.HEADER().isVisible()).isTrue();
        logger.info(String.format("I verify the grid filter using parent control elements is %s ", isHeaderDisplayed), isHeaderDisplayed);
        demoASPAwesemoPage.FILER_GRID_USING_PARENT_CONTROL_GRID().isDisplayed();
    }

    @And("^I filter the grid using the following :$")
    public void filterGridFilteringParentControl(DataTable dataTable) {

        List<Map<String, String>> dataTableList = dataTable.asMaps(String.class, String.class);
        logger.info(String.format("dataTableList %s", dataTableList));

        for (Map<String, String> e : dataTableList) {
            WebElementFacade currentControl = null;
            logger.info(String.format("dataTableList | Field : %s | Value : %s", e.get("field"), e.get("value")));
            String currValue = String.valueOf(e.get("value"));
            switch (e.get("field")) {
                case "person":
                    currentControl = demoASPAwesemoPage.SEARCH_PERSON_TXTBOX();
                    break;
                case "food":
                    currentControl = demoASPAwesemoPage.SEARCH_FOOD_TXTBOX();
                    break;
                case "country":
                    selectFilterCountry(currValue);
                    break;
                default:
                    break;
            }
            if (currentControl != null) {
                assertThat(currentControl.isVisible()).isTrue();
                currentControl.typeAndTab(currValue);
                commonStepDef.waitForPageInSecond(1000);
                Assert.assertEquals(currentControl.getValue(), currValue);
            }
        }
        commonStepDef.waitForPageInSecond(2000);
    }

    public void selectFilterCountry(String selectCountry) {
        logger.info(String.format("selectFilterCountry | Value: %s", selectCountry));
        demoASPAwesemoPage.SEARCH_COUNTRY_DRPDOWN().click();
        demoASPAwesemoPage.SEARCH_COUNTRY_DRPDOWN_ITEM().typeAndEnter(selectCountry);
    }

    @Then("^I validate the grid result using the following :$")
    public void iValidateTheGridResultUsingTheFollowing(DataTable dataTable) {
        List<String> actualResultList = getGridResult();
        List<List<String>> expectedResultList = dataTable.asLists();

        assertThat(actualResultList.size()).isEqualTo(expectedResultList.size() - 1);
        Assert.assertEquals(String.format("The Expected(%s) and Actual(%s) Result size in Grid List is not the same ",
                        expectedResultList.size() - 1, actualResultList.size()),
                expectedResultList.size() - 1, actualResultList.size());


        for (int i = 0; i < actualResultList.size(); i++) {

            String currExpectedList = expectedResultList.get(i + 1).toString().replaceAll("\\[|\\]|,", "");
            String currActualList = actualResultList.get(i);
            logger.info(String.format("currExpectedList %s | currActualList %s", currExpectedList, actualResultList.get(i)));
            assertThat(actualResultList).contains(currExpectedList);
//            Assert.assertEquals(currActualList, currExpectedList);
        }
    }

    public List<String> getGridResult() {
        return demoASPAwesemoPage.FILER_GRID_USING_PARENT_CONTROL_GRID_ROW().stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }
}
