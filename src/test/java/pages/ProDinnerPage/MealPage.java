package pages.ProDinnerPage;

import net.serenitybdd.core.pages.WebElementFacade;

public class MealPage extends CommonProDinnerPage {

    static String PARENT_HEADER = "//main[@class='main cont']";
    public WebElementFacade BTN_CREATE() {return $(PARENT_HEADER + "//button[@class='awe-btn' and text()='Create']");}

    static String PARENT_GRID = "//div[@data-g='mealsGrid' and  contains(@class,'o-glnew')]";

    public WebElementFacade BTN_CHANGE_PICTURE() { return $(PARENT_HEADER + "//button[contains(@class,'changePic')]");}
    public WebElementFacade TXT_NAME() {return $(PARENT_HEADER + "//input[@name='name']");}
    public WebElementFacade TXT_COMMENT() {return $(PARENT_HEADER + "//textarea[@name='Comments']");}
    public WebElementFacade BTN_SAVE() {return $(PARENT_HEADER + "//button[text()='Save']");}
    public WebElementFacade BTN_CANCEL() {return $(PARENT_HEADER + "//button[text()='Cancel']");}
    public WebElementFacade BTN_EDIT(String mealName) {return $("//div[text()='" + mealName + "']/following::button[text()='Edit'][1]");}

}
