import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;

import org.openqa.selenium.Cookie.Builder;
import org.openqa.selenium.NoSuchElementException;

import io.appium.java_client.*;
import io.appium.java_client.android.*;
import io.appium.java_client.ios.*;

/**
 * For programming samples and updated templates refer to the Perfecto GitHub at: https://github.com/PerfectoCode
 */
public class AppiumTest {
	static IOSDriver<MobileElement> driver = null;

    public static void main(String[] args) throws MalformedURLException, IOException {
        System.out.println("Run started");
        WebElement interval;

        String browserName = "mobileOS";
        DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
        String host = "https://partners.perfectomobile.com";
        capabilities.setCapability("user", "[User Name]");
        capabilities.setCapability("password", "[Password]");


        //TODO: Change your device ID
        capabilities.setCapability("deviceName", "3B74D48CD1555F29B76D55F495D01B384E2E7F58");

        // Use the automationName capability to define the required framework - Appium (this is the default) or PerfectoMobile.
        capabilities.setCapability("automationName", "Appium");

        // Call this method if you want the script to share the devices with the Perfecto Lab plugin.
        PerfectoLabUtils.setExecutionIdCapability(capabilities, host);

        // Application settings examples.
        capabilities.setCapability("app", "PUBLIC:image_injection\\RealTimeFilter.ipa");
		
		// Perform instrumentation for image injection support
        capabilities.setCapability("cameraInstrument", true);

        // For iOS:
        capabilities.setCapability("bundleId", "Victor.RealTimeFilter");

        // Add a persona to your script (see https://community.perfectomobile.com/posts/1048047-available-personas)
        //capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY, WindTunnelUtils.GEORGIA);

        // Name your script
        capabilities.setCapability("scriptName", "ImageInjection");

        // AndroidDriver driver = new AndroidDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        driver = new IOSDriver<>(new URL(host + "/nexperience/perfectomobile/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
                .withProject(new Project("My Project", "1.0"))
                .withJob(new Job("My Job", 45))
                .withContextTags("tag1")
                .withWebDriver(driver)
                .build();
        ReportiumClient reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);

        try {
            reportiumClient.testStart("Image Injection", new TestContext("tag2", "tag3"));

        	// check for popup
    		FluentWait<WebDriver> pwait = new FluentWait<WebDriver>(driver)
    				.withTimeout(10, TimeUnit.SECONDS)
    				.pollingEvery(2, TimeUnit.SECONDS)
    				.ignoring(NoSuchElementException.class);
    	
    		try {
    			interval = pwait.until(new Function<WebDriver, WebElement>() {
    				public WebElement apply(WebDriver d) {
    					WebElement lbl = d.findElement(By.xpath("//*[contains(@value, 'Access the Camera')]"));
    					WebElement rst = d.findElement(By.xpath("//*[@label='OK']"));
    					rst.click();
    					return lbl;
    				}
    			});
    		} catch (Exception t) {
    			System.out.println("Did not find the popup");
    		}
        	
			// Inject picture of credit card
        	Map<String, Object> params = new HashMap<>();
        	params.put("repositoryFile", "PUBLIC:image_injection\\PartnerTeam\\CreditCard.jpg");
        	params.put("identifier", "Victor.RealTimeFilter");
        	Object res = driver.executeScript("mobile:image.injection:start", params);

			// Visual checkpoint to verify that card is displayed
//			params.clear();
//			params.put("content", "PUBLIC:image_injection\\PartnerTeam\\CreditCard.jpg");
//			params.put("threshold", "70");
//			String resStr = (String) driver.executeScript("mobile:image:find", params);
			
			Map<String, Object> params2 = new HashMap<>();
			params2.put("content", "PUBLIC:image_injection\\PartnerTeam\\cc_card_verification.png");
			params2.put("threshold", "70");
			params2.put("match", "similar");
			String resStr = (String) driver.executeScript("mobile:image:find", params2);


			// if Unsuccessful - verify that camera access turned on
			if (resStr.equalsIgnoreCase("false")) {
				//check that camera access is turned on and then try image find again.
				checkSettings();

				
				
				params.clear();
				params.put("content", "PUBLIC:image_injection\\PartnerTeam\\CreditCard.jpg");
				params.put("threshold", "70");
				resStr = (String) driver.executeScript("mobile:image:find", params);
			}
			
			if (resStr.equalsIgnoreCase("false")) {
				System.out.println("Problems in wonderland");
			} else {
				System.out.println("Card is displayed");
			}

			// Stop the image injection - field should turn blank
			params.clear();
        	res = driver.executeScript("mobile:image.injection:stop", params);
        	
        	reportiumClient.testStop(TestResultFactory.createSuccess());
        	
        } catch (Exception e) {
        	reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
            e.printStackTrace();
        } finally {
            try {
                // Retrieve the URL of the Single Test Report, can be saved to your execution summary and used to download the report at a later point
                String reportURL = (String)(driver.getCapabilities().getCapability(WindTunnelUtils.SINGLE_TEST_REPORT_URL_CAPABILITY));

                driver.close();

                // In case you want to download the report or the report attachments, do it here.
                PerfectoLabUtils.downloadReport(driver, "pdf", "C:\\test\\reportImages");
                // PerfectoLabUtils.downloadAttachment(driver, "video", "C:\\test\\report\\video", "flv");
                // PerfectoLabUtils.downloadAttachment(driver, "image", "C:\\test\\report\\images", "jpg");

            } catch (Exception e) {
                e.printStackTrace();
            }

            driver.quit();
        }

        System.out.println("Run ended");
    }
    
    static void checkSettings() {
    	By camSetting = By.xpath("//*[@class='UIASwitch' and @name ='Camera']");
    	
    	Map<String, Object> pars = new HashMap<>();
    	pars.put("name", "Settings");
    	driver.executeScript("mobile:application:open", pars);
    	
    	driver.scrollTo("RealTimeFilter").click();
    	
    	MobileElement camSwitch = driver.findElement(camSetting);
    	String camon = camSwitch.getAttribute("value");
    	if (camon.equalsIgnoreCase("0"))
    		camSwitch.click();
    	
    	return;
    		
    }
}

