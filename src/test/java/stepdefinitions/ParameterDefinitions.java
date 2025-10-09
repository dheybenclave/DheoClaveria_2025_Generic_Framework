package stepdefinitions;

import actors.ActorLists;
import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.core.pages.PageComponent;
import net.serenitybdd.core.pages.PageObject;
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
    @BeforeAll
    public static void globalDriverSetup() {
        logger.info("Running global WebDriverManager setup for Chrome and Edge");
        try {
            // Downloads the appropriate driver for the platform and sets the webdriver system property
            WebDriverManager.chromedriver().setup();
            WebDriverManager.edgedriver().setup();
        } catch (Exception e) {
            logger.warn("WebDriverManager global setup failed: {}", e.getMessage());
        }
    }


}
