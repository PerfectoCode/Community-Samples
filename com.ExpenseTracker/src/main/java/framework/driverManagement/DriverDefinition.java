package framework.driverManagement;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverDefinition {

	private AppiumDriver<MobileElement> driver;
	private PerfectoExecutionContext perfectoExecutionContext;
	private ReportiumClient reportiumClient;

	private String Framework = "";
	private Boolean PerfectoDigitalZoomReportium = false;
	private Boolean Android = false;
	private Boolean iOS = false;

	//@BeforeSuite(alwaysRun=true)
	public Properties setupFramework() throws Exception {
		System.out.println("setupFramework\n");
		Properties prop = null;
		try (FileReader reader=new FileReader(System.getProperty("user.dir") + "/src/resources/application.properties")) {

			prop = new Properties();
			// load a properties file
			prop.load(reader);

			// get the property value and print it out
			/*System.out.println(prop.getProperty("Framework"));
			System.out.println(prop.getProperty("PerfectoDigitalZoomReportium"));
			System.out.println(prop.getProperty("Android"));
			System.out.println(prop.getProperty("iOS"));*/

			//assigning values to variables
			this.Framework = prop.getProperty("Framework");
			this.PerfectoDigitalZoomReportium = ((prop.getProperty("PerfectoDigitalZoomReportium").compareToIgnoreCase("true"))==0)? true:false;
			this.Android = ((prop.getProperty("Android").compareToIgnoreCase("true"))==0)? true:false;
			this.iOS = ((prop.getProperty("iOS").compareToIgnoreCase("true"))==0)? true:false;         

			System.out.println(this.Framework);
			System.out.println(this.PerfectoDigitalZoomReportium);
			System.out.println(this.Android);
			System.out.println(this.iOS);

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return prop;
	}

	@BeforeTest(alwaysRun = true)
	@Parameters({"platform","model","deviceID","port", "systemPort"})
	public AppiumDriver<MobileElement> creatDriver(
			String platform, @Optional String model, String deviceID, 
			@Optional String port, @Optional String systemPort) throws Exception{
		setupFramework();
		System.out.println("creatDriver\n");

		File file, fs = null;
		URL url = null;
		DesiredCapabilities caps = null;		

		switch(platform) {
		case "Android" : 
			file = new File("src\\resources");
			fs = new File(file, "ExpenseAppNative.apk");
			break;
		case "iOS" : 
			file = new File("src\\resources");
			fs = new File(file, "InvoiceApp1.0.ipa");
			break;
		}

		//Framework == Perfecto, Appium, Android, iOS
		//PerfectoDigitalZoomReportium==true
		//Android==true
		//iOS==true

		switch(this.Framework) {
		case "Appium" : //Common every thing on iOSand Android Apps for capabilities then use this for Appium local
			System.out.println("creatDriver : Appium\n");
			caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
			caps.setCapability(MobileCapabilityType.APPLICATION_NAME, "io.perfecto.expense.tracker");
			/*caps.setCapability("appPackage", "io.perfecto.expense.tracker");
			caps.setCapability("appActivity", "io.perfecto.expense.tracker.app.perfecto.com.expencemanager.ui.splash.SplashActivity");*/
			caps.setCapability(MobileCapabilityType.FULL_RESET, true);
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
			if(model!=null)
			{
				caps.setCapability("model", model);
			}
			/*switch(platform) {
			case "Android" : 
				caps.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
				break;
			case "iOS" : 
				caps.setCapability(MobileCapabilityType.BROWSER_NAME, "safari");
				break;
			}*/
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceID);
			caps.setCapability(MobileCapabilityType.UDID, deviceID);
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
			if(systemPort!=null)
			{
				caps.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
			}

			if(port==null)
			{
				System.out.println("!!!!! Error : Please provide Port number if your are using Local Appium !!!!!");
			}else{
				url = new URL("http://127.0.0.1:"+port+"/wd/hub");
			}
			
			// http://127.0.0.1:4273/wd/hub
			System.out.println("driver URL : "+url);
			this.driver = new AppiumDriver<MobileElement>(url,caps);
			this.driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
			System.out.println("driver implicit wait created : "+this.driver);

			break;
		case "Android" : 
			System.out.println("creatDriver : Android\n");
			break;
		case "iOS" : 
			System.out.println("creatDriver : iOS\n");
			break;
		case "Perfecto" : 
		default:

			System.out.println("creatDriver : Perfecto\n");
			String host = "mobilecloud.perfectomobile.com";

			caps = new DesiredCapabilities();

			//Generating SecurityToken : please refer : https://developers.perfectomobile.com/display/PD/Security+Token
			caps.setCapability("securityToken", "<YourPerfectoAccountSecurityToken>");

			switch(platform) {
			case "Android" : 
				caps.setCapability(MobileCapabilityType.BROWSER_NAME, "mobileOS");
				caps.setCapability(MobileCapabilityType.APP, "PUBLIC:ExpenseTracker/NativeApp/ExpenseAppVer1.0.apk");
				caps.setCapability("appPackage", "io.perfecto.expense.tracker");
				/*caps.setCapability("appActivity", "io.perfecto.expense.tracker.app.perfecto.com.expencemanager.ui.splash.SplashActivity");*/
				break;
			case "iOS" : 
				caps.setCapability(MobileCapabilityType.APP, "PUBLIC:ExpenseTracker/NativeApp/InvoiceApp1.0.ipa");
				caps.setCapability("bundleId", "io.perfecto.expense.tracker");
				break;
			}
			caps.setCapability(MobileCapabilityType.FULL_RESET, true);
			// Call this method if you want the script to share the devices with the Perfecto Lab plugin.
			//PerfectoLabUtils.setExecutionIdCapability(caps, host);
			// Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
			//caps.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);

			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
			if(model!=null)
			{
				caps.setCapability("model", model);
			}
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceID);
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");

			url = new URL("https://"+ host +"/nexperience/perfectomobile/wd/hub");

			System.out.println("driver URL : "+url);
			this.driver = new AppiumDriver<MobileElement>(url,caps);
			this.driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
			System.out.println("driver implicit wait created : "+this.driver);

			if(this.PerfectoDigitalZoomReportium) {
				//Perfecto Digital Report
				this.perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
						.withProject(new Project("LowPriceProject", "1.0"))
						.withJob(new Job("LowPriceJob", 1))
						.withContextTags("LowPrice")
						.withWebDriver(this.driver)
						.build();
				this.reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(this.perfectoExecutionContext);
				System.out.println("reportiumClient created : "+this.reportiumClient);
			}
			break;
		}

		Thread.sleep(2000);

		return this.driver;

	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception{
		System.out.println("tearDown\n");
		try {
			//this.driver.close();
			this.driver.quit();
			if(this.PerfectoDigitalZoomReportium) {
				// Retrieve the URL to the DigitalZoom Report (= Reportium Application) for an aggregated view over the execution
				String reportURL = this.reportiumClient.getReportUrl();
				// Retrieve the URL to the Execution Summary PDF Report
				String reportPdfUrl = (String)(this.driver.getCapabilities().getCapability("reportPdfUrl"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Thread.sleep(4000);
	}


	public AppiumDriver<MobileElement> getDriver() throws Exception{
		System.out.println("getDriver\n");
		return this.driver;
	}

	public ReportiumClient getReportiumClient() {
		System.out.println("getReportiumClient\n");
		return this.reportiumClient;
	}

	public Boolean getPerfectoDigitalZoomReportium() {
		System.out.println("getPerfectoDigitalZoomReportium\n");
		return this.PerfectoDigitalZoomReportium;
	}

}
