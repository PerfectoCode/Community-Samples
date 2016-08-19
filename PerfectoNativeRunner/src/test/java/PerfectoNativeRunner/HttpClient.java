package PerfectoNativeRunner;

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
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class HttpClient {
	private static final String UTF_8 = "UTF-8";

	public static String sendRequest(String url) throws IOException, URISyntaxException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		con.connect();

		int responseCode = con.getResponseCode();
		String response = "";

		if (responseCode > HttpURLConnection.HTTP_OK) {
			handleError(con);
		} else {
			response = getStream(con);
		}

		System.out.println("\nSending 'GET' request to URL : " + obj.toURI());
		System.out.println("Response Code : " + responseCode);
		System.out.println("Response message: " + response.toString());

		return response;
	}

	private static void handleError(HttpURLConnection connection) throws IOException {
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
		throw new RuntimeException(msg);
	}

	private static String getStream(HttpURLConnection connection) throws IOException {
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

	public static String getXMLValue(String xml, String node)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilder newDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document parse = newDocumentBuilder.parse(new ByteArrayInputStream(xml.getBytes()));
		
		return parse.getElementsByTagName(node).item(0).getTextContent();
	}

	public static String getJsonValue(String json, String node) {
		JSONObject obj = new JSONObject(json);
		String n = obj.getString(node);
		System.out.println(n);
		return n;
	}
}
