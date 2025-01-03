import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.Set;


public class TestValidatingEcommerce extends BaseTest {


    @Test
    public void FillFrom() throws MalformedURLException, InterruptedException {

        // Input name and select gender
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Kamoliddin");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();

        // Select country
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        // Add items to cart
        List<WebElement> addToCartButtons = driver.findElements(By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productAddCart']"));
        for (int i = 0; i < 2; i++) {
            addToCartButtons.get(i).click();
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        // Wait for the cart page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("com.androidsample.generalstore:id/toolbar_title"), "Cart"));

        // Calculate total sum
        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        double totalSum = productPrices.stream()
                .mapToDouble(e -> Double.parseDouble(e.getText().substring(1)))
                .sum();

        // Validate total amount
        String displayedTotal = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Assert.assertEquals(totalSum, getFormattedAmount(displayedTotal), "Total amount mismatch");

        // Agree to terms and proceed
        LongPressAction(driver.findElement(By.id("com.androidsample.generalstore:id/termsButton")));
        driver.findElement(By.id("android:id/button1")).click();
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

        // Print console Total sum
        System.out.println("Calculated Total: " + totalSum);
        System.out.println("Displayed Total: " + displayedTotal);
        Thread.sleep(6000);

//        Set<String> contexts = driver.getContextHandles();
//        for (String contextName : contexts)
//        {
//            System.out.println(contextName);
//        }
//
//        driver.context("WEBVIEW_com.androidsample.generalstore");
//        driver.findElement(By.name("q")).sendKeys("ravshanovkamoliddin");
//        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
//        driver.pressKey(new KeyEvent(AndroidKey.BACK));
//        driver.context("NATIVE_APP");

        Set<String> contexts = driver.getContextHandles();
        for (String contextName : contexts) {
            System.out.println("Available context: " + contextName);
        }

        if (contexts.contains("WEBVIEW_com.androidsample.generalstore")) {
            driver.context("WEBVIEW_com.androidsample.generalstore");
            driver.findElement(By.name("q")).sendKeys("ravshanovkamoliddin");
            driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
            driver.context("NATIVE_APP");
        } else {
            System.out.println("WEBVIEW context not found. Please check WebView configuration.");
        }


    }

}
