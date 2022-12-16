package com.testDemos;
import java.io.*;
import java.net.*;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;
import org.testng.annotations.Test;
import org.openqa.selenium.Cookie.Builder;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.appium.java_client.*;
import io.appium.java_client.android.*;
import io.appium.java_client.android.options.EspressoOptions;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.*;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.mac.options.Mac2Options;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.windows.options.WindowsOptions;

import com.ShadowDom.Utils.ShadowDomUtils;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.CustomField;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;

/**
 * 
 * Sample Example on Mac Firefox Web Application testing by using New and older way of writing the Capabilities for Appium v2.0 & Selenium v4.2.2 which has W3C standard.
 *	- using Duraion,
 *	- using AppiumBy,
 *	- ShadowDOM elements : finding ShadowDOM element , click Element ShadowDOM
 *	
 * @author Raghavendra Kundaragi
 * @version 1.0
 * 
 */
public class Appium_v_2_0_Demo_MFF {

	public static void main(String[] args) throws MalformedURLException, IOException {

		
	}

	@Test
	public void Appium_v2_0_By_using_NewCapabilitiesOptons_MFF() throws Exception {
		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String host = "demo.perfectomobile.com";
		
		FirefoxOptions browserOptions = new FirefoxOptions();
		Map<String, Object> perfectoOptions = new HashMap<>();
		
		browserOptions.setPlatformName("Mac");
		browserOptions.setBrowserVersion("latest");
		perfectoOptions.put("platformVersion", "macOS Big Sur");
		perfectoOptions.put("location", "NA-US-BOS");
		perfectoOptions.put("resolution", "1024x768");
		perfectoOptions.put("securityToken", "<securityToken>");
		perfectoOptions.put("scriptName", "Selenium_v4.2.2 Mac Firefox Test");
		browserOptions.setCapability("perfecto:options", perfectoOptions);
		
		RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), browserOptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(15000));
		
		// Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
		PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
				.withProject(new Project("Selenium_v4.2.2 Demo Proj", "1.0"))
				.withJob(new Job("Selenium_v4.2.2 Job", 45))
				.withCustomFields(new CustomField("programmer", "Raghavendra Kundaragi"))
				.withCustomFields(new CustomField("author", "rk@perforce.com"))
				.withContextTags("Selenium_v4.2.2")
				.withWebDriver(driver)
				.build();
		ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);

		try {
			reportiumClient.testStart("Selenium_v4.2.2 Demo Proj", new TestContext("Selenium_v4.2.2", "Mac Firefox"));
			
			// write your code here

			driver.get("http://expensetracker.perfectomobile.com/#/app-login");
			
			Thread.sleep(10000);
			
			
			driver.findElement(By.xpath("(//*[@id='login_email']/input)")).sendKeys("test@perfecto.com");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@id='login_password']/input)")).sendKeys("test123");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@id='login_login_btn'])")).click();
			//Assert.assertTrue(driver.findElement(By.xpath("//*[@text='Expenses']")).isDisplayed());
			Thread.sleep(2000);
			
			ShadowDomUtils shadowUtls = new ShadowDomUtils();
			//document.querySelector("").shadowRoot.querySelector("").click() 
			reportiumClient.stepStart("<Menu> Elements which is shadow root element");
			WebElement listShadowParent = ((RemoteWebDriver)driver).findElement(By.cssSelector("body > app-root > ion-app > ion-split-pane > ion-router-outlet > app-expense-list > ion-header > ion-toolbar > ion-buttons > ion-menu-button"));
			Map<String, Object> params = new HashMap<>();
			params.put("parentElement", listShadowParent);
			params.put("innerSelector", "ion-menu-toggle > button");
			//String str = shadowUtls.getTextShadowDOM(((RemoteWebDriver)driver), params);
			shadowUtls.clickElementShadowDOM(((RemoteWebDriver)driver), params);
			
			reportiumClient.reportiumAssert("clicked on shadow root element which <Menu> element", true);
			Thread.sleep(2000);
			
			reportiumClient.stepStart("<Logout> Elements which is shadow root element");
			WebElement listShadowParent2 = ((RemoteWebDriver)driver).findElement(By.cssSelector("body > app-root > ion-app > ion-split-pane > ion-menu > ion-content > ion-list > ion-menu-toggle:nth-child(4) > ion-item"));
			listShadowParent2.click();
			
			reportiumClient.reportiumAssert("clicked on shadow root element which <Logout> element", true);
			
			Thread.sleep(2000);

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
	
	@Test
	public void Appium_v2_0_By_using_OldWayCapabilities_MFF() throws Exception {
		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String host = "demo.perfectomobile.com";
		
		//-------------------------- W3C Capabilities ------------------------------//
		// W3C Protocol is mandatory for Appium 2.0
		
		capabilities.setCapability("platformName", "Mac"); // appium:platformName // Android OR iOS
		capabilities.setCapability("browserName", "Firefox"); //10 & 11 : Edge, Firefox, Chrome, for 7 & 8.1 : Internet Explorer, Firefox, Chrome
		capabilities.setCapability("browserVersion", "latest"); // beta
		
		//-------------------------- Appium Capabilities --------------------------//
		
		//capabilities.setCapability("appium:platformVersion", "10"); //appium:platformVersion
		//capabilities.setCapability("appium:deviceName", "Galaxy 10"); // appium:deviceName
		//capabilities.setCapability("appium:deviceName", "R38M604TNBZ"); // appium:deviceName //8D3E35CF16D8D827E4827ABBCD0E582E2761CADA , R38M604TNBZ
		
		// automationName for Appium 2.0
		//capabilities.setCapability("appium:automationName", "Appium"); //Appium,UIAutomator2 //appium:automationName
		
		// PUBLIC:Raghav/PerfectoNewDemoApp/Ver8.0/ExpenseAppVer1.3.apk, PUBLIC:raghav/v1.0/ExpenseHybridAppVer1.apk : io.perfecto.expense.tracker.hybrid : .activities.BrowseActivity
		// PUBLIC:raghav/v1.0/InvoiceApp1.ipa, PUBLIC:raghav/v1.0/InvoiceHybridApp1.ipa : io.perfecto.expense.tracker.hybrid
		//capabilities.setCapability("appium:app", "PUBLIC:Raghav/PerfectoNewDemoApp/Ver8.0/ExpenseAppVer1.3.apk"); // appium:app
		//capabilities.setCapability("bundleId", "io.perfecto.expense.tracker"); // appium:bundleId
		//capabilities.setCapability("appium:appPackage", "io.perfecto.expense.tracker"); // appium:appPackage
		//capabilities.setCapability("appActivity", ".activities.BrowseActivity"); // appium:appActivity 
		
		//-------------------------- Perfecto Capabilities -------------------------//

		capabilities.setCapability("perfecto:platformVersion", "macOS Big Sur"); // "macOS Ventura", "macOS Monterey", "macOS Big Sur", "macOS Catalina"
		capabilities.setCapability("perfecto:location", "NA-US-BOS");// "NA-US-BOS", 
		capabilities.setCapability("perfecto:resolution", "1024x768"); // Perfecto support different resolutions.
		//perfectoCaps.put("perfecto:model", "Galaxy S.*");  // perfecto:model //iPhone-X
		//perfectoCaps.put("perfecto:deviceSessionId", "1f9a89f7-2848-4d66-9a76-0abcae96e8b9");
		capabilities.setCapability("perfecto:securityToken", "<securityToken>");  // perfecto:securityToken
		
		// automationVersion OR appiumVersion is mandatory to use Appium 2.0 on Perfecto Platform
		//capabilities.setCapability("perfecto:appiumVersion", "1.22.3"); // (appium v2.0 beta)1.22.3=="latest", "1.20.2", or "1.18.3" // perfecto:appiumVersion
		//capabilities.setCapability("perfecto:automationVersion", "1.70.1"); // this override appiumVersion 3.59.0==iOS, 1.70.1==Android // perfecto:automationVersion
		
		//capabilities.setCapability("perfecto:autoInstrument", true); // perfecto:autoInstrument
		//capabilities.setCapability("perfecto:sensorInstrument", true); // perfecto:sensorInstrument
		//capabilities.setCapability("perfecto:iOSResign", true); // perfecto:iOSResign
		//capabilities.setCapability("perfecto:autoLaunch", true); // perfecto:autoLaunch //it is for appium:autoLaunch for Android
		
		//capabilities.setCapability("perfecto:useLegacyFindByName", true); //still work on 1.18.3 and even though have 1.20.2 // perfecto:useLegacyFindByName
		//capabilities.setCapability("perfecto:enableAppiumBehavior", true); //Mandatory // perfecto:enableAppiumBehavior
		//capabilities.setCapability("perfecto:javascriptEnabled", true); // perfecto:javascriptEnabled 
		//capabilities.setCapability("perfecto:openDeviceTimeout", 5.0); // perfecto:openDeviceTimeout
		capabilities.setCapability("perfecto:scriptName", "Selenium_v4.2.2 Mac Firefox Test"); // perfecto:scriptName
		
		
		
		//capabilities.setCapability("takesScreenshot", false); // perfecto:takesScreenshot
		//capabilities.setCapability("screenshotOnError", true); // perfecto:screenshotOnError

		// Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
		//capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);

		RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
		//IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(15000));
		
		// Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
		PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
				.withProject(new Project("Selenium_v4.2.2 Demo Proj", "1.0"))
				.withJob(new Job("Selenium_v4.2.2 Job", 45))
				.withCustomFields(new CustomField("programmer", "Raghavendra Kundaragi"))
				.withCustomFields(new CustomField("author", "rk@perforce.com"))
				.withContextTags("Selenium_v4.2.2")
				.withWebDriver(driver)
				.build();
		ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);

		try {
			reportiumClient.testStart("Selenium_v4.2.2 Demo Proj", new TestContext("Selenium_v4.2.2", "Mac Firefox"));
			
			// write your code here

			driver.get("http://expensetracker.perfectomobile.com/#/app-login");
			
			Thread.sleep(10000);
			
			
			driver.findElement(By.xpath("(//*[@id='login_email']/input)")).sendKeys("test@perfecto.com");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@id='login_password']/input)")).sendKeys("test123");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@id='login_login_btn'])")).click();
			//Assert.assertTrue(driver.findElement(By.xpath("//*[@text='Expenses']")).isDisplayed());
			Thread.sleep(2000);
			
			ShadowDomUtils shadowUtls = new ShadowDomUtils();
			//document.querySelector("").shadowRoot.querySelector("").click() 
			reportiumClient.stepStart("<Menu> Elements which is shadow root element");
			WebElement listShadowParent = ((RemoteWebDriver)driver).findElement(By.cssSelector("body > app-root > ion-app > ion-split-pane > ion-router-outlet > app-expense-list > ion-header > ion-toolbar > ion-buttons > ion-menu-button"));
			Map<String, Object> params = new HashMap<>();
			params.put("parentElement", listShadowParent);
			params.put("innerSelector", "ion-menu-toggle > button");
			//String str = shadowUtls.getTextShadowDOM(((RemoteWebDriver)driver), params);
			shadowUtls.clickElementShadowDOM(((RemoteWebDriver)driver), params);
			
			reportiumClient.reportiumAssert("clicked on shadow root element which <Menu> element", true);
			Thread.sleep(2000);
			
			reportiumClient.stepStart("<Logout> Elements which is shadow root element");
			WebElement listShadowParent2 = ((RemoteWebDriver)driver).findElement(By.cssSelector("body > app-root > ion-app > ion-split-pane > ion-menu > ion-content > ion-list > ion-menu-toggle:nth-child(4) > ion-item"));
			listShadowParent2.click();
			
			reportiumClient.reportiumAssert("clicked on shadow root element which <Logout> element", true);
			
			Thread.sleep(2000);

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

	public static void setPickerWheel(RemoteWebDriver driver,RemoteWebElement picker, String direction, String value) {
		value = value.replaceAll("[^\\x00-\\x7F]", "");
		String name = picker.getAttribute("value").replaceAll("[^\\x00-\\x7F]", "");
		while (!name.equals(value)) {
			System.out.println(name);
			pickerwheelStep(picker, direction, driver);
			// title based will retrieve the title as a string,
			// view based will retrieve a string that represent the view
			// (uniqueness depends on the developer of the app).
			name = picker.getAttribute("value").replaceAll("[^\\x00-\\x7F]", "");
		}
	}

	public static  void pickerwheelStep(RemoteWebElement element, String direction,RemoteWebDriver driver) {
		pickerwheelStep(driver,element, direction, 0.15D);
	}


	public static  void pickerwheelStep(RemoteWebDriver driver, RemoteWebElement element, String direction, double offset) {
		HashMap params = new HashMap();
		params.put("order", direction);
		params.put("offset", Double.valueOf(offset));
		params.put("element", element.getId());
		driver.executeScript("mobile: selectPickerWheelValue", new Object[]{params});
	}

	public static int getTimer(RemoteWebDriver driver, String Timer){

		Map<String, Object> params = new HashMap<>();
		//		params.clear();
		params.put("timerId", Timer);
		params.put("timerType", "UX");
		params.put("units", "seconds");
		int timeEx = toIntExact((Long)driver.executeScript("mobile:timer:info", params));

		System.out.println("App Launch UX timer: "+timeEx/1000.0);
		return timeEx;
	}

	public static int toIntExact(long value) {
		if ((int)value != value) {
			throw new ArithmeticException("integer overflow");
		}
		return (int)value;
	}

	public static void setSecureValue(RemoteWebDriver driver,String ElementIdentifier, String Secured_Value){
		Map<String, Object> params = new HashMap<>();

		params.put("text", Secured_Value);
		params.put("by", "identifier");
		params.put("value", ElementIdentifier);
		driver.executeScript("mobile:application.element:set", params);

	}
}
