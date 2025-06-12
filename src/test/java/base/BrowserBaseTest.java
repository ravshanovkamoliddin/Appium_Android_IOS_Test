package base;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class BrowserBaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void ConfigureAppium () throws MalformedURLException {

       // Driver URL Remote
//        System.out.println("Capabilities: " + options.toJson());
//        System.out.println("Appium server URL: " + "http://127.0.0.1:4723/wd/hub");


        UiAutomator2Options options = new UiAutomator2Options();

        options.setCapability("platformName", "Android");
        options.setCapability("deviceName", "SKEAONE6PV9LFYJN");
        options.setCapability("automationName", "UIAutomator2");
        options.setCapability("browserName", "Chrome");
        options.setChromedriverExecutable("src/main/resources/chromedriver.exe");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    public Double getFormattedAmount(String amount) {
        Double productPrices = Double.parseDouble(amount.substring(1));
        return productPrices;
    }

    @AfterClass
    public void tearDown () {
        driver.quit();
        //service.stop();
    }
}
