package pages.ProDinnerPage;

import net.serenitybdd.core.pages.WebElementFacade;

public class SignInPage extends CommonProDinnerPage {

    public WebElementFacade TXT_USERNAME() {return $("//input[@id='Login-awed']");}
    public WebElementFacade TXT_PASSWORD() {return $("//input[@id='Password']");}
    public WebElementFacade BTN_SIGN_IN() {return $("//input[@value='Sign in']");}
}
