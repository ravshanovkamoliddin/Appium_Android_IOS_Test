
import base.BaseTest;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class MiscellaneousAppiumTest extends BaseTest {

    @Test
    public void Miscellaneous() throws MalformedURLException {


        //adb shell dumpsys window | grep "mCurrentFocus" -Mac
        //adb shell dumpsys window | findstr mCurrentFocus -Windows

        //App Package & Activity
        Activity activity = new Activity("io.appium.android.apis","io.appium.android.apis.preference.PreferenceDependencies");

        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent","io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));
        //driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        //driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();

        //Wi-Fi checkbox click
        driver.findElement(AppiumBy.id("android:id/checkbox")).click();

        DeviceRotation  landSpace = new DeviceRotation(0,0,90);
        driver.rotate(landSpace);

        driver.findElement(AppiumBy.xpath("//android.widget.ListView[@resource-id=\"android:id/list\"]/android.widget.LinearLayout[2]")).click();

        //Title
        String alertTitle = driver.findElement(AppiumBy.id("android:id/alertTitle")).getText();
        Assert.assertEquals(alertTitle,"WiFi settings");
        //Wi-Fi settings input send keys
        driver.findElement(AppiumBy.id("android:id/edit")).sendKeys("Kamoliddin Wifi");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        //Pop modal click button
        driver.findElement(AppiumBy.id("android:id/button1")).click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

}
