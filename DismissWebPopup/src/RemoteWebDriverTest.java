import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.Cookie.Builder;

public class RemoteWebDriverTest {

    public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("Run started");

        String browserName = "mobileOS";
        String uname = "your username";
        String pword = "your pw";
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
        String host = "your cloud";
        capabilities.setCapability("user", uname);
        capabilities.setCapability("password", pword);

        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("browserVersion", "46");
//        capabilities.setCapability("browserName", "Chrome");
//        capabilities.setCapability("browserVersion", "50");
        capabilities.setCapability("resolution", "1366x768");
        capabilities.setCapability("location", "US East");

        // Name your script
        capabilities.setCapability("scriptName", "DismissPopup");

        RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        try {
            // Go to Yahoo Weather
        	driver.get("https://www.yahoo.com/news/weather/");
			// Click buttons to tell app to find your location
        	driver.findElementByXPath("//*[text()='Change location']").click();
        	driver.findElementByXPath("//*[text()='Detect my location']").click();
        	
			// Look for popup - requesting permission to share location.
        	try {
				Map<String, Object> params = new HashMap<>();
				params.put("content", "Share location");
				params.put("index", "1");
				params.put("timeout", 10);
				params.put("screen.top", "0");
				params.put("screen.height", "30%");
				params.put("screen.width", "35%");
				driver.executeScript("mobile:text:select", params);
			} catch (Exception e) {
				System.out.println("No browser popup found, continue with script");
			}
        	

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Retrieve the URL of the Single Test Report, can be saved to your execution summary and used to download the report at a later point
                String reportURL = (String)(driver.getCapabilities().getCapability(WindTunnelUtils.SINGLE_TEST_REPORT_URL_CAPABILITY));

                driver.close();

                // In case you want to download the report or the report attachments, do it here.
                // PerfectoLabUtils.downloadReport(driver, "pdf", "C:\\test\\report");
                // PerfectoLabUtils.downloadAttachment(driver, "video", "C:\\test\\report\\video", "flv");
                // PerfectoLabUtils.downloadAttachment(driver, "image", "C:\\test\\report\\images", "jpg");

            } catch (Exception e) {
                e.printStackTrace();
            }

            driver.quit();
        }

        System.out.println("Run ended");
    }

    private static void switchToContext(RemoteWebDriver driver, String context) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        Map<String,String> params = new HashMap<String,String>();
        params.put("name", context);
        executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
    }

    private static String getCurrentContextHandle(RemoteWebDriver driver) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        String context =  (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
        return context;
    }

    private static List<String> getContextHandles(RemoteWebDriver driver) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        List<String> contexts =  (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
        return contexts;
    }
}
