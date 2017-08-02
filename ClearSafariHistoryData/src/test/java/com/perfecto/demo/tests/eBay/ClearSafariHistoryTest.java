package com.perfecto.demo.tests.eBay;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

public class ClearSafariHistoryTest {

	AppiumDriver<WebElement> driver;

	@Test
	public void loginWithInvalidCredentials() throws InterruptedException {

		safariClearHistoryCookies(driver);

	}

	@Parameters({ "deviceID" })
	@BeforeTest
	public void beforeTest(String deviceID) throws MalformedURLException {
		driver = getAppiumDriver(deviceID);
	}

	@Parameters({ "deviceID" })
	@AfterTest
	public void afterTest(String deviceID) throws IOException {
		// Utilities.downloadReport(driver, "html", "FP_" + deviceID);
		driver.close();
		driver.quit();
	}

	public void safariClearHistoryCookies(AppiumDriver<WebElement> driver) {

		// Click on Home button
		deviceHome(driver);

		// Close settings if its running
		closeApplication(driver, "Settings");

		// Start settings
		openApplication(driver, "Settings");

		// Switch the context to Native
		switchToContext(driver, "NATIVE_APP");

		// Check if the control is in another option - by checking whether back
		// button is displayed - Click on Back 2 times depending on where in the
		//settings the control is present - For example if the settings open in 
		//Software update, then we have to click twice on Back to come to Settings
		//main screen
		try {
			driver.findElement(By.name("Back")).click();
		} catch (Exception NoSuchElementException) {
		}
		try {
			driver.findElement(By.name("Back")).click();
		} catch (Exception NoSuchElementException) {
		}

		// Click on Safari
		try {
			driver.findElement(By.xpath("//UIATableCell[@name='Safari']")).click();
		} catch (Exception NoSuchElementException) {
			driver.scrollToExact("Safari");
			driver.findElement(By.xpath("//UIATableCell[@name='Safari']")).click();
		}

		// Click on Clear history and website data
		try {
			driver.findElement(By.xpath("//UIATableCell[@name='Clear History and Website Data']")).click();
		} catch (Exception NoSuchElementException) {
			driver.scrollToExact("Clear History and Website Data");
			driver.findElement(By.xpath("//UIATableCell[@name='Clear History and Website Data']")).click();
		}

		// Click on Clear History and Data only if its enabled
		if (driver.findElement(By.name("Clear History and Data")).getAttribute("enabled").equals("true"))
			driver.findElement(By.name("Clear History and Data")).click();

		// Close settings
		closeApplication(driver, "Settings");

	}

	public static void deviceHome(RemoteWebDriver driver2) {
		System.out.println("hitting device HOME key");
		Map<String, Object> params1 = new HashMap<String, Object>();
		driver2.executeScript("mobile:handset:ready", params1);
	}

	public static void openApplication(RemoteWebDriver driver, String AppName) {
		System.out.println("opening application: " + AppName);
		String command = "mobile:application:open";
		Map<String, Object> Parms = new HashMap<String, Object>();
		Parms.put("name", AppName);
		driver.executeScript(command, Parms);
	}

	public static void switchToContext(RemoteWebDriver driver, String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}

	public static void closeApplication(RemoteWebDriver driver, String AppName) {
		System.out.println("closing application: " + AppName);
		String command = "mobile:application:close";
		Map<String, Object> Parms = new HashMap<String, Object>();
		Parms.put("name", AppName);
		driver.executeScript(command, Parms);
	}

	public static AppiumDriver<WebElement> getAppiumDriver(String deviceId) throws MalformedURLException {

		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);

		capabilities.setCapability("user", "<Your_Perfecto_Cloud_User>");
		capabilities.setCapability("password", "<Your_Perfecto_Cloud_Password>");
		capabilities.setCapability("deviceName", deviceId);
		AppiumDriver<WebElement> webdriver = new IOSDriver<WebElement>(
				new URL("https://" + "partners.perfectomobile.com" + "/nexperience/perfectomobile/wd/hub"),
				capabilities);

		return webdriver;

	}
}
