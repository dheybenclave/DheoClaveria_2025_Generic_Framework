package utils;

import io.cucumber.java.BeforeAll;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.pages.PageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stepdefinitions.ParameterDefinitions;

public class BaseClass extends PageObject {
    public static Logger logger = LoggerFactory.getLogger(ParameterDefinitions.class);

    @BeforeAll
    public static void globalDriverSetup() {
        logger.info("Running global WebDriverManager setup for Chrome and Edge");
        try {
            // Downloads the appropriate driver for the platform and sets the webdriver system property
            WebDriverManager.chromedriver().setup();

            WebDriverManager.chromedriver().create();

        } catch (Exception e) {
            logger.warn("WebDriverManager global setup failed: {}", e.getMessage());
        }
    }
}