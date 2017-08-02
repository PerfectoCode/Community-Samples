import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.Cookie.Builder;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.*;
import io.appium.java_client.android.*;
import io.appium.java_client.ios.*;
import pages.*;

import com.perfectomobile.selenium.util.EclipseConnector;

public class AppiumTest {

    public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("Run started");

        String browserName = "mobileOS";
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
        String host = <YOUR CLOUD>;
        String user = <USERNAME>;
        String pw = <PASSWORD>;
        
        // upload the application file to the Repository
		PerfectoLabUtils.uploadMedia(host, user, pw,
                "C:\\test\\applications\\com.voyagesoftech.myexpensemanager.apk",
                "PRIVATE:applications/com.voyagesoftech.myexpensemanager.apk");
        
        capabilities.setCapability("user", user);
        capabilities.setCapability("password", pw);
        capabilities.setCapability("deviceName", <DEVICE ID>);
        capabilities.setCapability("automationName", "Appium");

        // Call this method if you want the script to share the devices with the Perfecto Lab plugin.
        setExecutionIdCapability(capabilities, host);

        // Application installation capabilities.
        capabilities.setCapability("app", "PRIVATE:applications/com.voyagesoftech.myexpensemanager.apk");
        capabilities.setCapability("autoInstrument", true);
        capabilities.setCapability("fullReset", true);
        // For Android:
        capabilities.setCapability("appPackage", "com.voyagesoftech.myexpensemanager");
        // capabilities.setCapability("appActivity", ".activities.BrowseActivity");
        // For iOS:
        // capabilities.setCapability("bundleId", "com.yoctoville.errands");

        AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        // IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        
        try {
            // write your code here
        	Map<String, Object> params = new HashMap<>();
        	boolean ExpClick = true;
        	MainPg mainPg = new MainPg(driver);

    		// click the Expense button to enter a new expense
    		addExpense currPg = mainPg.addExp();
    		
        	// open the Expense category menu
    		currPg.addCategory("Miscellaneous");

    		currPg.addSubCat("Office");

    		currPg.addExp("Notebooks", "3.75");

    		ExpClick = currPg.saveExp();
        	if (ExpClick) {
        		System.out.println("Expense updated to ledger");
        	} else {
        		System.out.println("Expense not updated!");
        	}

        	// use the image identification to click the home page button
        	params.clear();
        	params.put("content", "PRIVATE:Home-cropped.png");
        	params.put("threshold", 80);
        	params.put("imageBounds.needleBound", 30);
        	driver.executeScript("mobile:image:select", params);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                driver.close();

                // In case you want to down the report or the report attachments, do it here.
                PerfectoLabUtils.downloadReport(driver, "pdf", "C:\\test\\reportAppHyb");
                // PerfectoLabUtils.downloadAttachment(driver, "video", "C:\\test\\report\\video", "flv");
                // PerfectoLabUtils.downloadAttachment(driver, "image", "C:\\test\\report\\images", "jpg");
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

    private static void setExecutionIdCapability(DesiredCapabilities capabilities, String host) throws IOException {
        EclipseConnector connector = new EclipseConnector();
        String eclipseHost = connector.getHost();
        if ((eclipseHost == null) || (eclipseHost.equalsIgnoreCase(host))) {
            String executionId = connector.getExecutionId();
            capabilities.setCapability(EclipseConnector.ECLIPSE_EXECUTION_ID, executionId);
        }
    }
    
    private static boolean checkAdvert(AppiumDriver driver, int timeout) {
        By adTree = By.xpath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]");
    	Map<String, Object> adParams = new HashMap<>();
		//trying to wait for the ad to come up and then click the Expense button
		
    	driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.context("NATIVE_APP");       	
    	FluentWait<WebDriver> await = new FluentWait<WebDriver> (driver)
				.withTimeout(timeout, TimeUnit.SECONDS)
				.pollingEvery(500, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		try {
			await.until(ExpectedConditions.visibilityOf(driver.findElement(adTree)));
    		// go BACK and try again
    		adParams.clear();
    		adParams.put("keySequence", "BACK");
    		driver.executeScript("mobile:presskey", adParams);
    		System.out.println("Press the back button to get out of ad");
    		return true;
		} catch (Exception t) {
			System.out.println("no ad showed up");
			return false;
		}
    	
    }
}
