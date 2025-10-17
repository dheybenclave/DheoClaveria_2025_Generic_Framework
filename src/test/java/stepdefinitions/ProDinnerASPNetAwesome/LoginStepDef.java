package stepdefinitions.ProDinnerASPNetAwesome;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.WhenPageOpens;
import net.serenitybdd.core.pages.PageComponent;
import net.serenitybdd.screenplay.Actor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.fluentlenium.core.annotation.Page;
import pages.ProDinnerPage.CommonProDinnerPage;
import pages.ProDinnerPage.SignInPage;
import stepdefinitions.CommonStepDef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ExcelReader;
import utils.UserCredentials;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoginStepDef {
    public static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    @Steps
    CommonStepDef commonStepDef;
    @Page
    CommonProDinnerPage commonPage;
    @Page
    SignInPage signInPage;

    String _username, _password = "";

    @WhenPageOpens
    public void maximizeScreen() {
        commonStepDef.getDriver().manage().window().maximize();
    }

    @Given("{} is login in {} using {}")
    public void LoginUser(String actor, String page, String role) {

        commonStepDef.navigatePage(page);
        commonStepDef.clickElement(commonPage.LNK_SIGN_IN());

        commonStepDef.testStep(String.format("I login in the application as %s", actor));
        GetCredentials(role);
        commonStepDef.enterText(signInPage.TXT_USERNAME(), _username, 2000);
        commonStepDef.enterText(signInPage.TXT_PASSWORD(), _password, 2000);
        commonStepDef.clickElement(signInPage.BTN_SIGN_IN());  //clickOn(signInPage.BTN_SIGN_IN);
        commonStepDef.waitForPageInSecond(2000);
        commonStepDef.verifyVisibilityofElement(commonPage.LNK_SIGN_OUT());

    }

    private void GetCredentials(String role) {

        ExcelReader reader = new ExcelReader();
        try {
            String exeFileName = "userCredentials";
            String exeSheetName = "UserList";
            // Use absolute path to ensure file is found
            String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/" + exeFileName + ".xlsx";
            Map<String, String> credentials = reader.getUsernameAndPasswordByRole(filePath, exeSheetName, role);
            _username = credentials.get("username");
            _password = credentials.get("password");

        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        logger.info(String.format("Get Credentials of %s", actor));
//        switch (actor) {
//            case "ProDinnerAdmin":
//                _username = UserCredentials.ProDinnerAdmin.getUsername();
//                _password = UserCredentials.ProDinnerAdmin.getPassword();
//                break;
//            case "Admin":
//                _username = UserCredentials.Admin.getUsername();
//                _password = UserCredentials.Admin.getPassword();
//                break;
//            default:
//                break;
//        }
    }


}
