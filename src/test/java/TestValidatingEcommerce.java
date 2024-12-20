import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

public class TestValidatingEcommerce extends BaseTest {


    @Test
    public void FillFrom() throws MalformedURLException, InterruptedException {


        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Kamoliddin");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text= 'Female']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text= 'Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        // locator find example1
        driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart'])[1]")).click();
        driver.findElement(By.xpath("(//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart'])[2]")).click();

        // locator click add to cart example2
//        driver.findElements(By.xpath("(//android.widget.TextView[@text='com.androidsample.generalstore:id/productAddCart'])[1]")).get(0).click();
//        driver.findElements(By.xpath("(//android.widget.TextView[@text='com.androidsample.generalstore:id/productAddCart'])[2]")).get(0).click();

        // locator click add to cart example3
//        driver.findElements(By.xpath("//android.widget.TextView[@text='ADDED TO CART']")).get(0).click();
//        driver.findElements(By.xpath("//android.widget.TextView[@text='ADDED TO CART']")).get(0).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();



        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text", "Cart"));
        List<WebElement> productPrice = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));

        int count = productPrice.size();
        double totalsum = 0;

        for (int i = 0; i < count; i ++) {
            String amountSting = productPrice.get(i).getText();
            // $160.97,$120.0
            double price = Double.parseDouble(amountSting.substring(1));
            totalsum = totalsum + price; // $ 160.97+120.0=280.97
        }

        String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Double displayFormattedSum = getFormattedAmount(displaySum);
        Assert.assertEquals(totalsum,displaySum);

    }
}
