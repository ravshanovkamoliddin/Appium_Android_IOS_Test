
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;

    @Test
    public void AppiumAndroidTest() throws MalformedURLException {

        // Correct Appium path
        AppiumDriverLocalService service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:/Users/kamoliddin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        //SKEAONE6PV9LFYJN
        // Options for the driver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("SKEAONE6PV9LFYJN"); // Qurilma ID
        options.setApp("src/main/resources/ApiDemos-debug.apk");
        options.setPlatformName("Android");
        options.setAutomationName("UIAutomator2");


        // Driver url
        driver = new AndroidDriver( new URL ("http://127.0.0.1:4723"), options);

        // Quit the driver
        driver.quit();

        // Stop the service
        service.stop();
    }
}
