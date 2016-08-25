package utilities;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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

import PerfectoNativeRunner.PerfectoRunner;

public abstract class ClassHelper {
	private static String splunkScheme;
	private static String splunkHost;
	private static String splunkPort;
	private static String splunkToken;
	private static long splunkSLA;

	public String host;
	public String username;
	public String password;
	public String scriptKey;
	public String deviceId;
	public String additionalParams;
	public int cycles;
	public long waitForCycles;

	// the map to store the test results
	public Map<String, Object> testResults = new HashMap<String, Object>();

	// Call this at the start of test to set the reporting class and
	// define splunk details
	// Params
	// @1SLA in milliseconds - this will define pass/fail threshold for steps
	// based on response time
	// @2 Splunk scheme http or https
	// @3 Splunk host
	// @4 Splunk port
	// @5 Splunk token if required
	// @6 Proxy if desired
	public void setSplunk() {
		SplunkReportingCollector reporting = ReportingCollectorFactory.createInstance(splunkSLA, splunkScheme,
				splunkHost, splunkPort, splunkToken);
		ReportingCollectorFactory.setReporting(reporting);
	}

	// Returns the reportingCollector class for use
	public SplunkReportingCollector getCollector() {
		return ReportingCollectorFactory.getCollector();
	}

	@Parameters({ "splunkScheme", "splunkHost", "splunkPort", "splunkToken", "splunkSLA" })
	@BeforeSuite
	public void beforeSuite(String splunkScheme, String splunkHost, String splunkPort, String splunkToken,
			long splunkSLA) {
		this.splunkScheme = splunkScheme;
		this.splunkHost = splunkHost;
		this.splunkPort = splunkPort;
		this.splunkToken = splunkToken;
		this.splunkSLA = splunkSLA;

		setSplunk();
	}

	@AfterSuite
	public void afterSuite() {
		try {
			// Submits the data to splunk
			// return type is String and contains the Json which was written to
			// Splunk
			String value = getCollector().commitSplunk();
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Parameters({ "host", "username", "password", "scriptKey", "deviceId", "additionalParams", "cycles",
			"waitForCycles" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method, String host, String username, String password, String scriptKey,
			String deviceId, String additionalParams, int cycles, long waitForCycles) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.scriptKey = scriptKey;
		this.deviceId = deviceId;
		this.additionalParams = additionalParams;
		this.cycles = cycles;
		this.waitForCycles = waitForCycles;

		setSplunk();

		getCollector().testExecutionStart();

		// Put any value into the reporting json by passing
		// Params
		// @1 parameter name
		// @2 parameter value
		getCollector().reporting.put("threadNo",
				Thread.currentThread().getName() + " " + Thread.currentThread().getId());

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(Method method, ITestResult testResult, ITestContext tcc) throws IOException {
		try {

			getCollector().reporting.put("executionID",
					testResults.get(PerfectoRunner.availableReportOptions.executionId.toString()).toString());
			getCollector().reporting.put("reportKey",
					testResults.get(PerfectoRunner.availableReportOptions.reportId.toString()).toString());
			getCollector().reporting.put("perfectoReport",
					testResults.get(PerfectoRunner.availableReportOptions.reportUrl.toString()).toString());

			// Transactions are returned in its own map
			Map<String, String> transactions = (Map<String, String>) testResults
					.get(PerfectoRunner.availableReportOptions.transactions.toString());

			// loop the transactions to get them all
			int i = 1;
			for (String key : transactions.keySet()) {
				getCollector().startTransaction("Step" + i, key.toString());
				getCollector().endTransaction("Step" + i, Long.parseLong(transactions.get(key).toString().trim()));

			}

		} catch (Exception ex) {

		}

		// Submits the test case to the collector
		// Params
		// @1 test name
		getCollector().submitReporting(
				testResults.get(testResult.getMethod().getMethodName()).toString());

	}
}
