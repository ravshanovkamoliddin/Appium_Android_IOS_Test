import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LongPressTest extends BaseTest {


    @Test
    public void LoongPressGesture () throws MalformedURLException, InterruptedException {

        //Views click
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();

        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
        //WebElement ele
        WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
        LongPressAction(ele);

       /* ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableBiMap.of("elementId",((RemoteWebElement)ele).getId(),
                        "duration",2000)); */

        String menuText = driver.findElement(By.id("android:id/title")).getText();
        Assert.assertEquals(menuText,"Sample menu");
        Assert.assertTrue(driver.findElement(By.id("android:id/title")).isDisplayed());
        Thread.sleep(200);
    }
}
