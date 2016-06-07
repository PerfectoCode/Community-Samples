import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.Cookie.Builder;

import io.appium.java_client.*;
import io.appium.java_client.android.*;
import io.appium.java_client.ios.*;

public class AppiumTest {

    public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("Run started");

        String browserName = "mobileOS";
        DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);
        String host = "your cloud";
        capabilities.setCapability("user", "your cloud username");
        capabilities.setCapability("password", "your cloud password");

        //TODO: Change your device ID
        capabilities.setCapability("deviceName", "DeviceID");

        // Use the automationName capability to define the required framework - Appium (this is the default) or PerfectoMobile.
        capabilities.setCapability("automationName", "Appium");

        // Call this method if you want the script to share the devices with the Perfecto Lab plugin.
        PerfectoLabUtils.setExecutionIdCapability(capabilities, host);

        // Application settings examples.
        capabilities.setCapability("app", "PUBLIC:appium/MultipleWebViews.ipa");
		capabilities.setCapability("autoInstrument", true);
        // For Android:
        // capabilities.setCapability("appPackage", "com.google.android.keep");
        // capabilities.setCapability("appActivity", ".activities.BrowseActivity");
        // For iOS:
        capabilities.setCapability("bundleId", "42S4GZUXGS.MultipleWebViews");

        // Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
        //capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);

        // Name your script
        capabilities.setCapability("scriptName", "Testing getCurrentUrl");

        //AndroidDriver driver = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        By topWindow = By.xpath("//*[@label='address1']");
        By btmWindow = By.xpath("//*[@label='address2']");
        By goBtn1 = By.xpath("//*[@label='button_navigate1']");
        By goBtn2 = By.xpath("//*[@label='button_navigate2']");
        
        try {
            // write your code here
        	// send top window to espn
        	driver.context("NATIVE_APP");
        	driver.findElement(topWindow).sendKeys("www.espn.com");
        	driver.findElement(goBtn1).click();
        	
        	// send bottom window to cbsSports
        	driver.findElement(btmWindow).sendKeys("www.cbssports.com");
        	driver.findElement(goBtn2).click();
        	

        	// set driver context to top webview and retrieve URL
        	driver.context("WEBVIEW_1");
        	System.out.println(driver.getCurrentUrl());
        	
        	driver.context("WEBVIEW_2");
        	System.out.println(driver.getCurrentUrl()); 
        	
        	driver.context("WEBVIEW");
        	System.out.println(driver.getCurrentUrl()); 

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Retrieve the URL of the Single Test Report, can be saved to your execution summary and used to download the report at a later point
                String reportURL = (String)(driver.getCapabilities().getCapability(WindTunnelUtils.SINGLE_TEST_REPORT_URL_CAPABILITY));

                driver.close();

                // In case you want to download the report or the report attachments, do it here.
                PerfectoLabUtils.downloadReport(driver, "pdf", "C:\\test\\reportGetUrl");
                // PerfectoLabUtils.downloadAttachment(driver, "video", "C:\\test\\report\\video", "flv");
                // PerfectoLabUtils.downloadAttachment(driver, "image", "C:\\test\\report\\images", "jpg");

            } catch (Exception e) {
                e.printStackTrace();
            }

            driver.quit();
        }

        System.out.println("Run ended");
    }
}
