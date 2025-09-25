package pages;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractions;

public class CommonPage extends UIInteractions {

    public WebElementFacade LBL_FIELD(String name) {return $("//*[contains(text(),'"+name+"')]");}
    public WebElementFacade LBL_FIELD_WITH_PARENT_SELECTOR(String parentSelector, String name) {return $( parentSelector+"//*[contains(text(),'"+name+"')]");}
    public WebElementFacade PAGE_CONTROL_LABEL(String labelText) { return $(String.format("//*[contains(text(),'%s')]", labelText));
    }


}
