import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;

public class AppiumTest {

	public static void main(String[] args) throws MalformedURLException, IOException {
		System.out.println("Run started");

		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
		String host = "myhost.perfectomobile.com";
		capabilities.setCapability("user", "<Perfecto_Cloud_User>");
		capabilities.setCapability("password", "<Perfecto_Cloud_Password");

		// TODO: Change your device ID
		capabilities.setCapability("deviceName", "<Device_ID>");

		// Use the automationName capability to define the required framework -
		// Appium (this is the default) or PerfectoMobile.
		capabilities.setCapability("automationName", "Appium");

		// Call this method if you want the script to share the devices with the
		// Perfecto Lab plugin.
		PerfectoLabUtils.setExecutionIdCapability(capabilities, host);

		// For iOS:
		capabilities.setCapability("bundleId", "42S4GZUXGS.MultipleWebViews");

		// Name your script
		capabilities.setCapability("scriptName", "HybridAppTest");

		// AndroidDriver driver = new AndroidDriver(new URL("https://" + host +
		// "/nexperience/perfectomobile/wd/hub"), capabilities);
		IOSDriver driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"),
				capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		try {

			// Enter URL in the first field and click on Go
			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@label=\"address1\"]").sendKeys("http://www.google.co.in");
			driver.findElementByXPath("//*[@label=\"button_navigate1\"]").click();

			// Click on Return button on the keyboard
			driver.findElementByXPath("//*[@label=\"return\"]").click();

			// Context switch to WebView 1 where Google site is launched
			driver.context("WEBVIEW");

			// Search for Perfecto mobile and print the first link text
			driver.findElementByXPath("(//input[@id=\"lst-ib\"])[1]").sendKeys("Perfecto mobile");
			driver.findElementByXPath("(//button[@id=\"tsbb\"])[1]").click();
			String attribute7 = driver.findElementByXPath("//div[@class='card-section']/div/div/h3/a")
					.getAttribute("text");
			System.out.println(attribute7);

			// Enter URL in the second field and click on Go
			driver.context("NATIVE_APP");
			driver.findElementByXPath("//*[@label=\"address2\"]").sendKeys("http://www.ebay.com");
			driver.findElementByXPath("//*[@label=\"button_navigate2\"]").click();
			
			// Click on Return button on the keyboard
			driver.findElementByXPath("//*[@label=\"return\"]").click();
			
			// Context switch to WebView 2 where eBay site is launched
			driver.context("WEBVIEW_2");

			// Search for iPhone 6S and print the result count
			driver.findElementByXPath("(//input[@id=\"kw\"])[1]").sendKeys("iPhone 6S");
			driver.findElementByXPath("(//a[@id=\"searchTxtBtn\"])[1]").click();
			String resultCount = driver.findElementByXPath("(//div[@id=\"rsltCnt\"])[1]").getAttribute("text");
			System.out.println(resultCount);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Retrieve the URL of the Single Test Report, can be saved to
				// your execution summary and used to download the report at a
				// later point
				String reportURL = (String) (driver.getCapabilities()
						.getCapability(WindTunnelUtils.SINGLE_TEST_REPORT_URL_CAPABILITY));
				System.out.println(reportURL);

				// Close the App
				driver.closeApp();

			} catch (Exception e) {
				e.printStackTrace();
			}

			driver.quit();
		}

		System.out.println("Run ended");
	}
}
