import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Library {
	private RemoteWebDriver driver;
	private boolean runVitals = true;

	public Library(RemoteWebDriver driver, boolean runVitals) {
		this.driver = driver;
		this.runVitals = runVitals;
	}
	
	public Library(RemoteWebDriver driver) {
		this(driver, true);
	}

	public void launchApplication(String app) {

		Map<String, Object> params1 = new HashMap<>();
		params1.put("identifier", app);
		Object result1 = driver.executeScript("mobile:application:open",
				params1);
	}
	
    public void TextValidation(String content) {	
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
	
	public void vitalsStart()
	{
		if (!runVitals) return;
		Map<String, Object> params1 = new HashMap<>();
		List<String> vitals1 = new ArrayList<>();
		vitals1.add("all");
		params1.put("vitals", vitals1);
		List<String> sources1 = new ArrayList<>();
		sources1.add("device");
		params1.put("sources", sources1);
		Object result1 = driver.executeScript("mobile:monitor:start", params1);
		
		
	}
	
	public void vitalsStop()
	{
		if (!runVitals) return;
		Map<String, Object> params2 = new HashMap<>();
		Object result2 = driver.executeScript("mobile:monitor:stop", params2);
		
		
	}
	
	public long step(long threshold, String description, String name)
	{
		long timer = getUXTimer();
		WindTunnelUtils.reportTimer(driver, timer, threshold, description, name);
		return timer;
		
	}

	public void home() {

		Map<String, Object> params1 = new HashMap<>();
		params1.put("keySequence", "HOME");
		Object result1 = driver.executeScript("mobile:presskey", params1);

	}

	public void waitForElement(By xpath, int timeout) {
		WebElement we = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.elementToBeClickable(xpath));
		;
	}

	public Object findText(String text) {
		Map<String, Object> params3 = new HashMap<>();
		params3.put("content", text);
		params3.put("target", "any");
		params3.put("timeout", "40");
		params3.put("threshold", "90");
		Object result = driver.executeScript("mobile:text:find", params3);
		return result;
	}

	public void closeApplication(String app) {
		try {
			Map<String, Object> params4 = new HashMap<>();
			params4.put("identifier", app);
			Object result4 = driver.executeScript("mobile:application:close",
					params4);
		} catch (Exception ex) {
		}
	}

	public void scrollToText(String text) {
		
    	for(int i = 1; i<=3; i++) {
    		
    		if(OCRTextValidation("find", text, "4").contains("true"))
				break;
    		
			Map<String, Object> params = new HashMap<>();
	    	params.put("start", "80%,50%");
	    	params.put("end", "20%,50%");
	    	params.put("duration", "1");
	    	Object res = driver.executeScript("mobile:touch:swipe", params);
	    	
    	}
	}
	
	public String OCRTextValidation(String operation, String content, String timeout){
		
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
	
    public void switchToContext(String context) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
        Map<String,String> params = new HashMap<String,String>();
        params.put("name", context);
        executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
    }
    
    
	public long timerGet(String timerType) {
		String command = "mobile:timer:info";
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", timerType);
		long result = (long) driver.executeScript(command, params);
		return result;
	}

	public long getUXTimer() {
		return timerGet("ux");
	}
	
	
	public boolean findByImage(String repoKey) {
		Map<String, Object> params1 = new HashMap<>();
		params1.put("content", repoKey);
		params1.put("source", "camera");
		params1.put("timeout", "30");
		params1.put("measurement", "accurate");
		params1.put("threshold", "90");
		Object result1 = driver.executeScript("mobile:image:find", params1);

		if (result1.equals("true")) {
			return true;
		} else {
			return false;
		}
	}
	
	public void javaScriptSendkeys(WebElement webElementTextBox, String text) {
        String jsScript = "let input = arguments[0]; \r\n" + "let lastValue = input.value;\r\n"
                + "input.value = arguments[1];\r\n" + "let event = new Event('input', { bubbles: true });\r\n"
                + "event.simulated = true;\r\n"
                + "let tracker = input._valueTracker;\r\n" + "if (tracker) {\r\n" + "  tracker.setValue(lastValue);\r\n"
                + "}\r\n" + "input.dispatchEvent(event);";
        driver.executeScript(jsScript, webElementTextBox, text);
    }
	
public void clearHistoryiOS(RemoteWebDriver driver) throws InterruptedException {
		
		//Use this for iOS version 11 and above
		WebDriverWait wait = new WebDriverWait(driver, 30);
		switchToContext("NATIVE_APP");
		try{
//			Map<String, Object> params = new HashMap<>();
//			params.put("identifier", "com.apple.Preferences");
//			driver.executeScript("mobile:application:close", params);
			closeApplication("com.apple.Preferences");
		}catch(Exception e){}
		Thread.sleep(1500);
		Map<String, Object> params = new HashMap<>();
//		params.put("identifier", "com.apple.Preferences");
//		driver.executeScript("mobile:application:open", params);
		launchApplication("com.apple.Preferences");
		
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

	public String deviceInfo(RemoteWebDriver driver, String deviceProperty){
		Map<String, Object> params = new HashMap<>();
		params.put("property", deviceProperty);
		return (String) driver.executeScript("mobile:device:info", params);
	}

}
