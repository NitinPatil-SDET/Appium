package AppiumAdvanceGesture;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC002_ReadAssetData extends BaseClass {

    @Test
    public void ReadAssetData(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5) );
        //Click on contain
        By ContentLocator = AppiumBy.accessibilityId("Content");
        WebElement Content = driver.findElement(ContentLocator);
        //wait.until(ExpectedConditions.elementToBeClickable(Content));
        Content.click();

        //Click on Asset
        By AssetsLocator = AppiumBy.accessibilityId("Assets");
        WebElement Assets = driver.findElement(AssetsLocator);
        //wait.until(ExpectedConditions.elementToBeClickable(Assets));
        Assets.click();

        //Click on Read Asset
        By ReadAssetLocator = AppiumBy.accessibilityId("Read Asset");
        WebElement ReadAsset = driver.findElement(ReadAssetLocator);
        //wait.until(ExpectedConditions.elementToBeClickable(ReadAsset));
        ReadAsset.click();

        //Get text and print on console
        By TextLocator = AppiumBy.id("io.appium.android.apis:id/text");
        WebElement Text = driver.findElement(TextLocator);
        System.out.println("The Text is --> : "+Text.getText());
    }
}
