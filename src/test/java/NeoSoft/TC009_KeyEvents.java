package NeoSoft;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC009_KeyEvents extends BaseClass {

    @Test
    public void verifyAndroidKeyEventsProperly() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        /* =========================================================
           STEP 1: Navigate to Preference screen
           ========================================================= */
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.accessibilityId("Preference")
        )).click();

        /* =========================================================
           STEP 2: Open Preference Dependencies
           ========================================================= */
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")
        )).click();

        /* =========================================================
           STEP 3: Enable WiFi checkbox
           ========================================================= */
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("android:id/checkbox")
        )).click();

        /* =========================================================
           STEP 4: Open WiFi Settings dialog
           ========================================================= */
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator(
                        "new UiSelector().text(\"WiFi settings\")"
                )
        )).click();

        /* =========================================================
           STEP 5: Enter WiFi name
           ========================================================= */
        WebElement wifiInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("android:id/edit")
                )
        );

        wifiInput.sendKeys("KeyEvent_Test");

        /* =========================================================
           KEY EVENT 1: ENTER
           ---------------------------------------------------------
           - ENTER adds a newline (\n) in EditText
           - So text must be NORMALIZED before assertion
           ========================================================= */
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

        String actualText = wifiInput.getText().trim();

        Assert.assertEquals(
                actualText,
                "KeyEvent_Test",
                "Text mismatch after ENTER key"
        );

        /* =========================================================
           STEP 6: Close dialog (CORRECT WAY)
           ---------------------------------------------------------
           - ENTER does NOT close dialog in API Demos
           - OK button must be clicked
           ========================================================= */
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("android:id/button1")
        )).click();

        /* =========================================================
           KEY EVENT 2: BACK
           ---------------------------------------------------------
           - Simulates Android BACK button
           - Navigates to Preference screen
           ========================================================= */
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        /* =========================================================
           FINAL VALIDATION
           ---------------------------------------------------------
           - Confirms BACK navigation worked
           ========================================================= */
        Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.accessibilityId("Preference")
                )).isDisplayed(),
                "BACK key navigation failed"
        );
    }
}
