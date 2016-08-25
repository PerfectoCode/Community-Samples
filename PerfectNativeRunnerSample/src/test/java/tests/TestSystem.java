package tests;

import java.lang.reflect.Method;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import PerfectoNativeRunner.PerfectoRunner;

public class TestSystem {

	// the map to store the test results
	private Map<String, Object> testResults = new HashMap<String, Object>();

	// Executes the Native Tests
	// define Perfecto and Script details
	// Params
	// @1 Perfecto Host
	// @2 Perfecto username
	// @3 Perfecto password
	// @4 Perfect script key "Private:yourscript or Private:directory/yourscript
	// @5 Device Id to execute the script
	// @6 Number of times to loop and wait for the completion of the script ---
	// suggested value in the 1000s
	// @7 Number of milliseconds to wait between each status check of the script
	// ---- suggest 5000 milliseconds
	@Parameters({ "host", "username", "password", "scriptKey", "deviceId", "additionalParams", "cycles",
			"waitForCycles" })
	@Test
	public void NativeTests(String host, String username, String password, String scriptKey, String deviceId,
			String additionalParams, int cycles, long waitForCycles) throws Exception {
		PerfectoRunner pr = new PerfectoRunner();

		// executes the script and gathers the test results
		testResults = pr.executeScript(host, username, password, scriptKey, deviceId, additionalParams, cycles,
				waitForCycles);

		// to grab the test results use the following syntax
		// PerfectoRunner.availableReportOptions.<selected report
		// options>.toString()

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

		// Transactions are returned in its own map
		Map<String, String> transactions = (Map<String, String>) testResults
				.get(PerfectoRunner.availableReportOptions.transactions.toString());

		// loop the transactions to get them all
		int i = 1;
		for (String key : transactions.keySet()) {
			System.out.println("transactionName:" + key.toString());
			System.out.println("timer:" + transactions.get(key).toString().trim());
		}
	}
}