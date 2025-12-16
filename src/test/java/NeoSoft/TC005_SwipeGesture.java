package NeoSoft;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC005_SwipeGesture extends BaseClass {

    @Test
    public void verifySwipeGestureOnGalleryImages() {

        /* =========================================================
           STEP 1: Navigate to Views screen
           ========================================================= */
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        /* =========================================================
           STEP 2: Navigate to Gallery
           ========================================================= */
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();

        /* =========================================================
           STEP 3: Open Photos section
           ========================================================= */
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();

        /* =========================================================
           STEP 4: Identify the first image
           ========================================================= */
        WebElement firstImage =
                driver.findElement(
                        AppiumBy.xpath("(//android.widget.ImageView)[1]")
                );

        /* =========================================================
           VALIDATION BEFORE SWIPE
           ---------------------------------------------------------
           - Before swipe, the first image should be in focus
           - 'focusable = true' confirms the image is currently selected
           ========================================================= */
        Assert.assertEquals(
                firstImage.getAttribute("focusable"),
                "true",
                "First image is not focused before swipe"
        );

        /* =========================================================
           SWIPE GESTURE (W3C mobile: swipeGesture)
           ---------------------------------------------------------
           WHAT THIS DOES:
           - Performs a swipe gesture on a specific element
           - Swipes LEFT inside the bounds of the element
           - Moves the gallery to the next image

           WHY elementId IS USED:
           - Avoids hard-coded screen coordinates
           - Works across different devices & resolutions
           - Recommended approach in Appium 2.x

           PARAMETERS:
           - elementId : Target element to swipe on
           - direction : left | right | up | down
           - percent   : Swipe distance (0.0 to 1.0 of element size)
           ========================================================= */
        ((JavascriptExecutor) driver).executeScript(
                "mobile: swipeGesture",
                ImmutableMap.of(
                        "elementId",
                        ((RemoteWebElement) firstImage).getId(),
                        "direction", "left",
                        "percent", 0.25
                )
        );

        /* =========================================================
           VALIDATION AFTER SWIPE
           ---------------------------------------------------------
           - After swipe, focus should move away from the first image
           - 'focusable = false' confirms swipe was successful
           ========================================================= */
        Assert.assertEquals(
                firstImage.getAttribute("focusable"),
                "false",
                "Swipe gesture did not move to the next image"
        );
    }
}
