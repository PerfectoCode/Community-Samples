package PerfectoNativeRunner;

import java.awt.List;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PerfectoRunner {

	public enum availableReportOptions {
		executionId, reportId, scriptName, scriptStatus, deviceId, os, osVersion, model, transactions, reportUrl
	}

	public static void main(String[] args) throws DOMException, Exception {
		/*Map<String, Object> testResults = new HashMap<String, Object>();
		String host = "demo.perfectomobile.com";
		String username = "jeremyp@perfectomobile.com";
		testResults = executeScript(host, username, "perfecto123", "Public:/Telstra/netflixchecker",
				"EBB620E023B3FA0A72CAF31B46A21A51213C590E");*/
		/*
		 * System.out.println("ScriptStatus:" +
		 * testResults.get(perfectoRunner.availableReportOptions.scriptStatus.
		 * toString()).toString());
		 * 
		 * System.out.println("Model:" +
		 * testResults.get(perfectoRunner.availableReportOptions.model.toString(
		 * )).toString());
		 * 
		 * System.out.println("Os:" +
		 * testResults.get(perfectoRunner.availableReportOptions.os.toString()).
		 * toString());
		 * 
		 * System.out.println("OSVersion:" +
		 * testResults.get(perfectoRunner.availableReportOptions.osVersion.
		 * toString()).toString());
		 * 
		 * System.out.println("DeviceId:" +
		 * testResults.get(perfectoRunner.availableReportOptions.deviceId.
		 * toString()).toString());
		 * 
		 * System.out.println("scriptName:" +
		 * testResults.get(perfectoRunner.availableReportOptions.scriptName.
		 * toString()).toString());
		 * 
		 * System.out.println("reportId:" +
		 * testResults.get(perfectoRunner.availableReportOptions.reportId.
		 * toString()).toString()); String reportId=
		 * testResults.get(perfectoRunner.availableReportOptions.reportId.
		 * toString()).toString(); System.out.println("executionId:" +
		 * testResults.get(perfectoRunner.availableReportOptions.executionId.
		 * toString()).toString());
		 * 
		 * Map<String, String> transactions = (Map<String, String>) testResults
		 * .get(perfectoRunner.availableReportOptions.transactions.toString());
		 * 
		 * int i = 1; for (String key : transactions.keySet()) {
		 * System.out.println("transactionName:" + key.toString());
		 * System.out.println("timer:" +
		 * transactions.get(key).toString().trim()); }
		 * 
		 * 
		 * System.out.println("reportURL:" +
		 * testResults.get(perfectoRunner.availableReportOptions.reportUrl.
		 * toString()).toString());
		 */

	}

	public static Map<String, Object> executeScript(String host, String username, String password, String scriptKey,
			String deviceId) throws DOMException, Exception {
		String executionId = "";
		String reportId = "";

		HttpClient hc = new HttpClient();
		String response = hc.sendRequest("https://" + host + "/services/executions?operation=execute&scriptkey="
				+ scriptKey + ".xml&responseformat=xml&param.DUT=" + deviceId + "&user=" + username + "&password="
				+ password + "");

		if (response.contains("xml")) {
			executionId = hc.getXMLValue(response, "executionId");
			for (int i = 0; i < 1000; i++) {

				response = hc.sendRequest("https://" + host + "/services/executions/" + executionId
						+ "?operation=status&user=" + username + "&password=" + password + "");
				if (response.contains("xml")) {
					if (!hc.getJsonValue(response, "status").equals("Completed")) {
						Thread.sleep(10000);
					} else {
						reportId = hc.getJsonValue(response, "reportKey");
						break;
					}
				}
			}
		}

		response = hc.sendRequest("https://" + host + "/services/reports/" + reportId + "?operation=download&user="
				+ username + "&password=" + password + "&responseformat=xml");

		Map<String, Object> testResults = new HashMap<String, Object>();

		testResults = parseReport(response, executionId, reportId);
		testResults.put("reportUrl",
				"https://" + host + "/nexperience/Report.html?reportId=SYSTEM%3Adesigns%2Freport&key="
						+ reportId.replace(".xml", "") + "%2Exml&liveUrl=rtmp%3A%2F%2F" + host.replace(".", "%2E")
						+ "%2Fengine&appUrl=https%3A%2F%2F" + host.replace(".", "%2E") + "%2Fnexperience&username="
						+ username);
		return testResults;
	}

	public static Map<String, Object> parseReport(String xml, String executionId, String reportId)
			throws DOMException, Exception {

		DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
		Map<String, Object> testResults = new HashMap<String, Object>();
		String nText = "";

		// Miscellaneous
		String scriptName = parse.getElementsByTagName("name").item(0).getTextContent();

		NodeList status = parse.getElementsByTagName("status");

		Element statusSub = (Element) status.item(0);

		String scriptStatus = "";

		if (statusSub.getElementsByTagName("success").item(0).getTextContent().equals("true")) {
			scriptStatus = "Pass";
			testResults.put("scriptStatus", scriptStatus);
		} else {
			scriptStatus = "Fail";
			testResults.put("scriptStatus", scriptStatus);
			if (!statusSub.getElementsByTagName("code").item(0).getTextContent().equals("CompletedWithErrors"))

			{
				if (!statusSub.getElementsByTagName("code").item(0).getTextContent().equals("Failed")) {

					if (!statusSub.getElementsByTagName("failedActions").equals("0")) {
						throw new Exception("ScriptName:" + scriptName + " ::: ExecutionId: " + executionId
								+ " ::: reportId: " + reportId + " ::: exception: Exeception "
								+ statusSub.getElementsByTagName("description").item(0).getTextContent());
					}
				}
			}
		}

		NodeList handsets = parse.getElementsByTagName("handset");

		Element handsetsSub = (Element) handsets.item(0);

		String deviceId = "";
		String os = "";
		String model = "";
		String osVersion = "";

		if (xml.contains("displayName=\"Phone Number\"")) {

			deviceId = handsetsSub.getElementsByTagName("value").item(1).getTextContent();
			os = handsetsSub.getElementsByTagName("value").item(16).getTextContent();
			model = handsetsSub.getElementsByTagName("value").item(12).getTextContent();
			osVersion = handsetsSub.getElementsByTagName("value").item(17).getTextContent();
		} else {
			deviceId = handsetsSub.getElementsByTagName("value").item(1).getTextContent();
			os = handsetsSub.getElementsByTagName("value").item(14).getTextContent();
			model = handsetsSub.getElementsByTagName("value").item(10).getTextContent();
			osVersion = handsetsSub.getElementsByTagName("value").item(15).getTextContent();
		}

		testResults.put("executionId", executionId);
		testResults.put("reportId", reportId);
		testResults.put("scriptName", scriptName);
		testResults.put("scriptStatus", scriptStatus);
		testResults.put("deviceId", deviceId);
		testResults.put("os", os);
		testResults.put("osVersion", osVersion);
		testResults.put("model", model);

		// Transactions
		Map<String, String> transactions = new HashMap<String, String>();
		String transName = "";
		String transTimer = "";
		NodeList nodeL = parse.getElementsByTagName("description");
		for (int i = 0; i < nodeL.getLength(); i++) {
			nText = nodeL.item(i).getTextContent();
			if (nText.contains("Value of ux timer")) {
				transName = nText.split("Value of ux timer ")[1].split(" is ")[0];
				transTimer = nText.split(" is ")[1].split("milliseconds")[0];
				transactions.put(transName, transTimer);
			}
		}
		testResults.put("transactions", transactions);

		return testResults;
	}
}
