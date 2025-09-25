package pages.ProDinnerPage;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;

public class CommonProDinnerPage extends UIInteractions {
    static String PARENT_HEADER = "//div[@id='header']//*[@role='navigation']";

    //    @FindBy(xpath = "//a[@href='/Account/SignIn']")
    public WebElementFacade LNK_SIGN_IN() {return $("//a[@href='/Account/SignIn']");}
    public WebElementFacade LNK_SIGN_OUT() {return $(PARENT_HEADER + "//a[@href='/Account/SignOut']");}
    public WebElementFacade NAV_NAME(String name) {return $(PARENT_HEADER + "//a[text()='"+name+"']");}
    public WebElementFacade TXT_FIELD(String name) {return $(  "//*[contains(text(),'"+name+"')]");}



}
