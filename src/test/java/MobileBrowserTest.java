import base.BrowserBaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MobileBrowserTest  extends BrowserBaseTest {

    @Test
    public void browserTest() throws InterruptedException {

        driver.get("https://github.com/RavshanovKamoliddin");
//        System.out.println(driver.getTitle());
//        driver.findElement(By.name("q")).sendKeys("ravshanovkamoliddin");
//        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.findElement(By.xpath("//span[normalize-space()='Appium_Android_IOS_Test']")).click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)","");
        Thread.sleep(5000);
        String text = driver.findElement(By.xpath("//p[contains(text(),'Contact')]")).getText();
        Assert.assertEquals(text,"\uD83D\uDCE7 Contact For any questions or suggestions, feel free to reach out: \uD83D\uDCE9 https://www.linkedin.com/in/ravshanovkamoliddin");


    }

}
