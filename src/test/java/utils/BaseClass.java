package utils;

import net.thucydides.core.pages.PageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseClass extends PageObject {
    public static Logger logger = LoggerFactory.getLogger(BaseClass.class);

    /**
     * Block ads at page level using JavaScript injection
     * Call this after page loads to hide ad elements
     */
    public void injectAdBlocker() {
        try {
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) getDriver();
            
            String hideAdsScript = 
                "const adSelectors = ['[class*=\"ad-\"]', '[class*=\"ads-\"]', '[class*=\"advert\"]', '[id*=\"ad-\"]', '[id*=\"ads-\"]', 'iframe[src*=\"doubleclick\"]', '[class*=\"sponsored\"]', '[class*=\"promotion\"]'];" +
                "adSelectors.forEach(function(selector) { document.querySelectorAll(selector).forEach(function(el) { el.style.display = 'none'; }); });";
            
            js.executeScript(hideAdsScript);
        } catch (Exception e) {
            logger.warn("Could not inject ad blocker: " + e.getMessage());
        }
    }
}