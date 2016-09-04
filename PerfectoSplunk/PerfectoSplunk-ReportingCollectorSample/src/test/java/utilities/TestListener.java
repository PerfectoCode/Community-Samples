package utilities;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IInvokedMethodListener2;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.internal.thread.IExecutor;
import org.testng.internal.thread.ThreadUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.perfecto.splunk.ReportingFactory;
import com.perfecto.splunk.SplunkReporting;
import com.perfecto.splunk.SplunkReportingCollector;

public class TestListener implements ISuiteListener, ITestListener, IInvokedMethodListener {

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult arg0) {
	}

	@Override
	public void onFinish(ISuite arg0) {
	}

	@Override
	public void onStart(ISuite arg0) {
	}

	@Override
	public void onFinish(ITestContext arg0) {
	}

	@Override
	public void onStart(ITestContext arg0) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}
	
	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		setDetails("Pass", arg0);
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		String result = "Fail";
		setDetails(result, arg0);
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		setDetails("Skip", arg0);
	}

	public void setDetails(String result, ITestResult testResult) {
		// gets the instance of the ClassHelper associated with the thread
		Object currentClass = testResult.getMethod().getInstance();
		ClassHelper classHelper = ((ClassHelper) currentClass);

		// Initializes Splunk in case of Skip
		if (result.equalsIgnoreCase("Skip")) {
			classHelper.setSplunk();
		}

		// Creates local instance of SplunkReporting for the listener
		SplunkReportingCollector splunkReport = classHelper.getCollector();

		splunkReport.reporting.put("testStatus", result);

		splunkReport.reporting.put("className", testResult.getMethod().getInstance().getClass().getName());

		try {
			splunkReport.reporting.put("hostName", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!result.equalsIgnoreCase("Skip")) {
			if (classHelper.lib.isDevice()) {
				splunkReport.reporting.put("model",
						(String) classHelper.lib.driver.getCapabilities().getCapability("model"));
				splunkReport.reporting.put("device",
						(String) classHelper.lib.driver.getCapabilities().getCapability("deviceName"));
				splunkReport.reporting.put("os",
						(String) classHelper.lib.driver.getCapabilities().getCapability("platformName"));
			} else {
				splunkReport.reporting.put("device",
						(String) classHelper.lib.driver.getCapabilities().getPlatform().family().name());
				splunkReport.reporting.put("os",
						(String) classHelper.lib.driver.getCapabilities().getPlatform().name());
				splunkReport.reporting.put("browser",
						(String) classHelper.lib.driver.getCapabilities().getBrowserName());
				splunkReport.reporting.put("browserVersion",
						(String) classHelper.lib.driver.getCapabilities().getVersion());
			}

		}

		if (result.equalsIgnoreCase("Fail")) {
			if (testResult.getThrowable() != null)
				if (testResult.getThrowable().getStackTrace() != null) {
					StringWriter sw = new StringWriter();
					testResult.getThrowable().printStackTrace(new PrintWriter(sw));
					splunkReport.reporting.put("stackTrace", sw.toString());
				}
		}

		if (!result.equalsIgnoreCase("Skip")) {
			// Sets the end time of the test
			splunkReport.testExecutionEnd();
		}

		splunkReport.reporting.put("testName", testResult.getTestContext().getName());
		splunkReport.reporting.put("methodName", testResult.getMethod().getMethodName());

		if (result.equalsIgnoreCase("Skip")) {
			// setting this here in case of skip
			// Submits the test case to the collector
			// Params
			// @1 test name
			splunkReport.submitReporting(testResult.getMethod().getMethodName());
		}
	}
}
