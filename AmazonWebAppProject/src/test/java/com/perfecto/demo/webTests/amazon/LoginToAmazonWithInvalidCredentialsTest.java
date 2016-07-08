package com.perfecto.demo.webTests.amazon;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.perfecto.demo.pageObjects.amazon.HomeScreenPageObject;
import com.perfecto.demo.pageObjects.amazon.LoginScreenPageObject;
import com.perfecto.demo.utils.Utilities;
import com.perfecto.demo.utils.WindTunnelUtils;

public class LoginToAmazonWithInvalidCredentialsTest {
	RemoteWebDriver driver;
	HomeScreenPageObject homeScreenPageObject;
	LoginScreenPageObject loginScreenPageObject;

	@Test
	public void loginWithInvalidCredentials() throws InterruptedException {

		// Click on the sign in button or link
		homeScreenPageObject.signInButtonClick();

		// Enter invalid credentials
		loginScreenPageObject.loginAmazon("test123@amazon.com", "abd123#");

		// Verify error message
		loginScreenPageObject.assertErrorMessage();
	}

	@Parameters({ "platformName", "platformVersion", "browserName", "browserVersion", "deviceId" })
	@BeforeTest
	public void beforeTest(String platformName, String platformVersion, String browserName, String browserVersion,
			String deviceId) throws MalformedURLException {

		// Creating driver instance
		driver = Utilities.getRemoteWebDriver(platformName, platformVersion, browserName, browserVersion, deviceId);

		// Page Objects instantiation
		homeScreenPageObject = new HomeScreenPageObject();
		loginScreenPageObject = new LoginScreenPageObject();
		PageFactory.initElements(driver, homeScreenPageObject);
		PageFactory.initElements(driver, loginScreenPageObject);

		// Maximize browser in case of desktop browser
		if (deviceId.isEmpty())
			driver.manage().window().maximize();

		// Launch Amazon site
		driver.get("http://www.amazon.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Parameters({ "deviceId" })
	@AfterTest
	public void afterTest(String deviceId) throws IOException {
		
		// Retrieve the URL of the Single Test Report, can be saved to your execution summary and used to download the report at a later point
        String reportURL = (String)(driver.getCapabilities().getCapability(WindTunnelUtils.SINGLE_TEST_REPORT_URL_CAPABILITY));
        System.out.println("Execution report URL - "+reportURL);
        
		// Close Driver
		driver.close();
		System.out.println("Driver Closed");

		// Fix for the download report issues, closing the WM as mobile so user
		// can still download the report...
		Map<String, Object> params = new HashMap<String, Object>();
		driver.executeScript("mobile:execution:close", params);

		// Quit the driver
		driver.quit();
	}

}
