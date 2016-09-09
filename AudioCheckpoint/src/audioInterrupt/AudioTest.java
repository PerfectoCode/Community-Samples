package audioInterrupt;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class AudioTest {
	private AndroidDriver<MobileElement> driver;
	private String reportName;
	private String deviceId;
	By ACall = By.xpath("//*[contains(@text, 'Answer')]");   //By.id("com.android.incallui:id/popup_call_answer");
	By ECall = By.xpath("//*[@resource-id='com.android.incallui:id/endButton']");
	
	@Test
	public void testAudio() {
		By searchBtn = By.xpath("//*[@resource-id='com.google.android.youtube:id/menu_search']");
		By searchTrm = By.xpath("//*[@resource-id='com.google.android.youtube:id/search_edit_text']");
		By result1 = By.xpath("//*[@resource-id='com.google.android.youtube:id/results']");
		By choice = By.xpath("//android.widget.TextView[contains(@text, 'playlist')]");
		By longTrailer = By.xpath("//*[@text='Avraham Fried Mix']");
		Map<String, Object> params = new HashMap<String, Object>();
		reportName = "_youTube_call";
		// provide test code - 
		// verify that YouTube is displayed
		try {
			driver.findElement(searchBtn).click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not find YouTube Search btn");
			// restart the application
			driver.closeApp();
			driver.launchApp();
			driver.findElement(searchBtn).click();
		}
		// search for  "Avraham Fried"
		driver.findElement(searchTrm).sendKeys("Avraham Fried");
		MobileElement resTbl = driver.findElement(result1);
		resTbl.findElement(choice).click();
		
		// select the music video
		// should perform wait for this call!
		driver.findElement(longTrailer).click();
		// set the volume to the highest level
		maxVolume();
		
		// start playing
		// use audio checkpoint to see that video is playing sound
		params.put("volume", -80);
		params.put("duration", 12);
		params.put("timeout", 20);

		String audioR = (String) driver.executeScript("mobile:checkpoint:audio", params);

		Assert.assertEquals(audioR, "true");
			
		// cause an interrupt of the broadcast
		intCall();
		
		// check that you tube returned to play by checking the audio
		// use audio checkpoint to see that video is playing sound
		params.put("volume", -80);
		params.put("duration", 12);
		params.put("timeout", 20);

		audioR = (String) driver.executeScript("mobile:checkpoint:audio", params);
		Assert.assertEquals(audioR, "true");	
		
 	}

	@Parameters({ "OperSys", "OSver", "device", "pkgName", "cloud", "userN", "userPw" })
	@BeforeMethod
	public void setupDriver(String opS, String vers, String dev, String appID,  
			String cloud, String user, String pw) {
		deviceId = dev;
        try {
			DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);
			String host = cloud;
			capabilities.setCapability("user", user);
			capabilities.setCapability("password", pw);
			
			// provide application parameters
			//capabilities.setCapability("app", repKey);
			//capabilities.setCapability("fullReset", true);
			if (opS.equalsIgnoreCase("android"))
				capabilities.setCapability("appPackage", appID);
			else
				capabilities.setCapability("bundleId", appID);
			
			// provide device parameters
			capabilities.setCapability("deviceName", dev);
			capabilities.setCapability("platformName", opS);
			capabilities.setCapability("platformVersion", vers);
			if (opS.equalsIgnoreCase("ios")) {
				capabilities.setCapability("model", "iPhone-6.*");
			}
			
			// Use the automationName capability to define the required framework - Appium (this is the default) or PerfectoMobile.
			capabilities.setCapability("automationName", "Appium");
			capabilities.setCapability("scriptName", "AudioInterruptTest");

			// Call this method if you want the script to share the devices with the Perfecto Lab plugin.
			PerfectoLabUtils.setExecutionIdCapability(capabilities, host);
			driver = new AndroidDriver<MobileElement>(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
      
			//driver = new IOSDriver<MobileElement>(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
			       
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to initialize the Driver instance!");
		}
        
	}
	
	@AfterMethod
	public void finishUp() {
        try {
            // Retrieve the URL of the Single Test Report, can be saved to your execution summary and used to download the report at a later point
            // String reportURL = (String)(driver.getCapabilities().getCapability(WindTunnelUtils.SINGLE_TEST_REPORT_URL_CAPABILITY));

            driver.close();

            // In case you want to download the report or the report attachments, do it here.
            PerfectoLabUtils.downloadReport(driver, "pdf", "C:\\test\\report" + reportName);
            // PerfectoLabUtils.downloadAttachment(driver, "video", "C:\\test\\report\\video", "flv");
            // PerfectoLabUtils.downloadAttachment(driver, "image", "C:\\test\\report\\images", "jpg");

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
        System.out.println("Run ended");
	}

	public void maxVolume() {
		int i = 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("keySequence", "VOL_UP");
		for (i = 0; i < 12; i++)
			driver.executeScript("mobile:presskey", params);
	}
	
	private void ansCall() throws InterruptedException {
		   // Waiting 30 seconds for an element to be present on the page, checking
		   // for its presence once every 2 seconds.
/*		   FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		       .withTimeout(30, TimeUnit.SECONDS)
		       .pollingEvery(2, TimeUnit.SECONDS)
		       .ignoring(NoSuchElementException.class);

		   MobileElement foo;
		try {
			foo = wait.until(new Function<WebDriver, MobileElement>() {
			     public MobileElement apply(WebDriver driver) {
			       return (MobileElement) driver.findElement(ansCall);
			     }
			   });
		} catch (Exception e) {
			// Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		   
		   foo.click();
*/
		// Temporary solution - sleep for 8 sec and then send Key code
		Thread.sleep(8000);
		driver.pressKeyCode(5);
	}
	
	private void endCall() throws InterruptedException {
		// let call continue for 8 seconds
		Thread.sleep(8000);
		try {
			driver.findElement(ECall).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void intCall() {
		// generate a phone call to the device
		Map<String, Object> paramC = new HashMap<String, Object>();
		paramC.put("to.handset", deviceId);
		Object result4 = driver.executeScript("mobile:gateway:call", paramC);
		
		// accept the call and play for 10 sec
		try {
			ansCall();
			// end the call 
			endCall();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
