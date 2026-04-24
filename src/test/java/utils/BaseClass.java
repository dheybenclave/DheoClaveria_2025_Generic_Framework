package utils;

import net.thucydides.core.pages.PageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseClass extends PageObject {
    public static Logger logger = LoggerFactory.getLogger(BaseClass.class);

    /**
     * Block ads at page level using JavaScript injection.
     * Called automatically after each navigation via CommonStepDef.
     * Loads comprehensive ad-blocking script from external resource.
     */
    public void injectAdBlocker() {
        try {
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) getDriver();

            // Load script from external resource to avoid string escaping issues
            java.util.Scanner scanner = new java.util.Scanner(
                getClass().getClassLoader().getResourceAsStream("adblocker.js"),
                "UTF-8"
            );
            String adBlockScript = scanner.useDelimiter("\\A").next();
            scanner.close();

            js.executeScript(adBlockScript);
        } catch (Exception e) {
            logger.warn("Could not inject ad blocker: " + e.getMessage());
        }
    }
}