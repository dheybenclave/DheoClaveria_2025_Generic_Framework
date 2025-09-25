package stepdefinitions.ProDinnerASPNetAwesome;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.pages.PageComponent;
import pages.ProDinnerPage.MealPage;
import stepdefinitions.CommonStepDef;
import utils.Utilities;

import java.util.List;
import java.util.Map;

public class MealPageStepDef extends PageComponent {

    CommonStepDef commonStepDef;
    MealPage mealPage;
    Utilities utils = new Utilities();

    @Then("validate and verify a meal created using the following :$")
    public void createMeal(DataTable dataTable) {

        commonStepDef.NavigateToUIPage(mealPage.NAV_NAME("Meals"));

        List<Map<String, String>> dataTableList = dataTable.asMaps(String.class, String.class);
        commonStepDef.testStep(String.format("Create Meal : dataTableList %s", dataTableList));

        for (Map<String, String> e : dataTableList) {
            String name = String.format("%s_%s", e.get("Name"), utils.generateRandomStringInteger(5));
            String comments = String.format("%s_%s", e.get("Comments"), utils.generateRandomStringInteger(5));

            commonStepDef.testStep(String.format("dataTableList | Name : '%s' | Comments : '%s'", name, comments));

            commonStepDef.clickElement(mealPage.BTN_CREATE());
            commonStepDef.setInputValue(mealPage.TXT_NAME(), name, 2000);
            commonStepDef.enterText(mealPage.TXT_COMMENT(), comments, 2000);
            commonStepDef.clickElement(mealPage.BTN_SAVE());
        }
        // dataTableList.stream().forEach(e -> {
        // String name = e.get("Name");
        // String comments = e.get("Comments");
        // commonStepDef.testStep(String.format("dataTableList | Name : '%s' | Comments
        // : '%s'", name, comments));
        //
        // commonStepDef.clickElement(mealPage.BTN_CREATE());
        // commonStepDef.setInputValue(mealPage.TXT_NAME(), name, 2000);
        // commonStepDef.enterText(mealPage.TXT_COMMENT(), comments, 2000);
        // commonStepDef.clickElement(mealPage.BTN_SAVE());
        // }
        // );
        verifyMeal(dataTableList);
    }

    public void verifyMeal(List<Map<String, String>> dataTableList) {
        commonStepDef.testStep(String.format("Verify Meal Created : dataTable %s", dataTableList));
        dataTableList.forEach(e -> commonStepDef.verifyTextInPage(e.get("Name"), e.get("Comments")));
    }


}
