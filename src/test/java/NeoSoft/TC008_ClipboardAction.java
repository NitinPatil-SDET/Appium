package NeoSoft;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC008_ClipboardAction extends BaseClass {

    @Test
    public void verifyClipboardUsageForTextInput() {

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
                        AppiumBy.xpath(
                                "//android.widget.TextView[@content-desc='3. Preference dependencies']"
                        )
                )
        ).click();

        /* =========================================================
           STEP 3: Enable WiFi checkbox
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
           CLIPBOARD ACTION – SET TEXT
           ---------------------------------------------------------
           - Places text directly into the device clipboard
           - Faster and more reliable than typing long strings
           - Very useful for copy/paste and share validations
           ========================================================= */
        String wifiName = "Nitin SDET";
        driver.setClipboardText(wifiName);

        /* =========================================================
           STEP 5: Paste text from clipboard into input field
           ---------------------------------------------------------
           - Reads clipboard content using getClipboardText()
           - Avoids flaky sendKeys with long or special text
           ========================================================= */
        String clipboardText = driver.getClipboardText();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        AppiumBy.id("android:id/edit")
                )
        ).sendKeys(clipboardText);

        /* =========================================================
           OPTIONAL VALIDATION
           ---------------------------------------------------------
           - Ensures clipboard text was correctly read
           ========================================================= */
        Assert.assertEquals(
                clipboardText,
                wifiName,
                "Clipboard text does not match expected value"
        );

        /* =========================================================
           STEP 6: Confirm WiFi settings
           ========================================================= */
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        AppiumBy.id("android:id/button1")
                )
        ).click();
    }
}
