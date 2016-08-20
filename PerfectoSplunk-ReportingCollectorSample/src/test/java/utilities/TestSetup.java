package utilities;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestSetup {

	private RemoteWebDriverExtended driver;
	private boolean device;
	private DesiredCapabilities capabilities = new DesiredCapabilities();
	private Library lib;
	private String target;
	public String host;
	private URL gridURL;

	public TestSetup(String targetEnvironment, RemoteWebDriverExtended driver) {
		this.target = targetEnvironment;
		this.driver = driver;
	}

	// sets capabilities based on environment returned from testNG
	public void flowControl() {
		switch (target) {
		case "iPad Mini":
			device = true;
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("model", "iPad Mini 2");
			capabilities.setCapability("browserName", "Safari");
			capabilities.setCapability("automationName", "PerfectoMobile");
			break;
		case "Galaxy S6":
			device = true;
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("model", "Galaxy S6");
			capabilities.setCapability("browserName", "Chrome");
			capabilities.setCapability("automationName", "PerfectoMobile");
			break;
		case "iPhone-6":
			device = true;
			capabilities.setCapability("platformName", "iOS");
			capabilities.setCapability("model", "iPhone-6");
			capabilities.setCapability("browserName", "");
			capabilities.setCapability("automationName", "PerfectoMobile");
			break;
		case "Galaxy Tab":
			device = true;
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("model", "SCH-I705 Galaxy Tab 2");
			capabilities.setCapability("browserName", "mobileChrome");
			capabilities.setCapability("automationName", "PerfectoMobile");
			break;
		case "Firefox":
			device = false;
			capabilities.setCapability("platform", Platform.ANY);
			capabilities.setCapability("browserName", "firefox");
			capabilities.setCapability("version", "");
			capabilities.setCapability("automationName", "PerfectoMobile");
			break;
		case "Chrome":
			device = false;
			capabilities.setCapability("platform", Platform.ANY);
			capabilities.setCapability("browserName", "chrome");
			capabilities.setCapability("version", "");
			break;
		case "Internet Explorer":
			device = false;
			capabilities.setCapability("platform", Platform.ANY);
			capabilities.setCapability("browserName", "internet explorer");
			capabilities.setCapability("version", "");
			capabilities.setCapability("automationName", "PerfectoMobile");
			break;
		default:
			lib.errorCleanup();
			break;
		}
	}

	// connects to selenium grid at perfecto as well as establishes
	// device connectivity to mobile cloud
	public Library driverAndLibrarySetup()
			throws UnsupportedEncodingException, MalformedURLException, InterruptedException {
		if (device) {
			host = "<yourHost>.perfectomobile.com";
			String user = "<yourUserName>";
			String password = "<yourPassword>";

			capabilities.setCapability("outputVisibility", "public");
			capabilities.setCapability("user", user);
			capabilities.setCapability("password", password);
			capabilities.setCapability("openDeviceTimeOut", 5);

			gridURL = new URL("https://" + host + "/nexperience/perfectomobile/wd/hub");
		} else {
			host = "<yourLocalGridHost>";
			gridURL = new URL("http://" + host + "/wd/hub");
		}

		try {
			driver = new RemoteWebDriverExtended(gridURL, capabilities);
		} catch (Exception ex) {
			System.out.println(ex.getMessage().toString());
			throw ex;
		}

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		lib = new Library(driver);

		return lib;
	}
}
