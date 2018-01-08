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
public class AppiumTest {
    
    public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("Run started");
        
        String browserName = "mobileOS";
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
        String host = "demo.perfectomobile.com";
//        capabilities.setCapability("user", "myUser");
//        capabilities.setCapability("password", "myPassword");
        
        capabilities.setCapability("securityToken", " <your securitytoken>");
        
        //TODO: Change your device ID
        capabilities.setCapability("deviceName", "143D71CAAC024D6E111E8E7FF460D4C8F0080E5A");
        
        // Use the automationName capability to define the required framework - Appium (this is the default) or PerfectoMobile.
        capabilities.setCapability("automationName", "XCUITest");
        
        // Call this method if you want the script to share the devices with the Perfecto Lab plugin.
        PerfectoLabUtils.setExecutionIdCapability(capabilities, host);
        
        // Application settings examples.
        // capabilities.setCapability("app", "PRIVATE:applications/Errands.ipa");
        // For Android:
        // capabilities.setCapability("appPackage", "com.google.android.keep");
        // capabilities.setCapability("appActivity", ".activities.BrowseActivity");
        // For iOS:
        capabilities.setCapability("bundleId", "com.perfectomobile.FingerprintTest");
        
        // Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
        //capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);
        
        // Name your script
        // capabilities.setCapability("scriptName", "AppiumTest");
        
        AndroidDriver driver = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        // IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
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
            reportiumClient.testStart("FaceIDTest", new TestContext("tag2", "tag3"));
            
            // write your code here
            
            driver.context("NATIVE_APP");
            driver.findElementByXPath("//*[@label=\"AUTH\"]").click();
 
            Map<String, Object> params = new HashMap<>();
            // use either the "identifier" or "name" parameter to identify the app
            params.put("identifier", "com.perfectomobile.FingerprintTest");
            params.put("resultAuth", "fail");  // may be either "fail" or "success"
            params.put("errorType", "userCancel");  // may be authFailed, userCancel, userFallback, systemCancel, or lockout
            String result = (String)driver.executeScript("mobile:sensorAuthentication:set", params);
           
            System.out.println(result);
            
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
    
}
