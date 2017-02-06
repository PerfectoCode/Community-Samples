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

import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;


public class TestSystem  {
	

	// the map to store the test results
	// Executes the Native Tests
	// define Perfecto and Script details
	// Params
	// @1 Perfecto Host
	// @2 Perfecto username
	// @3 Perfecto password
	// @4 Perfect script key "Private:yourscript or Private:directory/yourscript
	// @5 Device Id to execute the script
	// @6 Additional Params -- the format for the parameters
	// @7 Number of times to loop and wait for the completion of the script ---
	// suggested value in the 1000s
	// @8 Number of milliseconds to wait between each status check of the script
	// ---- suggest 5000 milliseconds
	@Parameters({ "host", "username", "password", "scriptKey", "deviceId",
			"additionalParams", "cycles", "waitForCycles" })
	@Test
	public void NativeTests(String host, String username, String password,
			String scriptKey, String deviceId, String additionalParams,
			int cycles, long waitForCycles) throws Exception {
		Map<PerfectoRunner.availableReportOptions, Object> testResults = new HashMap<PerfectoRunner.availableReportOptions, Object>();
		PerfectoRunner pr = new PerfectoRunner();

		// executes the script and gathers the test results

		// Executes the Native Tests
		// define Perfecto and Script details
		// Params
		// @1 Perfecto Host
		// @2 Perfecto username
		// @3 Perfecto password
		// @4 Perfect script key "Private:yourscript or
		// Private:directory/yourscript
		// @5 Device Id to execute the script
		// @6 Additional Params -- the format for the parameters
		// &param.url=m.newegg.com
		// @7 Number of times to loop and wait for the completion of the script
		// ---
		// suggested value in the 1000s
		// @8 Number of milliseconds to wait between each status check of the
		// script
		// ---- suggest 5000 milliseconds
		testResults = pr.executeScript(host, username, password, scriptKey,
				deviceId, additionalParams, cycles, waitForCycles, "d:\\testResults", "d:\\testResults", "d:\\testResults", "d:\\testResults", "d:\\testResults");

		System.out.println("ScriptStatus:"
				+ testResults.get(
						PerfectoRunner.availableReportOptions.scriptStatus)
						.toString());

		System.out.println("Model:"
				+ testResults.get(PerfectoRunner.availableReportOptions.model)
						.toString());

		System.out.println("Os:"
				+ testResults.get(PerfectoRunner.availableReportOptions.os)
						.toString());

		System.out.println("OSVersion:"
				+ testResults.get(
						PerfectoRunner.availableReportOptions.osVersion)
						.toString());

		System.out.println("DeviceId:"
				+ testResults.get(
						PerfectoRunner.availableReportOptions.deviceId)
						.toString());

		System.out.println("scriptName:"
				+ testResults.get(
						PerfectoRunner.availableReportOptions.scriptName)
						.toString());
		
		System.out.println("testName:" + testResults.get(
				PerfectoRunner.availableReportOptions.scriptName)
				.toString());

		System.out.println("reportId:"
				+ testResults.get(
						PerfectoRunner.availableReportOptions.reportId)
						.toString());

		System.out.println("executionId:"
				+ testResults.get(
						PerfectoRunner.availableReportOptions.executionId)
						.toString());
		
		System.out.println("xmlFileName:"
				+ testResults.get(
						PerfectoRunner.availableReportOptions.xmlFileName)
						.toString());

		System.out.println("xmlUrl:"
				+ testResults.get(
						PerfectoRunner.availableReportOptions.xmlUrl)
						.toString());


		if (testResults.get(PerfectoRunner.availableReportOptions.scriptStatus)
				.toString().equals("Fail")) {
			System.out.println("Exception: "
					+ testResults.get(
							PerfectoRunner.availableReportOptions.exception)
							.toString());
		}

		Map<String, String> variables = (Map<String, String>) testResults
				.get(PerfectoRunner.availableReportOptions.variables);

		int i = 1;
		for (String key : variables.keySet()) {
			System.out.println("variableName:" + key.toString());
			System.out.println("variableValue:"
					+ variables.get(key).toString().trim());
		}

		Table<String, String, String> transactions = (Table<String, String, String>) testResults
				.get(PerfectoRunner.availableReportOptions.transactions);
		for (Cell<String, String, String> cell : transactions.cellSet()) {
			System.out.println("transactionSuccess:" + cell.getValue());
			System.out.println("transactionName:" + cell.getRowKey());
			System.out.println("transactionTimer: " + cell.getColumnKey());
		}
		



	            System.out.println("MP4VideoUrl:"
	          + testResults.get(
	                  PerfectoRunner.availableReportOptions.recordingDownloadMp4Url)
	                  .toString());

	            System.out.println("FLVVideoUrl:"
	       + testResults.get(
	               PerfectoRunner.availableReportOptions.recordingDownloadFlvUrl)
	               .toString());

	            System.out.println("MP4VideoFileName:"
	       + testResults.get(
	               PerfectoRunner.availableReportOptions.recordingMp4FileName)
	               .toString());

	            System.out.println("FlvVideoFileName:"
	       + testResults.get(
	               PerfectoRunner.availableReportOptions.recordingFlvFileName)
	               .toString());

	            System.out.println("pcapFileName:"
	       + testResults.get(
	               PerfectoRunner.availableReportOptions.pcapFileName)
	               .toString());

	            System.out.println("pcalUrl:"
	       + testResults.get(
	               PerfectoRunner.availableReportOptions.pcapUrl)
	               .toString());

	            System.out.println("vitalsFileName:"
	 + testResults.get(
	         PerfectoRunner.availableReportOptions.vitalsFileName)
	         .toString());

	            System.out.println("vitalsUrl:"
	       + testResults.get(
	               PerfectoRunner.availableReportOptions.vitalsUrl)
	               .toString());


	            pr.downloadFLVVideo();

	            pr.downloadMP4Video();

	            pr.downloadPCAP();

	            pr.downloadVitals();
	            
	            pr.downloadXMLReport();
	            
	            pr.downloadPDFReport();
		
		

	}
}