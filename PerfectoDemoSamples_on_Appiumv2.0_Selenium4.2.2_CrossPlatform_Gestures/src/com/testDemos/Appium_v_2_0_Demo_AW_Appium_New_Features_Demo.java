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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.Cookie.Builder;

import io.appium.java_client.*;
import io.appium.java_client.android.*;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.EspressoOptions;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.*;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.mac.options.Mac2Options;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.serverevents.CustomEvent;
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
 * Sample Example on Google Map Application for demo testing by using Appium v2.0 & Selenium v4.2.2 which has W3C standard. which below functionalities as demo has
 * 1. New and older way of writing the Capabilities
 * 2. some exercise of Selenium operations alike 
 *	- using Sequence Actions methods
 *		- Swipe UP / Down / Left / Right
 *		- Double Tap,
 *		- Long Press,
 *		- Drag & Drop, 
 * 	- Location of Element understanding, 
 * 	- using Duraion,
 *	- using AppiumBy,
 *  
 * @author Raghavendra Kundaragi
 * @version 1.0
 * 
 */
public class Appium_v_2_0_Demo_AW_Appium_New_Features_Demo {

	public static void main(String[] args) throws MalformedURLException, IOException {

		
		
	}
	
	@Test
	public void Appium_v_2_0_Demo_AW() throws Exception {
		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String host = "partners.perfectomobile.com";
		
		UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
		Map<String, Object> perfectoOptions = new HashMap<>();
		
		uiAutomator2Options.setPlatformName("Android");
		uiAutomator2Options.setDeviceName("R38M604TNBZ"); //932AY05WL7, R38M604TNBZ
		//uiAutomator2Options.withBrowserName("Chrome");
		uiAutomator2Options.setAutomationName("Appium");
		//uiAutomator2Options.setApp("PRIVATE:ComposeMaterialCatalog_2.0.1.apk");
		uiAutomator2Options.setAppPackage("com.google.android.apps.maps"); //com.google.android.apps.maps, com.vodqareactnative
		//uiAutomator2Options.setAppActivity("app.perfecto.com.expencemanager.ui.splash.SplashActivity");
		
		perfectoOptions.put("securityToken", "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJhMzY3MTc2My05NmQwLTRmMzktYjcwZS0yNjFlNjlmZjM1NzYifQ.eyJpYXQiOjE2MjM4NzQ0MDgsImp0aSI6IjEwNWQzMTFjLWQ5NjUtNDZlZS1iZTNhLWVkYWZiOTI2ZjkwYiIsImlzcyI6Imh0dHBzOi8vYXV0aC5wZXJmZWN0b21vYmlsZS5jb20vYXV0aC9yZWFsbXMvcGFydG5lcnMtcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoiaHR0cHM6Ly9hdXRoLnBlcmZlY3RvbW9iaWxlLmNvbS9hdXRoL3JlYWxtcy9wYXJ0bmVycy1wZXJmZWN0b21vYmlsZS1jb20iLCJzdWIiOiJjNzIxMTRjOS0xNjM5LTQ5NTEtYTE2Zi1iNjEzNzUwMDc0Y2IiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJub25jZSI6IjUzNTRlMTIwLTBiODQtNDQyNS04YTdlLTRmODdjZDFhODJjMCIsInNlc3Npb25fc3RhdGUiOiJkYjE1YzhkZi1iMjg5LTQzZTctOWI5Yy1mNDFjNGNkMTY2NjIiLCJzY29wZSI6Im9wZW5pZCBvZmZsaW5lX2FjY2VzcyJ9.lcb5GcL-ZYA6bboBuUuAFanN_IYBnygMfXzaUwnJZdk");
		//perfectoOptions.put("deviceName", "R38M604TNBZ");
		//perfectoOptions.put("deviceSessionId", "d3de1ed9-cfce-424d-93d1-4cd28e8970c5");
		//perfectoOptions.put("app", "PRIVATE:ComposeMaterialCatalog_2.0.1.apk");
		//perfectoOptions.put("appPackage", "com.google.android.apps.maps");
		//perfectoOptions.put("bundleId", "com.google.android.apps.mapslite");
		perfectoOptions.put("appiumVersion", "1.22.3");
		perfectoOptions.put("automationVersion", "1.70.1");
		//perfectoOptions.put("autoInstrument", true);
		//perfectoOptions.put("sensorInstrument", true);
		//perfectoOptions.put("iOSResign", true);
		//perfectoOptions.put("autoLaunch", true);
		perfectoOptions.put("javascriptEnabled", true);
		perfectoOptions.put("openDeviceTimeout", 5.0);
		perfectoOptions.put("scriptName", "Appium_v2.0 Android New feature Testing Demo");
		//perfectoOptions.put("takesScreenshot", false);
		//perfectoOptions.put("screenshotOnError", true);
		uiAutomator2Options.setCapability("perfecto:options", perfectoOptions);
		
		AndroidDriver driver = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), uiAutomator2Options);
		
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
			reportiumClient.testStart("Appium v1.20 Demo Proj", new TestContext("Appium_v2.0", "Appium Android v2.0 New feature Testing Demo"));
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			
			
			
			/*
			
			//driver.get("https://www.google.com/maps/@12.9710746,77.7507786,16.48z");
			//these website's can be used for some exercise of selenium operations (Drag & Drop, Location of Element understanding, SendKeys, textbox usage, mouse pointer behaviour, scrolling page and Scrolling iFrames)
			//https://www.selenium.dev/selenium/web/mouse_interaction.html  
			//https://www.selenium.dev/selenium/web/pointerActionsPage.html
			//https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame_out_of_view.html
			//https://www.selenium.dev/selenium/web/scrolling_tests/frame_with_nested_scrolling_frame.html
			
			Set<String> contextNames = driver.getContextHandles();
			for(int i=0;i<contextNames.size(); i++)
			{
				System.out.println("Contexts are : " + contextNames.toArray()[0] + " " + contextNames.toArray()[1]);
			}
			driver.context("WEBVIEW_1"); // NATIVE_APP, WEBVIEW_1
			
			*/
			
			Thread.sleep(2000);
			WebElement exloreButton = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//*[@resource-id='com.google.android.apps.maps:id/explore_tab_strip_button']")));
			exloreButton.click();
			
			//WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//*[@class='map-inner map-extent']")));
			WebElement scrollableView = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//*[@resource-id='com.google.android.apps.maps:id/explore_tab_home_bottom_sheet']")));
			//WebElement scrollable = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//*[@resource-id='com.google.android.apps.maps:id/explore_tab_home_bottom_sheet']")));
			//WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//*[@resource-id='com.google.android.apps.maps:id/explore_tab_home_bottom_sheet']/android.widget.FrameLayout[1]")));
			
			
			
			//--------------------------------------------------------------------------//
			
			
			System.out.println("Swipe Down operation Start");
			reportiumClient.stepStart("Swipe Down opertion using Sequence Actions");
			
			//Point source = scrollable.getRect().getPoint();
			Point source_scrollableView = scrollableView.getRect().getPoint();
	        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        Sequence sequence = new Sequence(finger, 1);
	        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),
	                (source_scrollableView.x + (scrollableView.getSize().width/2)), (int) (source_scrollableView.y + (scrollableView.getSize().height * 0.2)) ));
	        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
	        sequence.addAction(finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), 
	        		source_scrollableView.getX() + (scrollableView.getSize().width/2) , (int) (source_scrollableView.y + (scrollableView.getSize().height * 0.6)) ));
	        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
	        driver.perform(Arrays.asList(sequence));
	        
	        reportiumClient.reportiumAssert("Swipe Down opertion using Sequence Actions", true);
	        System.out.println("Swipe Down operation End");
	        
	        
	        //--------------------------------------------------------------------------//
	        
	        
	        Thread.sleep(2000);
	        
	        System.out.println("Swipe Right : swiping Left to Right operation Start");
	        reportiumClient.stepStart("Swipe Right opertion using Sequence Actions");
	        
	        Point source_scrollableView1 = scrollableView.getRect().getPoint();
	        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        Sequence sequence1 = new Sequence(finger1, 1);
	        sequence1.addAction(finger1.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),
	                (int) (source_scrollableView1.x + (scrollableView.getSize().width * 0.2)), (int) (source_scrollableView1.y + (scrollableView.getSize().height / 2)) ));
	        sequence1.addAction(finger1.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
	        sequence1.addAction(finger1.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), 
	        		(int) (source_scrollableView1.getX() + (scrollableView.getSize().width * 0.8)) ,(int) (source_scrollableView1.y + (scrollableView.getSize().height / 2)) ));
	        sequence1.addAction(finger1.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
	        driver.perform(Arrays.asList(sequence1));
	        
	        reportiumClient.reportiumAssert("Swipe Right opertion using Sequence Actions", true);
	        System.out.println("Swipe Right : swiping Left to Right operation End");
	        
	        
	        //--------------------------------------------------------------------------//
	        
	        
	        
			Thread.sleep(2000);
			
			System.out.println("Swipe Left : swiping Right to Left operation Start");
	        reportiumClient.stepStart("Swipe Left opertion using Sequence Actions");
	        
	        Point source_scrollableView2 = scrollableView.getRect().getPoint();
	        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        Sequence sequence2 = new Sequence(finger2, 1);
	        sequence2.addAction(finger2.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),
	                (int) (source_scrollableView2.x + (scrollableView.getSize().width * 0.8)), (int) (source_scrollableView2.y + (scrollableView.getSize().height / 2)) ));
	        sequence2.addAction(finger2.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
	        sequence2.addAction(finger2.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), 
	        		(int) (source_scrollableView2.getX() + (scrollableView.getSize().width * 0.2)) ,(int) (source_scrollableView2.y + (scrollableView.getSize().height / 2)) ));
	        sequence2.addAction(finger2.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
	        driver.perform(Arrays.asList(sequence2));
	        
	        reportiumClient.reportiumAssert("Swipe Left opertion using Sequence Actions", true);
	        System.out.println("Swipe Left : swiping Right to Left operation End");
	        
	        
	        //--------------------------------------------------------------------------//
	        
	        
	        
			Thread.sleep(2000);
			
			System.out.println("Swipe Up operation Start");
			reportiumClient.stepStart("Swipe Up opertion using Sequence Actions");
			
			//Point source = scrollable.getRect().getPoint();
			Point source_scrollableView3 = scrollableView.getRect().getPoint();
	        PointerInput finger3 = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        Sequence sequence3 = new Sequence(finger3, 1);
	        sequence3.addAction(finger3.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(),
	                (source_scrollableView3.x + (scrollableView.getSize().width/2)), (int) (source_scrollableView3.y + (scrollableView.getSize().height * 0.8)) ));
	        sequence3.addAction(finger3.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
	        sequence3.addAction(finger3.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), 
	        		source_scrollableView3.getX() + (scrollableView.getSize().width/2) , (int) (source_scrollableView3.y + (scrollableView.getSize().height * 0.2)) ));
	        sequence3.addAction(finger3.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
	        driver.perform(Arrays.asList(sequence3));
	        
	        reportiumClient.reportiumAssert("Swipe Up opertion using Sequence Actions", true);
	        System.out.println("Swipe Up operation End");
	        
	        
	        //--------------------------------------------------------------------------//
	        
	        
	        Thread.sleep(2000);

	        System.out.println("Double Tap operation Start");
			reportiumClient.stepStart("Double Tap opertion using Actions");
			
	        WebElement element = (WebElement) driver.findElement(AppiumBy.xpath("//*[@resource-id='com.google.android.apps.maps:id/map_frame']//*[@password='false']"));
	        Thread.sleep(1000);
	        Point source = element.getRect().getPoint();
	        PointerInput finger4 = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        Sequence tap = new Sequence(finger4, 1);
	        tap.addAction(finger4.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.x, source.y));
	        tap.addAction(finger4.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
	        tap.addAction(new Pause(finger4, Duration.ofMillis(200)));
	        tap.addAction(finger4.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
	        tap.addAction(new Pause(finger4, Duration.ofMillis(40)));
	        tap.addAction(finger4.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
	        tap.addAction(new Pause(finger4, Duration.ofMillis(200)));
	        tap.addAction(finger4.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
	        driver.perform(Arrays.asList(tap));
	        Thread.sleep(5000);
			
	        reportiumClient.reportiumAssert("Double Tap opertion using Actions", true);
	        System.out.println("Double Tap operation End");
	        
	        
	        //--------------------------------------------------------------------------//
	        
	        
	        System.out.println("Long Press operation Start");
			reportiumClient.stepStart("Long Press opertion using Actions");
			
	        WebElement longPress = driver.findElement(AppiumBy.xpath("//*[@resource-id='com.google.android.apps.maps:id/map_frame']//*[@password='false']"));
	        new Actions(driver).clickAndHold(longPress).perform();
	        Thread.sleep(4000);
	        
	        reportiumClient.reportiumAssert("Long Press opertion using Actions", true);
	        System.out.println("Long Press operation End");
	        
	        
	        //--------------------------------------------------------------------------//
	        
	        System.out.println("Pinch&Zoom operation Start");
			reportiumClient.stepStart("Pinch&Zoom opertion using Actions");
			
	        PointerInput finger_1 = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        PointerInput finger_2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

	        Dimension size = driver.findElement(AppiumBy.xpath("//*[@resource-id='com.google.android.apps.maps:id/map_frame']//*[@password='false']")).getSize();
	        Point source1 = new Point(size.getWidth(), size.getHeight());

	        Sequence pinchAndZoom1 = new Sequence(finger_1, 0);
	        pinchAndZoom1.addAction(finger_1.createPointerMove(Duration.ofMillis(0),
	                PointerInput.Origin.viewport(), source1.x / 2, source1.y / 2));
	        pinchAndZoom1.addAction(finger_1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
	        pinchAndZoom1.addAction(new Pause(finger_1, Duration.ofMillis(100)));
	        pinchAndZoom1.addAction(finger_1.createPointerMove(Duration.ofMillis(600),
	                PointerInput.Origin.viewport(), source1.x / 3, source1.y / 3));
	        pinchAndZoom1.addAction(finger_1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));


	        Sequence pinchAndZoom2 = new Sequence(finger_2, 0);
	        pinchAndZoom2.addAction(finger_2.createPointerMove(Duration.ofMillis(0),
	                PointerInput.Origin.viewport(), source1.x / 2, source1.y / 2));
	        pinchAndZoom2.addAction(finger_2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
	        pinchAndZoom2.addAction(new Pause(finger_2, Duration.ofMillis(100)));
	        pinchAndZoom2.addAction(finger_2.createPointerMove(Duration.ofMillis(600),
	                PointerInput.Origin.viewport(), source1.x * 3 / 4, source1.y * 3 / 4));
	        pinchAndZoom2.addAction(finger_2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

	        driver.perform(Arrays.asList(pinchAndZoom1, pinchAndZoom2));
	        driver.perform(Arrays.asList(pinchAndZoom1, pinchAndZoom2));
	        
	        Thread.sleep(4000);
	        
	        reportiumClient.reportiumAssert("Pinch&Zoom opertion using Actions", true);
	        System.out.println("Pinch&Zoom operation End");
	        
	        //--------------------------------------------------------------------------//
	        
	        driver.pressKey(new KeyEvent(AndroidKey.HOME));
	        Thread.sleep(2000);
	        
	        System.out.println("Drag&Drop operation Start");
			reportiumClient.stepStart("Drag&Drop opertion using Actions");
			
			Thread.sleep(4000);
	        WebElement source_1 = (WebElement) driver.findElement(AppiumBy.xpath("//*[@resource-id='com.sec.android.daemonapp:id/widget_content']"));
	        WebElement target_1 = (WebElement) driver.findElement(AppiumBy.xpath("(//*[@content-desc='Gallery'])[2]"));

	        //new Actions(driver).clickAndHold(source_1).perform();
	        
	        Point source_11 = source_1.getRect().getPoint();
	        Point target_11 = target_1.getRect().getPoint();
	        PointerInput finger5 = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        Sequence dragNDrop = new Sequence(finger5, 1);
	        dragNDrop.addAction(finger5.createPointerMove(Duration.ofMillis(0),
	                            PointerInput.Origin.viewport(), source_11.x, source_11.y));
	        dragNDrop.addAction(finger5.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
	        dragNDrop.addAction(new Pause(finger5, Duration.ofMillis(600)));
	        dragNDrop.addAction(finger5.createPointerMove(Duration.ofMillis(600),
	                            PointerInput.Origin.viewport(),target_11.x, target_11.y+target_1.getSize().getHeight()));
	        dragNDrop.addAction(finger5.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
	        driver.perform(Arrays.asList(dragNDrop));
	        
	        reportiumClient.reportiumAssert("Drag&Drop opertion using Actions", true);
	        System.out.println("Drag&Drop operation End");
	        
	        Thread.sleep(2000);
	        
	        
	        //--------------------------------------------------------------------------//
	        
	        
	        
	        //--------------------------------------------------------------------------//
	        
	        
	        
	        
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

	//@Test
	public void Appium_v_2_0_Demo_AW_Appium_New_Features_Demo() throws Exception {
		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String host = "partners.perfectomobile.com";
		
		UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
		Map<String, Object> perfectoOptions = new HashMap<>();
		
		uiAutomator2Options.setPlatformName("Android");
		uiAutomator2Options.setDeviceName("R38M604TNBZ"); //932AY05WL7, R38M604TNBZ
		uiAutomator2Options.withBrowserName("Chrome");
		uiAutomator2Options.setAutomationName("Appium");
		//uiAutomator2Options.setApp("PUBLIC:raghav/v1.0/ExpenseHybridAppVer1.apk");
		//uiAutomator2Options.setAppPackage("io.perfecto.expense.tracker.hybrid");
		//uiAutomator2Options.setAppActivity("app.perfecto.com.expencemanager.ui.splash.SplashActivity");
		
		perfectoOptions.put("securityToken", "eyJhbGciOiJIUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJhMzY3MTc2My05NmQwLTRmMzktYjcwZS0yNjFlNjlmZjM1NzYifQ.eyJpYXQiOjE2MjM4NzQ0MDgsImp0aSI6IjEwNWQzMTFjLWQ5NjUtNDZlZS1iZTNhLWVkYWZiOTI2ZjkwYiIsImlzcyI6Imh0dHBzOi8vYXV0aC5wZXJmZWN0b21vYmlsZS5jb20vYXV0aC9yZWFsbXMvcGFydG5lcnMtcGVyZmVjdG9tb2JpbGUtY29tIiwiYXVkIjoiaHR0cHM6Ly9hdXRoLnBlcmZlY3RvbW9iaWxlLmNvbS9hdXRoL3JlYWxtcy9wYXJ0bmVycy1wZXJmZWN0b21vYmlsZS1jb20iLCJzdWIiOiJjNzIxMTRjOS0xNjM5LTQ5NTEtYTE2Zi1iNjEzNzUwMDc0Y2IiLCJ0eXAiOiJPZmZsaW5lIiwiYXpwIjoib2ZmbGluZS10b2tlbi1nZW5lcmF0b3IiLCJub25jZSI6IjUzNTRlMTIwLTBiODQtNDQyNS04YTdlLTRmODdjZDFhODJjMCIsInNlc3Npb25fc3RhdGUiOiJkYjE1YzhkZi1iMjg5LTQzZTctOWI5Yy1mNDFjNGNkMTY2NjIiLCJzY29wZSI6Im9wZW5pZCBvZmZsaW5lX2FjY2VzcyJ9.lcb5GcL-ZYA6bboBuUuAFanN_IYBnygMfXzaUwnJZdk");
		//perfectoOptions.put("deviceName", "R38M604TNBZ");
		//perfectoOptions.put("deviceSessionId", "d3de1ed9-cfce-424d-93d1-4cd28e8970c5");
		//perfectoOptions.put("app", "PUBLIC:raghav/v1.0/ExpenseHybridAppVer1.apk");
		//perfectoOptions.put("bundleId", "io.perfecto.expense.tracker.hybrid");
		perfectoOptions.put("appiumVersion", "1.22.3");
		perfectoOptions.put("automationVersion", "1.70.1");
		//perfectoOptions.put("autoInstrument", true);
		//perfectoOptions.put("sensorInstrument", true);
		//perfectoOptions.put("iOSResign", true);
		//perfectoOptions.put("autoLaunch", true);
		perfectoOptions.put("javascriptEnabled", true);
		perfectoOptions.put("openDeviceTimeout", 5.0);
		perfectoOptions.put("scriptName", "Appium_v2.0 Android New feature Testing Demo");
		//perfectoOptions.put("takesScreenshot", false);
		//perfectoOptions.put("screenshotOnError", true);
		uiAutomator2Options.setCapability("perfecto:options", perfectoOptions);
		
		RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), uiAutomator2Options);
		
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
			reportiumClient.testStart("Appium v1.20 Demo Proj", new TestContext("Appiumv1.20", "Appium Android v2.0 New feature Testing Demo"));
			
			// write your code here

			// write your code here

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			
			/*
			
			driver.toggleAirplaneMode();
			
			driver.get("http://expensetracker.perfectomobile.com/#/app-login");
			
			reportiumClient.reportiumAssert("Login page loading : validating : Check for Green/Red flag in Report ", wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@resource-id=\"com.android.chrome:id/location_bar_verbose_status\" and @text='Offline'] | //*[@resource-id=\"com.android.chrome:id/status_text\" and @text='No internet connection'])"))).isDisplayed()); 
			
			driver.toggleAirplaneMode();
			
			driver.get("http://expensetracker.perfectomobile.com/#/app-login");
			reportiumClient.reportiumAssert("Login page loading : validating : Check for Green/Red flag in Report ", wait.until(ExpectedConditions.invisibilityOfElementLocated(AppiumBy.xpath("(//*[@resource-id=\"com.android.chrome:id/location_bar_verbose_status\" and @text='Offline'] | //*[@resource-id=\"com.android.chrome:id/status_text\" and @text='No internet connection'])")))); 
			
			//driver.toggleData();
			//driver.toggleLocationServices();
			
			driver.toggleWifi();
			
			reportiumClient.reportiumAssert("Login page loading : validating : Check for Green/Red flag in Report ", wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@resource-id=\"com.android.chrome:id/status_text\" and @text='No internet connection'])"))).isDisplayed()); 

			Set<String> contextNames = driver.getContextHandles();
			System.out.println("Contexts are : " + contextNames.toArray()[0] + " " + contextNames.toArray()[1]);
			driver.context("WEBVIEW_1"); // NATIVE_APP, WEBVIEW_1
			
			reportiumClient.reportiumAssert("Login page loading : validating : Check for Green/Red flag in Report ", wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//h1//*[text()=\"No internet\"])"))).isDisplayed()); 
			
			contextNames = driver.getContextHandles();
			System.out.println("Contexts are : " + contextNames.toArray()[0] + " " + contextNames.toArray()[1]);
			driver.context("NATIVE_APP"); // NATIVE_APP, WEBVIEW_1
			
			driver.toggleWifi();
			
			driver.get("http://expensetracker.perfectomobile.com/#/app-login");
			reportiumClient.reportiumAssert("Login page loading : validating : Check for Green/Red flag in Report ", wait.until(ExpectedConditions.invisibilityOfElementLocated(AppiumBy.xpath("(//h1//*[text()=\"No internet\"])")))); 
			
			CustomEvent event = new CustomEvent();
			event.setEventName("onLoginScreen");
			event.setVendor("Perfecto_ExpenseTracker");
			driver.logEvent(event);

			*/
			
			
			driver.get("http://expensetracker.perfectomobile.com/#/app-login");
			
			
			//here we are using visibilityOfElementLocated & AppiumBy
			
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@id='login_email']/input)")));
			
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@id='login_email'])"))).sendKeys("test@perfecto.com");
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@id='login_password'])"))).sendKeys("test123");
			
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@id='login_login_btn'])"))).click();
			
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
			
			reportiumClient.stepStart("<Add Expense> Elements which is shadow root element");
			WebElement listShadowParent3 = ((RemoteWebDriver)driver).findElement(By.cssSelector("body > app-root > ion-app > ion-split-pane > ion-menu > ion-content > ion-list > ion-menu-toggle:nth-child(2) > ion-item"));
			listShadowParent3.click();
			reportiumClient.reportiumAssert("clicked on shadow root element which <Add Expense> element", true);
			Thread.sleep(2000);
			

			try {
				
//				Set<String> contextNames = ((AndroidDriver) driver).getContextHandles();
//				System.out.println("Contexts are : " + contextNames.toArray()[0] + " " + contextNames.toArray()[1]);
//				((AndroidDriver) driver).context("WEBVIEW_1"); // NATIVE_APP, WEBVIEW_1
				
				WebElement listShadowParent5 = ((RemoteWebDriver)driver).findElement(By.xpath("//*[@class=\"ng-untouched ng-pristine ng-valid ion-untouched ion-pristine ion-valid ion-color ion-color-secondary in-item hydrated\"]"));
				
				reportiumClient.reportiumAssert("clicked on shadow root element which <Sliding Button> element", true);
				Thread.sleep(2000);
			
				//wait.until(presenceOfElementLocated(listShadowParent5));
		        //driver.findElement(listShadowParent5).click();
		        //wait.until(presenceOfElementLocated(MobileBy.AccessibilityId("slider")));
		        //MobileElement slider = driver.findElement(listShadowParent5);
	
		        Point source = listShadowParent5.getLocation();
		        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		        Sequence sequence = new Sequence(finger, 1);
		        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.x + 50, source.y));
		        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
		        sequence.addAction(new Pause(finger, Duration.ofMillis(600)));
		        sequence.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), source.x + 300, source.y));
		        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
		        ((RemoteWebDriver)driver).perform(Arrays.asList(sequence));
		        //com.perfectomobile.selenium.server.PerfectoMobileDriver cannot be cast to org.openqa.selenium.remote.RemoteWebDriver
//				((AndroidDriver)driver).context("NATIVE_APP"); // NATIVE_APP, WEBVIEW_1
				
			} catch (Exception e) {
				;
			}
			
			
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@id=\"add_head\"])"))).click();
			//*[@id="add_head"]
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@class=\"alert-radio-label sc-ion-alert-md\" and text()=\"Flight\"])"))).click();
			//*[@class="alert-radio-label sc-ion-alert-md" and text()="Flight"]
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@class=\"alert-button-group sc-ion-alert-md\"]/button[2])"))).click();
			//*[@class="alert-button-group sc-ion-alert-md"]/button[2]
			
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@id=\"add_amount\"])"))).sendKeys("400");
			//*[@id="add_amount"] --> 400
			
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@id=\"add_currency\"])"))).click();
			//*[@id="add_currency"]
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@class=\"alert-radio-label sc-ion-alert-md\" and text()=\" SGD - S$ \"])"))).click();
			//*[@class="alert-radio-label sc-ion-alert-md" and text()=" SGD - S$ "]
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@class=\"alert-button-group sc-ion-alert-md\"]/button[2])"))).click();
			//*[@class="alert-button-group sc-ion-alert-md"]/button[2]
			
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@id=\"add_category\"])"))).click();
			//*[@id="add_category"]
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@class=\"alert-radio-label sc-ion-alert-md\" and text()=\" Business \"])"))).click();
			//*[@class="alert-radio-label sc-ion-alert-md" and text()=" Business "]
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@class=\"alert-button-group sc-ion-alert-md\"]/button[2])"))).click();
			//*[@class="alert-button-group sc-ion-alert-md"]/button[2]
			
			//swipe vertical 
		  //*[@class="sc-ion-card-md-h sc-ion-card-md-s hydrated"]
		    WebElement slider = driver.findElement(AppiumBy.xpath("//*[@class=\"ng-touched ng-dirty ng-valid\"]"));
	        Point source = slider.getRect().getPoint(); //slider.getCenter();
	        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        Sequence sequence = new Sequence(finger, 1);
	        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0),
	                PointerInput.Origin.viewport(),
	                source.x / 2, source.y + 400));
	        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
	        sequence.addAction(finger.createPointerMove(Duration.ofMillis(600),
	                PointerInput.Origin.viewport(), source.getX() / 2, source.y / 2));
	        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
	        driver.perform(Arrays.asList(sequence));
		    
		    wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//*[@id=\"add_save_btn\"])"))).click();
			
		    Thread.sleep(2000);
		    WebElement listShadowParent7 = ((RemoteWebDriver)driver).findElement(AppiumBy.xpath("//ion-item-sliding"));
			
			reportiumClient.reportiumAssert("clicked on shadow root element which <Sliding Expense Button> element", true);
			Thread.sleep(2000);
		
			//wait.until(presenceOfElementLocated(listShadowParent5));
	        //driver.findElement(listShadowParent5).click();
	        //wait.until(presenceOfElementLocated(MobileBy.AccessibilityId("slider")));
	        //MobileElement slider = driver.findElement(listShadowParent5);

	        source = listShadowParent7.getLocation();
	        finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
	        sequence = new Sequence(finger, 1);
	        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.x, source.y));
	        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
	        sequence.addAction(new Pause(finger, Duration.ofMillis(600)));
	        sequence.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), source.x + 50, source.y));
	        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
	        ((RemoteWebDriver)driver).perform(Arrays.asList(sequence));
			
			
			// reportiumClient.testStep("step1"); // this is a logical step for reporting
			// add commands...
			// reportiumClient.testStep("step2");
			// more commands...
			Thread.sleep(2000);
	        
			reportiumClient.stepStart("<Back button> Elements which is shadow root element");
			WebElement listShadowParent6 = ((RemoteWebDriver)driver).findElement(By.cssSelector("body > app-root > ion-app > ion-split-pane > ion-router-outlet > app-expense > ion-header > ion-toolbar > ion-buttons > ion-back-button > button"));
			listShadowParent6.click();
			
			reportiumClient.reportiumAssert("clicked on shadow root element which <Back Button> element", true);
			
	        ShadowDomUtils shadowUtls2 = new ShadowDomUtils();
			//document.querySelector("").shadowRoot.querySelector("").click() 
			reportiumClient.stepStart("<Menu> Elements which is shadow root element");
			WebElement listShadowParent2 = ((RemoteWebDriver)driver).findElement(By.cssSelector("body > app-root > ion-app > ion-split-pane > ion-router-outlet > app-expense-list > ion-header > ion-toolbar > ion-buttons > ion-menu-button"));
			Map<String, Object> params3 = new HashMap<>();
			params3.put("parentElement", listShadowParent2);
			params3.put("innerSelector", "ion-menu-toggle > button");
			//String str = shadowUtls.getTextShadowDOM(((RemoteWebDriver)driver), params3);
			shadowUtls2.clickElementShadowDOM(((RemoteWebDriver)driver), params3);
			
			reportiumClient.reportiumAssert("clicked on shadow root element which <Menu> element", true);
			Thread.sleep(2000);
	        
			reportiumClient.stepStart("<Logout> Elements which is shadow root element");
			WebElement listShadowParent4 = ((RemoteWebDriver)driver).findElement(By.cssSelector("body > app-root > ion-app > ion-split-pane > ion-menu > ion-content > ion-list > ion-menu-toggle:nth-child(4) > ion-item"));
			listShadowParent4.click();
			
			reportiumClient.reportiumAssert("clicked on shadow root element which <Logout> element", true);
			Thread.sleep(2000);
			
	        
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

