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
import java.util.concurrent.atomic.AtomicReference;

public class BaseTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @BeforeClass
    public void ConfigureAppium () throws MalformedURLException {

        //Correct Appium path local appium server
       service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:/Users/kamoliddin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();


        UiAutomator2Options options = getUiAutomator2Options();

        //eCommerce apps package and Activity
        //options.setAppPackage("com.androidsample.generalstore");
        //options.setAppActivity("com.androidsample.generalstore.MainActivity");

        //ApiDemos app
        /*
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity(".ApiDemos"); */


        /* Driver url local
        driver = new AndroidDriver( new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  */

        driver=new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        // Driver URL Remote
        System.out.println("Capabilities: " + options.toJson());
        System.out.println("Appium server URL: " + "http://127.0.0.1:4723/wd/hub");

        // Driver URL Remote
         /*String remoteAppiumServerURL = "http://127.0.0.1:4723/wd/hub";
        driver = new AndroidDriver(new URL(remoteAppiumServerURL), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); */

    }

    protected static UiAutomator2Options getUiAutomator2Options() {
        UiAutomator2Options options = new UiAutomator2Options();
        //Real devices
        options.setDeviceName("SKEAONE6PV9LFYJN"); // Qurilma ID

        //chrome driver
        options.setChromedriverExecutable("C:/chromedriver/chrome-win64");

        //emulator
        //options.setDeviceName("Emulator");

        //Demos app
        //options.setApp("src/main/resources/ApiDemos-debug.apk");

        //eCommerce app
        options.setApp("src/main/resources/General-Store.apk");
        options.setPlatformName("Android");
        options.setAutomationName("UIAutomator2");
        return options;
    }

    public void LongPressAction(WebElement ele) {

        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableBiMap.of("elementId",((RemoteWebElement)ele).getId(),
                        "duration",2000));
    }

    public void ScrollEndAction() {

        boolean canScrollMore;
        do {
            //noinspection DataFlowIssue
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                    ImmutableMap.<String, Object>builder()
                            .put("left", 100)
                            .put("top", 100)
                            .put("width", 200)
                            .put("height", 200)
                            .put("direction", "down")
                            .put("percent", 3.0)
                            .build()
            );
        } while (canScrollMore);
    }

    public void swipeAction(WebElement ele, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "direction", "left",
                "percent", 0.75
        ));
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
