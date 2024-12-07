import base.BaseTest;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;

public class SwipeTest extends BaseTest {


    @Test
    public void SwipeDemoTest() throws MalformedURLException, InterruptedException {
        // Navigate to Views > Gallery > Photos
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='1. Photos']")).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstImage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//android.widget.ImageView)[1]")));

        // Assert focusable attribute before swipe
        //noinspection deprecation
        Assert.assertEquals(firstImage.getAttribute("focusable"), "true");

        //extends Base test
        swipeAction(firstImage,"left");

        // Assert focusable attribute after swipe
        //noinspection deprecation
        Assert.assertEquals(firstImage.getAttribute("focusable"), "false");
    }
}
