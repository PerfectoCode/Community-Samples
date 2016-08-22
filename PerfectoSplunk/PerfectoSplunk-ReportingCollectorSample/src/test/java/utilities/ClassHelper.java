package utilities;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.perfecto.splunk.ReportingCollectorFactory;
import com.perfecto.splunk.SplunkReportingCollector;

public abstract class ClassHelper {
	private RemoteWebDriverExtended driver;
	public Library lib;
	private TestSetup tes;
	private static String splunkHost;
	private static String splunkPort;
	private static String splunkScheme;
	private static String splunkToken;
	private static String splunkSLA;
	private static String splunkUsername;
	private static String splunkPassword;
	public static String splunkIndex;

	// Call this at the start of test to set the reporting class and
	// define splunk details
	// Params
	// @1SLA in milliseconds - this will define pass/fail threshold for steps
	// //based on response time
	// @2Splunk host
	// @3Splunk port
	// @4Splunk scheme http or https
	// @5Splunk token if required
	// @4Splunk username
	// @5Splunk password
	public void setSplunk() {
		SplunkReportingCollector reporting = ReportingCollectorFactory.createInstance(Long.parseLong(splunkSLA),
				splunkHost, Integer.parseInt(splunkPort), splunkScheme, splunkToken, splunkUsername, splunkPassword);
		ReportingCollectorFactory.setReporting(reporting);
	}

	// Returns the reportingCollector class for use
	public SplunkReportingCollector getCollector() {
		return ReportingCollectorFactory.getCollector();
	}

	@Parameters({ "splunkHost",
			"splunkPort", "splunkScheme", "splunkToken", "splunkSLA", "splunkUsername", "splunkPassword","splunkIndex" })
	@BeforeSuite
	public void beforeSuite(String splunkHost, String splunkPort, String splunkScheme, String splunkToken,
			String splunkSLA, String splunkUsername, String splunkPassword, String splunkIndex) {
		this.splunkHost = splunkHost;
		this.splunkPort = splunkPort;
		this.splunkScheme = splunkScheme;
		this.splunkToken = splunkToken;
		this.splunkSLA = splunkSLA;
		this.splunkUsername = splunkUsername;
		this.splunkPassword = splunkPassword;
		this.splunkIndex=splunkIndex;

		setSplunk();
	}

	@AfterSuite
	public void afterSuite() {
		try {
			// Submits the data to splunk
			// Params
			// @1 Splunk index name
			// return type is String and contains the Json which was written to
			// Splunk
			String value = getCollector().commitSplunk(splunkIndex);
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Parameters({ "targetEnvironment", "perfectoHost", "perfectoUsername", "perfectoPassword" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method, String targetEnvironment, String perfectoHost, String perfectoUsername,
			String perfectoPassword) {
		setSplunk();

		// initializes testSetup class
		tes = new TestSetup(targetEnvironment, perfectoHost, perfectoUsername, perfectoPassword, driver);
		// sets up the testNG flows based on testsuite.xml
		tes.flowControl();

		try {
			lib = tes.driverAndLibrarySetup();
		} catch (UnsupportedEncodingException | MalformedURLException | InterruptedException e) {
			e.printStackTrace();
		}

		String testName = method.getDeclaringClass().getSimpleName() + "::" + method.getName();

		// Sets the start time of the test
		getCollector().testExecutionStart();

		// Put any value into the reporting json by passing
		// Params
		// @1 parameter name
		// @2 parameter value
		getCollector().reporting.put("seleniumHost", tes.perfectoHost);
		getCollector().reporting.put("threadNo",
				Thread.currentThread().getName() + " " + Thread.currentThread().getId());

		lib.setTestName(method.getName());
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(Method method, ITestResult testResult, ITestContext tcc) throws IOException {
		try {

			if (lib.isDevice()) {

				lib.getDriver().close();

				getCollector().reporting.put("executionID",
						(String) lib.getDriver().getCapabilities().getCapability("executionId"));
				getCollector().reporting.put("reportKey",
						(String) lib.getDriver().getCapabilities().getCapability("reportKey"));
				getCollector().reporting.put("perfectoReport",
						"https://" + tes.perfectoHost
								+ "/nexperience/Report.html?reportId=SYSTEM%3Adesigns%2Freport&key="
								+ (String) lib.getDriver().getCapabilities().getCapability("reportKey").toString()
										.replace(".xml", "")
								+ "%2Exml&liveUrl=rtmp%3A%2F%2F" + tes.perfectoHost.replace(".", "%2E")
								+ "%2Fengine&appUrl=https%3A%2F%2F" + tes.perfectoHost.replace(".", "%2E")
								+ "%2Fnexperience");
				getCollector().reporting.put("windTunnelReport",
						(String) lib.getDriver().getCapabilities().getCapability("windTunnelReportUrl"));

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().browse(
							new URI((String) lib.getDriver().getCapabilities().getCapability("windTunnelReportUrl")));
				}
			} else {
				lib.getDriver().quit();
			}
		} catch (Exception ex) {

		}

		// Submits the test case to the collector
		// Params
		// @1 test name
		getCollector().submitReporting(testResult.getMethod().getMethodName());

		try {
			lib.getDriver().quit();

		} catch (Exception ex) {

		}
	}
}
