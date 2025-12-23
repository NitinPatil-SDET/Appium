package AppiumAdvanceGesture;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC006_DragAndDropGesture extends BaseClass {

    @Test
    public void verifyDragAndDropGesture() {

        /* =========================================================
           STEP 1: Navigate to Views screen
           ========================================================= */
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        /* =========================================================
           STEP 2: Open Drag and Drop screen
           ========================================================= */
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        /* =========================================================
           STEP 3: Identify the source element to drag
           ========================================================= */
        WebElement sourceDot =
                driver.findElement(
                        AppiumBy.id("io.appium.android.apis:id/drag_dot_1")
                );

        /* =========================================================
           DRAG & DROP GESTURE (mobile: dragGesture)
           ---------------------------------------------------------
           WHAT THIS DOES:
           - Simulates a real user drag-and-drop action
           - Long-presses the source element
           - Drags it to the target coordinates
           - Releases it at the destination

           WHY COORDINATES ARE USED HERE:
           - The API Demos Drag & Drop screen does not expose
             a target element locator
           - Hence endX and endY are required

           PARAMETERS:
           - elementId : Source element to drag
           - endX      : X coordinate of drop location
           - endY      : Y coordinate of drop location
           ========================================================= */
        ((JavascriptExecutor) driver).executeScript(
                "mobile: dragGesture",
                ImmutableMap.of(
                        "elementId",
                        ((RemoteWebElement) sourceDot).getId(),
                        "endX", 647,
                        "endY", 577
                )
        );

        /* =========================================================
           STEP 4: Validate drag-and-drop result
           ---------------------------------------------------------
           - After successful drop, app displays "Dropped!"
           ========================================================= */
        WebElement dragResult =
                driver.findElement(
                        AppiumBy.id("io.appium.android.apis:id/drag_result_text")
                );

        Assert.assertEquals(
                dragResult.getText(),
                "Dropped!",
                "Drag and Drop action failed"
        );
    }
}
