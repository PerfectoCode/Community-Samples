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

import io.appium.java_client.*;
import io.appium.java_client.android.*;
import io.appium.java_client.android.options.EspressoOptions;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.*;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.mac.options.Mac2Options;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.windows.options.WindowsOptions;

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
 * Sample Example on iOS Native Application testing by using New and older way of writing the Capabilities for Appium v2.0 & Selenium v4.2.2 which has W3C standard.
 *	- using Duraion,
 *	- using AppiumBy,
 *	
 * @author Raghavendra Kundaragi
 * @version 1.0
 * 
 */
public class Appium_v_2_0_Demo_IN {

	public static void main(String[] args) throws MalformedURLException, IOException {

		
	}

	@Test
	public void Appium_v2_0_By_using_NewCapabilitiesOptons_IN() throws Exception {
		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String host = "partners.perfectomobile.com";
		
		XCUITestOptions xcuiTestOptions = new XCUITestOptions();
		Map<String, Object> perfectoOptions = new HashMap<>();
		
		xcuiTestOptions.setPlatformName("ios");
		xcuiTestOptions.setDeviceName("C996B233C51A72F3E80DB75A7CB580F200F398E8");
		xcuiTestOptions.setAutomationName("Appium");
		xcuiTestOptions.setApp("PUBLIC:raghav/v1.0/InvoiceApp1.ipa");
		xcuiTestOptions.setBundleId("io.perfecto.expense.tracker");
		
		perfectoOptions.put("securityToken", "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJhMzY3MTc2My05NmQwLTRmMzktYjcwZS0yNjFlNjlmZjM1NzYifQ.eyJpYXQiOjE2MjM4NzQ0MDgsImp0aSI6IjEwNWQzMTFjLWQ5NjUtNDZlZS1iZTNhLWVkYWZiOTI2ZjkwYiIsImlzcyI6Imh0dHBzOi8vYXV0aC5wZXJmZWN0b21vYmlsZS5jb20vYXV0aC9yZWFsbXMvcGFydG5lcnMtcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoiaHR0cHM6Ly9hdXRoLnBlcmZlY3RvbW9iaWxlLmNvbS9hdXRoL3JlYWxtcy9wYXJ0bmVycy1wZXJmZWN0b21vYmlsZS1jb20iLCJzdWIiOiJjNzIxMTRjOS0xNjM5LTQ5NTEtYTE2Zi1iNjEzNzUwMDc0Y2IiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJub25jZSI6IjUzNTRlMTIwLTBiODQtNDQyNS04YTdlLTRmODdjZDFhODJjMCIsInNlc3Npb25fc3RhdGUiOiJkYjE1YzhkZi1iMjg5LTQzZTctOWI5Yy1mNDFjNGNkMTY2NjIiLCJzY29wZSI6Im9wZW5pZCBvZmZsaW5lX2FjY2VzcyJ9.lcb5GcL-ZYA6bboBuUuAFanN_IYBnygMfXzaUwnJZdk");
		//perfectoOptions.put("deviceName", "C996B233C51A72F3E80DB75A7CB580F200F398E8");
		//perfectoOptions.put("app", "PUBLIC:raghav/v1.0/InvoiceApp1.ipa");
		//perfectoOptions.put("bundleId", "io.perfecto.expense.tracker");
		perfectoOptions.put("appiumVersion", "1.22.3");
		perfectoOptions.put("automationVersion", "3.59.0");
		perfectoOptions.put("autoInstrument", true);
		perfectoOptions.put("sensorInstrument", true);
		perfectoOptions.put("iOSResign", true);
		perfectoOptions.put("autoLaunch", true);
		perfectoOptions.put("javascriptEnabled", true);
		perfectoOptions.put("openDeviceTimeout", 5.0);
		perfectoOptions.put("scriptName", "Appium_v2.0 iOS Native Test");
		//perfectoOptions.put("takesScreenshot", false);
		//perfectoOptions.put("screenshotOnError", true);
		xcuiTestOptions.setCapability("perfecto:options", perfectoOptions);
		
		IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), xcuiTestOptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(15000));
		
		//IOSBatteryInfo
		
		// Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
		PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
				.withProject(new Project("Appium v2.0 Demo Proj", "1.0"))
				.withJob(new Job("Appium v2.0 Job", 45))
				.withCustomFields(new CustomField("programmer", "Raghavendra Kundaragi"))
				.withCustomFields(new CustomField("author", "rk@perforce.com"))
				.withContextTags("Appiumv2.0")
				.withWebDriver(driver)
				.build();
		ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);

		try {
			reportiumClient.testStart("Appium v1.20 Demo Proj", new TestContext("Appium_v2.0", "iOS Native"));
			
			// write your code here

			Thread.sleep(10000);
			
			driver.findElement(By.xpath("(//*[@name='login_email'] | //*[@class='native-input sc-ion-input-ios' and @name='login_email'] | //*[@id='login_email'])")).sendKeys("test@perfecto.com");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@name='login_password'] | //*[@class='native-input sc-ion-input-ios' and @name='login_password'] | //*[@id='login_password'])")).sendKeys("test123");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@label='Login'] | //*[@id='login_login_btn'])")).click();
			//Assert.assertTrue(driver.findElement(By.xpath("//*[@text='Expenses']")).isDisplayed());
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@label='side menu'] | //*[@class='sc-ion-buttons-ios-h sc-ion-buttons-ios-s hydrated'] | //*[@class='button ion-activatable hydrated'])")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@label='Logout'] | //*[@class='in-list item ion-focusable item-label hydrated'])")).click();
			
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
	public void Appium_v2_0_By_using_OldWayCapabilities_IN() throws Exception {
		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String host = "partners.perfectomobile.com";
		
		//-------------------------- W3C Capabilities ------------------------------//
		// W3C Protocol is mandatory for Appium 2.0
		
		capabilities.setCapability("platformName", "iOS"); // appium:platformName // Android OR iOS
		//capabilities.setCapability("browserName", "Safari");
		//capabilities.setCapability("browserVersion", "110");
		
		//-------------------------- Appium Capabilities --------------------------//
		
		//capabilities.setCapability("appium:platformVersion", "10"); //appium:platformVersion
		capabilities.setCapability("appium:deviceName", "C996B233C51A72F3E80DB75A7CB580F200F398E8"); // appium:deviceName //C996B233C51A72F3E80DB75A7CB580F200F398E8 , R38M604TNBZ
		
		// automationName for Appium 2.0
		capabilities.setCapability("appium:automationName", "Appium"); //Appium,UIAutomator2 //appium:automationName
		
		// PUBLIC:Raghav/PerfectoNewDemoApp/Ver8.0/ExpenseAppVer1.3.apk, PUBLIC:raghav/v1.0/ExpenseHybridAppVer1.apk : io.perfecto.expense.tracker.hybrid : app.perfecto.com.expencemanager.ui.splash.SplashActivity
		// PUBLIC:raghav/v1.0/InvoiceApp1.ipa, PUBLIC:raghav/v1.0/InvoiceHybridApp1.ipa : io.perfecto.expense.tracker.hybrid
		capabilities.setCapability("appium:app", "PUBLIC:raghav/v1.0/InvoiceApp1.ipa"); // appium:app
		capabilities.setCapability("bundleId", "io.perfecto.expense.tracker"); // appium:bundleId
		//capabilities.setCapability("appium:appPackage", "io.perfecto.expense.tracker"); // appium:appPackage
		//capabilities.setCapability("appActivity", "app.perfecto.com.expencemanager.ui.splash.SplashActivity"); // appium:appActivity 
		
		//-------------------------- Perfecto Capabilities -------------------------//

		//perfectoCaps.put("perfecto:model", "iPhone.*");  // perfecto:model //iPhone-X
		//perfectoCaps.put("perfecto:deviceSessionId", "1f9a89f7-2848-4d66-9a76-0abcae96e8b9");
		capabilities.setCapability("perfecto:securityToken", "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJhMzY3MTc2My05NmQwLTRmMzktYjcwZS0yNjFlNjlmZjM1NzYifQ.eyJpYXQiOjE2MjM4NzQ0MDgsImp0aSI6IjEwNWQzMTFjLWQ5NjUtNDZlZS1iZTNhLWVkYWZiOTI2ZjkwYiIsImlzcyI6Imh0dHBzOi8vYXV0aC5wZXJmZWN0b21vYmlsZS5jb20vYXV0aC9yZWFsbXMvcGFydG5lcnMtcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoiaHR0cHM6Ly9hdXRoLnBlcmZlY3RvbW9iaWxlLmNvbS9hdXRoL3JlYWxtcy9wYXJ0bmVycy1wZXJmZWN0b21vYmlsZS1jb20iLCJzdWIiOiJjNzIxMTRjOS0xNjM5LTQ5NTEtYTE2Zi1iNjEzNzUwMDc0Y2IiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJub25jZSI6IjUzNTRlMTIwLTBiODQtNDQyNS04YTdlLTRmODdjZDFhODJjMCIsInNlc3Npb25fc3RhdGUiOiJkYjE1YzhkZi1iMjg5LTQzZTctOWI5Yy1mNDFjNGNkMTY2NjIiLCJzY29wZSI6Im9wZW5pZCBvZmZsaW5lX2FjY2VzcyJ9.lcb5GcL-ZYA6bboBuUuAFanN_IYBnygMfXzaUwnJZdk");  // perfecto:securityToken
		
		// automationVersion OR appiumVersion is mandatory to use Appium 2.0 on Perfecto Platform
		capabilities.setCapability("perfecto:appiumVersion", "1.22.3"); // (appium v2.0 beta)1.22.3=="latest", "1.20.2", or "1.18.3" // perfecto:appiumVersion
		capabilities.setCapability("perfecto:automationVersion", "3.59.0"); // this override appiumVersion 3.59.0==iOS, 1.70.1==Android // perfecto:automationVersion
		
		capabilities.setCapability("perfecto:autoInstrument", true); // perfecto:autoInstrument
		capabilities.setCapability("perfecto:sensorInstrument", true); // perfecto:sensorInstrument
		capabilities.setCapability("perfecto:iOSResign", true); // perfecto:iOSResign
		capabilities.setCapability("perfecto:autoLaunch", true); // perfecto:autoLaunch //it is for appium:autoLaunch for Android
		
		//capabilities.setCapability("perfecto:useLegacyFindByName", true); //still work on 1.18.3 and even though have 1.20.2 // perfecto:useLegacyFindByName
		//capabilities.setCapability("perfecto:enableAppiumBehavior", true); //Mandatory // perfecto:enableAppiumBehavior
		capabilities.setCapability("perfecto:javascriptEnabled", true); // perfecto:javascriptEnabled 
		capabilities.setCapability("perfecto:openDeviceTimeout", 5.0); // perfecto:openDeviceTimeout
		capabilities.setCapability("perfecto:scriptName", "Appium_v2.0 iOS Native Test"); // perfecto:scriptName
				
		//capabilities.setCapability("takesScreenshot", false); // perfecto:takesScreenshot
		//capabilities.setCapability("screenshotOnError", true); // perfecto:screenshotOnError

		// Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
		//capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);

		IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
		//IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(15000));
		
		// Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
		PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
				.withProject(new Project("Appium v2.0 Demo Proj", "1.0"))
				.withJob(new Job("Appium v2.0 Job", 45))
				.withCustomFields(new CustomField("programmer", "Raghavendra Kundaragi"))
				.withCustomFields(new CustomField("author", "rk@perforce.com"))
				.withContextTags("Appiumv2.0")
				.withWebDriver(driver)
				.build();
		ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);

		try {
			reportiumClient.testStart("Appium v1.20 Demo Proj", new TestContext("Appiumv1.20", "iOS Native"));
			
			// write your code here

			Thread.sleep(10000);
			
			driver.findElement(By.xpath("(//*[@name='login_email'] | //*[@class='native-input sc-ion-input-ios' and @name='login_email'] | //*[@id='login_email'])")).sendKeys("test@perfecto.com");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@name='login_password'] | //*[@class='native-input sc-ion-input-ios' and @name='login_password'] | //*[@id='login_password'])")).sendKeys("test123");
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@label='Login'] | //*[@id='login_login_btn'])")).click();
			//Assert.assertTrue(driver.findElement(By.xpath("//*[@text='Expenses']")).isDisplayed());
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@label='side menu'] | //*[@class='sc-ion-buttons-ios-h sc-ion-buttons-ios-s hydrated'] | //*[@class='button ion-activatable hydrated'])")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//*[@label='Logout'] | //*[@class='in-list item ion-focusable item-label hydrated'])")).click();
			
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
