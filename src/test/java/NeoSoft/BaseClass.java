package NeoSoft; // <-- change to match your project folder

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class BaseClass {

    // Fields (must be declared inside the class)
    private AppiumDriverLocalService service;
    protected AndroidDriver driver;

    @BeforeClass
    public void configureAppium() throws Exception {
        // Build Appium service (verify the path to main.js matches your Appium installation)
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\Client\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();

        service.start();
        if (!service.isRunning()) {
            throw new IllegalStateException("Appium service failed to start");
        }

        // Use UiAutomator2Options (W3C) and set required capabilities
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2") // REQUIRED for Appium 2.x
                .setDeviceName("NitinEmulator")
                // .setUdid("emulator-5554") // uncomment and set if using a real device or specific emulator id
                .setApp("C:\\SDET with Nitin\\AppiumLearning\\resources\\ApiDemos-debug.apk")
                .setNewCommandTimeout(Duration.ofSeconds(300));

        // Create driver — use URL directly
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }




    public void longClickGesture(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId()
        ));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            try { driver.quit(); } catch (Exception ignored) {}
        }
        if (service != null && service.isRunning()) {
            try { service.stop(); } catch (Exception ignored) {}
        }
    }
}
