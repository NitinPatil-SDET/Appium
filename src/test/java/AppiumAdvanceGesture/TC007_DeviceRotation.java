package AppiumAdvanceGesture;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



import java.time.Duration;

public class TC007_DeviceRotation extends BaseClass {

    @Test
    public void verifyDeviceRotationDuringInteraction() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        /* =========================================================
           STEP 1: Navigate to Preference screen
           ========================================================= */
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.accessibilityId("Preference")
                )
        ).click();

        /* =========================================================
           STEP 2: Open "3. Preference dependencies"
           ========================================================= */
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")
                )
        ).click();

        /* =========================================================
           DEVICE ROTATION: PORTRAIT → LANDSCAPE
           ---------------------------------------------------------
           - Uses DeviceRotation (angle-based rotation)
           - Rotates the device along Z-axis by 90 degrees
           - Simulates real user rotating the phone

           Axes meaning:
           X-axis → forward/back tilt (not used here)
           Y-axis → left/right tilt (not used here)
           Z-axis → screen rotation (MOST IMPORTANT)
           ========================================================= */
        DeviceRotation landscape = new DeviceRotation(0, 0, 90);
        driver.rotate(landscape);

        /* =========================================================
           STEP 3: Enable WiFi checkbox in landscape mode
           ========================================================= */
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/checkbox")
                )
        ).click();

        /* =========================================================
           STEP 4: Open WiFi Settings dialog
           ========================================================= */
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.androidUIAutomator(
                                "new UiSelector().text(\"WiFi settings\")"
                        )
                )
        ).click();

        /* =========================================================
           STEP 5: Enter WiFi name
           ---------------------------------------------------------
           - Verifies that input works correctly after rotation
           ========================================================= */
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("android:id/edit")
                )
        ).sendKeys("Test123");

        /* =========================================================
           STEP 6: Confirm WiFi settings
           ========================================================= */
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();

        /* =========================================================
           DEVICE ROTATION: LANDSCAPE → PORTRAIT
           ---------------------------------------------------------
           - Restores the device to its original orientation
           - Good practice to reset state after test execution
           ========================================================= */
        DeviceRotation portrait = new DeviceRotation(0, 0, 0);
        driver.rotate(portrait);
    }
}
