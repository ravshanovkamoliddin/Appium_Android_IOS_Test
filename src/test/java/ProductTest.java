import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;

public class ProductTest extends BaseTest {


    @Test
    public void FillFrom() throws MalformedURLException, InterruptedException {


        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Kamoliddin");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text= 'Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text= 'Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"LeBron Soldier 12 \"));"));

        int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for (int i = 0; i < productCount;i++) {
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (productName.equalsIgnoreCase("LeBron Soldier 12 "))
            {
              //  driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).click();
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }

        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        //Testcase-Dynamically selecting Product by scanning list based on text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text", "Cart"));

        String lastProduct = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(lastProduct,"LeBron Soldier 12 ");

        Thread.sleep(3000);

    }
}
