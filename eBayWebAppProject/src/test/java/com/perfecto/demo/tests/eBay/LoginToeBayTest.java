package com.perfecto.demo.tests.eBay;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.perfecto.demo.pageObjects.eBay.HomeScreenPageObject;
import com.perfecto.demo.pageObjects.eBay.LoginScreenPageObject;
import com.perfecto.demo.utils.Utilities;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginToeBayTest {
	RemoteWebDriver driver;
	HomeScreenPageObject homeScreenPageObject;
	LoginScreenPageObject loginScreenPageObject;

	@Test
	public void loginWithInvalidCredentials() throws InterruptedException {

		//Click on the user icon
		homeScreenPageObject.userIconClick();
		
		//Enter invalid credentials
		loginScreenPageObject.logineBay();
		
		//Verify error message
		loginScreenPageObject.checkErrorMessage();
			}

	@Parameters({ "deviceID" })
	@BeforeTest
	public void beforeTest(String deviceID) throws MalformedURLException {
		driver = Utilities.getAppiumDriver(deviceID);
		homeScreenPageObject = new HomeScreenPageObject();
		loginScreenPageObject = new LoginScreenPageObject();
		PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), homeScreenPageObject);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 5, TimeUnit.SECONDS), loginScreenPageObject);
		driver.get("http://www.ebay.com");
	}

	@Parameters({ "deviceID" })
	@AfterTest
	public void afterTest(String deviceID) throws IOException {
		//Utilities.stopApp("com.appflipkart.flipkart", driver);
		Utilities.downloadReport(driver, "html", "FP_" + deviceID);
		driver.close();
		driver.quit();	
	}	
}
