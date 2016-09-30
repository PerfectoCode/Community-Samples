package vitalsgatherer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.json.JSONArray;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class VitalsGather {

	private static Proxy proxy = null;

	// proxy constructor
	public VitalsGather(Proxy proxy) {
		this.proxy = proxy;
	}

	// default constructor
	public VitalsGather() {
	}

	public enum availableTimeTypes {
		started, completed
	}

	public enum availableFileTypes {
		csv, json
	}

	public enum resultOptions {
		statusWindows, statusTransactionWindows, jsonWindows, jsonTransactionWindows, fullPathJsonWindows, fullPathCsvWindows, fullPathTransactionCsvWindows, fullPathTransactionJsonWindows, statusAndroid, statusTransactionAndroid, jsonAndroid, jsonTransactionAndroid, fullPathJsonAndroid, fullPathCsvAndroid, fullPathTransactionCsvAndroid, fullPathTransactionJsonAndroid, statusIos, statusTransactionIos, jsonIos, jsonTransactionIos, fullPathJsonIos, fullPathCsvIos, fullPathTransactionCsvIos, fullPathTransactionJsonIos
	}

	public Map<resultOptions, String> results = new HashMap<resultOptions, String>();

	@SuppressWarnings("deprecation")
	public void main(String[] args) throws IOException, URISyntaxException, ParserConfigurationException, SAXException,
			XPathExpressionException {
		/*
		 * // TODO Auto-generated method stub
		 * getVitals("demo.perfectomobile.com", "jeremyp@perfectomobile.com",
		 * "perfecto123", "PRIVATE:NewEggSite", new Date("09/12/2016 19:51:15"),
		 * "", availableTimeTypes.started, "dataz", "d:\\", true);
		 */
	}

	public Map<resultOptions, String> getVitals(String host, String username, String password, String scriptKey,
			Date startTime, String offSet, availableTimeTypes att, String fileName, String fileLocation,
			boolean outputJson, boolean overwrite) throws IOException, URISyntaxException, ParserConfigurationException,
			SAXException, XPathExpressionException {
		HttpClient hc;
		if (proxy != null) {
			hc = new HttpClient(proxy);
		} else {
			hc = new HttpClient();
		}

		long startTimeEpoch = (startTime.getTime());

		String csv = "";
		String csvTrans = "";
		File csvFile = null;
		File csvFileTrans = null;
		FileWriter fw = null;
		CSVWriter writer = null;
		String response = "";
		String os = "";

		if (!scriptKey.equals("")) {
			if (!offSet.equals("")) {
				response = hc.sendRequest("https://" + host + "/services/executions?operation=list&scriptKey="
						+ scriptKey.replace(" ", "%20") + ".xml&user=" + username + "&completed=true&password="
						+ password + "&time.type=" + att.toString() + "&time.anchor=" + startTimeEpoch + "&time.offset="
						+ offSet);
			} else {
				response = hc.sendRequest("https://" + host + "/services/executions?operation=list&scriptKey="
						+ scriptKey.replace(" ", "%20") + ".xml&user=" + username + "&completed=true&password="
						+ password + "&time.type=" + att.toString() + "&time.anchor=" + startTimeEpoch);
			}
		} else {
			if (!offSet.equals("")) {
				response = hc.sendRequest("https://" + host + "/services/executions?operation=list&user=" + username
						+ "&password=" + password + "&time.type=" + att.toString() + "&completed=true&time.anchor="
						+ startTimeEpoch + "&time.offset=" + offSet);
			} else {
				response = hc.sendRequest("https://" + host + "/services/executions?operation=list&user=" + username
						+ "&password=" + password + "&time.type=" + att.toString() + "&completed=true&time.anchor="
						+ startTimeEpoch);
			}
		}

		JSONArray ja = hc.getJsonArray(response, "executions");

		String[] rk = hc.getJsonString(ja, "reportKey");

		String[] ei = hc.getJsonString(ja, "executionId");
		boolean foundAndroid = false;
		boolean foundIos = false;
		boolean foundWindows = false;
		boolean transFoundAndroid = false;
		boolean transFoundIos = false;
		boolean transFoundWindows = false;
		boolean overCompleteAndroid = false;
		boolean overCompleteIos = false;
		boolean overCompleteWindows = false;
		for (int i = 0; i < rk.length; i++) {

			response = hc.sendRequest("https://" + host + "/services/reports/" + rk[i].replace(" ", "%20")
					+ "?operation=download&user=" + username + "&password=" + password + "&responseformat=xml");

			os = hc.getXPathValue(response, "//*[@displayName='OS']/following-sibling::value");
			if (os == null) {
				continue;
			}

			if (overwrite && !overCompleteAndroid && os.equals("Android")) {
				String jsonFilePath = fileLocation + fileName + startTimeEpoch + os + ".json";
				String csvFilePath = fileLocation + fileName + startTimeEpoch + os + ".csv";
				String transactionCSVFilePath = fileLocation + fileName + startTimeEpoch + os + "Transaction.csv";
				String transactionJsonFilePath = fileLocation + fileName + startTimeEpoch + os + "Transaction.json";

				results.put(resultOptions.fullPathCsvAndroid, csvFilePath);
				results.put(resultOptions.fullPathJsonAndroid, jsonFilePath);
				results.put(resultOptions.fullPathTransactionJsonAndroid, transactionJsonFilePath);
				results.put(resultOptions.fullPathTransactionCsvAndroid, transactionCSVFilePath);

				File csvTemp = new File(csvFilePath);
				File jsonTemp = new File(jsonFilePath);
				File jsonTransTemp = new File(transactionJsonFilePath);
				File csvTransTemp = new File(transactionCSVFilePath);
				csvTemp.delete();
				jsonTemp.delete();
				jsonTransTemp.delete();
				csvTransTemp.delete();
				overCompleteAndroid = true;
			}

			if (overwrite && !overCompleteIos && os.equals("iOS")) {
				String jsonFilePath = fileLocation + fileName + startTimeEpoch + os + ".json";
				String csvFilePath = fileLocation + fileName + startTimeEpoch + os + ".csv";
				String transactionCSVFilePath = fileLocation + fileName + startTimeEpoch + os + "Transaction.csv";
				String transactionJsonFilePath = fileLocation + fileName + startTimeEpoch + os + "Transaction.json";

				results.put(resultOptions.fullPathCsvIos, csvFilePath);
				results.put(resultOptions.fullPathJsonIos, jsonFilePath);
				results.put(resultOptions.fullPathTransactionJsonIos, transactionJsonFilePath);
				results.put(resultOptions.fullPathTransactionCsvIos, transactionCSVFilePath);

				File csvTemp = new File(csvFilePath);
				File jsonTemp = new File(jsonFilePath);
				File jsonTransTemp = new File(transactionJsonFilePath);
				File csvTransTemp = new File(transactionCSVFilePath);
				csvTemp.delete();
				jsonTemp.delete();
				jsonTransTemp.delete();
				csvTransTemp.delete();
				overCompleteIos = true;
			}

			if (overwrite && !overCompleteWindows && os.equals("Windows")) {
				String jsonFilePath = fileLocation + fileName + startTimeEpoch + os + ".json";
				String csvFilePath = fileLocation + fileName + startTimeEpoch + os + ".csv";
				String transactionCSVFilePath = fileLocation + fileName + startTimeEpoch + os + "Transaction.csv";
				String transactionJsonFilePath = fileLocation + fileName + startTimeEpoch + os + "Transaction.json";

				results.put(resultOptions.fullPathCsvWindows, csvFilePath);
				results.put(resultOptions.fullPathJsonWindows, jsonFilePath);
				results.put(resultOptions.fullPathTransactionJsonWindows, transactionJsonFilePath);
				results.put(resultOptions.fullPathTransactionCsvWindows, transactionCSVFilePath);

				File csvTemp = new File(csvFilePath);
				File jsonTemp = new File(jsonFilePath);
				File jsonTransTemp = new File(transactionJsonFilePath);
				File csvTransTemp = new File(transactionCSVFilePath);
				csvTemp.delete();
				jsonTemp.delete();
				jsonTransTemp.delete();
				csvTransTemp.delete();
				overCompleteWindows = true;
			}

			csv = fileLocation + fileName + startTimeEpoch + os + ".csv";
			csvTrans = fileLocation + fileName + startTimeEpoch + os + "Transaction.csv";
			csvFile = new File(csv);
			csvFileTrans = new File(csvTrans);
			fw = new FileWriter(csv, true);
			writer = new CSVWriter(fw);
			String vitalFile = hc.getXPathValue(response, "//dataItem/attachment[contains(text(),'vitals')]");

			if (vitalFile != null) {
				CSVReader reader = null;
				if (csvFile.length() <= 0) {
					reader = readCSV(new URL("https://" + host + "/services/reports/" + rk[i].replace(" ", "%20")
							+ "?operation=monitor&user=" + username + "&password=" + password + "&attachment="
							+ vitalFile), true);
				} else {
					reader = readCSV(new URL("https://" + host + "/services/reports/" + rk[i].replace(" ", "%20")
							+ "?operation=monitor&user=" + username + "&password=" + password + "&attachment="
							+ vitalFile), false);
				}
				writer.writeAll(reader.readAll());
				if (os.equals("iOS")) {
					foundIos = true;
				} else if (os.equals("Android")) {
					foundAndroid = true;
				} else if (os.equals("Windows")) {
					foundWindows = true;
				}

			}

			writer.close();

			Table<String, String, String> transactions = HashBasedTable.create();

			String transName = "";
			String transTimer = "";
			String time = "";
			String nText = "";
			String nText2 = "";
			String nText3 = "";
			NodeList nodeL = hc.getXPathList(response,
					"//name[@displayName=\"Timer report\"]/parent::info/following-sibling::parameters/*/name[@displayName=\"Description\"]/following-sibling::value");
			NodeList nodeL2 = hc.getXPathList(response,
					"//name[@displayName=\"Timer report\"]/parent::info/following-sibling::parameters/*/name[@displayName=\"Result\"]/following-sibling::value");
			NodeList nodeL3 = hc.getXPathList(response,
					"//name[@displayName=\"Timer report\"]/parent::info/times/flowTimes/end/millis");
			for (int z = 0; z < nodeL.getLength(); z++) {

				nText = nodeL.item(z).getTextContent();
				nText2 = nodeL2.item(z).getTextContent();
				nText3 = nodeL3.item(z).getTextContent();
				transName = nText;
				transTimer = nText2;
				time = nText3;

				transactions.put(transName, transTimer, time);

				if (os.equals("iOS")) {
					transFoundIos = true;
				} else if (os.equals("Android")) {
					transFoundAndroid = true;
				} else if (os.equals("Windows")) {
					transFoundWindows = true;
				}

			}

			if (os.equals("iOS")) {
				if (foundIos) {
					results.put(resultOptions.statusIos, "success");
				}
				if (transFoundIos) {
					results.put(resultOptions.statusTransactionIos, "success");
					if (csvFileTrans.length() <= 0) {
						MapToCSV(transactions, results.get(resultOptions.fullPathTransactionCsvIos),
								rk[i].replace(" ", "%20"), username, true);
					} else {
						MapToCSV(transactions, results.get(resultOptions.fullPathTransactionCsvIos),
								rk[i].replace(" ", "%20"), username, false);
					}
				}
			} else if (os.equals("Android")) {
				if (foundAndroid) {
					results.put(resultOptions.statusAndroid, "success");

				}
				if (transFoundAndroid) {
					results.put(resultOptions.statusTransactionAndroid, "success");
					if (csvFileTrans.length() <= 0) {
						MapToCSV(transactions, results.get(resultOptions.fullPathTransactionCsvAndroid),
								rk[i].replace(" ", "%20"), username, true);
					} else {
						MapToCSV(transactions, results.get(resultOptions.fullPathTransactionCsvAndroid),
								rk[i].replace(" ", "%20"), username, false);
					}
				}
			} else if (os.equals("Windows")) {
				if (foundWindows) {
					results.put(resultOptions.statusWindows, "success");
				}
				if (transFoundWindows) {
					results.put(resultOptions.statusTransactionWindows, "success");
					if (csvFileTrans.length() <= 0) {
						MapToCSV(transactions, results.get(resultOptions.fullPathTransactionCsvWindows),
								rk[i].replace(" ", "%20"), username, true);
					} else {
						MapToCSV(transactions, results.get(resultOptions.fullPathTransactionCsvWindows),
								rk[i].replace(" ", "%20"), username, false);
					}
				}
			}
		}

		if (results.get(resultOptions.statusAndroid) != null) {
			if (results.get(resultOptions.statusAndroid).equals("success")) {
				if (outputJson) {
					CSVToJson(new File(results.get(resultOptions.fullPathCsvAndroid)),
							new File(results.get(resultOptions.fullPathJsonAndroid)));
				}
				results.put(resultOptions.jsonAndroid,
						readAsJson(readObjectsFromCsv(new File(results.get(resultOptions.fullPathCsvAndroid)))));
			} else {
				results.put(resultOptions.statusAndroid, "CSV data for Android not found with parameters provided");
				System.out.println("CSV data for Android not found with parameters provided");
			}

		} else {
			results.put(resultOptions.statusAndroid, "CSV data for Android not found with parameters provided");
			System.out.println("CSV data for Android not found with parameters provided");
		}

		if (results.get(resultOptions.statusTransactionAndroid) != null) {
			if (results.get(resultOptions.statusTransactionAndroid).equals("success")) {

				if (outputJson) {
					CSVToJson(new File(results.get(resultOptions.fullPathTransactionCsvAndroid)),
							new File(results.get(resultOptions.fullPathTransactionJsonAndroid)));
				}

				results.put(resultOptions.jsonTransactionAndroid, readAsJson(
						readObjectsFromCsv(new File(results.get(resultOptions.fullPathTransactionCsvAndroid)))));

			} else {
				results.put(resultOptions.statusTransactionAndroid,
						"Transaction data for Android not found with parameters provided");
				System.out.println("Transaction data for Android not found with parameters provided");
			}
		} else {
			results.put(resultOptions.statusTransactionAndroid,
					"Transaction data for Android not found with parameters provided");
			System.out.println("Transaction data for Android not found with parameters provided");

		}

		if (results.get(resultOptions.statusIos) != null) {
			if (results.get(resultOptions.statusIos).equals("success")) {
				if (outputJson) {
					CSVToJson(new File(results.get(resultOptions.fullPathCsvIos)),
							new File(results.get(resultOptions.fullPathJsonIos)));
				}
				results.put(resultOptions.jsonIos,
						readAsJson(readObjectsFromCsv(new File(results.get(resultOptions.fullPathCsvIos)))));
			} else {
				results.put(resultOptions.statusIos, "CSV data for iOS not found with parameters provided");
				System.out.println("CSV data for iOS not found with parameters provided");
			}
		} else {
			results.put(resultOptions.statusIos, "CSV data for iOS not found with parameters provided");
			System.out.println("CSV data for iOS not found with parameters provided");
		}

		if (results.get(resultOptions.statusTransactionIos) != null) {
			if (results.get(resultOptions.statusTransactionIos).equals("success")) {

				if (outputJson) {
					CSVToJson(new File(results.get(resultOptions.fullPathTransactionCsvIos)),
							new File(results.get(resultOptions.fullPathTransactionJsonIos)));
				}

				results.put(resultOptions.jsonTransactionIos,
						readAsJson(readObjectsFromCsv(new File(results.get(resultOptions.fullPathTransactionCsvIos)))));
			} else {
				results.put(resultOptions.statusTransactionIos,
						"Transaction data for iOS not found with parameters provided");
				System.out.println("Transaction data for iOS not found with parameters provided");
			}
		} else {
			results.put(resultOptions.statusTransactionIos,
					"Transaction data for iOS not found with parameters provided");
			System.out.println("Transaction data for iOS not found with parameters provided");
		}

		if (results.get(resultOptions.statusWindows) != null) {
			if (results.get(resultOptions.statusWindows).equals("success")) {
				if (outputJson) {
					CSVToJson(new File(results.get(resultOptions.fullPathCsvWindows)),
							new File(results.get(resultOptions.fullPathJsonWindows)));
				}
				results.put(resultOptions.jsonWindows,
						readAsJson(readObjectsFromCsv(new File(results.get(resultOptions.fullPathCsvWindows)))));
			} else {
				results.put(resultOptions.statusWindows, "CSV data for Windows not found with parameters provided");
				System.out.println("CSV data for Windows not found with parameters provided");
			}
		} else {
			results.put(resultOptions.statusWindows, "CSV data for Windows not found with parameters provided");
			System.out.println("CSV data for Windows not found with parameters provided");
		}

		if (results.get(resultOptions.statusTransactionWindows) != null) {
			if (results.get(resultOptions.statusTransactionWindows).equals("success")) {

				if (outputJson) {
					CSVToJson(new File(results.get(resultOptions.fullPathTransactionCsvWindows)),
							new File(results.get(resultOptions.fullPathTransactionJsonWindows)));
				}

				results.put(resultOptions.jsonTransactionWindows, readAsJson(
						readObjectsFromCsv(new File(results.get(resultOptions.fullPathTransactionCsvWindows)))));
			} else {
				results.put(resultOptions.statusTransactionWindows,
						"Transaction data for Windows not found with parameters provided");
				System.out.println("Transaction data for Windows not found with parameters provided");
			}
		} else {
			results.put(resultOptions.statusTransactionWindows,
					"Transaction data for Windows not found with parameters provided");
			System.out.println("Transaction data for Windows not found with parameters provided");
		}

		return results;

	}

	public CSVReader readCSV(URL url, boolean headers) throws IOException {
		CSVReader reader = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		if (headers) {
			reader = new CSVReader(in, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, 0);
		} else {
			reader = new CSVReader(in, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, 1);
		}

		return reader;
	}

	public String CSVToJson(File in, File out) throws IOException {
		List<Map<?, ?>> data = readObjectsFromCsv(in);
		return writeAsJson(data, out);
	}

	public void MapToCSV(Table<String, String, String> results, String csv, String rk, String username,
			boolean header) {

		try (FileWriter writer = new FileWriter(csv, true)) {
			if (header) {
				writer.write("\"Time\",\"Transaction\",\"Timer\",\"ReportURL\"\r\n");
			}
			int counter = 0;
			String reportURL = "<a href='https://demo.perfectomobile.com/nexperience/singletest/report/?reportRepositoryKey="
					+ rk + "&ownderId=" + username + "' target='_about' >Perfecto Url</a>";

			for (Cell<String, String, String> cell : results.cellSet()) {

				String out = "\"" + cell.getValue() + "\",\"" + cell.getRowKey() + "\",\"" + cell.getColumnKey()
						+ "\",\"" + reportURL + "\"\r\n";

				writer.write(out);
			}

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Map<?, ?>> readObjectsFromCsv(File file) throws IOException {
		CsvSchema bootstrap = CsvSchema.emptySchema().withHeader();
		CsvMapper csvMapper = new CsvMapper();
		MappingIterator<Map<?, ?>> mappingIterator = csvMapper.reader(Map.class).with(bootstrap).readValues(file);

		return mappingIterator.readAll();
	}

	public String writeAsJson(List<Map<?, ?>> data, File file) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(file, data);
		return readAsJson(data);
	}

	public String readAsJson(List<Map<?, ?>> data) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(data);
	}

}
