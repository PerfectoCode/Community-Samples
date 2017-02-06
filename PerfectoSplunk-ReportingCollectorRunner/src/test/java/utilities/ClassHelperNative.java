package utilities;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;



import PerfectoNativeRunner.PerfectoRunner.availableReportOptions;

import com.perfecto.splunk.ReportingCollectorFactory;
import com.perfecto.splunk.SplunkReportingCollector;


public abstract class ClassHelperNative {
	public String testName;
	public String executionId = "";
	public String reportId = "";
	public String host = "";
	public String perfectoReport = "";
	public Map<availableReportOptions, Object> testResults = new HashMap<availableReportOptions, Object>();
	// Call this at the start of test to set the reporting class and define
	// splunk details
	// Params
	// SLA in milliseconds - this will define pass/fail threshold for steps
	// based on response time
	// Splunk host
	// Splunk port
	// Splunk username
	// Splunk password
//	public void setSplunk() {
//		SplunkReportingCollector reporting = ReportingCollectorFactory.createInstance(200000, "52.204.229.203", 8089, "admin",
//				"Hova8584");
//		ReportingCollectorFactory.setReporting(reporting);
//	}
	public void setSplunk() {
        SplunkReportingCollector reporting = ReportingCollectorFactory.createInstance(200000,
                    "http", "localhost", "8088", "805E9E81-171D-458B-8599-D50A34A45792");
        ReportingCollectorFactory.setReporting(reporting);
  }
	// Returns the reporting class for use
	public SplunkReportingCollector getCollector() {
		return ReportingCollectorFactory.getCollector();
	}

	@BeforeSuite
	public void beforeSuite() {

		setSplunk();
	}

	@AfterSuite
	public void afterSuite() throws Exception {
		String json = getCollector().commitSplunk();
		System.out.print(json);
	}

	@Parameters({ "host", "username", "password", "scriptKey", "deviceId" })
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method, String host, String username, String password, String scriptKey,
			String deviceId) {
		this.host = host;
		setSplunk();

		// Sets the start time of the test
		getCollector().testExecutionStart();
		getCollector().reporting.put("seleniumHost", host);
		getCollector().reporting.put("threadNo",
				Thread.currentThread().getName() + " " + Thread.currentThread().getId());
		
		
		
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(Method method, ITestResult testResult, ITestContext tcc) throws IOException {

		getCollector().reporting.put("perfectoReport", perfectoReport);

		getCollector().submitReporting(testName);

	}
}
