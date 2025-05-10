package base;


import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class IOSBaseTest {

    public IOSDriver driver;
    public AppiumDriverLocalService service;

    public void ConfigureAppium () throws MalformedURLException {

        //Correct Appium path local appium server
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:/Users/kamoliddin/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        XCUITestOptions options = new XCUITestOptions();

        options.setDeviceName("iphone 13 pro");
        options.setApp("");
        options.setPlatformVersion("15.5");
        options.setWdaLaunchTimeout(Duration.ofSeconds(20));

        driver = new IOSDriver(new URL("https://127.0.0.1:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown () {
        driver.quit();
        service.stop();
    }
}
