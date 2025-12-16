package NeoSoft;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_LongPressGesture extends BaseClass {

    @Test
    public void verifyLongPressGesture() {

        /* =========================================================
           STEP 1: Navigate to Views
           ========================================================= */
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        /* =========================================================
           STEP 2: Open Expandable Lists
           ========================================================= */
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();

        /* =========================================================
           STEP 3: Open Custom Adapter option
           ========================================================= */
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

        /* =========================================================
           STEP 4: Identify the element to perform long press
           ========================================================= */
        WebElement peopleNames =
                driver.findElement(
                        AppiumBy.xpath("//android.widget.TextView[@text='People Names']")
                );

        /* =========================================================
           METHOD 1: longClickGesture using JavaScript (W3C Gesture)
           ---------------------------------------------------------
           - Triggers Android native long-press
           - Recommended for Appium 2.x
           - Uses elementId instead of coordinates
           ========================================================= */
        ((JavascriptExecutor) driver).executeScript(
                "mobile: longClickGesture",
                ImmutableMap.of(
                        "elementId",
                        ((RemoteWebElement) peopleNames).getId()
                )
        );

        /* =========================================================
           METHOD 2: Reusable longClickGesture method (BaseClass)
           ---------------------------------------------------------
           - Cleaner and reusable
           - Preferred approach in real frameworks
           - Uncomment when using utility method
           ========================================================= */
        // longClickGesture(peopleNames);

        /* =========================================================
           STEP 5: Validate the long-press action result
           ========================================================= */
        WebElement sampleAction =
                driver.findElement(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().text(\"Sample action\")"
                        )
                );

        Assert.assertEquals(
                sampleAction.getText(),
                "Sample action",
                "Sample action text is not displayed after long press"
        );
    }
}
