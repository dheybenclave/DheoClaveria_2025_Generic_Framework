package stepdefinitions;

import actors.ActorLists;
import io.cucumber.java.*;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.core.pages.PageComponent;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterDefinitions extends PageComponent {
    public static Logger logger = LoggerFactory.getLogger(ParameterDefinitions.class);
    ActorLists actorLists;
    PageObject pageObject;

//    @ParameterType(".*")
//    public Actor actor(String actorName) {
//        actorName = actorName.isEmpty() ? "tester" : actorName;
//        return OnStage.theActorCalled(actorName);
//    }
//
//
//    @Before
//    public void setTheStage() {
//        OnStage.setTheStage(new OnlineCast());
//    }


}
