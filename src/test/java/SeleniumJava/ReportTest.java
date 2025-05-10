package SeleniumJava;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestListener;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReportTest implements ITestListener {

    private ExtentReports extent;

    @BeforeTest
    public void Config() {
        String path = System.getProperty("user.dir") + "/reports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Report");
        reporter.config().setDocumentTitle("Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        //author name
        extent.setSystemInfo("Tester", "Ravshanov Kamoliddin");
    }

    @Test
    public void SeleniumTest() {
        ExtentTest test = extent.createTest("SeleniumTest");
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        test.info("Navigated to Google");
        test.pass("Title: " + driver.getTitle());
        driver.quit();
        extent.flush();
    }
}