package utilities;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

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

import PerfectoNativeRunner.PerfectoRunner;

import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.perfecto.splunk.ReportingFactory;
import com.perfecto.splunk.SplunkReporting;
import com.perfecto.splunk.SplunkReportingCollector;


public class TestListenerNative implements ISuiteListener, ITestListener, IInvokedMethodListener {

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult arg0) {

	}

	@Override
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext arg0) {

	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		String result = "Fail";

		try {
			setDetails(result, arg0);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		try {
			setDetails("Skip", arg0);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDetails(String result, ITestResult testResult) throws NumberFormatException, Exception {
		Object currentClass = testResult.getMethod().getInstance();
		ClassHelperNative classHelper = ((ClassHelperNative) currentClass);

		if (result.equalsIgnoreCase("Skip")) {
			classHelper.setSplunk();
		}

		SplunkReportingCollector splunkReport = classHelper.getCollector();

		// Allows you to put any value into the reporting hashMap for submission
		// to splunk

		splunkReport.reporting.put("className", testResult.getMethod().getInstance().getClass().getName());

		try {
			splunkReport.reporting.put("testStatus", classHelper.testResults
					.get(PerfectoRunner.availableReportOptions.scriptStatus).toString());
		} catch (Exception ex) {
			splunkReport.reporting.put("testStatus", "Fail");
		}

		try {
			splunkReport.reporting.put("model",
					classHelper.testResults.get(PerfectoRunner.availableReportOptions.model).toString());
			splunkReport.reporting.put("os",
					classHelper.testResults.get(PerfectoRunner.availableReportOptions.os).toString());
			splunkReport.reporting.put("osVersion",
					classHelper.testResults.get(PerfectoRunner.availableReportOptions.osVersion).toString());
			splunkReport.reporting.put("device",
					classHelper.testResults.get(PerfectoRunner.availableReportOptions.deviceId).toString());
		} catch (Exception ex) {

		}

		try {
			splunkReport.reporting.put("testResults",classHelper.testResults
					.get(PerfectoRunner.availableReportOptions.scriptName).toString());
			splunkReport.reporting.put("reportId",classHelper.reportId = classHelper.testResults
					.get(PerfectoRunner.availableReportOptions.reportId).toString());
					splunkReport.reporting.put("executionId",classHelper.executionId = classHelper.testResults
					.get(PerfectoRunner.availableReportOptions.executionId).toString());
		} catch (Exception ex) {

		}

		try
		{
			int i = 1;
			Table<String, String, String> transactions = (Table<String, String, String>) classHelper.testResults
	                .get(PerfectoRunner.availableReportOptions.transactions);
	        for (Cell<String, String, String> cell : transactions.cellSet()) {
	            System.out.println("transactionSuccess:" + cell.getValue());
	            System.out.println("transactionName:" + cell.getRowKey());
	            System.out.println("transactionTimer: " + cell.getColumnKey());
	            
	            splunkReport.startTransaction("Step_" + i, cell.getRowKey());
				splunkReport.endTransaction("Step_" + i, Long.parseLong(cell.getColumnKey().trim()));
	            i++;
	        }
		}
		catch(Exception ex)
		{
			
		}
			
			
			

			
		

		if (result.equalsIgnoreCase("Fail")) {
			if (testResult.getThrowable() != null) {
				if (testResult.getThrowable().getStackTrace() != null) {
					StringWriter sw = new StringWriter();
					testResult.getThrowable().printStackTrace(new PrintWriter(sw));
					splunkReport.reporting.put("stackTrace", sw.toString());
					String[] swAr = sw.toString().split(":::");
					splunkReport.reporting.put("testName", swAr[0].split(":")[2].trim());
					splunkReport.reporting.put("reportId", swAr[2].split(":")[1].trim()+":"+swAr[2].split(":")[2].trim());
						splunkReport.reporting.put("executionId" , swAr[1].split(":")[1]);
					classHelper.reportId= swAr[2].split(":")[1].trim()+":"+swAr[2].split(":")[2].trim();					
				}
			}	
			
			splunkReport.reporting.put("stackTrace", classHelper.testResults
	                .get(PerfectoRunner.availableReportOptions.exception).toString());
		}

		if (!result.equalsIgnoreCase("Skip")) {
			// Sets the end time of the test
			// Divides the start and end time to create a test duration in
			// seconds
			// and finally converts the start/end time to real date formats
			splunkReport.testExecutionEnd();
		}

		splunkReport.reporting.put("methodName", testResult.getMethod().getMethodName());

		if (result.equalsIgnoreCase("Skip")) {
			// Submits the report to splunk in json format
			// Params
			// Title to append to the json
			// if null testName � methodName is used
			// otherwise custom title can be used

			splunkReport.submitReporting(testResult.getMethod().getMethodName());
		}
		
		classHelper.perfectoReport= classHelper.testResults
				.get(PerfectoRunner.availableReportOptions.reportUrl).toString();
		System.out.println(classHelper.perfectoReport);
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

		try {
			setDetails("Pass", arg0);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
