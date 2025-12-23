package AppiumAdvanceGesture;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC001_SetWiFiName extends BaseClass {

    @Test
    public void SetWiFiName() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Click Preference
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Preference"))).click();

        // 2. Click "3. Preference dependencies"
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.xpath("//android.widget.TextView[@content-desc='3. Preference dependencies']")
        )).click();

        // 3. Click WiFi checkbox
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("android:id/checkbox"))).click();

        // 4. Click WiFi Settings (corrected UiSelector)
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"WiFi settings\")")
        )).click();

        // 5. Enter WiFi name
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("android:id/edit")
        )).sendKeys("Test123");

        // 6. Click OK
        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("android:id/button1")
        )).click();
    }
}
