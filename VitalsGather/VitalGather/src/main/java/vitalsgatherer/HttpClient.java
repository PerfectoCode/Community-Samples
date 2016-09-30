package vitalsgatherer;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class HttpClient {
	private static final String UTF_8 = "UTF-8";
	private Proxy proxy;

	// constructor for the Proxy connection
	public HttpClient(Proxy proxy) {
		this.proxy = proxy;
	}

	// default contructor
	public HttpClient() {

	}

	// makes the api call
	public String sendRequest(String url) throws IOException, URISyntaxException {
		URL obj = new URL(url);
		HttpURLConnection con = null;

		// setting proxy if available
		if (proxy != null) {
			con = (HttpURLConnection) obj.openConnection(proxy);
		} else {
			con = (HttpURLConnection) obj.openConnection();
		}
		System.out.println("Sending GET request to URL : " + obj.toURI());
		con.setRequestMethod("GET");
		con.connect();

		int responseCode = con.getResponseCode();
		String response = "";

		if (responseCode > HttpURLConnection.HTTP_OK) {
			handleError(con);
		} else {
			response = getStream(con);
		}

		System.out.println("Response Code : " + responseCode);
		System.out.println("Response message: " + response.toString());

		return response;
	}

	// processes errors returned by server
	private void handleError(HttpURLConnection connection) throws IOException {
		String msg = "Request failed: ";
		InputStream errorStream = connection.getErrorStream();
		if (errorStream != null) {
			InputStreamReader inputStreamReader = new InputStreamReader(errorStream, UTF_8);
			BufferedReader bufferReader = new BufferedReader(inputStreamReader);
			try {
				StringBuilder builder = new StringBuilder();
				String outputString;
				while ((outputString = bufferReader.readLine()) != null) {
					if (builder.length() != 0) {
						builder.append("\n");
					}
					builder.append(outputString);
				}
				String response = builder.toString();
				msg += "Response: " + response;
			} finally {
				bufferReader.close();
			}
		}
		System.out.println(msg);
		throw new RuntimeException(msg);
	}

	// gets body of the response
	private String getStream(HttpURLConnection connection) throws IOException {
		InputStream inputStream = connection.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, UTF_8);
		BufferedReader bufferReader = new BufferedReader(inputStreamReader);
		String response = "";
		try {
			StringBuilder builder = new StringBuilder();
			String outputString;
			while ((outputString = bufferReader.readLine()) != null) {
				if (builder.length() != 0) {
					builder.append("\n");
				}
				builder.append(outputString);
			}
			response = builder.toString();
		} finally {
			bufferReader.close();
		}
		return response;
	}

	// parses the json response body
	public JSONArray getJsonArray(String json, String node) {

		JSONObject o = new JSONObject(json);

		JSONArray result = ((JSONArray) o.get(node));

		System.out.println(result);
		return result;
	}

	public String[] getJsonString(JSONArray json, String node) {
		String[] a = new String[json.length()];
		for (int i = 0; i < json.length(); i++) {
			a[i] = json.getJSONObject(i).get(node).toString();
		}

		return a;
	}

	public String getXPathValue(String xml, String XpathString)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		NodeList result = getXPathList(xml, XpathString);
		if (result.item(0) != null) {
			return result.item(0).getTextContent();
		} else {
			return null;
		}
	}

	public NodeList getXPathList(String xml, String XpathString)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(XpathString);
		return (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
	}

}
