import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.html5.*;
import org.openqa.selenium.logging.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.Cookie.Builder;

import io.appium.java_client.*;
import io.appium.java_client.android.*;
import io.appium.java_client.ios.*;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResult;
import com.perfecto.reportium.test.result.TestResultFactory;

/**
 * For programming samples and updated templates refer to the Perfecto GitHub
 * at: https://github.com/PerfectoCode
 */
public class AppiumTest {

	public static void main(String[] args) throws MalformedURLException, IOException {
		System.out.println("Run started");

		String browserName = "mobileOS";
		DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
		String host = "https://partners.perfectomobile.com";
        capabilities.setCapability("user", "[User Name]");
        capabilities.setCapability("password", "[Password]");

		// TODO: Change your device ID
		capabilities.setCapability("deviceName", "3B74D48CD1555F29B76D55F495D01B384E2E7F58");

		// Use the automationName capability to define the required framework -
		// Appium (this is the default) or PerfectoMobile.
		capabilities.setCapability("automationName", "Appium");

		// Call this method if you want the script to share the devices with the
		// Perfecto Lab plugin.
		PerfectoLabUtils.setExecutionIdCapability(capabilities, host);

		// Application settings examples.
		// capabilities.setCapability("app",
		// "PRIVATE:applications/Errands.ipa");
		// For Android:
		// capabilities.setCapability("appPackage", "com.google.android.keep");
		// capabilities.setCapability("appActivity",
		// ".activities.BrowseActivity");
		
		capabilities.setCapability("app", "PUBLIC:FingerPrint\\FingerprintTest.ipa");
		
		// For iOS:
		capabilities.setCapability("bundleId", "com.perfectomobile.FingerprintTest");
		
		capabilities.setCapability("fingerprintInstrument", true);

		// Add a persona to your script (see
		// https://community.perfectomobile.com/posts/1048047-available-personas)
		// capabilities.setCapability(WindTunnelUtils.WIND_TUNNEL_PERSONA_CAPABILITY,
		// WindTunnelUtils.GEORGIA);

		// Name your script
		// capabilities.setCapability("scriptName", "AppiumTest");

		// AndroidDriver driver = new AndroidDriver(new URL("https://" + host +
		// "/nexperience/perfectomobile/wd/hub"), capabilities);
		IOSDriver driver = new IOSDriver(new URL(host + "/nexperience/perfectomobile/wd/hub"),
				capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Reporting client. For more details, see
		// https://github.com/perfectocode/samples/wiki/reporting
		PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
				.withProject(new Project("My Project", "1.0")).withJob(new Job("My Job", 45)).withContextTags("tag1")
				.withWebDriver(driver).build();
		ReportiumClient reportiumClient = new ReportiumClientFactory()
				.createPerfectoReportiumClient(perfectoExecutionContext);

		try {
			reportiumClient.testStart("TouchID Automation IOS", new TestContext("touchID"));

			// write your code here
			driver.findElement(By.xpath("//*[@label='AUTH']")).click();

			Map<String, Object> params1 = new HashMap<>();
			params1.put("content", "Touch ID for");
			params1.put("timeout", "20");
			Object result1 = driver.executeScript("mobile:checkpoint:text", params1);

			Map<String, Object> params = new HashMap<>();
			// use either the "identifier" or "name" parameter to identify the
			// app
			params.put("identifier", "com.perfectomobile.FingerprintTest");
			params.put("resultAuth", "fail"); // may be either "fail" or
												// "success"
			driver.executeScript("mobile:fingerprint:set", params);

			params1 = new HashMap<>();
			params1.put("content", "result: failure");
			params1.put("timeout", "20");
			result1 = driver.executeScript("mobile:checkpoint:text", params1);

			params1 = new HashMap<>();
			params1.put("content", "Authentication failed");
			params1.put("timeout", "20");
			result1 = driver.executeScript("mobile:checkpoint:text", params1);

			driver.findElement(By.xpath("//*[@label='AUTH']")).click();
			
			params1 = new HashMap<>();
			params1.put("content", "Touch ID for");
			params1.put("timeout", "20");
			result1 = driver.executeScript("mobile:checkpoint:text", params1);

			params = new HashMap<>();
			// use either the "identifier" or "name" parameter to identify the
			// app
			params.put("identifier", "com.perfectomobile.FingerprintTest");
			params.put("resultAuth", "success"); // may be either "fail" or
												// "success"
			driver.executeScript("mobile:fingerprint:set", params);

			params1 = new HashMap<>();
			params1.put("content", "result: success");
			params1.put("timeout", "20");
			result1 = driver.executeScript("mobile:checkpoint:text", params1);

			// reportiumClient.testStep("step1"); // this is a logical step for
			// reporting
			// reportiumClient.testStep("step2");

			reportiumClient.testStop(TestResultFactory.createSuccess());
		} catch (Exception e) {
			reportiumClient.testStop(TestResultFactory.createFailure(e.getMessage(), e));
			e.printStackTrace();
		} finally {
			try {
				// Retrieve the URL of the Single Test Report, can be saved to
				// your execution summary and used to download the report at a
				// later point
				String reportURL = reportiumClient.getReportUrl();

				// For documentation on how to export reporting PDF, see
				// https://github.com/perfectocode/samples/wiki/reporting
				// String reportPdfUrl =
				// (String)(driver.getCapabilities().getCapability("reportPdfUrl"));

				driver.closeApp();

				// In case you want to download the report or the report
				// attachments, do it here.
				// PerfectoLabUtils.downloadAttachment(driver, "video",
				// "C:\\test\\report\\video", "flv");
				// PerfectoLabUtils.downloadAttachment(driver, "image",
				// "C:\\test\\report\\images", "jpg");

			} catch (Exception e) {
				e.printStackTrace();
			}

			driver.quit();
		}

		System.out.println("Run ended");
	}
}
