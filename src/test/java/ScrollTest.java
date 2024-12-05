import base.BaseTest;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class ScrollTest extends BaseTest {


    @Test
    public void ScrollDemoTest() throws MalformedURLException, InterruptedException {

        //Views click
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        //where to scroll
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));

        //NO prior idea
        ScrollEndAction();

        /*boolean canScrollMore;
        do
        {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 1.0
            ));

        }while (canScrollMore);

        Thread.sleep(200); */

    }
}
