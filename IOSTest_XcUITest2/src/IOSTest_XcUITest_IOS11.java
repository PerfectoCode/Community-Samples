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

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;

/**
 * This template is for users that use DigitalZoom Reporting (ReportiumClient).
 * For any other use cases please see the basic template at https://github.com/PerfectoCode/Templates.
 * For more programming samples and updated templates refer to the Perfecto Documentation at: http://developers.perfectomobile.com/
 */
public class IOSTest_XcUITest_IOS11 {
    
	public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("Run started");
        
        String browserName = "mobileOS";
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
        String host = "CLOUDADDRESS";
        capabilities.setCapability("user", "USERNAME");
        capabilities.setCapability("password", "PASSWORD");
        
        //TODO: Change your device ID
        //capabilities.setCapability("deviceName", "AA7EEEAADD92242C665D2807B538BDACFAA5A0DB");
        capabilities.setCapability("deviceName", "F41FB514D76C4374B983F4285C7C1BE60DA12E36");
        
        
        // Use the automationName capability to define the required framework - Appium (this is the default) or PerfectoMobile.
         //capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("automationName", "XCUITest");
        
        // Call this method if you want the script to share the devices with the Perfecto Lab plugin.
        PerfectoLabUtils.setExecutionIdCapability(capabilities, host);
        
        // Application settings examples.
        // capabilities.setCapability("app", "PRIVATE:applications/Errands.ipa");
        // For Android:
        // capabilities.setCapability("appPackage", "com.google.android.keep");
        // capabilities.setCapability("appActivity", ".activities.BrowseActivity");
        // For iOS:
        capabilities.setCapability("bundleId", "com.mttnow.iphone.etihad");
        //capabilities.setCapability("bundleId", "com.marriott.iphoneprod");
        
        
        
        // Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
        //capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);
        
        // Name your script
        // capabilities.setCapability("scriptName", "AppiumTest");
        
        //AndroidDriver driver = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        
        // Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
        PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
        .withProject(new Project("My Project", "1.0"))
        .withJob(new Job("My Job", 45))
        .withContextTags("tag1")
        .withWebDriver(driver)
        .build();
        ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
        
        try {
            reportiumClient.testStart("My test name", new TestContext("tag2", "tag3"));
            
            // write your code here
            
            try {
				stopApp("Etihad Airways", driver);
			} catch (Exception e) {}

            //startApp("Etihad Airways", driver);
            
            driver.launchApp();
            
//            driver.findElementByXPath("//XCUIElementTypeStaticText[@value=\"Hotel\"]").click();
//            
//            driver.findElementByXPath("//XCUIElementTypeStaticText[@value=\"Deal\"]").click();
//            
            driver.findElementByXPath("//XCUIElementTypeButton[@label=\"Book\"]").click();
            
            driver.findElementByXPath("//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeButton[1]").click();
            
            Thread.sleep(2000);
            
            ClickText("A Coruna", driver);
            
            Thread.sleep(2000);

            
            
            ClickText("Arrival airport", driver);
            
            Thread.sleep(2000);
            
            ClickText("Abu Dhabhi", driver);
            
            driver.findElementByXPath("//*[@label=\"CHOOSE DATES\"]").click();
            
            //scrolltoXPath(driver, "//UIATableCell[@label=\"September 2017\"]/following-sibling::*//UIAButton[@enabled='true']");
            
            // reportiumClient.testStep("step1"); // this is a logical step for reporting
            // add commands...
            // reportiumClient.testStep("step2");
            // more commands...
            
            reportiumClient.testStop(TestResultFactory.createSuccess());
        } catch (Exception e) {
            reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
            e.printStackTrace();
        } finally {
            try {
                driver.quit();
                
                // Retrieve the URL to the DigitalZoom Report (= Reportium Application) for an aggregated view over the execution
                String reportURL = reportiumClient.getReportUrl();
                
                // Retrieve the URL to the Execution Summary PDF Report
                String reportPdfUrl = (String)(driver.getCapabilities().getCapability("reportPdfUrl"));
                // For detailed documentation on how to export the Execution Summary PDF Report, the Single Test report and other attachments such as
                // video, images, device logs, vitals and network files - see http://developers.perfectomobile.com/display/PD/Exporting+the+Reports
                
            } catch (Exception e) {
                e.printStackTrace();
            }
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
    
	public static void startApp(String appName, AppiumDriver<WebElement> d) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", appName);
		d.executeScript("mobile:application:open", params);
	}

	public static void stopApp(String appName, AppiumDriver<WebElement> d) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", appName);
		d.executeScript("mobile:application:close", params);
	}
	
	public static void ClickText(String content, AppiumDriver driver){
		
        Map<String, Object> params1 = new HashMap<>();
        params1.put("label", content);
        params1.put("timeout", "20");
        params1.put("words", "words");
        params1.put("ignorecase", "nocase");
        Object result1 = driver.executeScript("mobile:button-text:click", params1);
        
	}
	

		public static void scrolltoXPath(AppiumDriver<WebElement> driver,String xPath) {
			do {
				try {
					driver.findElement(By.xpath(xPath)).click();
					break;

				} catch (Exception NoSuchElementException) {
					Dimension dimensions = driver.manage().window().getSize();
					Double screenHeightStart = dimensions.getHeight() * 0.5;
					int scrollStart = screenHeightStart.intValue();
					Double screenHeightEnd = dimensions.getHeight() * 0.2;
					int scrollEnd = screenHeightEnd.intValue();
					//driver.swipe(0, scrollStart, 0, scrollEnd, 2000);
				}
			} while (true);
		}

}
