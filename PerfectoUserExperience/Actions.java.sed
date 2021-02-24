/*
 * LoadRunner Java script. (Build: _build_number_)
 * 
 * Script Description: 
 *                     
 */

import lrapi.lr;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;


import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;

import io.appium.java_client.AppiumDriver;

public class Actions
{
	private static RemoteWebDriver driver;

	public int init() throws Throwable {
		return 0;
	}//end of init


	public int action() throws Throwable {

	}//end of action


	private static void switchToContext(RemoteWebDriver driver, String context) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        Map<String,String> params = new HashMap<String,String>();
        params.put("name", context);
        executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
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
        params1.put("timeout", "30");
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
  	
	public int end() throws Throwable {
		return 0;
	}//end of end
}
