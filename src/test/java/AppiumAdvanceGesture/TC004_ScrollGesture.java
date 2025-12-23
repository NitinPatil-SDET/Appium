package AppiumAdvanceGesture;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC004_ScrollGesture extends BaseClass {

    @Test
    public void verifyScrollUsingDifferentMethods() {

        // Step 1: Navigate to Views screen
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        /* =========================================================
           METHOD 1: scrollIntoView()  (SEARCH-BASED SCROLL)
           ---------------------------------------------------------
           - Best when the target text is known
           - Automatically scrolls until element is found
           - Stops immediately once element is visible
           - Most reliable for menus & settings
           ========================================================= */

        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector().scrollable(true))" +
                                ".scrollIntoView(new UiSelector().text(\"WebView\"))"
                )
        );



        /* =========================================================
           METHOD 2: scrollGesture() USING COORDINATES (NOT RECOMMENDED)
           ---------------------------------------------------------
           - Scrolls a fixed screen area
           - Breaks on different screen sizes/devices
           - Included ONLY for learning purposes
           - Avoid in real frameworks
           ========================================================= */
        /*
        boolean canScrollMoreByCoordinates;
        do {
            canScrollMoreByCoordinates = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript(
                            "mobile: scrollGesture",
                            ImmutableMap.of(
                                    "left", 100,
                                    "top", 100,
                                    "width", 200,
                                    "height", 400,
                                    "direction", "down",
                                    "percent", 0.75
                            )
                    );
        } while (canScrollMoreByCoordinates);

         */



        /* =========================================================
           METHOD 3: scrollGesture() USING ELEMENT (BEST PRACTICE)
           ---------------------------------------------------------
           - Scrolls inside a specific scrollable container
           - Works across devices and screen sizes
           - Recommended for production automation
           ========================================================= */
/*
        WebElement scrollableList =
                driver.findElement(AppiumBy.id("android:id/list"));

        boolean canScrollMoreByElement;
        do {
            canScrollMoreByElement = (Boolean) ((JavascriptExecutor) driver)
                    .executeScript(
                            "mobile: scrollGesture",
                            ImmutableMap.of(
                                    "elementId",
                                    ((RemoteWebElement) scrollableList).getId(),
                                    "direction", "down",
                                    "percent", 0.75
                            )
                    );
        } while (canScrollMoreByElement);

 */


        // Step 2: Click WebView after scrolling
        WebElement webView =
                driver.findElement(AppiumBy.accessibilityId("WebView"));
        webView.click();

        // Step 3: Validation
        WebElement pageText =
                driver.findElement(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().textContains(\"Selenium sandbox\")"
                        )
                );

        Assert.assertTrue(
                pageText.isDisplayed(),
                "WebView page content is not displayed"
        );
    }
}
