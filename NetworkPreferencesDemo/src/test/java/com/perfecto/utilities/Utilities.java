package com.perfecto.utilities;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Utilities {

	public static String REPORT_LIB = "C:\\Users\\sreevatsa\\Desktop\\Perfecto\\Automation\\TestReports";
	public static String SCREENSHOTS_LIB = "C:\\Users\\sreevatsa\\Desktop\\Perfecto\\Automation\\TestReports\\Screenshots";
	public static final String REPORT_TIMER_RESULT = "result";
	public static final String REPORT_TIMER_THRESHOLD = "threshold";
	public static final String REPORT_TIMER_DESCRIPTION = "description";
	public static final String REPORT_TIMER_NAME = "name";
	public static final String MOBILE_STATUS_TIMER_COMMAND = "mobile:status:timer";

	// To create an appium driver instance depending on mobile OS
	public static AppiumDriver<WebElement> getDriver(String platformName, String deviceId, String androidAppPackage,
			String iOSBundleID) throws IOException {

		AppiumDriver<WebElement> driver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);

		capabilities.setCapability("user", "<Perfecto_UserID>");
		capabilities.setCapability("password", "<Perfecto_UserPassword");
		capabilities.setCapability("deviceName", deviceId);
		capabilities.setCapability("automationName", "appium");
		
		   // Call this method if you want the script to share the devices with the Perfecto Lab plugin.
        //PerfectoLabUtils.setExecutionIdCapability(capabilities, "partners.perfectomobile.com");


		if (platformName.equalsIgnoreCase("android")) {
			capabilities.setCapability("app-activity", androidAppPackage);
			capabilities.setCapability("appPackage", androidAppPackage);
			driver = new AndroidDriver<WebElement>(
					new URL("https://partners.perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);
		} else if (platformName.equalsIgnoreCase("ios")) {
			capabilities.setCapability("bundleId", iOSBundleID);
			driver = new IOSDriver<WebElement>(
					new URL("https://partners.perfectomobile.com/nexperience/perfectomobile/wd/hub"), capabilities);
		}

		return driver;

	}

	// To start network virtualization
	public static void startNetworkVirtualization(AppiumDriver<WebElement> driver, String networkProfile) {
		Map<String, Object> params = new HashMap<>();
		params.put("profile", networkProfile);
		driver.executeScript("mobile:vnetwork:start", params);
	}

	// To stop network virtualization
	public static void stopNetworkVirtualization(AppiumDriver<WebElement> driver) {
		Map<String, Object> params = new HashMap<>();
		driver.executeScript("mobile:vnetwork:stop", params);
	}
	
	//To perform OCR Find
	public static void ocrTextFind(AppiumDriver<WebElement> driver, String textToBeFound)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("content", textToBeFound);
		params.put("timeout", "120");
		driver.executeScript("mobile:text:find", params);
	}
	
	// Perform text check ocr function
	public static String ocrTextCheck(AppiumDriver<WebElement> driver, String text, int threshold, int timeout) {
		// Verify that arrived at the correct page, look for the Header Text
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("content", text);
		params.put("timeout", Integer.toString(timeout));
		params.put("measurement", "accurate");
		params.put("source", "camera");
		params.put("analysis", "automatic");
		if (threshold > 0) {
			params.put("threshold", Integer.toString(threshold));
		}
		String result = (String) driver.executeScript("mobile:checkpoint:text", params);
		return result;
	}
	
	// Returns ux timer
	// Wind Tunnel: Gets the requested timer
	public static long timerGet(AppiumDriver<WebElement> driver, String timerType) {
		String command = "mobile:timer:info";
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", timerType);
		long result = (Long) driver.executeScript(command, params);
		return result;
	}
	public static long getUXTimer(AppiumDriver<WebElement> driver) {
		long timer = timerGet(driver, "ux");
		return timer;
	}
	
	public static String reportTimer(RemoteWebDriver driver, long result, long threshold, String description,
			String name) {
		Map<String, Object> params = new HashMap<String, Object>(7);
		params.put(REPORT_TIMER_RESULT, result);
		params.put(REPORT_TIMER_THRESHOLD, threshold);
		params.put(REPORT_TIMER_DESCRIPTION, description);
		params.put(REPORT_TIMER_NAME, name);
		String status = (String) driver.executeScript(MOBILE_STATUS_TIMER_COMMAND, params);
		return status;
	}
}
