package com.quantum.listerners;

import static com.qmetry.qaf.automation.core.ConfigurationManager.getBundle;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ISuiteListener, ITestListener {

	@Override
	public void onTestSuccess(ITestResult testResult) {

		setDetails("Pass", testResult);

	}

	@Override
	public void onTestFailure(ITestResult testResult) {

		setDetails("Fail", testResult);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		setDetails("Skip", result);
	}

	public void setDetails(String result, ITestResult testResult) {

		SplunkHelper.getCollector().reporting.put("testStatus", result);

		SplunkHelper.getCollector().reporting.put("className",
				testResult.getMethod().getInstance().getClass().getName());

		try {
			SplunkHelper.getCollector().reporting.put("hostName", InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!result.equalsIgnoreCase("Skip")) {

			SplunkHelper.getCollector().reporting.put("model", (String) SplunkHelper.getDeviceInfo("model"));
			SplunkHelper.getCollector().reporting.put("device", (String) SplunkHelper.getDeviceInfo("deviceID"));
			SplunkHelper.getCollector().reporting.put("os", (String) SplunkHelper.getDeviceInfo("os"));
			SplunkHelper.getCollector().reporting.put("location", (String) SplunkHelper.getDeviceInfo("location"));
			SplunkHelper.getCollector().reporting.put("description", (String) SplunkHelper.getDeviceInfo("description"));
			try {
				SplunkHelper.getCollector().reporting.put("monitorTag", (String) SplunkHelper.getMonitorTag());
			} catch (Exception ex) {

			}
		}

		if (result.equalsIgnoreCase("Fail")) {
			if (testResult.getThrowable() != null)
				if (testResult.getThrowable().getStackTrace() != null) {
					StringWriter sw = new StringWriter();
					testResult.getThrowable().printStackTrace(new PrintWriter(sw));
					SplunkHelper.getCollector().reporting.put("stackTrace", sw.toString());
				}
		}

		if (!result.equalsIgnoreCase("Skip")) {
			// Sets the end time of the test
			// Divides the start and end time to create a test duration in
			// seconds
			// and finally converts the start/end time to real date formats
			SplunkHelper.getCollector().testExecutionEnd();
		}

		SplunkHelper.getCollector().reporting.put("testName", testResult.getTestContext().getName());
		SplunkHelper.getCollector().reporting.put("methodName", testResult.getMethod().getMethodName());

		SplunkHelper.getCollector().reporting.put("executionID",
				(String) SplunkHelper.getQAFDriver().getCapabilities().getCapability("executionId"));
		SplunkHelper.getCollector().reporting.put("reportKey",
				(String) SplunkHelper.getQAFDriver().getCapabilities().getCapability("reportKey"));
		SplunkHelper.getCollector().reporting.put("perfectoReport",
				"https://" + getBundle().getPropertyValue("host")
						+ "/nexperience/Report.html?reportId=SYSTEM%3Adesigns%2Freport&key="
						+ (String) SplunkHelper.getQAFDriver().getCapabilities().getCapability("reportKey").toString()
								.replace(".xml", "")
						+ "%2Exml&liveUrl=rtmp%3A%2F%2F" + getBundle().getPropertyValue("host").replace(".", "%2E")
						+ "%2Fengine&appUrl=https%3A%2F%2F" + getBundle().getPropertyValue("host").replace(".", "%2E")
						+ "%2Fnexperience");
		SplunkHelper.getCollector().reporting.put("windTunnelReport",
				(String) SplunkHelper.getQAFDriver().getCapabilities().getCapability("windTunnelReportUrl"));

		SplunkHelper.getCollector().reporting.put("reportiumReport",
				QuantumReportiumListener.getReportiumClient().getReportUrl());

		SplunkHelper.getCollector().submitReporting(testResult.getMethod().getMethodName());

	}

	@Override
	public void onFinish(ISuite arg0) {
		try {
			System.out.println(SplunkHelper.getCollector().commitSplunk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onStart(ISuite arg0) {
		SplunkHelper.setSplunk();
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		SplunkHelper.getCollector().testExecutionStart();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		SplunkHelper.setSplunk();
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
