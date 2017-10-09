package TestNG.xcuitest;

import org.testng.annotations.Test;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;
import com.perfectomobile.selenium.util.EclipseConnector;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;



public class SingleScriptXCUITest {
	
	ReportiumClient reportiumClient;
	private IOSDriver driver;
	
  @Test
  public void f() throws InterruptedException {
	  
      try {
    	  
    	  String SelectBoardingAiport = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeButton[1] | //UIAScrollView/UIAButton[1]";
    	  //String SelectBoardingAiport2 = "//XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[2]/XCUIElementTypeButton[1] | //UIAScrollView/UIAButton[1]";
    	  
          reportiumClient.testStart("My test name", new TestContext("tag2", "tag3"));
          
          // write your code here
          
          try {
				stopApp("Etihad Airways", driver);
			} catch (Exception e) {}

          startApp("Etihad Airways", driver);
            
          driver.findElementByXPath("//*[@label=\"Book\"]").click();
          
          driver.findElementByXPath(SelectBoardingAiport).click();
          
          driver.getCapabilities().getVersion();
          
          ClickText("A Coruna", driver);
          
          Thread.sleep(2000);
  
          ClickText("Arrival airport", driver);
          
          Thread.sleep(2000);
          
          ClickText("Abu Dhabhi", driver);
          
          driver.findElementByXPath("//*[@label=\"CHOOSE DATES\"]").click();
          
          //scrolltoXPath(driver, "//UIATableCell[@label=\"September 2017\"]/following-sibling::*//UIAButton[@enabled='true']");
          
          // reportiumClient.testStep("step1"); // this is a logical step for reporting
          // add commands...
          // reportiumClient.testStep("step2");
          // more commands...
          
          reportiumClient.testStop(TestResultFactory.createSuccess());
      } catch (Exception e) {
          reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
          e.printStackTrace();
      }
  }
  
  @Parameters({"cloud", "userN", "userPw", "deviceid", "automationname"})
  @BeforeClass
  public void beforeClass(String cloudurl, String username, String password, String deviceid, String automationname) throws IOException {
	  
      System.out.println("Run started");
      
      String browserName = "mobileOS";
      DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
      String host = cloudurl;
      capabilities.setCapability("user", username);
      capabilities.setCapability("password", password);
      
      //TODO: Change your device ID
      //capabilities.setCapability("deviceName", "AA7EEEAADD92242C665D2807B538BDACFAA5A0DB");
      capabilities.setCapability("deviceName", deviceid);
      
      
      // Use the automationName capability to define the required framework - Appium (this is the default) or PerfectoMobile.
       //capabilities.setCapability("automationName", "Appium");
      capabilities.setCapability("automationName", automationname);
      
      // Call this method if you want the script to share the devices with the Perfecto Lab plugin.
      setExecutionIdCapability(capabilities, host);
      
      // Application settings examples.
      // capabilities.setCapability("app", "PRIVATE:applications/Errands.ipa");
      // For Android:
      // capabilities.setCapability("appPackage", "com.google.android.keep");
      // capabilities.setCapability("appActivity", ".activities.BrowseActivity");
      // For iOS:
      capabilities.setCapability("bundleId", "com.mttnow.iphone.etihad");
      //capabilities.setCapability("bundleId", "com.marriott.iphoneprod");
      
      
      
      // Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
      //capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);
      
      // Name your script
      // capabilities.setCapability("scriptName", "AppiumTest");
      
      //AndroidDriver driver = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
      this.driver = new IOSDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      
      // Reporting client. For more details, see http://developers.perfectomobile.com/display/PD/Reporting
      PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
      .withProject(new Project("My Project", "1.0"))
      .withJob(new Job("My Job", 45))
      .withContextTags("tag1")
      .withWebDriver(driver)
      .build();
      reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
  }

  @AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }
  
  /**
   * Sets the execution id capability
   */
  public static void setExecutionIdCapability(DesiredCapabilities capabilities, String host) throws IOException {
      try {
          EclipseConnector connector = new EclipseConnector();
          String eclipseHost = connector.getHost();
          if ((eclipseHost == null) || (eclipseHost.equalsIgnoreCase(host))) {
              String executionId = connector.getExecutionId();
              capabilities.setCapability(EclipseConnector.ECLIPSE_EXECUTION_ID, executionId);
          }
      } catch (Exception e) {
         // logger.warn("Error connecting with the Eclipse connector - Resuming execution without the execution id capability");
      }
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
	
	public static void ClickText(String content, AppiumDriver driver){
		
      Map<String, Object> params1 = new HashMap<>();
      params1.put("label", content);
      params1.put("timeout", "20");
      params1.put("words", "words");
      params1.put("ignorecase", "nocase");
      Object result1 = driver.executeScript("mobile:button-text:click", params1);
      
	}

}
