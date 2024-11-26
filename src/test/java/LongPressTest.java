import base.BaseTest;
import com.google.common.collect.ImmutableBiMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LongPressTest extends BaseTest {


    @Test
    public void LoongPressGesture () throws MalformedURLException, InterruptedException {

        //Views click
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();

        //
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

        WebElement ele = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableBiMap.of("elementId",((RemoteWebElement)ele).getId(),
                        "duration",2000));

        Thread.sleep(200);
    }
}
