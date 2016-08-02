/*Following test showcase how to set various network conditions like 2G, 3G, 4G
and measure the app response*/

package com.networkPreference.tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.perfecto.utilities.Utilities;
import com.perfecto.utilities.WindTunnelUtils;

import io.appium.java_client.AppiumDriver;

public class NetworkPreferencesTest {

	AppiumDriver<WebElement> driver;

	@Parameters({ "platformName", "deviceId", "androidAppPackage", "iOSBundleID", "networkProfile" })
	@BeforeTest
	public void beforeTest(String platformName, String deviceId, String androidAppPackage, String iOSBundleID,
			String networkProfile) throws IOException {
		
		//Get the driver instance
		driver = Utilities.getDriver(platformName, deviceId, androidAppPackage, iOSBundleID);

		// Start network virtualization
		Utilities.startNetworkVirtualization(driver, networkProfile);

	}

	@Parameters({"networkProfile" })
	@Test
	public void networkPreferenceTest(String networkProfile) {
		
		//Click on the authorization dismiss button
		try {
			driver.findElement(By.xpath("//*[@name='AUTHDismiss']")).click();
		} catch (Exception NoSuchElementException) {
		}

		//Click on the find hotels button
		driver.findElement(By.xpath("//*[@label='Find Hotels']")).click();
		
		//Put a text checkpoint for Work "Unlock" which appears only after all hotels are displayed		
		//Get the UX Timer
		//Wait until we get the word "Unlock"
		Utilities.ocrTextCheck(driver, "Unlock", -1, 120);
		
		//Start the uxtimer
		//Maximum threshold is 10 seconds - Under which we need the results
		long uxTimer = Utilities.getUXTimer(driver);
		System.out.println("Time took to find hotels (in milliseconds) - " +uxTimer+" - on "+networkProfile);
		Utilities.reportTimer(driver, uxTimer, 10000, "Time taken to load hotels", "FindHotelTimer");
		
		driver.context("NATIVE_APP");
		driver.findElementByXPath("//*[@label=\"Find Hotels\"]").click();
		
	}

	@AfterTest
	public void afterClass() {

		// Stop network virtualization
		Utilities.stopNetworkVirtualization(driver);

		  // Retrieve the URL of the Wind Tunnel Report, can be saved to your execution summary and used to download the report at a later point
        String reportURL = (String)(driver.getCapabilities().getCapability(WindTunnelUtils.WIND_TUNNEL_REPORT_URL_CAPABILITY));
        System.out.println("Link to WindTunnel Report - "+reportURL);
		// Close App
		driver.closeApp();

		// Quit the session
		driver.quit();
	}
}
