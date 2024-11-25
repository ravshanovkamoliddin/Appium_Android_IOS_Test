
import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class AppiumTest extends BaseTest {

    @Test
    public void WifiSettings() throws MalformedURLException {

        //Preference.click
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();

        //Wi-Fi checkbox click
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]")).click();

        //Title
        String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle,"WiFi settings");
        //Wi-Fi settings input send keys
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Test pass");

        //Pop modal click button
        driver.findElement(AppiumBy.id("android:id/button1")).click();
    }

}
