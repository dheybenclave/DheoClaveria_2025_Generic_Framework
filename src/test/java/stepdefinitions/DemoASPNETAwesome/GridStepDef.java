package stepdefinitions.DemoASPNETAwesome;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.pages.WebElementFacade;
import org.assertj.core.api.AbstractBooleanAssert;
import org.fluentlenium.core.annotation.Page;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.DemoASPNETAwesome.CommonDemoASPAwesomePage;
import pages.DemoASPNETAwesome.DemoASPAwesomePage;
import stepdefinitions.CommonStepDef;
import utils.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
        // Skips the first row (header) and takes everything from index 1 to the end
        List<List<String>> expectedResultList = dataTable.asLists().subList(1, dataTable.asLists().size());
        logger.info(String.format("Expected Result List from DataTable: %s", expectedResultList));
        logger.info(String.format("Actual Result List from DataTable: %s", actualResultList));

        assertThat(actualResultList.size()).isEqualTo(expectedResultList.size());
        Assert.assertEquals(
                String.format("The Expected(%s) and Actual(%s) Result size in Grid List is not the same ", expectedResultList.size(), actualResultList.size()),
                expectedResultList.size(),
                actualResultList.size());

        for (int i = 0; i < actualResultList.size(); i++) {

            String currExpectedList = expectedResultList.get(i).toString().replaceAll("\\[|\\]|,", "");
            String currActualList = actualResultList.get(i);
            logger.info(String.format("currExpectedList %s | currActualList %s", currExpectedList, actualResultList.get(i)));
            assertThat(actualResultList).contains(currExpectedList);
//            Assert.assertEquals(currActualList, currExpectedList);
        }
    }

    public List<String> getGridResult() {
        logger.info("Getting grid results from the page...");
        return demoASPAwesemoPage.FILER_GRID_USING_PARENT_CONTROL_GRID_ROW().stream()
                .map(WebElementFacade::getText)
                .collect(Collectors.toList());
    }

    @Then("^I validate the grid result :$")
    public void iValidateTheGridResultWithDataTable(DataTable dataTable) throws IOException {
        logger.info("Validating grid results against expected results from JSON based on filters...");
        List<Map<String, String>> filters = dataTable.asMaps(String.class, String.class);

        List<String> actualResultList = getGridResult();
        List<String> expectedRows = getExpectedGridResultsFromJson(filters);

        logger.info(String.format("Expected Rows from JSON: %s", expectedRows));
        logger.info(String.format("Actual Rows from Grid: %s", actualResultList));

        Assert.assertTrue(
                String.format("Grid result mismatch for filters %s. Expected: %s | Actual: %s", filters, expectedRows, actualResultList),
                isGridResultMatch(actualResultList, expectedRows)
        );
    }

    private List<String> getExpectedGridResultsFromJson(List<Map<String, String>> filters) throws IOException {
        logger.info(String.format("Reading expected grid results from JSON for filters: %s", filters));
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/json/gridFilter.json";
        JsonReader jsonReader = new JsonReader();
        List<Map<String, Object>> expectedResults = jsonReader.getGridExpectedResults(filePath, filters);
        return expectedResults.stream().map(this::buildGridRowText).collect(Collectors.toList());
    }

    private String buildGridRowText(Map<String, Object> row) {
        logger.info(String.format("Building grid row text from JSON row: %s", row));
        return String.format("%s %s %s %s %s %s %s",
                        row.get("Id"),
                        row.get("Person"),
                        row.get("Food"),
                        row.get("Country"),
                        row.get("Date"),
                        row.get("Location"),
                        row.get("Chef"))
                .replaceAll("\\s+", " ")
                .trim();
    }

    private boolean isGridResultMatch(List<String> actual, List<String> expected) {
        logger.info(String.format("Comparing actual grid results with expected results. Actual: %s | Expected: %s", actual, expected));
        if (actual.size() != expected.size()) {
            return false;
        }

        List<String> normalizedActual = actual.stream()
                .map(value -> value.replaceAll("\\s+", " ").trim())
                .collect(Collectors.toList());

        return expected.stream().allMatch(normalizedActual::contains);
    }
}
