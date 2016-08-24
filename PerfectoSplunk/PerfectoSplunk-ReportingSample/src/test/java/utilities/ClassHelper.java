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
import com.perfecto.splunk.ReportingFactory;
import com.perfecto.splunk.SplunkReporting;
import com.perfecto.splunk.SplunkReportingCollector;

public abstract class ClassHelper {
	private RemoteWebDriverExtended driver;
	public Library lib;
	private TestSetup tes;
	private static String splunkScheme;
	private static String splunkHost;
	private static String splunkPort;
	private static String splunkToken;
	private static String splunkSLA;

	// Call this at the start of test to set the reporting class and
	// define splunk details
	// Params
	// @1SLA in milliseconds - this will define pass/fail threshold for steps based on response time
	// @2 Splunk scheme http or https
	// @3 Splunk host
	// @4 Splunk port
	// @5 Splunk token if required
	// @6 Proxy if desired
	public void setSplunk() {
		SplunkReporting reporting = ReportingFactory.createInstance(Long.parseLong(splunkSLA), splunkScheme, splunkHost,
				splunkPort, splunkToken);
		ReportingFactory.setReporting(reporting);
	}

	// Returns the reportingCollector class for use
	public SplunkReporting getCollector() {
		return ReportingFactory.getReporting();
	}

	@BeforeSuite
	public void beforeSuite() {

	}

	@AfterSuite
	public void afterSuite() {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Parameters({ "targetEnvironment", "perfectoHost", "perfectoUsername", "perfectoPassword", "splunkHost",
			"splunkPort", "splunkScheme", "splunkToken", "splunkSLA" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method, String targetEnvironment, String perfectoHost, String perfectoUsername,
			String perfectoPassword, String splunkHost, String splunkPort, String splunkScheme, String splunkToken,
			String splunkSLA) {

		this.splunkScheme = splunkScheme;
		this.splunkHost = splunkHost;
		this.splunkPort = splunkPort;
		this.splunkToken = splunkToken;
		this.splunkSLA = splunkSLA;

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
	public void afterMethod(Method method, ITestResult testResult, ITestContext tcc) throws Exception {
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

		// Submits the data to splunk
		// Params
		// @1 High level test name - first node in JSON
		// @2 test case name
		// return type is String and contains the Json which was written to
		// Splunk
		String value = getCollector().commitSplunk("Perfecto", testResult.getMethod().getMethodName());
		System.out.println(value);

		try {
			lib.getDriver().quit();

		} catch (Exception ex) {

		}
	}
}
