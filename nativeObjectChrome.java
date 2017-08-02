import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.Cookie.Builder;
import io.appium.java_client.*;
import io.appium.java_client.android.*;
import io.appium.java_client.ios.*;
import com.perfectomobile.selenium.util.EclipseConnector;
public class AppiumTest {
    public static void main(String[] args) throws MalformedURLException, 
IOException {
        System.out.println("Run started");
        String browserName = "mobileOS";
        DesiredCapabilities capabilities = new 
DesiredCapabilities(browserName, "", Platform.ANY);
        String host = "myPerfectoLab.perfectomobile.com";        
        capabilities.setCapability("user", "myUsername");
        capabilities.setCapability("password", "myPassword");
        capabilities.setCapability("deviceName", "myDeviceID");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("automationName", "Appium");
        // Call this method if you want the script to share the devices 
with the Perfecto Lab plugin.
        setExecutionIdCapability(capabilities, host);
        AndroidDriver driver = new AndroidDriver(new URL("https://" + 
host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        // IOSDriver driver = new IOSDriver(new URL("https://" + host + 
"/nexperience/perfectomobile/wd/hub"), capabilities);
        String Address1="http://auburn.craigslist.org/";
        String Address2="http://www.google.com";
        try {
             Navigation _navigation = driver.navigate();
            _navigation.to(Address1);
            _navigation.refresh();
            driver.hideKeyboard();               
            driver.context("NATIVE_APP");
            
driver.findElement(By.xpath("//*[@resource-id=\"com.android.chrome:id/url_bar\"]")).sendKeys(Address2);
            driver.pressKeyCode(66);
            driver.context("WEBVIEW_1");
            
driver.findElement(By.xpath("//*[@id=\"lst-ib\"]")).sendKeys("ebay 
batman");
            driver.pressKeyCode(66);
            driver.context("NATIVE_APP");
            // add to bookmarks
            
driver.findElement(By.xpath("//*[contains(@resource-id,\"menu_button\")]")).click();            
            
driver.findElement(By.xpath("//*[contains(@resource-id,\"two\")]")).click();
            // show bookmarks.
            
driver.findElement(By.xpath("//*[contains(@resource-id,\"menu_button\")]")).click();
            
driver.findElement(By.xpath("//*[contains(@content-desc,\"Book\")]")).click();
            Thread.sleep(1000);
            
driver.findElement(By.xpath("//*[contains(@resource-id,\"close\")]")).click();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                driver.close();
                // In case you want to down the report or the report 
attachments, do it here.
                // RemoteWebDriverUtils.downloadReport(driver, "pdf", 
"C:\\test\\report");
                // RemoteWebDriverUtils.downloadAttachment(driver, 
"video", "C:\\test\\report\\video", "flv");
                // RemoteWebDriverUtils.downloadAttachment(driver, 
"image", "C:\\test\\report\\images", "jpg");
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.quit();
        }
        System.out.println("Run ended");
    }
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
        }
    }
     private static void setExecutionIdCapability(DesiredCapabilities 
capabilities, String host) throws IOException {
        EclipseConnector connector = new EclipseConnector();
        String eclipseHost = connector.getHost();
        if ((eclipseHost == null) || 
(eclipseHost.equalsIgnoreCase(host))) {
            String executionId = connector.getExecutionId();
            
capabilities.setCapability(EclipseConnector.ECLIPSE_EXECUTION_ID, 
executionId);
        }
    }
}
