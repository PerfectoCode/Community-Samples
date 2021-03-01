import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;



/**
 * This template is for users that use DigitalZoom Reporting (ReportiumClient).
 * For any other use cases please see the basic template at https://github.com/PerfectoCode/Templates.
 * For more programming samples and updated templates refer to the Perfecto Documentation at: http://developers.perfectomobile.com/
 */
public class EtihadPerformanceWeb {
	
    private static RemoteWebDriver driver;

	public static void main(String[] args) throws MalformedURLException, IOException {
		
        System.out.println("Run started");        
        String platformName = "Android";        
        String browserName = "mobileOS";
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
        String host = "partners.perfectomobile.com";
        
        capabilities.setCapability("takesScreenshot", true);
        capabilities.setCapability("securityToken", <EnterTokenString>);
         
        // Call this method if you want the script to share the devices with the Perfecto Lab plugin.
        //PerfectoLabUtils.setExecutionIdCapability(capabilities, host);
        
        capabilities.setCapability("useAppiumForWeb", "true");
        //capabilities.setCapability("enableAppiumBehavior", "true");
        capabilities.setCapability("deviceName", "932AY05WL7");
        // capabilities.setCapability("deviceName", "F39C49EB5D722D6DBD14CC4366FF6E44084EC96D");
         	
        // Use the automationName capability to define the required framework - Appium (this is the default) or PerfectoMobile.
        //capabilities.setCapability("automationName", "Appium");

        driver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
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
            reportiumClient.testStart("PerfectouserExperience", new TestContext("tag2", "tag3"));
             
            //Set End User conditions
            //Throttle Network and Capture Traffic - Har capture only for Android since Etihad IOS app expects trusted certifcate
            if(platformName.equalsIgnoreCase("Android")) {
	            Map<String, Object> pars = new HashMap<>();
	            pars.put("profile", "4g_lte_good");
	            pars.put("generateHarFile", "true");
	            driver.executeScript("mobile:vnetwork:start", pars); 
            }
            else {
	            Map<String, Object> pars = new HashMap<>();
	            pars.put("profile", "4g_lte_good");
	            driver.executeScript("mobile:vnetwork:start", pars); 
            }
            
            //Set location
			Map<String, Object> params = new HashMap<>();
			params.put("address", "Bangalore, India");
			driver.executeScript("mobile:location:set", params); 
            
			//Run background application
			String backGroundApps = "YouTube,Messages,Maps,Calculator,Calendar";
			 String[] bApps = backGroundApps.split(",");
			 for(String i: bApps) {
			    try {
					Map<String, Object> params1 = new HashMap<>();
					params1.put("name", i);
					driver.executeScript("mobile:application:open", params1);
				} catch (Exception e) {}		
			 }

			 //Device Vitals
			 Map<String, Object> params2 = new HashMap<>();
			 params2.put("sources", "Device");
			 driver.executeScript("mobile:monitor:start", params2); 

            //Launch Web application
            if(platformName.equalsIgnoreCase("iOS")) {
            	clearHistoryiOS(driver);
            	StartApp(driver, "com.apple.mobilesafari");
            	switchToContext(driver, "WEBVIEW");
                driver.get("http://expensetracker.perfectomobile.com/");
                Thread.sleep(2000);
            	switchToContext(driver, "NATIVE_APP");
            	driver.findElementByXPath("//XCUIElementTypeOther[@label=\"Address\"]").click();
            	driver.findElementByXPath("//*[@label=\"Address\"]").sendKeys("https://www.etihad.com/en-us/book");
            	driver.findElementByXPath("//*[@label=\"Go\"]").click();
            	switchToContext(driver, "WEBVIEW");
            }
            else {
            	driver.get("http://expensetracker.perfectomobile.com/");
            	Thread.sleep(2000);
            	driver.get("https://www.etihad.com/en-us/book");
            }
            
            
            TextValidation(driver, "one Way");
            
            // Wind Tunnel: Measure UX timer 1 - Able to retrieve UX Timer value
            long AppLaunchTime = timerGet(driver, "ux");
            System.out.println("'Measured UX time for App launch time is: " + AppLaunchTime);

            // Wind Tunnel: Add timer to Wind Tunnel Report
            reportTimer(driver, AppLaunchTime, 10000, "Checkpoint load time of App launch.", "AppLaunchTime");
         
            Thread.sleep(2000);
            

            
            driver.findElementById("oneWay").click();
            
            //driver.findElementById("oneWayDestination").sendKeys("Abu Dhabi");
            
            driver.findElementById("oneWayOrigin").click();
            
            javaScriptSendkeys(driver,driver.findElementById("oneWayOrigin"), "BLR");
            
            //driver.findElementById("oneWayOrigin").sendKeys("BLR");
            
            driver.findElementByXPath("//*[@id=\"rbt-menu-item-0\"]/a/div/div[1]/span[1]").click();
            
            driver.findElementById("oneWayDestination").sendKeys("Abu Dhabi");
            
            driver.findElementById("oneWayDestination").click();
            
            javaScriptSendkeys(driver,driver.findElementById("oneWayDestination"), "Abu Dhabi");
            
            driver.findElementByXPath("//*[@id=\"rbt-menu-item-0\"]/a/div/div[1]/span[1]").click();
            
            driver.findElementById("oneWayCalendarDepartDate").click();           
            
            driver.findElementByXPath("(//table[@class=\"CalendarMonth_table CalendarMonth_table_1\"])[4]/tbody/tr[2]/td[4]").click();

            
            driver.findElementByXPath("//*[@id=\"flightsearch\"]/div/form/div[4]/div[2]/div/button").click();
            
   
	           TextValidation(driver, "currency");
	           
	            // Wind Tunnel: Measure UX timer 1 - Able to retrieve UX Timer value
	           long SearchFlight = timerGet(driver, "ux");
	            System.out.println("'Measured UX time for Search Flight time is: " + SearchFlight);
	            
	            // Wind Tunnel: Add timer to Wind Tunnel Report
	            reportTimer(driver, SearchFlight, 1000, "Checkpoint load time of App launch.", "SearchFlight");
	            
	            //stop Network Virtualization
		        Map<String, Object> pars1 = new HashMap<>();
		        driver.executeScript("mobile:vnetwork:stop", pars1); 

		        //stop vitals
				Map<String, Object> params3 = new HashMap<>();
				driver.executeScript("mobile:monitor:stop", params3); 
				

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
    
    private  static void StartApp(RemoteWebDriver driver, String appName) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("identifier", appName);
    	driver.executeScript("mobile:application:open", params);
    }
    
    private  static void CloseApp(RemoteWebDriver driver, String appName) {
    	Map<String, Object> params = new HashMap<>();
    	params.put("identifier", appName);
    	driver.executeScript("mobile:application:close", params);
    }
    
  	// Wind Tunnel: Gets the user experience (UX) timer
    private static long timerGet(RemoteWebDriver driver, String timerType) {
         String command = "mobile:timer:info";
         Map<String,String> params = new HashMap<String,String>();
         params.put("type", timerType);
         params.put("timerId", "myTime");
         long result = (long)driver.executeScript(command, params);
             return result;
    }
    
    
    private static void TextValidation(RemoteWebDriver driver, String content) {	
        // verify that the correct page is displayed as result of signing in.
        Map<String, Object> params1 = new HashMap<>();
        // Check for the text that indicates that the sign in was successful
        params1.put("content", content);
        // allow up-to 30 seconds for the page to display
        params1.put("timeout", "40");
        // Wind Tunnel: Adding accurate timers to text checkpoint command
        params1.put("measurement", "accurate");
        params1.put("source", "camera");
        params1.put("analysis", "automatic");
        params1.put("threshold", "90");
        params1.put("index", "1");
       String resultString = (String) driver.executeScript("mobile:checkpoint:text", params1);
    }
    
   private static void StopWatch(StopWatch stopwatch) {
	   
      // Report it to the Perfecto Execution Report
        Map<String, Object> params = new HashMap<>();

		params.put("name", "stopwatch timer");
	//	params.put("result", numberAsString);
		driver.executeScript("mobile:status:timer", params);
   }
   

	public static void scrollToText(AppiumDriver driver, String text) {
			
	    	for(int i = 1; i<=3; i++) {
	    		
	    		if(OCRTextValidation(driver, "find", text, "4").contains("true"))
					break;
	    		
				Map<String, Object> params = new HashMap<>();
		    	params.put("start", "80%,50%");
		    	params.put("end", "20%,50%");
		    	params.put("duration", "1");
		    	Object res = driver.executeScript("mobile:touch:swipe", params);
		    	
	    	}
		}
	
	public static String OCRTextValidation(AppiumDriver driver, String operation, String content, String timeout){
		
		String result = null;
		
		Map<String, Object> params1 = new HashMap<>();
		params1.put("timeout", timeout);
		params1.put("threshold", "80");
		params1.put("words", "words");
        params1.put("measurement", "accurate");
        params1.put("source", "camera");
        params1.put("analysis", "automatic");	
		List<String> genericOptions1 = new ArrayList<>();
		genericOptions1.add("natural-language=true");
		params1.put("ocr", genericOptions1);
		
		if(operation.contentEquals("find")){
			params1.put("content", content);
			result = (String) driver.executeScript("mobile:checkpoint:text", params1);
		}
		else if (operation.contentEquals("click")){
			params1.put("label", content);
			result = (String) driver.executeScript("mobile:button-text:click", params1);
		}
		
		return result;
	}
	
	public static void pressHomeKey() {
		Map<String, Object> params = new HashMap<>();
        params.put("keySequence", "HOME");
        driver.executeScript("mobile:presskey", params);
	}
	

public static void javaScriptSendkeys(RemoteWebDriver driver, WebElement webElementTextBox, String text) {
        String jsScript = "let input = arguments[0]; \r\n" + "let lastValue = input.value;\r\n"
                + "input.value = arguments[1];\r\n" + "let event = new Event('input', { bubbles: true });\r\n"
                + "event.simulated = true;\r\n"
                + "let tracker = input._valueTracker;\r\n" + "if (tracker) {\r\n" + "  tracker.setValue(lastValue);\r\n"
                + "}\r\n" + "input.dispatchEvent(event);";
        driver.executeScript(jsScript, webElementTextBox, text);
    }

public static void clearHistoryiOS(RemoteWebDriver driver) throws InterruptedException {
		
		//Use this for iOS version 11 and above
		WebDriverWait wait = new WebDriverWait(driver, 30);
		switchToContext(driver, "NATIVE_APP");
		try{
//			Map<String, Object> params = new HashMap<>();
//			params.put("identifier", "com.apple.Preferences");
//			driver.executeScript("mobile:application:close", params);
			CloseApp(driver, "com.apple.Preferences");
		}catch(Exception e){}
		Thread.sleep(1500);
		Map<String, Object> params = new HashMap<>();
//		params.put("identifier", "com.apple.Preferences");
//		driver.executeScript("mobile:application:open", params);
		StartApp(driver, "com.apple.Preferences");
		
		//System.out.println("el present" + driver.findElementByXPath("//*[@label=\"Safari\"]").isDisplayed());
		
		if(!driver.findElementByXPath("//*[@label=\"Safari\"]").isDisplayed()) {
			Map<String, Object> pars = new HashMap<>();
			pars.put("children", 4);
			driver.executeScript("mobile:objects.optimization:start", pars);
			params.clear();
			params.put("start", "20%,30%");
			params.put("end", "15%,90%");
			params.put("duration", "1");
			driver.executeScript("mobile:touch:swipe", params);
			
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			WebElement search = driver.findElementByXPath("//XCUIElementTypeSearchField[@label='Search' or @value='Settings' and @enabled='true']");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			wait.until(ExpectedConditions.visibilityOf(search)).click();
			wait.until(ExpectedConditions.elementToBeClickable(search)).sendKeys("Safari");
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//XCUIElementTypeStaticText[@label='Safari'])[last()]"))).click();
		}
		

		if(deviceInfo(driver, "model").contains("iPad")){
			driver.findElementByXPath("//*[@label='Cancel']").click();
		}
		params.clear();
		params.put("content", "Clear History");
		params.put("threshold", "90");
		params.put("scrolling", "scroll");
		params.put("next", "SWIPE_UP");
		driver.executeScript("mobile:text:select", params);
		if(deviceInfo(driver, "model").contains("iPad")){
			wait.until(ExpectedConditions.visibilityOf(((IOSDriver) driver).findElementByIosClassChain("**/XCUIElementTypeButton[`label =='Clear'`]"))).click();
		}else {
			//wait.until(ExpectedConditions.visibilityOf(((IOSDriver) driver).findElementByIosClassChain("**/XCUIElementTypeButton[`label =='Clear History and Data'`]"))).click();
			driver.findElementByXPath("//*[@label=\"Clear History and Data\"]").click();
		}
		Thread.sleep(2000);
		try{
			params = new HashMap<>();
			params.put("identifier", "com.apple.Preferences");
			driver.executeScript("mobile:application:close", params);
		}catch(Exception e){}
	
		Map<String, Object> pars2 = new HashMap<>();
		driver.executeScript("mobile:objects.optimization:stop", pars2);
	}

	public static String deviceInfo(RemoteWebDriver driver, String deviceProperty){
		Map<String, Object> params = new HashMap<>();
		params.put("property", deviceProperty);
		return (String) driver.executeScript("mobile:device:info", params);
	}

	    /**
	 * Adds a timer report to the Wind Tunnel report.
	 * Example:
	 * reportTimer(driver, loginScreenTimer, 5000, "Timer for login screen", "");
	 */
	public static String reportTimer(RemoteWebDriver driver, long result, long threshold,
	                                 String description, String name) {
	    Map<String, Object> params = new HashMap<String, Object>(7);
	    params.put("result", result);
	    params.put("threshold", threshold);
	    params.put("description", description);
	    params.put("name", name);
	    String status = (String) driver.executeScript("mobile:status:timer", params);
	    return status;
	}

}
