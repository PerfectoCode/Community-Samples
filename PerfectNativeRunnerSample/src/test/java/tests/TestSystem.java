package tests;

import java.lang.reflect.Method;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import PerfectoNativeRunner.PerfectoRunner;

public class TestSystem {

	private Map<String, Object> testResults = new HashMap<String, Object>();

	@Parameters({ "host", "username", "password", "scriptKey", "deviceId" })
	@Test
	public void NativeTests(String host, String username, String password, String scriptKey, String deviceId)
			throws Exception {
		PerfectoRunner pr = new PerfectoRunner();
		testResults = pr.executeScript(host, username, password, scriptKey, deviceId);

		System.out.println("ScriptStatus:"
				+ testResults.get(PerfectoRunner.availableReportOptions.scriptStatus.toString()).toString());

		System.out
				.println("Model:" + testResults.get(PerfectoRunner.availableReportOptions.model.toString()).toString());

		System.out.println("Os:" + testResults.get(PerfectoRunner.availableReportOptions.os.toString()).toString());

		System.out.println(
				"OSVersion:" + testResults.get(PerfectoRunner.availableReportOptions.osVersion.toString()).toString());

		System.out.println(
				"DeviceId:" + testResults.get(PerfectoRunner.availableReportOptions.deviceId.toString()).toString());

		System.out.println("scriptName:"
				+ testResults.get(PerfectoRunner.availableReportOptions.scriptName.toString()).toString());

		System.out.println(
				"reportId:" + testResults.get(PerfectoRunner.availableReportOptions.reportId.toString()).toString());

		System.out.println("executionId:"
				+ testResults.get(PerfectoRunner.availableReportOptions.executionId.toString()).toString());

		Map<String, String> transactions = (Map<String, String>) testResults
				.get(PerfectoRunner.availableReportOptions.transactions.toString());

		int i = 1;
		for (String key : transactions.keySet()) {
			System.out.println("transactionName:" + key.toString());
			System.out.println("timer:" + transactions.get(key).toString().trim());
		}
	}
}