package com.perfecto.demo.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class Utilities {
	AppiumDriver<WebElement> driver;
	public static String REPORT_LIB = "C:\\Users\\sreevatsa\\Desktop\\Perfecto\\Automation\\TestReports";
	public static String SCREENSHOTS_LIB = "C:\\Users\\sreevatsa\\Desktop\\Perfecto\\Automation\\TestReports\\Screenshots";
	
	public static RemoteWebDriver getAppiumDriver(String deviceId) throws MalformedURLException {

		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
		String host = "partners.perfectomobile.com";
		capabilities.setCapability("user", "XXXXXXXXXXXX");
		capabilities.setCapability("password", "XXXXXXXXXXXXXXXX");
		capabilities.setCapability("deviceName", deviceId);
	      RemoteWebDriver webdriver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);


		return webdriver;

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

	@SuppressWarnings("unchecked")
	public static String getScreenShot(AppiumDriver<WebElement> driver, String name, String deviceID) {
		String screenShotName = SCREENSHOTS_LIB + name + "_" + deviceID + ".png";
		driver = (AppiumDriver<WebElement>) new Augmenter().augment(driver);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(screenShotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return screenShotName;
	}

	public static void downloadReport(RemoteWebDriver driver, String type, String fileName)
			throws IOException {
		try {
			String command = "mobile:report:download";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type", "html");
			String report = (String) driver.executeScript(command, params);
			File reportFile = new File(getReprtName(fileName, true));
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(reportFile));
			byte[] reportBytes = OutputType.BYTES.convertFromBase64Png(report);
			output.write(reportBytes);
			output.close();
		} catch (Exception ex) {
			System.out.println("Got exception " + ex);
		}
	}

	public static String getReprtName(String repID, boolean withPath) {
		if (withPath) {
			return REPORT_LIB + "/rep_" + repID + ".html";
		} else {
			return "/rep_" + repID + ".html";
		}
	}
}
