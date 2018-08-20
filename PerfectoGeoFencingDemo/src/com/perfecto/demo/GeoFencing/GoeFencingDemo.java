package com.perfecto.demo.GeoFencing;
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
import com.sun.istack.internal.Nullable;

/**
 * This template is for users that use DigitalZoom Reporting (ReportiumClient).
 * For any other use cases please see the basic template at https://github.com/PerfectoCode/Templates.
 * For more programming samples and updated templates refer to the Perfecto Documentation at: http://developers.perfectomobile.com/
 */
public class GoeFencingDemo {
	public static AppiumDriver driver = null;
	public static void main(String[] args) throws MalformedURLException, IOException {
		System.out.println("Run started");

		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
		
		String host = "MyHost.perfectomobile.com";
		capabilities.setCapability("user", "user@perfectomobile.com");
		capabilities.setCapability("securityToken", "tokenNeedToBeAddedHere");

		//TODO: Change your device ID 
		capabilities.setCapability("deviceName", "");
		capabilities.setCapability("outputVideo", false);
		//capabilities.setCapability("autoInstrument", false);
		// Use the automationName capability to define the required framework - Appium (this is the default) or PerfectoMobile.
		capabilities.setCapability("automationName", "Appium");

		// Call this method if you want the script to share the devices with the Perfecto Lab plugin.
		PerfectoLabUtils.setExecutionIdCapability(capabilities, host);

		// Application settings examples.
		//capabilities.setCapability("app", "PRIVATE:applications/Errands.apk");
		// For Android: //geofence.killerrech.com.GeoAlert , //com.arpacell.fencer
		capabilities.setCapability("appPackage", "geofence.killerrech.com.GeoAlert");
		//capabilities.setCapability("appActivity", ".activities.BrowseActivity");
		// For iOS:
		// capabilities.setCapability("bundleId", "com.yoctoville.errands");

		// Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
		//capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);

		// Name your script
		capabilities.setCapability("scriptName", "GeoFencingPerfectoDemoTest");

		driver = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
		// IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
		PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
				.withProject(new Project("My Project", "1.0"))
				.withJob(new Job("My Job", 45))
				.withContextTags("GeoFenceDemo")
				.withWebDriver(driver)
				.build();
		ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);

		try {
			reportiumClient.testStart("GeoFencingPerfectoDemoTest", new TestContext("GeoFenceDemo", "GeoFencingPerfectoDemoTest"));

			// write your code here

			try {
				driver.context("NATIVE_APP");
				driver.findElementByXPath("//*[@resource-id=\"com.android.packageinstaller:id/permission_allow_button\"]").click();
			} catch (Exception e3) {
				;
			}			

			Thread.sleep(5000);
			//inject location "ITPL, Bangalore, Karnataka, India"
			setDeviceLocationByAddress(driver, "ITPL, Bangalore, Karnataka, India");
			Thread.sleep(8000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/material_design_android_floating_action_menu\"]//*[@class=\"android.widget.ImageButton\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/material_design_floating_add_geofence\"]").click();
			Thread.sleep(2000);

			try {
				driver.context("NATIVE_APP");
				driver.findElementByXPath("//*[@text=\"GeoAlert\"]").click();
				Thread.sleep(2000);
			} catch (Exception e2) {
				;
			}

			/*driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@text=\"Search\"]").sendKeys("1, Markley Way, Lowell, MA 01852, USA");*/

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@text=\"Search\"]").click();
			Thread.sleep(2000);

			/*driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"com.google.android.gms:id/edit_text\"]").sendKeys("Lowell, MA 01852, USA");*/

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"com.google.android.gms:id/edit_text\"]").sendKeys("Whitefield, Bangalore, Karnataka, India");
			Thread.sleep(4000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"com.google.android.gms:id/list\"]/android.widget.RelativeLayout[1]").click();
			Thread.sleep(4000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"com.google.android.gms:id/confirm_button\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/input_geoname\"]").sendKeys("WhitefieldGeoFenceLocation");
			Thread.sleep(4000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/confirm_button\"]").click();
			Thread.sleep(8000);

			driver.context("NATIVE_APP");
			try {
				driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/switch_toggle\" and @checked=\"false\"]").click();
			} catch (Exception e) {
				// xpath = //*[@resource-id="geofence.killerrech.com.GeoAlert:id/switch_toggle" and (@checked="false" or @checked="true")]
				try {
					driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/switch_toggle\" and @checked=\"true\"]").isDisplayed();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/switchNoti\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/chekNofiEnter\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/chekNotiexit\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/switchAlwaram\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/chekAlaramEntry\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/chekAlaramExit\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/imgBtnChangeProfileIn\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@text=\"Meeting\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"android:id/button1\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/imgBtnChangeProfileOut\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@text=\"General\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"android:id/button1\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/fab\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/planList\"]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.CheckBox[1]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/planList\"]/android.widget.FrameLayout[3]/android.widget.RelativeLayout[1]/android.widget.CheckBox[1]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/planList\"]/android.widget.FrameLayout[6]/android.widget.RelativeLayout[1]/android.widget.CheckBox[1]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/selectButton\"]").click();

			Thread.sleep(5000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@content-desc=\"Navigate up\"]").click();
			Thread.sleep(4000);

			Thread.sleep(3000);
			//inject location "Whitefield, Bangalore, Karnataka, India"
			setDeviceLocationByAddress(driver, "Whitefield, Bangalore, Karnataka, India");
			Thread.sleep(20000);

			/*//inject location "Whitefield, Bangalore, Karnataka, India"
			setDeviceLocationByAddress(driver, "Hope Form, Whitefield, Bangalore, Karnataka, India");
			Thread.sleep(20000);
			//inject location "Whitefield, Bangalore, Karnataka, India"
			setDeviceLocationByAddress(driver, "Whitefield, Bangalore, Karnataka, India");
			Thread.sleep(20000);*/

			boolean exittestingflag = false;

			try {
				driver.context("NATIVE_APP");
				String attribute91 = driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/textView2\"]").getAttribute("text");
				System.out.println("Messages for exit is : " + attribute91);
				//Hey! you have been Entered WhitefieldGeoFenceLocation geofence. You can see nearBy places by clicking on location icon below.
				Thread.sleep(4000);
			} catch (Exception e) {

				//now need to cover exit part as entering is not provided notification popup page

				//inject location "Hope Form, Whitefield, Bangalore, Karnataka, India"
				setDeviceLocationByAddress(driver, "Hope Form, Whitefield, Bangalore, Karnataka, India");
				Thread.sleep(20000);

				driver.context("NATIVE_APP");
				//List<WebElement> elements103 = driver.findElementsByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/textView2\"]");
				String  attribute92 = driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/textView2\"]").getAttribute("text");
				System.out.println("Messages for exit is : " + attribute92);
				//You have Exited WhitefieldGeoFenceLocation. Press cancel to stop Alarm.
				Thread.sleep(4000);


				driver.context("NATIVE_APP");
				driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/cross_fab\"]").click();
				Thread.sleep(4000);

				//notification bring now.
				Map<String, Object> params31 = new HashMap<>();
				Object result31 = driver.executeScript("mobile:notifications:open", params31);
				Thread.sleep(5000);

				driver.context("NATIVE_APP");
				List<WebElement> elements105 = driver.findElementsByXPath("//*[@text=\"WhitefieldGeoFenceLocation\"]/../..//*[@text=\"Exited\"]");
				System.out.println("Notification showed as Exited");

				Thread.sleep(2000);

				Map<String, Object> params1171 = new HashMap<>();
				params1171.put("keySequence", "HOME");
				Object result1171 = driver.executeScript("mobile:presskey", params1171);
				Thread.sleep(5000);
				driver.launchApp();			
				Thread.sleep(10000);

				exittestingflag = true;
				
				//inject location "Whitefield, Bangalore, Karnataka, India"
				setDeviceLocationByAddress(driver, "Whitefield, Bangalore, Karnataka, India");
				Thread.sleep(20000);

				driver.context("NATIVE_APP");
				String attribute91 = driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/textView2\"]").getAttribute("text");
				System.out.println("Messages for exit is : " + attribute91);
				//Hey! you have been Entered WhitefieldGeoFenceLocation geofence. You can see nearBy places by clicking on location icon below.
				Thread.sleep(4000);
				
			}

						
			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/cross_fab\"]").click();
			Thread.sleep(4000);


			Map<String, Object> params2 = new HashMap<>();
			Object result2 = driver.executeScript("mobile:notifications:open", params2);
			Thread.sleep(5000);

			driver.context("NATIVE_APP");
			List<WebElement> elements96 = driver.findElementsByXPath("//*[@text=\"WhitefieldGeoFenceLocation\"]/../..//*[@text=\"Entered\"]");
			System.out.println("Notification showed as Entered");
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("(//*[@resource-id=\"com.android.systemui:id/clear_all_button_label\"] | //*[@resource-id=\"com.android.systemui:id/clear_all\"])").click();
			Thread.sleep(2000);

			Map<String, Object> params117 = new HashMap<>();
			params117.put("keySequence", "HOME");
			Object result117 = driver.executeScript("mobile:presskey", params117);
			Thread.sleep(5000);
			driver.launchApp();			
			Thread.sleep(10000);

			if(!exittestingflag) {
				//inject location "Hope Form, Whitefield, Bangalore, Karnataka, India"
				setDeviceLocationByAddress(driver, "Hope Form, Whitefield, Bangalore, Karnataka, India");
				Thread.sleep(20000);

				driver.context("NATIVE_APP");
				//List<WebElement> elements103 = driver.findElementsByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/textView2\"]");
				String  attribute92 = driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/textView2\"]").getAttribute("text");
				System.out.println("Messages for exit is : " + attribute92);
				//You have Exited WhitefieldGeoFenceLocation. Press cancel to stop Alarm.
				Thread.sleep(4000);


				driver.context("NATIVE_APP");
				driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/cross_fab\"]").click();
				Thread.sleep(4000);

				//notification bring now.
				Map<String, Object> params31 = new HashMap<>();
				Object result31 = driver.executeScript("mobile:notifications:open", params31);
				Thread.sleep(5000);

				driver.context("NATIVE_APP");
				List<WebElement> elements105 = driver.findElementsByXPath("//*[@text=\"WhitefieldGeoFenceLocation\"]/../..//*[@text=\"Exited\"]");
				System.out.println("Notification showed as Exited");

				Thread.sleep(2000);

				driver.context("NATIVE_APP");
				driver.findElementByXPath("(//*[@resource-id=\"com.android.systemui:id/clear_all_button_label\"] | //*[@resource-id=\"com.android.systemui:id/clear_all\"])").click();
				Thread.sleep(2000);

				Map<String, Object> params1171 = new HashMap<>();
				params1171.put("keySequence", "HOME");
				Object result1171 = driver.executeScript("mobile:presskey", params1171);
				Thread.sleep(5000);
				driver.launchApp();			
				Thread.sleep(10000);

			}

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/menu_nearby\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/mDelet\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"android:id/button1\"]").click();
			Thread.sleep(2000);

			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@resource-id=\"geofence.killerrech.com.GeoAlert:id/toolbar\"]//*[@class=\"android.widget.ImageButton\"]").click();
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
				driver.closeApp();
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

	public static void setDeviceLocationByAddress(RemoteWebDriver driver, String address){
		Map<String, Object> params = new HashMap<>();
		params.put("address", address);
		driver.executeScript("mobile:location:set", params); 
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

	/**
	 * Generates an external voice call recording to the selected destination
	 * It is possible to select multiple destinations that may include devices, users, and phone numbers.
	 * There is no default. To use, at least one destination must be selected
	 * 
	 * Parameters : DeviceID    : The destination device. It is possible to select multiple devices (separated by commas).
	 * Parameters : PhoneNumber : The destination phone number. It is possible to select multiple phone numbers
	 * Parameters : UserID      : The destination user. It is possible to select multiple users. The user identifier is the Perfecto Mobile username.
	 * Parameters : Logical     : The user currently running the script.
	 * Parameters : Return type int : 1=Success, 0=Failed, -1=All parameter are passed as NULL value, atleast one value should be passed to function.
	 * 
	 * For more info go to : 
	 *
	 */
	public static int callCloudServices(AppiumDriver driver, 
			@Nullable String DeviceID, @Nullable String PhoneNumber,
			@Nullable String UserID, @Nullable String Logical) throws Exception{
		int ErrorCode = -2;
		System.out.println("DeviceID:"+DeviceID);
		System.out.println("PhoneNumber:"+PhoneNumber);
		System.out.println("UserID:"+UserID);
		System.out.println("Logical:"+Logical);

		try {
			Map<String, Object> params1 = new HashMap<>();
			if(null != Logical)
			{
				params1.put("to.logical", Logical);
			}else if(null != UserID)
			{
				params1.put("to.user", UserID);
			}else if(null != PhoneNumber)
			{
				params1.put("to.number", PhoneNumber);
			}else if(null != DeviceID)
			{
				params1.put("to.handset", DeviceID);
			}else{
				return ErrorCode = -1;
			}

			Object result = driver.executeScript("mobile:gateway:call", params1);
			Thread.sleep(15000);
			return ErrorCode = 1;
		} catch (Exception e) {
			return ErrorCode = 0;
		}
	}

	/**
	 * Generates an external SMS to the selected destination
	 * It is possible to select multiple destinations that may include devices, users, and phone numbers.
	 * There is no default. To use, at least one destination must be selected
	 * 
	 * Parameters : DeviceID    : The destination device. It is possible to select multiple devices (separated by commas).
	 * Parameters : PhoneNumber : The destination phone number. It is possible to select multiple phone numbers
	 * Parameters : UserID      : The destination user. It is possible to select multiple users. The user identifier is the Perfecto Mobile username.
	 * Parameters : Logical     : The user currently running the script.
	 * Parameters : Return type int : 1=Success, 0=Failed, -1=All parameter are passed as NULL value, atleast one value should be passed to function.
	 * 
	 * For more info go to : 
	 *
	 */

	public static int smsCloudServices(AppiumDriver driver, 
			@Nullable String DeviceID, @Nullable String PhoneNumber,
			@Nullable String UserID, @Nullable String Logical, 
			@Nullable String MessageContent) throws Exception{
		int ErrorCode = -2;
		System.out.println("DeviceID:"+DeviceID);
		System.out.println("PhoneNumber:"+PhoneNumber);
		System.out.println("UserID:"+UserID);
		System.out.println("Logical:"+Logical);

		try {
			Map<String, Object> params1 = new HashMap<>();
			params1.put("body", MessageContent);

			if(null != Logical)
			{
				params1.put("to.logical", Logical);
			}else if(null != UserID)
			{
				params1.put("to.user", UserID);
			}else if(null != PhoneNumber)
			{
				params1.put("to.number", PhoneNumber);
			}else if(null != DeviceID)
			{
				params1.put("to.handset", DeviceID);
			}else{
				return ErrorCode = -1;
			}

			Object result = driver.executeScript("mobile:gateway:sms", params1);
			Thread.sleep(15000);
			return ErrorCode = 1;
		} catch (Exception e) {
			return ErrorCode = 0;
		}
	}
}
